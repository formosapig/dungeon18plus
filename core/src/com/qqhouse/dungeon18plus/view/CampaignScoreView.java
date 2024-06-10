package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.ui.QQIconText;

public class CampaignScoreView extends AssetGroup {

    private Texture icon;
    private QQIconText cost;
    private float iconShiftX, costShiftX;

    public CampaignScoreView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignScore score) {

    }

}
