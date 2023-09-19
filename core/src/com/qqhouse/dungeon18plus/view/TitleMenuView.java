package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    @Override
    public boolean touchDown(float x, float y) {
        Gdx.app.error("TEST", "touchDown : " + this.menu);
        return true;
    }

    @Override
    public boolean touchUp(float x, float y) {
        Gdx.app.error("TEST", "touchUp : " + this.menu);
        return true;
    }

    public void draw(SpriteBatch batch) {
        // draw gradient (漸層)
        ShapeRenderer render;


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
