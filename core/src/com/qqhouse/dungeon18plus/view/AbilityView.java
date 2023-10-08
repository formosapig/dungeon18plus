package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class AbilityView extends QQView implements QQView.IsParentView {

    private ArrayList<QQView> childrenView = new ArrayList<>();
    private QQIconText life, attack, defense, speed;
    private Assets assets;

    public AbilityView(Assets assets) {
        this.assets = assets;

        life = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("life"));
        life.setSize(48, 16);
        life.setPosition(0, 0);
        life.setColor(Game.color.LIFE);
        life.setAlign(Align.right);
        life.setWrapWidth(true);
        childrenView.add(life);

        attack = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("attack"));
        attack.setSize(48, 16);
        attack.setPosition(48, 0);
        attack.setColor(Game.color.ATTACK);
        attack.setAlign(Align.right);
        attack.setWrapWidth(true);
        childrenView.add(attack);

        defense = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("defense"));
        defense.setSize(48, 16);
        defense.setPosition(96, 0);
        defense.setColor(Game.color.DEFENSE);
        defense.setAlign(Align.right);
        defense.setWrapWidth(true);
        childrenView.add(defense);

        speed = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("speed"));
        speed.setSize(48, 16);
        speed.setPosition(144, 0);
        speed.setColor(Game.color.SPEED);
        speed.setAlign(Align.right);
        speed.setWrapWidth(true);
        childrenView.add(speed);
    }

    //@Override
    protected void rearrange() {
        float anchorX = width;
        speed.setPosition(anchorX -= speed.getWidth(), 0);
        defense.setPosition(anchorX -= (defense.getWidth() + 2), 0);
        attack.setPosition(anchorX -= (attack.getWidth() + 2), 0);
        life.setPosition(anchorX -= (life.getWidth() + 2), 0);


    }


    public void update(Ability ability) {
        life.setText(Integer.toString(ability.life));
        attack.setText(Integer.toString(ability.attack));
        defense.setText(Integer.toString(ability.defense));
        speed.setText(Integer.toString(ability.speed));
        rearrange();
    }

    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            view.draw(batch, originX, originY);
        }
    }
}
