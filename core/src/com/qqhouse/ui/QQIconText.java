package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;

public class QQIconText extends QQText {
    /*
        icon's size = this.height x this.height ...
        icon may be null.
     */

    private static final int innerMargin = 2;
    public QQIconText(BitmapFont font) {
        super(font);
    }

    public QQIconText(BitmapFont font, TextureRegion icon) {
        super(font);
        this.icon = icon;
        //font.setFixedWidthGlyphs("01234567890+-=%");
    }

    public QQIconText(BitmapFont font, NinePatch bg, TextureRegion icon) {
        super(font, bg);
        this.icon = icon;
        //this.align = Align.right;
    }

    private TextureRegion icon;

    public void setIcon(TextureRegion icon) {
        this.icon = icon;
    }

    @Override
    public void resetWrapWidth() {
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        width = (null != icon ? icon.getRegionWidth() + innerMargin : 0) + glyphs.width;
    }

    @Override
    protected void rearrange() {
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // width
        if (wrapWidth) {
            width = (null != icon ? icon.getRegionWidth() + innerMargin : 0) + glyphs.width;
        }

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = ((null != icon ? icon.getRegionWidth() + innerMargin/*height + 2*/ : 0) + width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width - rightPadding;
        } else {
            shiftX = (null != icon ? icon.getRegionWidth() + innerMargin/*height + 2*/ : 0);
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
            batch.draw(icon, originX + leftPadding, originY + bottomPadding, icon.getRegionWidth(), icon.getRegionHeight()/*height, height*/);
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
