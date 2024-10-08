package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.Help;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;

public class Game {

    public static void trace(Object obj, String format, Object... param) {
        if (!Debug.TRACE) return;
        String clsInfo = obj.toString();
        Gdx.app.error(clsInfo.substring(clsInfo.lastIndexOf(".") + 1), String.format(Locale.US, format, param));
    }

    // graphic
    public static final class Size {
        // UI Setting
        // 主流是 19 : 9 , 縮小成 16 : 9 依然好看... 相反的話就很醜....
        // 在 sharp aquos s2 上是 17 : 9 ...
        public static final int WIDTH = 360; // in pixel
        public static final int HEIGHT = 680; // in pixel
        public static final int UI_MARGIN = 4; // in pixel

        public static final int DIALOG_MARGIN = 22; // dialog left / right margin...
        public static final int WIDGET_MARGIN = 4; // margin between widgets
        public static final int INNER_MARGIN = 2;  // margin inside widget
        public static final int BLOCKEE_PADDING = 8; // padding around blockee....

        public static final int BLOCKEE_SIZE = 48; // 48 x 48
    }

    public static final String SAVE_FILE = "d18p2";

    // game mode, used in HeroClassRecord.flag
    public static final class Mode {
        public static final int DUNGEON    = 1<<0;
        public static final int TOWER      = 1<<1;
        public static final int COLOSSEUM  = 1<<2;
        public static final int WILDERNESS = 1<<3;
        public static final int CASTLE     = 1<<4;
        public static final int GALLERY    = 1<<5;
    }

    public static final class GalleryAction {
        public static final int EQUIPMENT_CATALOG = 1;
        public static final int MONSTER_GUIDE = 2;
        public static final int DUNGEON_LEADERBOARD = 3;
        public static final int WILDERNESS_BARRACK = 4;
        public static final int HERO_ALBUM = 5;
        public static final int GIANT_ALBUM = 6;
    }

    // debug flag
    public static final class Debug {
        // debug tag
        public static final String TAG                              = "d18p";
        // premium feature.
        public static final boolean PREMIUM                         = false;
        // test enum duplicate.
        public static final boolean TEST_ENUM                       = true;
        // test help message
        public static final boolean TEST_HELP                       = false;
        // powerful start mHero
        public static final boolean QUICK_GAME                      = false;
        // test special potion
        public static final boolean SPECIAL_POTION                  = true;
        // create gladiator
        public static final boolean CREATE_GLADIATOR                = false;
        // calculate collectable equipment
        public static final boolean COLLECTABLE_EQUIPMENT           = true;
        // auto finish campaign in wilderness mode
        public static final boolean CAMPAIGN_AUTOMATION             = false;
        // fill up random soul
        public static final boolean FILL_UP_RANDOM_SOUL             = true;
        // Log QQView series's trace info
        public static final boolean TRACE                           = true;
        // all giant.
        public static final boolean TEST_ALL_GIANT                  = true;
        // call summary dialog
        public static final boolean CALL_SUMMARY_DIALOG             = true;
        // call Equipment Select Dailog
        public static final boolean CALL_SELECT_EQUIPMENT_DIALOG    = true;
    }

    /*
     * Game Settings
     */
    public static final class Setting {
        // global setting
        public static final int GLOBAL_MAX_DAMAGE                   = 9999;
        public static final int GLOBAL_HERO_MIN_SPEED               = 4;
        // campaign ( Wilderness battle / Dual Battle )
        public static final int CAMPAIGN_MAX_DAMAGE                 = 99999;
        public static final int CAMPAIGN_MAX_TIME                   = 999;
        /*
         * Hero Define
         */
        public static final int HERO_MAX_MIRROR         = 3;
        public static final int HERO_DEFAULT_SOUL_SIZE  = 4;
        public static final int HERO_MAX_LIFE           = 3000;
        /*
         * Mastery define
         */
        public static final int MASTERY_NOT_FOUND       = -1;     // can not find mastery data.
        public static final int GENERAL_MASTERY_MAX     = 10;    // mastery max with general equipment
    }



