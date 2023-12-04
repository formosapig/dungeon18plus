package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.io.QQSaveGame;

import java.util.ArrayList;
import java.util.Collections;

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
        //Gdx.app.error("TEST", "SaveGame.newGame()");

        // add all mHero class with nothing unlocked.
        mHeroClassData.records.clear();
        for (HeroClass hc : HeroClass.values())
            mHeroClassData.records.add(new HeroClassRecord(hc));

        // unlock dungeon mode
        getHeroClassRecord(HeroClass.NOVICE).unlockGameMode(Game.Mode.DUNGEON);
        getHeroClassRecord(HeroClass.BARBARIAN).unlockGameMode(Game.Mode.DUNGEON);
        getHeroClassRecord(HeroClass.BERSERKER).unlockGameMode(Game.Mode.DUNGEON);
        getHeroClassRecord(HeroClass.DRAGOON).unlockGameMode(Game.Mode.DUNGEON);
        getHeroClassRecord(HeroClass.THIEF).unlockGameMode(Game.Mode.DUNGEON);

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
    /*
     * mHero class series
     */
    public void unlockGameMode(HeroClass heroClass, int gameMode) {
        for (HeroClassRecord record : mHeroClassData.records)
            if (record.heroClass == heroClass)
                record.unlockGameMode(gameMode);
    }


    // unlock dungeon
    private void unlockDungeon(HeroClass heroClass) {
        for (HeroClassRecord record : mHeroClassData.records)
            if (record.heroClass == heroClass)
                record.unlockGameMode(Game.Mode.DUNGEON);
    }

    // unlock colosseum
    public void unlockColosseum(HeroClass heroClass) {
        for (HeroClassRecord record : mHeroClassData.records)
            if (record.heroClass == heroClass)
                record.unlockGameMode(Game.Mode.COLOSSEUM);
    }

	public void addHeroClass(HeroClass heroClass, boolean isColosseum) {
		HeroClassRecord record = new HeroClassRecord(heroClass);
		if (isColosseum)
			record.unlockGameMode(Game.Mode.COLOSSEUM);
		else
			record.unlockGameMode(Game.Mode.DUNGEON);
		if (!mHeroClassData.records.contains(record)) {
			mHeroClassData.records.add(record);
			Collections.sort(mHeroClassData.records);
		}
	}

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
        if (Game.isPremium) {
            // dungeon
            unlockDungeon(HeroClass.FAIRY);
            // colosseum
            unlockColosseum(HeroClass.FIRE_KNIGHT);
            unlockColosseum(HeroClass.WATER_KNIGHT);
        }
    }

    public ArrayList<HeroClassRecord> getAvailableHeroClassRecord(int gameMode) {
        ArrayList<HeroClassRecord> result = new ArrayList<>();
        for (HeroClassRecord record : mHeroClassData.records) {
            if (record.isGameModeAvailable(gameMode)) {
                result.add(record);
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
        reference to G.GAME_MODE_xxxxxx
     */
    public boolean isGameModeUnlocked(int gameMode) {
        for (HeroClassRecord record : mHeroClassData.records) {
            if (record.isGameModeAvailable(gameMode))
                return true;
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
     * Wilderness and giant series
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

    public boolean isOpenGiantAlbum() {
        for (GiantRecord gr : mWildernessData.giants) {
            if (0 < gr.exp)
                return true;
        }
        return false;
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
            if (mLeaderboardData.heroes.size() > Game.LEADER_BOARD_SIZE)
                mLeaderboardData.heroes.remove(Game.LEADER_BOARD_SIZE);
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
}
