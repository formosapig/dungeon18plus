package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.tools.QQTextUtils;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;

public class CampaignScoreView extends AssetGroup {

    private QQImage icon;
    private QQImage loot;
    private QQIconText soul, damage, guard, heal;
    private final int soulW = 5, dmgW = 9, grdW = 8, healW = 8;
    private final int gap = 4;

    public CampaignScoreView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignScore score) {

        icon = new QQImage(assets.getBlockee(score.iconKey));
        icon.setSize(24, 24);
        //addChild(icon);

        // soul
        soul = new QQIconText(assets.getFont(Game.Font.CAMPAIGN_SMALL), assets.getBlockee(score.iconKey), 24);//Icon("item/soul"), 24);
        soul.setColor(Game.Colour.ROUND);
        soul.setText(Integer.toString(score.soul));
        soul.setAlign(Align.bottom);
        soul.setPadding(0, 0, 0, gap);
        addChild(soul);

        // damage
        damage = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon("icon/damage"), 24);
        damage.setColor(Game.Colour.DAMAGE);
        damage.setText(Integer.toString(score.damage));
        damage.setAlign(Align.right);
        damage.setPadding(0, 0, gap, gap);
        addChild(damage);

        // guard
        guard = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon("icon/guard"), 24);
        guard.setColor(Game.Colour.GUARD);
        guard.setText(Integer.toString(score.guard));
        guard.setAlign(Align.right);
        guard.setPadding(0, 0, gap, gap);
        addChild(guard);

        // heal
        heal = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon("icon/life"), 24);
        heal.setColor(Game.Colour.LIFE);
        heal.setText(Integer.toString(score.heal));
        heal.setAlign(Align.right);
        heal.setPadding(0, 0, gap, gap);
        addChild(heal);

        if (!QQTextUtils.isEmpty(score.lootIconKey)) {
            loot = new QQImage(assets.getIcon(score.lootIconKey));//Blockee(score.iconKey));
            loot.setSize(24, 24);
            addChild(loot);
        }

        setBackground(assets.getNinePatch("campaign_score1"));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - (leftPadding) - (rightPadding + 24 + gap)) / (soulW + dmgW + grdW + healW);
        damage.setSize(splitWidth * dmgW, 24);
        guard.setSize(splitWidth * grdW, 24);
        heal.setSize(splitWidth * healW, 24);
        soul.setSize(splitWidth * soulW, 24);
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
        float splitWidth = (width - (leftPadding ) - (rightPadding + 24 + gap)) / (soulW + dmgW + grdW + healW);
        if (null != soul)
            soul.setPosition(leftPadding, bottomPadding);
        if (null != damage)
            damage.setPosition(leftPadding + splitWidth * (soulW), bottomPadding);
        if (null != guard)
            guard.setPosition(leftPadding + splitWidth * (soulW + dmgW), bottomPadding);
        if (null != heal)
            heal.setPosition(leftPadding + splitWidth * (soulW + dmgW + grdW), bottomPadding);
    }

}
