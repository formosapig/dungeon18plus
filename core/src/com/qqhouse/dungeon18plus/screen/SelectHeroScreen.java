package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.view.TitleBarView;
import com.qqhouse.ui.QQListView;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

public class SelectHeroScreen extends QQScreen {
    public SelectHeroScreen(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void onEnter() {
        // one title view ..., just print select hero ?
        TitleBarView title = new TitleBarView(this,
                new Texture(Gdx.files.internal("blockee/fairy.png")),
                "Select Hero : ");

        addView(title);
        title.setPadding(8);
        title.setSize(G.WIDTH, 48);
        title.setPosition(0, G.HEIGHT - 48);

        // one list view ...
        QQListView.Adapter adapter = new QQListView.Adapter() {
            @Override
            public int count() {
                return 0;
            }

            @Override
            public QQView getView(int index) {
                return null;
            }
        };
        QQListView listView = new QQListView(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void onLeave() {

    }
}