    // debug setup, should set false when release.


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
    // FIXME 移去由各個 manager 自行定義
    public static final class result {
        public static final int process      = 0;
        public static final int win          = -2;
        public static final int lose         = -3;
        public static final int process_boss = -4;
    }

    /*
       color
     */
    // 使用 Color 會導致 Color 引用完整的 package name + class name 變成很長一串...
    // 所以改用 Colour...
    public static final class Colour {
        public static final Color LIFE       = new Color(0xFFFF60FF);
        public static final Color ATTACK     = new Color(0xFF6060FF);
        public static final Color DEFENSE    = new Color(0x8080FFFF);
        public static final Color SPEED      = new Color(0x60FF60FF);
        public static final Color DAMAGE     = new Color(0xFF60FFFF);
        public static final Color RARE       = new Color(0x9E8064FF);
        public static final Color RANK       = new Color(0x60FFFFFF);
        public static final Color ROUND      = new Color(0xB0A0C0FF);
        public static final Color GUARD      = new Color(0xF0F0F0FF);
        public static final Color ZAKO_LEVEL = new Color(0xFFFFFFFF);
        public static final Color COUNT      = new Color(0xFFFFFFFF);
        public static final Color SOUL       = new Color(0xB0A0C0FF);
        public static final Color MASTERY    = new Color(0x80E0FFFF);
        public static final Color SOUL_LEVEL = new Color(0xFFE080FF);
        public static final Color TIME       = SPEED;//new Color(0x80E080FF);
        public static final Color CALENDAR   = new Color(0xAAAAAAFF);   // kingdom calendar
        public static final Color CAMPAIGN   = new Color(0x1F2B36FF);   // campaign 荒原藍 (91,122,153)
    }

    /*
       font ...
     */
    public static final class Font {
        public static final Assets.FontSet TITLE28 = new Assets.FontSet("NotoSansTC-Regular", 28);
        public static final Assets.FontSet NAME20 = new Assets.FontSet("NotoSansTC-Regular", 20);
        public static final Assets.FontSet HELP14 = new Assets.FontSet("NotoSansTC-Regular", 14);

        public static final Assets.FontSet LEVEL16 = new Assets.FontSet("ConsolaMono-Bold", 16);
        //public static final Assets.FontSet LEVEL16 = new Assets.FontSet("overpass-mono.bold", 15);//ConsolaMono-Bold", 16);
        public static final Assets.FontSet MASTERY14 = new Assets.FontSet("whitrabt", 14, true);
        public static final Assets.FontSet DIGITAL16 = new Assets.FontSet("whitrabt", 16, true);
        //public static final Assets.FontSet HERO_ABILITY = new Assets.FontSet("Nouveau_IBM", 18);
        public static final Assets.FontSet HERO_ABILITY = new Assets.FontSet("whitrabt", 18, true);
        public static final Assets.FontSet LOOT_INFO = HERO_ABILITY;//new Assets.FontSet("whitrabt", 18, true);
        public static final Assets.FontSet EVENT_COST = new Assets.FontSet("whitrabt", 22, true);

        // version tag
        public static final Assets.FontSet VERSION_TAG = EVENT_COST;

        // campaign
        public static final Assets.FontSet CAMPAIGN = HERO_ABILITY;
        public static final Assets.FontSet CAMPAIGN_SMALL = DIGITAL16;
        public static final Assets.FontSet CAMPAIGN_BIG = EVENT_COST;

        // mastery ?
        public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("overpass-mono.bold", 15);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("ConsolaMono-Bold", 16);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("natural-mono.bold", 16);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("minim.100", 16);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("intelone-mono.bold", 16);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("panoptic-monospace.bold", 10);
        //public static final Assets.FontSet ITEM_COUNT = new Assets.FontSet("quadaptor.regular", 14, true);



    }



    /*
     * cost type
     */
    public static final class Cost {
        public static final int NONE   = 0; // cost nothing
        public static final int KEY    = 1; // cost key
        public static final int COIN   = 2; // cost coin
        public static final int STAR   = 3; // cost star/soul
        public static final int DAMAGE = 4; // battle and cost life
        public static final int NEVER  = 5; // can pay any cost
        public static final int BLOCK  = 6; // block, no damage.
    }

