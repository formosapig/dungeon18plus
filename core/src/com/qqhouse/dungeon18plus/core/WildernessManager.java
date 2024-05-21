package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignAction;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.campaign.Giant;
import com.qqhouse.dungeon18plus.struct.campaign.GuardInfo;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.campaign.Campaigner;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.struct.Operation;
import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

// XXX 0415 己經改成支緩兩個 team 對戰的模式了.嘿嘿~~
public class WildernessManager {

	// status define.
	private static final int INITIAL		   = 0x00000001;	// create class
	public static final int START		   = 0x00000002;	// press start
	private static final int BATTLE		   = 0x00000004;	// tick can work.
    private static final int RESULT		   = 0x00000008;	// after result and summary, should update all

	// update
    private static final int UPDATE_HISTORY = 0x00001000;	// update history.
    private static final int UPDATE_GIANT   = 0x00002000;	// update giant status.
    private static final int UPDATE_LEGION  = 0x00004000;	// update legion status.
    private static final int UPDATE_ALL     = UPDATE_HISTORY | UPDATE_GIANT | UPDATE_LEGION; // update everything.
    private static final int CLEAR_UPDATE   = 0xFFFF00FF;	// clear update
    private static final int RESULT_UPDATE  = RESULT | UPDATE_ALL;
	
	
	// game result
    private static final int GIANT_WIN	   = 0x00100000;	// giant win, you get nothing.
    private static final int LEGION_WIN     = 0x00200000;	// legion win, get soul.
    private static final int TIME_UP        = 0x00400000;	// time up, legion lose, you get nothing but giant exp.

	// state
	private int state;
	
	public ArrayList<Campaigner> giants;
	public ArrayList<Campaigner> legions;
	public ArrayList<Object> battleHistory;
	
	public int time;
	
	//public boolean start;
	
	private CampaignResult store;
	
	private int mKillLegionCount;
	
	// XXX 在一個 for 迴圈中,使用 new Random().nextInt(n) 可能會一直取到同樣的值,因為
	//     new Random() 使用時間當種子,而在一個 for loop 中,時間的種子幾乎是一樣的.
	private Random mRand;
	
	// skill record
	private Map<Integer, ArrayList<UniqueSkill>> mSkillRecord;
	
	public WildernessManager(GiantRace race) {
		
		// initial giant and guard
		giants = new ArrayList<>();
		GuardInfo giantGuard = new GuardInfo();
		Giant giant = new Giant(race);
		giant.ourGuard = giantGuard;
		giant.icon = giant.race.icon;
		giant.bg = "bg_boss";
		giants.add(giant);
		
		// initial time
		time = Game.Setting.CAMPAIGN_MAX_TIME;
		
		// initial kill count
		mKillLegionCount = 0;
		
		// initial legion
		legions = new ArrayList<>();
		GuardInfo legionGuard = new GuardInfo();
		
		// mHero from
		int heroFrom = 0;
		/*for (Veteran veteran : GameData.getInstance().getLegionData()) {
			Legion hero = new Legion(veteran);
			// mHero from
			hero.heroFrom = heroFrom++;
			hero.action = veteran.equipment.skill.get(veteran.mastery);
			hero.ourGuard = legionGuard;
			hero.icon = hero.heroClass.key;
			hero.bg = hero.heroClass.getBackgroundRes();
			// add mHero
			legions.add(hero);
		}*/
		
		state = INITIAL;
		
		// battle history
		store = new CampaignResult();
		battleHistory = new ArrayList<>();
		
		// rand
		mRand = new Random();
		
		// skill record
		mSkillRecord = new TreeMap<>();
	}


	// can call and call again.
	public void challenge() {
	    // initial legion

        // initial giant

        // ...





    }


	// check if can start
	public boolean start() {
		if ((state & INITIAL) == 0)
			return false;
		
		// initial start data
		for (Campaigner legion : legions) {
			legion.maxLife = legion.life;
			legion.nextLife = legion.maxLife;
			// fast character can use skill fast.
			legion.coolDown = mRand.nextInt(legion.speed * 10);
		}
		
		state = BATTLE;
		return true;
	}
	
