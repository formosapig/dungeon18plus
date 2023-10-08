package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.ui.QQView;

public class ItemView extends QQView {
    /*
        item = 32 x 32
        small white rabbit font (16) to display quantity of item.

        total size = 32 x 32 (+2) , display quantity's play needs 2 pixel...
     */

    private Texture icon;
    private BitmapFont font; // display quantity
    private Color color;
    private String text;
    private boolean countable; // if not countable , do not display quantity
    private float shiftX, shiftY;

    public ItemView(Texture icon, BitmapFont font) {
        this.icon = icon;
        this.font = font;
        touchable = false; // do not touch item ....
    }

    public ItemView(BitmapFont font) {
        this.font = font;
        touchable = false;
    }


    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public void setCountable(boolean countable) {
        this.countable = countable;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout glyphs = new GlyphLayout();
        glyphs.setText(font, text);

        shiftX = 32 - glyphs.width;
        shiftY = glyphs.height;
    }

    @Override
    protected void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw item
        batch.draw(icon, originX, originY, 32, 32);

        // draw quantity
        if (null != color) {
            font.setColor(color);
        }
        font.draw(batch, text, originX + shiftX, originY + shiftY);
    }



    @Override
    public void dispose() {}
}
