package com.qqhouse.dungeon18plus.core;

import static com.qqhouse.dungeon18plus.core.HeroClass.PEDDLER;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.EventInfo;
import com.qqhouse.dungeon18plus.struct.EventResult;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.Variety;
import com.qqhouse.dungeon18plus.struct.event.BattleEvent;
import com.qqhouse.dungeon18plus.struct.event.ColosseumShop;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.struct.event.VariedHero;
import com.qqhouse.dungeon18plus.struct.hero.ColosseumHero;
import com.qqhouse.dungeon18plus.struct.hero.Hero;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQListAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class ColosseumManager extends GameManager<ColosseumHero> {

    private final ArrayList<Event> mEvents = new ArrayList<>();
    private final ArrayList<Item> mShopItems = new ArrayList<>();
    private final ArrayList<EquipmentMastery> backpack = new ArrayList<>();

    // mark end game event.
    private Event END_GAME = new Event(EventType.GAME_OVER);

    // tmp
    public final EventResult eventResult = new EventResult();
    private final EventInfo mEventInfo = new EventInfo();
    private int mEventIndex;
    private final SaveGame savedGame;

    public ColosseumManager(HeroClass heroClass, SaveGame savedGame) {

        // saved game
        this.savedGame = savedGame;
        // class record
        HeroClassRecord record = savedGame.getHeroClassRecord(heroClass);

        // create Hero
        mHero = new ColosseumHero();
        buildUpHero(heroClass, record);
        setupActionSlot(heroClass);
        setupActionSlotOfSpecialItem();

        // force setup key, coin, star
        // filter.
        mHero.feats = Feat.COLOSSEUM.and(mHero.feats);//mHero.feats &= Game.feat.colosseumFeat;
        mHero.coin = record.maxRound * 5;
        // no more limit, max round = 40;
        if (mHero.coin > 150)
            mHero.coin = 150;
        //mHero.coin = 999;
        mHero.star = 50;
        mHero.key = 0;
        mHero.round = 0;

        // initial shops
        mShopItems.clear();
        mShopItems.addAll(savedGame.getEquipmentData());
        if (Game.isPremium) {
            mShopItems.add(Item.YGGDRASIL_DAGGER);
            mShopItems.add(Item.YGGDRASIL_SWORD);
            mShopItems.add(Item.YGGDRASIL_STAFF);
            mShopItems.add(Item.YGGDRASIL_SHIELD);
            mShopItems.add(Item.YGGDRASIL_RING);
        }
        Collections.shuffle(mShopItems);

        // potion
        //mShopItems.add(0, Item.YELLOW_POTION);
        //mShopItems.add(1, Item.RED_POTION);
        //mShopItems.add(2, Item.BLUE_POTION);
        //mShopItems.add(3, Item.GREEN_POTION);


        // initial round
        enterRound();
        UpdateCostValue();
    }

    private void enterRound() {
        // round number ++
        mHero.round++;
        mHero.clearBattleBuffer();

        // give coins every 3 round [50, 50, 50, 55]
        if (mHero.round % 3 == 0) {
            int giveCoin = mRandom.nextInt(4) == 0 ? 55 : 50;
            mHero.coin += giveCoin;
            eventResult.coin += giveCoin;
        }

        // give stars every round 30 [28, 30, 32]
        int giveStar = mRandom.nextInt(3) * 2 + 28;
        mHero.star += giveStar;//30;
        eventResult.star += giveStar;//30;

        // update mastery in backpack
        for (EquipmentMastery em : backpack)
        	em.mastery++;

        // give gladiator and added into Event.
        if (mHero.round < 40) {
            HeroClass[] gladiators =
                (30 != mHero.round)
                ? new HeroClass[] {HeroClass.NOVICE, HeroClass.BARBARIAN, HeroClass.BERSERKER, HeroClass.DRAGOON, HeroClass.THIEF, HeroClass.ASSASSIN, HeroClass.CRUSADER}
                : new HeroClass[] {HeroClass.EARTH_KNIGHT, HeroClass.FIRE_KNIGHT, HeroClass.WATER_KNIGHT, HeroClass.WIND_KNIGHT};

            HeroClass gladiatorClass = gladiators[mRandom.nextInt(gladiators.length)];

            VariedHero gladiator = new VariedHero(EventType.GLADIATOR, gladiatorClass);
            GladiatorCreator.create(gladiator, gladiatorClass, mHero.round);

            mEvents.add(0, gladiator);
            if (null != adapter)
                adapter.insert(0);
            //if (null != mAdapter)
            //   mAdapter.insert(0);

            // give 2 more shop...
            int shopNum = Feat.SHOPPING.in(mHero.feats) && (mHero.round % 2 != 0)? 2 : 1;
            for (int i = 0; i < shopNum; ++i) {
                if (!mShopItems.isEmpty()) {
                    Item loot = mShopItems.remove(0);
                    ColosseumShop shop = new ColosseumShop(loot);
                    // mastery
                    int maxMastery = MasteryManager.getMastery(mHero.heroClass, loot);
                    if (maxMastery > Game.Setting.GENERAL_MASTERY_MAX) {
                        shop.setMasterEquipment();
                    }

                    int pos, size = mEvents.size();
                    for (pos = 1; pos < size; ++pos) {
                        if (mEvents.get(pos).loot.price < loot.price) {
                            break;
                        }
                    }
                    mEvents.add(pos, shop);
                    if (null != adapter)
                        adapter.insert(pos);
                }
            }
        } else {
            // add colosseum master.
            Event colosseumMaster = new Event(EventType.COLOSSEUM_MASTER);
            mEvents.add(0, colosseumMaster);
        }

    }

    /*
        data source for event adapter
     */
    private QQListAdapter adapter;
    public void setAdapter(QQListAdapter adapter) {
        this.adapter = adapter;
    }

    public void doAction(int index) {
        eventResult.clear();
        doAction(index, eventResult);
        UpdateCostValue();
    }
    
    private boolean isEventDoable(Event evt) {
        switch(evt.costType) {
        case Game.Cost.NONE:
            return true;
        case Game.Cost.COIN:
            return mHero.coin >= evt.costValue;
        case Game.Cost.DAMAGE:
            return (mHero.life > evt.costValue) || Game.Debug.QUICK_GAME;
        case Game.Cost.NEVER:
            return false;
        default:
            throw new RuntimeException("invalid cost type : " + evt.costType);
        }
    }

    public int doEvent() {
        Event evt = mEvents.get(mEventIndex);

        // remove it first
        mEvents.remove(mEventIndex);
        adapter.remove(mEventIndex);
        //mAdapter.remove(mEventIndex);

        // pay cost
        eventResult.clear();
        payForEvent(evt, eventResult);

        // use loot
        useLoot(evt.loot, eventResult);

        // if kill gladiator, then enter next round.
        if (EventType.GLADIATOR == evt.type) {

            enterRound();

            // 50% rate novice can learn new attribute up
            if (Feat.APPRENTICE.in(mHero.feats) && mRandom.nextBoolean()) {
                learning(evt.getHeroClass());
            }

            // unlock knights.
            if (evt.isKnight()) {
                //GameData.getInstance().heroClassData.addColosseum(evt.heroClass, 0);
                //HeroClassRecord record = GameData.getInstance().getHeroClassRecord(mHero.heroClass);
                HeroClassRecord record = savedGame.getHeroClassRecord(mHero.heroClass);

                switch(evt.getHeroClass()) {
                    case EARTH_KNIGHT: {
                        if (Game.Setting.HERO_MAX_MIRROR > record.yellowMirror) {
                            record.yellowMirror++;
                        }
                        savedGame.unlockColosseum(HeroClass.EARTH_KNIGHT);
                    }
                    break;
                    case FIRE_KNIGHT: {
                        if (Game.Setting.HERO_MAX_MIRROR > record.redMirror) {
                            record.redMirror++;
                        }
                    }
                    break;
                    case WATER_KNIGHT: {
                        if (Game.Setting.HERO_MAX_MIRROR > record.blueMirror) {
                            record.blueMirror++;
                        }
                    }
                    break;
                    case WIND_KNIGHT: {
                        if (Game.Setting.HERO_MAX_MIRROR > record.greenMirror) {
                            record.greenMirror++;
                        }
                        savedGame.unlockColosseum(HeroClass.WIND_KNIGHT);
                    }
                    break;

                }
            }

        } else if (evt.type.endGame()) {
            // end , record max turn
            HeroClassRecord record = savedGame.getHeroClassRecord(mHero.heroClass);
            record.updateRound(mHero.round);
            return Game.result.lose;
        }

        // check end.
        UpdateCostValue();
        return Game.result.process;
    }
    
    private void UpdateCostValue() {
        boolean isEnd = true;

        for (Event evt : mEvents) {
            switch (evt.type) {
            case PEDDLER: {
                evt.costValue = evt.loot.price;
                if (Feat.BARGAIN.in(mHero.feats)) {
                    evt.costValue = evt.costValue * 9 / 10;
                }
                if (mHero.coin >= evt.costValue)
                    isEnd = false;
            }
                break;
            case GAME_OVER:
                break;
            case COLOSSEUM_MASTER:
                isEnd = false;
                break;
            case GLADIATOR: {
                if (evt instanceof VariedHero) {
                    enhanceGladiator((VariedHero)evt, mHero);
                }
                evt.costType = Game.Cost.DAMAGE;
                evt.costValue = battle(mHero, (BattleEvent)evt);
                if (mHero.life > evt.costValue)
                    isEnd = false;
            }    break;
            default:
                throw new RuntimeException("invalid event type : " + evt.type);
            }
        }

        // check if no action can do
        if (someActionCanDo()) {
            isEnd = false;
        }

        if (isEnd && !mEvents.contains(END_GAME)) {
            mEvents.add(0, END_GAME);
        } else if (!isEnd && mEvents.contains(END_GAME)) {
            mEvents.remove(END_GAME);
        }

//		// end, add game over
//		if (isEnd) {
//		    if (endGameEvent == null) {
//		        endGameEvent = Event.create(EventType.GAME_OVER).cost(Game.cost.none, 0).done();
//		        mEvents.add(0, endGameEvent);
//            }
//		} else {
//		    // remove game over event.
//            mEvents.remove(endGameEvent);
//        }
    }
    
    private void enhanceGladiator(VariedHero evt, Hero hero) {
        switch (evt.getHeroClass()) {
        case EARTH_KNIGHT:
            evt.enhanceLife(hero.getBody().life);
            break;
        case FIRE_KNIGHT:
            evt.enhanceAttack(hero.getBody().attack);
            break;
        case WATER_KNIGHT:
            evt.enhanceDefense(hero.getBody().defense);
            break;
        case WIND_KNIGHT:
            evt.enhanceSpeed(hero.getBody().speed);
            break;
        }
    }
    
    private void payForEvent(Event evt, EventResult result) {
        switch(evt.costType) {
        case Game.Cost.NONE:
            break;
        case Game.Cost.DAMAGE:
            if (evt instanceof VariedHero) {
                Ability contrast = ((VariedHero)evt).getAbility();
                // RAGE
                if (Feat.RAGE.in(mHero.feats)) {
                    int rate = evt.costValue * 100 / mHero.life;
                    int attackPlus = 0;
                    if (90 < rate)
                        attackPlus = 3;
                    else if (75 < rate)
                        attackPlus = 2;
                    else if (50 < rate)
                        attackPlus = 1;
                    mHero.upgradeAbility(new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, attackPlus), eventResult);
                } else if (Feat.YELLOW_MIRROR.in(mHero.feats)) {
                    // copy life ~
                    if (contrast.life > mHero.getBody().life) {
                        mHero.upgradeAbility(new Variety(Variety.Type.LIFE | Variety.Type.SET, contrast.life), eventResult);
                    }
                } else if (Feat.RED_MIRROR.in(mHero.feats)) {
                    // copy attack
                    if (contrast.attack > mHero.getBody().attack) {
                        mHero.upgradeAbility(new Variety(Variety.Type.ATTACK | Variety.Type.SET, contrast.attack), eventResult);
                    }
                } else if (Feat.BLUE_MIRROR.in(mHero.feats)) {
                    // copy defense
                    if (contrast.defense > mHero.getBody().defense) {
                        mHero.upgradeAbility(new Variety(Variety.Type.DEFENSE | Variety.Type.SET, contrast.defense), eventResult);
                    }
                } else if (Feat.GREEN_MIRROR.in(mHero.feats)) {
                    if (contrast.speed < mHero.getBody().speed) {
                        mHero.upgradeAbility(new Variety(Variety.Type.SPEED | Variety.Type.SET, contrast.speed), eventResult);
                    }
                }
            }
            break;
        case Game.Cost.COIN:
            mHero.coin -= evt.costValue;
            break;
        default:
            throw new RuntimeException("invalid cost type : " + evt.costType);
        }
    }
    
    private void useLoot(Item loot, EventResult result) {

        gainLoot(loot, result);

        // store equipment to backpack
        if (loot.isEquipment()) {
            backpack.add(new EquipmentMastery(loot, 0));//savedGame.getHeroClassRecord(mHero.heroClass).getMastery(loot)));
            //Collections.sort(backpack);
        }
    }
    
    private void learning(HeroClass gladiatorClass) {
        switch (gladiatorClass) {
            case BARBARIAN:
                changeAction(Action.LIFE_UP, Action.MORE_LIFE_UP);
                break;
            case BERSERKER:
                changeAction(Action.ATTACK_UP, Action.MORE_ATTACK_UP);
                break;
            case DRAGOON:
                changeAction(Action.DEFENSE_UP, Action.MORE_DEFENSE_UP);
                break;
            case THIEF:
                changeAction(Action.SPEED_UP, Action.MORE_SPEED_UP);
                break;
        }

    }

    /*
     * show event info
     */
    public EventInfo getEventInfo(int position) {
        Event evt = mEvents.get(position);

        mEventInfo.clear();

        mEventInfo.eventIcon = evt.getIcon();
        mEventInfo.event = evt.type;
        mEventInfo.eventExp = 0;//evt.type.isZako() ? getExp(mHero, evt.level) : 0;

        if (Item.NONE != evt.loot) {
            mEventInfo.loot = evt.loot;
            mEventInfo.isUnknown = false;//evt.loot.isEquipment() && !GameData.getInstance().equipmentData.contains(evt.loot);
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

    /*
     * create veteran from mHero
     * create backpack from List's backpack
     */
    public Veteran createVeteran() {
        return new Veteran(mHero);
    }

    public ArrayList<EquipmentMastery> createBackpack() {
        for (EquipmentMastery em : backpack) {
            final int maxMastery = MasteryManager.getMastery(mHero.heroClass, em.equipment);
            if (em.mastery > maxMastery)
                em.mastery = maxMastery;
            // update mastery...
            if (maxMastery > Game.Setting.GENERAL_MASTERY_MAX && 0 < em.mastery) {
                savedGame.getHeroClassRecord(mHero.heroClass).updateMastery(em.equipment, em.mastery);
            }
        }
        return backpack;
    }

    public void debugFillBackpack() {
        backpack.clear();
        for (Item i : savedGame.getEquipmentData()) {
            //final int mastery = MasteryManager.getMastery(mHero.heroClass, i);
            final int mastery = mRandom.nextInt(40);
            backpack.add(new EquipmentMastery(i, mastery));
        }
        // update hero status
        mHero.copy(mHero.getLimit());
    }


    /*
     * RecyclerViewAdapter.DataSource
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
}