	public boolean useSkill(int index) {
		if ((state & BATTLE) == 0)
			return false;
		
		Campaigner legion = legions.get(index);
		// 1. cool down != 0
		// 2. !alive
		// 3. is auto skill
		// can not use skill
		if (0 < legion.coolDown || !legion.alive || legion.action.auto)
			return false;
		
		// battle action
		addBattleAction(legion);

		// do action
		doAction(legion, legions, giants);
		
		// flush all battle result
		flushBattleResult();
		
		// reset cool down
		legion.coolDown = legion.action.coolDown;
		
		return true;
	}
	
	private void doAction(Campaigner source, ArrayList<Campaigner> friends, ArrayList<Campaigner> enemies) {
		
		for (Operation2 opt : source.action.operations) {
			if (opt.isDamage()) {
				if (opt.isAll()) {
					// attack all.
					for (Campaigner target : enemies) {
						if (target.alive) {
							int skillDamage = getDamageValue(opt, source, target.defense);
							target.life -= skillDamage;
							// record total damage...
							source.recordTotalDamage(skillDamage);
							addBattleResult(target.icon, Operation.LIFE, -skillDamage);
						}
					}
				} else {
					for (int i = 0; i < opt.target; ++i) {
						// guard.
						GuardInfo gi = enemies.get(0).ourGuard;
						if (null != gi.guarder) {
							Campaigner target = gi.guarder;
							int resistDamage = getDamageValue(opt, source, target.defense);
							resistDamage = resistDamage * (100 - gi.resist) / 100;
							if (0 > resistDamage)
								resistDamage = 0;
							target.life -= resistDamage;
							// record guard
							target.recordTotalGuard(resistDamage);
							addBattleResult(target.icon, Operation.LIFE, -resistDamage);
						} else {
							Campaigner target = findRandomTarget(enemies, true);
							if (null != target) {
								int skillDamage = getDamageValue(opt, source, target.defense);
								target.life -= skillDamage;
								// record total damage
								source.recordTotalDamage(skillDamage);
								addBattleResult(target.icon, Operation.LIFE, -skillDamage);
							}
						}
					}
				}	
			} else if (opt.isAssist()) {
				if (opt.isAll()) {
					// to all friends.
					for (Campaigner target : friends) {
						applyAssist(source, opt, target);
					}
				} else if (opt.isMe()) {
					// to myself.
					applyAssist(source, opt, source);
				} else {
					// random target.
					for (int i = 0; i < opt.target; ++i) {
						// find alive or dead target.
						Campaigner target = findRandomTarget(friends, opt.isResurrection() ? false : true);
						if (null != target)
							applyAssist(source, opt, target);
					}
				}
			} else if (opt.isDebuff()) {
				if (opt.isAll()) {
					// to all enemy
					for (Campaigner target : enemies) {
						applyAssist(source, opt, target);
					}
				} else {
					// random target.
					for (int i = 0; i < opt.target; ++i) {
						// find alive enemy target.
						Campaigner target = findRandomTarget(enemies, true);
						if (null != target)
							applyAssist(source, opt, target);
					}
				}
			}
		}
	}
	
