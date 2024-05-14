package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.MasterView;
import com.qqhouse.dungeon18plus.view.SummaryView;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class MasterDialog extends AssetDialog {

    private MasterView master;

    public MasterDialog(Assets assets, Viewport viewport) {
        super(assets);

        setModal(true);

        master = new MasterView(assets, viewport);
        master.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

        setCustomView(master);
    }

    public void reset(Veteran veteran, ArrayList<EquipmentMastery> backpack, QQPressListener listener) {
        master.reset(veteran, backpack, listener);
    }
}
