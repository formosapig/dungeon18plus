package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.ProfileTitleView;
import com.qqhouse.dungeon18plus.view.ProfileView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQCyclePager;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQScroll;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class HeroAlbumScreen extends QQScreen {

    public HeroAlbumScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<HeroClass> allHeroClass;
    private QQScroll scroll;
    private ProfileView profile;

    @Override
    public void onEnter() {

        // data
        allHeroClass = savedGame.getHeroClass();//equipments = savedGame.getEquipmentData();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // title bar with peddler and equipment count...
        TitleBarView2 fairy = new TitleBarView2(assets);
        fairy.reset("fairy", "fairy", null, Game.Colour.RARE, "");
        fairy.setSize(QQView.MATCH_PARENT, 48);
        //fairy.setPosition(0, Game.Size.HEIGHT - 48);
        fairy.setPadding(8);
        fairy.setBackground(assets.getNinePatch("neutral"));
        group.addChild(fairy);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);//Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        //line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatch("white"));
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
        cyclePager.setPageChangedListener(new QQCyclePager.PageChangedListener() {
            @Override
            public void onChange(int page) {
                //Gdx.app.error("HeroAlbumScreen", "on cyclePager change");
                profile.update(savedGame.getHeroClassRecord(allHeroClass.get(page)), savedGame);
                scroll.scrollToTop();
            }
        });
        group.addChild(cyclePager);

        // hero profile
        scroll = new QQScroll(getViewport());
        scroll.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scroll.setPadding(8);
        scroll.setBackground(assets.getNinePatch("help"));
        group.addChild(scroll);


        profile = new ProfileView(assets);
        profile.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        profile.update(savedGame.getHeroClassRecord(allHeroClass.get(0)), savedGame);
        scroll.addChild(profile);

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
            ProfileTitleView v = new ProfileTitleView(assets);
            v.update(hr.key, hr.key);
            v.setPadding(8);
            v.setBackground(assets.getNinePatch(hr.alignment.key));
            v.setSize(Game.Size.WIDTH * 0.9f, QQView.WRAP_CONTENT);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
