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

    public QQGroup() {
        this.direct = DIRECT_NONE;
        this.innerMargin = 0;
    }

    public QQGroup(int direct) {
        this.direct = direct;
    }

    public QQGroup(int direct, int innerMargin) {
        this.direct = direct;
        this.innerMargin = innerMargin;
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
        // do not arrange if size is zero...
        if (0 >= width || 0 >= height)
            return;

        // 由下往上排喔....
        if (DIRECT_VERTICAL == direct) {
            // check if view need match parent ...
            int matchChildren = 0;
            float heightForMatch = height - topPadding - bottomPadding;

            for (QQView v : childrenView) {
                if (!v.isVisible())
                    continue;
                if (v.matchHeight)
                    matchChildren++;
                else // TODO if height == 0 ? should consider this.
                    heightForMatch -= v.getHeight();
            }

            // heightForMatch split to matchChildren
            if (0 < matchChildren) {
                for (QQView v : childrenView) {
                    if (v.matchHeight && v.isVisible()) {
                        //Gdx.app.error("QQGroup", "v.set size." + v.getWidth() + "," + heightForMatch / matchChildren);
                        v.setSize(v.getWidth(), heightForMatch / matchChildren);
                        //v.height = heightForMatch / matchChildren;
                    }
                }
            }

            // reset position
            float anchorY = bottomPadding;
            for (QQView v : childrenView) {
                if (!v.isVisible() || v.getHeight() == 0)
                    continue;
                // 滿版時, 重設 x 的位置, 否則依照 v 原本的設定...
                v.setPosition(v.matchWidth ? leftPadding : v.getX(), anchorY);
                anchorY += v.getHeight() + innerMargin;
                //Gdx.app.error("QQGroup", "put v in : " + v.getX() + "," + v.getY() + "@" + v);
                //Gdx.app.error("QQGroup", "    size : " + v.getWidth() + "," + v.getHeight());
            }
        }
    }

    /*
        add child from bottom to top, from left to right ...
     */
    @Override
    public void addChild(QQView child) {
        childrenView.add(child);
        child.setParent(this);
        // calculate child size
        if (child.matchWidth) {
            if (wrapWidth)
                throw new GdxRuntimeException("wrap width with match width child.");
            if (0 < width)
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
        }
        if (child.matchHeight) {
            if (wrapHeight)
                throw new GdxRuntimeException("wrap height with match height child.");
            if (0 < height)
                child.setSize(child.getWidth(), height - topPadding - bottomPadding);
        }
        // calculate my size
        if (wrapWidth)
            resetWrapWidth();
        if (wrapHeight)
            resetWrapHeight();
        // arrange child...
        arrangeChildren();
    }

    @Override
    public void removeChild(QQView view) {}


    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView)
            view.draw(batch, originX, originY);
    }
}
