package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SummaryView extends AssetGroup {

    // data
    private ArrayList<BossKill> kills;
    private final QQLinear group;

    private final Viewport viewport;
    public SummaryView(Assets assets, Viewport viewport) {
        super(assets);
        this.viewport = viewport;
        setPadding(8);
        bgNormal = assets.getNinePatchBG("dialog");

        group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        group.setPosition(leftPadding, bottomPadding);
        addChild(group);
    }

    public void reset(ArrayList<BossKill> kills, boolean isWin, QQButton.BackgroundSet bgSet, QQPressListener listener) {
        this.kills = kills;

        // total score
        int score = 0;
        for (BossKill bk : kills)
            score += bk.score;
        TitleBarView2 fairy = new TitleBarView2(assets);
        fairy.setSize(QQView.MATCH_PARENT, 40);
        fairy.setPadding(4, 4, 4, 8);
        fairy.reset(null, "score", "rank", Game.Colour.RANK, Integer.toString(score));
        fairy.setBackground(assets.getNinePatchBG("underline"));
        group.addChild(fairy);

        // list
        QQList1 scores = new QQList1(viewport, Game.Size.INNER_MARGIN);
        scores.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        scores.setMaxHeight(400);
        scores.setAdapter(adapter);
        group.addChild(scores);

        // button
        QQButtonEx done = new QQButtonEx(bgSet);
        done.setSize(QQView.MATCH_PARENT, 40);
        done.addQQClickListener(listener, 0);
        done.setText(assets.getFont(Game.Font.NAME20), assets.geti18n(isWin ? "win" : "lose"));
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
            return kills.size();
        }

        @Override
        public QQView getView(int index) {
            final BossKillView v = new BossKillView(assets, kills.get(index));
            v.setSize(QQView.MATCH_PARENT, 56);
            return v;
        }
    };
}
