package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;

public class ProfileRestrictionView extends AssetGroup {

    private final QQText title;
    private final QQIconText life, attack, defense, speed;
    private final ItemView yellowMirror, redMirror, blueMirror, greenMirror;
    private final int innerMargin;

    public ProfileRestrictionView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatch("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        title.setText(assets.geti18n("profile_restriction"));
        addChild(title);

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/life"));
        life.setPadding(8);
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        yellowMirror = new ItemView(assets.getIcon("item/yellow_mirror"), assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(yellowMirror);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/attack"));
        attack.setPadding(8);
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        redMirror = new ItemView(assets.getIcon("item/red_mirror"), assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(redMirror);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/defense"));
        defense.setPadding(8);
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        blueMirror = new ItemView(assets.getIcon("item/blue_mirror"), assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(blueMirror);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon("icon/speed"));
        speed.setPadding(8);
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        greenMirror = new ItemView(assets.getIcon("item/green_mirror"), assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(greenMirror);
    }

    public void update(HeroClassRecord record) {
        setData(life, yellowMirror, record.yellowMirror, record.getMinLifeLimit(), record.getMaxLifeLimit());
        setData(attack, redMirror, record.redMirror, record.getMinAttackLimit(), record.getMaxAttackLimit());
        setData(defense, blueMirror, record.blueMirror, record.getMinDefenseLimit(), record.getMaxDefenseLimit());
        setData(speed, greenMirror, record.greenMirror, record.getMinSpeedLimit(), record.getMaxSpeedLimit());
    }

    public void setData(QQText tv, ItemView mirror, int mirrorCount, int min, int max) {
        if (0 < mirrorCount) {
            mirror.setVisible(true);
            mirror.setText(Integer.toString(mirrorCount));
        } else
            mirror.setVisible(false);
        String result = Integer.toString(min) + "!";
        if (min != max) {
            result += " ~ ";
            result += max + "!";
        }

        tv.setText(result);
    }

    @Override
    public void setBackground(NinePatch bg) {
        life.setBackground(bg);
        attack.setBackground(bg);
        defense.setBackground(bg);
        speed.setBackground(bg);
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + 32 + 4 + 48 * 4 + innerMargin * 3 + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        title.setSize(width, 32);
        float fixWidth = width - leftPadding - rightPadding;
        life.setSize(fixWidth, 48);
        attack.setSize(fixWidth, 48);
        defense.setSize(fixWidth, 48);
        speed.setSize(fixWidth, 48);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        title.setPosition(leftPadding, bottomPadding + 48 * 4 + innerMargin * 3 + 4);

        life.setPosition(leftPadding, bottomPadding + 48 * 3 + innerMargin * 3);
        yellowMirror.setPosition(leftPadding + 48, bottomPadding + 8 + 48 * 3 + innerMargin * 3);
        attack.setPosition(leftPadding, bottomPadding + 48 * 2 + innerMargin * 2);
        redMirror.setPosition(leftPadding + 48, bottomPadding + 8 + 48 * 2 + innerMargin * 2);
        defense.setPosition(leftPadding, bottomPadding + 48 + innerMargin);
        blueMirror.setPosition(leftPadding + 48, bottomPadding + 8 + 48 + innerMargin);
        speed.setPosition(leftPadding, bottomPadding);
        greenMirror.setPosition(leftPadding + 48, bottomPadding + 8);

    }

}
