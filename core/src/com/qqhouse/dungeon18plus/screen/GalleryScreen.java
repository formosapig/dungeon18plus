package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.PreviewView2;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class GalleryScreen extends QQScreen {

    public interface GalleryCallback {
        void onGalleryAction(int action);
    }

    private GalleryCallback callback;
    public GalleryScreen(SaveGame saveGame, Viewport viewport, Assets assets, GalleryCallback callback) {
        super(saveGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {

        // group of background.
        QQGroup group = new QQGroup(QQGroup.DIRECT_VERTICAL, Game.Size.WIDGET_MARGIN);
        group.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        group.setSize(Game.Size.WIDTH - 12 - 12, QQView.WRAP_CONTENT);//Game.Size.HEIGHT * 0.9f);
        //group.setPosition(12, Game.Size.HEIGHT * 0.05f);
        group.setPadding(8);
        addChild(group);

        // equipment collect.
        if (0 < savedGame.getEquipmentCount()) {
            // equipment preview ... ?
            PreviewView2 test = new PreviewView2(assets);
            test.reset("merchant", "merchant", "equipment_catalog_help", "special");
            test.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            group.addChild(test);
        }

        // monster collect
        if (0 < savedGame.getMonsterCount()) {
            PreviewView2 test = new PreviewView2(assets);
            test.reset("skeleton", "skeleton", "monster_guide_help", "ordinary");
            test.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            group.addChild(test);
        }

        // leader board.
        if (0 < savedGame.getLeaderboardCount()) {
            PreviewView2 test = new PreviewView2(assets);
            test.reset("novice", "old_hero", "leaderboard_help", "lawful");
            test.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            group.addChild(test);
        }

        // barrack.
        if (0 < savedGame.getBarrackCount()) {
            PreviewView2 test = new PreviewView2(assets);
            test.reset("crusader", "crusader", "barrack_help", "lawful");
            test.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            group.addChild(test);
        }

        // hero album, must have.
        PreviewView2 test1 = new PreviewView2(assets);
        test1.reset("fairy", "fairy", "hero_album_help", "neutral");
        test1.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        group.addChild(test1);


        // giant album
        if (savedGame.isOpenGiantAlbum()) {
            PreviewView2 test = new PreviewView2(assets);
            test.reset("valkyrie", "valkyrie", "giant_album_help", "neutral");
            test.setSize(QQView.MATCH_PARENT, 64);
            group.addChild(test);
        }

        // group set size
        group.setPosition(12, (Game.Size.HEIGHT - group.getHeight()) / 2);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
