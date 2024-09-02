package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

public class ProfileTitleView extends AssetGroup {

    private final QQImage blockee;
    private final QQText name;
    private final ItemView coin;

    public ProfileTitleView(Assets assets) {
        super(assets);

        blockee = new QQImage();
        blockee.setSize(48, 48);
        addChild(blockee);
        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        addChild(name);
        coin = new ItemView(assets.getIcon("item/golden_coin"), assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        coin.setSize(32, 32);
        coin.setVisible(false);
        addChild(coin);
    }

    public void update(String blockeeKey, String nameKey) {
        blockee.setImage(assets.getBlockee(blockeeKey));
        name.setText(assets.geti18n(nameKey));
    }

    public void update(String blockeeKey, String nameKey, int goldenCoin) {
        blockee.setImage(assets.getBlockee(blockeeKey));
        name.setText(assets.geti18n(nameKey));
        coin.setText(Integer.toString(goldenCoin));
        coin.setVisible(true);
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + blockee.getHeight() + bottomPadding;

        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width)// || 0 >= height)
            return;
        name.setSize(width - leftPadding - rightPadding - 48 - 4, 48);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        blockee.setPosition(leftPadding, bottomPadding);
        name.setPosition(leftPadding + 48 + 4, bottomPadding);
        coin.setPosition(width - rightPadding - 32, bottomPadding + 8);
    }

}
