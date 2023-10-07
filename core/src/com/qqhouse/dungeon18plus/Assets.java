package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.qqhouse.ui.QQAssets;

public class Assets extends QQAssets {

    public Assets() {
        manager = new AssetManager();
        ttParam = new TextureLoader.TextureParameter();
        ttParam.minFilter = Texture.TextureFilter.Linear;
        ttParam.magFilter = Texture.TextureFilter.Linear;

        // free type font ...
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    public AssetManager manager = new AssetManager();

    private I18NBundle langBundle;

    // Texture parameter for TextureFilter = linear... make graphic more smooth....
    private TextureLoader.TextureParameter ttParam;


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
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon16(String key) {
        String fileName = "icon16/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon32(String key) {
        String fileName = "icon32/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getItem(String key) {
        String fileName = "item/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getBackground(String key) {
        String fileName = "background/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public BitmapFont getFont(String fontName, int fontSize) {
        String fileName = String.format("%s%d.ttf", fontName, fontSize);
        if (!manager.contains(fileName)) {
            FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
            param.fontFileName = "font/" + fontName + ".ttf";
            param.fontParameters.size = fontSize;
            param.fontParameters.minFilter = Texture.TextureFilter.Linear;
            param.fontParameters.magFilter = Texture.TextureFilter.Linear;
            param.fontParameters.characters += "";
            manager.load(fileName, BitmapFont.class, param);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, BitmapFont.class);
    }

    public void dispose() {
        manager.dispose();
    }

}