	private int getDamageValue(Operation2 opt, Campaigner source, int defense) {
		int result = 0;
		switch (opt.getPureType()) {
		case Operation.LIFE: 
			// -25% ~ +25%
		{
			final int damage = source.life * opt.value / 100;
			final int bonus = mRand.nextInt(51) - 25;
			result = damage * (100 + bonus) / 100 - defense;
		}
			break;
		case Operation.ATTACK: 
			// -10% ~ +10% 
		{
			final int damage = source.attack * opt.value / 100;
			final int bonus =  mRand.nextInt(21) - 10;
			result = damage * (100 + bonus) / 100 - defense;
		}
			break;
		case Operation.DEFENSE: 
			// -5% ~ +5%
		{
			final int damage = source.defense * opt.value / 100;
			final int bonus =  mRand.nextInt(11) - 5;
			result = damage * (100 + bonus) / 100 - defense;
		}
			break;
		case Operation.SPEED: 
			// -10% ~ +10%
		{
			final int damage = opt.value / source.speed;
			final int bonus =  mRand.nextInt(21) - 10;
			result = damage * (100 + bonus) / 100 - defense;
		}
			break;
		case Operation.FIX: 
			result = opt.value;
			break;
		case Operation.WOUND: 
			// -20% ~ +0%
		{
			final int damage = (source.maxLife - source.life) * opt.value / 100;
			final int bonus = - mRand.nextInt(21);
			result = damage * (100 + bonus) / 100;
		}
			break;
		default:
			break;
		}
		
		// check result.
		if (0 > result) {
            result = 0;
        }
		if (result > Game.Setting.GLOBAL_MAX_DAMAGE) {
            result = Game.Setting.GLOBAL_MAX_DAMAGE;
        }
		return result;
	}
	
	private Campaigner findRandomTarget(ArrayList<Campaigner> targets, boolean alive) {
		final int size = targets.size();
		final int start = mRand.nextInt(size);
		for (int i = 0; i < size; ++i) {
			Campaigner target = targets.get((start + i) % size);
			if (target.alive == alive)
				return target;
		}
		
		return null;
	}
	
	private void applyAssist(Campaigner source, Operation2 assist, Campaigner target) {
		if (!target.alive && !assist.isResurrection())
			return;
		
		int changeValue = 0;
		if (assist.isLife()) {
			if (assist.isTo()) {
                changeValue = assist.value - target.life;
            } else if (assist.isRate()) {
                changeValue = target.maxLife * assist.value / 100;
            } else {
                changeValue = assist.value;
            }
            // record heal.
            if (changeValue > 0) {
                source.recordTotalHeal(changeValue);
            }
			target.life += changeValue;
			addBattleResult(target.icon, Operation.LIFE, changeValue);
			// check life
			if (1 > target.life)
				target.life = 1; // assist will not kill yourself.
			if (target.maxLife < target.life)
				target.life = target.maxLife;
		}
		if (assist.isAttack()) {
			if (assist.isTo())
				changeValue = assist.value - target.attack;
			else if (assist.isRate())
				changeValue = target.attack * assist.value / 100;
			else
				changeValue = assist.value;
			target.attack += changeValue;
			addBattleResult(target.icon, Operation.ATTACK, changeValue);
			// check attack
			if (0 > target.attack)
				target.attack = 0;
		}
		if (assist.isDefense()) {
			if (assist.isTo())
				changeValue = assist.value - target.defense;
				
			else if (assist.isRate())
				changeValue = target.defense * assist.value / 100;
			else
				changeValue = assist.value;
			target.defense += changeValue;
			addBattleResult(target.icon, Operation.DEFENSE, changeValue);
			// check defense
			if (0 > target.defense)
				target.defense = 0;
		}
		if (assist.isSpeed()) {
			if (assist.isTo())
				changeValue = assist.value - target.speed;
			else if (assist.isRate())
				changeValue = target.speed * assist.value / 100;
			else
				changeValue = assist.value;
			target.speed += changeValue;
			addBattleResult(target.icon, Operation.SPEED, changeValue);
			// check speed.
			if (target.speed < Game.Setting.GLOBAL_HERO_MIN_SPEED) {
                target.speed = Game.Setting.GLOBAL_HERO_MIN_SPEED;
            }
		}
		// resurrection.
		if (assist.isResurrection() && !target.alive) {
			target.alive = true;
			target.life = target.maxLife * assist.value / 100;
			// record heal
            source.recordTotalHeal(target.life);
			addBattleResult(target.icon, Operation.RESURRECTION, target.life);
		}
		// quick
		if (assist.isQuick()) {
			changeValue = target.coolDown * assist.value / 100;
			target.coolDown += changeValue;
			addBattleResult(target.icon, Operation.COOL_DOWN, changeValue);
			if (0 > target.coolDown)
				target.coolDown = 0;
		}
		// guard
		if (assist.isGuard()) {
			target.ourGuard.guarder = target;
			target.ourGuard.resist = assist.value;
			addBattleResult(target.icon, Operation.GUARD, assist.value);
		}
	}
	
