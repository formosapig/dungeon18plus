package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
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

public class GiantAlbumScreen extends QQScreen {

    public GiantAlbumScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    private ArrayList<GiantRace> allGiantRace;
    private QQScroll scroll;
    private ProfileView profile;

    @Override
    public void onEnter() {

        // data
        allGiantRace = savedGame.getGiant();//.getHeroClass();//equipments = savedGame.getEquipmentData();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        //group.setPosition(0, 0);
        addChild(group);

        // title bar with valkyrie
        TitleBarView2 valkyrie = new TitleBarView2(assets);
        valkyrie.reset("valkyrie", "valkyrie", null, Game.Colour.RARE, "");
        valkyrie.setSize(QQView.MATCH_PARENT, 48);
        valkyrie.setPadding(8);
        valkyrie.setBackground(assets.getNinePatchBG("neutral"));
        group.addChild(valkyrie);

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
        cyclePager.setPageChangedListener(new QQCyclePager.PageChangedListener() {
            @Override
            public void onChange(int page) {
                //Gdx.app.error("HeroAlbumScreen", "on cyclePager change");
                profile.update(savedGame.getGiantRecord(allGiantRace.get(page)), savedGame);
                scroll.scrollToTop();
            }
        });
        group.addChild(cyclePager);

        // hero profile
        scroll = new QQScroll(getViewport());
        scroll.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        scroll.setPadding(8);
        scroll.setBackground(assets.getNinePatchBG("help"));
        group.addChild(scroll);


        profile = new ProfileView(assets);
        profile.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        profile.update(savedGame.getGiantRecord(allGiantRace.get(0)), savedGame);
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
            return allGiantRace.size();
        }

        @Override
        public QQView getView(int index, QQView view) {
            GiantRace gr = allGiantRace.get(index);
            ProfileTitleView v = new ProfileTitleView(assets);
            v.update(gr.iconKey, gr.nameKey);
            v.setPadding(8);
            v.setBackground(assets.getNinePatchBG(gr.alignment.key));
            v.setSize(Game.Size.WIDTH * 0.9f, QQView.WRAP_CONTENT);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
