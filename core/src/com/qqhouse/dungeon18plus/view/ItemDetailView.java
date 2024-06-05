package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import org.jetbrains.annotations.NotNull;

public class ItemDetailView extends AssetGroup {

    private ItemView icon;
    private QQText name, help;
    private VarierView upgrade;


    public ItemDetailView(Assets assets) {
        super(assets);
        setPadding(Game.Size.BLOCKEE_PADDING);
    }

    public void reset() {
        // item...
        icon = new ItemView(null);
        icon.setSize(32, 32);
        addChild(icon);

        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        addChild(name);

        upgrade = new VarierView(assets);
        addChild(upgrade);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setAlign(Align.left);
        //help =
        addChild(help);

        bgNormal = assets.getNinePatchBG("special");
    }

    public void update(@NotNull Item item, boolean isUnknown) {
        //Gdx.app.error("ItemDetailView", "update : " + item);
        // item...
        icon.setIcon(assets.getItem(item.icon));
        if (!isUnknown) {
            if (item.isBlessed())
                icon.setStatus(assets.getBackground("blessed"));
            else if (item.isCursed())
                icon.setStatus(assets.getBackground("cursed"));
            else if (item.isRefined())
                icon.setStatus(assets.getBackground("refined"));
            else
                icon.setStatus(null);
        }

        name.setText(assets.geti18n(item.name));

        //upgrade.update(item.upgrade);
        if (!isUnknown)
            upgrade.update(item.upgrade);
        upgrade.setVisible(!isUnknown);

        // help maybe null
        if (null != item.help)
            help.setText(assets.geti18n(isUnknown ? item.unknown : item.help), true);
        help.setVisible(null != item.help);
    }

    @Override
    public void resetWrapHeight() {
        // calculate height
        if (0 == this.width)
            return;
        // decide this.height
        this.height =
                topPadding +
                Math.max(32, name.getHeight() + (upgrade.isVisible() ? (upgrade.getHeight() + Game.Size.INNER_MARGIN) : 0)) +
                //name.getHeight() +
                //Game.Size.INNER_MARGIN +
                //upgrade.getHeight() +
                (help.isVisible() ? (Game.Size.INNER_MARGIN + help.getHeight()) : 0)  +
                bottomPadding;
        //Gdx.app.error("ItemDetailView", "resetWrapHeight = " + this.height);
        // parent...
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    /*
        IsParent series
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width)
            return;
        // name
        name.setSize(width - 48 - leftPadding - rightPadding, 24);
        // upgrade
        upgrade.setSize(width - 48 - leftPadding - rightPadding, 16);
        // help
        help.setSize(width - leftPadding - rightPadding, QQView.WRAP_CONTENT);
    }

    @Override
    public void onChildSizeChanged(QQView child) {
        if (wrapHeight) {
            final float preHeight = this.height;
            resetWrapHeight();
            if (preHeight != this.height) {
                arrangeChildren();
            }
        }
    }

    @Override
    public void arrangeChildren() {
        //Gdx.app.error("ItemDetailView", "arrangeChildren = " + this.width + "," + this.height);
        if (0 == width || 0 == height)
            return;
        // item icon
        icon.setPosition(leftPadding, height - topPadding - 36);
        // name
        name.setPosition(leftPadding + 40, height - topPadding - 24);
        // upgrade
        upgrade.setPosition(leftPadding + 40, height - topPadding - 42);
        // help
        help.setPosition(leftPadding, bottomPadding);
    }
}
