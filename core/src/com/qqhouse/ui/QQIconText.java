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

    private final int innerMargin = 2;
    private TextureRegion icon;
    private int iconSize; // w = h (square)
    private float iconShiftY;

    public QQIconText(BitmapFont font) {
        super(font);
    }

    public QQIconText(BitmapFont font, TextureRegion icon) {
        super(font);
        this.icon = icon;
        iconSize = icon.getRegionWidth();
    }

    public QQIconText(BitmapFont font, NinePatch bg, TextureRegion icon) {
        super(font, bg);
        this.icon = icon;
        if (null != icon)
            iconSize = icon.getRegionWidth();
    }

    public void setIcon(TextureRegion icon) {
        this.icon = icon;
        if (null != icon)
            iconSize = icon.getRegionWidth();
    }

    // icon size version
    public QQIconText(BitmapFont font, TextureRegion icon, int iconSize) {
        super(font);
        this.icon = icon;
        this.iconSize = iconSize;
    }

    public QQIconText(BitmapFont font, NinePatch bg, TextureRegion icon, int iconSize) {
        super(font, bg);
        this.icon = icon;
        this.iconSize = iconSize;
    }

    public void setIcon(TextureRegion icon, int iconSize) {
        this.icon = icon;
        this.iconSize = iconSize;
    }

    @Override
    public void resetWrapWidth() {
        glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        width = (null != icon ? iconSize + innerMargin : 0) + glyphs.width;
    }

    @Override
    protected void rearrange() {
        glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        // width
        if (wrapWidth) {
            width = (null != icon ? iconSize + innerMargin : 0) + glyphs.width;
        }

        // shift x
        if (Align.isCenterHorizontal(align)) {
            shiftX = ((null != icon ? (iconSize + innerMargin) : 0) + width - glyphs.width) / 2;
        } else if (Align.isRight(align)) {
            shiftX = width - glyphs.width - rightPadding;
        } else {
            shiftX = (null != icon ? (iconSize + innerMargin) : 0);
        }

        // shift y
        if (Align.isCenterVertical(align)) {
            shiftY = (height + glyphs.height) / 2;
        } else if (Align.isBottom(align)) {
            shiftY = glyphs.height;
        } else {
            shiftY = height;
        }

        iconShiftY = bottomPadding + (height - iconSize) / 2;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != icon)
            batch.draw(icon, originX + leftPadding, originY + iconShiftY, iconSize, iconSize);
        super.drawForeground(batch, originX, originY);
    }
}
