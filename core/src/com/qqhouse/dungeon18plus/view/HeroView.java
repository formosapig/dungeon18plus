package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQButton;

public class HeroView extends QQButton {
    public HeroView(String buttonKey) {
        super(buttonKey);
    }

    /*
        fix data.
        1. hero class icon.
        2. life / attack / defense / speed icon.
        3. key / coin / star icon.


     */
    private Texture hero, life, attack, defense, speed, key, coin, star;
    private BitmapFont fntDigital, fntSmallDigital;

    public void preset(Texture hero, Texture life, Texture attack, Texture defense, Texture speed, Texture key, Texture coin, Texture star) {
        this.hero = hero;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.key = key;
        this.coin = coin;
        this.star = star;
    }

    public void setFont(BitmapFont font, BitmapFont font2) {
        fntDigital = font;
        fntSmallDigital = font2;
    }


    @Override
    public void drawForeground(SpriteBatch batch) {
        // 1. draw hero icon
        batch.draw(hero, x + 8, y + 8);

        // life / attack / defense / speed
        batch.draw(life, x + 60, y + 6);     // life
        batch.draw(attack, x + 132, y + 6);  // attack
        batch.draw(defense, x + 204, y + 6); // defense
        batch.draw(speed, x + 276, y + 6); // speed

        // draw life ?
        //fntDigital.setUseIntegerPositions(true);
        fntDigital.setColor(Color.YELLOW);
        fntDigital.draw(batch, "  500", x + 78, y + 20);
        fntDigital.setColor(Color.RED);
        fntDigital.draw(batch, "   15", x + 150, y + 20);
        fntDigital.setColor(Color.BLUE);
        fntDigital.draw(batch, "    5", x + 222, y + 20);
        fntDigital.setColor(Color.GREEN);
        fntDigital.draw(batch, "   40", x + 294, y + 20);

        // key / coin / star
        batch.draw(key, x + 212, y + 26);
        batch.draw(coin, x + 262, y + 26);
        batch.draw(star, x + 312, y + 26);

        fntSmallDigital.setColor(Color.WHITE);
        fntSmallDigital.draw(batch, "999", x + 212, y + 37);
        fntSmallDigital.draw(batch, " 12", x + 262, y + 37);
        fntSmallDigital.draw(batch, "  0", x + 312, y + 37);

    }

}
