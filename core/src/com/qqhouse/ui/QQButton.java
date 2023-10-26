package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class QQButton extends QQView implements QQView.IsTouchable {

    public static class BackgroundSet {
        public NinePatch normal;
        public NinePatch pressed;
        public NinePatch disable;
    }

    private boolean pressed = false;
    private boolean enable = true;
    private NinePatch bgPressed = null;
    private NinePatch bgDisable = null;

    public QQButton(BackgroundSet set) {
        setBackground(set);
        //bgNormal = new NinePatch(assets.getTexture("button", buttonKey + "_up"), 4, 4, 4, 4);
        //bgPressed = new NinePatch(assets.getTexture("button", buttonKey + "_down"), 4, 4, 4, 4);
        //bgDisable = new NinePatch(assets.getTexture("button", "disable"), 4, 4, 4, 4);
    }

    public QQButton() {}

    public void setBackground(BackgroundSet set) {
        bgNormal = set.normal;
        bgPressed = set.pressed;
        bgDisable = set.disable;
    }

    /*
        long press series
     */
    private float longPressCounter = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        if (pressed) {
            longPressCounter += delta;
            if (longPressCounter >= 0.5f) {
                longPressCounter = 0;
                pressed = false;
                if (null != clickListener)
                    clickListener.onLongPress(this);
            }

        }
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
    private QQPressListener clickListener;
    private int index;

    public void addQQClickListener(QQPressListener listener, int index) {
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
                clickListener.onPress(index);
            }
            return true;
        }
        return false;
    }

    // list view 內的元件 cancel 的機制是另外一套?!

    @Override
    public boolean touchDragged(float x, float y) {
        if (pressed && null == hit(x, y)) {
            pressed = false;
        }
        return false;
    }

    @Override
    public void dispose() {}

    @Override
    public void cancelTouching() {
        if (pressed) {
            pressed = false;
        }
    }

    // chain function
    public QQButton disable() {
        enable = false;
        return this;
    }

    public QQButton qqListener(QQPressListener listener, int index) {
        this.clickListener = listener;
        this.index = index;
        return this;
    }
}
