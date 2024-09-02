package com.qqhouse.dungeon18plus.dialog;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.dungeon18plus.view.AssetGroup;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

public class BattleReportDetailView extends AssetGroup {

    private QQImage icon;
    private QQText damage, guard, heal;

    public BattleReportDetailView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignScore score) {
        icon = new QQImage(assets.getBlockee(score.icon));
        icon.setSize(32, 32);
        addChild(icon);

        // damage
        damage = new QQText(assets.getFont(Game.Font.LOOT_INFO));
        damage.setColor(Game.Colour.DAMAGE);
        damage.setText(Integer.toString(score.damage));
        addChild(damage);

        // guard
        guard = new QQText(assets.getFont(Game.Font.LOOT_INFO));
        guard.setColor(Game.Colour.GUARD);
        guard.setText(Integer.toString(score.guard));
        addChild(guard);

        // heal
        heal = new QQIconText(assets.getFont(Game.Font.LOOT_INFO));
        heal.setColor(Game.Colour.LIFE);
        heal.setText(Integer.toString(score.heal));
        addChild(heal);
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - leftPadding - rightPadding - 32 - 4) / 3;
        damage.setSize(splitWidth, 24);
        guard.setSize(splitWidth, 24);
        heal.setSize(splitWidth, 24);

    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 > height)
            return;

        if (null != icon)
            icon.setPosition(leftPadding, bottomPadding);

        float splitWidth = (width - leftPadding - rightPadding - 32 - 4) / 3;
        if (null != damage)
            damage.setPosition(leftPadding + 32 + 4, bottomPadding);

        if (null != guard)
            guard.setPosition(leftPadding + 32 + 4 + splitWidth, bottomPadding);

        if (null != heal)
            heal.setPosition(leftPadding + 32 + 4 + splitWidth * 2, bottomPadding);


    }

}
