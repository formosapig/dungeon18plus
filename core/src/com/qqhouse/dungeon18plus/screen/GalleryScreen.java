package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.PreviewView2;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQGroup2;
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
        QQGroup2 group = new QQGroup2(QQGroup.DIRECT_VERTICAL, Game.Size.WIDGET_MARGIN);
        group.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        group.setSize(Game.Size.WIDTH - 12 - 12, QQView.WRAP_CONTENT);//Game.Size.HEIGHT * 0.9f);
        //group.setPosition(12, Game.Size.HEIGHT * 0.05f);
        group.setPadding(8);
        addChild(group);

        // equipment collect.
        if (0 < savedGame.getEquipmentCount()) {
            // equipment preview ... ?
            PreviewView2 tmp = new PreviewView2(assets);
            tmp.reset("merchant", "merchant", "equipment_catalog_help", "special");
            tmp.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            tmp.addPressListener(new QQView.PressAdapter() {
                @Override
                public void onPress(QQView view) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.EQUIPMENT_CATALOG);
                }
            });
            group.addChild(tmp);
        }

        // monster collect
        if (0 < savedGame.getMonsterCount()) {
            PreviewView2 tmp = new PreviewView2(assets);
            tmp.reset("skeleton", "skeleton", "monster_guide_help", "ordinary");
            tmp.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            tmp.addPressListener(new QQView.PressAdapter() {
                @Override
                public void onPress(QQView view) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.MONSTER_GUIDE);
                }
            });
            group.addChild(tmp);
        }

        // leader board.
        if (0 < savedGame.getLeaderboardCount()) {
            PreviewView2 tmp = new PreviewView2(assets);
            tmp.reset("novice", "old_hero", "leaderboard_help", "lawful");
            tmp.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            tmp.addPressListener(new QQView.PressAdapter() {
                @Override
                public void onPress(QQView view) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.DUNGEON_LEADERBOARD);
                }
            });
            group.addChild(tmp);
        }

        // barrack.
        if (0 < savedGame.getBarrackCount()) {
            PreviewView2 tmp = new PreviewView2(assets);
            tmp.reset("crusader", "crusader", "barrack_help", "lawful");
            tmp.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            tmp.addPressListener(new QQView.PressAdapter() {
                @Override
                public void onPress(QQView view) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.WILDERNESS_BARRACK);
                }
            });
            group.addChild(tmp);
        }

        // hero album, must have.
        PreviewView2 tmp2 = new PreviewView2(assets);
        tmp2.reset("fairy", "fairy", "hero_album_help", "neutral");
        tmp2.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        tmp2.addPressListener(new QQView.PressAdapter() {
            @Override
            public void onPress(QQView view) {
                if (null != callback)
                    callback.onGalleryAction(Game.GalleryAction.HERO_ALBUM);
            }
        });
        group.addChild(tmp2);


        // giant album
        if (savedGame.isOpenGiantAlbum()) {
            PreviewView2 tmp = new PreviewView2(assets);
            tmp.reset("valkyrie", "valkyrie", "giant_album_help", "neutral");
            tmp.setSize(QQView.MATCH_PARENT, 64);
            tmp.addPressListener(new QQView.PressAdapter() {
                @Override
                public void onPress(QQView view) {
                    if (null != callback)
                        callback.onGalleryAction(Game.GalleryAction.GIANT_ALBUM);
                }
            });
            group.addChild(tmp);
        }

        // group set size
        group.setPosition(12, (Game.Size.HEIGHT - group.getHeight()) / 2);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
