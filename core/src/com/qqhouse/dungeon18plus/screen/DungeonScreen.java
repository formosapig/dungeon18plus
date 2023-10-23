package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.view.ActionView;
import com.qqhouse.dungeon18plus.view.EventInfoView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.view.SummaryDialog;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class DungeonScreen extends QQScreen {

    public interface DungeonCallback {
        void onDungeonResult(boolean isWin, ArrayList<BossKill> kills);
    }

    private DungeonManager manager;

    public void setHero(HeroClass hero) {
        manager = new DungeonManager(hero, savedGame);
        manager.setAdapter(eventAdapter);
        manager.setSpecialAdapter(specialEventAdapter);
    }

    public DungeonScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private HeroView heroView;
    private final ArrayList<ActionView> actionViews = new ArrayList<>();
    private LootInfoView lootInfo;
    private EventInfoView eventInfo;

    /*
        QQList Adapter ...
     */
    private final QQList.Adapter eventAdapter = new QQList.Adapter() {

        @Override
        public int getSize() {
            return manager.getEventCount();
        }

        @Override
        public EventView getView(int index) {
            Event event = manager.getEvent(index);

            EventView evt = new EventView(assets);
            evt.setSize(Game.Size.WIDTH, 64);
            evt.reset(event);

            return evt;
        }

        @Override
        public void updateView(int index, QQView view) {
            EventView v = (EventView) view;
            v.setEnable(manager.isEventDoable(index));
            v.update(manager.getEvent(index));

        }
    };

    private final QQList.Adapter specialEventAdapter = new QQList.Adapter() {

        @Override
        public int getSize() {return manager.getSpecialEventCount();}

        @Override
        public EventView getView(int index) {
            Gdx.app.error("DungeonScreen", "SpecialAdapter.getView : " + index);
            Event event = manager.getSpecialEvent(index);

            EventView evt = new EventView(assets);
            evt.setSize(Game.Size.WIDTH, 64);
            evt.reset(event);

            return evt;
        }

        @Override
        public void updateView(int index, QQView view) {
            EventView v = (EventView) view;
            v.setEnable(manager.isSpecialEventDoable(index));
            v.update(manager.getSpecialEvent(index));
            //Gdx.app.error("DungeonScreen", "SpecialAdapter.updateView : " + index + " = " + v.getX() + "," + v.getX());

        }
    };



    @Override
    public void onEnter() {

        // hero view ...
        heroView = new HeroView(assets);
        heroView.setPadding(8);
        heroView.setPosition(0, Game.Size.HEIGHT - 64);
        //heroView.setSize(QQView.FILL_PARENT, 64);
        heroView.setSize(Game.Size.WIDTH, 64);
        heroView.reset(manager.getHero());
        //heroView.setData(manager.getHero());
        addView(heroView);

        // group ( event, special event ), just a container...
        QQGroup group = new QQGroup(QQGroup.DIRECT_VERTICAL, 2);
        // FIXME 使用 match_parent 以及 Screen 內含 QQView ...
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 2 - 2 - 24 -2 - 64);
        group.setPosition(0, 64 + 2 + 24 + 2);
        addView(group);

        // special event list
        QQList specialEventList = new QQList();
        //specialEventList.setSize(Game.WIDTH, QQView.WRAP_CONTENT);//64 + 64 + 2 + 2 + 2);
        specialEventList.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        specialEventList.setPosition(0, 0);
        specialEventList.setCamera(getCamera());
        specialEventList.setAdapter(specialEventAdapter);
        specialEventList.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                if (manager.isSpecialEventDoable(index)) {
                    int result = manager.doSpecialEvent(index);
                    update();
                    if (Game.result.process > result) {
                        endGame(Game.result.win == result);
                    }
                }
            }

            @Override
            public void onLongPress(int index) {
                Gdx.app.error("DungeonScreen", "special event long press : " + index);
            }
        });
        //addView(specialEventList);
        group.addChild(specialEventList);

        // event listview ...
        QQList eventList = new QQList();
        //eventList.setSize(Game.WIDTH, Game.HEIGHT - 64 - 2 - 2 - 24 -2 - 64);
        eventList.setSize(Game.Size.WIDTH, QQView.MATCH_PARENT);
        //eventList.setPosition(0, 64 + 2 + 24 + 2);
        eventList.setCamera(getCamera());
        eventList.setAdapter(eventAdapter);
        eventList.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                if (manager.isEventDoable(index)) {
                    //Gdx.app.error("DungeonScreen", " do event.");
                    int result = manager.doEvent();
                    //Gdx.app.error("DungeonScreen", " update.");
                    update();
                    if (Game.result.process > result) {
                        endGame(Game.result.win == result);
                    }
                }
            }

            @Override
            public void onLongPress(int index) {
                eventInfo.update(manager.getEvent(index));
                openDialog(eventInfo, false);
                Gdx.app.error("DungeonScreen", "event long press : " + index);
            }
        });
        //addView(eventList);
        group.addChild(eventList);

        // message view ...
        lootInfo = new LootInfoView(assets);
        lootInfo.setSize(Game.Size.WIDTH, 24);
        lootInfo.setPosition(0, 64 + 2);
        addView(lootInfo);

        // action view ...
        int actionCount = manager.getActionSlotCount();
        // 由於會縮放,需要注意 int 會導致捨位誤差...
        float actionWidth = ((Game.Size.WIDTH) - (actionCount - 1) * 2) / (float)actionCount;

        for (int i = 0; i < actionCount; ++i) {
            ActionSlot slot = manager.getActionSlot(i);

            ActionView action = new ActionView(
                    assets.getBackgroundSet(manager.getHero().heroClass.alignment.key),
                    assets.getIcon32(manager.getActionSlot(i).action.key),
                    assets.getFont(Game.Font.LEVEL16),
                    assets.getIcon16(slot.action.cost.getIcon16Key()),
                    slot.action.cost.value
                    );
            action.setSize(actionWidth, 64);
            action.setPosition((actionWidth + 2) * i, 0);
            action.addQQClickListener(new QQPressListener() {
                @Override
                public void onPress(int index) {
                    if (manager.canDoAction(index)) {
                        manager.doAction(index);
                        // update status...
                        update();
                    }
                    debug();
                }

                @Override
                public void onLongPress(QQView view) {

                }
            }, i);
            actionViews.add(action);
            addView(action);
        }

        // event info
        eventInfo = new EventInfoView();

        update();
    }

    private void debug() {
        //Gdx.app.error("DungeonScreen", "event table = .....");
        //for (int i = 0; i < manager.getEventCount(); ++i) {
        //    Gdx.app.error("DungeonScreen", String.format("Event %2d = %s", i, manager.getEvent(i).toString()));
        //}
        //manager.test();
        manager.test2();
    }

    private void update() {
        // 1. heroview
        heroView.update(manager.getHero());

        // 2. event list
        eventAdapter.updateAll();
        specialEventAdapter.updateAll();
        // 3. loof info
        lootInfo.update(manager.eventResult);
        // 4. action list
        for (int i = 0, s = actionViews.size(); i < s; ++i) {
            actionViews.get(i).setEnable(manager.canDoAction(i));
        }
    }

    private void endGame(boolean isWin) {
        SummaryDialog summary = new SummaryDialog(assets);
        summary.reset(manager.killList, isWin);
        //summry.setPressListener();
        openDialog(summary, true);
    }

    @Override
    public void onLeave() {

    }
}
