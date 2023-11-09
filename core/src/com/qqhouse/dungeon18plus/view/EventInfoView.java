package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class EventInfoView extends AssetGroup {

    private Texture Blockee;
    private QQText name;
    private QQText help;
    private QQIconText exp;
    private ItemView loot;
    private QQText lootName;
    private QQText lootHelp;
    private AbilityView lootUpgrade;

    private PurePreviewView preview;

    public EventInfoView(Assets assets) {
        super(assets, QQGroup.DIRECT_VERTICAL);
        setPadding(Game.Size.BLOCKEE_PADDING);
        //setBackground(assets.getNinePatchBG("dialog"));

        preview = new PurePreviewView(assets);
        preview.reset();
        preview.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        addChild(preview);
    }

    public void update(Event event) {
        preview.update(event.type.icon, event.type.align.key);

    }

}
