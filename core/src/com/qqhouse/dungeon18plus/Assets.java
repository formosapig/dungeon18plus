package com.qqhouse.dungeon18plus;

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
import com.qqhouse.ui.QQButton;

import java.util.HashMap;
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

    public NinePatch getNinePatchBG(String key) {
        return new NinePatch(getBackground(key), 4, 4, 4, 4);
    }
    //public BitmapFont getFont(String fontName, int fontSize) {
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
    //        // fixed size ?! FIXME white rabbit 的 1 沒有 fixed width ... 導致浪費效能...
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
            manager.load("i18n/dungeon18plus", I18NBundle.class);
            manager.finishLoading();
            langBundle = manager.get("i18n/dungeon18plus", I18NBundle.class);
        }
        return langBundle.get(key);
    }

    public String formati18n(String key, Object... args) {
        if (null == langBundle) {
            manager.load("i18n/dungeon18plus", I18NBundle.class);
            manager.finishLoading();
            langBundle = manager.get("i18n/dungeon18plus", I18NBundle.class);
        }
        return langBundle.format(key, args);
    }

    public Texture getTexture(String folder, String key) {
        String fileName = folder + "/" + key + ".png";
        if (!manager.contains(fileName, Texture.class)) {
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
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
            FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
            param.fontFileName = "font/" + fontName + ".ttf";
            param.fontParameters.size = fontSize;
            param.fontParameters.minFilter = Texture.TextureFilter.Linear;
            param.fontParameters.magFilter = Texture.TextureFilter.Linear;
            param.fontParameters.characters += "";
            manager.load(fileName, BitmapFont.class, param);
            manager.finishLoadingAsset(fileName);
            // fixed size ?! FIXME white rabbit 的 1 沒有 fixed width ... 導致浪費效能...
            //BitmapFont font = manager.get(fileName, BitmapFont.class);
            //font.setFixedWidthGlyphs(FreeTypeFontGenerator.DEFAULT_CHARS);
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
            manager.load(fileName, Texture.class, ttParam);
            manager.finishLoadingAsset(fileName);
        }
        return manager.get(fileName, Texture.class);
    }

    public QQButton.BackgroundSet getBackgroundSet(String key) {
        if (!buttonBackgroundSets.containsKey(key)) {
            QQButton.BackgroundSet set = new QQButton.BackgroundSet();
            set.normal = new NinePatch(getButton(key + "_up"), 4, 4, 4, 4);
            set.pressed = new NinePatch(getButton(key + "_down"), 4, 4, 4, 4);
            set.disable = new NinePatch(getButton("disable"), 4, 4, 4, 4);
            buttonBackgroundSets.put(key, set);
        }
        return buttonBackgroundSets.get(key);
    }

}
