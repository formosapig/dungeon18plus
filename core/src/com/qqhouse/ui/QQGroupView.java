package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.SnapshotArray;

public class QQGroupView extends QQView implements QQView.IsParent {

    SnapshotArray<QQView> children;

    public QQGroupView() {
        children = new SnapshotArray<>();
    }

    public void addChildren(QQView view) {
        children.add(view);
    }

    @Override
    public void dispose() {

    }

    //@Override
    //public void visitDraw(SpriteBatch batch) {
    //    QQView[] childrenArray = children.items;
    //    for (int i = children.size - 1; i >= 0; i--) {
    //        childrenArray[i].draw(batch);
    //    }
    //}

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void addChild(QQView view) {

    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {

    }
}
