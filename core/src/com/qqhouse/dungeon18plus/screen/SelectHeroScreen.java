package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.view.PreviewView;
import com.qqhouse.dungeon18plus.view.TitleBarView;
import com.qqhouse.ui.QQClickListener;
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectHeroScreen extends QQScreen implements QQClickListener {
    public SelectHeroScreen(Viewport viewport) {
        super(null, viewport);
    }

    // resource kept
    private BitmapFont fntTitle;
    private BitmapFont fntName;
    private BitmapFont fntDesc;
    private QQListView list;

    @Override
    public void onEnter() {

        // initial language bundle.
        lanBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/dungeon18plus"));
        // initial font.
        // bitmap font...
        fntTitle = createFont(18, Color.WHITE, "請選擇英雄");

        // one title view ..., just print select hero ?
        TitleBarView title = new TitleBarView(
                this,
                new Texture(Gdx.files.internal("blockee/fairy.png")),
                fntTitle,
                "Select Hero : ");

        //addView(title);
        title.setPadding(8);
        title.setSize(G.WIDTH, 48);
        title.setPosition(0, G.HEIGHT - 48);

        ArrayList<HeroClass> tmp = new ArrayList<>();
        tmp.add(HeroClass.NOVICE);
        tmp.add(HeroClass.BARBARIAN);
        tmp.add(HeroClass.BERSERKER);
        tmp.add(HeroClass.DRAGOON);
        tmp.add(HeroClass.THIEF);
        tmp.add(HeroClass.ASSASSIN);
        tmp.add(HeroClass.CRUSADER);
        tmp.add(HeroClass.FAIRY);
        tmp.add(HeroClass.SKELETON_KING);

        fntName = createFont("NotoSansTC-Bold.ttf", 20, new Color(0x9E8064FF), "");
        fntDesc = createFont(14, Color.WHITE, "");

        // list view of hero preview view
        list = (QQListView) new QQListView(this)
                .size(G.WIDTH, G.HEIGHT - title.getHeight() - 4).
                position(0, 0).
                padding(4);
        addView(list);

        for (int i = 0, s = tmp.size(); i < s; ++i) {
            HeroClass hero = tmp.get(i);
            PreviewView view = new PreviewView(this,
                    hero.alignment.key, // Alignment decides background.
                    new Texture(Gdx.files.internal("blockee//" + hero.key + ".png")),
                    fntName,
                    lanBundle.get(hero.key),
                    fntDesc,
                    lanBundle.get(hero.key+"_help"));
            view.setPadding(8);
            view.setSize(QQView.FILL_PARENT, QQView.WRAP_CONTENT);
            view.addQQClickListener(this, hero.code);
            list.addView(view);
        }
    }

    @Override
    public void onLeave() {

        list.dispose();
        fntTitle.dispose();
        fntName.dispose();
        fntDesc.dispose();

    }

    /*
        click to select hero class...
     */
    @Override
    public void onClick(int index) {

    }
}
