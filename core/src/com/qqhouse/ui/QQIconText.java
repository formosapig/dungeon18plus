package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

/*
    icon's size = this.height x this.height ...
 */

public class QQIconText extends QQText {
    public QQIconText(BitmapFont font, Texture icon) {
        super(font);
        this.icon = icon;
        //this.align = Align.right;
    }

    private Texture icon;

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        batch.draw(icon, originX, originY, height, height);
        super.drawForeground(batch, originX, originY);
    }

    //@Override
    //public void setText(String text) {
    //    GlyphLayout glyphs = new GlyphLayout();
    //    glyphs.setText(font, text);

    //    width = 16 + 2 + glyphs.width;
    //    super.setText(text);
    //}

}
