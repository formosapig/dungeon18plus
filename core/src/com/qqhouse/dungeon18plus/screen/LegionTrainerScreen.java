package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.LegionHeroView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.dungeon18plus.view.VeteranButton;
import com.qqhouse.ui.QQGrid;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Collections;

public class LegionTrainerScreen extends QQScreen {

    private ArrayList<Veteran> barrack;
    private ArrayList<Veteran> legion;

    public LegionTrainerScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    @Override
    public void onEnter() {

        // get data
        legion = savedGame.getLegionData();
        barrack = savedGame.getBarrackData();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // legion view.
        QQGrid grid = new QQGrid(getViewport());
        grid.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        grid.setCamera(getCamera());
        grid.setNumColumns(4);
        grid.setAdapter(legionAdapter);
        grid.setPressListener( new QQGrid.PressListener() {

            @Override
            public void onPress(int index) {
                // remove legion
                barrack.add(legion.remove(index));
                Collections.sort(barrack);
                barrackAdapter.updateAll();
                legionAdapter.updateAll();
            }

            @Override
            public void onLongPress(int index) {}
        });
        group.addChild(grid);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);
        line.setBackground(assets.getNinePatch("white"));
        group.addChild(line);

        // title bar with crusader
        TitleBarView2 crusader = new TitleBarView2(assets);
        crusader.reset("crusader", "crusader", null, Game.Colour.COUNT, "");
        crusader.setSize(QQView.MATCH_PARENT, 48);
        crusader.setPadding(8);
        crusader.setBackground(assets.getNinePatch("lawful"));
        group.addChild(crusader);

        // split line...
        QQView line2 = new QQView();
        line2.setSize(QQView.MATCH_PARENT, 4);
        line2.setBackground(assets.getNinePatch("white"));
        group.addChild(line2);

        // barrack
        QQList1 list = new QQList1(getViewport(), Game.Size.WIDGET_MARGIN);
        list.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        list.setAdapter(barrackAdapter);
        list.setPressListener(new QQList1.PressAdapter() {

            @Override
            public void onPress(int index) {
                // add legion
                if (canAddFromBarrackPosition(index)) {
                    // can add.
                    legion.add(barrack.remove(index));
                    legionAdapter.updateAll();
                    barrackAdapter.updateAll();
                }
            }
        });
        group.addChild(list);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    private boolean canAddFromBarrackPosition(int position) {
        if (Game.MAX_LEGION_SIZE <= legion.size())
            return false;

        HeroClass wantToAdd = barrack.get(position).heroClass;
        // check duplicate.
        for (Veteran v : legion)
            if (v.heroClass == wantToAdd)
                return false;

        return true;
    }

    /*
        QQList.Adapter series...
     */
    private final QQGrid.Adapter legionAdapter = new QQGrid.Adapter() {

        @Override
        public int getSize() {
            return legion.size();
        }

        @Override
        public QQView getView(int index, QQView view) {
            Veteran vet = legion.get(index);

            LegionHeroView v = (LegionHeroView) view;
            if (null == v)
                v = new LegionHeroView(assets);

            v.reset(vet);
            v.setSize(QQView.MATCH_PARENT, 64);
            return v;
        }
    };

    private final QQListAdapter barrackAdapter = new QQListAdapter() {

        @Override
        public int getSize() {
            return barrack.size();
        }

        @Override
        public QQView getView(int index) {
            Veteran vet = barrack.get(index);

            VeteranButton v = new VeteranButton(assets);
            v.setPadding(8);
            v.reset(vet);
            v.updateUniqueSkill(vet);
            v.setSize(QQView.MATCH_PARENT, 64);
            v.setEnabled(canAddFromBarrackPosition(index));
            return v;
        }
    };
}