	// FIXME 0420 BUG 發生,滑動 history 時顯示有內容更新但未呼叫 notifyDataSetChanged. 檢查或注意一下
	// tick
	public void tick() {
		if ((state & BATTLE) == 0)
			return;
		
		// reset update state
		state &= CLEAR_UPDATE;
		
		time--;
		
		if (0 >= time) {
			timeUp();
			state = RESULT_UPDATE | TIME_UP;
			return;
		}
		
		// giant
		for (Campaigner giant : giants) {
			if (giant.alive) {
				if (null == giant.action)
					// always check action.
					((Giant)giant).decideAction(time);
				else {
					giant.coolDown--;
					if (0 >= giant.coolDown) {
						// battle action
						addBattleAction(giant);
						// do action and get result.
						doAction(giant, giants, legions);
						// add unique skill record...
						recordUniqueSkill((Giant)giant);
						// flush all battle result
						flushBattleResult();
						// reset action data
						giant.action = null;
						// update history
						state |= UPDATE_HISTORY;
					}
				}
				
				// always normal attack!
				if (time % giant.speed == 0){
					normalAttack(giant, findRandomTarget(legions, true));
				}
			}
		}
		
		// legion
		for (Campaigner legion : legions) {
			// always normal attack, skill should trigger by user.
			
			if (legion.alive) {
				if (0 < legion.coolDown)
					legion.coolDown--;
				else if (/*legion.action.auto*/true) {
					// auto use unique skill
					// battle action
					addBattleAction(legion);
					// do action
					doAction(legion, legions, giants);
					// flush all battle result
					flushBattleResult();
					// reset cool down
					legion.coolDown = legion.action.coolDown;
					// update history
					state |= UPDATE_HISTORY;
				}
					
				if (time % legion.speed == 0)
					normalAttack(legion, findRandomTarget(giants, true));
			}
		}
		
		/*
		 * update next life
		 */
		for (Campaigner giant : giants) {
			if (giant.nextLife > giant.life)
				giant.nextLife -= 500;
		}
		for (Campaigner legion : legions) {
			if (legion.nextLife > legion.life)
				legion.nextLife -= 10;
		}
		
		/*
		 * check win or lose
		 */
		boolean allDie = true;
		for (Campaigner giant : giants) {
			if (giant.alive) {
				if (0 >= giant.life) {
					// die
					giant.life = 0;
					giant.alive = false;
					// remove guard
					if (giant.ourGuard.guarder == giant) {
						giant.ourGuard.guarder = null;
						giant.ourGuard.resist = 0;
					}
					addBattleResult(giant.icon, Operation.SOUL, -1);
					state |= UPDATE_HISTORY;
				} else
					allDie = false;
			}
		}
		flushBattleResult();
		if (allDie) {
			legionWin();
			state = RESULT_UPDATE | LEGION_WIN;
			return;
		}
		
		allDie = true;
		for (Campaigner legion : legions) {
			if (legion.alive) {
				if (0 >= legion.life) {
					legion.life = 0;
					legion.alive = false;
					// remove guard
					if (legion.ourGuard.guarder == legion) {
						legion.ourGuard.guarder = null;
						legion.ourGuard.resist = 0;
					}
					addBattleResult(legion.icon, Operation.SOUL, -1);
					heroLoseLife((Legion)legion);
					state |= UPDATE_HISTORY;
				} else
					allDie = false;
			}
		}
		flushBattleResult();
		if (allDie) {
			legionLose();
			state = RESULT_UPDATE | GIANT_WIN;
			return;
		}
		
		//if (time % 10 == 0)
			state = state | UPDATE_GIANT | UPDATE_LEGION;
	}

	private void normalAttack(Campaigner source, Campaigner target) {
		if (null == target)
			return;
		int damage = source.attack - target.defense;
		if (0 > damage)
			damage = 0;
		target.life -= damage;
		
	}
	
