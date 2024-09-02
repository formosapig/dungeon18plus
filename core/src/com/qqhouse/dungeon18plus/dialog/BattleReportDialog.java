package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class BattleReportDialog extends AssetDialog {

    /*
        battle report for wilderness
        battle against Giant Beast...
     */

    private final BattleReportView battleReport;

    public BattleReportDialog(Assets assets, Viewport viewport) {
        super(assets);

        setModal(true);

        battleReport = new BattleReportView(assets, viewport);
        battleReport.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

        setCustomView(battleReport);
    }

    public void reset(ArrayList<CampaignScore> scores, String resultKey, QQPressListener listener) {
        battleReport.reset(scores, resultKey, listener);
    }
}
