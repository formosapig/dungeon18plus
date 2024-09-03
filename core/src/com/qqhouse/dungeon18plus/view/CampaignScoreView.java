package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;

public class CampaignScoreView extends AssetGroup {

    private QQImage icon;
    private QQImage loot;
    private QQIconText damage, guard, heal;

    public CampaignScoreView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignScore score) {

        icon = new QQImage(assets.getBlockee(score.iconKey));
        icon.setSize(24, 24);
        //icon.setPosition(leftPadding, bottomPadding);
        addChild(icon);

        if ("" != score.lootIconKey && null != score.lootIconKey) {
            loot = new QQImage(assets.getIcon(score.lootIconKey));//Blockee(score.iconKey));
            loot.setSize(24, 24);
            //icon.setPosition(leftPadding, bottomPadding);
            addChild(loot);
        }

        // damage
        damage = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/damage"));
        damage.setColor(Game.Colour.DAMAGE);
        damage.setText(Integer.toString(score.damage));
        damage.setAlign(Align.right);
        damage.setPadding(0, 0, 8, 8);
        addChild(damage);

        // guard
        guard = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/guard"));
        guard.setColor(Game.Colour.GUARD);
        guard.setText(Integer.toString(score.guard));
        guard.setAlign(Align.right);
        guard.setPadding(0, 0, 8, 8);
        addChild(guard);

        // heal
        heal = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/life"));
        heal.setColor(Game.Colour.LIFE);
        heal.setText(Integer.toString(score.heal));
        heal.setAlign(Align.right);
        heal.setPadding(0, 0, 8, 8);
        addChild(heal);

        setBackground(assets.getNinePatch("campaign_score1"));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - (leftPadding + 24 + 8) - (rightPadding + 24 + 8)) / 3;
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

        if (null != loot)
            loot.setPosition(width - rightPadding - 24, bottomPadding);

        //float splitWidth = (width - leftPadding - rightPadding - 24 - 4 * 3) / 3;
        float splitWidth = (width - (leftPadding + 24 + 8) - (rightPadding + 24 + 8)) / 3;
        if (null != damage)
            damage.setPosition(leftPadding + 24 + 8, bottomPadding);

        if (null != guard)
            guard.setPosition(leftPadding + 24 + 8 + splitWidth * 1, bottomPadding);

        if (null != heal)
            heal.setPosition(leftPadding + 24 + 8 + splitWidth * 2, bottomPadding);


    }

}
