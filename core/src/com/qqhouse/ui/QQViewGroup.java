package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.ArrayList;

public class QQViewGroup extends QQView implements QQView.IsParent {

    public QQViewGroup() {}

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

    protected ArrayList<QQView> childrenView = new ArrayList<>();

    /*
        add child first add in the bottom...
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
    public void onParentSizeChanged(float width, float height) {
        if (childrenView.isEmpty())
            return;
        for (QQView child : childrenView) {
            if (child.matchWidth && 0 < width) {
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
            }
            if (child.matchHeight && 0 < height) {
                child.setSize(child.getWidth(), height - topPadding - bottomPadding);
            }
        }
    }

    @Override
    public void onChildSizeChanged(QQView child) {
        if (wrapWidth)
            resetWrapWidth();
        if (wrapHeight)
            resetWrapHeight();
        arrangeChildren();
    }

    @Override
    public void arrangeChildren() {}

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView)
            view.draw(batch, originX, originY);
    }
}
