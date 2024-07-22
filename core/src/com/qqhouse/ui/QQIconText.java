package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

/*
    icon's size = this.height x this.height ...
    icon may be null.
 */

public class QQIconText extends QQText {

    private static final int innerMargin = 2;
    public QQIconText(BitmapFont font) {
        super(font);
    }

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
    public void resetWrapWidth() {
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        width = (null != icon ? icon.getWidth() + innerMargin : 0) + glyphs.width;
    }

    @Override
    protected void rearrange() {
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // width
        if (wrapWidth) {
            width = (null != icon ? icon.getWidth()/*height*/ + innerMargin : 0) + glyphs.width;
        }

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = ((null != icon ? icon.getWidth() + innerMargin/*height + 2*/ : 0) + width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width;
        } else {
            shiftX = (null != icon ? icon.getWidth() + innerMargin/*height + 2*/ : 0);
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
        if (null != icon)
            batch.draw(icon, originX + leftPadding, originY + bottomPadding, icon.getWidth(), icon.getHeight()/*height, height*/);
        super.drawForeground(batch, originX, originY);
    }

    /*
        chain methods...
     */
    public QQIconText color(Color color) {
        super.setColor(color);
        return this;
    }

    public QQIconText align(int align) {
        super.setAlign(align);
        return this;
    }

    public QQIconText size(float width, float height) {
        super.setSize(width, height);
        return this;
    }

    public QQIconText position(float x, float y) {
        super.setPosition(x, y);
        return this;
    }

    public QQIconText attach(IsParent parent) {
        parent.addChild(this);
        return this;
    }
}
