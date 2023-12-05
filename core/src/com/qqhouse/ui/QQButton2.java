package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQButton2 extends QQView implements QQView.IsTouchable {

    private boolean pressed = false;
    private boolean enable = true;
    private NinePatch bgPressed = null;
    private NinePatch bgDisable = null;

    public QQButton2(QQButton.BackgroundSet set) {
        setBackground(set);
    }

    public QQButton2() {}

    public void setBackground(QQButton.BackgroundSet set) {
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
                if (null != pressListener)
                    pressListener.onLongPress(this);
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

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean touchDown(float x, float y) {
        //Gdx.app.error("QQButton", String.format(Locale.US, "touchDown. enable:%b, touchable:%b", enable, touchable));
        if (enable && touchable) {
            pressed = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(float x, float y) {
        //Gdx.app.error("TEST", "touch up : " + this + "@" + TimeUtils.millis());
        //Gdx.app.error("QQButton", String.format(Locale.US, "touchUp enable:%b, touchable:%b", enable, touchable));
        if (!enable || !touchable)
            return false;
        //Gdx.app.error("QQButton", String.format(Locale.US, "touchUp pressed:%b", pressed));
        if (pressed) {
            longPressCounter = 0;
            pressed = false;
            if (null != pressListener) {
                pressListener.onPress(this);
            }
            return true;
        }
        return false;
    }

    // list view 內的元件 cancel 的機制是另外一套?!

    @Override
    public boolean touchDragged(float x, float y) {
        //Gdx.app.error("QQButton", "touchDragged.");
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
            //Gdx.app.error("QQButton", "cancelTouching.");
            pressed = false;
        }
    }

    // chain function
    public QQButton2 disable() {
        enable = false;
        return this;
    }
}
