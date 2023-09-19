package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.Help;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.core.UltimateSkill;
import com.qqhouse.dungeon18plus.gamedata.GameData;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;

public class G {

    // UI Setting
    public static final int WIDTH = 360; // in pixel
    public static final int HEIGHT = 640; // in pixel
    public static final int UI_MARGIN = 4; // in pixel

    // blockee size.
    public static final int BLOCKEE_SIZE = 48;

    // test title menu
    public static final boolean FULL_TITLE_MENU = true;



    // debug flag
    public static final class Debug {
        // debug tag
        public static final String TAG                              = "d18";
        // premium feature.
        public static final boolean PREMIUM                         = true;
        // test enum duplicate.
        public static final boolean TEST_ENUM                       = true;
        // debug recycler view series
        public static final boolean RECYCLER_VIEW                   = false;
        public static final boolean RECYCLER_ADAPTER                = false;
        // test help message
        public static final boolean TEST_HELP                       = false;
        // powerful start mHero
        public static final boolean QUICK_GAME                      = false;
        // bluetooth function...
        public static final boolean BLUETOOTH                       = true;
        // test special potion
        public static final boolean SPECIAL_POTION                  = true;
        // creat galidator
        public static final boolean CREATE_GLADIATOR                = false;
        // calculate collectable equipment
        public static final boolean COLLECTABLE_EQUIPMENT           = true;
        // auto finish campaign in wilderness mode
        public static final boolean AUTOMAGIC_WILDERNESS_CAMPAIGN   = true;
        // fill up random soul
        public static final boolean FILL_UP_RANDOM_SOUL             = true;
    }

    /*
     * Game Settings
     */
    public static final class Setting {
        // global setting
        public static final int GLOBAL_MAX_DAMAGE                   = 9999;
        public static final int GLOBAL_HERO_MIN_SPEED               = 4;
        // campaign ( Wilderness battle / Dual Battle )
        public static final int CAMPAIGN_MAX_TIME                   = 999;


    }



    // debug setup, should set false when release.

    public static final boolean TEST_ALL_GIANT                 = false;
    public static final boolean TEST_GIANT_DROP_SAME_SOUL_ICON = false;

    public static final boolean TEST_UNIQUE_SKILL_COOL_DOWN    = false;
    //public static final boolean DEBUG = false;

    // leader board size
    public static final int LEADER_BOARD_SIZE        = 20;

    // legion size
    public static final int MAX_LEGION_SIZE            =  8;

    // barrack size
    public static final int MAX_BARRACK_SIZE        = 20;

    // game data file name
    public static final String GAME_DATA_FILE_NAME     = "d18";

    /*
     * result
     */
    public static final class result {
        public static final int process      = 0;
        public static final int win          = -2;
        public static final int lose         = -3;
        public static final int process_boss = -4;
    }


    /*
     * cost type
     */
    public static final class cost {
        public static final int none    = 0; // cost nothing
        public static final int key     = 1; // cost key
        public static final int coin    = 2; // cost coin
        public static final int star    = 3; // cost star/soul
        public static final int damage  = 4; // battle and cost life
        public static final int never   = 5; // can pay any cost
    }

    // zako data
    public static final int ZAKO_LEVEL_MAX     = 20;    // highest level
    // boss data
    public static final int BOSS_LEVEL_MAX    = 7;    // skeleton king exclude

    /*
     * Hero Define
     */
    public static final int HERO_MAX_MIRROR        = 3;
    public static final int HERO_DEFAULT_SOUL_SIZE = 4;
    public static final int HERO_MAX_LIFE          = 3000;

    /*
     * Mastery define
     */
    public static final int MASTERY_NOT_FOUND    = -1;    // can not find mastery data.
    public static final int SPECIFIC_MASTERY_MAX = 200;    // mastery max with specific equipment
    public static final int GENERAL_MASTERY_MAX     = 100;    // mastery max with general equipment

    /*
     * from another dungeon
     */
    public static final long DEFAULT_DUNGEON_NUMBER = 0;

    public static final int COLLECTABLE_EQUIPMENT = 46;  // 會用程式檢查一遍...





    // if buy premium.
    public static boolean isPremium = false;

