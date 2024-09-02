package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.TimeUtils;
import com.qqhouse.ui.QQButton;

import java.sql.Time;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Assets {

    public AssetManager manager = new AssetManager();

    private I18NBundle langBundle;

    //private final String i18nFile = "i18n/dungeon18plus";
    private final String i18nFile = "i18n/game_text";
    private final String chineseChar = "。！：，／？";
    private final String chineseChar2 = "地下城法師塔圓形競技場荒原魔王圖書館鑑選擇英雄挑戰巨獸失敗勝利時間到在個回合內殺死分數安息取而代之離開偶爾出現的軍團教官協助你建立來打有會獲得靈魂她可以幫控制力量紀錄了所中收集裝備過怪物列表冒險高排行榜勇敢們這裏查閱職業詳細資料傳記解鎖條件基礎屬性上限動專長絕招熟練度付費版容擊他敬請期待尚未尋暗者陰影潛無聲敵人渴望光明卻能夠操縱黑更多野蠻自大雪山遠古遺族極端惡劣環境培養強韌體魄及堅持底意志狂士扛著一把劍獨往從不與交談悍畏鬥激發色寶箱躲裡偽成樣子當靠近後突然攻是移被吞噬據說面希史萊姆跟喜歡東西吃掉其復生命隻組緩碰炸飛滿天爛泥啫血狼階平常態月蝕才變總唯也它機藍學徒皇家院冰系實習化防禦憂鬱思考讓加坐必需答問題暴風掩埋貓妖精貴氣質紳七種語言牧恢主全部角最十字擅使用各武器具身重甲導致作遲頓去落兼差眼只謎試永恒誘惑國門老舊兵盾牌達先完美格擋再架進衝土騎四沉默擁黃鏡小仙女聖授予神並帶領倒骷髏火脾燥紅炎術遊戲結束沒任何決事殊博綠雷快速密稱青南瓜但爆知道顆為什麼如此且又新獅鷲鷹頭喙和翅膀鍊金菜鳥拆扣手點準都就闖入果早販處閒晃賣還某太硬吸鮮異或骨誕殘忍犧牲喚醒對拿盪似乎找君統治整爬亮粉秘隨戒指鋼鐵由造產幾弱石臉板張住路釁話識淵歷吹泡因追求雙目特元素盜賊別逃走妙空商遇損慘雨嘴放閃電禁咒陷阱食花瘋管心欲給哭牆鋒泣水溫柔善良夜很鑰匙銅幣銀錢店買星提昇珠白晶正些詛幅藥暫訓短刀杖經年累非趁木聊於靴藏少議匠磨鍛品製世罕見已消千絲萬縷關係祥焰賜熄滅凍輕揮滾搬敲塊愛套祝福奇增界樹枝幹構龍怒吼寒冬呼嘯驗值等級較獵幸運兒忿受傷定程害價減破直接除偷淨勢療每次複背包零視窗按將左右滑模式購終檢外方閉啟狀示鍵鈕許贏三傑巧我諸昏施像位類推冷活引好確認旦改想要參嗎抗信吧棄營退承同疊狩九始畢役相";

    public Assets() {
        manager = new AssetManager();

        // free type font ...
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // change locale...
        //Locale.setDefault(Locale.ENGLISH);
    }

    public void dispose() {
        if (null != atlas)
            atlas.dispose();
        manager.dispose();
    }

    public String geti18n(String key) {
        if (null == key || "" == key)
            return "";
        if (null == langBundle) {
            long t = TimeUtils.millis();
            manager.load(i18nFile, I18NBundle.class);//, new I18NBundleLoader.I18NBundleParameter(Locale.TRADITIONAL_CHINESE));
            manager.finishLoading();
            langBundle = manager.get(i18nFile, I18NBundle.class);
            //Gdx.app.error("Assets", String.format(Locale.US, "geti18n(%S) : %d", key, (TimeUtils.millis() - t)));
            //Gdx.app.error("Assets", "Default Local : " + Locale.getDefault());
        }
        return langBundle.get(key);
    }

    public String formati18n(String key, Object... args) {
        if (null == langBundle) {
            long t = TimeUtils.millis();
            manager.load(i18nFile, I18NBundle.class);//, new I18NBundleLoader.I18NBundleParameter(Locale.TRADITIONAL_CHINESE));
            manager.finishLoading();
            langBundle = manager.get(i18nFile, I18NBundle.class);
            //Gdx.app.error("Assets", String.format(Locale.US, "formati18n(%S) : %d", key, (TimeUtils.millis() - t)));

        }
        return langBundle.format(key, args);
    }

    public BitmapFont getFont(FontSet set) {
        return getFont(set.name, set.size, set.fixedWidth);
    }

    public static final class FontSet {
        public String name;
        public int size;
        public boolean fixedWidth = false;
        public FontSet(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public FontSet(String name, int size, boolean fixedWidth) {
            this.name = name;
            this.size = size;
            this.fixedWidth = fixedWidth;
        }
    }

    private BitmapFont getFont(String fontName, int fontSize, boolean fixedWidth) {
        String fontKey = String.format(Locale.US, "%s%d.ttf", fontName, fontSize);
        if (!manager.contains(fontKey)) {
            long t = TimeUtils.millis();
            FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
            param.fontFileName = "font/" + fontName + ".ttf";
            param.fontParameters.size = fontSize;
            param.fontParameters.minFilter = Texture.TextureFilter.Linear;
            param.fontParameters.magFilter = Texture.TextureFilter.Linear;
            param.fontParameters.characters += chineseChar + chineseChar2;
            manager.load(fontKey, BitmapFont.class, param);
            manager.finishLoadingAsset(fontKey);
            // fixed size ?! white rabbit 的 1 沒有 fixed width ... 導致浪費效能...
            //BitmapFont font = manager.get(fontKey, BitmapFont.class);
            //font.setFixedWidthGlyphs(FreeTypeFontGenerator.DEFAULT_CHARS);
            Gdx.app.error("Assets", String.format(Locale.US, "getFont(%S) : %d", fontName, (TimeUtils.millis() - t)));

        }
        BitmapFont font = manager.get(fontKey, BitmapFont.class);
        if (fixedWidth)
            font.setFixedWidthGlyphs("0123456789");//0123456789");
        return font;
    }

    /*
        button background set series
     */
    private Map<String, QQButton.BackgroundSet> buttonBackgroundSets = new HashMap<>();

    public QQButton.BackgroundSet getBackgroundSet(String key) {
        if (!buttonBackgroundSets.containsKey(key)) {
            long t = TimeUtils.millis();
            QQButton.BackgroundSet set = new QQButton.BackgroundSet();
            set.normal = getNinePatch(key + "_up");//new NinePatch(getButton(key + "_up"), 4, 4, 4, 4);
            set.pressed = getNinePatch(key + "_down");//new NinePatch(getButton(key + "_down"), 4, 4, 4, 4);
            set.disable = getNinePatch("disable");//new NinePatch(getButton("disable"), 4, 4, 4, 4);
            buttonBackgroundSets.put(key, set);
            //Gdx.app.error("Assets", String.format(Locale.US, "getBackgroundSet(%S) : %d", key, (TimeUtils.millis() - t)));

        }
        return buttonBackgroundSets.get(key);
    }

    /*
        text region series
     */
    private TextureAtlas atlas;

    //private Map<String, TextureRegion> regions = new HashMap<>();

    // 在 30 萬次讀取的情況下 findRegion ~= 99 ms , HashMap ~= 20 ms.... 所以沒有必要用 HashMap 存起來...
    public TextureRegion getBlockee(String key) {
        if (null == atlas) {
            manager.load("game.atlas", TextureAtlas.class);
            manager.finishLoadingAsset("game.atlas");
            atlas = manager.get("game.atlas", TextureAtlas.class);
        }
        //if (!regions.containsKey(key)) {
        //    regions.put(key, atlas.findRegion("blockee/" + key));
        //}
        //return regions.get(key);
        //Gdx.app.error("Assets", "find blockee : " + key);
        return atlas.findRegion("blockee/" + key);
    }

    public TextureRegion getIcon(String key) {
        if (null == atlas) {
            manager.load("game.atlas", TextureAtlas.class);
            manager.finishLoadingAsset("game.atlas");
            atlas = manager.get("game.atlas", TextureAtlas.class);
        }
        return atlas.findRegion(key);
    }

    public TextureRegion getBackground(String key) {
        if (null == atlas) {
            manager.load("game.atlas", TextureAtlas.class);
            manager.finishLoadingAsset("game.atlas");
            atlas = manager.get("game.atlas", TextureAtlas.class);
        }
        //Gdx.app.error("Assets", "getBackground : " + key);
        return atlas.findRegion("bg/" + key);
    }

    private Map<String, NinePatch> ninePatchs = new HashMap<>();

    // desktop create Nine Patch = 0 ms.
    // 20000 次讀取時, createPatch = 365 ms , HashMap = 25 ms....
    public NinePatch getNinePatch(String key) {
        //long t = TimeUtils.millis();
        if (null == atlas) {
            manager.load("game.atlas", TextureAtlas.class);
            manager.finishLoadingAsset("game.atlas");
            atlas = manager.get("game.atlas", TextureAtlas.class);
        }
        if (!ninePatchs.containsKey(key))
            //NinePatch np = atlas.createPatch("9patch/" + key);
            ninePatchs.put(key, atlas.createPatch("9patch/" + key));
            //Gdx.app.error("Assets", "create9Patch : " + key);
        //Gdx.app.error("Assets", String.format(Locale.US, "getNinePatch(%S) : %d", key, (TimeUtils.millis() - t)));
        return ninePatchs.get(key);
        //return atlas.createPatch("9patch/" + key);
    }
}
