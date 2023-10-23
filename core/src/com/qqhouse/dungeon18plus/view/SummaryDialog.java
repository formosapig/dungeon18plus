package com.qqhouse.dungeon18plus.view;

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
    public SummaryDialog(Assets assets) {
        this.assets = assets;
    }

    public void reset(ArrayList<BossKill> kills, boolean isWin) {
        this.kills = kills;
        this.isWin = isWin;

        // create ...
        fairy = new TitleBarView(
                assets.getBlockee("fairy"),
                assets.getFont(Game.Font.NAME20),
                assets.geti18n("score"));
        fairy.setPosition(0, 0);
        fairy.setSize(48 + 8 + 8, QQView.MATCH_PARENT);
        addChild(fairy);


        // button
        done = new QQButton(assets.getBackgroundSet(GameAlignment.NEUTRAL.key));
        done.setPosition(0, 0);
        done.setSize(40, QQView.MATCH_PARENT);
        addChild(done);

    }

}
