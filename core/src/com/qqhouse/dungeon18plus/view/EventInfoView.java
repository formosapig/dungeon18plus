package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQView;

public class EventInfoView extends AssetGroup {

    private ItemDetailView item;
    private PurePreviewView preview;
    private QQLinear container;

    public EventInfoView(Assets assets) {
        super(assets);
        setPadding(Game.Size.BLOCKEE_PADDING);
        //setBackground(assets.getNinePatchBG("dialog"));

        container = new QQLinear(Game.Size.WIDGET_MARGIN);
        container.setSize(MATCH_PARENT, WRAP_CONTENT);
        addChild(container);

        preview = new PurePreviewView(assets);
        preview.reset();
        preview.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        container.addChild(preview);

        item = new ItemDetailView(assets);
        item.reset();
        item.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        container.addChild(item);

    }

    public void update(Event event) {
        item.setVisible(event.loot != Item.NONE);
        if (Item.NONE != event.loot ) {
 //           if (event.type == EventType.DOOR || event.type.isMonster())
            item.update(event.loot, (event.type == EventType.DOOR || event.type.isMonster()) && event.loot.isEquipment());
        }
        preview.update(event.getIcon(), event.type.name, event.type.help, event.type.align.key);

        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    @Override
    public void resetWrapHeight() {
        height = container.getHeight() + topPadding + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    @Override
    public void arrangeChildren() {
        if (null != container)
            container.setPosition(leftPadding, bottomPadding);
    }

}
