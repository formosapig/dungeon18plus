package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQView;

public class ActionShortcutButton extends AssetGroupButton {

    private final QQImage icon;
    private final QQIconText cost;

    public ActionShortcutButton(Assets assets) {
        super(assets);

        icon = new QQImage();
        icon.setSize(32, 32);
        addChild(icon);

        cost = new QQIconText(assets.getFont(Game.Font.ITEM_COUNT));
        cost.setSize(QQView.WRAP_CONTENT, 16);
        addChild(cost);
    }

    public void update(ActionSlot slot, boolean enabled) {
        icon.setImage(assets.getIcon(slot.action.key));

        cost.setIcon(assets.getIcon(slot.action.cost.getIcon16Key()));
        cost.setText(Integer.toString(slot.action.cost.value));

        setEnabled(enabled);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        icon.setPosition((width - 32) / 2, 24);
        cost.setPosition((width - cost.getWidth()) / 2, 8);
    }

}
