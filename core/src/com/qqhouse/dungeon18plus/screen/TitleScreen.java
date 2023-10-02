package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.dungeon18plus.core.GameAlignment.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.ui.QQClickListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.List;

public class TitleScreen extends QQScreen {

    public interface TitleCallback {
        void onTitle(int gameMode);
    }

    private ArrayList<QQView> menus;
    private TitleCallback callback;
    private BitmapFont font;

    public TitleScreen(SaveGame savedGame, Viewport viewport, TitleCallback callback) {
        super(savedGame, viewport);
        this.callback = callback;
    }

    @Override
    public void onEnter() {

        // language bundle
        lanBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/dungeon18plus"));

        // bitmap font...
        font = createFont(28, new Color(0x9E8064FF), "地下城巫師塔圓形競技場荒原魔王城圖書館");

        menus = new ArrayList<QQView>();

        // Dungeon : default.
        float menu_width = 240;
        float menu_height = 64;
        float menu_margin = 6;
        menus.add(new TitleMenuView(ORDINARY.key,
                getTexture("blockee/skeleton_fighter.png"),
                font,
                getI18N("dungeon"))
                .qqListener(clickListener, G.GAME_MODE_DUNGEON)
                .size(menu_width, menu_height));

        // Tower : collect five hero class.
        if (savedGame.isGameModeUnlocked(G.GAME_MODE_TOWER)) {
            menus.add(new TitleMenuView(LAWFUL.key,
                    getTexture("blockee/fire_sorcerer.png"),
                    font,
                    getI18N("tower"))
                    .qqListener(clickListener, G.GAME_MODE_TOWER)
                    .size(menu_width, menu_height));
        }

        // Colosseum : defeat skeleton fighter in the dungeon.
        if (savedGame.isGameModeUnlocked(G.GAME_MODE_COLOSSEUM)) {
            menus.add(new TitleMenuView(NEUTRAL.key,
                    getTexture("blockee/arena.png"),
                    font,
                    getI18N("colosseum"))
                    .qqListener(clickListener, G.GAME_MODE_COLOSSEUM)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Wilderness : see sword master in the colosseum.
        if (savedGame.isGameModeUnlocked(G.GAME_MODE_WILDERNESS)) {
            menus.add(new TitleMenuView(SPECIAL.key,
                    getTexture("blockee/steel_cyclops.png"),
                    font,
                    getI18N("wilderness"))
                    .qqListener(clickListener, G.GAME_MODE_WILDERNESS)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Castle : defeat demon in wilderness
        if (savedGame.isGameModeUnlocked(G.GAME_MODE_CASTLE)) {
            menus.add(new TitleMenuView(CHAOTIC.key,
                    getTexture("blockee/skeleton_king.png"),
                    font,
                    getI18N("castle"))
                    .qqListener(clickListener, G.GAME_MODE_CASTLE)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Library : default
        menus.add(new TitleMenuView(LAWFUL.key,
                getTexture("blockee/merchant.png"),
                // 下面這行 code 把 texture 變成灰階的了,笑死
                //new Texture(Gdx.files.internal("blockee/merchant.png"), Pixmap.Format.LuminanceAlpha, true),
                font,
                getI18N("library"))
                .qqListener(clickListener, G.GAME_MODE_LIBRARY)
                .size(menu_width, menu_height));

        float margin_x = (G.WIDTH - menu_width) / 2;
        float margin_y = (G.HEIGHT - (menu_height * menus.size()) - (menu_margin * (menus.size() - 1))) / 2;
        float startY = G.HEIGHT - margin_y - menu_height;

        for (int i = 0; i < menus.size(); ++i) {
            addView(menus.get(i));
            menus.get(i).setPosition(margin_x, startY - (menu_height + menu_margin) * i);
        }
    }

    private QQClickListener clickListener = new QQClickListener() {
        @Override
        public void onClick(int gameMode) {
            callback.onTitle(gameMode);
        }
    };

    @Override
    public void onLeave() {
        for (QQView view : menus) {
            removeView(view);
            view.dispose();
        }
        font.dispose();
    }
}
