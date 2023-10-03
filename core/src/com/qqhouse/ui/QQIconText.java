package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
    icon's size = this.height x this.height ...
 */

public class QQIconText extends QQText {
    public QQIconText(BitmapFont font, Texture icon) {
        super(font);
        this.icon = icon;
    }

    private Texture icon;

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        batch.draw(icon, originX, originY, height, height);
        super.drawForeground(batch, originX, originY);
    }


}
