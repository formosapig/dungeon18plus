package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.view.ActionShortcutView;
import com.qqhouse.dungeon18plus.dialog.EventInfoDialog;
import com.qqhouse.dungeon18plus.view.EventInfoView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.dialog.SummaryDialog;
import com.qqhouse.dungeon18plus.view.ProfileView;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQScroll;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class DungeonScreen extends QQScreen {

    public interface DungeonCallback {
        void onDungeonResult(boolean isWin, ArrayList<BossKill> kills);
    }

    private DungeonManager manager;
    private HeroView heroView;
    private final ArrayList<ActionShortcutView> actionViews = new ArrayList<>();
    private LootInfoView lootInfo;
    private EventInfoView eventInfo;
    private EventInfoDialog eventInfoDialog;
    private final PopupScreen callback;
    private QQScroll scroll;
    private ProfileView profile;

    public DungeonScreen(SaveGame savedGame, Viewport viewport, Assets assets, PopupScreen callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    public void setHero(HeroClass hero) {
        manager = new DungeonManager(hero, savedGame);
        manager.setAdapter(eventAdapter);
        manager.setSpecialAdapter(specialEventAdapter);
    }

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
            evt.update(event);
            return evt;
        }

        @Override
        public void updateView(int index, QQView view) {
            EventView v = (EventView) view;
            v.setEnable(manager.isEventDoable(index));
            v.update(manager.getEvent(index));

        }

        // animation end, then update event value.
        @Override
        public void onAnimationEnd() {
            //Gdx.app.error("DungeonScreen", String.format(Locale.US, "onAnimationEnd %d", manager.getEventCount()));
            //Gdx.app.error("DungeonScreen", "event animation end.");
            //eventAdapter.updateAll();
            //specialEventAdapter.updateAll();
            //lootInfo.update(manager.eventResult);
            update();
            // FIXME consider new item change status immediately....looks strange.
            // 1. hero view
            //heroView.update(manager.getHero());

            // 2. event list
            //eventAdapter.updateAll();
            //specialEventAdapter.updateAll();
            // 3. loot info
            //lootInfo.update(manager.eventResult);
            // 4. action list
            //for (int i = 0, s = actionViews.size(); i < s; ++i) {
            //    actionViews.get(i).setEnable(manager.canDoAction(i));
            //}

        }
    };

    private final QQList.Adapter specialEventAdapter = new QQList.Adapter() {

        @Override
        public int getSize() {return manager.getSpecialEventCount();}

        @Override
        public EventView getView(int index) {
            //Gdx.app.error("DungeonScreen", "SpecialAdapter.getView : " + index);
            Event event = manager.getSpecialEvent(index);

            EventView evt = new EventView(assets);
            evt.setSize(Game.Size.WIDTH, 64);
            evt.reset(event);
            evt.update(event);
            return evt;
        }

        @Override
        public void updateView(int index, QQView view) {
            EventView v = (EventView) view;
            v.setEnable(manager.isSpecialEventDoable(index));
            v.update(manager.getSpecialEvent(index));
            //Gdx.app.error("DungeonScreen", "SpecialAdapter.updateView : " + index + " = " + v.getX() + "," + v.getX());

        }

        // animation end, then update event value.
        @Override
        public void onAnimationEnd() {
            //Gdx.app.error("DungeonScreen", "special event animation end.");
            update();
            //eventAdapter.updateAll();
            //specialEventAdapter.updateAll();
            //lootInfo.update(manager.eventResult);

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
        heroView.addQQClickListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                scroll.setVisible(true);
            }
        }, 1);
        addChild(heroView);

        // group ( event, special event ), just a container...
        QQGroup group = new QQGroup(QQGroup.DIRECT_VERTICAL, 2);
        // FIXME 使用 match_parent 以及 Screen 內含 QQView ...
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 24 - 64 - Game.Size.WIDGET_MARGIN * 3);
        group.setPosition(0, 64 + Game.Size.WIDGET_MARGIN * 2 + 24);
        addChild(group);

        // special event list
        QQList specialEventList = new QQList(getViewport());
        //specialEventList.setSize(Game.WIDTH, QQView.WRAP_CONTENT);//64 + 64 + 2 + 2 + 2);
        specialEventList.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        specialEventList.setPosition(0, 0);

        specialEventList.setAdapter(specialEventAdapter);
        specialEventList.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                if (manager.isSpecialEventDoable(index)) {
                    int result = manager.doSpecialEvent(index);
                    //update();
                    if (Game.result.process > result) {
                        endGame(Game.result.win == result);
                    }
                }
            }

            @Override
            public void onLongPress(int index) {
                //Gdx.app.error("DungeonScreen", "special event long press : " + index);
                eventInfoDialog.update(manager.getSpecialEvent(index));
                openDialog(eventInfoDialog);
            }
        });
        //addView(specialEventList);
        group.addChild(specialEventList);

        // event listview ...
        QQList eventList = new QQList(getViewport());
        //eventList.setSize(Game.WIDTH, Game.HEIGHT - 64 - 2 - 2 - 24 -2 - 64);
        eventList.setSize(Game.Size.WIDTH, QQView.MATCH_PARENT);
        //eventList.setPosition(0, 64 + 2 + 24 + 2);
        //eventList.setCamera(getCamera());
        eventList.setAdapter(eventAdapter);
        eventList.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                if (manager.isEventDoable(index)) {
                    //Gdx.app.error("DungeonScreen", " do event.");
                    int result = manager.doEvent();
                    //Gdx.app.error("DungeonScreen", " update.");
                    //update();
                    if (Game.result.process > result) {
                        endGame(Game.result.win == result);
                    }
                }
            }

            @Override
            public void onLongPress(int index) {
                //Gdx.app.error("DungeonScreen", "event long press : " + index);
                //eventInfo.setSize(320, 480);
                //eventInfo.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
                //eventInfo.update(manager.getEvent(index));

                //QQCustomDialog eventInfoDialog = new QQCustomDialog(assets, eventInfo, false);
                eventInfoDialog.update(manager.getEvent(index));
                openDialog(eventInfoDialog);
            }
        });
        //addView(eventList);
        group.addChild(eventList);

        //group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 24 - 64 - Game.Size.WIDGET_MARGIN * 3);
        //group.setPosition(0, 64 + Game.Size.WIDGET_MARGIN * 2 + 24);
        //addChild(group);

        // hero profile
        scroll = new QQScroll(getViewport());
        scroll.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 24 - 64 - Game.Size.WIDGET_MARGIN * 3);
        scroll.setPosition(0, 64 + Game.Size.WIDGET_MARGIN * 2 + 24);
        scroll.setPadding(8);
        scroll.setBackground(assets.getNinePatchBG("help"));
        scroll.setVisible(false);
        addChild(scroll);

        profile = new ProfileView(assets);
        profile.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //profile.update(savedGame.getHeroClassRecord(allHeroClass.get(0)), savedGame);
        profile.update(savedGame.getHeroClassRecord(manager.getHero().heroClass), savedGame);
        scroll.addChild(profile);

        // message view ...
        lootInfo = new LootInfoView(assets);
        lootInfo.setSize(Game.Size.WIDTH, 24);
        lootInfo.setPosition(0, 64 + Game.Size.WIDGET_MARGIN);
        addChild(lootInfo);

        // action view ...
        actionViews.clear();
        int actionCount = manager.getActionSlotCount();
        // 由於會縮放,需要注意 int 會導致捨位誤差...
        float actionWidth = ((Game.Size.WIDTH) - (actionCount - 1) * 2) / (float)actionCount;

        for (int i = 0; i < actionCount; ++i) {
            ActionSlot slot = manager.getActionSlot(i);

            ActionShortcutView action = new ActionShortcutView(
                    assets.getBackgroundSet(manager.getHero().heroClass.alignment.key),
                    assets.getIcon(manager.getActionSlot(i).action.key),
                    assets.getFont(Game.Font.LEVEL16),
                    assets.getIcon(slot.action.cost.getIcon16Key()),
                    slot.action.cost.value
                    );
            action.setSize(actionWidth, 64);
            action.setPosition((actionWidth + 2) * i, 0);
            action.addQQClickListener(new QQPressListener() {
                @Override
                public void onPress(int index) {
                    //Gdx.app.error("DungeonScreen", "press action.");
                    if (manager.canDoAction(index)) {
                        manager.doAction(index);
                        // update status...
                        update();
                        //Gdx.app.error("DungeonScreen", "do action and update.");
                    }
                    //debug();
                }

                @Override
                public void onLongPress(QQView view) {

                }
            }, i);
            actionViews.add(action);
            addChild(action);
        }

        // event info dialog
        eventInfoDialog = new EventInfoDialog(assets);
        //eventInfo = new EventInfoView(assets);
        //eventInfo.setBackground(assets.getNinePatchBG("help"));

        update();
        //eventAdapter.updateAll();
        //specialEventAdapter.updateAll();
    }

    private void debug() {
        //Gdx.app.error("DungeonScreen", "event table = .....");
        //for (int i = 0; i < manager.getEventCount(); ++i) {
        //    Gdx.app.error("DungeonScreen", String.format("Event %2d = %s", i, manager.getEvent(i).toString()));
        //}
        //manager.test();
        //manager.test2();
    }

    private void update() {
        //Gdx.app.error("DungeonScreen", "update");
        // 1. hero view
        heroView.update(manager.getHero());

        // 2. event list
        eventAdapter.updateAll();
        specialEventAdapter.updateAll();
        // 3. loot info
        lootInfo.update(manager.eventResult);
        // 4. action list
        for (int i = 0, s = actionViews.size(); i < s; ++i) {
            actionViews.get(i).setEnable(manager.canDoAction(i));
        }
    }

    private void endGame(boolean isWin) {
        // disable swipe right.
        setSwipeRightCallback(null);

        // call summary dialog.
        SummaryDialog dialog = new SummaryDialog(assets, getViewport());
        dialog.reset(manager.killList, isWin, new QQPressListener() {
            @Override
            public void onPress(int index) {
                callback.onPopupScreen();
            }
            @Override
            public void onLongPress(QQView view) {}
        });
        openDialog(dialog);
    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
