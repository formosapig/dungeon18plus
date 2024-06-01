package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.dungeon18plus.view.VeteranButton;
import com.qqhouse.dungeon18plus.view.VeteranView;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class BarrackScreen extends QQScreen {

    private ArrayList<Veteran> barrack;
    private Veteran veteran;
    private PopupScreen callback;

    public BarrackScreen(SaveGame savedGame, Viewport viewport, Assets assets, PopupScreen callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    public void setVeteran(Veteran veteran) {
        this.veteran = veteran;
    }

    @Override
    public void onEnter() {

        // data
        barrack = savedGame.getBarrackData();

        // title bar with merchant and equipment count...
        TitleBarView2 crusader = new TitleBarView2(assets);
        crusader.reset("crusader", "crusader", null, Game.Colour.COUNT, "");
        crusader.setSize(Game.Size.WIDTH, 48);
        crusader.setPosition(0, Game.Size.HEIGHT - 48);
        crusader.setPadding(8);
        crusader.setBackground(assets.getNinePatchBG("lawful"));
        addChild(crusader);

        // split line...
        QQView line = new QQView();
        line.setSize(Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatchBG("white"));
        addChild(line);

        // equipment adapter ....
        QQList list = new QQList(getViewport());
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        //list.setMaxHeight(Game.Size.HEIGHT * 0.9f - 48 - 4 - 8 - 8); // 680 * 0.9 - 48 - 4
        list.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 48 - 8 - 4);
        list.setPosition(0, 0);
        list.setAdapter(veteran != null ? adapter : adapter2);
        addChild(list);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    /*
        QQList.Adapter series...
     */
    private final QQList.Adapter adapter2 = new QQList.Adapter() {

        @Override
        public int getSize() {
            return barrack.size();
        }

        @Override
        public QQView getView(int index) {
            Veteran vet = barrack.get(index);

            VeteranView v = new VeteranView(assets);
            v.setPadding(8);
            v.reset(vet);
            v.updateUniqueSkill(vet);
            v.setSize(QQView.MATCH_PARENT, 64);

            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };

    private final QQList.Adapter adapter = new QQList.Adapter() {

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

            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
