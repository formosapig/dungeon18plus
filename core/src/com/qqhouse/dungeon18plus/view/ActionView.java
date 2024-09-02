package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.ui.QQImage;

public class ActionView extends AssetGroup {

    // display action
    private final QQImage icon;
    private final ItemView cost;
    private final VarietyView upgrade;

    public ActionView(Assets assets) {
        super(assets);

        icon = new QQImage();
        icon.setSize(32, 32);
        addChild(icon);

        upgrade = new VarietyView(assets);
        addChild(upgrade);

        cost = new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(cost);
    }

    public void update(Action action) {
        icon.setImage(assets.getIcon(action.key));

        upgrade.update32(action.effects);

        cost.setIcon(assets.getIcon("item/star"));
        cost.setText(Integer.toString(action.cost.value));
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + 32 + bottomPadding;
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        upgrade.setSize(width - leftPadding - 32 - 8 - rightPadding - 32 - 8, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        icon.setPosition(leftPadding, bottomPadding);
        upgrade.setPosition(leftPadding + 32 + 8, bottomPadding);
        cost.setPosition(width - rightPadding - 32, bottomPadding);
    }

}
