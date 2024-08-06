package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;

public class CampaignScoreView extends AssetGroup {

    private QQImage icon;
    private QQIconText damage, guard, heal;

    public CampaignScoreView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignScore score) {

        icon = new QQImage(assets.getBlockee(score.icon));
        icon.setSize(24, 24);
        //icon.setPosition(leftPadding, bottomPadding);
        addChild(icon);

        // damage
        damage = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/damage"));
        damage.setColor(Game.Colour.DAMAGE);
        damage.setText(Integer.toString(score.damage));
        addChild(damage);

        // guard
        guard = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/guard"));
        guard.setColor(Game.Colour.GUARD);
        guard.setText(Integer.toString(score.guard));
        addChild(guard);


        // heal
        heal = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/life"));
        heal.setColor(Game.Colour.LIFE);
        heal.setText(Integer.toString(score.heal));
        addChild(heal);

        setBackground(assets.getNinePatchBG("help"));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - leftPadding - rightPadding - 24 - 4 * 3) / 3;
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

        float splitWidth = (width - leftPadding - rightPadding - 24 - 4 * 3) / 3;
        if (null != damage)
            damage.setPosition(leftPadding + 24 + 4, bottomPadding);

        if (null != guard)
            guard.setPosition(leftPadding + 24 + splitWidth + 4, bottomPadding);

        if (null != heal)
            heal.setPosition(leftPadding + 24 + 4 +(splitWidth + 4) * 2, bottomPadding);


    }

}
