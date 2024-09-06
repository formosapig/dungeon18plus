package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.core.ColosseumManager;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.purchase.IAB;
import com.qqhouse.dungeon18plus.screen.BarrackScreen;
import com.qqhouse.dungeon18plus.screen.ColosseumScreen;
import com.qqhouse.dungeon18plus.screen.DungeonScreen;
import com.qqhouse.dungeon18plus.screen.EquipmentCatalogScreen;
import com.qqhouse.dungeon18plus.screen.GalleryScreen;
import com.qqhouse.dungeon18plus.screen.GiantAlbumScreen;
import com.qqhouse.dungeon18plus.screen.HeroAlbumScreen;
import com.qqhouse.dungeon18plus.screen.LeaderboardScreen;
import com.qqhouse.dungeon18plus.screen.LegionTrainerScreen;
import com.qqhouse.dungeon18plus.screen.MonsterGuideScreen;
import com.qqhouse.dungeon18plus.screen.PopupScreen;
import com.qqhouse.dungeon18plus.screen.SelectGiantScreen;
import com.qqhouse.dungeon18plus.screen.SoulMasterScreen;
import com.qqhouse.dungeon18plus.screen.TitleScreen;
import com.qqhouse.dungeon18plus.screen.SelectHeroScreen;
import com.qqhouse.dungeon18plus.screen.WildernessScreen;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQGameMachine;

import java.util.ArrayList;

