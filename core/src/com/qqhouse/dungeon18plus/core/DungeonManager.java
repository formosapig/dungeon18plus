package com.qqhouse.dungeon18plus.core;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.hero.DungeonHero;
import com.qqhouse.dungeon18plus.struct.event.BattleEvent;
import com.qqhouse.dungeon18plus.struct.event.DungeonBoss;
import com.qqhouse.dungeon18plus.struct.event.DungeonMonster;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.struct.event.VariedHero;
import com.qqhouse.dungeon18plus.struct.EventInfo;
import com.qqhouse.dungeon18plus.struct.EventResult;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.Varier;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;
import com.qqhouse.ui.QQList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class DungeonManager extends GameManager<DungeonHero> /*implements ActionSlotSource, DataSource*/ {

    private static final int WIN_LEVEL_NONE = 0;
    private static final int WIN_LEVEL_NORMAL = 1;
    private static final int WIN_LEVEL_GOLDEN = 2;
    
    // zako number
    public static final int ZAKO_NUM_FOR_EACH_TYPE     = 26;
    private static final int ZAKO_PICKUP_NUM         = 3;
    private static final int[] ZAKO_RANK_UP_NUM         = { 2, 3, 4 };    // hard, normal, easy
    public static final int ZAKO_EXPAND_NUM            = 2;    // after kill one boss.
        
    // total number of shop 6
    private static final int SHOP_NUM        = 6;
        
        
    // total number of boss 3
    private static final int BOSS_NUM        = 2;
        
    // how much event you can view
    private static final int MAX_CHOICE        = 12;

    

    // serialize
    //public boolean skeletonKingMode;
    //public int score;
    public final ArrayList<BossKill> killList = new ArrayList<>();
    
    private final ArrayList<Event> mEvents = new ArrayList<>();
    private final ArrayList<Event> mSpecialEvents = new ArrayList<>();
    private final ArrayList<Event> mAllEvent = new ArrayList<>();
    private final ArrayList<EventType> mBossList = new ArrayList<>();
    private final ArrayList<Item> mShopItems = new ArrayList<>();
    private int mWinLevel;        // 0 = not win, 1 = normal win, 2 = golden win.
    private int mBattleEventNum;
    private final EventType[] zakoType = new EventType[ZAKO_PICKUP_NUM];
    private final int[] zakoCount = new int[ZAKO_PICKUP_NUM];
//    private int zakoMaxForEachType = 0;
    private int bossLevel = 0;

    // QQList Adapter
    private QQList.Adapter mAdapter;
    private QQList.Adapter specialEventAdapter;

    // special event
    public Event specialEvent;

    // tmp
    public final EventResult eventResult = new EventResult();
    private final EventInfo mEventInfo = new EventInfo();
    private int mEventIndex;
    private SaveGame savedGame;

    public DungeonManager(HeroClass heroClass, SaveGame savedGame) {

        this.savedGame = savedGame;
        // class record
        HeroClassRecord record = savedGame.getHeroClassRecord(heroClass);

        // create Hero
        mHero = new DungeonHero();
        buildUpHero(heroClass, record);
        setupActionSlot(heroClass);
        setupActionSlotOfSpecialItem();

        if (HeroClass.SKELETON_KING == heroClass)
            mHero.level = 22;
        else if (HeroClass.FAIRY == heroClass)
            mHero.level = 0;
        else
            mHero.level = 1;
        mHero.exp = 0;
        mHero.maxExp = getMaxExp(heroClass, 1);


        if (Game.Debug.QUICK_GAME) {
            //mHero.getBody().life = mHero.getLimit().life;
            //mHero.getBody().attack = mHero.getLimit().attack;
            //mHero.getBody().defense = mHero.getLimit().defense;
            //mHero.getBody().speed = mHero.getLimit().speed;
            mHero.key = 999;
            mHero.coin = 999;
            mHero.star = 999;
        }

//        if (Game.Debug.RECYCLER_VIEW) {
//            mHero.life = 1000;
//            mHero.attack = 100;
//            mHero.defense = 100;
//            mHero.speed = 10;
//        }

        // add bonus for coin collect if not dark presence....
        if (Feat.EXPERIENCE.in(mHero.feats)) {
            mHero.coin += record.highLevel * 10;// * 2;
            //mHero.star += record.highLevel * 4;
        }
        
        // initial dungeon
        killList.clear();
        
        // initial shop items
        mShopItems.clear();
        mShopItems.addAll(savedGame.getEquipmentData());
        // default shop item
        Item[] defaultShopItem = {Item.WOODEN_SWORD, Item.WOODEN_SHIELD, Item.WOODEN_RING, Item.IRON_SWORD, Item.IRON_SHIELD, Item.IRON_BOOTS, Item.IRON_RING};
        for (Item loot : defaultShopItem)
            if (!mShopItems.contains(loot))
                mShopItems.add(loot);
        // extra item
        if (Game.isPremium) {
            Item[] extraItem = {Item.IRON_DAGGER_1
                , Item.IRON_SWORD_1
                , Item.IRON_SHIELD_1
                , Item.IRON_RING_1
                , Item.IRON_BOOTS_1
                , Item.YGGDRASIL_DAGGER
                , Item.YGGDRASIL_SWORD
                , Item.YGGDRASIL_STAFF
                , Item.YGGDRASIL_SHIELD
                , Item.YGGDRASIL_RING};
            for (Item l : extraItem) {
                if (!mShopItems.contains(l)) {
                    mShopItems.add(l);
                }
            }
        }
        Collections.shuffle(mShopItems);
        
        // inital events
        initialEvents();

        mBattleEventNum = 0;
        mWinLevel = WIN_LEVEL_NONE;
        
        fillEvent();
        
        isEndAfterUpdateExtraValue(false);
    }

    private int getMaxExp(HeroClass heroClass, int level) {
        return HeroClass.NOVICE == heroClass
                ? level * 8
                : level * 9 - 3;
        // v2
        //return 100;
    }

    int getExp(DungeonHero hero, int zakoLevel) {
        int exp = zakoLevel;
        if (zakoLevel > hero.level)
            exp += (zakoLevel - hero.level) * (zakoLevel - hero.level);
        return exp;
        // v2
        // monster level - mHero level
        // -2 :   3
        // -1 :   6
        //  0 :  12
        //  1 :  14
        //  2 :  16
        //  3 :  18
        //  4 :  20
        //  5 :  22
        //  6 :  24
        //  7 :  26
        //  8 :  28
        //  9 :  30


//		int diff = zakoLevel - mHero.level;
//		if (-2 >= diff)
//			return 3;
//		else if (-1 == diff)
//			return 6;
//		else if (0 == diff)
//			return 12;
//		else
//			return 12 + diff * 2;//(diff + 3) * diff + 16;
    }

    boolean expUp(DungeonHero hero, int exp, EventResult result) {
        hero.exp += exp;
        if (hero.exp >= hero.maxExp) {
            hero.exp -= hero.maxExp;
            hero.level++;
            hero.star += 30;
            hero.maxExp = getMaxExp(hero.heroClass, hero.level);
            result.star = 30;
            return true;
        }
        return false;
    }



    private void initialEvents() {
        mEvents.clear();
        mAllEvent.clear();
        
        initialZako();
        
        // give shop
        int shopNum = SHOP_NUM;
        for (int i = 0; i < shopNum; ++i) {
            if (!mShopItems.isEmpty()) {
                Item shopItem = mShopItems.remove(0);
                Event shop = new Event(EventType.MERCHANT)
                        .setLoot(shopItem)
                        .setCost(Game.cost.coin, shopItem.price);
                int position = (int) (Math.random() * mAllEvent.size());
                mAllEvent.add(position, shop);
            }
        }
        
        // give door 3 ~ 5
        int doorNum = mRandom.nextInt(3) + 3;
        for (int i = 0; i < doorNum; ++i) {
            // memo 暫訂先亂數要求 6, 8, 10
            int keyNum = mRandom.nextInt(3) * 2 + 6;
            Event door = new Event(EventType.DOOR).setLoot(getDoorTreasure()).setCost(Game.cost.key, keyNum);
            int position = (int) (Math.random() * mAllEvent.size());
            mAllEvent.add(position, door);
        }
        
//        // 5% rate -> holy set trap
//        if (GameManager.RANDOM.nextInt(20) == 0 && Game.heroClass.skeletonKing != mHero.heroClass) {
//            Event trap = Event.create(Game.event.trap)
//                    .loot(LootUnit.getTrapDrop())
//                    .cost(Game.cost.damage, 1000)
//                    .done();
//            int position = (int) (Math.random() * mAllEvent.size());
//            mAllEvent.add(position, trap);
//        }
        
        // 5% for power ring
        // TODO power ring 要少一點出, 但陷阱要多出一點.
        Item[] powerRing = {Item.RING_OF_LIFE, Item.RING_OF_ATTACK, Item.RING_OF_DEFENSE, Item.RING_OF_SPEED};
        int rate = Feat.HOLY_ONE.in(mHero.feats) ? 10 : 5;
        for (Item ring : powerRing) {
            if (mRandom.nextInt(100) < rate) {
                final int trapDamage = (mRandom.nextInt(10) + 1) * (mRandom.nextInt(10) + 1) * 10;
                Event trap = new Event(EventType.TRAP).setLoot(ring).setCost(Game.cost.damage, trapDamage);
                int position = (int) (Math.random() * mAllEvent.size());
                mAllEvent.add(position, trap);
            }
        }
        
        // XXX 0328 以後再改成打倒 Boss 後也可能出現老英雄.
        rate = Feat.HOLY_ONE.in(mHero.feats) ? 10 : 5;
        //for (Veteran old : GameData.getInstance().getBarrackData()) {
          //  if (mRandom.nextInt(100) < rate && !old.heroClass.isKnight()) {
            //    VariedHero oldHero = new VariedHero(EventType.VETERAN, old.heroClass).setAbility(old);
              //  int pos = mRandom.nextInt(mAllEvent.size());
              //  mAllEvent.add(pos, oldHero);
        //    }
        //}

        initialBoss();

    }
    
    private void initialZako() {

        ArrayList<EventType> allZako = new ArrayList<>();
        allZako.add(EventType.PUMPKIN);
        allZako.add(EventType.WEREWOLF);
        allZako.add(EventType.YETI);
        allZako.add(EventType.GRIFFON);
        allZako.add(EventType.SLIME);
        allZako.add(EventType.SKELETON);
        allZako.add(EventType.CYCLOPS);
        // black slime
        if (savedGame.isGiantDefeated(GiantRace.BLACK_SLIME))
            allZako.add(EventType.BLACK_SLIME);
        Collections.shuffle(allZako);
        
        // decide zako type, fixed.
        for (int i = 0; i < ZAKO_PICKUP_NUM; ++i) {
            zakoType[i] = allZako.remove(0);
            zakoCount[i] = 0;
        }
        
        fillZako(78);

    }

    private void fillZako(int count) {
        
        // fill data
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < count; ++i)
            input.add(i % ZAKO_PICKUP_NUM);
        Collections.shuffle(input);
            
        // max level
        final int maxLevel = 20;

        for (int seed : input) {
            // fill zako
                zakoCount[seed]++;
            int level = (zakoCount[seed] - 1) / ZAKO_RANK_UP_NUM[seed] + 1;
            if (level > maxLevel)
                level = maxLevel;

            DungeonMonster zako = new DungeonMonster(zakoType[mRandom.nextInt(zakoType.length)], level);
            mAllEvent.add(zako);
        }

    }
    
    private void expandZako() {

        // no bosses no zako.
        //if (mBossList.isEmpty())
        //    return;
        
        //if (zakoMaxForEachType >= Settings.ZAKO_LEVEL_MAX * 2) return;

        // shop
        int shopNum = Feat.SHOPPING.in(mHero.feats) ? 2 : 1;
        for (int i = 0; i < shopNum; ++i) {
            if (!mShopItems.isEmpty()) {
                Item shopItem = mShopItems.remove(0);
                Event shop = new Event(EventType.MERCHANT)
                        .setLoot(shopItem)
                        .setCost(Game.cost.coin, shopItem.price);
                mAllEvent.add(shop);
            }
        }
        
        // zako
        fillZako(6);
        
        // add boss
        if (!mBossList.isEmpty()) {
            final EventType type = mBossList.remove(0);
            mAllEvent.add(new DungeonBoss(type, bossLevel));
            bossLevel += 2;

            // bring squleton or skeleton fighter
            if (type == EventType.SKELETON_KING) {
                if (savedGame.isGiantDefeated(GiantRace.SKELETON_FIGHTER) && mRandom.nextBoolean()) {
                    DungeonMonster fighter = new DungeonMonster(EventType.SKELETON_FIGHTER, mRandom.nextInt(Game.ZAKO_LEVEL_MAX) + 1)
                            .setLoot(getSkeletonFighterDrop());
                    mAllEvent.add(fighter);
                } else {
                    DungeonMonster squleton = new DungeonMonster(EventType.SQULETON, mRandom.nextInt(20) + 1)
                            .setLoot(getSquletonDrop());
                    mAllEvent.add(squleton);
                }
            }
        }
        
    }
    
    private void initialBoss() {
        
        // generate boss type list
        mBossList.clear();
        mBossList.add(EventType.EARTH_KNIGHT);
        mBossList.add(EventType.FIRE_KNIGHT);
        mBossList.add(EventType.WATER_KNIGHT);
        mBossList.add(EventType.WIND_KNIGHT);
        mBossList.add(EventType.CAT_SITH);
        mBossList.add(EventType.WAILING_WALL);
        mBossList.add(EventType.DEMON);
        // steel cyclops
        if (savedGame.isGiantDefeated(GiantRace.STEEL_CYCLOPS))
            mBossList.add(EventType.STEEL_CYCLOPS);
        Collections.shuffle(mBossList);
        mBossList.add(EventType.SKELETON_KING);
        
        bossLevel = 8;
        
        // fill with boss
        for (int i = 0; i < BOSS_NUM; ++i) {
            EventType sub = mBossList.remove(0);
            DungeonBoss boss = new DungeonBoss(sub, bossLevel);
            int minSize = mAllEvent.size() / 4;
            int position = (int) (Math.random() * minSize) + minSize * (2 + i); 
            mAllEvent.add(position, boss);
            bossLevel += 2;
        }
    }
    
    /*
     * data source for event adapter
     */
    public int getEventCount() {
        return mEvents.size();
    }

    public Event getEvent(int position) {
        return mEvents.get(position);
    }

    public boolean isEventDoable(int position) {
        mEventIndex = position;
        return isEventDoable(mEvents.get(position));
    }
    
    public void setAdapter(QQList.Adapter adapter) {
        mAdapter = adapter;
    }

    /*
        data source for special event adapter
     */
    public int getSpecialEventCount() {return mSpecialEvents.size();}

    public Event getSpecialEvent(int index) {return mSpecialEvents.get(index);}

    public boolean isSpecialEventDoable(int index) {
        return isEventDoable(mSpecialEvents.get(index));
    }

    public void setSpecialAdapter(QQList.Adapter adapter) {specialEventAdapter = adapter;}

    /*
     * attribute up series
     */
    public void doAction(int index) {
        eventResult.clear();
        doAction(index, eventResult);
        isEndAfterUpdateExtraValue(false);
        // TODO 理清 notifyDataSeetChanged() 的流程,有一些 notifyChange 的 call 並不會引起動畫,所以就得 call notifyDataSetChanged
        // 但其它 case 其實會正常運作的.
        //mAdapter.notifyDataSetChanged();
    }
    
    private boolean processSkillAction(DungeonHero hero, Action act) {
        switch (act) {
            /*
             * War Cry :
             *   range : all
             *   zako : life -1
             *   merchant : runaway
             */
            case WAR_CRY: {
//                int damage = 99 / mEvents.size();
//                for (int i = mEvents.size() - 1; i >= 0; --i) {
//                    Event evt = mEvents.get(i);
//                    if (evt.type.isMonster() && evt.life > 1) {
//                        evt.life -= damage;
//                        if (evt.life < 1)
//                            evt.life = 1;
//                        mAdapter.change(i);
//                    } else if (evt.type == EventType.MERCHANT) {
//                        mEvents.remove(i);
//                        mAdapter.remove(i);
//                    }
//
//                }

            }
                break;
            /*
             *     frenzy :
             *  use rage instead of speed cost.
             *  example : defeat zako use 120 tick, then rage - 120 until rage = 0;
             */
            case FRENZY: {
                int attackTime = hero.rage / hero.speed;

            }
                break;

            /*
             * Two hand hold :
             *  attack +50%
             *  defense -50%
             *  get two hand hold feat.
             *  remove this action.
             */
            case TWO_HAND_HOLD:
                // TODO create two hand hold feat.
                // mHero.feats = Feat.APPRENTICE.and(mHero.feats);
                //hero.actions.remove(Action.TWO_HAND_HOLD);
                // effect need apply
                return false;
            /*
             * HIT , just attack enemy with 1 point of life.
             *  for test skill .....
             *
             *
             *
             *
             *
             */
            case HIT: {
//                Log.e(Game.Debug.TAG, "hit = " + mAdapter.firstItem);
//                final Event target = mEvents.get(mAdapter.firstItem);
//                if (target.type.isMonster() && 1 < target.life) {
//                    target.life -= 1;
//                    mAdapter.change(mAdapter.firstItem);
//                }
            }
                break;

            default:
                return false;
        }
        return true;
    }
    
    
    private boolean isEventDoable(Event evt) {
        switch(evt.costType) {
        case Game.cost.key:
            return mHero.key >= evt.costValue;
        case Game.cost.coin:
            // TODO check equpi with all type attribute, pure attack & max attack cap ???
            return mHero.coin >= evt.costValue && (!evt.loot.isPureSpeed() || mHero.getLimit().speed < mHero.speed || EventType.MERCHANT != evt.type);
        case Game.cost.star:
            return mHero.star >= evt.costValue;
        case Game.cost.damage:
            return mHero.life > evt.costValue;
        case Game.cost.none:
        case Game.cost.block:
            return true;
        case Game.cost.never:
            return false;
        default:
            throw new RuntimeException("invalid cost type : " + evt.costType);
        }
    }

    public int doSpecialEvent(int index) {
        final Event evt = mSpecialEvents.get(index);
        mSpecialEvents.remove(index);
        specialEventAdapter.remove(index);
        return doEventImpl(evt);
    }

    public int doEvent() {
        final Event evt = mEvents.get(mEventIndex);
        // remove it first
        mEvents.remove(mEventIndex);
        mAdapter.remove(mEventIndex);

        return doEventImpl(evt);
    }

    private int doEventImpl(Event evt) {
        // pay cost
        eventResult.clear();
        payForEvent(evt);
        
        // use loot
        useLoot(evt.loot, evt.itemCount);
        
        // gain star or soul
        if (Feat.DARK_PRESENCE.in(mHero.feats)) {
            if (isBattleEvent(evt.type, HeroClass.SKELETON_KING))
                mHero.soul += 10;
            // skeleton does not collect coin
            eventResult.coin = 0;
        } else if (Feat.HOLY_ONE.in(mHero.feats)) {
            /*if (evt.type.isMonster()) {
                mHero.star += 10;
                eventResult.star = 10;
            }*/
        } else if (Feat.EXPERIENCE.in(mHero.feats)) {
            if (evt instanceof DungeonMonster) {
                expUp(mHero, ((DungeonMonster)evt).getExp(mHero.level), eventResult);
            }
        }

        // record kill monster
        if (isBattleEvent(evt.type, mHero.heroClass)) {
            mBattleEventNum--;
            
            // record killed monster
            if (evt instanceof DungeonMonster) {
                savedGame.addMonster(new Monster(evt.type, evt.getLevel()));
            }

        }
        
        // novice learning new skill from past mHero!
        if (Feat.APPRENTICE.in(mHero.feats) && evt instanceof VariedHero) {
            learning(evt.getHeroClass());
        }

        // check win condition
        if (evt instanceof DungeonBoss) {
            if (mWinLevel == WIN_LEVEL_NONE) {
                mWinLevel = WIN_LEVEL_NORMAL;
                upgradeEndGameEventWithWinLevel(WIN_LEVEL_NORMAL);
            }

            // TODO list kill and score in dungeon adapter ... not change screen.
            // record kill list
            int score = evt.getScore();
            killList.add(new BossKill(evt.type, score, evt.getTurn()));
            
            // check and record high score.
            if (EventType.SKELETON_KING == evt.type) {
                if (!Feat.DARK_PRESENCE.in(mHero.feats)) {
                    mWinLevel = WIN_LEVEL_GOLDEN;
                    // with potion, win level may update...
                    upgradeEndGameEventWithWinLevel(WIN_LEVEL_GOLDEN);
                }
            } else {
                // only expand when kill other boss.
                expandZako();
            }
        }

        // clear all doors
        if (0 == mHero.key && !(Feat.DARK_PRESENCE.in(mHero.feats) || Feat.UNLOCK.in(mHero.feats) || Feat.BURST_DOOR.in(mHero.feats)))
            clearAllDoors();

        int eventAdded = fillEvent();
        
        HeroClassRecord classData = savedGame.getHeroClassRecord(mHero.heroClass);
        
        if (EventType.WIN_NORMAL == evt.type || EventType.WIN_GOLDEN == evt.type) {
            // record high level
            classData.updateLevel(mHero.level);
            
            // record high score
            int totalScore = 0;
            for (BossKill kill : killList)
                totalScore += kill.score;
            classData.updateScore(totalScore);
            
            savedGame.addScoreHero(new ScoreHero(mHero, totalScore));
            
//            // remove killed past
//            if ((mHero.feats & Game.feat.darkPresence) > 0 && !mPastKilled.isEmpty()) {
//                Collections.sort(mPastKilled, Collections.reverseOrder());
//                for (int pos : mPastKilled)
//                    Memory.getInstance().removePast(pos);
//            }
            if (EventType.WIN_GOLDEN == evt.type) {
                // enable skeleton king in dungeon mode
                savedGame.unlockGameMode(HeroClass.SKELETON_KING, Game.Mode.DUNGEON);
                //savedGame.getHeroClassRecord(HeroClass.SKELETON_KING).unlockDungeon();
                //savedGame.addHeroClass(HeroClass.SKELETON_KING, false);
                
                // enable colosseum for mHero class (exclude skeleton king)
                if (Feat.EXPERIENCE.in(mHero.feats))
                    classData.unlockGameMode(Game.Mode.COLOSSEUM);
                    //GameData.getInstance().addHeroClass(mHero.heroClass, false);
                
                // unlock endless dungeon
                //classData.unlockEndlessDungeon();
            }

            return Game.result.win;
        } else if (EventType.GAME_OVER == evt.type) {
            classData.updateLevel(mHero.level);
            return Game.result.lose;
        }
        
        // turn passed when battle.
        isEndAfterUpdateExtraValue(isBattleEvent(evt.type, mHero.heroClass));
        return eventAdded;//Game.result.process;
    }
    
    private void clearAllDoors() {

        // clear my self
        int size = mEvents.size();
        for (int i = size - 1; i >= 0; --i) {
            Event evt = mEvents.get(i);
            if (EventType.DOOR == evt.type) {
                mEvents.remove(i);
                //mAdapter.remove(i);
            }
        }
        
        // clear all event
        size = mAllEvent.size();
        for (int i = size - 1; i >= 0; --i) {
            Event evt = mAllEvent.get(i);
            if (EventType.DOOR == evt.type)
                mAllEvent.remove(i);
        }

    }
    
    private int fillEvent() {
        int added = 0;
        
        while (MAX_CHOICE > mBattleEventNum && !mAllEvent.isEmpty()) {
            Event newEvent = mAllEvent.remove(0);
            // generate drop (feat effected)
            if (newEvent.type.isZako()) {
                if (EventType.SQULETON != newEvent.type && EventType.SKELETON_FIGHTER != newEvent.type) {
                    // TODO 0328 不好,這樣不能掉落金幣了,應該從 zakoDrop 開始改起.
                    // transfer coin to copper coin only.
                    Item coinDrop = getZakoDrop(mHero.feats);
                    if (Item.COPPER_COIN.equals(coinDrop)) { // 4 ~ 6
                        newEvent.itemCount = mRandom.nextInt(3) + 4;
                        newEvent.loot = Item.COPPER_COIN;
                    } else if (Item.SILVER_COIN.equals(coinDrop)) {// 8 ~ 12
                        newEvent.itemCount = mRandom.nextInt(5) + 8;
                        newEvent.loot = Item.COPPER_COIN;
                    } else if (Item.GOLDEN_COIN.equals(coinDrop)) {
                        newEvent.itemCount = mRandom.nextInt(9) + 16;
                        newEvent.loot = Item.COPPER_COIN;
                    } else
                        newEvent.loot = coinDrop;
                    
                }
            } else if (newEvent.type.isBoss()) {
                newEvent.loot = getBossDrop(newEvent.type, mHero.feats);
            } else if (EventType.VETERAN == newEvent.type) {
                setVeteranDrop(mHero.feats, newEvent);
            }

            // calculate battle event num;
            if (isBattleEvent(newEvent.type, mHero.heroClass)) {
                mBattleEventNum++;
            }

            if (newEvent.type.isBoss()) {
                mSpecialEvents.add(newEvent);
                if (null != specialEventAdapter) {
                    Gdx.app.error("DungeonManager", "add boss.");
                    specialEventAdapter.insert(mSpecialEvents.size() - 1);
                }
            } else {
                mEvents.add(newEvent);
                if (null != mAdapter) {
                    mAdapter.insert(mEvents.size() - 1);
                }
            }
            added++;
        }
        return added;
    }

    private final class CheckResult {
        private boolean noBattle;
        private boolean isEnd;
        private boolean hasEndEvent;
        private CheckResult() {
            this.noBattle = true;
            this.isEnd = true;
            this.hasEndEvent = false;
        }
    }

    private void isEndAfterUpdateExtraValue(boolean nextTurn) {
        CheckResult result = new CheckResult();

        // normal event
        for (Event evt : mEvents) {
            checkAndUpdateExtraValue(result, nextTurn, evt);
        }

        // special event
        for (Event evt : mSpecialEvents) {
            checkAndUpdateExtraValue(result, nextTurn, evt);
        }

        if (result.hasEndEvent) {
            return;
        }
        
        // check if no action can do
        if (someActionCanDo()) {
            result.isEnd = false;
        }

        // end, add game over
        if (result.isEnd || result.noBattle) {
            switch (mWinLevel) {
            case WIN_LEVEL_NONE: {
                Event gameOver = new Event(EventType.GAME_OVER);
                mEvents.add(0, gameOver);
                mAdapter.insert(0);
            }
                break;
            case WIN_LEVEL_NORMAL: {
                Event win = new Event(EventType.WIN_NORMAL);
                mEvents.add(0, win);
                mAdapter.insert(0);
            }
                break;
            case WIN_LEVEL_GOLDEN: {
                Event win = new Event(EventType.WIN_GOLDEN);
                mEvents.add(0, win);
                // RecyclerView 預設會加在最上面，此時沒有動畫，自然也沒有 OnAnimationEnd 事件，導致　
                // 整個流程卡死，目前的作法是強制不作動畫，如此一來，在只有兩三個事件時，最上的事件會很突然的
                // 出現，只要是只有 Add  的事件都有可能有類似的 Bug ，以後再研究出好的解決。
                mAdapter.insert(0);
            }
                break;
            }
        }
    }

    private void upgradeEndGameEventWithWinLevel(int winLevel) {
        if (mEvents.isEmpty())
            return;

        final Event evt = mEvents.get(0);

        if (evt.type.endGame()) {
            switch (winLevel) {
                case WIN_LEVEL_NORMAL: {
                    if (evt.type == EventType.GAME_OVER) {
                        evt.type = EventType.WIN_NORMAL;
                    }
                }
                break;
                case WIN_LEVEL_GOLDEN: {
                    if (evt.type == EventType.WIN_NORMAL) {
                        evt.type = EventType.WIN_GOLDEN;
                    }
                }
                break;
            }
        }
    }

    private void checkAndUpdateExtraValue(CheckResult result, boolean nextTurn, Event evt) {

        // part 1. update event.
        if (isBattleEvent(evt.type, mHero.heroClass))
        {
            // turn++
            if ((evt instanceof DungeonBoss) && nextTurn) {
                ((DungeonBoss)evt).addTurn();
                //evt.score = getScoreByTurn(evt.turn);
            }
            // battle
            if (evt instanceof DungeonMonster) {
                enhanceMonster(mHero, (DungeonMonster)evt);
            }
            evt.costValue = battle(mHero, (BattleEvent)evt);
            //evt.costValue = Fightable.battle(mHero, evt);
            //if (evt.costValue == Fightable.UNBEATABLE) {
            //    evt.costType = Game.cost.never;
            //} else {
            evt.costType = Game.cost.damage;
            /*
             * feat : block
             */
            if (Feat.BLOCK.in(mHero.feats) && evt.costValue < (mHero.level + 3)) {
                evt.costType = Game.cost.block;
                evt.costValue = 0;
            }
            /*
             * feat : life leech
             */
            if (Feat.LIFE_LEECH.in(mHero.feats) && evt.costValue == 0)
                evt.costValue = -4;
            //}
            result.noBattle = false;
        } else if (EventType.DOOR == evt.type) {
            /*
             * door
             * thief, skeleton king : none
             * other : key
             */
            if (Feat.UNLOCK.in(mHero.feats) || Feat.DARK_PRESENCE.in(mHero.feats))
                evt.costType = Game.cost.none;
            else if (Feat.BURST_DOOR.in(mHero.feats) && mHero.key < evt.costValue) {
                // 在要求使用鑰匙的情況下, 轉換成使用體力開門
                // 6 key = 60 life, 8 key = 80 life and son on...
                if (evt.costType == Game.cost.key) {
                    evt.costType = Game.cost.damage;
                    evt.costValue = evt.costValue * 10;//mHero.life / 8;
                }
            } else
                evt.costType = Game.cost.key;
        } else if (EventType.TRAP == evt.type) {
            /*
             * trap
             * assassin : none
             * thief : key x 10
             * other : damage
             */

            // decide cost type
            if (Feat.EVASION.in(mHero.feats) || Feat.DARK_PRESENCE.in(mHero.feats)) {
                evt.costType = Game.cost.none;
            } else if (Feat.DISARM_TRAP.in(mHero.feats) && mHero.key >= 10) {
                evt.costType = Game.cost.key;
                // todo define disarm trap key usage ....
                evt.costValue = 10;
            } else {
                evt.costType = Game.cost.damage;
            }

            // decide cost value
            if (Game.cost.damage == evt.costType && nextTurn) {
                // damage = random (1 ~ 100) * 10
                evt.costValue = (mRandom.nextInt(10) + 1) * (mRandom.nextInt(10) + 1) * 10;
            }
        } else if (EventType.MERCHANT == evt.type) {
            /*
             * shop
             * dark presence : none
             * steal : key / coin , (key = coin / 8) (coint x 110%)
             * bargain : cost valu x 90%
             * other : do nothing
             */
            evt.costType = Game.cost.coin;
            evt.costValue = evt.loot.price;

            if (Feat.DARK_PRESENCE.in(mHero.feats))
                evt.costType = Game.cost.none;
            else if (Feat.STEAL.in(mHero.feats)) {
                evt.costValue = evt.costValue * 11 / 10;
                if (mHero.coin < evt.costValue && mHero.key > 0) {
                    evt.costType = Game.cost.key;
                    evt.costValue = evt.loot.price / 10;
                }
            } else if (Feat.BARGAIN.in(mHero.feats)) {
                evt.costValue = evt.costValue * 9 / 10;
            }
        } else if (evt.type.endGame()) {
            result.hasEndEvent = true;
        }

        // part 2. check event
        if (isEventDoable(evt)) {
            result.isEnd = false;
        }
    }

    
    private void payForEvent(Event evt) {
        switch(evt.costType) {
        case Game.cost.none:
        case Game.cost.never:
        case Game.cost.block:
            break;
        case Game.cost.key:
            mHero.key -= evt.costValue;
            break;
        case Game.cost.coin:
            // endless purse will not decrease coin.
            if (!Feat.ENDLESS_PURSE.in(mHero.feats)) {
                mHero.coin -= evt.costValue;
            }
            break;
        case Game.cost.star:
            mHero.star -= evt.costValue;
            break;
        case Game.cost.damage:
        {
            mHero.upgradeAbility(new Varier(Varier.Type.LIFE | Varier.Type.OFFSET, -evt.costValue), null);
            if (evt.costValue < 0) {
                eventResult.life = -evt.costValue;
            }
            if (Feat.RAGE.in(mHero.feats)) {
                final int attackPlus = getAttackPlusFromRage(evt.costValue);
                mHero.upgradeAbility(new Varier(Varier.Type.ATTACK | Varier.Type.OFFSET, attackPlus), eventResult);
            }
            // heal
            if (Feat.HEAL.in(mHero.feats)) {
                // restore to max life.
                mHero.upgradeAbility(new Varier(Varier.Type.LIFE | Varier.Type.SET, mHero.getLimit().life), null);
            }
            // try try.,..
            mHero.clearBattleBuffer();
        }
            break;
        default:
            throw new RuntimeException("invalid cost type : " + evt.costType);
        }
    }
    
    private void useLoot(Item loot, int itemCount) {

        switch(loot) {
            case NONE:
                break;
            case STAR:
                mHero.star += itemCount;
                eventResult.star += itemCount;
                break;
            case KEY:
                mHero.key++;
                break;
            case COPPER_COIN:
                // endless purse will not increase coin.
                if (!Feat.ENDLESS_PURSE.in(mHero.feats)) {
                    mHero.coin += itemCount;
                    eventResult.coin += itemCount;
                }
                break;
            case SILVER_COIN:
            case GOLDEN_COIN:
                throw new RuntimeException(String.format(Locale.US, "invalid item %s", loot));
            default:
                gainLoot(loot, eventResult);
                break;
        }

        // add to equipment catalog, will check in quipmentData
        savedGame.addEquipment(loot);
    }
    
    private void learning(HeroClass pastClass) {
        int seed = mRandom.nextInt(12);
        
        switch (pastClass) {
        case NOVICE:
            // 1. more life up
            // 2. more attack up
            // 3. more defense up
            // 4. more speed up
            if (seed < 3)
                changeAction(Action.LIFE_UP, Action.MORE_LIFE_UP);
            else if (seed < 6)
                changeAction(Action.ATTACK_UP, Action.MORE_ATTACK_UP);
            else if (seed < 9)
                changeAction(Action.DEFENSE_UP, Action.MORE_DEFENSE_UP);
            else
                changeAction(Action.SPEED_UP, Action.MORE_SPEED_UP);
            break;
        case BARBARIAN:
            // 1. more life up
            // 2. gem seeker
            // 2. rich
            if (seed < 4)
                changeAction(Action.LIFE_UP, Action.MORE_LIFE_UP);
            else if (seed < 8)
                addNewFeat(Feat.GEM_SEEKER);
            else
                addNewFeat(Feat.RICH);
            break;
        case BERSERKER:
            // 1. more attack up
            // 2. lucky
            // 3. block
            if (seed < 4)
                changeAction(Action.ATTACK_UP, Action.MORE_ATTACK_UP);
            else if (seed < 8)
                addNewFeat(Feat.LUCKY);
            else
                addNewFeat(Feat.RAGE);
            break;
        case DRAGOON:
            // 1. more defense up
            // 2. shopping
            // 3. bargain
            if (seed < 3)
                changeAction(Action.DEFENSE_UP, Action.MORE_DEFENSE_UP);
            else if (seed < 6)
                addNewFeat(Feat.BURST_DOOR);
            else if (seed < 9)
                addNewFeat(Feat.BARGAIN);
            else
                addNewFeat(Feat.BLOCK);
            break;
        case THIEF:
            // 1. more speed up
            // 2. unlock
            // 3. disarm trap
            // 4. steal
            if (seed < 3)
                changeAction(Action.SPEED_UP, Action.MORE_SPEED_UP);
            else if (seed < 6)
                addNewFeat(Feat.UNLOCK);
            else if (seed < 9)
                addNewFeat(Feat.DISARM_TRAP);
            else
                addNewFeat(Feat.STEAL);
            break;
        case ASSASSIN:
            // 1. purifacation
            // 2. evasion
            // 3. life leech
            if (seed < 4)
                addNewFeat(Feat.PURIFICATION);
            else if (seed < 8)
                addNewFeat(Feat.EVASION);
            else
                addNewFeat(Feat.LIFE_LEECH);
            break;
        case CRUSADER:
            if (seed < 6)
                addNewFeat(Feat.SHOPPING);
            else
                addNewFeat(Feat.FORGING);
        default:
            break;
        }
    }

    private void addNewFeat(Feat feat) {
        mHero.feats = feat.or(mHero.feats);
    }

    /*
     * show event info
     */

    public EventInfo getSpecialEventInfo(int position) {
        return getEventInfoImpl(mSpecialEvents.get(position));
    }

    public EventInfo getEventInfo(int position) {
        return getEventInfoImpl(mEvents.get(position));
    }

    private EventInfo getEventInfoImpl(Event evt) {
        mEventInfo.clear();
        
        mEventInfo.eventIcon = evt.getIcon();
        mEventInfo.event = evt.type;
        mEventInfo.eventExp = evt.type.isZako() ? ((DungeonMonster)evt).getExp(mHero.level) : 0;

        if (Item.NONE != evt.loot) {
            mEventInfo.loot = evt.loot;
            //mEventInfo.isUnknown = evt.loot.isEquipment() && evt.loot.isNotPremium() && !GameData.getInstance().hasEquipment(evt.loot);
            if (Item.KEY == evt.loot)
                mEventInfo.lootKey = 1;
            else if (Item.COPPER_COIN == evt.loot)
                mEventInfo.lootCoin = 1;
            else if (Item.SILVER_COIN == evt.loot)
                mEventInfo.lootCoin = 2;
            else if (Item.GOLDEN_COIN == evt.loot)
                mEventInfo.lootCoin = 4;
            else if (Item.STAR == evt.loot)
                mEventInfo.lootStar = 1;
        }
        
        return mEventInfo;
    }
    
    // drop series
    private Item getDoorTreasure() {
        // WOODEN_DAGGER x 4, WOODEN_SWORD x 4, WOODEN_STAFF x 4, WOODEN_SHIELD x 4, WOODEN_RING x 4
        // GREAT_SWORD x 2, WIND_DAGGER x 2, TOWER_SHIELD x 2
        // MYTHRIL_DAGGER x 1, MYTHRIL_SWORD x 1, MYTHRIL_STAFF x 1, MYTHRIL_SHIELD x 1, MYTHRIL_BOOTS x 1

        final int seed = mRandom.nextInt(31);
        
        if (seed < 1) return Item.MITHRIL_DAGGER;
        else if (seed < 2) return Item.MITHRIL_SWORD;
        else if (seed < 3) return Item.MITHRIL_STAFF;
        else if (seed < 4) return Item.MITHRIL_SHIELD;
        else if (seed < 5) return Item.MITHRIL_BOOTS;
        else if (seed < 7) return Item.GREAT_SWORD;
        else if (seed < 9) return Item.WIND_DAGGER;
        else if (seed < 11) return Item.TOWER_SHIELD;
        else if (seed < 15) return Item.WOODEN_DAGGER;
        else if (seed < 19) return Item.WOODEN_SWORD;
        else if (seed < 21) return Item.WOODEN_STAFF;
        else if (seed < 25) return Item.WOODEN_SHIELD;
        else return Item.WOODEN_RING;
    }
    
    private Item getSquletonDrop() {
        // drop                rate    sum
        // skull ring        2        2    
        // white ring        3        5
        // black ring        5        10
        // wooden ring        10        20
        // yellow ring        10        30
        // red ring            10        40
        // blue ring        10        50
        // green ring        10        60
        // iron ring        40        100
        final int seed = mRandom.nextInt(100);
        
        if (seed < 2) return Item.SKULL_RING;
        else if (seed < 5) return Item.WHITE_RING;
        else if (seed < 10) return Item.BLACK_RING;
        else if (seed < 20) return Item.WOODEN_RING;
        else if (seed < 30) return Item.YELLOW_RING;
        else if (seed < 40) return Item.RED_RING;
        else if (seed < 50) return Item.BLUE_RING;
        else if (seed < 60) return Item.GREEN_RING;
        else return Item.IRON_RING;
    }
    
    private Item getSkeletonFighterDrop() {
        // drop              rate      sum
        // skull sword          2        2
        // shadow dagger        3        5
        // mithril sword        5       10
        // great sword         20       30
        // iron daggers        30       60
        // wooden sword        40      100
        final int seed = mRandom.nextInt(100);
        
        if (seed < 2) return Item.SKULL_SWORD;
        else if (seed < 5) return Item.SHADOW_DAGGER;
        else if (seed < 10) return Item.MITHRIL_SWORD;
        else if (seed < 30) return Item.GREAT_SWORD;
        else if (seed < 60) return Item.IRON_DAGGER;
        else return Item.WOODEN_SWORD;
    }
    
    // zako drops
    private Item getZakoDrop(long feats) {
        int yellowGem, redGem, blueGem, greenGem, whiteGem, copperCoin, silverCoin, goldenCoin;
        
        if (Feat.GEM_SEEKER.in(feats) || Feat.HOLY_ONE.in(feats)) {
            yellowGem = 6;
            redGem = 12;
            blueGem = 18;
            greenGem = 24;
            whiteGem = 30;
        } else {
            yellowGem = 5;
            redGem = 10;
            blueGem = 15;
            greenGem = 20;
            whiteGem = 25;
        }

        if (Feat.RICH.in(feats) || Feat.HOLY_ONE.in(feats)) {
            copperCoin = whiteGem + 24;
            silverCoin = copperCoin + 12;
            goldenCoin = silverCoin + 6;
        } else {
            copperCoin = whiteGem + 20;
            silverCoin = copperCoin + 10;
            goldenCoin = silverCoin + 5;
        }
        
        int seed = (int)(Math.random() * 100);
        
        if (seed < yellowGem) return Feat.HOLY_ONE.in(mHero.feats) ? Item.YELLOW_CRYSTAL : Item.LIFE_CRYSTAL;
        else if (seed < redGem) return Feat.HOLY_ONE.in(mHero.feats) ? Item.RED_CRYSTAL : Item.ATTACK_CRYSTAL;
        else if (seed < blueGem) return Feat.HOLY_ONE.in(mHero.feats) ? Item.BLUE_CRYSTAL : Item.DEFENSE_CRYSTAL;
        else if (seed < greenGem) return Feat.HOLY_ONE.in(mHero.feats) ? Item.GREEN_CRYSTAL : Item.SPEED_CRYSTAL;
        else if (seed < whiteGem) return Feat.HOLY_ONE.in(mHero.feats) ? Item.WHITE_CRYSTAL :  Item.POWER_CRYSTAL;
        else if (seed < copperCoin) return Item.COPPER_COIN;
        else if (seed < silverCoin) return Item.SILVER_COIN;
        else if (seed < goldenCoin) return Item.GOLDEN_COIN;
        else return Item.NONE;
    }
    
    // all magic sets and holy drops
    private Item getBossDrop(EventType boss, long feats) {
        
        final int rate = Feat.HOLY_ONE.in(feats) ? 10 : (Feat.LUCKY.in(feats) ? 8 : 5);
        
        if (mRandom.nextInt(100) >= rate) {
            // normal drop
            return Feat.HOLY_ONE.in(mHero.feats) ? Item.WHITE_CRYSTAL : Item.POWER_CRYSTAL;
        } else {
            // rare drop
            switch (boss) {
            case CAT_SITH:      return Item.WIND_BOOTS;
            case WAILING_WALL:  return Item.BLACK_SHIELD;
            case DEMON:         return Item.SHADOW_DAGGER;
            case EARTH_KNIGHT:  return Item.WHITE_SHIELD;
            case FIRE_KNIGHT:   return Item.FIRE_SWORD;
            case WATER_KNIGHT:  return Item.ICE_SWORD;
            case WIND_KNIGHT:   return Item.THUNDER_SWORD;
            case STEEL_CYCLOPS: {
                Item[] ironPlus = {Item.IRON_DAGGER_1, Item.IRON_SWORD_1, Item.IRON_SHIELD_1, Item.IRON_RING_1, Item.IRON_BOOTS_1};
                return ironPlus[mRandom.nextInt(ironPlus.length)];
            }
            case SKELETON_KING: return mRandom.nextBoolean() ? Item.SKULL_SWORD : Item.SKULL_SHIELD;
            default:            return Item.NONE;
            }
        }
    }

    // veteran drop
    private void setVeteranDrop(long feats, Event evt) {
        
        final int seed = mRandom.nextInt(100);
        
        Item[] holySet = {Item.HOLY_SWORD, Item.HOLY_SHIELD, Item.RING_OF_GODDESS};
        
        // dark presence 3%
        if (Feat.DARK_PRESENCE.in(feats)) {
            if (seed < 3)
                evt.loot = holySet[mRandom.nextInt(holySet.length)];
            else
                evt.loot = Item.NONE;
        } else {
            final int rate = Feat.HOLY_ONE.in(feats) ? 10 : (Feat.LUCKY.in(feats) ? 8 : 5);
            if (seed < rate)
                evt.loot = holySet[mRandom.nextInt(holySet.length)];
            else {
                evt.loot = Item.STAR;
                evt.itemCount = 10;
            }
        }
    }

    /*
     * feat functions
     */

    /*
     *  get attack plus from rage
     *
     *    0 -  0 (+20)
     *   20 -  1 (+22)
     *   42 -  2 (+24)
     *   66 -  3 (+26)
     *   92 -  4 (+28)
     *  120 -  5 (+30)
     *  150 -  6 (+32)
     *  182 -  7 (+34)
     *  216 -  8 (+36)
     *  252 -  9 (+38)
     *  290 - 10 (+40)
     *  330 - 11 (+42)
     *  372 - 12 (+44)
     *  ...
     */
    private int getAttackPlusFromRage(final int value) {
        int attackPlus = 0;
        int gap = 20;
        int testValue = value - gap;
        while (0 <= testValue) {
            attackPlus++;
            gap = gap + 2;
            testValue = testValue - gap;
        }
        return attackPlus;
    }

    /*
     * enhance monster
     */
    
    // enhance monster
    private void enhanceMonster(DungeonHero hero, DungeonMonster mon) {
        // todo fix this.... use monster's feat.

        if (mon.type.isBoss()) {
            //mon.resetBattleAbility();
            
            switch(mon.type) {
            case EARTH_KNIGHT:
                // life x 2
                mon.enhanceLife(hero.getBody().life * 2);
                break;
            case FIRE_KNIGHT:
                mon.enhanceAttack(hero.getBody().attack);
                break;
            case WATER_KNIGHT:
                mon.enhanceDefense(hero.getBody().defense);
                break;
            case WIND_KNIGHT:
                mon.enhanceSpeed(hero.getBody().speed - 4);
                break;
            case SKELETON_KING: {
                if (Feat.EXPERIENCE.in(hero.feats)) {
                    mon.getAbility().life = 999;
                    mon.enhanceAttack(hero.getBody().attack);
                    mon.enhanceDefense(hero.getBody().defense);

                    // speed / 2
                    mon.getAbility().speed = (hero.getBody().speed + 7/*mon.getAbility().speed + 1*/)/ 2;
                    //mon.speed = 8;
                }
            }
                break;
            default:
                break;
            }
        } else if (EventType.SQULETON == mon.type && Feat.EXPERIENCE.in(hero.feats)) {
            mon.enhanceAttack(hero.getBody().attack);
            mon.enhanceDefense(hero.getBody().defense);
            mon.enhanceSpeed(hero.getBody().speed);
        }
    }
    
    // battle event
    private boolean isBattleEvent(EventType type, HeroClass heroClass) {
        return type.isMonster() || (HeroClass.SKELETON_KING == heroClass && EventType.VETERAN == type);
    }

    /*
     * special event adapter
     */
    /*public final DataSource specialDataSource = new DataSource() {


        public int getEventCount() {
            return mSpecialEvents.size();
        }


        public Event getEvent(int position) {
            return mSpecialEvents.get(position);
        }


        public boolean isEventDoable(int position) {
            return DungeonManager.this.isEventDoable(mSpecialEvents.get(position));
        }


        //public void setAdapter(RecyclerViewAdapter adapter) {
        //    DungeonManager.this.mSpecialAdapter = adapter;
        //}


        public String toString() {
            return "Special";
        }
    };*/

    /*
     * test ...
     */
    public boolean test() {
        Event shop = new Event(EventType.FAIRY);
        mSpecialEvents.add(0, shop);
        specialEventAdapter.insert(0);//mSpecialAdapter.insert(0);//mSpecialEvents.size() - 1);
        return true;
    }

    public void test2() {

        killList.add(new BossKill(EventType.CAT_SITH, 100, 1));
        killList.add(new BossKill(EventType.BLACK_SLIME, 90, 2));
        killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DOOR, 5, 12));
        //killList.add(new BossKill(EventType.DEMON, 5, 11));

        Event win = new Event(EventType.WIN_GOLDEN);
        mEvents.add(0, win);
        mAdapter.insert(0);
    }

}
