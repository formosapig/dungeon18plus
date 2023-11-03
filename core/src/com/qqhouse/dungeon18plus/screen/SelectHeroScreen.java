package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.view.PreviewView;
import com.qqhouse.dungeon18plus.view.TitleBarView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectHeroScreen extends QQScreen implements QQPressListener {

    public interface SelectHeroCallback {
        void onSelectHero(int gameMode, HeroClass hero);
    }

    public SelectHeroScreen(SaveGame savedGame, Viewport viewport, Assets assets, SelectHeroCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    private SelectHeroCallback callback;
    private int gameMode;

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    // resource kept
    //private BitmapFont fntTitle;
    //private BitmapFont fntName;
    //private BitmapFont fntDesc;
    private QQListView list;
    private ArrayList<HeroClassRecord> availableHero;

    @Override
    public void onEnter() {

        // initial hero class adapter ...
        savedGame.checkUnlockHeroClass();

        availableHero = savedGame.getAvailableHeroClass(gameMode);


        // initial font.
        // bitmap font...
        //fntTitle = createFont(18, Color.WHITE, "請選擇英雄");

        // group of background.
        QQGroup group = new QQGroup(QQGroup.DIRECT_VERTICAL, Game.Size.INNER_MARGIN);
        group.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        group.setSize(Game.Size.WIDTH - 12 - 12, Game.Size.HEIGHT * 0.9f);
        group.setPosition(12, Game.Size.HEIGHT * 0.05f);
        group.setPadding(8);
        addChild(group);

        // list of heroes
        ArrayList<HeroClass> tmp = new ArrayList<>();
        tmp.add(HeroClass.NOVICE);
        tmp.add(HeroClass.BARBARIAN);
        tmp.add(HeroClass.BERSERKER);
        tmp.add(HeroClass.DRAGOON);
        tmp.add(HeroClass.THIEF);
        tmp.add(HeroClass.ASSASSIN);
        tmp.add(HeroClass.CRUSADER);
        tmp.add(HeroClass.FAIRY);
        tmp.add(HeroClass.SKELETON_KING);
        tmp.add(HeroClass.CLERIC);
        tmp.add(HeroClass.RED_MAGE);
        tmp.add(HeroClass.BLUE_MAGE);
        tmp.add(HeroClass.GREEN_MAGE);
        tmp.add(HeroClass.SWORD_MASTER);

        // FIXME hero name must change font color...
        //fntName = createFont("NotoSansTC-Bold.ttf", 20, new Color(0x9E8064FF), "");
        //fntDesc = createFont(14, Color.WHITE, "'");
        //ScrollPane

        // list view of hero preview view
        list = (QQListView) new QQListView()
                .size(QQView.MATCH_PARENT, QQView.MATCH_PARENT).
                position(0, 0);
        list.setCamera(getCamera());
        list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        group.addChild(list);

        for (int i = 0, s = tmp.size(); i < s; ++i) {
            HeroClass hero = tmp.get(i);
            PreviewView view = new PreviewView(
                    assets.getBackgroundSet(hero.alignment.key), // Alignment decides background.
                    assets.getBlockee(hero.key),
                    assets.getFont(Game.Font.NAME20),
                    assets.geti18n(hero.key),
                    assets.getFont(Game.Font.HELP14),
                    assets.geti18n(hero.key+"_help"));
            view.setPadding(8);
            view.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            view.addQQClickListener(this, hero.code);
            list.addView(view);
        }

        // one title view ..., just print select hero ?
        TitleBarView title = new TitleBarView(
                assets.getBlockee("fairy"),
                assets.getFont(Game.Font.NAME20),
                "Select Hero : ");

        title.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        //addView(title);
        title.setPadding(8);
        title.setSize(QQView.MATCH_PARENT, 48);
        title.setPosition(0, Game.Size.HEIGHT - 48);
        group.addChild(title);

    }

    @Override
    public void onLeave() {
        list.dispose();
    }

    /*
        click to select hero class...
     */
    @Override
    public void onPress(int code) {
        //Gdx.app.error("TEST", "click. @" + TimeUtils.millis());
        //Gdx.app.error("TEST", "click");
        //Gdx.app.error("TEST", "click " + HeroClass.find(code) + "@" + TimeUtils.millis());
        // 時間上來看沒有什麼特別的問題. 差 1 ms 而已.
        callback.onSelectHero(gameMode, HeroClass.find(code));
    }

    @Override
    public void onLongPress(QQView view) {

    }
}
