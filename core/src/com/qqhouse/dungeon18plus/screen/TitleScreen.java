package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.ui.QQScreen;

public class TitleScreen extends QQScreen {

    private Texture txrDungeon, txrColosseum;
    private NinePatch npBG;
    private TitleMenuView menuDungeon;

    public TitleScreen(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void onEnter() {

        menuDungeon = (TitleMenuView) new TitleMenuView("skeleton_king", "Dungeon", "boss").position(60, 560).size(240, 64);
        addView(menuDungeon);
    }

    @Override
    public void onLeave() {
        removeView(menuDungeon);
        menuDungeon.dispose();
    }
}
