package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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



    public PreviewView(QQScreen master, String btnKey, Texture icon, BitmapFont fntName, String name, BitmapFont fntDesc, String desc) {
        super(master, btnKey);
        this.icon = icon;
        this.fntName = fntName;
        this.name = name;
        this.fntDesc = fntDesc;
        this.desc = desc;
    }

    @Override
    public void drawForeground(SpriteBatch batch) {

        // draw icon
        batch.draw(icon, x + 8, y + 8, 48, 48);

        // draw name


    }



    @Override
    public void dispose() {

    }
}
