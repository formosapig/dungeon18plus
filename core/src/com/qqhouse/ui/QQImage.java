package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class QQImage extends QQView {
    private TextureRegion region;

    public QQImage() {}

    public QQImage(TextureRegion region) {
        setImage(region);
    }

    public void setImage(TextureRegion region) {
        this.region = region;
        this.width = region.getRegionWidth();
        this.height = region.getRegionHeight();
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        batch.draw(region, originX, originY, width, height);
    }
}
