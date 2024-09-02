package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.view.ExtendSoulSizeButton;
import com.qqhouse.dungeon18plus.view.ProfileSoulView;
import com.qqhouse.dungeon18plus.view.ProfileTitleView;
import com.qqhouse.dungeon18plus.view.ProfileView;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.ui.QQCyclePager;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQScroll;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class SoulMasterScreen extends QQScreen {

    private ArrayList<HeroClass> heroClassWithSoul;
    private QQCyclePager cyclePager;
    private ExtendSoulSizeButton extendSize;
    private ProfileSoulView soulView;

    public SoulMasterScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        super(savedGame, viewport, assets);
    }

    @Override
    public void onEnter() {
        // data
        heroClassWithSoul = savedGame.getHeroClassWithSoul();

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        group.setPosition(0, 0);
        addChild(group);

        // title bar with valkyrie
        TitleBarView2 valkyrie = new TitleBarView2(assets);
        valkyrie.reset("valkyrie", "valkyrie", null, Game.Colour.RARE, "");
        valkyrie.setSize(QQView.MATCH_PARENT, 48);
        //valkyrie.setPosition(0, Game.Size.HEIGHT - 48);
        valkyrie.setPadding(8);
        valkyrie.setBackground(assets.getNinePatch("neutral"));
        group.addChild(valkyrie);

        // split line...
        QQView line = new QQView();
        line.setSize(QQView.MATCH_PARENT, 4);//Game.Size.WIDTH - Game.Size.WIDGET_MARGIN, 4);
        //line.setPosition(Game.Size.WIDGET_MARGIN / 2, Game.Size.HEIGHT - 48 - 4 - Game.Size.WIDGET_MARGIN);
        line.setBackground(assets.getNinePatch("white"));
        group.addChild(line);

        // all hero ...
        cyclePager = new QQCyclePager(getViewport(), Game.Size.WIDGET_MARGIN);
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
                update(page);
            }
        });
        group.addChild(cyclePager);

        // extend size button
        extendSize = new ExtendSoulSizeButton(assets);
        extendSize.setPadding(8);
        extendSize.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        extendSize.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                final int currentPage = cyclePager.getCurrentPage();
                HeroClassRecord record = savedGame.getHeroClassRecord(heroClassWithSoul.get(currentPage));
                if (record.extendSoulSize()) {
                    update(currentPage);
                    cyclePager.updateAll();
                }
            }
        });
        group.addChild(extendSize);

        // soul view
        soulView = new ProfileSoulView(assets, Game.Size.INNER_MARGIN);
        soulView.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        group.addChild(soulView);

        // default update
        update(0);
    }

    private void update(int index) {
        HeroClassRecord record = savedGame.getHeroClassRecord(heroClassWithSoul.get(index));
        extendSize.update(record);
        soulView.update(record, removeSoul);
        soulView.setBackground(assets.getNinePatch(record.heroClass.alignment.key));
    }

    private QQPressListener removeSoul = new QQPressAdapter() {
        @Override
        public void onPress(int index) {
            //super.onPress(index);
            Gdx.app.error("SoulMasterScreen", "remove soul " + index);
            final int currentPage = cyclePager.getCurrentPage();
            HeroClassRecord record = savedGame.getHeroClassRecord(heroClassWithSoul.get(currentPage));
            record.removeSoulAt(index);
            //if (record.extendSoulSize()) {
                update(currentPage);
                //cyclePager.updateAll();
            //}
        }
    };

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
            return heroClassWithSoul.size();
        }

        @Override
        public QQView getView(int index, QQView view) {
            HeroClass hr = heroClassWithSoul.get(index);
            HeroClassRecord record = savedGame.getHeroClassRecord(hr);
            ProfileTitleView v = new ProfileTitleView(assets);
            v.update(hr.key, hr.key, record.coin);
            v.setPadding(8);
            v.setBackground(assets.getNinePatch(hr.alignment.key));
            v.setSize(Game.Size.WIDTH * 0.9f, QQView.WRAP_CONTENT);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {
            if (view instanceof ProfileTitleView) {
                HeroClass hr = heroClassWithSoul.get(index);
                HeroClassRecord record = savedGame.getHeroClassRecord(hr);
                ((ProfileTitleView)view).update(hr.key, hr.key, record.coin);
            }
        }
    };
}
