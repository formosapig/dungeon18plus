package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.dungeon18plus.view.AssetGroup;
import com.qqhouse.dungeon18plus.view.BossKillView;
import com.qqhouse.dungeon18plus.view.CampaignScoreView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class BattleReportView extends AssetGroup {

    // data
    private ArrayList<CampaignScore> scores;
    private final QQLinear group;

    private final Viewport viewport;
    public BattleReportView(Assets assets, Viewport viewport) {
        super(assets);
        this.viewport = viewport;
        setPadding(8);
        bgNormal = assets.getNinePatchBG("dialog");

        group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        group.setPosition(leftPadding, bottomPadding);
        addChild(group);
    }

    public void reset(ArrayList<CampaignScore> scores, String resultKey, QQPressListener listener) {
        this.scores = scores;

        // battle report
        QQText title1 = new QQText(assets.getFont(Game.Font.NAME20));
        title1.setSize(QQView.MATCH_PARENT, 32);
        title1.setPadding(4);
        title1.setText(assets.geti18n("battle_report"));
        title1.setBackground(assets.getNinePatchBG("underline"));
        group.addChild(title1);

        BattleReportBaseView base = new BattleReportBaseView(assets);
        base.setSize(QQView.MATCH_PARENT, 20);
        //base.setBackground(assets.getNinePatchBG("help"));
        group.addChild(base);

        // list
        QQList1 scoresList = new QQList1(viewport, Game.Size.INNER_MARGIN);
        scoresList.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        scoresList.setMaxHeight(400);
        scoresList.setAdapter(adapter);
        group.addChild(scoresList);

        // war trophy (ies)
        QQText title2 = new QQText(assets.getFont(Game.Font.NAME20));
        title2.setSize(QQView.MATCH_PARENT, 32);
        title2.setPadding(4);
        title2.setText(assets.geti18n("war_trophy"));
        title2.setBackground(assets.getNinePatchBG("underline"));
        group.addChild(title2);

        // button
        QQButtonEx done = new QQButtonEx(assets.getBackgroundSet("ordinary"));
        done.setSize(QQView.MATCH_PARENT, 40);
        done.addQQClickListener(listener, 0);
        done.setText(assets.getFont(Game.Font.NAME20), assets.geti18n(resultKey));
        group.addChild(done);
    }

    @Override
    public void resetWrapHeight() {
        height = group.getHeight() + topPadding + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 < width)
            group.setSize(width - leftPadding - rightPadding, group.getHeight());
    }

    /*
        list view of boss kill....
     */
    private final QQListAdapter adapter = new QQListAdapter() {
        @Override
        public int getSize() {
            return scores.size();
        }

        @Override
        public QQView getView(int index) {
            BattleReportDetailView v = new BattleReportDetailView(assets);
            v.reset(scores.get(index));
            v.setPadding(4);
            v.setSize(QQView.MATCH_PARENT, 40);
            //v.setBackground(assets.getNinePatchBG("underline"));
            //v.setBackground(null);
            return v;
        }
    };
}