    // zako data
    public static final int ZAKO_LEVEL_MAX     = 20;    // highest level
    // boss data
    public static final int BOSS_LEVEL_MAX    = 7;    // skeleton king exclude




    /*
     * from another dungeon
     */
    public static final long DEFAULT_DUNGEON_NUMBER = 0;

    public static final int COLLECTABLE_EQUIPMENT = 46;  // 會用程式檢查一遍...





    // if buy premium.
    public static boolean isPremium = false || Debug.PREMIUM;

    public void start() {

        // premium test
        if (Game.Debug.PREMIUM) {
            Game.isPremium = true;
        }

        // bundle data to application's life cycle.
        //if (!GameData.getInstance().validDungeonId()) {
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
        //}

        //GameData.getInstance();

        if (Game.Debug.TEST_ENUM) {
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
                    if (!testSoulIcon.add(s.iconKey) && !s.isMoneyBag())
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


        /*if (TEST_UNIQUE_SKILL_COOL_DOWN) {
            for (UniqueSkill us : UniqueSkill.values()) {
                UniqueSkillData data = us.get(Game.Setting.SPECIFIC_MASTERY_MAX);
                if (40 >= data.coolDown) {
                    Gdx.app.error("d18", String.format("Unique Skill : %s[%d] -> CD=%d", us.toString(), Game.Setting.SPECIFIC_MASTERY_MAX, data.coolDown));
                }
            }
        }*/

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
            if (count != Game.COLLECTABLE_EQUIPMENT) {
                throw new RuntimeException(String.format(Locale.US, "collectable equipment should be : %d (%d)", count, Game.COLLECTABLE_EQUIPMENT));
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
    public static void checkAllEnum() {

        boolean pass = true;
        // test
        Set<Integer> test = new TreeSet<>();
        test.add(0);
        for (EventType e : EventType.values()) {
            if (!test.add(e.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", e.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (GiantRace g : GiantRace.values()) {
            if (!test.add(g.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", g.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (HeroClass h : HeroClass.values()) {
            if (!test.add(h.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", h.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
            }
        }

        for (Item l : Item.values()) {
            if (!test.add(l.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error(Game.Debug.TAG, String.format(Locale.US, "%s duplicate, set code = 0x%08X", l, newCode));
                pass = false;
            }
        }

        // check soul
        for (UniqueSkill us : UniqueSkill.values()) {
            if (!test.add(us.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", us.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
            }
        }

        // check soul
        for (Soul s : Soul.values()) {
            if (!test.add(s.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", s.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
            }
        }

        // check help
        for (Help h : Help.values()) {
            if (!test.add(h.code)) {
                int newCode = new Random().nextInt();
                Gdx.app.error("D18", h.toString() + " duplicate, set code = 0x" + String.format("%08X", newCode));
                pass = false;
                //throw new RuntimeException(h.toString() + " duplicate, set code = " + String.format("%08X", newCode));
            }
        }

        if (!pass)
            throw new RuntimeException("code setting fail.");

    }

    public static void checkUniqueSkill() {
        Gdx.app.error("D18", "check unique skill");
        Ability base = new Ability(3000, 300, 300, 1, 300, 4);
        for (UniqueSkill us : UniqueSkill.values()) {
            final int lv = new Random().nextInt(20) + 1;
            UniqueSkillData data = us.get(lv);
            String opStr = "";
            for (Operation2 op : data.operations)
                opStr += op.getType() + ":" + op.getText(base) + "/";
            Gdx.app.error("D18", String.format(Locale.US, "%16s L%2d CD(%3d) -> %s", us, lv, data.coolDown, opStr));
            //data = us.get(20);
            //opStr = "";
            //for (Operation2 op : data.operations)
            //    opStr += op.getText(base) + "/";
            //Gdx.app.error("D18", String.format(Locale.US, "Unique Skill : %s[20] CD : %d -> %s", us, data.coolDown, opStr));
        }
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
