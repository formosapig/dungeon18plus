package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SummaryDialog extends QQGroup {

    // fairy title view to tell score....
    private TitleBarView fairy;

    // list view list scores ...
    private QQList scores;
    private QQButton done; // victory / failed.

    // data
    private ArrayList<BossKill> kills;
    private boolean isWin;

    private Assets assets;
    private Camera camera;
    public SummaryDialog(Assets assets, Camera camera) {
        super(DIRECT_VERTICAL, 2);
        this.assets = assets;
        this.camera = camera;
        bgNormal = new NinePatch(assets.getBackground("dialog"), 4, 4, 4, 4);
    }

    public void reset(ArrayList<BossKill> kills, boolean isWin) {
        this.kills = kills;
        this.isWin = isWin;

        // create ...
        // button
        done = new QQButton(assets.getBackgroundSet(GameAlignment.NEUTRAL.key));
        //done.setPosition(leftPadding, bottomPadding);
        done.setSize(QQView.MATCH_PARENT, 40);
        addChild(done);

        // list
        scores = new QQList();
        scores.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scores.setCamera(camera);
        // FIXME 相反的順序就找不到寛度了...
        addChild(scores);
        scores.setAdapter(adapter);
        //scores.setBackground(new NinePatch(assets.getBackground("white"), 4, 4, 4, 4));

        fairy = new TitleBarView(
                assets.getBlockee("fairy"),
                assets.getFont(Game.Font.NAME20),
                assets.geti18n("score"));
        //fairy.setPosition(leftPadding + 4, height - 40 - topPadding);
        fairy.setSize(QQView.MATCH_PARENT, 40);
        addChild(fairy);

    }

    /*
        list view of boss kill....
     */
    private QQList.Adapter adapter = new QQList.Adapter() {
        @Override
        public int getSize() {
            return kills.size();
        }

        @Override
        public QQView getView(int index) {
            final BossKillView v = new BossKillView(assets);
            v.reset(kills.get(index));
            v.setSize(QQView.MATCH_PARENT, 64);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
