package com.qqhouse.dungeon18plus.dialog;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.view.AssetGroup;
import com.qqhouse.ui.QQImage;

public class BattleReportBaseView extends AssetGroup {

    private final QQImage damage, guard, heal;

    public BattleReportBaseView(Assets assets) {
        super(assets);

        damage = new QQImage(assets.getIcon("icon16/damage"));
        damage.setSize(16, 16);
        addChild(damage);

        guard = new QQImage(assets.getIcon("icon16/guard"));
        guard.setSize(16, 16);
        addChild(guard);

        heal = new QQImage(assets.getIcon("icon16/life"));
        heal.setSize(16, 16);
        addChild(heal);

        setPadding(40, 0, 4, 4);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 > height)
            return;

        float splitWidth = (width - leftPadding - rightPadding - 32 - 4) / 3;
        float shiftX = leftPadding + 32 + 4 + (splitWidth - 16) / 2;
        if (null != damage)
            damage.setPosition(shiftX, bottomPadding);

        if (null != guard)
            guard.setPosition(shiftX + splitWidth, bottomPadding);

        if (null != heal)
            heal.setPosition(shiftX + splitWidth  * 2, bottomPadding);
    }

}
