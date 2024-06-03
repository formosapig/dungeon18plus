package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class EventInfoView extends AssetGroup {

    private ItemDetailView item;
    private PurePreviewView preview;

    public EventInfoView(Assets assets) {
        super(assets);
        setPadding(Game.Size.BLOCKEE_PADDING);
        //setBackground(assets.getNinePatchBG("dialog"));

        item = new ItemDetailView(assets);
        item.reset();
        item.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        addChild(item);


        preview = new PurePreviewView(assets);
        preview.reset();
        preview.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        addChild(preview);
    }

    public void update(Event event) {
        item.setVisible(event.loot != Item.NONE);
        if (Item.NONE != event.loot ) {
 //           if (event.type == EventType.DOOR || event.type.isMonster())
            item.update(event.loot, (event.type == EventType.DOOR || event.type.isMonster()) && event.loot.isEquipment());
        }
        preview.update(event.type.icon, event.type.align.key);

        if (null != parent)
            parent.onChildSizeChanged(this);
    }

}
