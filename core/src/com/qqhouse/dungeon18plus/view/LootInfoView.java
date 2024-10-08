package com.qqhouse.dungeon18plus.view;

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
        bgNormal = assets.getNinePatch("loot_info");

        star = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/cost_star"));
        star.setColor(Game.Colour.RARE);
        star.setSize(16, 16);
        star.setPosition(0, 0);
        star.setWrapWidth(true);
        star.setVisible(false);
        childrenView.add(star);

        coin = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/cost_coin"));
        coin.setColor(Game.Colour.RARE);
        coin.setSize(16, 16);
        coin.setPosition(0, 0);
        coin.setWrapWidth(true);
        coin.setVisible(false);
        childrenView.add(coin);

        life = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/life"));
        life.setColor(Game.Colour.LIFE);
        life.setSize(16, 16);
        life.setPosition(0, 0);
        life.setWrapWidth(true);
        life.setVisible(false);
        childrenView.add(life);

        attack = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/attack"));
        attack.setColor(Game.Colour.ATTACK);
        attack.setSize(16, 16);
        attack.setPosition(0, 0);
        attack.setWrapWidth(true);
        attack.setVisible(false);
        childrenView.add(attack);

        defense = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/defense"));
        defense.setColor(Game.Colour.DEFENSE);
        defense.setSize(16, 16);
        defense.setPosition(0, 0);
        defense.setWrapWidth(true);
        defense.setVisible(false);
        childrenView.add(defense);

        speed = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/speed"));
        speed.setColor(Game.Colour.SPEED);
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
        for (QQView child : childrenView)
            child.draw(batch, originX, originY);
    }
}
