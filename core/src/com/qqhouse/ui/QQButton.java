package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQButton extends QQView {

    public QQButton(QQScreen master, String buttonKey) {
        super(master);
        pressed = false;
        disable = false;
        String resBase = "button/" + buttonKey;
        btnNormal = new NinePatch(new Texture(Gdx.files.internal(resBase + "_up.png")), 4, 4, 4, 4);
        btnNormal.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        btnPressed = new NinePatch(new Texture(Gdx.files.internal(resBase + "_down.png")), 4, 4, 4, 4);
        btnPressed.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        btnDisable = new NinePatch(new Texture(Gdx.files.internal("button/disable.png")), 4, 4, 4, 4);
        btnDisable.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    /*
        click series ...
     */
    private QQClickListener clickListener;
    private int index;
    private boolean pressed;
    private boolean disable;
    private final NinePatch btnNormal;
    private final NinePatch btnPressed;
    private final NinePatch btnDisable;


    public void addQQClickListener(QQClickListener listener, int index) {
        this.clickListener = listener;
        this.index = index;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    @Override
    public boolean touchDown(float x, float y) {
        if (!disable)
            pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(float x, float y) {
        if (disable)
            return false;
        if (pressed) {
            pressed = false;
            if (null != clickListener) {
                clickListener.onClick(index);
            }
            return true;
        }
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        // draw background ... must called first...
        if (disable)
            btnDisable.draw(batch, x, y, width, height);
        else {
            if (pressed)
                btnPressed.draw(batch, x, y, width, height);
            else
                btnNormal.draw(batch, x, y, width, height);
        }
    }

    @Override
    public void dispose() {
        btnNormal.getTexture().dispose();
        btnPressed.getTexture().dispose();
        btnDisable.getTexture().dispose();
    }

    // chain function
    public QQButton defaultDisable() {
        this.setDisable(true);
        return this;
    }

    public QQButton qqListener(QQClickListener listener, int index) {
        this.clickListener = listener;
        this.index = index;
        return this;
    }

}
