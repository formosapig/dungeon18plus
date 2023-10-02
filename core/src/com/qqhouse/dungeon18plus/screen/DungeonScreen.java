package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.ui.QQScreen;

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

    @Override
    public void onEnter() {
        // hero view ...


        // event listview ...

        // message view ...

        // action view ...
    }

    @Override
    public void onLeave() {

    }
}
