package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class QQButton extends QQView {

    private boolean pressed = false;
    private boolean enable = true;
    private NinePatch bgPressed = null;
    private NinePatch bgDisable = null;

    public QQButton(String buttonKey) {
        //super(master);
        String resBase = "button/" + buttonKey;
        bgNormal = new NinePatch(new Texture(Gdx.files.internal(resBase + "_up.png")), 4, 4, 4, 4);
        //bgNormal.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bgPressed = new NinePatch(new Texture(Gdx.files.internal(resBase + "_down.png")), 4, 4, 4, 4);
        //bgPressed.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bgDisable = new NinePatch(new Texture(Gdx.files.internal("button/disable.png")), 4, 4, 4, 4);
        //bgDisable.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    protected void drawBackground(SpriteBatch batch, float originX, float originY) {
        if (!enable && null != bgDisable) {
            bgDisable.draw(batch, originX, originY, width, height);
            return;
        }
        if (pressed && null != bgPressed) {
            bgPressed.draw(batch, originX, originY, width, height);
            return;
        }
        if (null != bgNormal)
            bgNormal.draw(batch, originX, originY, width, height);
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
        if (enable && touchable) {
            pressed = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(float x, float y) {
        //Gdx.app.error("TEST", "touch up : " + this + "@" + TimeUtils.millis());
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
    public boolean touchDragged(float x, float y) {
        if (pressed && null == hit(x, y)) {
            pressed = false;
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
