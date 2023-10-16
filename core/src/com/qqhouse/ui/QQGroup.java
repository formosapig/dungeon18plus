package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.Game;

import java.util.ArrayList;

public class QQGroup extends QQView implements QQView.IsParent {

    private boolean vertical = true; // vertical / horizontal
    private float innerMargin = 0;
    public void setInnerMargin(float margin) {this.innerMargin = margin;}

    private void rearrange() {

    }



    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {
        if (vertical) {

        }
    }

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
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
        if (wrapWidth) {
            float w = 0;
            for (QQView v : childrenView)
                w += v.getWidth() + innerMargin;
            width = w - innerMargin + leftPadding + rightPadding;
        }
        if (wrapHeight) {
            float h = 0;
            for (QQView v : childrenView)
                h += v.getHeight() + innerMargin;
            height = h - innerMargin + topPadding + bottomPadding;
        }
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView)
            view.draw(batch, originX, originY);
    }
}
