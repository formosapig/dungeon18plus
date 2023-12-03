package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.ui.QQScreen;

public class GalleryScreen extends QQScreen {

    public interface GalleryCallback {
        void onGalleryAction(int action);
    }

    private GalleryCallback callback;
    public GalleryScreen(Viewport viewport, Assets assets, GalleryCallback callback) {
        super(null, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {






    }

    @Override
    public void onLeave() {

    }
}
