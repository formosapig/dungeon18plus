package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQView;

public class TitleMenuView extends QQView {

    private Texture txrBlockee;
    private String menu;
    private NinePatch npBackground;

    public TitleMenuView(String blockeeName, String menu, String background) {
        txrBlockee = new Texture(Gdx.files.internal("blockee\\" + blockeeName + ".png"));
        this.menu = menu;
        npBackground = new NinePatch(new Texture(Gdx.files.internal("btn\\" + background + ".png")), 4, 4, 4, 4);
    }

    public void draw(SpriteBatch batch) {
        // draw background
        npBackground.draw(batch, x, y, width, height);
        // draw blockee
        batch.draw(txrBlockee, 8 + x, 8 + y);
        // draw font...
        //batch.draw();


    }


    @Override
    public void dispose() {
        txrBlockee.dispose();
        npBackground.getTexture().dispose();
    }
}
