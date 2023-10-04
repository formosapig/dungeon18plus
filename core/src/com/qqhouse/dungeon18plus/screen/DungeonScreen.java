package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.io.Assets;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class DungeonScreen extends QQScreen {

    public interface DungeonCallback {
        void onDungeonResult();
    }

    private DungeonManager manager;

    public void setHero(HeroClass hero) {
        manager = new DungeonManager(hero, savedGame);
    }

    public DungeonScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private BitmapFont fntDigital, fntSmallDigital;

    @Override
    public void onEnter() {

        // font
        //String fntName = "Sono-SemiBold.ttf";
        String fntName = "Sono-Bold.ttf";
        //String fntName = "Sono-ExtraBold.ttf";
        String fntName1 = "whitrabt.ttf";
        String fntName2 = "ConsolaMono-Bold.ttf";
        String fntName3 = "F25_Bank_Printer.ttf";
        fntDigital = createFont(fntName1, 16, Color.WHITE, "");
        fntSmallDigital = createFont(fntName1, 16, Color.WHITE, "");

        //fntDigital.setFixedWidthGlyphs("0123456789");

        // hero view ...
        HeroView heroView = new HeroView(manager.getHero().heroClass.alignment.key);
        heroView.preset(
                assets.getBlockee(manager.getHero().heroClass.key), // hero
                assets.getIcon16("life"), // life
                assets.getIcon16("attack"), // attack
                assets.getIcon16("defense"), // defense
                assets.getIcon16("speed"), // speed
                assets.getItem("key"), // key
                assets.getItem("copper_coin"), // coin
                assets.getItem("star") // star
        );
        heroView.setFont(fntDigital, fntSmallDigital);
        heroView.setPadding(8);
        heroView.setPosition(4, G.HEIGHT - 64 - 4);
        //heroView.setSize(QQView.FILL_PARENT, 64);
        heroView.setSize(G.WIDTH - 4 - 4, 64);
        heroView.setData(manager.getHero());
        addView(heroView);

        // event listview ...








        // message view ...

        // action view ...
    }

    @Override
    public void onLeave() {

    }
}
