package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.dungeon18plus.core.GameAlignment.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
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
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.ui.QQClickListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.List;

public class TitleScreen extends QQScreen {

    public interface TitleCallback {
        public void onTitle(int titleMenu);
    }

    private Texture txrDungeon, txrColosseum;
    private NinePatch npBG;
    private ArrayList<QQView> menus;
    private TitleCallback callback;

    public TitleScreen(Viewport viewport, TitleCallback callback) {
        super(viewport);
        this.callback = callback;
    }

    @Override
    public void onEnter() {

        // language bundle
        lanBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/dungeon18plus"));

        // bitmap font...
        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NotoSansTC-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 28; // font size
        parameter.color = new Color(0x9E8064FF);
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "地下城巫師塔圓形競技場荒原魔王城圖書館";
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
        // 加了這行, 字體變漂亮了... 在手機上的效果無法確定....
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        menus = new ArrayList<QQView>();

        // Dungeon : default.
        float menu_width = 240;
        float menu_height = 64;
        float menu_margin = 6;
        menus.add(new TitleMenuView(this, NORMAL.key)
                .setResource("dungeon", "skeleton_fighter")
                .qqListener(clickListener, G.TITLE_DUNGEON)
                .size(menu_width, menu_height));
        // Tower : collect five hero class.
        menus.add(new TitleMenuView(this, LAWFUL.key)
                .setResource("tower", "fire_sorcerer")
                .qqListener(clickListener, G.TITLE_TOWER)
                //.defaultDisable()
                .size(menu_width, menu_height));
        // Colosseum : defeat skeleton fighter in the dungeon.
        menus.add(new TitleMenuView(this, NEUTRAL.key)
                .setResource("colosseum", "arena")
                .qqListener(clickListener, G.TITLE_COLOSSEUM)
                //.defaultDisable()
                .size(menu_width, menu_height));
        // Wilderness : see sword master in the colosseum.
        menus.add(new TitleMenuView(this, SPECIAL.key)
                .setResource("wilderness", "steel_cyclops")
                .qqListener(clickListener, G.TITLE_WILDERNESS)
                //.defaultDisable()
                .size(menu_width, menu_height));
        // Castle : defeat demon in wilderness
        menus.add(new TitleMenuView(this, CHAOTIC.key)
                .setResource("castle", "skeleton_king")
                .qqListener(clickListener, G.TITLE_CASTLE)
                //.defaultDisable()
                .size(menu_width, menu_height));
        // Library : default
        menus.add(new TitleMenuView(this, LAWFUL.key)
                .setResource("library", "merchant")
                .qqListener(clickListener, G.TITLE_LIBRARY)
                .size(menu_width, menu_height));

        float margin_x = (G.WIDTH - menu_width) / 2;
        float margin_y = (G.HEIGHT - (menu_height * menus.size()) - (menu_margin * (menus.size() - 1))) / 2;
        float startY = G.HEIGHT - margin_y - menu_height;

        for (int i = 0; i < menus.size(); ++i) {
            menus.get(i).setPosition(margin_x, startY - (menu_height + menu_margin) * i);
        }
    }

    private QQClickListener clickListener = new QQClickListener() {
        @Override
        public void onClick(int index) {
            callback.onTitle(index);
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
