package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.hero.ColosseumHero;
import com.qqhouse.dungeon18plus.struct.hero.DungeonHero;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class HeroView extends AssetGroupButton {

    /*
        fix data.
        1. hero class icon.
        2. life / attack / defense / speed icon.
        3. soul / key / coin / star icon.
        4. level / exp
     */
    private final QQImage blockee;
    private final QQIconText life, attack, defense, speed;
    private ItemView soul, key, coin, star;
    private QQText level;
    private QQProgress exp;

    public HeroView(Assets assets, DungeonHero hero) {
        super(assets);

        blockee = new QQImage();
        blockee.setSize(Game.Size.BLOCKEE_SIZE, Game.Size.BLOCKEE_SIZE);
        blockee.setPosition(8, 8);
        blockee.setImage(assets.getBlockee(hero.heroClass.key));
        addChild(blockee);

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/life"));
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/attack"));
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/defense"));
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/speed"));
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        if (Feat.DARK_PRESENCE.in(hero.feats)) {
            soul = new ItemView(assets.getIcon("item/soul"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
            soul.setSize(32, 32);
            soul.setColor(Game.Colour.COUNT);
            addChild(soul);
        } else {
            key = new ItemView(assets.getIcon("item/key"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
            key.setSize(32, 32);
            key.setColor(Game.Colour.COUNT);
            addChild(key);

            coin = new ItemView(assets.getIcon("item/copper_coin"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
            coin.setSize(32, 32);
            coin.setColor(Game.Colour.COUNT);
            addChild(coin);

            star = new ItemView(assets.getIcon("item/star"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
            star.setSize(32, 32);
            star.setColor(Game.Colour.COUNT);
            addChild(star);
        }

        if (Feat.EXPERIENCE.in(hero.feats)) {
            // level
            level = new QQText(assets.getFont(Game.Font.LEVEL16), assets.getNinePatchBG("level"), 0.75f);
            level.setColor(Game.Colour.RANK);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
            level.setPadding(4);
            level.setPosition(4, 40);
            level.setAlign(Align.center);
            addChild(level);

            // progress bar for exp...
            exp = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("cyan"));
            exp.setSize(50, 8);
            exp.setPosition(7, 4);
            addChild(exp);
        }

        setBackground(assets.getBackgroundSet(hero.heroClass.alignment.key));
    }

    public HeroView(Assets assets, ColosseumHero hero) {
        super(assets);

        blockee = new QQImage();
        blockee.setSize(Game.Size.BLOCKEE_SIZE, Game.Size.BLOCKEE_SIZE);
        blockee.setPosition(8, 8);
        blockee.setImage(assets.getBlockee(hero.heroClass.key));
        addChild(blockee);

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/life"));
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/attack"));
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/defense"));
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon16/speed"));
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        coin = new ItemView(assets.getIcon("item/copper_coin"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
        coin.setSize(32, 32);
        coin.setColor(Game.Colour.COUNT);
        addChild(coin);

        star = new ItemView(assets.getIcon("item/star"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
        star.setSize(32, 32);
        star.setColor(Game.Colour.COUNT);
        addChild(star);

        soul = new ItemView(assets.getIcon("item/soul"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
        soul.setSize(32, 32);
        soul.setPosition(64, 26);
        soul.setColor(Game.Colour.COUNT);
        addChild(soul);

        setBackground(assets.getBackgroundSet(hero.heroClass.alignment.key));
    }

    public void update(DungeonHero hero) {

        life.setText(hero.displayLife());
        attack.setText(hero.displayAttack());
        defense.setText(hero.displayDefense());
        speed.setText(hero.displaySpeed());

        if (Feat.DARK_PRESENCE.in(hero.feats)) {
            soul.setText(Integer.toString(hero.soul));
        } else {
            key.setText(Integer.toString(hero.key));
            coin.setText(Integer.toString(hero.coin));
            star.setText(Integer.toString(hero.star));
        }

        if (Feat.EXPERIENCE.in(hero.feats)) {
            level.setText(Integer.toString(hero.level));
            exp.setPercent(hero.exp * 100 / hero.maxExp);
        }

    }

    public void update(ColosseumHero hero) {

        life.setText(hero.displayLife());
        attack.setText(hero.displayAttack());
        defense.setText(hero.displayDefense());
        speed.setText(hero.displaySpeed());

        soul.setText(Integer.toString(hero.round));
        coin.setText(Integer.toString(hero.coin));
        star.setText(Integer.toString(hero.star));

    }

    /*
        IsParent...
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        // reset life, attack, defense, speed size.
        float fixWidth = (width - leftPadding - rightPadding - 48 - 8) / 4;
        life.setSize(fixWidth, 16);
        attack.setSize(fixWidth, 16);
        defense.setSize(fixWidth, 16);
        speed.setSize(fixWidth, 16);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        // set life, attack, defense, speed position.
        life.setPosition(64, 6);
        attack.setPosition(life.getX() + life.getWidth(), 6);
        defense.setPosition(attack.getX() + attack.getWidth(), 6);
        speed.setPosition(defense.getX() + defense.getWidth(), 6);

        if (null != soul && 0 == soul.getX())
            soul.setPosition(width - rightPadding - 32 - 8, 26);
        if (null != star)
            star.setPosition(width - rightPadding - 32 - 8, 26);
        if (null != coin && null != star)
            coin.setPosition(star.getX() - 32 - 20, 26);
        if (null != key && coin != null)
            key.setPosition(coin.getX() - 32 - 20, 26);
    }
}