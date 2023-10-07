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
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;

import java.util.ArrayList;

public class DungeonScreen extends QQScreen {

    public interface DungeonCallback {
        void onDungeonResult();
    }

    private DungeonManager manager;

    public void setHero(HeroClass hero) {
        manager = new DungeonManager(hero, savedGame);
    }

    public DungeonScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private BitmapFont fntDigital, fntSmallDigital;
    private HeroView heroView;
    private ArrayList<ActionView> actionViews = new ArrayList<>();
    private LootInfoView lootInfo;
    private ArrayList<EventView> eventViews = new ArrayList<>();


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
        heroView = new HeroView(manager.getHero().heroClass.alignment.key, assets);
        heroView.preset(
                assets.getBlockee(manager.getHero().heroClass.key), // hero
                assets.getIcon16("life"), // life
                assets.getIcon16("attack"), // attack
                assets.getIcon16("defense"), // defense
                assets.getIcon16("speed"), // speed
                assets.getItem("key"), // key
                assets.getItem("copper_coin"), // coin
                assets.getItem("star") // star
        );
        heroView.setFont(fntDigital, fntSmallDigital);
        heroView.setPadding(8);
        heroView.setPosition(0, Game.HEIGHT - 64);
        //heroView.setSize(QQView.FILL_PARENT, 64);
        heroView.setSize(Game.WIDTH, 64);
        heroView.reset(manager.getHero());
        //heroView.setData(manager.getHero());
        addView(heroView);

        // event listview ...
        QQListView list = (QQListView) new QQListView()
                .size(Game.WIDTH, Game.HEIGHT - 64 -2 - 2 - 24 - 2 - 64).
                position(0, 64 + 2 + 24 + 2);
        list.setCamera(getCamera());
        addView(list);

        int eventCount = manager.getEventCount();

        for (int i = 0; i < eventCount; ++i) {
            Event event = manager.getEvent(i);

            EventView evt = new EventView(event.type.align.key, assets);
            evt.setSize(Game.WIDTH, 64);
            evt.reset(event);
            evt.addQQClickListener(new QQClickListener() {
                @Override
                public void onClick(int index) {
                    if (manager.isEventDoable(index)) {
                        manager.doEvent();
                        update();
                    }
                }
            }, i);

            list.addView(evt);
            eventViews.add(evt);
        }







        // message view ...
        lootInfo = new LootInfoView(assets.getBackground("loot_info"));
        lootInfo.setSize(Game.WIDTH, 24);
        lootInfo.setPosition(0, 64 + 2);
        addView(lootInfo);

        // action view ...
        int actionCount = manager.getActionSlotCount();
        // 由於會縮放,需要注意 int 會導致捨位誤差...
        float actionWidth = ((Game.WIDTH) - (actionCount - 1) * 2) / (float)actionCount;
        Gdx.app.error("DungeonScreen.java", "actionWidth = " + actionWidth);

        for (int i = 0; i < actionCount; ++i) {
            ActionSlot slot = manager.getActionSlot(i);

            ActionView action = new ActionView(
                    manager.getHero().heroClass.alignment.key,
                    assets.getIcon32(manager.getActionSlot(i).action.key),
                    fntSmallDigital,
                    assets.getIcon16(slot.action.cost.getIcon16Key()),
                    slot.action.cost.value
                    );
            action.setSize(actionWidth, 64);
            float pos = (actionWidth + 2) * i;
            Gdx.app.error("DungeonScreen.java", "pos = " + pos);
            action.setPosition((actionWidth + 2) * i, 0);
            action.addQQClickListener(new QQClickListener() {
                @Override
                public void onClick(int index) {
                    if (manager.canDoAction(index)) {
                        manager.doAction(index);
                        // update status...
                        update();
                    }
                }
            }, i);
            actionViews.add(action);
            addView(action);
        }

        update();
    }

    public void update() {
        // 1. heroview
        heroView.setData(manager.getHero());
        // 2. event list
        for (int i = 0, s = eventViews.size(); i < s; ++i) {
            EventView evt = eventViews.get(i);
            evt.setEnable(manager.isEventDoable(i));
            evt.update(manager.getEvent(i));
        }
        // 3. action list
        for (int i = 0, s = actionViews.size(); i < s; ++i) {
            actionViews.get(i).setEnable(manager.canDoAction(i));
        }
    }


    @Override
    public void onLeave() {

    }
}
