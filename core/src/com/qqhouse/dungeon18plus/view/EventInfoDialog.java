package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQCustomDialog;
import com.qqhouse.ui.QQView;

public class EventInfoDialog extends CustomDialog {
    private EventInfoView evtInfo;

    public EventInfoDialog(Assets assets) {
        super(assets);

        setModal(false);

        evtInfo = new EventInfoView(assets);
        evtInfo.setBackground(assets.getNinePatchBG("help"));
        evtInfo.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //evtInfo.update(manager.getEvent(index));

        setCustomView(evtInfo);
    }

    public void update(Event event) {
        evtInfo.update(event);
    }
}
