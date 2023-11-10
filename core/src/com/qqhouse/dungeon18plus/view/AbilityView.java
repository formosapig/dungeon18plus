package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.Varier;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class AbilityView extends QQView implements QQView.IsParent {

    private Assets assets;
    private QQIconText life, attack, defense, speed, coin;
    private final int VARIER_FILTER = Varier.Type.LIFE | Varier.Type.ATTACK | Varier.Type.DEFENSE | Varier.Type.SPEED | Varier.Type.COIN;


    public AbilityView(Assets assets) {
        this.assets = assets;

        life = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("life"));
        life.setSize(48, 16);
        life.setPosition(0, 0);
        life.setColor(Game.Colour.LIFE);
        life.setAlign(Align.right);
        life.setWrapWidth(true);
        childrenView.add(life);

        attack = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("attack"));
        attack.setSize(48, 16);
        attack.setPosition(48, 0);
        attack.setColor(Game.Colour.ATTACK);
        attack.setAlign(Align.right);
        attack.setWrapWidth(true);
        childrenView.add(attack);

        defense = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("defense"));
        defense.setSize(48, 16);
        defense.setPosition(96, 0);
        defense.setColor(Game.Colour.DEFENSE);
        defense.setAlign(Align.right);
        defense.setWrapWidth(true);
        childrenView.add(defense);

        speed = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("speed"));
        speed.setSize(48, 16);
        speed.setPosition(144, 0);
        speed.setColor(Game.Colour.SPEED);
        speed.setAlign(Align.right);
        speed.setWrapWidth(true);
        childrenView.add(speed);

        coin = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("cost_coin"));
        coin.setSize(48, 16);
        coin.setPosition(192, 0);
        coin.setColor(Game.Colour.RARE);
        coin.setAlign(Align.right);
        coin.setWrapWidth(true);
        childrenView.add(coin);
    }

    //@Override
    protected void rearrange() {
        float anchorX = width;
        if (speed.isVisible()) {
            anchorX -= speed.getWidth();
            speed.setPosition(anchorX, 0);
            anchorX -= Game.Size.INNER_MARGIN;
        }
        if (defense.isVisible()) {
            anchorX -= defense.getWidth();
            defense.setPosition(anchorX, 0);
            anchorX -= Game.Size.INNER_MARGIN;
        }
        if (attack.isVisible()) {
            anchorX -= attack.getWidth();
            attack.setPosition(anchorX, 0);
            anchorX -= Game.Size.INNER_MARGIN;
        }
        if (life.isVisible()) {
            anchorX -= life.getWidth();
            life.setPosition(anchorX, 0);
        }
    }


    public void update(Ability ability) {
        life.setText(Integer.toString(ability.life));
        attack.setText(Integer.toString(ability.attack));
        defense.setText(Integer.toString(ability.defense));
        speed.setText(Integer.toString(ability.speed));
        rearrange();
    }

    public void update(Varier... variers) {
        life.setVisible(false);
        attack.setVisible(false);
        defense.setVisible(false);
        speed.setVisible(false);
        coin.setVisible(false);

        for (Varier var : variers) {
            if ((var.type & VARIER_FILTER) == 0)
                continue;

            String result;
            if (var.isSet()) {
                result = String.format(Locale.US, "=%d", var.value);
            } else {
                result = String.format(Locale.US, "%+d", var.value);
                if (var.isPercent()) {
                    result += "%";
                }
            }

            // set test
            if (var.isLife()) {
                life.setVisible(true);
                life.setText(result);
            } else if (var.isAttack()) {
                attack.setVisible(true);
                attack.setText(result);
            } else if (var.isDefense()) {
                defense.setVisible(true);
                defense.setText(result);
            } else if (var.isSpeed()) {
                speed.setVisible(true);
                speed.setText(result);
            } else {
                coin.setVisible(true);
                coin.setText(result);
            }
        }
        rearrange();
    }

    /*
        IsParent series
     */
    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            if (view.isVisible())
                view.draw(batch, originX, originY);
        }
    }
}
