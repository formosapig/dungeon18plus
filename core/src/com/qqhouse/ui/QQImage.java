package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQImage extends QQView {
    private Texture img;

    public QQImage() {}

    public QQImage(Texture texture) {
        this.img = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void setImage(Texture texture) {
        this.img = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        batch.draw(img, originX, originY, width, height);
    }
}
