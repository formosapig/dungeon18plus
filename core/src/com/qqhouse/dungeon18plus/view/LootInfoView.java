package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.EventResult;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class LootInfoView extends QQView implements QQView.IsParent {
    /*
        needs background..
     */
    private QQIconText star, coin, life, attack, defense, speed;
    private float totalW;

    private Assets assets;
    public LootInfoView(Assets assets) {

        this.assets = assets;
        bgNormal = new NinePatch(assets.getBackground("loot_info"), 4, 4, 4, 4);

        star = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("cost_star"));
        star.setColor(Game.color.RARE);
        star.setSize(16, 16);
        star.setPosition(0, 0);
        star.setWrapWidth(true);
        star.setVisible(false);
        childrenView.add(star);

        coin = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("cost_coin"));
        coin.setColor(Game.color.RARE);
        coin.setSize(16, 16);
        coin.setPosition(0, 0);
        coin.setWrapWidth(true);
        coin.setVisible(false);
        childrenView.add(coin);

        life = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("life"));
        life.setColor(Game.color.LIFE);
        life.setSize(16, 16);
        life.setPosition(0, 0);
        life.setWrapWidth(true);
        life.setVisible(false);
        childrenView.add(life);

        attack = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("attack"));
        attack.setColor(Game.color.ATTACK);
        attack.setSize(16, 16);
        attack.setPosition(0, 0);
        attack.setWrapWidth(true);
        attack.setVisible(false);
        childrenView.add(attack);

        defense = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("defense"));
        defense.setColor(Game.color.DEFENSE);
        defense.setSize(16, 16);
        defense.setPosition(0, 0);
        defense.setWrapWidth(true);
        defense.setVisible(false);
        childrenView.add(defense);

        speed = new QQIconText(assets.getFont(Game.font.DIGITAL, 18), assets.getIcon16("speed"));
        speed.setColor(Game.color.SPEED);
        speed.setSize(16, 16);
        speed.setPosition(0, 0);
        speed.setWrapWidth(true);
        speed.setVisible(false);
        childrenView.add(speed);
    }

    public void update(EventResult result) {
        // disable all view at first.
        for (QQView view : childrenView)
            view.setVisible(false);

        if (result.star != 0) {
            star.setText(String.format(Locale.US, "%+d", result.star));
            star.setVisible(true);
        }

        if (result.coin != 0) {
            coin.setText(String.format(Locale.US, "%+d", result.coin));
            coin.setVisible(true);
        }

        if (result.life != 0) {
            life.setText(String.format(Locale.US, "%+d", result.life));
            life.setVisible(true);
        }

        if (result.attack != 0) {
            attack.setText(String.format(Locale.US, "%+d", result.attack));
            attack.setVisible(true);
        }

        if (result.defense != 0) {
            defense.setText(String.format(Locale.US, "%+d", result.defense));
            defense.setVisible(true);
        }

        if (result.speed != 0) {
            speed.setText(String.format(Locale.US, "%+d", result.speed));
            speed.setVisible(true);
        }


        rearrange();
    }

    protected void rearrange() {
        // align center.
        float totalW = 0;
        for (QQView view : childrenView) {
            if (view.isVisible())
                totalW += (view.getWidth() + 2);
        }
        // last one does not need margin.
        totalW -= 2;

        float anchorX = (width - totalW) / 2;

        for (QQView view : childrenView) {
            if (view.isVisible()) {
                view.setPosition(anchorX, 4);
                anchorX += view.getWidth() + 2;
            }
        }
    }

    /*
        IsParent series
     */
    private ArrayList<QQView> childrenView = new ArrayList<>();
    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            if (view.isVisible()) {
                view.draw(batch, originX, originY);
            }
        }
    }
}
