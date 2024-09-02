package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.PreviewView3;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class GalleryScreen extends QQScreen {

    public interface GalleryCallback {
        void onGalleryAction(int action);
    }

    private final GalleryCallback callback;
    public GalleryScreen(SaveGame saveGame, Viewport viewport, Assets assets, GalleryCallback callback) {
        super(saveGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {

        // group of background.
        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setBackground(assets.getNinePatch("help"));
        group.setSize(Game.Size.WIDTH - 12 - 12, QQView.WRAP_CONTENT);
        group.setPadding(8);
        addChild(group);

        // equipment collect.
        if (0 < savedGame.getEquipmentCount()) {
            // equipment preview ... ?
            PreviewView3 equip = new PreviewView3(assets);
            equip.reset("peddler", "peddler", "equipment_catalog_intro", "special");
            equip.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            equip.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.EQUIPMENT_CATALOG);
                }
            });
            group.addChild(equip);
        }

        // monster collect
        if (0 < savedGame.getMonsterCount()) {
            PreviewView3 monster = new PreviewView3(assets);
            monster.reset("skeleton", "skeleton", "monster_guide_intro", "ordinary");
            monster.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            monster.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.MONSTER_GUIDE);
                }
            });
            group.addChild(monster);
        }

        // leader board.
        if (0 < savedGame.getLeaderboardCount()) {
            PreviewView3 leaderboard = new PreviewView3(assets);
            leaderboard.reset("novice", "veteran", "leaderboard_intro", "lawful");
            leaderboard.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            leaderboard.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.DUNGEON_LEADERBOARD);
                }
            });
            group.addChild(leaderboard);
        }

        // barrack.
        if (0 < savedGame.getBarrackCount()) {
            PreviewView3 barrack = new PreviewView3(assets);
            barrack.reset("crusader", "crusader", "barrack_intro", "lawful");
            barrack.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            barrack.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.WILDERNESS_BARRACK);
                }
            });
            group.addChild(barrack);
        }

        // hero album, must have.
        PreviewView3 heroAlbum = new PreviewView3(assets);
        heroAlbum.reset("fairy", "fairy", "hero_album_intro", "neutral");
        heroAlbum.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        heroAlbum.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                if (null != callback)
                    callback.onGalleryAction(Game.GalleryAction.HERO_ALBUM);
            }
        });
        group.addChild(heroAlbum);


        // giant album
        if (savedGame.isOpenGiantAlbum()) {
            PreviewView3 giantAlbum = new PreviewView3(assets);
            giantAlbum.reset("valkyrie", "valkyrie", "giant_album_intro", "neutral");
            giantAlbum.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            giantAlbum.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.GIANT_ALBUM);
                }
            });
            group.addChild(giantAlbum);
        }

        // group set size
        group.setPosition(12, (Game.Size.HEIGHT - group.getHeight()) / 2);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
