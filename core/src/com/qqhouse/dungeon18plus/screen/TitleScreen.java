package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.MainMenuView;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.Locale;

public class TitleScreen extends QQScreen {

    public interface TitleCallback {
        void onTitle(int gameMode);
    }

    private final TitleCallback callback;

    public TitleScreen(SaveGame savedGame, Viewport viewport, Assets assets, TitleCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {

        // group
        QQLinear group = new QQLinear(6);
        group.setSize(Game.Size.WIDTH * 0.7f, QQView.WRAP_CONTENT);
        addChild(group);

        // Dungeon : default.
        MainMenuView dungeon = new MainMenuView(assets);
        dungeon.reset("skeleton_fighter", "dungeon", "chaotic");
        dungeon.setSize(QQView.MATCH_PARENT, 64);
        dungeon.setQQPressListener(clickListener, Game.Mode.DUNGEON);
        group.addChild(dungeon);

        // Colosseum : defeat skeleton king in the dungeon.
        if (savedGame.openColosseum()) {
            MainMenuView colosseum = new MainMenuView(assets);
            colosseum.reset("arena", "colosseum", "neutral");
            colosseum.setSize(QQView.MATCH_PARENT, 64);
            colosseum.setQQPressListener(clickListener, Game.Mode.COLOSSEUM);
            group.addChild(colosseum);
        }

        // Wilderness : see sword master in the colosseum.
        if (savedGame.openWilderness()) {
            MainMenuView wilderness = new MainMenuView(assets);
            wilderness.reset("steel_cyclops", "wilderness", "ordinary");
            wilderness.setSize(QQView.MATCH_PARENT, 64);
            wilderness.setQQPressListener(clickListener, Game.Mode.WILDERNESS);
            group.addChild(wilderness);
        }

        // Library : default
        MainMenuView gallery = new MainMenuView(assets);
        gallery.reset("peddler", "gallery", "special");
        gallery.setSize(QQView.MATCH_PARENT, 64);
        gallery.setQQPressListener(clickListener, Game.Mode.GALLERY);
        group.addChild(gallery);

        group.setPosition((Game.Size.WIDTH - group.getWidth()) / 2, (Game.Size.HEIGHT - group.getHeight()) / 2);

        // iap and version button...
        QQButtonEx version = new QQButtonEx(assets.getBackgroundSet("lawful"));
        version.setText(assets.getFont(Game.Font.VERSION_TAG), assets.geti18n("version_tag"));
        version.setSize(100, 40);
        version.setPosition(Game.Size.WIDTH - version.getWidth() - 8, 8);
        addChild(version);
    }

    private final QQPressListener clickListener = new QQPressListener() {
        @Override
        public void onPress(int gameMode) {
            callback.onTitle(gameMode);
        }

        @Override
        public void onLongPress(int gameMode) {
            switch (gameMode) {
                case Game.Mode.DUNGEON: {
                    // clear all save data.
                    savedGame.reset();
                } break;
            }
            //Gdx.app.error("TitleScreen", "view = " + gameMode);
            //String[] data = {"black","chaotic","chaotic_down","chaotic_up","dialog","disable","help","hero_info","lawful","lawful_down","lawful_up","level","loot_info","neutral","neutral_down","neutral_up","ordinary","ordinary_down","ordinary_up","progress_back","progress_cyan_primary","progress_cyan_secondary","progress_yellow_primary","progress_yellow_secondary","special","special_down","special_up","underline","white","zako_level"};
            //long t = TimeUtils.millis();
            //for (int i = 0; i < 20000; ++i)
            //    for (String key : data)
            //        assets.getNinePatch(key);
            //Gdx.app.error("TitleScreen", "total time : " + (TimeUtils.millis() - t));
        }
    };

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
