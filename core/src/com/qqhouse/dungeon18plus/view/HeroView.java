package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class HeroView extends QQButton implements QQView.IsParentView {
    public HeroView(String buttonKey) {
        super(buttonKey);
        childrenView = new ArrayList<>();
    }

    /*
        fix data.
        1. hero class icon.
        2. life / attack / defense / speed icon.
        3. key / coin / star icon.


     */
    private Texture hero, life, attack, defense, speed, key, coin, star;
    private BitmapFont fntDigital, fntSmallDigital;
    private QQText digital;
    private ArrayList<QQView> childrenView;

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
        // digital of Star quantity
        digital = new QQText(fntSmallDigital,
                new NinePatch(new Texture(Gdx.files.internal("background/white.png")), 0, 0, 0, 0));
        digital.setPosition(312, 26); // related to parent.
        digital.setSize(32, 16);
        digital.setAlign(Align.right);
        digital.setText("999");
        childrenView.add(digital);

        // life view ?!
        QQIconText lifeView = new QQIconText(fntDigital, life);
        lifeView.setAlign(Align.right);
        lifeView.setSize(62, 16);
        lifeView.setPosition(60, 6);
        lifeView.setColorText(Color.YELLOW, "999");
        childrenView.add(lifeView);

        // attack view
        QQIconText attView = new QQIconText(fntDigital, attack);
        attView.setAlign(Align.right);
        attView.setSize(62, 16);
        attView.setPosition(132, 6);
        attView.setColorText(Color.RED, "15");
        childrenView.add(attView);

        // defense view
        QQIconText defView = new QQIconText(fntDigital, defense);
        defView.setAlign(Align.right);
        defView.setSize(62, 16);
        defView.setPosition(204, 6);
        defView.setColorText(Color.BLUE, "15");
        childrenView.add(defView);

        // speed view
        QQIconText spdView = new QQIconText(fntDigital, speed);
        spdView.setAlign(Align.right);
        spdView.setSize(62, 16);
        spdView.setPosition(276, 6);
        spdView.setColorText(Color.GREEN, "15");
        childrenView.add(spdView);


    }


    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // 1. draw hero icon
        batch.draw(hero, originX + 8, originY + 8);

        // life / attack / defense / speed
        //batch.draw(life, originX + 60, originY + 6);     // life
        //batch.draw(attack, originX + 132, originY + 6);  // attack
        //batch.draw(defense, originX + 204, originY + 6); // defense
        //batch.draw(speed, originX + 276, originY + 6); // speed

        // draw life ?
        //fntDigital.setUseIntegerPositions(true);
        //fntDigital.setColor(Color.YELLOW);
        //fntDigital.draw(batch, "  500", originX + 78, originY + 20);
        //fntDigital.setColor(Color.RED);
        //fntDigital.draw(batch, "   15", originX + 150, originY + 20);
        //fntDigital.setColor(Color.BLUE);
        //fntDigital.draw(batch, "    5", originX + 222, originY + 20);
        //fntDigital.setColor(Color.GREEN);
        //fntDigital.draw(batch, "   40", originX + 294, originY + 20);

        // key / coin / star
        batch.draw(key, originX + 212, originY + 26);
        batch.draw(coin, originX + 262, originY + 26);
        batch.draw(star, originX + 312, originY + 26);

        fntSmallDigital.setColor(Color.WHITE);
        fntSmallDigital.draw(batch, "999", originX + 212, y + 37);
        fntSmallDigital.draw(batch, " 12", originX + 262, y + 37);
        //fntSmallDigital.draw(batch, "  0", originX + 312, y + 37);
        //digital.setText("0");
        //digital.draw(batch, originX, originY);

    }




    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            view.draw(batch, originX, originY);
        }
    }
}
