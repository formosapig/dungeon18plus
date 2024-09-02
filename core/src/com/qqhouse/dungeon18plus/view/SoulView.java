package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQImage;

public class SoulView extends AssetGroup {
    private final ItemView soul;
    private final VarietyView upgrade;

    public SoulView(Assets assets, SoulCount sc) {
        super(assets);

        soul = ItemView.create(assets, sc);//new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        //soul.setSoulCount(sc);
        addChild(soul);

        upgrade = new VarietyView(assets);
        upgrade.update32(sc.soul.getInfluence(sc.count));
        addChild(upgrade);
    }

    public void update(SoulCount sc) {
        //soul.setSoulCount(assets, sc);

        //upgrade.update32(sc.soul.getInfluence(sc.count));
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
        upgrade.setSize(width - leftPadding - 32 - 8 - rightPadding, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        soul.setPosition(leftPadding, bottomPadding);
        upgrade.setPosition(leftPadding + 32 + 8, bottomPadding);
    }

}
