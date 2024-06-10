package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.dungeon18plus.view.VeteranButton;
import com.qqhouse.dungeon18plus.view.VeteranView;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQText;
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

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        addChild(group);

        // title bar with merchant and equipment count...
        TitleBarView2 crusader = new TitleBarView2(assets);
        crusader.reset("crusader", "crusader", null, Game.Colour.COUNT, "");
        crusader.setSize(Game.Size.WIDTH, 48);
        //crusader.setPosition(0, Game.Size.HEIGHT - 48);
        crusader.setPadding(8);
        crusader.setBackground(assets.getNinePatchBG("lawful"));
        group.addChild(crusader);

        // split line...
        QQView line = new QQView();
        line.setSize(Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        //line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatchBG("white"));
        group.addChild(line);

        if (null != veteran) {
            // take one's place
            QQText info = new QQText(assets.getFont(Game.Font.NAME20));
            info.setSize(QQView.MATCH_PARENT, 28);
            info.setPadding(0, 0, 4, 0);
            info.setText(assets.geti18n("msg_barrack_take_place"));
            info.setAlign(Align.left);
            group.addChild(info);
        }

        // equipment adapter ....
        QQList1 list = new QQList1(getViewport(), Game.Size.WIDGET_MARGIN);
        list.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        //list.setPosition(0, 0);
        list.setAdapter(veteran != null ? adapter : adapter2);
        list.setPressListener(new QQList1.PressListener() {
            @Override
            public void onPress(int index) {
                savedGame.addVeteranToBarrack(index, veteran);
                veteran = null;
                if (null != callback)
                    callback.onPopupScreen();
            }

            @Override
            public void onLongPress(int index) {}
        });
        group.addChild(list);

        if (null != veteran) {
            // leave
            QQText info = new QQText(assets.getFont(Game.Font.NAME20));
            info.setSize(QQView.MATCH_PARENT, 28);
            info.setPadding(0, 0, 4, 0);
            info.setText(assets.geti18n("msg_barrack_leave"));
            info.setAlign(Align.left);
            group.addChild(info);

            // veteran...
            VeteranButton vb = new VeteranButton(assets);
            vb.setPadding(8);
            vb.reset(veteran);
            vb.updateUniqueSkill(veteran);
            vb.setSize(QQView.MATCH_PARENT, 64);
            vb.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    veteran = null;
                    if (null != callback)
                        callback.onPopupScreen();
                }
            });
            group.addChild(vb);
        }

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    /*
        QQList.Adapter series...
     */
    private final QQList1.Adapter adapter2 = new QQList1.Adapter() {

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

    private final QQList1.Adapter adapter = new QQList1.Adapter() {

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
