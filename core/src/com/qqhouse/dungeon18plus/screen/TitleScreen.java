package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.ImageResolver;
import com.qqhouse.dungeon18plus.view.TitleMenuView;
import com.qqhouse.ui.QQScreen;

public class TitleScreen extends QQScreen {

    private Texture txrDungeon, txrColosseum;
    private NinePatch npBG;
    private TitleMenuView menuDungeon;


    @Override
    public void onEnter() {

        menuDungeon = (TitleMenuView) new TitleMenuView("skeleton_king", "Dungeon", "boss").position(60, 560).size(240, 64);


    }

    @Override
    public void onLeave() {
        menuDungeon.dispose();
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        menuDungeon.draw(batch);
    }
}
