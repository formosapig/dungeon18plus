package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

public class MainMenuView extends AssetGroupButton {

    private QQImage blockee;
    private QQText menu;

    public MainMenuView(Assets assets) {
        super(assets);
        setPadding(8);
    }

    public void reset(String blockeeKey, String menuKey, String bgKey) {
        blockee = new QQImage(assets.getBlockee(blockeeKey));
        addChild(blockee);

        menu = new QQText(assets.getFont(Game.Font.TITLE28));
        menu.setColor(Game.Colour.RARE);
        menu.setText(assets.geti18n(menuKey));
        addChild(menu);

        setBackground(assets.getBackgroundSet(bgKey));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        super.onParentSizeChanged(width, height);
        if (0 < width) {
            blockee.setSize(48, 48);
            menu.setSize(width - leftPadding - rightPadding - 8 - 48, 48);
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        blockee.setPosition(leftPadding, bottomPadding);
        menu.setPosition(leftPadding + 48 + 8, bottomPadding);
    }
}
