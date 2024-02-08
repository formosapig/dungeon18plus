package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.TimeUtils;
import com.qqhouse.ui.QQButton;

import java.sql.Time;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Assets {

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

    public Texture getBlockee(String key) {
        String fileName = "blockee/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getBlockee(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon16(String key) {
        String fileName = "icon16/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getIcon16(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getIcon32(String key) {
        String fileName = "icon32/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getIcon32(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getItem(String key) {
        String fileName = "item/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getItem(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public Texture getBackground(String key) {
        String fileName = "background/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getBackground(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public NinePatch getNinePatchBG(String key) {
        long t = TimeUtils.millis();
        NinePatch np = new NinePatch(getBackground(key), 4, 4, 4, 4);
        //Gdx.app.error("Assets", String.format(Locale.US, "getNinePatchBG(%S) : %d", key, (TimeUtils.millis() - t)));

        return np;//new NinePatch(getBackground(key), 4, 4, 4, 4);
    }

    // public BitmapFont getFont(String fontName, int fontSize) {
    //    String fileName = String.format("%s%d.ttf", fontName, fontSize);
    //    if (!manager.contains(fileName)) {
    //        FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    //        param.fontFileName = "font/" + fontName + ".ttf";
    //        param.fontParameters.size = fontSize;
    //        param.fontParameters.minFilter = Texture.TextureFilter.Linear;
    //        param.fontParameters.magFilter = Texture.TextureFilter.Linear;
    //        param.fontParameters.characters += "";
    //        manager.load(fileName, BitmapFont.class, param);
    //        manager.finishLoadingAsset(fileName);
    //        // fixed size ?! white rabbit 的 1 沒有 fixed width ... 導致浪費效能...
    //        BitmapFont font = manager.get(fileName, BitmapFont.class);
    //        font.setFixedWidthGlyphs(FreeTypeFontGenerator.DEFAULT_CHARS);
    //    }
    //    return manager.get(fileName, BitmapFont.class);
    //}




    public void dispose() {
        manager.dispose();
    }

    public String geti18n(String key) {
        if (null == key || "" == key)
            return "";
        if (null == langBundle) {
            long t = TimeUtils.millis();
            manager.load("i18n/dungeon18plus", I18NBundle.class);
            manager.finishLoading();
            langBundle = manager.get("i18n/dungeon18plus", I18NBundle.class);
            //Gdx.app.error("Assets", String.format(Locale.US, "geti18n(%S) : %d", key, (TimeUtils.millis() - t)));
        }
        return langBundle.get(key);
    }

    public String formati18n(String key, Object... args) {
        if (null == langBundle) {
            long t = TimeUtils.millis();
            manager.load("i18n/dungeon18plus", I18NBundle.class);
            manager.finishLoading();
            langBundle = manager.get("i18n/dungeon18plus", I18NBundle.class);
            //Gdx.app.error("Assets", String.format(Locale.US, "formati18n(%S) : %d", key, (TimeUtils.millis() - t)));

        }
        return langBundle.format(key, args);
    }

    public Texture getTexture(String folder, String key) {
        String fileName = folder + "/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getTexture(%s/%S) : %d",folder, key, (TimeUtils.millis() - t)));
        }
        return manager.get(fileName, Texture.class);
    }

    public BitmapFont getFont(FontSet set) {
        return getFont(set.name, set.size);
    }

    public static final class FontSet {
        public String name;
        public int size;
        public FontSet(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }

    private BitmapFont getFont(String fontName, int fontSize) {
        String fileName = String.format("%s%d.ttf", fontName, fontSize);
        if (!manager.contains(fileName)) {
            long t = TimeUtils.millis();
            FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
            param.fontFileName = "font/" + fontName + ".ttf";
            param.fontParameters.size = fontSize;
            param.fontParameters.minFilter = Texture.TextureFilter.Linear;
            param.fontParameters.magFilter = Texture.TextureFilter.Linear;
            param.fontParameters.characters += "";
            manager.load(fileName, BitmapFont.class, param);
            manager.finishLoadingAsset(fileName);
            // fixed size ?! white rabbit 的 1 沒有 fixed width ... 導致浪費效能...
            //BitmapFont font = manager.get(fileName, BitmapFont.class);
            //font.setFixedWidthGlyphs(FreeTypeFontGenerator.DEFAULT_CHARS);
            //Gdx.app.error("Assets", String.format(Locale.US, "getFont(%S) : %d", fontName, (TimeUtils.millis() - t)));

        }
        return manager.get(fileName, BitmapFont.class);
    }

    /*
        button background set series
     */
    private Map<String, QQButton.BackgroundSet> buttonBackgroundSets = new HashMap<>();

    private Texture getButton(String key) {
        String fileName = "button/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            long t = TimeUtils.millis();
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
            //Gdx.app.error("Assets", String.format(Locale.US, "getButton(%S) : %d", key, (TimeUtils.millis() - t)));

        }
        return manager.get(fileName, Texture.class);
    }

    public QQButton.BackgroundSet getBackgroundSet(String key) {
        if (!buttonBackgroundSets.containsKey(key)) {
            long t = TimeUtils.millis();
            QQButton.BackgroundSet set = new QQButton.BackgroundSet();
            set.normal = new NinePatch(getButton(key + "_up"), 4, 4, 4, 4);
            set.pressed = new NinePatch(getButton(key + "_down"), 4, 4, 4, 4);
            set.disable = new NinePatch(getButton("disable"), 4, 4, 4, 4);
            buttonBackgroundSets.put(key, set);
            //Gdx.app.error("Assets", String.format(Locale.US, "getBackgroundSet(%S) : %d", key, (TimeUtils.millis() - t)));

        }
        return buttonBackgroundSets.get(key);
    }

}
