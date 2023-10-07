package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class EventView extends QQButton implements QQView.IsParentView {

    public EventView(String buttonKey, Assets assets) {
        super(buttonKey);
        this.assets = assets;
    }

    private Assets assets;
    private Texture icon;
    private ItemView item; // loot
    private QQIconText cost; // big font ?!
    private AbilityView ability; // ability of foe or something...

    public void reset(Event event) {
        icon = assets.getBlockee(event.type.icon);

        // cost
        Texture costIcon = null;
        Color costColor = Color.WHITE;
        String costValue = "";
        switch (event.costType) {
            case Game.cost.key:
                costIcon = assets.getItem("key");
                costColor = Game.color.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.cost.coin:
                costIcon = assets.getItem("copper_coin");
                costColor = Game.color.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.cost.none:
                costIcon = assets.getIcon32("win");
                break;
            case Game.cost.never:
                costIcon = assets.getIcon32("lose");
                break;
            case Game.cost.damage:
                if (event.costValue < 0) {
                    costIcon = assets.getIcon32("life");
                    costColor = Game.color.LIFE;
                    costValue = String.format(Locale.US, "%+d", -event.costValue);
                } else {
                    costIcon = assets.getIcon32("damage");
                    costColor = Game.color.DAMAGE;
                    costValue = String.format(Locale.US, "%d", event.costValue);
                }
                break;
        }
        //QQIconText cost = new QQIconText(assets.getFont("whitrabt"), new NinePatch(assets.getBackground("blessed")), costIcon);
        cost = new QQIconText(assets.getFont("whitrabt", 22), costIcon);
        cost.setSize(64, 32);
        cost.setPosition(64, 24);
        cost.setAlign(Align.left);
        cost.setColorText(costColor, costValue);
        childrenView.add(cost);

        // item ...
        if (event.loot != Item.NONE) {

            item = new ItemView(assets.getItem(event.loot.icon), assets.getFont("whitrabt", 16));
            //item.setColor(Game.color.RARE);
            if (event.itemCount > 0) {
                item.setText(Integer.toString(event.itemCount));
            } else {
                item.setText("");
            }
            item.setSize(32, 32);
            item.setPosition(Game.WIDTH - 8 - 32, 24);
            childrenView.add(item);
        }

    }

    public void update(Event event) {
        // update cost
        Texture costIcon = null;
        Color costColor = Color.WHITE;
        String costValue = "";
        switch (event.costType) {
            case Game.cost.key:
                costIcon = assets.getItem("key");
                costColor = Game.color.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.cost.coin:
                costIcon = assets.getItem("copper_coin");
                costColor = Game.color.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.cost.none:
                costIcon = assets.getIcon32("win");
                break;
            case Game.cost.never:
                costIcon = assets.getIcon32("lose");
                break;
            case Game.cost.damage:
                if (event.costValue < 0) {
                    costIcon = assets.getIcon32("life");
                    costColor = Game.color.LIFE;
                    costValue = String.format(Locale.US, "%+d", -event.costValue);
                } else {
                    costIcon = assets.getIcon32("damage");
                    costColor = Game.color.DAMAGE;
                    costValue = String.format(Locale.US, "%d", event.costValue);
                }
                break;
        }
        //QQIconText cost = new QQIconText(assets.getFont("whitrabt"), new NinePatch(assets.getBackground("blessed")), costIcon);
        cost.setIcon(costIcon);
        cost.setColorText(costColor, costValue);
    }



    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw icon
        batch.draw(icon, originX + 8, originY + 8, 48, 48);

    }

    private ArrayList<QQView> childrenView = new ArrayList<>();
    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {
        for (QQView view : childrenView) {
            view.draw(batch, originX, originY);
        }
    }
}
