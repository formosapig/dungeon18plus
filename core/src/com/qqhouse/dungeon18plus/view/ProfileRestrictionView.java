package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;

public class ProfileRestrictionView extends AssetGroup {

    private final QQIconText life, attack, defense, speed;
    private final ItemView yellowMirror, redMirror, blueMirror, greenMirror;
    private final int innerMargin;

    public ProfileRestrictionView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        life = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("life"));
        life.setPadding(4, 4, 8, 8);
        life.setColor(Game.Colour.LIFE);
        addChild(life);

        yellowMirror = new ItemView(assets.getItem("yellow_mirror"), assets.getFont(Game.Font.DIGITAL16), assets.getBackground("black"));
        addChild(yellowMirror);

        attack = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("attack"));
        attack.setPadding(4, 4, 8, 8);
        attack.setColor(Game.Colour.ATTACK);
        addChild(attack);

        redMirror = new ItemView(assets.getItem("red_mirror"), assets.getFont(Game.Font.DIGITAL16), assets.getBackground("black"));
        addChild(redMirror);

        defense = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("defense"));
        defense.setPadding(4, 4, 8, 8);
        defense.setColor(Game.Colour.DEFENSE);
        addChild(defense);

        blueMirror = new ItemView(assets.getItem("blue_mirror"), assets.getFont(Game.Font.DIGITAL16), assets.getBackground("black"));
        addChild(blueMirror);

        speed = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon32("speed"));
        speed.setPadding(4, 4, 8, 8);
        speed.setColor(Game.Colour.SPEED);
        addChild(speed);

        greenMirror = new ItemView(assets.getItem("green_mirror"), assets.getFont(Game.Font.DIGITAL16), assets.getBackground("black"));
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
        height = topPadding + 40 * 4 + innerMargin * 3 + bottomPadding;
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        float fixWidth = width - leftPadding - rightPadding;
        life.setSize(fixWidth, 40);
        attack.setSize(fixWidth, 40);
        defense.setSize(fixWidth, 40);
        speed.setSize(fixWidth, 40);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        life.setPosition(leftPadding, bottomPadding + 40 * 3 + innerMargin * 3);
        yellowMirror.setPosition(leftPadding + 48, bottomPadding + 4 + 40 * 3 + innerMargin * 3);
        attack.setPosition(leftPadding, bottomPadding + 40 * 2 + innerMargin * 2);
        redMirror.setPosition(leftPadding + 48, bottomPadding + 4 + 40 * 2 + innerMargin * 2);
        defense.setPosition(leftPadding, bottomPadding + 40 + innerMargin);
        blueMirror.setPosition(leftPadding + 48, bottomPadding + 4 + 40 + innerMargin);
        speed.setPosition(leftPadding, bottomPadding);
        greenMirror.setPosition(leftPadding + 48, bottomPadding + 4);

    }

}
