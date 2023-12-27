package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;
import com.qqhouse.dungeon18plus.view.ItemDetailView;
import com.qqhouse.dungeon18plus.view.ScoreHeroView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class LeaderboardScreen extends QQScreen {

    public LeaderboardScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<ScoreHero> leaderboard;


    @Override
    public void onEnter() {

        // data
        leaderboard = savedGame.getLeaderboardData();

        // title bar with merchant and equipment count...
        TitleBarView2 novice = new TitleBarView2(assets);
        novice.reset("novice", "old_hero", null, Game.Colour.COUNT, "");
        novice.setSize(Game.Size.WIDTH, 48);
        novice.setPosition(0, Game.Size.HEIGHT - 48);
        novice.setPadding(8);
        novice.setBackground(assets.getNinePatchBG("ordinary"));
        addChild(novice);

        // split line...
        QQView line = new QQView();
        line.setSize(Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
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
            return leaderboard.size();
        }

        @Override
        public QQView getView(int index) {
            ScoreHeroView v = new ScoreHeroView(assets);
            v.reset(leaderboard.get(index), index + 1);
            v.setSize(QQView.MATCH_PARENT, 64);

            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
