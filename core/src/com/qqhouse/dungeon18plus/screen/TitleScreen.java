package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.dungeon18plus.core.GameAlignment.*;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class TitleScreen extends QQScreen {

    public interface TitleCallback {
        void onTitle(int gameMode);
    }

    private ArrayList<QQView> menus;
    private final TitleCallback callback;
    //private BitmapFont font;

    public TitleScreen(SaveGame savedGame, Viewport viewport, Assets assets, TitleCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {
        //Gdx.app.error("TitleScreen", "onEnter.");
        // bitmap font...
        //font = createFont(28, new Color(0x9E8064FF), "地下城巫師塔圓形競技場荒原魔王城圖書館");
        BitmapFont fnt = assets.getFont(Game.Font.TITLE28);
        fnt.setColor(Game.Colour.RARE);
        //Gdx.app.error("TitleScreen", "font created.");

        menus = new ArrayList<>();

        // Dungeon : default.
        float menu_width = 240;
        float menu_height = 64;
        float menu_margin = 6;
        menus.add(new TitleMenuView(assets.getBackgroundSet(ORDINARY.key),
                assets.getBlockee("skeleton_fighter"),
                fnt,
                assets.geti18n("dungeon"))
                .qqListener(clickListener, Game.Mode.DUNGEON)
                .size(menu_width, menu_height));
        //Gdx.app.error("TitleScreen", "dungeon menu added.");

        // Tower : collect five hero class.
        if (savedGame.isGameModeUnlocked(Game.Mode.TOWER)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(LAWFUL.key),
                    assets.getBlockee("fire_sorcerer"),
                    fnt,
                    assets.geti18n("tower"))
                    .qqListener(clickListener, Game.Mode.TOWER)
                    .size(menu_width, menu_height));
        }

        // Colosseum : defeat skeleton fighter in the dungeon.
        if (savedGame.isGameModeUnlocked(Game.Mode.COLOSSEUM)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(NEUTRAL.key),
                    assets.getBlockee("arena"),
                    fnt,
                    assets.geti18n("colosseum"))
                    .qqListener(clickListener, Game.Mode.COLOSSEUM)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Wilderness : see sword master in the colosseum.
        if (savedGame.isGameModeUnlocked(Game.Mode.WILDERNESS)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(SPECIAL.key),
                    assets.getBlockee("steel_cyclops"),
                    fnt,
                    assets.geti18n("wilderness"))
                    .qqListener(clickListener, Game.Mode.WILDERNESS)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Castle : defeat demon in wilderness
        if (savedGame.isGameModeUnlocked(Game.Mode.CASTLE)) {
            menus.add(new TitleMenuView(assets.getBackgroundSet(CHAOTIC.key),
                    assets.getBlockee("skeleton_king"),
                    fnt,
                    assets.geti18n("castle"))
                    .qqListener(clickListener, Game.Mode.CASTLE)
                    //.defaultDisable()
                    .size(menu_width, menu_height));
        }

        // Library : default
        menus.add(new TitleMenuView(assets.getBackgroundSet(LAWFUL.key),
                assets.getBlockee("merchant"),
                // 下面這行 code 把 texture 變成灰階的了,笑死
                //new Texture(Gdx.files.internal("blockee/merchant.png"), Pixmap.Format.LuminanceAlpha, true),
                fnt,
                assets.geti18n("gallery"))
                .qqListener(clickListener, Game.Mode.GALLERY)
                .size(menu_width, menu_height));
        //Gdx.app.error("TitleScreen", "library menu added.");

        float margin_x = (Game.Size.WIDTH - menu_width) / 2;
        float margin_y = (Game.Size.HEIGHT - (menu_height * menus.size()) - (menu_margin * (menus.size() - 1))) / 2;
        float startY = Game.Size.HEIGHT - margin_y - menu_height;

        for (int i = 0; i < menus.size(); ++i) {
            addChild(menus.get(i));
            menus.get(i).setPosition(margin_x, startY - (menu_height + menu_margin) * i);
        }
        //Gdx.app.error("TitleScreen", "OnEnter finish.");
    }

    private final QQPressListener clickListener = new QQPressListener() {
        @Override
        public void onPress(int gameMode) {
            callback.onTitle(gameMode);
        }

        @Override
        public void onLongPress(QQView view) {

        }
    };

    @Override
    public void onLeave() {
        for (QQView view : menus) {
            removeChild(view);
            view.dispose();
        }
    }
}
