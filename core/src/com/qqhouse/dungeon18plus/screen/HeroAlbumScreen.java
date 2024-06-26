package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.ItemDetailView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQCyclePager;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;
import com.qqhouse.ui.QQViewPager;

import java.util.ArrayList;

public class HeroAlbumScreen extends QQScreen {

    public HeroAlbumScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<HeroClass> allHeroClass;

    @Override
    public void onEnter() {

        // data
        allHeroClass = savedGame.getHeroClass();//equipments = savedGame.getEquipmentData();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);


        // title bar with merchant and equipment count...
        TitleBarView2 merchant = new TitleBarView2(assets);
        merchant.reset("fairy", "fairy", null, Game.Colour.RARE, "");
        merchant.setSize(QQView.MATCH_PARENT, 48);
        //merchant.setPosition(0, Game.Size.HEIGHT - 48);
        merchant.setPadding(8);
        merchant.setBackground(assets.getNinePatchBG("neutral"));
        group.addChild(merchant);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);//Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        //line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatchBG("white"));
        group.addChild(line);

        // all hero ...
        QQCyclePager cyclePager = new QQCyclePager(getViewport(), Game.Size.WIDGET_MARGIN);
        //QQList list = new QQList(getViewport());
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        //list.setMaxHeight(Game.Size.HEIGHT * 0.9f - 48 - 4 - 8 - 8); // 680 * 0.9 - 48 - 4
        cyclePager.setSize(QQView.MATCH_PARENT, 64);
        cyclePager.setAdapter(adapter);
        //viewPager.setPosition(0, 100);
        //viewPager.setBackground(assets.getNinePatchBG("blessed"));
        group.addChild(cyclePager);

    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    /*
        QQList.Adapter series...
     */
    private QQCyclePager.Adapter adapter = new QQCyclePager.Adapter() {

        @Override
        public int getSize() {
            return allHeroClass.size();
        }

        @Override
        public QQView getView(int index, QQView view) {
            HeroClass hr = allHeroClass.get(index);
            TitleBarView2 v = new TitleBarView2(assets);
            v.reset(hr.key, hr.key);
            v.setPadding(8);
            v.setBackground(assets.getNinePatchBG(hr.alignment.key));
            v.setSize(Game.Size.WIDTH * 0.9f, 64);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
