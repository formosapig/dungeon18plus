package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.ui.QQScreen;

public class EquipmentCatalogScreen extends QQScreen {
    public EquipmentCatalogScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    @Override
    public void onEnter() {
        // title bar with merchant and equipment count...

        // split line...

        // equipment adapter ....

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
