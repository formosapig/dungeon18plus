package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.core.ColosseumManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.screen.ColosseumScreen;
import com.qqhouse.dungeon18plus.screen.DungeonScreen;
import com.qqhouse.dungeon18plus.screen.EquipmentCatalogScreen;
import com.qqhouse.dungeon18plus.screen.GalleryScreen;
import com.qqhouse.dungeon18plus.screen.LeaderboardScreen;
import com.qqhouse.dungeon18plus.screen.MonsterGuideScreen;
import com.qqhouse.dungeon18plus.screen.TitleScreen;
import com.qqhouse.dungeon18plus.screen.SelectHeroScreen;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQGameMachine;

import java.util.ArrayList;

public class Main extends QQGameMachine implements
        TitleScreen.TitleCallback,
        SelectHeroScreen.SelectHeroCallback,
        DungeonScreen.DungeonCallback,
        ColosseumScreen.ColosseumCallback,
        GalleryScreen.GalleryCallback {

    public static final int STATE_TITLE       = 0;
    public static final int STATE_SELECT_HERO = 1;
    //public static final int

    // state machine 有用嗎, 目前還沒有想到只有 state 而沒有 screen 的情況

    private TitleScreen title;
    private SelectHeroScreen selectHero;
    private DungeonScreen dungeon;
    private ColosseumScreen colosseum;
    private GalleryScreen gallery;
    private EquipmentCatalogScreen equipmentCatalog;
    private MonsterGuideScreen monsterGuide;
    private LeaderboardScreen leaderboard;
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

        setRoot(title);
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
                push(selectHero);
            }
                break;
            case Game.Mode.WILDERNESS:
                break;
            case Game.Mode.CASTLE: {
                int a = 0;
            }
                break;
            case Game.Mode.GALLERY: {
                if (null == gallery) {
                    gallery = new GalleryScreen((SaveGame)savedGame, viewport, assets, this);
                }
                push(gallery);
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
                // go from select hero, pop select hero first then push dungeon...
                swap(dungeon);
            }
                break;
            case Game.Mode.COLOSSEUM: {
                if (null == colosseum) {
                    colosseum = new ColosseumScreen((SaveGame) savedGame, viewport, assets, this);
                }
                colosseum.setHero(hero);
                // go from select hero, pop select hero first then push dungeon...
                swap(colosseum);
            }
            break;
            default:
                throw new GdxRuntimeException("Unsupported game mode." + gameMode);
        }
    }

    @Override
    public void onDungeonResult(boolean isWin, ArrayList<BossKill> kills) {
        popup();
    }

    @Override
    public void onColosseumResult(boolean isWin, ArrayList<BossKill> kills) {

    }

    @Override
    public void onGalleryAction(int action) {
        switch (action) {
            case Game.GalleryAction.EQUIPMENT_CATALOG: {
                if (null == equipmentCatalog) {
                    equipmentCatalog = new EquipmentCatalogScreen((SaveGame) savedGame, viewport, assets);
                }
                push(equipmentCatalog);
            }
                break;
            case Game.GalleryAction.MONSTER_GUIDE: {
                if (null == monsterGuide) {
                    monsterGuide = new MonsterGuideScreen((SaveGame) savedGame, viewport, assets);
                }
                push(monsterGuide);
            }
                break;
            case Game.GalleryAction.DUNGEON_LEADERBOARD:
                if (null == leaderboard) {
                    leaderboard = new LeaderboardScreen((SaveGame) savedGame, viewport, assets);
                }
                push(leaderboard);
                break;
            case Game.GalleryAction.WILDERNESS_BARRACK:
                break;
            case Game.GalleryAction.HERO_ALBUM:
                break;
            case Game.GalleryAction.GIANT_ALBUM:
                break;
            default:
                throw new GdxRuntimeException("Gallery action doesn't exist. : " + action);
        }
    }
}
