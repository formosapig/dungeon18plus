package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.ColosseumManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.dialog.SelectEquipmentDialog;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.ActionShortcutButton;
import com.qqhouse.dungeon18plus.view.ActionShortcutView;
import com.qqhouse.dungeon18plus.dialog.EventInfoDialog;
import com.qqhouse.dungeon18plus.view.EventInfoView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.dungeon18plus.view.ProfileView;
import com.qqhouse.dungeon18plus.view.SelectEquipmentView;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQScroll;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ColosseumScreen extends QQScreen {
    public interface ColosseumCallback {
        void onColosseumResult(Veteran veteran);
    }

    private ColosseumManager manager;
    private HeroView heroView;
    private final ArrayList<ActionShortcutButton> actionViews = new ArrayList<>();
    private LootInfoView lootInfo;
    private EventInfoView eventInfo;
    private EventInfoDialog eventInfoDialog;
    private final ColosseumScreen.ColosseumCallback callback;
    private QQScroll scroll;
    private ProfileView profile;

    public ColosseumScreen(SaveGame savedGame, Viewport viewport, Assets assets, ColosseumScreen.ColosseumCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    public void setHero(HeroClass hero) {
        manager = new ColosseumManager(hero, savedGame);
        manager.setAdapter(eventAdapter);
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
        public void onAnimationEnd() {update();}
    };

    @Override
    public void onEnter() {

        // hero view ...
        heroView = new HeroView(assets, manager.getHero());
        heroView.setPadding(8);
        heroView.setPosition(0, Game.Size.HEIGHT - 64);
        heroView.setSize(Game.Size.WIDTH, 64);
        heroView.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                scroll.setVisible(!scroll.isVisible());
                if (scroll.isVisible())
                    profile.update(manager.getHero(), manager.getActionSlots());
            }
        });
        addChild(heroView);

        // group ( event, special event ), just a container...
        QQGroup group = new QQGroup(QQGroup.DIRECT_VERTICAL, 2);
        // FIXME 使用 match_parent 以及 Screen 內含 QQView ...
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 24 - 64 - Game.Size.WIDGET_MARGIN * 3);
        group.setPosition(0, 64 + Game.Size.WIDGET_MARGIN * 2 + 24);
        addChild(group);

        // event listview ...
        QQList eventList = new QQList(getViewport());
        eventList.setSize(Game.Size.WIDTH, QQView.MATCH_PARENT);
        eventList.setAdapter(eventAdapter);
        eventList.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                if (manager.isEventDoable(index)) {
                    int result = manager.doEvent();
                    if (Game.result.process > result) {
                        endGame(Game.result.win == result);
                    }
                }
            }

            @Override
            public void onLongPress(int index) {
                eventInfoDialog.update(manager.getEvent(index));
                openDialog(eventInfoDialog);
            }
        });
        group.addChild(eventList);

        // message view ...
        lootInfo = new LootInfoView(assets);
        lootInfo.setSize(Game.Size.WIDTH, 24);
        lootInfo.setPosition(0, 64 + Game.Size.WIDGET_MARGIN);
        addChild(lootInfo);

        // hero profile
        scroll = new QQScroll(getViewport());
        scroll.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 64 - 64 - Game.Size.WIDGET_MARGIN * 2);
        scroll.setPosition(0, 64 + Game.Size.WIDGET_MARGIN);
        scroll.setPadding(8);
        scroll.setBackground(assets.getNinePatchBG("help"));
        scroll.setVisible(false);
        addChild(scroll);

        profile = new ProfileView(assets);
        profile.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        profile.update(manager.getHero(), manager.getActionSlots());
        scroll.addChild(profile);

        // action view ...
        actionViews.clear();
        int actionCount = manager.getActionSlotCount();
        float actionWidth = ((Game.Size.WIDTH) - (actionCount - 1) * 2) / (float)actionCount;

        for (int i = 0; i < actionCount; ++i) {
            ActionSlot slot = manager.getActionSlot(i);

            ActionShortcutButton action = new ActionShortcutButton(assets);
            action.update(slot, true);
            action.setSize(actionWidth, 64);
            action.setPosition((actionWidth + 2) * i, 0);
            action.setBackground(assets.getBackgroundSet(manager.getHero().heroClass.alignment.key));
            action.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (manager.canDoAction(index)) {
                        manager.doAction(index);
                        update();
                    }
                    debug();
                }
            }, i);
            actionViews.add(action);
            addChild(action);
        }

        // event info dialog
        eventInfoDialog = new EventInfoDialog(assets);

        update();
    }

    private void update() {
        // 1. hero view
        heroView.update(manager.getHero());

        // 2. event list
        eventAdapter.updateAll();

        // 3. loot info
        lootInfo.update(manager.eventResult);

        // 4. action list
        for (int i = 0, s = actionViews.size(); i < s; ++i) {
            actionViews.get(i).update(manager.getActionSlot(i), manager.canDoAction(i));
        }
    }

    private void debug() {
        manager.debugFillBackpack();
        endGame(true);
    }

    private void endGame(boolean isWin) {
        if (manager.createBackpack().isEmpty()) {
            callback.onColosseumResult(null);
        } else {

            // disable swipe right.
            setSwipeRightCallback(null);

            // call summary dialog.
            SelectEquipmentDialog dialog = new SelectEquipmentDialog(assets, getViewport());
            dialog.reset(manager.createVeteran(), manager.createBackpack(), new SelectEquipmentView.SelectEquipmentCallback() {
                @Override
                public void SelectEquipmentDone(Veteran veteran) {
                    if (savedGame.getLegionCount() + savedGame.getBarrackCount() < Game.MAX_BARRACK_SIZE) {
                        savedGame.addVeteranToBarrack(Game.MAX_BARRACK_SIZE, veteran);
                        callback.onColosseumResult(null);
                    } else {
                        callback.onColosseumResult(veteran);
                    }
                }
            });
            openDialog(dialog);
        }
    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }
}
