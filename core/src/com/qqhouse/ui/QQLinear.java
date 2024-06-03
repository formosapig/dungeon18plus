package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.ArrayList;

public class QQLinear extends QQViewGroup {

    /*
     *   vertical = default
     *   top -> down
     *
     *   horizontal
     *   left -> right
     */
    private boolean isVertical = true;
    protected float innerMargin = 0;

    public QQLinear() {}

    public QQLinear(int innerMargin) {
        this.innerMargin = innerMargin;
    }

    public QQLinear(boolean isVertical, int innerMargin) {
        this.isVertical = isVertical;
        this.innerMargin = innerMargin;
    }

    /*
     * resetWrapWidth / resetWrapHeight
     */
    @Override
    public void resetWrapWidth() {
        if (childrenView.isEmpty())
            return;
        float w = 0;
        for (QQView v : childrenView)
            w += v.getWidth() + innerMargin;
        width = w - innerMargin + leftPadding + rightPadding;
    }

    @Override
    public void resetWrapHeight() {
        if (childrenView.isEmpty())
            return;
        float h = 0;
        for (QQView v : childrenView)
            h += v.getHeight() + innerMargin;
        height = h - innerMargin + topPadding + bottomPadding;
    }

    /*
     * IsParent series
     *
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (childrenView.isEmpty())
            return;
        for (QQView child : childrenView) {
            if (isVertical) {
                if (child.matchWidth && 0 < width)
                    child.setSize(width - leftPadding - rightPadding, child.getHeight());
            } else {
                if (child.matchHeight && 0 < height)
                    child.setSize(child.getWidth(), height - topPadding - bottomPadding);
            }
        }
    }

    @Override
    public void arrangeChildren() {
        if (childrenView.isEmpty())
            return;

        if (isVertical) {
            float anchorY = height - topPadding;
            for (QQView v : childrenView) {
                if (!v.isVisible() || v.getHeight() == 0)
                    continue;
                // 滿版時, 重設 x 的位置, 否則依照 v 原本的設定...
                anchorY -= v.getHeight();
                v.setPosition(v.matchWidth ? leftPadding : v.getX(), anchorY);
                anchorY -= innerMargin;
            }
        } else {
            float anchorX = leftPadding;
            for (QQView v : childrenView) {
                if (!v.isVisible() || v.getWidth() == 0)
                    continue;
                anchorX -= v.getWidth();
                v.setPosition(anchorX, v.matchHeight ? topPadding : v.getY());
                anchorX += innerMargin;
            }

        }


    }



}
