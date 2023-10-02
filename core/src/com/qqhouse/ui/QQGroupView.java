package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;

public class QQGroupView extends QQView implements QQView.ChildrenVisitor {

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

    @Override
    public void visitDraw(SpriteBatch batch) {
        QQView[] childrenArray = children.items;
        for (int i = children.size - 1; i >= 0; i--) {
            childrenArray[i].draw(batch);
        }
    }
}
