package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.WildernessManager;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignAction;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignScore;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.view.CampaignActionView;
import com.qqhouse.dungeon18plus.view.CampaignResultView;
import com.qqhouse.dungeon18plus.view.CampaignScoreView;
import com.qqhouse.dungeon18plus.view.GiantView;
import com.qqhouse.dungeon18plus.view.LegionHeroView;
import com.qqhouse.ui.QQGrid;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class WildernessScreen extends QQScreen {

    private WildernessManager manager;
    private final PopupScreen callback;
    private GiantView giant;
    private QQList1 history;
    private boolean campaignStarted;
    private float period;
    private float tickPerSecond;

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
        //giant.setSize(QQView.MATCH_PARENT, 128);
        giant.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                if (!manager.start())
                    manager.toggle();
            }

            @Override
            public void onLongPress(QQView view) {
                manager.start();
                if (Game.Debug.CAMPAIGN_AUTOMATION) {
                    manager.auto();
                    while (manager.isBattle()) {
                        manager.tick();
                    }
                    giant.update(manager.giants.get(0), manager.time);
                    history.updateAll();
                    history.scrollDown();
                    legionAdapter.refresh();
                } else {
                    if (manager.auto())
                        tickPerSecond = 20f / Game.Setting.CAMPAIGN_MAX_TIME;
                }
            }
        });
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
                manager.useSkill(index);
                history.updateAll();
                history.scrollDown();
            }

            @Override
            public void onLongPress(int index) {}
        });
        group.addChild(grid);

        tickPerSecond = 99f / Game.Setting.CAMPAIGN_MAX_TIME;
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
            if (period >= tickPerSecond) {
                period -= tickPerSecond;
                manager.tick();
                if (manager.isUpdateGiant())
                    giant.update(manager.giants.get(0), manager.time);
                if (manager.isUpdateHistory()) {
                    history.updateAll();
                    history.scrollDown();
                }
                if (manager.isUpdateLegion())
                    legionAdapter.refresh();
            }
        } /*else if (manager.isResult()) {
            // disable swipe right.
            //setSwipeRightCallback(null);

            // call battle report dialog.
            //BattleReportDialog dialog = new BattleReportDialog(assets, getViewport());
            //dialog.reset(manager.getAllScore(), manager.resultKey, new QQPressAdapter() {
            //    @Override
            //    public void onPress(int index) {
            //        callback.onPopupScreen();
            //    }
            //});
            //openDialog(dialog);

        }*/
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

        @Override
        public void updateView(int index, QQView view) {
            Legion leg = (Legion) manager.legions.get(index);

            LegionHeroView v = (LegionHeroView) view;
            v.update(leg);
        }
    };

    private final QQListAdapter battleHistoryAdapter = new QQListAdapter() {

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
                //convertView.setPadding(8);
                convertView.setSize(QQView.MATCH_PARENT, 48);
            } else if (TYPE_RESULT == getItemViewType(index)) {
                CampaignResult result = (CampaignResult) manager.battleHistory.get(index);
                convertView = new CampaignResultView(assets);
                ((CampaignResultView) convertView).reset(result);
                //convertView.setPadding(8);
                convertView.setSize(QQView.MATCH_PARENT, 40);
            } else {
                CampaignScore score = (CampaignScore) manager.battleHistory.get(index);
                convertView = new CampaignScoreView(assets);
                ((CampaignScoreView) convertView).reset(score);
                //convertView.setPadding(4, 4, 8, 8);
                convertView.setSize(QQView.MATCH_PARENT, 40);
            }
            convertView.setPadding(8);
            //convertView.setSize(QQView.MATCH_PARENT, 40);

            return convertView;
        }
    };
}
