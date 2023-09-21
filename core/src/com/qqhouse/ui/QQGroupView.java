package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQGroupView extends QQView implements QQView.ChildrenVisitor {


    public QQGroupView(QQScreen master) {
        super(master);
        this.visitor = this;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void visitDraw(SpriteBatch batch) {
        Gdx.app.error("TEST", "call children draw...");
    }
}
