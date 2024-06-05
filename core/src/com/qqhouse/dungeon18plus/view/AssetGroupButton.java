package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

public class AssetGroupButton extends AssetGroup implements QQView.IsTouchable {

    public AssetGroupButton(Assets assets) {
        super(assets);
    }

    // button background
    private boolean pressed = false;
    private boolean enabled = true;
    private NinePatch bgPressed = null;
    private NinePatch bgDisable = null;

    public void setEnabled(boolean enabled) {this.enabled = enabled;}

    public void setBackground(QQButton.BackgroundSet set) {
        bgNormal = set.normal;
        bgPressed = set.pressed;
        bgDisable = set.disable;
    }

    // long press
    private float longPressCounter = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        if (pressed) {
            longPressCounter += delta;
            if (longPressCounter >= 0.5f) {
                longPressCounter = 0;
                pressed = false;
                if (null != pressListener) {
                    pressListener.onLongPress(this);
                }
            }

        }
    }

    @Override
    protected void drawBackground(SpriteBatch batch, float originX, float originY) {
        if (!enabled && null != bgDisable) {
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

    @Override
    public boolean touchDown(float x, float y) {
        if (enabled && touchable) {
            pressed = true;
        }
        return super.touchDown(x, y);
    }

    @Override
    public boolean touchUp(float x, float y) {
        // process child view.
        if (super.touchUp(x, y))
            return true;

        // process AssetGroupButton
        if (!enabled || !touchable)
            return false;
        if (pressed) {
            longPressCounter = 0;
            pressed = false;
            if (null != pressListener) {
                pressListener.onPress(pressIndex);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(float x, float y) {
        super.touchDragged(x, y);
        if (pressed && null == hit(x, y)) {
            pressed = false;
        }
        return false;
    }


    @Override
    public void cancelTouching() {
        if (pressed) {
            pressed = false;
        }
    }

    /*
        click series ...
     */
    private QQPressListener pressListener;
    private int pressIndex;

    public void setQQPressListener(QQPressListener listener, int index) {
        this.pressListener = listener;
        this.pressIndex = index;
    }

    public void setQQPressListener(QQPressListener listener) {
        this.pressListener = listener;
        this.pressIndex = 0;
    }
}
