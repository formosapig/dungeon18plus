package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class QQText extends QQView{


    protected BitmapFont font;
    protected Color color = null;
    protected String text = "";
    protected float shiftX, shiftY;
    private float alpha = 1; // 0 ~ 1

    public QQText(BitmapFont font) {
        this.font = font;
    }

    public QQText(BitmapFont font, NinePatch background) {
        this.font = font;
        this.bgNormal = background;
    }

    public QQText(BitmapFont font, NinePatch background, float alpha) {
        this.font = font;
        this.bgNormal = background;
        this.alpha = alpha;
    }

    @Override
    public void setSize(float w, float h) {
        super.setSize(w, h);
        rearrange();
    }

    @Override
    public void setAlign(int align) {
        this.align = align;
        rearrange();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColorText(Color color, String text) {
        this.color = color;
        this.text = text;
        rearrange();
    }

    public void setText(String text) {
        this.text = text;
        rearrange();
    }


    protected void rearrange() {
        if (null == text)
            return;

        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // wrap width
        if (wrapWidth) {
            width = glyphs.width + leftPadding + rightPadding;
            //Gdx.app.error("QQText", "wrapWidth = " + width);
        }

        if (wrapHeight) {
            height = glyphs.height + topPadding + bottomPadding;
            //Gdx.app.error("QQText", "wrapHeight = " + height);
        }

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
            batch.setColor(1, 1, 1, alpha);
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
