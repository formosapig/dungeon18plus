package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQCustomDialog;

public class CustomDialog extends QQCustomDialog {

    private Assets assets;
    public CustomDialog(Assets assets) {
        super();
        this.assets = assets;

        // set background
        bgNormal = assets.getNinePatchBG("black");
        bgNormal.setColor(new Color(1, 1, 1, 0.66f));
    }

}
