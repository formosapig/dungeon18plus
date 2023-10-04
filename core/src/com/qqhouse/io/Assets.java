package com.qqhouse.io;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

public class Assets {
    public AssetManager manager = new AssetManager();

    private I18NBundle langBundle;

    /*
        get series
     */
    public String geti18n(String key) {
        if (null == langBundle) {
            manager.load("i18n/dungeon18plus", I18NBundle.class);
            manager.finishLoading();
            langBundle = manager.get("i18n/dungeon18plus", I18NBundle.class);
        }
        return langBundle.get(key);
    }

    public Texture getBlockee(String key) {
        String fileName = "blockee/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon16(String key) {
        String fileName = "drawable/icon16_" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon32(String key) {
        String fileName = "drawable/icon32_" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getItem(String key) {
        String fileName = "drawable/item_" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }



    public void dispose() {
        manager.dispose();
    }

}
