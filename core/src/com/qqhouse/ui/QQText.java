package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Game;

public class QQText extends QQView {


    protected BitmapFont font;
    protected Color color = Color.WHITE;
    protected String text = "";
    private String truncate = null;
    private boolean wrap = false;
    protected float shiftX, shiftY;
    protected GlyphLayout glyphs = new GlyphLayout();

    //private float alpha = 1; // 0 ~ 1

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
        this.bgNormal.setColor(new Color(1, 1, 1, alpha));
    }

    public BitmapFont getFont() {
        return this.font;
    }

    @Override
    public void setSize(float w, float h) {
        //Game.trace(this, "QQText.setSize w:%.0f, h:%.0f", w, h);
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

    private void setColorText(Color color, String text) {
        this.color = color;
        this.text = text;
        rearrange();
    }

    public void setText(String text) {
        this.text = text;
        wrap = false;
        truncate = null;
        rearrange();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text, boolean wrap) {
        this.text = text;
        this.wrap = wrap;
        truncate = null;
        rearrange();
    }

    public void setText(String text, String truncate) {
        this.text = text;
        wrap = false;
        this.truncate = truncate;
        rearrange();
    }

    private void setTruncate(String truncate) {
        this.truncate = truncate;
        //rearrange();
    }

    private void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    protected void rearrange() {
        // text = "" 時, 還是會有一定的高度
        if (null == text)
            return;

        //Gdx.app.error("QQText", "text = " + text);

        //GlyphLayout glyphs = new GlyphLayout();
        if (null != truncate)
            glyphs.setText(font, text, 0, text.length(), color, (width - leftPadding - rightPadding), align, wrap, truncate);
        else {
            //Gdx.app.error("QQText", this + " : " + (width - leftPadding - rightPadding));
            glyphs.setText(font, text, 0, text.length(), color, (width - leftPadding - rightPadding), Align.topLeft, wrap, null);
        }

        //Gdx.app.error("QQText", this + " : " + glyphs.width);

        // wrap width
        if (wrapWidth) {
            width = glyphs.width + leftPadding + rightPadding;
            //Gdx.app.error("QQText", "wrapWidth = " + width);
            if (null != parent)
                parent.onChildSizeChanged(this);
        }

        if (wrapHeight) {
            height = glyphs.height + topPadding + bottomPadding;
            //Gdx.app.error("QQText", "wrapHeight = " + height);
            if (null != parent)
                parent.onChildSizeChanged(this);
        }

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = (width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width - rightPadding;
        } else {
            shiftX = leftPadding;
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

    //@Override
    //public void drawBackground(SpriteBatch batch, float originX, float originY) {
    //    if (null != bgNormal) {
    //        Color tmp = batch.getColor();
    //        batch.setColor(1, 1, 1, alpha);
    //        bgNormal.draw(batch, originX, originY, width, height);
    //        batch.setColor(1, 1, 1, 1);
    //        batch.flush();
    //    }
    //}


    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != color) {
            font.setColor(color);
        }
        if (null != truncate)
            font.draw(batch, text, originX + shiftX, originY + shiftY, 0, text.length(), glyphs.width, Align.topLeft, false, truncate);
        else {
            //GroupLayout.Alignment
            font.draw(batch, text, originX + shiftX, originY + shiftY, glyphs.width, /*this.width,*/ Align.left, wrap);
            //font.draw(batch, text, originX + shiftX, originY + shiftY);
        }
    }

    @Override
    public void dispose() {

    }
}
