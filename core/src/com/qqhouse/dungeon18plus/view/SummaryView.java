package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQButton;
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
        super(DIRECT_VERTICAL, 2);
        this.assets = assets;
        this.camera = camera;
        bgNormal = new NinePatch(assets.getBackground("dialog"), 4, 4, 4, 4);
    }

    public void reset(ArrayList<BossKill> kills, boolean isWin, QQPressListener listener) {
        this.kills = kills;
        this.isWin = isWin;

        // create ...
        // button
        // TODO QQButton can add image / text ...
        QQButton done = new QQButton(assets.getBackgroundSet(GameAlignment.NEUTRAL.key));
        //done.setPosition(leftPadding, bottomPadding);
        done.setSize(QQView.MATCH_PARENT, 40);
        done.addQQClickListener(listener, 0);
        addChild(done);

        // list
        QQList scores = new QQList();
        scores.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scores.setCamera(camera);
        // FIXME 相反的順序就找不到寛度了...
        scores.setAdapter(adapter);
        addChild(scores);
        //scores.setBackground(new NinePatch(assets.getBackground("white"), 4, 4, 4, 4));

        TitleBarView fairy = new TitleBarView(
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
    private final QQList.Adapter adapter = new QQList.Adapter() {
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
