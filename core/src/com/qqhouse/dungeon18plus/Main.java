package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.screen.TitleScreen;
import com.qqhouse.dungeon18plus.screen.SelectHeroScreen;
import com.qqhouse.io.QQSaveGame;
import com.qqhouse.ui.QQGameMachine;
import com.qqhouse.ui.QQScreen;

public class Main extends QQGameMachine implements TitleScreen.TitleCallback {

    public static final int STATE_TITLE       = 0;
    public static final int STATE_SELECT_HERO = 1;
    //public static final int






    @Override
    public void create() {
        initial(G.WIDTH, G.HEIGHT);

        // save game
        savedGame = new SaveGame(G.SAVE_FILE);
        savedGame.load();


        // add states....
        addState(STATE_TITLE, new TitleScreen((SaveGame) savedGame, viewport, this));
        addState(STATE_SELECT_HERO, new SelectHeroScreen(viewport));



        // enter title (default state)
        changeState(STATE_TITLE);

    }

    @Override
    public void onTitle(int titleMenu) {
        if (G.TITLE_DUNGEON == titleMenu ||
            G.TITLE_TOWER == titleMenu ||
            G.TITLE_COLOSSEUM == titleMenu ) {
            // set title....
            changeState(STATE_SELECT_HERO);
        }
    }
}
