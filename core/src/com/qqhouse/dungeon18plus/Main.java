package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.screen.TitleScreen;
import com.qqhouse.ui.QQGameMachine;
import com.qqhouse.ui.QQScreen;

public class Main extends QQGameMachine {

    public static final int STATE_TITLE = 0;
    //public static final int






    @Override
    public void create() {
        initial(G.WIDTH, G.HEIGHT);

        // add states....
        addState(STATE_TITLE, new TitleScreen());


        // enter title (default state)
        changeState(STATE_TITLE);

    }

}
