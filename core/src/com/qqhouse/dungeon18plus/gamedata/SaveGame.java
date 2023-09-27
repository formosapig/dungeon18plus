package com.qqhouse.dungeon18plus.gamedata;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.io.QQSaveGame;

public class SaveGame extends QQSaveGame {

    private HeroClassData_1_6_2 mHeroClassData;
    private WildernessData_1_6_2 mWildernessData;
    private EquipmentData_1_6_1 mEquipmentData;
    private MonsterData_1_6_1 mMonsterData;
    private LeaderboardData_1_6_3 mLeaderboardData;
    private VeteranData_1_6_2 mVeteranData;
    private SystemData_1_6_3 mSystemData;
    private HelpData_1_6_2 mHelpData;
    private SpecialItemData_1_6_3 mSpecialItemData;


    public SaveGame(String fileName) {
        super(fileName);
    }

    @Override
    protected void prepareData() {

        addDataPart(mHeroClassData = new HeroClassData_1_6_2());
        addDataPart(mWildernessData = new WildernessData_1_6_2());
        addDataPart(mEquipmentData = new EquipmentData_1_6_1());
        addDataPart(mMonsterData = new MonsterData_1_6_1());
        addDataPart(mLeaderboardData = new LeaderboardData_1_6_3());
        addDataPart(mVeteranData = new VeteranData_1_6_2());
        addDataPart(mSystemData = new SystemData_1_6_3());
        addDataPart(mHelpData = new HelpData_1_6_2());
        addDataPart(mSpecialItemData = new SpecialItemData_1_6_3());

    }

    @Override
    protected void newGame() {
        Gdx.app.error("TEST", "SaveGame.newGame()");

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

    @Override
    protected void afterLoad() {

    }

    /*
        functions
    */
    public HeroClassRecord getHeroClassRecord(HeroClass heroClass) {
        for (HeroClassRecord record : mHeroClassData.records) {
            if (record.heroClass == heroClass)
                return record;
        }
        // can not find
        throw new RuntimeException("invalid mHero class : " + heroClass);
    }


    public GiantRecord getGiantRecord(GiantRace race) {
        for (GiantRecord data : mWildernessData.giants) {
            if (data.race == race)
                return data;
        }

        throw new RuntimeException("invalid giant race : " + race);
    }

    /*
        reference to G.GAME_MODE_xxxxxx
     */
    public boolean isGameModeUnlocked(int gameMode) {
        for (HeroClassRecord record : mHeroClassData.records) {
            if (record.checkFlag(gameMode))
                return true;
        }
        return false;
    }

}
