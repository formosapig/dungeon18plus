package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class PreviewView extends QQButton {

    private Texture icon;
    private BitmapFont fntName, fntDesc;
    private String name, desc;

    /*
        Icon : blockee icon
        Level : blockee level (if needs), at upper left of icon.
        Name : horizontal central, next to icon.
        Score / round : at upper right, may overlap name field.
        help : description of hero class, multi line, max lines = 3...
     */

    private float iconShiftX, iconShiftY, nameShiftX, nameShiftY, descShiftX, descShiftY, descW;

    /*
        font name :
            size = 26
            CapHeight = 20
            LineHeight = 38

        font desc :
            size = 16
            CapHeight = 12
            LineHeight = 23
     */


    public PreviewView(QQScreen master, String btnKey, Texture icon, BitmapFont fntName, String name, BitmapFont fntDesc, String desc) {
        super(master, btnKey);
        this.icon = icon;
        this.fntName = fntName;
        this.name = name;
        this.fntDesc = fntDesc;
        this.desc = desc;
    }

    @Override
    protected void calculateContentHeight() {
        // if width not set, can not calculate height of font with multi line.
        if (QQView.FILL_PARENT == width)
            return;

        float height = topPadding + bottomPadding;

        // font : Name (26, 20, 38)
        GlyphLayout glName = new GlyphLayout();
        glName.setText(fntName, name);
        height += 20;//glyphLayout.height;
        //Gdx.app.error("TEST", "name height = " + glyphLayout.height);
        // widget margin
        height += 4;



        // font : Desc (16, 12, 23)
        GlyphLayout glDesc = new GlyphLayout();
        descW = (int)(this.width - leftPadding - rightPadding - 8 - 48);
        glDesc.setText(fntDesc, desc, 0, 50, Color.WHITE, descW, Align.topLeft, true, null);
        height += glDesc.height - 12 + 16;
        Gdx.app.error("TEST", "desc height = " + glDesc.height);
        Gdx.app.error("TEST", "desc glyph = " + glDesc.glyphCount);

        this.height = height;
        Gdx.app.error("TEST", "size = " + this.width + "," + this.height);

        // icon position
        iconShiftX = leftPadding;
        iconShiftY = this.height - topPadding - 48;
        Gdx.app.error("TEST", "icon shift = " + iconShiftX + "," + iconShiftY);

        nameShiftX = iconShiftX + 48 + 8 + (descW - glName.width) / 2;
        nameShiftY = this.height - topPadding;
        Gdx.app.error("TEST", "name shift = " + nameShiftX + "," + nameShiftY);

        descShiftX = iconShiftX + 48 + 8;
        descShiftY = this.height - topPadding - 20 - 4;
        Gdx.app.error("TEST", "desc shift = " + descShiftX + "," + descShiftY);


    }


    @Override
    public void drawForeground(SpriteBatch batch) {

        // draw icon
        batch.draw(icon, (int)(x + iconShiftX), (int)(y + iconShiftY), 48, 48);

        // draw name
        fntName.draw(batch, name, x + nameShiftX, y + nameShiftY);

        // draw desc
        fntDesc.draw(batch, desc, x + descShiftX, y + descShiftY, 0, 50, descW, Align.topLeft, true, null);

    }



    @Override
    public void dispose() {

    }
}