	private void heroLoseLife(Legion hero) {
		//if (!mHero.alive)
		//hero.round = GameData.getInstance().veteranLoseLife(hero.heroFrom);
		// kill legion count.
		mKillLegionCount++;
	}
	
	/*
	 * record unique skill
	 */
	private void recordUniqueSkill(Giant giant) {
		ArrayList<UniqueSkill> rec = mSkillRecord.get(giant.race.code);
		if (null == rec) {
			ArrayList<UniqueSkill> newRec = new ArrayList<>();
			newRec.add(giant.action.skill);
			mSkillRecord.put(giant.race.code, newRec);
		} else {
			rec.add(giant.action.skill);
		}
	}
	
	/*
	 * end game series
	 */
	// time up
	// 1. get giant experience x 1 (for each mHero class)
	// 2. get skill info if giant used.
	// 3. r.i.p.
	private void timeUp() {
		CampaignAction timeUp = new CampaignAction();
		timeUp.icon = "blockee_crusader";
		timeUp.skillIcon = "";
		timeUp.info = "time_up";
		timeUp.bg = "btn_fairy";
		timeUp.time = 0;
		battleHistory.add(timeUp);
		
		for (Campaigner ir : giants) {
			Giant giant = (Giant) ir;

			GiantRecord record = null;//GameData.getInstance().getGiantRecord(giant.race);
			
			// giant experience.
			getGiantExperience(record, 1);
			
			// record skill
			ArrayList<UniqueSkill> skillUsed = mSkillRecord.get(giant.race.code);
			if (null != skillUsed)
				for (UniqueSkill us : skillUsed)
					record.addSkill(us);
		}
		

		// r.i.p.
		legionRIP();
		flushBattleResult();

        // display summary
        displayTotalSummary();
	}
	
	// legion win
	// 1. get giant experience x 3 (for each mHero class)
	// 2. get skill info if giant used.
	// 3. get soul info if mHero get.
	// 4. get soul (for each mHero class)
	// 5. record defeat record.
	// 6. r.i.p.
	private void legionWin() {
		CampaignAction legionWin = new CampaignAction();
		legionWin.icon = "blockee_crusader";
		legionWin.skillIcon = "";
		legionWin.info = "win";
		legionWin.bg = "btn_fairy";
		legionWin.time = time;
		battleHistory.add(legionWin);
		
		for (Campaigner ir : giants) {
			Giant giant = (Giant) ir;
			
			GiantRecord record = null;//GameData.getInstance().getGiantRecord(giant.race);

			// giant experience
			getGiantExperience(record, 3);
			
			// get soul
			getAndRecordGiantSoul(record);
			
			// record skill
			ArrayList<UniqueSkill> skillUsed = mSkillRecord.get(giant.race.code);
			if (null != skillUsed)
				for (UniqueSkill us : skillUsed)
					record.addSkill(us);
			
			// record defeat
			record.defeat(time);
		}
		flushBattleResult();
		
		// r.i.p.
		legionRIP();
		flushBattleResult();

        // display summary
        displayTotalSummary();
	}
	
	// legion lose
	// 1. r.i.p.
	private void legionLose() {
		CampaignAction legionLose = new CampaignAction();
		legionLose.icon = "blockee_crusader";
		legionLose.skillIcon = "";
		legionLose.info = "lose";
		legionLose.bg = "btn_fairy";
		legionLose.time = time;
		battleHistory.add(legionLose);
		
		// exp +1
		for (Campaigner ir : giants) {
			Giant giant = (Giant) ir;
			GiantRecord record = null;//GameData.getInstance().getGiantRecord(giant.race);
			record.addExperience(1);
		}
		
		// r.i.p.
		legionRIP();
		flushBattleResult();

		// display summary
        displayTotalSummary();
	}

	private void displayTotalSummary() {
	    ArrayList<CampaignScore> allScore = new ArrayList<>();
	    for (Campaigner legion : legions) {
            allScore.add(new CampaignScore(legion.icon, legion.getTotalDamage(), legion.getTotalGuard(), legion.getTotalHeal()));
        }
	    Collections.sort(allScore, Collections.reverseOrder());
	    battleHistory.addAll(allScore);
    }
	
