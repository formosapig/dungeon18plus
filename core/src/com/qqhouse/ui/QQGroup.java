package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.Game;

import java.util.ArrayList;

public class QQGroup extends QQView implements QQView.IsParent {

    public static final int DIRECT_NONE = 0;
    public static final int DIRECT_VERTICAL = 1;
    public static final int DIRECT_HORIZONTAL = 2;

    private int direct = DIRECT_NONE;

    public QQGroup(int direct) {
        this.direct = direct;
    }


    private float innerMargin = 0;
    public void setInnerMargin(float margin) {this.innerMargin = margin;}

    private void rearrange() {

    }

    /*
        touch events
     */
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        // walk through children view
        QQView target = null;
        for (QQView child : childrenView) {
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                return target.touchDown(childRelativeX, childRelativeY);
            }
        }
        return false;
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
        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView child = childrenView.get(i);
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                if (target.touchUp(childRelativeX, childRelativeY)) {
                    return true;
                }
            }
        }

        return false;
    }
    /*
        touch dragged event.
        1. do scroll by dragged distance ....
        2. tell child dragged if exit hit area ?
     */
    @Override
    public boolean touchDragged(float relativeX, float relativeY) {
        for (QQView v : childrenView) {
            v.touchDragged(relativeX - v.getX(), relativeY - v.getY());
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        for (QQView v : childrenView) {
            v.scrolled(amountX, amountY);
        }
        return false;
    }

    /*
        act...
     */
    @Override
    public void act(float delta) {
        for (QQView view : childrenView)
            view.act(delta);
    }

    @Override
    public void resetWrapWidth() {
        float w = 0;
        for (QQView v : childrenView)
            w += v.getWidth() + innerMargin;
        width = w - innerMargin + leftPadding + rightPadding;
    }

    @Override
    public void resetWrapHeight() {
        float h = 0;
        for (QQView v : childrenView)
            h += v.getHeight() + innerMargin;
        height = h - innerMargin + topPadding + bottomPadding;
    }



    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {
        if (DIRECT_VERTICAL == direct) {
            // check if view need match parent ...
            int matchChildren = 0;
            float heightForMatch = height;

            for (QQView v : childrenView) {
                if (v.matchHeight)
                    matchChildren++;
                else
                    heightForMatch -= v.getHeight();
            }

            // heightForMatch split to matchChildren
            for (QQView v : childrenView) {
                if (v.matchHeight)
                    v.height = heightForMatch / matchChildren;
            }

            // reset position
            float anchorY = 0;
            for (QQView v : childrenView) {
                v.setPosition(v.getX(), anchorY);
                anchorY += v.getHeight() + innerMargin;
                Gdx.app.error("QQGroup", "put v in : " + v.getX() + "," + v.getY() + "@" + v);
                Gdx.app.error("QQGroup", "    size : " + v.getWidth() + "," + v.getHeight());
            }
        }
    }

    /*
        add child from bottom to top, from left to right ...
     */
    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
        view.setParent(this);
        // calculate child size
        if (view.matchWidth) {
            if (wrapWidth)
                throw new GdxRuntimeException("wrap width with match width child.");
            view.setSize(width - leftPadding - rightPadding, view.getHeight());
        }
        if (view.matchHeight) {
            if (wrapHeight)
                throw new GdxRuntimeException("wrap height with match height child.");
            view.setSize(view.getWidth(), height - topPadding - bottomPadding);
        }
        if (wrapWidth)
            resetWrapWidth();
        if (wrapHeight)
            resetWrapHeight();
        // arrange child...
        arrangeChildren();
    }




    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView)
            view.draw(batch, originX, originY);
    }
}
