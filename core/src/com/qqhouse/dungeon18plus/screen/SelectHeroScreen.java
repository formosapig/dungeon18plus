package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.view.PreviewView;
import com.qqhouse.dungeon18plus.view.TitleBarView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectHeroScreen extends QQScreen implements QQPressListener {

    public interface SelectHeroCallback {
        void onSelectHero(int gameMode, HeroClass hero);
    }

    public SelectHeroScreen(Viewport viewport, Assets assets, SelectHeroCallback callback) {
        super(null, viewport, assets);
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

    @Override
    public void onEnter() {
        // initial font.
        // bitmap font...
        //fntTitle = createFont(18, Color.WHITE, "請選擇英雄");

        // one title view ..., just print select hero ?
        TitleBarView title = new TitleBarView(
                assets.getBlockee("fairy"),
                assets.getFont(Game.Font.NAME20),
                "Select Hero : ");

        //addView(title);
        title.setPadding(8);
        title.setSize(Game.WIDTH, 48);
        title.setPosition(0, Game.HEIGHT - 48);
        addView(title);

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
                .size(Game.WIDTH, Game.HEIGHT - title.getHeight() - 8).
                position(0, 0).
                padding(8);
        list.setCamera(getCamera());
        addView(list);

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
