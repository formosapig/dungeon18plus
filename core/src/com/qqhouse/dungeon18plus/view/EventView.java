package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.event.BattleEvent;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class EventView extends QQButton implements QQView.IsParent {

    private Assets assets;
    public EventView(Assets assets) {
        this.assets = assets;
    }

    private TextureRegion icon;
    private ItemView item; // loot
    private QQIconText cost; // big font ?!
    private AbilityView ability; // ability of foe or something...
    private QQText level, score;

    public void reset(Event event) {
        icon = assets.getBlockee(event.getIcon());

        // level
        if (event.type.isZako()) {
            level = new QQText(assets.getFont(Game.Font.LEVEL16), assets.getNinePatch("zako_level"), 0.75f);
            level.setColor(Game.Colour.ZAKO_LEVEL);
            level.setPadding(4);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
            //level.setSize(24, 16);
            level.setPosition(4, 40);
            childrenView.add(level);
        }

        // score
        if (event.type.isBoss()) {
            score = new QQText(assets.getFont(Game.Font.LEVEL16), assets.getNinePatch("level"), 0.75f);
            score.setColor(Game.Colour.RANK);
            score.setPadding(4);
            score.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
            score.setPosition(4, 4);
            childrenView.add(score);
        }

        // cost
        TextureRegion costIcon = null;
        Color costColor = Color.WHITE;
        String costValue = "";
        switch (event.costType) {
            case Game.Cost.KEY:
                costIcon = assets.getIcon("item/key");
                costColor = Game.Colour.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.Cost.COIN:
                costIcon = assets.getIcon("item/copper_coin");
                costColor = Game.Colour.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.Cost.NONE:
                costIcon = assets.getIcon("icon/win");
                break;
            case Game.Cost.NEVER:
                costIcon = assets.getIcon("icon/lose");
                break;
            case Game.Cost.DAMAGE:
                if (event.costValue < 0) {
                    costIcon = assets.getIcon("icon/life");
                    costColor = Game.Colour.LIFE;
                    costValue = String.format(Locale.US, "%+d", -event.costValue);
                } else {
                    costIcon = assets.getIcon("icon/damage");
                    costColor = Game.Colour.DAMAGE;
                    costValue = String.format(Locale.US, "%d", event.costValue);
                }
                break;
            case Game.Cost.BLOCK:
                costIcon = assets.getIcon("icon/defense");
                break;
        }
        //QQIconText cost = new QQIconText(assets.getFont("whitrabt"), new NinePatch(assets.getBackground("blessed")), costIcon);
        cost = new QQIconText(assets.getFont(Game.Font.EVENT_COST), costIcon);
        cost.setSize(64, 32);
        cost.setPosition(64, 24);
        cost.setAlign(Align.left);
        cost.setColor(costColor);
        cost.setText(costValue);
        childrenView.add(cost);

        // item ...
        item = new ItemView(assets.getFont(Game.Font.DIGITAL16), assets.getNinePatch("black"));
        item.setSize(32, 32);
        item.setPosition(Game.Size.WIDTH - 8 - 32, 26);
        item.setColor(Game.Colour.COUNT);
        item.setVisible(false);
        childrenView.add(item);

        //if (event.loot != Item.NONE) {

            //item = new ItemView(assets.getItem(event.loot.icon), assets.getFont("whitrabt", 16));
            //item.setColor(Game.color.RARE);
            //if (event.itemCount > 0) {
            //    item.setText(Integer.toString(event.itemCount));
            //} else {
            //    item.setText("");
            //}
        //}

        // ability view
        ability = new AbilityView(assets);
        ability.setSize(200, 16);
        ability.setPosition(Game.Size.WIDTH - 200 - 8, 6);
        ability.setVisible(false);
        childrenView.add(ability);

    }

    public void update(Event event) {
        icon = assets.getBlockee(event.getIcon());

        // level
        if (null != level) {
            level.setText(Integer.toString(event.getLevel()));
        }

        // score
        if (null != score) {
            score.setText(Integer.toString(event.getScore()));
        }

        // update cost
        TextureRegion costIcon = null;
        Color costColor = Color.WHITE;
        String costValue = "";
        switch (event.costType) {
            case Game.Cost.KEY:
                costIcon = assets.getIcon("item/key");
                costColor = Game.Colour.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.Cost.COIN:
                costIcon = assets.getIcon("item/copper_coin");
                costColor = Game.Colour.RARE;
                costValue = String.format(Locale.US, "%d", event.costValue);
                break;
            case Game.Cost.NONE:
                costIcon = assets.getIcon("icon/win");
                break;
            case Game.Cost.NEVER:
                costIcon = assets.getIcon("icon/lose");
                break;
            case Game.Cost.DAMAGE:
                if (event.costValue < 0) {
                    costIcon = assets.getIcon("icon/life");
                    costColor = Game.Colour.LIFE;
                    costValue = String.format(Locale.US, "%+d", -event.costValue);
                } else {
                    costIcon = assets.getIcon("icon/damage");
                    costColor = Game.Colour.DAMAGE;
                    costValue = String.format(Locale.US, "%d", event.costValue);
                }
                break;
            case Game.Cost.BLOCK:
                costIcon = assets.getIcon("icon/defense");
                break;
        }
        //QQIconText cost = new QQIconText(assets.getFont("whitrabt"), new NinePatch(assets.getBackground("blessed")), costIcon);
        cost.setIcon(costIcon);
        cost.setColor(costColor);
        cost.setText(costValue);

        // item ...
        if (event.loot != Item.NONE) {
            item.setItem(assets, event.loot);

            if (event.lootCount > 1) {
                item.setText(Integer.toString(event.lootCount));
            } else {
                item.setText("");
            }
            item.setVisible(true);
        } else {
            item.setVisible(false);
        }

        // update ability
        if (/*showDetail && */event.type.showDetail()) {
            if (event instanceof BattleEvent) {
                ability.update(((BattleEvent)event).getAbility());
                ability.setVisible(true);
            }
        } else if (event.type.showEquipmentData() && Item.NONE != event.loot) {
            ability.update(event.loot.upgrade);
            ability.setVisible(true);
        } else {
            ability.setVisible(false);
        }

        //Gdx.app.error("EventView", "event :" + event.type + ",bg : " + event.type.align.key);
        setBackground(assets.getBackgroundSet(event.type.align.key));
    }



    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw icon
        batch.draw(icon, originX + 8, originY + 8, 48, 48);

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