    public void start() {

        // premium test
        if (G.Debug.PREMIUM) {
            G.isPremium = true;
        }

        // bundle data to application's life cycle.
        if (!GameData.getInstance().validDungeonId()) {
            // get ADID and setup dungeon id
            // get advertise id
            /*
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    String adId = "dungeon18";
                    try {
                        AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext.get());
                        if (null != adInfo) {
                            adId = adInfo.getId();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    GameData.getInstance().setDungeonIdAndInitialExtraData(createHashCode(adId));
                }
            });
            */
        }

        GameData.getInstance();

        if (G.Debug.TEST_ENUM) {
            checkAllEnum();

//            int sum = 0;
//            int count = 0;
//            for (Loot loot : Loot.values()) {
//                if (loot.isEquipment()) {
//                    count++;
//                    sum += loot.cost;
//                }
//            }
//            Log.e("D18", "AVG Cost : " + (int)(sum / count));

        }

        if (TEST_GIANT_DROP_SAME_SOUL_ICON) {
            for (GiantRace gr : GiantRace.values()) {
                final Set<String> testSoulIcon = new TreeSet<>();
                testSoulIcon.clear();
                for (Soul s : gr.drops) {
                    if (!testSoulIcon.add(s.icon) && !s.isMoneyBag())
                        throw new RuntimeException("Duplicate soul icon : " + gr + "/" + s);
                }
            }
        }

//        // test = =+
//        for (int i = 1; i < 31; ++i) {
//
//            // new
//            Ability newOne = EventType.GRIFFON.getAbility(i);
//            Ability new2 = EventType.SKELETON.getAbility(i);
//            Log.e("d18", "data : " + newOne + "/" + new2);
//    }

        if (TEST_UNIQUE_SKILL_COOL_DOWN) {
            for (UltimateSkill us : UltimateSkill.values()) {
                UniqueSkillData data = us.get(G.SPECIFIC_MASTERY_MAX);
                if (40 >= data.coolDown) {
                    Gdx.app.error("d18", String.format("Unique Skill : %s[%d] -> CD=%d", us.toString(), G.SPECIFIC_MASTERY_MAX, data.coolDown));
                }
            }
        }

        if (Debug.SPECIAL_POTION) {
            //Game.consumeSKU(BillingConstants.SKU_POTION);
        }

        if (Debug.COLLECTABLE_EQUIPMENT) {
            int count = 0;
            for (Item item : Item.values()) {
                if (item.isEquipment() && item.isNotSpecial() && item.isNotPremium()) {
                    count++;
                }
            }
            if (count != G.COLLECTABLE_EQUIPMENT) {
                throw new RuntimeException(String.format(Locale.US, "collectable equipment should be : %d (%d)", count, G.COLLECTABLE_EQUIPMENT));
            }
        }
    }

    // adid to md5 integer ... 18 * 18 = 324, 324th prime = 2143
    private int createHashCode(String input) {
        int result = 0;
        for (byte b : input.getBytes()) {
            result = result * 2143 + b;
        }
        return result;
    }


    // check all enum codes are not duplicate.
    private void checkAllEnum() {

        boolean pass = true;
        // test
        Set<Integer> test = new TreeSet<>();
        test.add(0);
        for (EventType e : EventType.values()) {
            if (!test.add(e.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", e.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (GiantRace g : GiantRace.values()) {
            if (!test.add(g.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", g.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (HeroClass h : HeroClass.values()) {
            if (!test.add(h.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", h.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (Item l : Item.values()) {
            if (!test.add(l.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error(G.Debug.TAG, String.format(Locale.US, "%s duplicate, set code = %08X", l, newCode));
                pass = false;
            }
        }

        // check soul
        for (UltimateSkill us : UltimateSkill.values()) {
            if (!test.add(us.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", us.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
            }
        }

        // check soul
        for (Soul s : Soul.values()) {
            if (!test.add(s.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", s.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
            }
        }

        // check help
        for (Help h : Help.values()) {
            if (!test.add(h.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", h.toString() + " duplicate, set code = " + String.format("%08X", newCode));
                pass = false;
                //throw new RuntimeException(h.toString() + " duplicate, set code = " + String.format("%08X", newCode));
            }
        }

        if (!pass)
            throw new RuntimeException("code setting fail.");

    }

    // sku ....
    public static void consumeSKU(String sku) {
        /*
        switch (sku) {
            case BillingConstants.SKU_POTION: {
                GameData.getInstance().addJob(new Runnable() {
                    @Override
                    public void run() {
                        // give 9 potions.
                        // 5% cyan potion
                        // 15% green potion
                        // 25% blue potion
                        // 25% red potion
                        // 30% yellow potion
                        for (int i = 0; i < 9; ++i) {
                            final int seed = new Random().nextInt(100);
                            Item loot;
                            if (seed < 5) loot = Item.CYAN_POTION;
                            else if (seed < 20) loot = Item.GREEN_POTION;
                            else if (seed < 45) loot = Item.BLUE_POTION;
                            else if (seed < 70) loot = Item.RED_POTION;
                            else loot = Item.YELLOW_POTION;
                            GameData.getInstance().setSpecialItem(loot, 1);
                        }
                        // show toast.
                        Toast toast = Toast.makeText(Game.getContext(), "you get 9 potion.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    @Override
                    public String toString() { return "Add Special Potion x 9."; }
                });
            }
            break;
        }
        */
    }
}
