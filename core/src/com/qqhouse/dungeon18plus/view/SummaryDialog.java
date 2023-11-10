package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Camera;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SummaryDialog extends AssetDialog {

    private SummaryView summary;

    public SummaryDialog(Assets assets, Camera camera) {
        super(assets);

        setModal(true);

        summary = new SummaryView(assets, camera);
        summary.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

        setCustomView(summary);
    }

    public void reset(ArrayList<BossKill> killList, boolean isWin, QQPressListener listener) {
        summary.reset(killList, isWin, listener);
    }
}
