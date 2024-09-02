package com.qqhouse.dungeon18plus.dialog;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQView;

public class EventInfoDialog extends AssetDialog {
    private final EventInfoView evtInfo;

    public EventInfoDialog(Assets assets) {
        super(assets);

        setModal(false);

        evtInfo = new EventInfoView(assets);
        evtInfo.setBackground(assets.getNinePatch("help"));
        evtInfo.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //evtInfo.update(manager.getEvent(index));

        setCustomView(evtInfo);
    }

    public void update(Event event) {
        evtInfo.update(event);
    }
}
