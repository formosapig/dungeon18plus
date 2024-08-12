package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.SelectEquipmentView;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectEquipmentDialog extends AssetDialog {

    private final SelectEquipmentView selectEquipment;

    public SelectEquipmentDialog(Assets assets, Viewport viewport) {
        super(assets);

        setModal(true);

        selectEquipment = new SelectEquipmentView(assets, viewport);
        selectEquipment.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

        setCustomView(selectEquipment);
    }

    public void reset(Veteran veteran, ArrayList<EquipmentMastery> backpack, SelectEquipmentView.SelectEquipmentCallback callback) {
        selectEquipment.reset(veteran, backpack, callback);
    }
}
