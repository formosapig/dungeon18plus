package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    public void setImage(TextureRegion img) {
        if (null == this.img) {
            this.img = new QQImage(img);
        }
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != text) {
            text.draw(batch, originX, originY);
        }
        if (null != img) {
            img.draw(batch, originX, originY);
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
        if (null != img) {
            img.setPosition(leftPadding, bottomPadding);
        }
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {}
}