public class Main extends QQGameMachine implements
        TitleScreen.TitleCallback,
        SelectHeroScreen.SelectHeroCallback,
        SelectGiantScreen.SelectGiantCallback,
        ColosseumScreen.ColosseumCallback,
        GalleryScreen.GalleryCallback,
        PopupScreen {

    // purchase
    public PurchaseManager purchaseManager;

    private TitleScreen title;
    private SelectHeroScreen selectHero;
    private SelectGiantScreen selectGiant;
    private DungeonScreen dungeon;
    private ColosseumScreen colosseum;
    private GalleryScreen gallery;
    private EquipmentCatalogScreen equipmentCatalog;
    private MonsterGuideScreen monsterGuide;
    private LeaderboardScreen leaderboard;
    private BarrackScreen barrack;
    private HeroAlbumScreen heroAlbum;
    private GiantAlbumScreen giantAlbum;
    private LegionTrainerScreen legionTrainer;
    private SoulMasterScreen soulMaster;
    private WildernessScreen wilderness;
    private Assets assets;

    @Override
    public void create() {
        //Game.checkAllEnum();
        //Game.checkUniqueSkill();
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
        //Gdx.app.error("Main.java", "SafeInsetTop : " + Gdx.graphics.getSafeInsetTop());
        //Gdx.app.error("Main.java", "SafeInsetBottom : " + Gdx.graphics.getSafeInsetBottom());
        //Gdx.app.error("Main.java", "SafeInsetLeft : " + Gdx.graphics.getSafeInsetLeft());
        //Gdx.app.error("Main.java", "SafeInsetRight : " + Gdx.graphics.getSafeInsetRight());

        //Gdx.app.error("Main.java", "before enter initialPurchaseManager()");
        initialPurchaseManager();
    }

    private void initialPurchaseManager() {

        PurchaseManagerConfig pmc = new PurchaseManagerConfig();
        //pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(IAB.PREMIUM.sku));
        //pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(IAB.FAIRY.sku));
        //pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(IAB.FIRE_KNIGHT.sku));
        //pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(IAB.WATER_KNIGHT.sku));

        //purchaseManager.install(new MyPurchaseObserver(), pmc, true);
    }

    private class MyPurchaseObserver implements PurchaseObserver {

        private boolean restorePressed;

        @Override
        public void handleInstall() {
            Gdx.app.log("IAP", "Installed");

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    //updateGuiWhenPurchaseManInstalled(null);
                    Information skuInfo = purchaseManager.getInformation(IAB.PREMIUM.sku);
                    //skuInfo.

                }
            });
        }

        @Override
        public void handleInstallError(final Throwable e) {
            Gdx.app.error("IAP", "Error when trying to install PurchaseManager", e);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    //updateGuiWhenPurchaseManInstalled(e.getMessage());
                }
            });
        }

        @Override
        public void handleRestore(final Transaction[] transactions) {
            if (transactions != null && transactions.length > 0)
                for (Transaction t : transactions) {
                    handlePurchase(t, true);
                }
            else if (restorePressed) {
                showErrorOnMainThread("Nothing to restore");
            }
        }

        @Override
        public void handleRestoreError(Throwable e) {
            if (restorePressed)
                showErrorOnMainThread("Error restoring purchases: " + e.getMessage());
        }

        @Override
        public void handlePurchase(final Transaction transaction) {
            handlePurchase(transaction, false);
        }

        protected void handlePurchase(final Transaction transaction, final boolean fromRestore) {
            Gdx.app.postRunnable(() -> {
                if (transaction.isPurchased()) {
                    for (IAB item : IAB.values()) {
                        if (transaction.getIdentifier().equals(item.sku)) {
                            //this.flag |= item.flag;
                        }
                    }
                }
            });
        }

        @Override
        public void handlePurchaseError(Throwable e) {
            showErrorOnMainThread("Error on buying:\n" + e.getMessage());
        }

        @Override
        public void handlePurchaseCanceled() {

        }

        private void showErrorOnMainThread(final String message) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    // show a dialog here...
                }
            });
        }
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
            case Game.Mode.WILDERNESS: {
                if (null == selectGiant) {
                    selectGiant = new SelectGiantScreen((SaveGame) savedGame, viewport, assets, this);
                }
                push(selectGiant);
            }
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
    public void onColosseumResult(Veteran veteran) {
        if (null == veteran) {
            popup();
        } else {
            popup();
            if (null == barrack) {
                barrack = new BarrackScreen((SaveGame) savedGame, viewport, assets, this);
            }
            barrack.setVeteran(veteran);
            push(barrack);
        }
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
            case Game.GalleryAction.DUNGEON_LEADERBOARD: {
                if (null == leaderboard) {
                    leaderboard = new LeaderboardScreen((SaveGame) savedGame, viewport, assets);
                }
                push(leaderboard);
            }
                break;
            case Game.GalleryAction.WILDERNESS_BARRACK: {
                if (null == barrack) {
                    barrack = new BarrackScreen((SaveGame) savedGame, viewport, assets, this);
                }
                push(barrack);
            }
                break;
            case Game.GalleryAction.HERO_ALBUM:
                if (null == heroAlbum) {
                    heroAlbum = new HeroAlbumScreen((SaveGame) savedGame, viewport, assets);
                }
                push(heroAlbum);
                break;
            case Game.GalleryAction.GIANT_ALBUM:
                if (null == giantAlbum) {
                    giantAlbum = new GiantAlbumScreen((SaveGame) savedGame, viewport, assets);
                }
                push(giantAlbum);
                break;
            default:
                throw new GdxRuntimeException("Gallery action doesn't exist. : " + action);
        }
    }

    @Override
    public void onPopupScreen() {
        popup();
    }

    /*
     * select giant call back.
     */
    @Override
    public void onLegionTrainer() {
        if (null == legionTrainer) {
            legionTrainer = new LegionTrainerScreen((SaveGame) savedGame, viewport, assets);
        }
        push(legionTrainer);
    }

    @Override
    public void onSoulMaster() {
        if (null == soulMaster) {
            soulMaster = new SoulMasterScreen((SaveGame) savedGame, viewport, assets);
        }
        push(soulMaster);
    }

    @Override
    public void onSelectGiant(GiantRace giant) {
        if (null == wilderness) {
            wilderness = new WildernessScreen((SaveGame) savedGame, viewport, assets, this);
        }
        wilderness.setGiantRace(giant);
        push(wilderness);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (null != purchaseManager)
            purchaseManager.dispose();
    }
}
