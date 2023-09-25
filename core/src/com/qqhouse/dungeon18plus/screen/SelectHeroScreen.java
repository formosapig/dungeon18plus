package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.view.PreviewView;
import com.qqhouse.dungeon18plus.view.TitleBarView;
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectHeroScreen extends QQScreen implements QQListView.Adapter {
    public SelectHeroScreen(Viewport viewport) {
        super(viewport);
    }

    // resource
    private BitmapFont font;
    private QQListView list;
    private ArrayList<HeroClass> tmp;
    private ArrayList<PreviewView> views;
    private BitmapFont fntName;
    private BitmapFont fntDesc;

    @Override
    public void onEnter() {
        // initial font.
        // bitmap font...
        font = createFont(18, Color.WHITE, "請選擇英雄");

        // one title view ..., just print select hero ?
        TitleBarView title = new TitleBarView(
                this,
                new Texture(Gdx.files.internal("blockee/fairy.png")),
                font,
                "Select Hero : ");

        //addView(title);
        title.setPadding(8);
        title.setSize(G.WIDTH, 48);
        title.setPosition(0, G.HEIGHT - 48);

        tmp = new ArrayList<HeroClass>();
        tmp.add(HeroClass.NOVICE);
        tmp.add(HeroClass.BARBARIAN);

        fntName = createFont(24, Color.BROWN, "");
        fntDesc = createFont(16, Color.WHITE, "");

        views = new ArrayList<PreviewView>();

        // add hero profile view...
        for (int i = 0, s = tmp.size(); i < s; ++i) {
            HeroClass hero = tmp.get(i);
            PreviewView view = new PreviewView(this,
                    new Texture(Gdx.files.internal("blockee//" + hero.key + ".png")),
                    fntName,
                    "Novice",
                    fntDesc,
                    "this is novices....");
            view.setPadding(8);
            view.setSize(G.WIDTH, 60);
            views.add(view);
        }

        // one list view ...
        list = new QQListView(this);
        list.setAdapter(this);
        list.setPosition(0, 0);
        list.setSize(G.WIDTH, G.HEIGHT - 48 - 4);

    }

    @Override
    public void onLeave() {
        list.dispose();
    }

    /*
        QQListView's adapter
     */
    @Override
    public int count() {
        return views.size();
    }

    @Override
    public QQView getView(int index) {
        return views.get(index);
    }
}
