package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.WildernessManager;
import com.qqhouse.dungeon18plus.dialog.EventInfoDialog;
import com.qqhouse.dungeon18plus.dialog.SummaryDialog;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.view.ActionView;
import com.qqhouse.dungeon18plus.view.EventInfoView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class WildernessScreen extends QQScreen {

    private WildernessManager manager;

    public void setGiantRace(GiantRace race) {
        manager = new WildernessManager(race, savedGame);
    }

    public WildernessScreen(SaveGame savedGame, Viewport viewport, Assets assets, PopupScreen callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    private PopupScreen callback;

    @Override
    public void onEnter() {




    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
