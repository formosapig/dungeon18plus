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

    public DungeonScreen(SaveGame savedGame, Viewport viewport) {
        super(savedGame, viewport);
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
                new Texture(Gdx.files.internal("blockee\\" + manager.getHero().heroClass.key + ".png")), // hero
                new Texture(Gdx.files.internal("drawable/icon16_life.png")), // life
                new Texture(Gdx.files.internal("drawable/icon16_attack.png")), // attack
                new Texture(Gdx.files.internal("drawable/icon16_defense.png")), // defense
                new Texture(Gdx.files.internal("drawable/icon16_speed.png")), // speed
                new Texture(Gdx.files.internal("drawable/item_key.png")), // key
                new Texture(Gdx.files.internal("drawable/item_copper_coin.png")), // coin
                new Texture(Gdx.files.internal("drawable/item_star.png")) // star
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
