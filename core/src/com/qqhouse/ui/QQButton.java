package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public abstract class QQButton extends QQView {

    public QQButton(QQScreen master, String buttonKey) {
        super(master);
        String resBase = "button/" + buttonKey;
        bgNormal = new NinePatch(new Texture(Gdx.files.internal(resBase + "_up.png")), 4, 4, 4, 4);
        bgNormal.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bgPressed = new NinePatch(new Texture(Gdx.files.internal(resBase + "_down.png")), 4, 4, 4, 4);
        bgPressed.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bgDisable = new NinePatch(new Texture(Gdx.files.internal("button/disable.png")), 4, 4, 4, 4);
        bgDisable.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    /*
        click series ...
     */
    private QQClickListener clickListener;
    private int index;

    public void addQQClickListener(QQClickListener listener, int index) {
        this.clickListener = listener;
        this.index = index;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean touchDown(float x, float y) {
        if (enable && touchable)
            pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(float x, float y) {
        Gdx.app.error("TEST", "touch up : " + this);
        if (!enable || !touchable)
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
    public void dispose() {
        bgNormal.getTexture().dispose();
        bgPressed.getTexture().dispose();
        bgDisable.getTexture().dispose();
    }

    // chain function
    public QQButton disable() {
        enable = false;
        return this;
    }

    public QQButton qqListener(QQClickListener listener, int index) {
        this.clickListener = listener;
        this.index = index;
        return this;
    }

}
