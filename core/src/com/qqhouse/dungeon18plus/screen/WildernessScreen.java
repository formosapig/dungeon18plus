package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
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
import com.qqhouse.dungeon18plus.struct.campaign.CampaignAction;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.dungeon18plus.struct.campaign.Campaigner;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.ActionView;
import com.qqhouse.dungeon18plus.view.CampaignActionView;
import com.qqhouse.dungeon18plus.view.CampaignResultView;
import com.qqhouse.dungeon18plus.view.CampaignScoreView;
import com.qqhouse.dungeon18plus.view.EventInfoView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.GiantView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LegionHeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.ui.QQGrid;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Collections;

public class WildernessScreen extends QQScreen {

    private WildernessManager manager;
    private PopupScreen callback;
    private GiantView giant;
    private QQList1 history;
    private float period;

    public WildernessScreen(SaveGame savedGame, Viewport viewport, Assets assets, PopupScreen callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    public void setGiantRace(GiantRace race) {
        manager = new WildernessManager(race, savedGame);
    }

    @Override
    public void onEnter() {

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // giant 96 x 96 giant ...
        giant = new GiantView(assets);
        giant.reset(manager.giants.get(0));
        giant.setSize(QQView.MATCH_PARENT, 128);
        group.addChild(giant);


        // list
        history = new QQList1(getViewport(), Game.Size.WIDGET_MARGIN);
        history.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        history.setAdapter(battleHistoryAdapter);
        group.addChild(history);

        // grid view of all Legion....
        QQGrid grid = new QQGrid(getViewport());
        grid.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        grid.setCamera(getCamera());
        grid.setNumColumns(4);
        grid.setAdapter(legionAdapter);
        grid.setPressListener( new QQGrid.PressListener() {

            @Override
            public void onPress(int index) {
                if (0 == index)
                    manager.start();
                else if (1 == index) {
                    manager.start();
                    while (manager.isBattle()) {
                        manager.tick();
                    }
                    history.updateAll();
                }
                //else {
                //    manager.tick();
                //    history.updateAll();
                //    Gdx.app.error("WildernessScreen", "history size = " + manager.battleHistory.size());
                //}
            }

            @Override
            public void onLongPress(int index) {}
        });
        group.addChild(grid);
    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (manager.isBattle()) {
            period += delta;
            if (period >= 0.066f) {
                period -= 0.066f;
                manager.tick();
                //Gdx.app.error("WildernessScreen", "time = " + manager.time);
                giant.update(manager.giants.get(0));
                history.updateAll();
                history.scrollDown();
            }
        }
    }

    /*
        QQList.Adapter series...
     */
    private final QQGrid.Adapter legionAdapter = new QQGrid.Adapter() {

        @Override
        public int getSize() {
            return manager.legions.size();
        }

        @Override
        public QQView getView(int index, QQView view) {
            Legion leg = (Legion) manager.legions.get(index);

            LegionHeroView v = (LegionHeroView) view;
            if (null == v)
                v = new LegionHeroView(assets);

            v.reset(leg);
            v.setSize(QQView.MATCH_PARENT, 76);
            return v;
        }
    };

    private final QQList1.Adapter battleHistoryAdapter = new QQList1.Adapter() {

        private static final int TYPE_ACTION = 0;
        private static final int TYPE_RESULT = 1;
        private static final int TYPE_SCORE  = 2;
        private static final int TYPE_MAX    = 3;

        public int getItemViewType(int index) {
            Object obj = manager.battleHistory.get(index);
            if (obj instanceof CampaignAction)
                return TYPE_ACTION;
            else if (obj instanceof CampaignResult)
                return TYPE_RESULT;
            else
                return TYPE_SCORE;
        }

        @Override
        public int getSize() {
            return manager.battleHistory.size();
        }

        @Override
        public QQView getView(int index) {
            QQView convertView;
            if (TYPE_ACTION == getItemViewType(index)) {
                CampaignAction action = (CampaignAction) manager.battleHistory.get(index);
                convertView = new CampaignActionView(assets);
                ((CampaignActionView) convertView).reset(action);
            } else if (TYPE_RESULT == getItemViewType(index)) {
                CampaignResult result = (CampaignResult) manager.battleHistory.get(index);
                convertView = new CampaignResultView(assets);
                ((CampaignResultView) convertView).reset(result);
            } else {
                CampaignScore score = (CampaignScore) manager.battleHistory.get(index);
                convertView = new CampaignScoreView(assets);
                ((CampaignScoreView) convertView).reset(score);
            }
            convertView.setPadding(8);
            convertView.setSize(QQView.MATCH_PARENT, 40);

            return convertView;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
