package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class HeroProfileView extends QQView {

    private Texture icon;
    private BitmapFont fntName, fntDesc;
    private String name, desc;


    public HeroProfileView(QQScreen master, Texture icon, BitmapFont fntName, String name, BitmapFont fntDesc, String desc) {
        super(master);
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