	private void getGiantExperience(GiantRecord record, int value) {
		// get alive count;
		int count = 0;
		for (Campaigner ir : legions)
			if (ir.alive)
				count++;
		
		final int exp = count * value;
		
		// add giant experience
		record.addExperience(exp);
	}
	
	private void getAndRecordGiantSoul(GiantRecord record) {
		ArrayList<Soul> drops = new ArrayList<>();
		
		// every soul will test drop.
		for (Soul soul : record.race.drops) {
			final int seed = mRand.nextInt(1000);
			if (seed < soul.dropRate())
				drops.add(soul);
		}
		
		for (Soul drop : drops) {
			Legion legion = findLuckyGuy();
			if (null != legion) {
				// update loop Icon
				legion.lootIcon = drop.icon;
				// giant drop record add.
				record.addSoul(drop);
				// mHero get soul / coin
				HeroClassRecord hcRecord = null;//GameData.getInstance().getHeroClassRecord(legion.heroClass);
				if (drop.isMoneyBag()) {
					final int gold = drop.getGoldenCoin();
					hcRecord.coin += gold;
					addBattleResult(legion.heroClass.key, Operation.GOLDEN_COIN, gold);
				} else {
					hcRecord.addSoul(drop);
					addBattleResult(legion.heroClass.key, Operation.GIANT_SOUL, drop.code);
				}
			}
		}
	}
	
	private Legion findLuckyGuy() {
		final int size = legions.size();
		final int start = mRand.nextInt(size);
		
		for (int i = 0; i < size; ++i) {
			int index = (i + start) % size;
			Legion legion = (Legion) legions.get(index);
			if (legion.alive && "" == legion.lootIcon)
				return legion;
		}
		
		return null;
	}
	
	private void legionRIP() {
		ArrayList<Veteran> legionData = null;//GameData.getInstance().getLegionData();
		Iterator<Veteran> itr = legionData.iterator();
		while (itr.hasNext()) {
			Veteran veteran = itr.next();
			if (0 >= veteran.round) {
				addBattleResult(veteran.heroClass.key, Operation.DEATH, 1);
				itr.remove();
			}
		}
		// add kill count
		for (Campaigner ir : giants) {
			Giant giant = (Giant) ir;
			GiantRecord record = null;//GameData.getInstance().getGiantRecord(giant.race);
			record.addKillCount(mKillLegionCount);
		}
		
		
	}
	
	/*
	 * battle history
	 */
	private void addBattleAction(Campaigner source) {
		CampaignAction ba = new CampaignAction();
		ba.icon = source.icon;
		ba.bg = source.bg;
		ba.skillIcon = source.action.skill.icon;
		ba.time = time;
		battleHistory.add(ba);
	}

	private void addBattleResult(String icon, int type, int value) {
		if (0 == value)
			return;
		
		int storePos = -1;
		for (int i = 0; i < 3; ++i)
			if (null != store.icon[i] || "" == store.icon[i]) {
				storePos = i;
				break;
			}

		store.icon[storePos] = icon;
		store.type[storePos] = type;
		store.value[storePos] = value;
		
		if (2 == storePos) {
			battleHistory.add(store);
			store = new CampaignResult();
		}
	}

	private void flushBattleResult() {
		if (null != store.icon[0] && "" != store.icon[0]) {
			battleHistory.add(store);
			store = new CampaignResult();
		}
	}
	
	/*
	 * state series
	 */
	public boolean isUpdateGiant() {
		return (state & UPDATE_GIANT) != 0;
	}
	
	public boolean isUpdateLegion() {
		return (state & UPDATE_LEGION) != 0;
	}
	
	public boolean isUpdateHistory() {
		return (state & UPDATE_HISTORY) != 0;
	}
	
	public boolean isBattle() {
		return (state & BATTLE) != 0;
	}
	
	public boolean isResult() {
		return (state & RESULT) != 0;
	}
	
	public boolean isWin() {
		return (state & LEGION_WIN) != 0;
	}
}
