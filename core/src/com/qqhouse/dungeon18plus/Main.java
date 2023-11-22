package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.screen.DungeonScreen;
import com.qqhouse.dungeon18plus.screen.TitleScreen;
import com.qqhouse.dungeon18plus.screen.SelectHeroScreen;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQGameMachine;

import java.util.ArrayList;

public class Main extends QQGameMachine implements
        TitleScreen.TitleCallback,
        SelectHeroScreen.SelectHeroCallback,
        DungeonScreen.DungeonCallback {

    public static final int STATE_TITLE       = 0;
    public static final int STATE_SELECT_HERO = 1;
    //public static final int

    // state machine 有用嗎, 目前還沒有想到只有 state 而沒有 screen 的情況

    private TitleScreen title;
    private SelectHeroScreen selectHero;
    private DungeonScreen dungeon;
    private Assets assets;




    @Override
    public void create() {
        //Gdx.app.error("Main", "Main.create()");
        initial(Game.Size.WIDTH, Game.Size.HEIGHT);

        // save game
        savedGame = new SaveGame(Game.SAVE_FILE);
        savedGame.load();

        //Gdx.app.error("Main.java", "start load assert@" + TimeUtils.millis());
        assets = new Assets();
        //assets.manager.finishLoading();
        //Gdx.app.error("Main.java", "finish load assert@" + TimeUtils.millis());

        // title screen first
        title = new TitleScreen((SaveGame) savedGame, viewport, assets, this);

        changeScreen(title);

        // add states....
        //addState(STATE_TITLE, new TitleScreen((SaveGame) savedGame, viewport, this));
        //addState(STATE_SELECT_HERO, new SelectHeroScreen(viewport));

        // enter title (default state)
        //changeState(STATE_TITLE);
        //Gdx.app.error("Main", "Main.create() end.");
    }

    @Override
    public void onTitle(int gameMode) {
        switch(gameMode) {
            // need select hero first
            case Game.Mode.DUNGEON:
            case Game.Mode.TOWER:
            case Game.Mode.COLOSSEUM: {
                if (null == selectHero) {
                    selectHero = new SelectHeroScreen((SaveGame) savedGame, viewport, assets, this);
                }
                selectHero.setGameMode(gameMode);
                changeScreen(selectHero);
            }
                break;
            case Game.Mode.WILDERNESS:
                break;
            case Game.Mode.CASTLE: {
                int a = 0;
            }
                break;
            case Game.Mode.LIBRARY: {
                float a = 0.0f;
            }
                break;
            default:
                throw new GdxRuntimeException("invalid game mode : " + gameMode);
        }
    }

    @Override
    public void onSelectHero(int gameMode, HeroClass hero) {
        switch (gameMode) {
            case Game.Mode.DUNGEON: {
                if (null == dungeon) {
                    dungeon = new DungeonScreen((SaveGame) savedGame, viewport, assets, this);
                }
                dungeon.setHero(hero);
                changeScreen(dungeon);
            }
                break;
            default:
                throw new GdxRuntimeException("Unsupported game mode." + gameMode);
        }
    }

    @Override
    public void onDungeonResult(boolean isWin, ArrayList<BossKill> kills) {
        //Gdx.app.error("Main", "on dungeon result.");
        changeScreen(title);


    }
}
