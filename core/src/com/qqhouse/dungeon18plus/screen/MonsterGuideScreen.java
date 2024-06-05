package com.qqhouse.dungeon18plus.screen;

import static com.qqhouse.ui.QQView.MATCH_PARENT;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.view.MonsterView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQGrid;
import com.qqhouse.ui.QQLinear;
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

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // title bar with merchant and equipment count...
        TitleBarView2 merchant = new TitleBarView2(assets);
        merchant.reset("skeleton", "skeleton", null, Game.Colour.COUNT, Integer.toString(monsters.size()));
        merchant.setSize(QQView.MATCH_PARENT, 48);
        //merchant.setPosition(0, Game.Size.HEIGHT - 48);
        merchant.setPadding(8);
        merchant.setBackground(assets.getNinePatchBG("ordinary"));
        group.addChild(merchant);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);
        //line.setPosition(0, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatchBG("white"));
        group.addChild(line);

        // equipment adapter ....
        QQGrid grid = new QQGrid();
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        //list.setMaxHeight(Game.Size.HEIGHT * 0.9f - 48 - 4 - 8 - 8); // 680 * 0.9 - 48 - 4
        grid.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);//Game.Size.WIDTH, Game.Size.HEIGHT - 48 - 8 - 4);
        //grid.setPosition(0, 0);
        grid.setCamera(getCamera());
        grid.setNumColumns(5);
        grid.setAdapter(adapter);
        group.addChild(grid);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    /*
        QQList.Adapter series...
     */
    private final QQGrid.Adapter adapter = new QQGrid.Adapter() {

        @Override
        public int getSize() {
            return monsters.size();
        }

        @Override
        public QQView getView(int index) {
            MonsterView monster = new MonsterView(assets);
            monster.reset(monsters.get(index));
            monster.setSize(64, 64);
            return monster;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
