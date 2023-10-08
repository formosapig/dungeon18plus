package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.hero.DungeonHero;
import com.qqhouse.dungeon18plus.struct.hero.Hero;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class HeroView extends QQButton implements QQView.IsParentView {
    public HeroView(String buttonKey, Assets assets) {
        super(buttonKey);
        this.assets = assets;
        childrenView = new ArrayList<>();
    }

    /*
        fix data.
        1. hero class icon.
        2. life / attack / defense / speed icon.
        3. key / coin / star icon.


     */
    private final Assets assets;
    private Texture hero, life, attack, defense, speed, key, coin, star;
    private BitmapFont fntDigital, fntSmallDigital;
    private QQText digital;
    private ArrayList<QQView> childrenView;
    private QQIconText lifeText, attackText, defenseText, speedText;
    private ItemView keyItem, coinItem, starItem;
    private QQText level;
    private QQProgress exp;

    public void reset(DungeonHero hero) {
        this.hero = assets.getBlockee(hero.heroClass.key);

        /*
              experience hero : key, coin, star
              fairy           : key, coin, star, no exp
              skeleton king   : soul, no exp
         */
        if (Feat.EXPERIENCE.in(hero.feats)) {
            // level
            level = new QQText(assets.getFont("whitrabt", 16), new NinePatch(assets.getBackground("level"), 4, 4, 4, 4), 0.75f);
            level.setColor(Game.color.RANK);
            level.setSize(24, 16); // TODO 1007 希望可以浮動的調整寬度...
            level.setPosition(4, 44);
            level.setAlign(Align.center);
            level.setText("22");
            childrenView.add(level);

            // progress bar for exp...
            exp = new QQProgress(
                    new NinePatch(assets.getBackground("black")),
                    new NinePatch(assets.getBackground("white")));
            exp.setSize(50, 8);
            exp.setPosition(7, 4);
            childrenView.add(exp);
        }





        // life view ?!
        //lifeText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("life"));
        lifeText = new QQIconText(assets.getFont("whitrabt", 18), assets.getIcon16("life"));
        lifeText.setAlign(Align.center);
        lifeText.setSize(72, 16);
        lifeText.setPosition(64, 6);
        lifeText.setColor(Game.color.LIFE);
        childrenView.add(lifeText);

        // attack view
        //attackText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("attack"));
        attackText = new QQIconText(assets.getFont("whitrabt", 18), assets.getIcon16("attack"));
        attackText.setAlign(Align.center);
        attackText.setSize(72, 16);
        attackText.setPosition(136, 6);
        attackText.setColor(Game.color.ATTACK);
        childrenView.add(attackText);

        // defense view
        //defenseText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("defense"));
        defenseText = new QQIconText(assets.getFont("whitrabt", 18), assets.getIcon16("defense"));
        defenseText.setAlign(Align.center);
        defenseText.setSize(72, 16);
        defenseText.setPosition(208, 6);
        defenseText.setColor(Game.color.DEFENSE);
        childrenView.add(defenseText);

        // speed view
        //speedText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("speed"));
        speedText = new QQIconText(assets.getFont("whitrabt", 18), assets.getIcon16("speed"));
        speedText.setAlign(Align.center);
        speedText.setSize(72, 16);
        speedText.setPosition(280, 6);
        speedText.setColor(Game.color.SPEED);
        childrenView.add(speedText);

    }

    public void update(DungeonHero hero) {

        if (null != level) {
            level.setText(Integer.toString(hero.level));
        }
        if (null != exp) {
            exp.setPercent(hero.exp * 100/ hero.maxExp);
        }

        lifeText.setText(Integer.toString(hero.life));
        attackText.setText(Integer.toString(hero.attack));
        //attackText.setText("199x2");
        defenseText.setText(Integer.toString(hero.defense));
        speedText.setText(Integer.toString(hero.speed));
        keyItem.setText(Integer.toString(hero.key));
        coinItem.setText(Integer.toString(hero.coin));
        starItem.setText(Integer.toString(hero.star));
        // level
        // exp...
    }

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
        //childrenView.add(digital);

        // life view ?!
        //lifeText = new QQIconText(fntDigital, life);
        //lifeText.setAlign(Align.right);
        //lifeText.setSize(62, 16);
        //lifeText.setPosition(60, 6);
        //lifeText.setColorText(Color.YELLOW, "");
        //childrenView.add(lifeText);

        // attack view
        //attackText = new QQIconText(fntDigital, attack);
        //attackText.setAlign(Align.right);
        //attackText.setSize(62, 16);
        //attackText.setPosition(132, 6);
        //attackText.setColorText(Color.RED, "");
        //childrenView.add(attackText);

        // defense view
        //defenseText = new QQIconText(fntDigital, defense);
        //defenseText.setAlign(Align.right);
        //defenseText.setSize(62, 16);
        //defenseText.setPosition(204, 6);
        //defenseText.setColorText(Color.BLUE, "");
        //childrenView.add(defenseText);

        // speed view
        //speedText = new QQIconText(fntDigital, speed);
        //speedText.setAlign(Align.right);
        //speedText.setSize(62, 16);
        //speedText.setPosition(276, 6);
        //speedText.setColorText(Color.GREEN, "");
        //childrenView.add(speedText);

        // key item
        keyItem = new ItemView(key, fntSmallDigital);
        keyItem.setColor(Color.WHITE);
        keyItem.setSize(32, 32);
        keyItem.setPosition(212, 26);
        childrenView.add(keyItem);

        // coin
        coinItem = new ItemView(coin, fntSmallDigital);
        coinItem.setColor(Color.WHITE);
        coinItem.setSize(32, 32);
        coinItem.setPosition(262, 26);
        childrenView.add(coinItem);

        // star
        starItem = new ItemView(star, fntSmallDigital);
        starItem.setColor(Color.WHITE);
        starItem.setSize(32, 32);
        starItem.setPosition(312, 26);
        childrenView.add(starItem);

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

        //AssetDescriptor

        //AssetManager

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
        //batch.draw(key, originX + 212, originY + 26);
        //batch.draw(coin, originX + 262, originY + 26);
        //batch.draw(star, originX + 312, originY + 26);

        //fntSmallDigital.setColor(Color.WHITE);
        //fntSmallDigital.draw(batch, "999", originX + 212, y + 37);
        //fntSmallDigital.draw(batch, " 12", originX + 262, y + 37);
        //fntSmallDigital.draw(batch, "  0", originX + 312, y + 37);
        //digital.setText("0");
        //digital.draw(batch, originX, originY);

    }

    public void setData(DungeonHero hero) {
        lifeText.setText(Integer.toString(hero.life));
        attackText.setText(Integer.toString(hero.attack));
        //attackText.setText("199x2");
        defenseText.setText(Integer.toString(hero.defense));
        speedText.setText(Integer.toString(hero.speed));
        keyItem.setText(Integer.toString(hero.key));
        coinItem.setText(Integer.toString(hero.coin));
        starItem.setText(Integer.toString(hero.star));
        // level
        // exp...
    }




    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            view.draw(batch, originX, originY);
        }
    }
}
