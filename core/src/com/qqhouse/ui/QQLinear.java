package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
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
    protected boolean isVertical = true;
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
        for (QQView v : childrenView) {
            if (v.isVisible())
                w += v.getWidth() + innerMargin;
        }
        width = w - innerMargin + leftPadding + rightPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    @Override
    public void resetWrapHeight() {
        if (childrenView.isEmpty())
            return;
        float h = 0;
        for (QQView v : childrenView) {
            if (v.isVisible())
                h += v.getHeight() + innerMargin;
        }
        height = h - innerMargin + topPadding + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
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
        if (0 >= width || 0 >= height)
            return;

        if (isVertical) {
            // check if view need match parent ...
            int matchChildren = 0;
            float heightForMatch = height - topPadding - bottomPadding;

            for (QQView v : childrenView) {
                // TODO float compare needs fix.
                if (!v.isVisible() || 0 >= v.getHeight())
                    continue;
                if (v.matchHeight)
                    matchChildren++;
                else {
                    heightForMatch -= v.getHeight();
                }
                heightForMatch -= innerMargin;
            }
            heightForMatch += innerMargin;
            // heightForMatch split to matchChildren
            if (0 < matchChildren) {
                for (QQView v : childrenView) {
                    if (v.matchHeight && v.isVisible()) {
                        v.setSize(v.getWidth(), heightForMatch / matchChildren);
                        //v.height = heightForMatch / matchChildren;
                    }
                }
            }

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
            // 由左至右 ... TODO 檢查這一段程式碼
            // check if view need match parent ...
            int matchChildren = 0;
            float widthForMatch = width - leftPadding - rightPadding;

            for (QQView child : childrenView) {
                if (!child.isVisible())
                    continue;
                if (child.matchWidth)
                    matchChildren++;
                else // TODO if height == 0 ? should consider this.
                    widthForMatch -= child.getHeight();
                if (child.matchHeight && 0 == child.height)
                    child.setSize(child.getWidth(), height - topPadding - bottomPadding);
            }

            // widthForMatch split to matchChildren
            if (0 < matchChildren) {
                for (QQView v : childrenView) {
                    if (v.matchWidth && v.isVisible()) {
                        //Gdx.app.error("QQGroup", "v.set size." + v.getWidth() + "," + heightForMatch / matchChildren);
                        v.setSize(widthForMatch / matchChildren, v.getHeight());
                        //v.height = heightForMatch / matchChildren;
                    }
                }
            }

            // get total width
            float totalWidth = 0;
            for (QQView cv : childrenView) {
                totalWidth += cv.getWidth() + innerMargin;
            }
            totalWidth -= innerMargin;

            float x = 0;
            if (Align.isRight(align)) {
                x = width - leftPadding - rightPadding - totalWidth;
            } else if (Align.isCenterHorizontal(align)) {
                x = (width - leftPadding - rightPadding - totalWidth) / 2 + leftPadding;
            }

            for (QQView cv : childrenView) {
                cv.setPosition(x, bottomPadding);
                x += cv.getWidth() + innerMargin;
            }
        }
    }
}
