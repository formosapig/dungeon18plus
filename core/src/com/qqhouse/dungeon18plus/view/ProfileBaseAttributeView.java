package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileBaseAttributeView extends AssetGroup {

    private final QQIconText life, attack, defense, speed, key, coin, star;
    private final int innerMargin;

    public ProfileBaseAttributeView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("life"));
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("attack"));
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("defense"));
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("speed"));
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        key = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getItem("key"));
        key.setColor(Game.Colour.RARE);
        addChild(key);

        coin = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getItem("copper_coin"));
        coin.setColor(Game.Colour.RARE);
        addChild(coin);

        star = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getItem("star"));
        star.setColor(Game.Colour.RARE);
        addChild(star);
    }

    public void update(HeroClassRecord record) {
        // todo 20190415 暫時先改好了, 之後再看看.
        SoulEffect bonus = new SoulEffect();

        for (SoulCount sc : record.souls) {
            bonus.plus(sc.soul.getEffect(sc.count));
        }

        setTextData(life, record.heroClass.startLife, bonus.life);
        setTextData(attack, record.heroClass.startAttack, bonus.attack);
        setTextData(defense, record.heroClass.startDefense, bonus.defense);
        setTextData(speed, record.heroClass.startSpeed, bonus.speed);
        setTextData(key, record.heroClass.startKey, bonus.key);
        setTextData(coin, record.heroClass.startCoin, bonus.coin);
        setTextData(star, record.heroClass.startStar, bonus.star);

    }

    private void setTextData(QQText tv, int value, int bonus) {
        int finalValue = value + bonus;
        if (0 > finalValue)
            finalValue = 0;
        String result = Integer.toString(finalValue);
        if (0 != bonus)
            result += "(" + (bonus > 0 ? "+" + bonus : Integer.toString(bonus)) + ")";
        tv.setText(result);
    }


    @Override
    public void resetWrapHeight() {
        height = topPadding + 32 * 4 + innerMargin * 3 + bottomPadding;
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        float fixWidth = (width - leftPadding - rightPadding) / 2;
        life.setSize(fixWidth, 32);
        attack.setSize(fixWidth, 32);
        defense.setSize(fixWidth, 32);
        speed.setSize(fixWidth, 32);
        key.setSize(fixWidth, 32);
        coin.setSize(fixWidth, 32);
        star.setSize(fixWidth, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        life.setPosition(leftPadding, bottomPadding + 32 * 3 + innerMargin * 3);
        attack.setPosition(leftPadding, bottomPadding + 32 * 2 + innerMargin * 2);
        defense.setPosition(leftPadding, bottomPadding + 32 + innerMargin);
        speed.setPosition(leftPadding, bottomPadding);

        key.setPosition(width / 2, bottomPadding + 32 * 3 + innerMargin * 3);
        coin.setPosition(width / 2, bottomPadding + 32 * 2 + innerMargin * 2);
        star.setPosition(width / 2, bottomPadding + 32 + innerMargin);

    }

}
