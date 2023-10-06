package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class QQText extends QQView{


    protected BitmapFont font;
    protected Color color = null;
    protected String text;
    protected int align;
    protected float shiftX, shiftY;

    public QQText(BitmapFont font) {
        this.font = font;
    }

    public QQText(BitmapFont font, NinePatch background) {
        this.font = font;
        this.bgNormal = background;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        adjust();
    }

    public void setAlign(int align) {
        this.align = align;
        adjust();
    }


    public void setColorText(Color color, String text) {
        this.color = color;
        this.text = text;
        adjust();
    }

    public void setText(String text) {
        this.text = text;
        adjust();
    }


    protected void adjust() {
        if (null == text)
            return;
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = (width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width;
        } else {
            shiftX = 0;
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
    public void drawBackground(SpriteBatch batch, float originX, float originY) {
        if (null != bgNormal) {
            Color tmp = batch.getColor();
            batch.setColor(1, 1, 1, 0.5f);
            bgNormal.draw(batch, originX, originY, width, height);
            batch.setColor(1, 1, 1, 1);
            batch.flush();
        }
    }


    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != color) {
            font.setColor(color);
        }
        font.draw(batch, text, originX + shiftX, originY + shiftY);
    }

    @Override
    public void dispose() {

    }
}
