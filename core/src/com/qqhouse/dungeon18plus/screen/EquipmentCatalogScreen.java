package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.ui.QQView.MATCH_PARENT;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.ItemDetailView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class EquipmentCatalogScreen extends QQScreen {

    public EquipmentCatalogScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<Item> equipments;

    @Override
    public void onEnter() {

        // data
        equipments = savedGame.getEquipmentData();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // title bar with merchant and equipment count...
        TitleBarView2 merchant = new TitleBarView2(assets);
        merchant.reset("merchant", "merchant", null, Game.Colour.COUNT, Integer.toString(equipments.size()));
        merchant.setSize(QQView.MATCH_PARENT, 48);
        merchant.setPadding(8);
        merchant.setBackground(assets.getNinePatchBG("special"));
        group.addChild(merchant);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);
        line.setBackground(assets.getNinePatchBG("white"));
        group.addChild(line);

        // equipment adapter ....
        QQList list = new QQList(getViewport());
        list.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        list.setAdapter(adapter);
        group.addChild(list);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    /*
        QQList.Adapter series...
     */
    private QQList.Adapter adapter = new QQList.Adapter() {

        @Override
        public int getSize() {
            return equipments.size();
        }

        @Override
        public QQView getView(int index) {
            ItemDetailView view = new ItemDetailView(assets);
            view.reset();
            view.update(equipments.get(index), false);
            view.setSize(MATCH_PARENT, QQView.WRAP_CONTENT);

            return view;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
