package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileBaseAttributeView extends AssetGroup {

    private final QQText title;
    private final QQIconText life, attack, defense, speed, key, coin, star;
    private final QQView bg;
    private final int innerMargin;

    public ProfileBaseAttributeView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatch("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        title.setText(assets.geti18n("profile_base_attribute"));
        addChild(title);

        bg = new QQView();
        addChild(bg);

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/life"));
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/attack"));
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/defense"));
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/speed"));
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        key = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("item/key"));
        key.setColor(Game.Colour.RARE);
        addChild(key);

        coin = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("item/copper_coin"));
        coin.setColor(Game.Colour.RARE);
        addChild(coin);

        star = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("item/star"));
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

    public void update(GiantRecord record) {
        // key -> exp
        key.setIcon(assets.getIcon("icon/rank"));
        key.setColor(Game.Colour.RANK);
        key.setText(Integer.toString(record.exp));

        // coin -> fast win
        coin.setIcon(assets.getIcon("icon/time"));
        coin.setColor(Game.Colour.SPEED);
        coin.setText(0 < record.fastWin ? Integer.toString(record.fastWin) : "-");

        // star -> kill count
        star.setIcon(assets.getIcon("item/soul"));
        star.setColor(Game.Colour.SOUL);
        star.setText(0 < record.killCount ? Integer.toString(record.killCount) : "-");

        /*
         * life, attack, defense, speed
         */
        life.setText(record.displayLife() ? Integer.toString(record.race.attr.life) : "?");
        attack.setText(record.displayAttack() ? Integer.toString(record.race.attr.attack) : "?");
        defense.setText(record.displayDefense() ? Integer.toString(record.race.attr.defense) : "?");
        speed.setText(record.displaySpeed() ? Integer.toString(record.race.attr.speed) : "?");
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
    public void setBackground(NinePatch bg) {
        this.bg.setBackground(bg);
    }

    @Override
    public void resetWrapHeight() {
        height = 32 + 4 + 8 + 32 * 4 + innerMargin * 3 + 8;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        title.setSize(width, 32);
        float fixWidth = (width - leftPadding - rightPadding) / 2;
        life.setSize(fixWidth, 32);
        attack.setSize(fixWidth, 32);
        defense.setSize(fixWidth, 32);
        speed.setSize(fixWidth, 32);
        key.setSize(fixWidth, 32);
        coin.setSize(fixWidth, 32);
        star.setSize(fixWidth, 32);
        bg.setSize(width, 8 + 32 * 4 + innerMargin * 3 + 8);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        title.setPosition(0, bg.getHeight() + Game.Size.WIDGET_MARGIN);

        life.setPosition(8, 8 + 32 * 3 + innerMargin * 3);
        attack.setPosition(8, 8 + 32 * 2 + innerMargin * 2);
        defense.setPosition(8, 8 + 32 + innerMargin);
        speed.setPosition(8, 8);

        key.setPosition(width / 2 + 8, 8 + 32 * 3 + innerMargin * 3);
        coin.setPosition(width / 2 + 8, 8 + 32 * 2 + innerMargin * 2);
        star.setPosition(width / 2 + 8, 8 + 32 + innerMargin);

        //bg.setPosition(leftPadding, bottomPadding);

    }

}
