package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.List;

public class TitleScreen extends QQScreen {

    private Texture txrDungeon, txrColosseum;
    private NinePatch npBG;
    private ArrayList<QQView> menus;

    public TitleScreen(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void onEnter() {

        menus = new ArrayList<QQView>();

        // Dungeon : default.
        float menu_width = 240;
        float menu_height = 64;
        float menu_margin = 4;
        menus.add(new TitleMenuView("skeleton_fighter", "地下城"/*"Dungeon"*/, "boss").size(menu_width, menu_height));
        // Tower : collect five hero class.
        menus.add(new TitleMenuView("fire_sorcerer", "巫師塔"/*"Tower"*/, "boss").size(menu_width, menu_height));
        // Colosseum : defeat skeleton fighter in the dungeon.
        menus.add(new TitleMenuView("arena", "圓形競技場"/*"Colosseum"*/, "boss").size(menu_width, menu_height));
        // Wilderness : see sword master in the colosseum.
        menus.add(new TitleMenuView("steel_cyclops", "荒原"/*"Wilderness"*/, "boss").size(menu_width, menu_height));
        // Castle : defeat demon in wilderness
        menus.add(new TitleMenuView("skeleton_king", "魔王城"/*"Castle"*/, "boss").size(menu_width, menu_height));
        // Library : default
        menus.add(new TitleMenuView("merchant", "圖書館"/*"Library"*/, "boss").size(menu_width, menu_height));

        float margin_x = (G.WIDTH - menu_width) / 2;
        float margin_y = (G.HEIGHT - (menu_height * menus.size()) - (menu_margin * (menus.size() - 1))) / 2;
        float startY = G.HEIGHT - margin_y - menu_height;

        for (int i = 0; i < menus.size(); ++i) {
            addView(menus.get(i), margin_x, startY - (menu_height + menu_margin) * i);
        }
    }

    @Override
    public void onLeave() {
        for (QQView view : menus) {
            removeView(view);
            view.dispose();
        }
    }
}
