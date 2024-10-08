package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SummaryDialog extends AssetDialog {

    private final SummaryView summary;

    public SummaryDialog(Assets assets, Viewport viewport) {
        super(assets);

        setModal(true);

        summary = new SummaryView(assets, viewport);
        summary.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

        setCustomView(summary);
    }

    public void reset(ArrayList<BossKill> killList, boolean isWin, QQButton.BackgroundSet bgSet, QQPressListener listener) {
        summary.reset(killList, isWin, bgSet, listener);
    }
}
