package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    private TextureRegion icon, status;
    private QQText rank;
    private QQText count;

    /*
        status background
     */
    private static TextureRegion blessed;
    private static TextureRegion cursed;
    private static TextureRegion refined;

    public ItemView() {}

    public ItemView(TextureRegion icon) {
        this.icon = icon;
    }

    // cursed / blessed / refined type items are countless.
    public ItemView(TextureRegion icon, TextureRegion status) {
        this.icon = icon;
        this.status = status;
    }

    public ItemView(BitmapFont font, NinePatch bg) {
        count = new QQText(font, bg, 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPosition(0, 0);
        count.setPadding(2);
    }

    // for SoulCount only.
    ItemView(BitmapFont font, NinePatch bgRank, NinePatch bgCount) {
        // rank of soul
        rank = new QQText(font, bgRank, 0.5f);
        rank.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        rank.setPadding(2);
        rank.setColor(Game.Colour.SOUL_LEVEL);
        // count of soul
        count = new QQText(font, bgCount, 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPadding(2);
    }

    public ItemView(TextureRegion icon, BitmapFont font, NinePatch bg) {
        this.icon = icon;
        count = new QQText(font, bg, 0.5f);
        count.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        count.setPosition(0, 0);
        count.setPadding(2);
    }

    public void setIcon(TextureRegion icon) {
        this.icon = icon;
    }

    public void setStatus(TextureRegion status) {
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

    public void setItem(Assets assets, Item item) {
        icon = assets.getIcon(item.icon);
        if (item.isBlessed())
            status = null == blessed ? blessed = assets.getBackground("blessed") : blessed;
        else if (item.isCursed())
            status = null == cursed ? cursed = assets.getBackground("cursed") : cursed;
        else if (item.isRefined())
            status = null == refined ? refined = assets.getBackground("refined") : refined;
        else
            status = null;
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
        setItem(assets, em.equipment);
        /*setIcon(assets.getIcon(em.equipment.icon));
        if (em.equipment.isBlessed())
            setStatus(assets.getBackground("blessed"));
        else if (em.equipment.isCursed())
            setStatus(assets.getBackground("cursed"));
        else if (em.equipment.isRefined())
            setStatus(assets.getBackground("refined"));*/
        count.setColor(Game.Colour.MASTERY);
        count.setText(Integer.toString(em.mastery));
        count.setPosition(34 - count.getWidth(), -2);
    }

    /*
        Soul Count
     */
    public void setSoulCount(SoulCount sc) {
        //rank.setText(Integer.toString(sc.soul.rank));
        //rank.setPosition(-2, 34 - rank.getHeight());
        //count.setText(Integer.toString(sc.count));
        //count.setPosition(34 - count.getWidth(), -2);
    }


    /*
        ItemView creator
     */
    public static ItemView create(Assets assets, Item item) {
        ItemView iv = new ItemView();
        iv.setItem(assets, item);
        /*ItemView iv = new ItemView(assets.getIcon(item.icon));
        if (item.isBlessed())
            iv.setStatus(assets.getBackground("blessed"));
        else if (item.isCursed())
            iv.setStatus(assets.getBackground("cursed"));
        else if (item.isRefined())
            iv.setStatus(assets.getBackground("refined"));
        */
        return iv;
    }

    public static ItemView create(Assets assets, Item item, int count) {
        ItemView iv = new ItemView(assets.getIcon(item.icon), assets.getFont(Game.Font.LEVEL16), assets.getNinePatch("black"));
        return iv;
    }

    public static ItemView create() {
        return new ItemView();
    }

    public static ItemView create(Assets assets, SoulCount sc) {
        ItemView iv = new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"), assets.getNinePatch("black"));
        iv.setIcon(assets.getIcon(sc.soul.iconKey));

        iv.rank.setText(Integer.toString(sc.soul.rank));
        iv.rank.setPosition(-2, 34 - iv.rank.getHeight());

        iv.count.setText(Integer.toString(sc.count));
        iv.count.setPosition(34 - iv.count.getWidth(), -2);

        return iv;
    }

}
