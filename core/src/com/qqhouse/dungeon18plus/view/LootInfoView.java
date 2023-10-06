package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.ui.QQView;

public class LootInfoView extends QQView {
    /*
        needs background..
     */
    public LootInfoView(Texture bg) {
        // use background/loot_info.png....
        bgNormal = new NinePatch(bg, 4, 4, 4, 4);
    }




}
