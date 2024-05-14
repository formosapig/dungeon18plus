package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class MasterView extends QQGroup {

    // data
    private ArrayList<EquipmentMastery> backpack;

    private final Assets assets;
    private final Viewport viewport;
    public MasterView(Assets assets, Viewport viewport) {
        super(DIRECT_VERTICAL, Game.Size.WIDGET_MARGIN);
        this.assets = assets;
        this.viewport = viewport;
        setPadding(Game.Size.BLOCKEE_PADDING);
        bgNormal = new NinePatch(assets.getBackground("dialog"), 4, 4, 4, 4);
    }

    public void reset(ArrayList<EquipmentMastery> backpack, QQPressListener listener) {
        this.backpack = backpack;

        // create ...
        // button
        // TODO QQButton can add image / text ...
        QQButtonEx done = new QQButtonEx(assets.getBackgroundSet(GameAlignment.NEUTRAL.key));
        //done.setPosition(leftPadding, bottomPadding);
        done.setSize(QQView.MATCH_PARENT, 40);
        done.addQQClickListener(listener, 0);
        //done.setText(assets.getFont(Game.Font.NAME20), assets.geti18n(isWin ? "win" : "lose"));
        addChild(done);

        // list
        QQList scores = new QQList(viewport);
        //scores.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scores.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        scores.setMaxHeight(400);
        scores.setAdapter(adapter);
        addChild(scores);
        //scores.setBackground(new NinePatch(assets.getBackground("white"), 4, 4, 4, 4));

        TitleBarView master = new TitleBarView(
                assets.getBlockee("sword_master"),
                assets.getFont(Game.Font.NAME20),
                assets.geti18n("score"));
        master.setPosition(leftPadding + 4, height - 40 - topPadding);
        master.setSize(QQView.MATCH_PARENT, 40);
        addChild(master);

        resetWrapHeight();
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    /*
        list view of boss kill....
     */
    private final QQList.Adapter adapter = new QQList.Adapter() {
        @Override
        public int getSize() {
            return backpack.size();
        }

        @Override
        public QQView getView(int index) {
            final EquipmentMasteryView v = new EquipmentMasteryView(assets);
            v.reset(backpack.get(index));
            v.setSize(QQView.MATCH_PARENT, 48);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
