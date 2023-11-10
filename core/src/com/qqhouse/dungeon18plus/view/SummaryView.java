package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SummaryView extends QQGroup {

    // data
    private ArrayList<BossKill> kills;
    private boolean isWin;

    private final Assets assets;
    private final Camera camera;
    public SummaryView(Assets assets, Camera camera) {
        super(DIRECT_VERTICAL, Game.Size.WIDGET_MARGIN);
        this.assets = assets;
        this.camera = camera;
        setPadding(Game.Size.BLOCKEE_PADDING);
        bgNormal = new NinePatch(assets.getBackground("dialog"), 4, 4, 4, 4);
    }

    public void reset(ArrayList<BossKill> kills, boolean isWin, QQPressListener listener) {
        this.kills = kills;
        this.isWin = isWin;

        // create ...
        // button
        // TODO QQButton can add image / text ...
        QQButtonEx done = new QQButtonEx(assets.getBackgroundSet(GameAlignment.NEUTRAL.key));
        //done.setPosition(leftPadding, bottomPadding);
        done.setSize(QQView.MATCH_PARENT, 40);
        done.addQQClickListener(listener, 0);
        done.setText(assets.getFont(Game.Font.NAME20), assets.geti18n(isWin ? "win" : "lose"));
        addChild(done);

        // list
        QQList scores = new QQList();
        //scores.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scores.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        scores.setMaxHeight(400);
        scores.setCamera(camera);
        scores.setAdapter(adapter);
        addChild(scores);
        //scores.setBackground(new NinePatch(assets.getBackground("white"), 4, 4, 4, 4));

        //TitleBarView fairy = new TitleBarView(
        //        assets.getBlockee("fairy"),
        //        assets.getFont(Game.Font.NAME20),
        //        assets.geti18n("score"));
        //fairy.setPosition(leftPadding + 4, height - 40 - topPadding);
        //fairy.setSize(QQView.MATCH_PARENT, 40);
        //addChild(fairy);

        // total score
        int score = 0;
        for (BossKill bk : kills)
            score += bk.score;
        TitleBarView2 fairy = new TitleBarView2(assets);
        fairy.setSize(QQView.MATCH_PARENT, 40);
        fairy.setPosition(leftPadding + 4, height - 40 - topPadding);
        fairy.setPadding(4, 4, 4, 8);
        fairy.reset(null, "score", "rank", Game.Colour.RANK, Integer.toString(score));
        fairy.setBackground(assets.getNinePatchBG("underline"));

        //fairy.setPadding(4);


        addChild(fairy);

        resetWrapHeight();
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    /*
        list view of boss kill....
     */
    private final QQList.Adapter adapter = new QQList.Adapter() {
        @Override
        public int getSize() {
            return kills.size();
        }

        @Override
        public QQView getView(int index) {
            final BossKillView v = new BossKillView(assets);
            v.reset(kills.get(index));
            v.setSize(QQView.MATCH_PARENT, 56);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
