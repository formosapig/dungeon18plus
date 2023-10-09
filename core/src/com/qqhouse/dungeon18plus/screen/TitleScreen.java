package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.dungeon18plus.core.GameAlignment.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQClickListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class TitleScreen extends QQScreen {

    public interface TitleCallback {
        void onTitle(int gameMode);
    }

    private ArrayList<QQView> menus;
    private TitleCallback callback;
    private BitmapFont font;

    public TitleScreen(SaveGame savedGame, Viewport viewport, Assets assets, TitleCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {
        // bitmap font...
        font = createFont(28, new Color(0x9E8064FF), "地下城巫師塔圓形競技場荒原魔王城圖書館");

        menus = new ArrayList<QQView>();

        // Dungeon : default.
        float menu_width = 240;
        float menu_height = 64;
        float menu_margin = 6;
        menus.add(new TitleMenuView(assets.getBackgroundSet(ORDINARY.key),
                assets.getBlockee("skeleton_fighter"),
                font,
                assets.geti18n("dungeon"))
                .qqListener(clickListener, Game.GAME_MODE_DUNGEON)
                .size(menu_width, menu_height));

        // Tower : collect five hero class.
        if (savedGame.isGameModeUnlocked(Game.GAME_MODE_TOWER)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(LAWFUL.key),
                    assets.getBlockee("fire_sorcerer"),
                    font,
                    assets.geti18n("tower"))
                    .qqListener(clickListener, Game.GAME_MODE_TOWER)
                    .size(menu_width, menu_height));
        }

        // Colosseum : defeat skeleton fighter in the dungeon.
        if (savedGame.isGameModeUnlocked(Game.GAME_MODE_COLOSSEUM)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(NEUTRAL.key),
                    assets.getBlockee("arena"),
                    font,
                    assets.geti18n("colosseum"))
                    .qqListener(clickListener, Game.GAME_MODE_COLOSSEUM)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Wilderness : see sword master in the colosseum.
        if (savedGame.isGameModeUnlocked(Game.GAME_MODE_WILDERNESS)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(SPECIAL.key),
                    assets.getBlockee("steel_cyclops"),
                    font,
                    assets.geti18n("wilderness"))
                    .qqListener(clickListener, Game.GAME_MODE_WILDERNESS)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Castle : defeat demon in wilderness
        if (savedGame.isGameModeUnlocked(Game.GAME_MODE_CASTLE)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(CHAOTIC.key),
                    assets.getBlockee("skeleton_king"),
                    font,
                    assets.geti18n("castle"))
                    .qqListener(clickListener, Game.GAME_MODE_CASTLE)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Library : default
        menus.add(new TitleMenuView(assets.getBackgroundSet(LAWFUL.key),
                assets.getBlockee("merchant"),
                // 下面這行 code 把 texture 變成灰階的了,笑死
                //new Texture(Gdx.files.internal("blockee/merchant.png"), Pixmap.Format.LuminanceAlpha, true),
                font,
                assets.geti18n("library"))
                .qqListener(clickListener, Game.GAME_MODE_LIBRARY)
                .size(menu_width, menu_height));

        float margin_x = (Game.WIDTH - menu_width) / 2;
        float margin_y = (Game.HEIGHT - (menu_height * menus.size()) - (menu_margin * (menus.size() - 1))) / 2;
        float startY = Game.HEIGHT - margin_y - menu_height;

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
