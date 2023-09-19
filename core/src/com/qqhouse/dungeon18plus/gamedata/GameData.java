package com.qqhouse.dungeon18plus.gamedata;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.Help;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public final class GameData extends DataCore {

	private final HeroClassData_1_6_2 mHeroClassData;
	private final WildernessData_1_6_2 mWildernessData;
	private final EquipmentData_1_6_1 mEquipmentData;
	private final MonsterData_1_6_1 mMonsterData;
	private final LeaderboardData_1_6_1 mLeaderboardData;
	private final VeteranData_1_6_2 mVeteranData;
	private final SystemData_1_6_3 mSystemData;
	private final HelpData_1_6_2 mHelpData;
	private final SpecialItemData_1_6_3 mSpecialItemData;
	
	/*
	 * singleton, please keep initial order and comment.
	 */
	private GameData() {
		
		// 1.6.1
		addDataPart(new HeroClassData_1_6_1());
		addDataPart(mEquipmentData = new EquipmentData_1_6_1());
		addDataPart(mMonsterData = new MonsterData_1_6_1());
		addDataPart(mLeaderboardData = new LeaderboardData_1_6_1());
		addDataPart(new LocalArenaData_1_6_1()); // local arena rename to barrack

		// 1.6.2
		addDataPart(mHeroClassData = new HeroClassData_1_6_2());
		addDataPart(mVeteranData = new VeteranData_1_6_2());
		addDataPart(mHelpData = new HelpData_1_6_2());
		addDataPart(mWildernessData = new WildernessData_1_6_2());
		
		// 1.6.3 not ready.
        // TODO wait blue tooth version ...
		// addDataPart(mLeaderboardData = new LeaderboardData_1_6_3());
        addDataPart(mSystemData = new SystemData_1_6_3());
        addDataPart(mSpecialItemData = new SpecialItemData_1_6_3());

		// initial game.
		init();
	}
	private static GameData sInstance = new GameData();
	public static GameData getInstance() {
		return sInstance;
	}
	
	public static DataPart sFind(int key) {
		return sInstance.find(key);
	}

	private void init() {
		// add all mHero class with nothing unlocked.
		mHeroClassData.records.clear();
		for (HeroClass hc : HeroClass.values())
			mHeroClassData.records.add(new HeroClassRecord(hc));
		
		// unlock dungeon mode
		getHeroClassRecord(HeroClass.NOVICE).unlockDungeon();
		getHeroClassRecord(HeroClass.BARBARIAN).unlockDungeon();
		getHeroClassRecord(HeroClass.BERSERKER).unlockDungeon();
		getHeroClassRecord(HeroClass.DRAGOON).unlockDungeon();
		getHeroClassRecord(HeroClass.THIEF).unlockDungeon();

		// knight have mirror at beginning.
		getHeroClassRecord(HeroClass.EARTH_KNIGHT).yellowMirror = 3;
		getHeroClassRecord(HeroClass.FIRE_KNIGHT).redMirror = 3;
		getHeroClassRecord(HeroClass.WATER_KNIGHT).blueMirror = 3;
		getHeroClassRecord(HeroClass.WIND_KNIGHT).greenMirror = 3;
		
		// TODO 0407 BT to exchange giant list.
		mWildernessData.giants.clear();
		for (GiantRace race : GiantRace.values()) {
			mWildernessData.giants.add(new GiantRecord(race));
		}

        // skeleton fighter, always unlocked.
        getGiantRecord(GiantRace.SKELETON_FIGHTER).unlock();
    }

    /*
     * for schedule job...
     */
    private Queue<Runnable> jobs = new LinkedList<>();
    private boolean mInitialized = false;
    public void addJob(Runnable job) {
        if (!mInitialized) {
            jobs.offer(job);
        } else {
            job.run();
            Gdx.app.error(G.Debug.TAG, "do runable : " + job);
        }
    }

    void afterLoad() {
        Runnable job;
        while ((job = jobs.poll()) != null) {
            job.run();
            Gdx.app.error(G.Debug.TAG, "do runable : " + job);
        }
        mInitialized = true;
    }

	/*
	 * data control
	 */
	
	/*
	 * mHero class series
	 */
	// unlock dungeon
	private void unlockDungeon(HeroClass heroClass) {
		for (HeroClassRecord record : mHeroClassData.records)
			if (record.heroClass == heroClass)
				record.unlockDungeon();
	}

	// unlock colosseum
	public void unlockColosseum(HeroClass heroClass) {
		for (HeroClassRecord record : mHeroClassData.records)
			if (record.heroClass == heroClass)
				record.unlockColosseum();
	}
	
//	public void addHeroClass(HeroClass heroClass, boolean isColosseum) {
//		HeroClassRecord record = new HeroClassRecord(heroClass);
//		if (isColosseum)
//			record.unlockColosseum();
//		else
//			record.unlockDungeon();
//		if (!mHeroClassData.records.contains(record)) {
//			mHeroClassData.records.add(record);
//			Collections.sort(mHeroClassData.records);
//		}
//	}

	public HeroClassRecord getHeroClassRecord(HeroClass heroClass) {
		for (HeroClassRecord record : mHeroClassData.records) {
			if (record.heroClass == heroClass)
				return record;
		}
		// can not find
		throw new RuntimeException("invalid mHero class : " + heroClass);
	}
	
	
	public void checkUnlockHeroClass() {
		// XXX 0323 之後需要 unlock manager 吧.
		if (getMonsterCount() >= 180)
			unlockDungeon(HeroClass.ASSASSIN);
		if (getEquipmentCount() >= 30)
			unlockDungeon(HeroClass.CRUSADER);
		if (G.isPremium) {
			// dungeon
			unlockDungeon(HeroClass.FAIRY);
			// colosseum
			unlockColosseum(HeroClass.FIRE_KNIGHT);
			unlockColosseum(HeroClass.WATER_KNIGHT);
		}
	}

	public ArrayList<HeroClass> getAvailableHeroClass(boolean isColosseum) {
		ArrayList<HeroClass> result = new ArrayList<>();
		for (HeroClassRecord record : mHeroClassData.records) {
			if (record.checkUnlock(isColosseum)) {
				result.add(record.heroClass);
			}
		}
		return result;
	}
	
	public ArrayList<HeroClass> getHeroClass() {
		ArrayList<HeroClass> result = new ArrayList<>();
		for (HeroClassRecord record : mHeroClassData.records)
			result.add(record.heroClass);
		return result;
	}

	public ArrayList<HeroClass> getHeroClassWithSoul() {
		ArrayList<HeroClass> result = new ArrayList<>();
		for (HeroClassRecord record : mHeroClassData.records)
			if (!record.souls.isEmpty())
				result.add(record.heroClass);
		return result;
	}
	
	public boolean isOpenSoulMaster() {
		for (HeroClassRecord record : mHeroClassData.records) {
			if (!record.souls.isEmpty())
				return true;
		}
		return false;
	}
	
	/*
	 * Wilderness series
	 */
	public ArrayList<GiantRace> getAvailableGiant() {
		ArrayList<GiantRace> result = new ArrayList<>();
		for (GiantRecord record : mWildernessData.giants)
			if (record.isUnlocked())
				result.add(record.race);
		return result;
	}

	public ArrayList<GiantRace> getGiant() {
		ArrayList<GiantRace> result = new ArrayList<>();
		for (GiantRecord record : mWildernessData.giants)
			result.add(record.race);
		return result;
	}
	
	public GiantRecord getGiantRecord(GiantRace race) {
		for (GiantRecord data : mWildernessData.giants) {
			if (data.race == race)
				return data;
		}
		
		throw new RuntimeException("invalid giant race : " + race);
	}
	
	public boolean isGiantDefeated(GiantRace race) {
		for (GiantRecord data : mWildernessData.giants) {
			if (data.race == race) {
				return data.fastWin > 0;
			}
		}
		return false;
	}
		
	/*
	 * Equipment series
	 */
	public ArrayList<Item> getEquipmentData() {
		return mEquipmentData.equipments;
	}
	
	public int getEquipmentCount() {
		return mEquipmentData.equipments.size();
	}
	
	public boolean addEquipment(Item equip) {
		if (equip.isEquipment() && equip.isNotPremium() && !mEquipmentData.equipments.contains(equip)) {
			mEquipmentData.equipments.add(equip);
			Collections.sort(mEquipmentData.equipments);
			return true;
		}
		return false;
	}
	
	public boolean hasEquipment(Item equip) {
		return mEquipmentData.equipments.contains(equip);
	}
	
	/*
	 * Monster series.
	 */
	public ArrayList<Monster> getMonsterData() {
		return mMonsterData.monsters;
	}
	
	public boolean addMonster(Monster monster) {
		if (!mMonsterData.monsters.contains(monster)) {
			mMonsterData.monsters.add(monster);
			Collections.sort(mMonsterData.monsters);
			return true;
		}
		return false;
	}

	public boolean hasMonster(final Monster monster) {
		return mMonsterData.monsters.contains(monster);
	}

	public int getMonsterCount() {
		return mMonsterData.monsters.size();
	}
	
	/*
	 * Leaderboard series
	 */
	public ArrayList<ScoreHero> getLeaderboardData() {
		return mLeaderboardData.heroes;
	}
	
	public boolean addScoreHero(ScoreHero hero) {
		
		if (!mLeaderboardData.heroes.contains(hero)) {
			mLeaderboardData.heroes.add(hero);
			Collections.sort(mLeaderboardData.heroes);
			if (mLeaderboardData.heroes.size() > G.LEADER_BOARD_SIZE)
				mLeaderboardData.heroes.remove(G.LEADER_BOARD_SIZE);
			return true;
		}
		return false;
	}
	
	public int getLeaderboardCount() {
		return mLeaderboardData.heroes.size();
	}
	
	/*
	 * Veteran (Barrack/Legion) series
	 */
	public ArrayList<Veteran> getBarrackData() {
		return mVeteranData.barrack;
	}
	
	public ArrayList<Veteran> getLegionData() {
		return mVeteranData.legion;
	}
	
	public int getBarrackCount() {
		return mVeteranData.barrack.size();
	}
	
	public int getLegionCount() {
		return mVeteranData.legion.size();
	}
	
	public int veteranLoseLife(int index) {
		return --mVeteranData.legion.get(index).round;
	}

	public void veteranRestInPeace() {
		for (int i = mVeteranData.legion.size() - 1; i >= 0; --i) {
			if (0 >= mVeteranData.legion.get(i).round)
				mVeteranData.legion.remove(i);
		}
	}
	
	public void addVeteranToBarrack(int position, Veteran veteran) {
		if (position < mVeteranData.barrack.size())
			mVeteranData.barrack.set(position, veteran);
		else
			mVeteranData.barrack.add(veteran);
		Collections.sort(mVeteranData.barrack);
	}
	
	/*
	 * giant album series
	 */
	public boolean isOpenGiantAlbum() {
		for (GiantRecord gr : mWildernessData.giants) {
			if (0 < gr.exp)
				return true;
		}
		return false;
	}
	
	
	/*
	 * System Data series
	 */
	public boolean validDungeonId() {
	    return 0 != mSystemData.dungeonId;
    }

	public void setDungeonIdAndInitialExtraData(int id) {
        mSystemData.dungeonId = id;

        // initial giant ...
        Random rand = new Random(mSystemData.dungeonId);
        // choose [BLACK_SLIME, RED_SLIME]
        getGiantRecord(rand.nextBoolean() ? GiantRace.BLACK_SLIME : GiantRace.RED_SLIME).unlock();

        // choose [GREEN_PUMPKIN, BLOODY_WEREWALF, SNOW_YETI, THUNDER_BIRD]
        GiantRace[] middle = {GiantRace.GREEN_PUMPKIN, GiantRace.BLOODY_WEREWOLF, GiantRace.BLUE_YETI, GiantRace.THUNDER_BIRD};
        getGiantRecord(middle[rand.nextInt(middle.length)]).unlock();

        // choose [STEEL_CYCLOPS, STONE_FACE, BLACK_MIMIC];
        GiantRace[] high = {GiantRace.STEEL_CYCLOPS, GiantRace.STONE_FACE, GiantRace.BLACK_MIMIC};
        getGiantRecord(high[rand.nextInt(high.length)]).unlock();
    }


	// getter
	public boolean openColosseum() {
		for (HeroClassRecord record : mHeroClassData.records) {
			if (record.checkUnlock(true))
				return true;
		}
		return false;
	}
		
	public boolean openWilderness() {
		return !mVeteranData.legion.isEmpty() || !mVeteranData.barrack.isEmpty();
	}
		
	public boolean openGallery() {
		return !mMonsterData.monsters.isEmpty();
	}
	
	/*
	 * Help Data series.
	 */
	public boolean isHelpUnderstood(Help h) {
		return mHelpData.understand.contains(h);
	}
	
	public void understandHelp(Help h) {
		if (!mHelpData.understand.contains(h)) {
			mHelpData.understand.add(h);
		}
	}

	/*
	 * special item series
	 */
    public int getSpecialItemCount(Item loot) {
        return 0;//mSpecialItemData.specialItems.get(loot.code, 0);
    }

    // + / - ok...
    public void setSpecialItem(Item loot, int count) {
        /*if (mSpecialItemData.specialItems.indexOfKey(loot.code) >= 0) {
	        mSpecialItemData.specialItems.put(loot.code,
                    mSpecialItemData.specialItems.get(loot.code) + count);
        } else if (count > 0) {
	        mSpecialItemData.specialItems.put(loot.code, count);
        }*/
    }

}
