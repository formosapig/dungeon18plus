package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.view.ItemDetailView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class MonsterGuideScreen extends QQScreen {

    public MonsterGuideScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<Monster> monsters;

    @Override
    public void onEnter() {

        // data
        monsters = savedGame.getMonsterData();

        // title bar with merchant and equipment count...
        TitleBarView2 merchant = new TitleBarView2(assets);
        merchant.reset("skeleton", "skeleton", null, Game.Colour.COUNT, Integer.toString(monsters.size()));
        merchant.setSize(Game.Size.WIDTH, 48);
        merchant.setPosition(0, Game.Size.HEIGHT - 48);
        merchant.setPadding(8);
        merchant.setBackground(assets.getNinePatchBG("ordinary"));
        addChild(merchant);

        // split line...
        QQView line = new QQView();
        line.setSize(Game.Size.WIDTH, 4);
        line.setPosition(0, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatchBG("white"));
        addChild(line);

        // equipment adapter ....
        QQList list = new QQList();
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        //list.setMaxHeight(Game.Size.HEIGHT * 0.9f - 48 - 4 - 8 - 8); // 680 * 0.9 - 48 - 4
        list.setSize(Game.Size.WIDTH, Game.Size.HEIGHT - 48 - 8 - 4);
        list.setPosition(0, 0);
        list.setCamera(getCamera());
        list.setAdapter(adapter);
        addChild(list);

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
            return monsters.size();
        }

        @Override
        public QQView getView(int index) {
            ItemDetailView view = new ItemDetailView(assets);
            //view.reset();
            //view.update(monsters.get(index), false);
            //view.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);

            return view;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
