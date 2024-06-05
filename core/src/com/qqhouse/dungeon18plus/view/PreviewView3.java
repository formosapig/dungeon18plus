package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class PreviewView3 extends AssetGroupButton {

    private QQImage icon;
    private QQText level, name, help;
    private QQIconText extra;

    public PreviewView3(Assets assets) {
        super(assets);
        setPadding(8);
    }

    public void reset(String iconKey, String nameKey, String bgKey) {
        // set background
        setBackground(assets.getBackgroundSet(bgKey));

        // icon
        icon = new QQImage(assets.getBlockee(iconKey));
        addChild(icon);

        // name
        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        name.setText(assets.geti18n(nameKey));
        addChild(name);
    }

    public void reset(String iconKey, String nameKey, String helpKey, String bgKey) {
        reset(iconKey, nameKey, bgKey);

        // help
        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.geti18n(helpKey), true);
        help.setAlign(Align.left);
        help.setPosition(64, bottomPadding);
        addChild(help);
    }

    public void resetLevel(String strLevel) {
        level = new QQText(assets.getFont(Game.Font.LEVEL16), assets.getNinePatchBG("level"), 0.75f);
        level.setColor(Game.Colour.RANK);
        level.setPadding(4);
        level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        //level.setPosition(4, 40);
        level.setText(strLevel);
        addChild(level);
    }

    public void resetExtra(String iconKey, String strExtra, Color color) {
        extra = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16(iconKey));
        extra.setSize(54, 16);
        extra.setColor(color);
        extra.setText(strExtra);
        addChild(extra);
    }

    @Override
    public void resetWrapHeight() {
        // if width not set, can not calculate height of font with multi line.
        if (0 == width) // QQView.MATCH_PARENT == width)
            return;

        // Text will calculate their height ...
        if (null != help) {
            float h = topPadding + bottomPadding + name.getHeight() + help.getHeight() + Game.Size.INNER_MARGIN;
            this.height = Math.max(48 + 8 + 8, h);
        } else {
            this.height = 64;
        }

        if (null != parent) {
            parent.onChildSizeChanged(this);
        }
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        super.onParentSizeChanged(width, height);
        if (0 < width) {
            name.setSize(width - 64 - 8, 24);
            if (null != help)
                help.setSize(width - 64 - 8, QQView.WRAP_CONTENT);
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        icon.setPosition(leftPadding, height - topPadding - 48);
        name.setPosition(64, height - 32);
        if (null != level)
            level.setPosition(4, this.height - 24);
        if (null != extra)
            extra.setPosition(this.width - extra.getWidth() - rightPadding, this.height - 24);
    }

}
