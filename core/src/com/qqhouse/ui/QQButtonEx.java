package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO should implements IsParent ??????
public class QQButtonEx extends QQButton implements QQView.IsParent {

    private QQText text;
    private QQImage img; // TODO can put image...

    public QQButtonEx(BackgroundSet backgroundSet) {
        super(backgroundSet);
    }

    public void setText(BitmapFont font, String text) {
        if (null == this.text) {
            this.text = new QQText(font);
            this.text.setText(text);
        }
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != text) {
            text.draw(batch, originX, originY);
        }
    }

    @Override
    public void addChild(QQView view) {}

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void arrangeChildren() {
        if (text != null) {
            text.setSize(this.width, this.height);
            text.setPosition(0, 0);
        }
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {}
}
