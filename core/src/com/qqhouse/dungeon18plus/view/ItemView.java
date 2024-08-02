package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ItemView extends QQView {
    /*
        item = 32 x 32
        small white rabbit font (16) to display quantity of item.

        total size = 32 x 32 (+2) , display quantity's play needs 2 pixel...
     */

    private Texture icon, status;
    private QQText rank;
    private QQText count;

    public ItemView() {}

    public ItemView(Texture icon) {
        this.icon = icon;
    }

    // cursed / blessed / refined type items are countless.
    public ItemView(Texture icon, Texture status) {
        this.icon = icon;
        this.status = status;
    }

    public ItemView(BitmapFont font, Texture bg) {
        count = new QQText(font, new NinePatch(bg, 4, 4, 4, 4), 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPosition(0, 0);
        count.setPadding(2);
    }

    public ItemView(BitmapFont font, NinePatch bg) {
        rank = new QQText(font, bg, 0.5f);
        rank.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        rank.setPadding(2);
        count = new QQText(font, bg, 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPadding(2);
    }

    public ItemView(Texture icon, BitmapFont font, Texture bg) {
        this.icon = icon;
        count = new QQText(font, new NinePatch(bg, 4, 4, 4, 4), 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPosition(0, 0);
        count.setPadding(2);
    }

    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public void setStatus(Texture status) {
        this.status = status;
    }

    public void setColor(Color color) {
        count.setColor(color);
    }

    public void setText(String text) {
        if ("".equals(text)) {
            count.setVisible(false);
        } else {
            count.setVisible(true);
            count.setText(text);
            count.setPosition(34 - count.getWidth(), -2);
        }
    }

    @Override
    protected void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw type if exist, then item
        if (null != status)
            batch.draw(status, originX, originY, 32, 32);
        if (null != icon)
            batch.draw(icon, originX, originY, 32, 32);

        // draw count if exist
        if (null != rank)
            rank.draw(batch, originX, originY);
        if (null != count)
            count.draw(batch, originX, originY);
    }

    @Override
    public void dispose() {}

    /*
        Equipment Mastery
     */
    public void setEquipmentMastery(Assets assets, EquipmentMastery em) {
        setIcon(assets.getItem(em.equipment.icon));
        if (em.equipment.isBlessed())
            setStatus(assets.getBackground("blessed"));
        else if (em.equipment.isCursed())
            setStatus(assets.getBackground("cursed"));
        else if (em.equipment.isRefined())
            setStatus(assets.getBackground("refined"));
        count.setColor(Game.Colour.MASTERY);
        count.setText(Integer.toString(em.mastery));
        count.setPosition(34 - count.getWidth(), -2);
    }

    /*
        Soul Count
     */
    public void setSoulCount(Assets assets, SoulCount sc) {
        setIcon(assets.getItem(sc.soul.iconKey));

        rank.setColor(Game.Colour.SOUL_LEVEL);
        rank.setText(Integer.toString(sc.soul.rank));
        rank.setPosition(-2, 34 - rank.getHeight());

        count.setText(Integer.toString(sc.count));
        count.setPosition(34 - count.getWidth(), -2);
    }

    /*
        chain method
     */
    public ItemView color(Color color) {
        count.setColor(color);
        return this;
    }

    // FIXME QQView 有 generic type function, 所以這邊的要檢查型別?
    // uses unchecked or unsafe operations
    public ItemView size(float width, float height) {
        super.setSize(width, height);
        return this;
    }

    public ItemView position(float x, float y) {
        super.setPosition(x, y);
        return this;
    }

    public ItemView attach(IsParent parent) {
        parent.addChild(this);
        return this;
    }

    /*
        ItemView creator
     */
    public static ItemView create(Assets assets, Item item) {
        ItemView iv = new ItemView(assets.getItem(item.icon));
        if (item.isBlessed())
            iv.setStatus(assets.getBackground("blessed"));
        else if (item.isCursed())
            iv.setStatus(assets.getBackground("cursed"));
        else if (item.isRefined())
            iv.setStatus(assets.getBackground("refined"));
        return iv;
    }

    public static ItemView create(Assets assets, Item item, int count) {
        ItemView iv = new ItemView(assets.getItem(item.icon), assets.getFont(Game.Font.LEVEL16), assets.getBackground("black"));
        return iv;
    }

    public static ItemView create() {
        return new ItemView();
    }
}
