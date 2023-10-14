package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.DungeonManager;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.view.ActionView;
import com.qqhouse.dungeon18plus.view.EventView;
import com.qqhouse.dungeon18plus.view.HeroView;
import com.qqhouse.dungeon18plus.view.LootInfoView;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQClickListener;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class DungeonScreen extends QQScreen {

    public interface DungeonCallback {
        void onDungeonResult();
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

    private BitmapFont fntDigital, fntSmallDigital;
    private HeroView heroView;
    private ArrayList<ActionView> actionViews = new ArrayList<>();
    private LootInfoView lootInfo;
    private ArrayList<EventView> eventViews = new ArrayList<>();
    private QQList eventList, specialEventList;

    /*
        QQList Adapter ...
     */
    private QQList.Adapter eventAdapter = new QQList.Adapter() {

        @Override
        public int getSize() {
            return manager.getEventCount();
        }

        @Override
        public EventView getView(int index) {
            Event event = manager.getEvent(index);

            EventView evt = new EventView(assets);
            evt.setSize(Game.WIDTH, 64);
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

    private QQList.Adapter specialEventAdapter = new QQList.Adapter() {

        @Override
        public int getSize() {return manager.getSpecialEventCount();}

        @Override
        public EventView getView(int index) {
            Gdx.app.error("DungeonScreen", "SpecialAdapter.getView : " + index);
            Event event = manager.getSpecialEvent(index);

            EventView evt = new EventView(assets);
            evt.setSize(Game.WIDTH, 64);
            evt.reset(event);

            return evt;
        }

        @Override
        public void updateView(int index, QQView view) {
            EventView v = (EventView) view;
            v.setEnable(manager.isSpecialEventDoable(index));
            v.update(manager.getSpecialEvent(index));
            Gdx.app.error("DungeonScreen", "SpecialAdapter.updateView : " + index + " = " + v.getX() + "," + v.getX());

        }
    };



    @Override
    public void onEnter() {

        // font
        //String fntName = "Sono-SemiBold.ttf";
        String fntName = "Sono-Bold.ttf";
        //String fntName = "Sono-ExtraBold.ttf";
        String fntName1 = "whitrabt.ttf";
        String fntName2 = "ConsolaMono-Bold.ttf";
        String fntName3 = "F25_Bank_Printer.ttf";
        fntDigital = createFont(fntName1, 16, Color.WHITE, "");
        fntSmallDigital = createFont(fntName1, 16, Color.WHITE, "");

        //fntDigital.setFixedWidthGlyphs("0123456789");

        // hero view ...
        heroView = new HeroView(assets);
        heroView.setPadding(8);
        heroView.setPosition(0, Game.HEIGHT - 64);
        //heroView.setSize(QQView.FILL_PARENT, 64);
        heroView.setSize(Game.WIDTH, 64);
        heroView.reset(manager.getHero());
        //heroView.setData(manager.getHero());
        addView(heroView);

        // event listview ...
        eventList = new QQList();
        eventList.setSize(Game.WIDTH, Game.HEIGHT - 64 - 2 - 2 - 24 -2 - 64);
        eventList.setPosition(0, 64 + 2 + 24 + 2);
        eventList.setCamera(getCamera());
        eventList.setAdapter(eventAdapter);
        eventList.addQQClickListener(new QQClickListener() {
            @Override
            public void onClick(int index) {
                if (manager.isEventDoable(index)) {
                    //Gdx.app.error("DungeonScreen", " do event.");
                    manager.doEvent();
                    //Gdx.app.error("DungeonScreen", " update.");
                    update();
                }
            }});
        addView(eventList);

        // special event list
        specialEventList = new QQList();
        specialEventList.setSize(Game.WIDTH, QQView.WRAP_CONTENT);//64 + 64 + 2 + 2 + 2);
        specialEventList.setPosition(0, 64 + 2 + 24 + 2);
        specialEventList.setCamera(getCamera());
        specialEventList.setAdapter(specialEventAdapter);
        specialEventList.addQQClickListener(new QQClickListener() {
            @Override
            public void onClick(int index) {
                if (manager.isSpecialEventDoable(index)) {
                    manager.doSpecialEvent(index);
                    update();
                }
            }});
        addView(specialEventList);



        //QQListView list = (QQListView) new QQListView()
        //        .size(Game.WIDTH, Game.HEIGHT - 64 -2 - 2 - 24 - 2 - 64).
        //        position(0, 64 + 2 + 24 + 2);
        //list.setCamera(getCamera());
        //addView(list);

        //int eventCount = manager.getEventCount();

        //for (int i = 0; i < eventCount; ++i) {
        //    Event event = manager.getEvent(i);

        //    EventView evt = new EventView(assets);
        //    evt.setSize(Game.WIDTH, 64);
        //    evt.reset(event);
        //    evt.addQQClickListener(new QQClickListener() {
        //        @Override
        //        public void onClick(int index) {
        //            if (manager.isEventDoable(index)) {
        //                manager.doEvent();
        //                update();
        //            }
        //        }
        //    }, i);

        //    list.addView(evt);
        //    eventViews.add(evt);
        //}

        // message view ...
        lootInfo = new LootInfoView(assets);
        lootInfo.setSize(Game.WIDTH, 24);
        lootInfo.setPosition(0, 64 + 2);
        addView(lootInfo);

        // action view ...
        int actionCount = manager.getActionSlotCount();
        // 由於會縮放,需要注意 int 會導致捨位誤差...
        float actionWidth = ((Game.WIDTH) - (actionCount - 1) * 2) / (float)actionCount;

        for (int i = 0; i < actionCount; ++i) {
            ActionSlot slot = manager.getActionSlot(i);

            ActionView action = new ActionView(
                    assets.getBackgroundSet(manager.getHero().heroClass.alignment.key),
                    assets.getIcon32(manager.getActionSlot(i).action.key),
                    fntSmallDigital,
                    assets.getIcon16(slot.action.cost.getIcon16Key()),
                    slot.action.cost.value
                    );
            action.setSize(actionWidth, 64);
            action.setPosition((actionWidth + 2) * i, 0);
            action.addQQClickListener(new QQClickListener() {
                @Override
                public void onClick(int index) {
                    if (manager.canDoAction(index)) {
                        manager.doAction(index);
                        // update status...
                        update();
                    }
                    debug();
                }
            }, i);
            actionViews.add(action);
            addView(action);
        }

        update();
    }

    private void debug() {
        //Gdx.app.error("DungeonScreen", "event table = .....");
        //for (int i = 0; i < manager.getEventCount(); ++i) {
        //    Gdx.app.error("DungeonScreen", String.format("Event %2d = %s", i, manager.getEvent(i).toString()));
        //}
        //manager.test();
    }

    public void update() {
        // 1. heroview
        heroView.update(manager.getHero());
        // 2. event list
        //for (int i = 0, s = eventViews.size(); i < s; ++i) {
        //    EventView evt = eventViews.get(i);
        //    evt.setEnable(manager.isEventDoable(i));
        //    evt.update(manager.getEvent(i));
        //}
        eventAdapter.updateAll();
        specialEventAdapter.updateAll();

        lootInfo.update(manager.eventResult);
        // 3. action list
        for (int i = 0, s = actionViews.size(); i < s; ++i) {
            actionViews.get(i).setEnable(manager.canDoAction(i));
        }
    }


    @Override
    public void onLeave() {

    }
}
