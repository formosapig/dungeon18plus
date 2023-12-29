package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.hero.ColosseumHero;
import com.qqhouse.dungeon18plus.struct.hero.DungeonHero;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class HeroView extends QQButton implements QQView.IsParent {

    private final Assets assets;

    public HeroView(Assets assets) {
        this.assets = assets;
    }

    /*
        fix data.
        1. hero class icon.
        2. life / attack / defense / speed icon.
        3. key / coin / star icon.
     */
    private Texture hero;
    private QQIconText lifeText, attackText, defenseText, speedText;
    private ItemView keyItem, coinItem, starItem;
    private QQText level;
    private QQProgress exp;

    public void reset(DungeonHero hero) {
        this.hero = assets.getTexture("blockee", hero.heroClass.key);

        /*
              experience hero : key, coin, star
              fairy           : key, coin, star, no exp
              skeleton king   : soul, no exp
         */
        if (Feat.EXPERIENCE.in(hero.feats)) {
            // level
            level = new QQText(assets.getFont(Game.Font.LEVEL16), new NinePatch(assets.getTexture("background", "level"), 4, 4, 4, 4), 0.75f);
            level.setColor(Game.Colour.RANK);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT); // TODO 1007 希望可以浮動的調整寬度...
            level.setPadding(4);//2, 2, 2, 2);
            level.setPosition(4, 40);
            level.setAlign(Align.center);
            //level.setText("22");
            childrenView.add(level);

            // progress bar for exp...
            exp = new QQProgress(
                    new NinePatch(assets.getTexture("background","black"), 4, 4, 4, 4),
                    new NinePatch(assets.getTexture("background","white"), 4, 4, 4, 4));
            exp.setSize(50, 8);
            exp.setPosition(7, 4);
            childrenView.add(exp);
        }

        // life view ?!
        //lifeText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("life"))
        lifeText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "life"))
                .size(72, 16).position(64, 6).color(Game.Colour.LIFE).attach(this);

        // attack view
        //attackText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("attack"))
        attackText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "attack"))
                .size(72, 16).position(136, 6).color(Game.Colour.ATTACK).attach(this);

        // defense view
        //defenseText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("defense"))
        defenseText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "defense"))
                .size(72, 16).position(208, 6).color(Game.Colour.DEFENSE).attach(this);

        // speed view
        //speedText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("speed"))
        speedText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "speed"))
                .size(72, 16).position(280, 6).color(Game.Colour.SPEED).attach(this);

        // Soul ...

        // key item
        //keyItem = new ItemView(assets.getTexture("item", "key"), assets.getFont(Game.Font.DIGITAL16))
        //        .color(Game.color.COUNT).size(32, 32).position(212, 26).attach(this);
        keyItem = new ItemView(assets.getTexture("item", "key"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(212, 26).attach(this);

        // coin
        coinItem = new ItemView(assets.getTexture("item", "copper_coin"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(262, 26).attach(this);

        // star
        starItem = new ItemView(assets.getTexture("item", "star"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(312, 26).attach(this);

        // background
        setBackground(assets.getBackgroundSet(hero.heroClass.alignment.key));
    }

    public void update(DungeonHero hero) {

        if (null != level) {
            level.setText(Integer.toString(hero.level));
        }
        if (null != exp) {
            exp.setPercent(hero.exp * 100/ hero.maxExp);
        }

        lifeText.setText(hero.displayLife());
        attackText.setText(hero.displayAttack());
        //attackText.setText("199x2");
        defenseText.setText(hero.displayDefense());
        speedText.setText(hero.displaySpeed());
        keyItem.setText(Integer.toString(hero.key));
        coinItem.setText(Integer.toString(hero.coin));
        starItem.setText(Integer.toString(hero.star));
        // level
        // exp...
    }

    public void reset(ColosseumHero hero) {
        this.hero = assets.getTexture("blockee", hero.heroClass.key);

        /*
              experience hero : key, coin, star
              fairy           : key, coin, star, no exp
              skeleton king   : soul, no exp
         */
        if (Feat.EXPERIENCE.in(hero.feats)) {
            // level
            level = new QQText(assets.getFont(Game.Font.LEVEL16), new NinePatch(assets.getTexture("background", "level"), 4, 4, 4, 4), 0.75f);
            level.setColor(Game.Colour.RANK);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT); // TODO 1007 希望可以浮動的調整寬度...
            level.setPadding(4);//2, 2, 2, 2);
            level.setPosition(4, 40);
            level.setAlign(Align.center);
            //level.setText("22");
            childrenView.add(level);

            // progress bar for exp...
            exp = new QQProgress(
                    new NinePatch(assets.getTexture("background","black"), 4, 4, 4, 4),
                    new NinePatch(assets.getTexture("background","white"), 4, 4, 4, 4));
            exp.setSize(50, 8);
            exp.setPosition(7, 4);
            childrenView.add(exp);
        }

        // life view ?!
        //lifeText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("life"))
        lifeText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "life"))
                .size(72, 16).position(64, 6).color(Game.Colour.LIFE).attach(this);

        // attack view
        //attackText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("attack"))
        attackText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "attack"))
                .size(72, 16).position(136, 6).color(Game.Colour.ATTACK).attach(this);

        // defense view
        //defenseText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("defense"))
        defenseText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "defense"))
                .size(72, 16).position(208, 6).color(Game.Colour.DEFENSE).attach(this);

        // speed view
        //speedText = new QQIconText(assets.getFont("whitrabt", 18), new NinePatch(assets.getBackground("refined")), assets.getIcon16("speed"))
        speedText = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getTexture("icon16", "speed"))
                .size(72, 16).position(280, 6).color(Game.Colour.SPEED).attach(this);

        // Soul ...

        // key item
        //keyItem = new ItemView(assets.getTexture("item", "key"), assets.getFont(Game.Font.DIGITAL16))
        //        .color(Game.color.COUNT).size(32, 32).position(212, 26).attach(this);
        keyItem = new ItemView(assets.getTexture("item", "key"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(212, 26).attach(this);

        // coin
        coinItem = new ItemView(assets.getTexture("item", "copper_coin"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(262, 26).attach(this);

        // star
        starItem = new ItemView(assets.getTexture("item", "star"), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"))
                .color(Game.Colour.COUNT).size(32, 32).position(312, 26).attach(this);

        // background
        setBackground(assets.getBackgroundSet(hero.heroClass.alignment.key));
    }

    public void update(ColosseumHero hero) {

        lifeText.setText(hero.displayLife());
        attackText.setText(hero.displayAttack());
        //attackText.setText("199x2");
        defenseText.setText(hero.displayDefense());
        speedText.setText(hero.displaySpeed());
        keyItem.setText(Integer.toString(hero.key));
        coinItem.setText(Integer.toString(hero.coin));
        starItem.setText(Integer.toString(hero.star));
        // level
        // exp...
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // 1. draw hero icon
        batch.draw(hero, originX + 8, originY + 8);
    }

    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {}

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {}

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView child : childrenView)
            child.draw(batch, originX, originY);
    }
}
