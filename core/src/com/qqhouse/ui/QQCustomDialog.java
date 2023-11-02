package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;

public class QQCustomDialog extends QQView implements QQView.IsParent {

    // FIXME 不能使用專案專屬資源, 因為QQ系列算是 SDK..
    private Assets assets;
    public QQCustomDialog(Assets assets, QQView customView, boolean isModal) {
        this.assets = assets;

        final QQView dialogMask = new QQView();

        // FIXME SDK 不能依存於專案, assets 不是能隨時存取到的資源...
        bgNormal = new NinePatch(assets.getBackground("black"), 4, 4, 4, 4);
        bgNormal.setColor(new Color(1, 1, 1, 0.66f));
        setPosition(0, 0);
        setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        //dialogMask.bgNormal = new NinePatch(assets.getBackground("black"), 4, 4, 4, 4);
        //dialogMask.bgNormal.setColor(new Color(1, 1, 1, 0.66f));
        //dialogMask.setPosition(0, 0);
        //dialogMask.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);

        // add click listener to dialogMask , any click on it will dismiss dialog.
        if (!isModal) {
            addPressListener(new QQView.PressListener() {
                @Override
                public void onPress(QQView view) {
                    dismiss();
                }

                @Override
                public void onLongPress(QQView view) {}
            });
        }

        // calculate view's size
        if (customView.matchWidth)
            customView.setSize(Game.Size.WIDTH - 10 - 10, customView.getHeight());

        // set position
        customView.setPosition((Game.Size.WIDTH - customView.getWidth())/2,
                (Game.Size.HEIGHT - customView.getHeight())/2);

        addChild(customView);


    }

    // remove dialog from parent.
    public void dismiss() {
        if (null != parent) {
            parent.removeChild(this);
        }
    }




    /*
        IsParent series...
     */

    private QQView customView = null;

    @Override
    public void addChild(QQView view) {
        customView = view;
    }

    @Override
    public void removeChild(QQView view) {

    }

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        customView.draw(batch, originX, originY);
    }

    /*
        touch events
     */
    private boolean pressed = false;
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        // walk through children view
        QQView target = null;
        float childRelativeX = relativeX - customView.getX();
        float childRelativeY = relativeY - customView.getY();
        target = customView.hit(childRelativeX, childRelativeY);
        if (null != target) {
            target.touchDown(childRelativeX, childRelativeY);
        } else {
            // hit background...
            if (null != hit(relativeX, relativeY)) {
                pressed = true;
            }
        }

        return true; // always handled because this is dialog.
    }

    /*
        touch up event.
        1. list view will trace this event to cancel scroll mode. reset scrollPos to zero ?
        2. walk through all child and find out hit one, send touch up to it.
     */
    @Override
    public boolean touchUp(float relativeX, float relativeY) {
        // 2. tell child touch up
        QQView target = null;
        float childRelativeX = relativeX - customView.getX();
        float childRelativeY = relativeY - customView.getY();
        target = customView.hit(childRelativeX, childRelativeY);
        if (null != target) {
            target.touchUp(childRelativeX, childRelativeY);
        } else {
            if (null != hit(relativeX, relativeY) && pressed) {
                pressed = false;
                if (null != pressListener) {
                    pressListener.onPress(this);
                }
            }
        }

        return true; // always handled because this is dialog.
    }
    /*
        touch dragged event.
        1. do scroll by dragged distance ....
        2. tell child dragged if exit hit area ?
     */
    @Override
    public boolean touchDragged(float relativeX, float relativeY) {
        customView.touchDragged(relativeX - customView.getX(), relativeY - customView.getY());
        if (pressed && null == hit(relativeX, relativeY)) {
            pressed = false;
        }
        return true; // always handled because this is dialog.
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        customView.scrolled(amountX, amountY);
        // cancel background press
        pressed = false;
        return true; // always handled because this is dialog.
    }

    /*
        act...
     */
    @Override
    public void act(float delta) {
        customView.act(delta);
    }
}
