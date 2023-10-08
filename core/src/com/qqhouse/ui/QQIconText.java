package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

/*
    icon's size = this.height x this.height ...
 */

public class QQIconText extends QQText {
    public QQIconText(BitmapFont font, Texture icon) {
        super(font);
        this.icon = icon;
        //font.setFixedWidthGlyphs("01234567890+-=%");
    }

    public QQIconText(BitmapFont font, NinePatch bg, Texture icon) {
        super(font, bg);
        this.icon = icon;
        //this.align = Align.right;
    }

    private Texture icon;

    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    @Override
    protected void rearrange() {
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // width
        if (wrapWidth) {
            width = icon.getWidth() + 2 + glyphs.width;
        }

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = (height + 2 + width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width;
        } else {
            shiftX = height + 2;
        }

        // shift y
        if (Align.isCenterVertical(align)) {
            shiftY = (height + glyphs.height) / 2;
        } else if (Align.isBottom(align)) {
            shiftY = glyphs.height;
        } else {
            shiftY = height;
        }
    }

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
