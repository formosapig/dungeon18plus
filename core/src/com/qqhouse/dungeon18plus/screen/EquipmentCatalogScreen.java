package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class EquipmentCatalogScreen extends QQScreen {
    public EquipmentCatalogScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    @Override
    public void onEnter() {
        // title bar with merchant and equipment count...
        TitleBarView2 merchant = new TitleBarView2(assets);
        merchant.reset("merchant", "merchant", null, Game.Colour.COUNT, "10");
        merchant.setSize(Game.Size.WIDTH, 48);
        merchant.setPosition(0, Game.Size.HEIGHT - 48);
        merchant.setPadding(8);
        merchant.setBackground(assets.getNinePatchBG("special"));
        addChild(merchant);


        // split line...

        // equipment adapter ....

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
