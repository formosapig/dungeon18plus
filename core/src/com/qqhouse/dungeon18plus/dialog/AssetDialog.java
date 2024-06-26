package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQCustomDialog;

public class AssetDialog extends QQCustomDialog {

    private Assets assets;
    public AssetDialog(Assets assets) {
        super();
        this.assets = assets;

        // set background
        bgNormal = assets.getNinePatchBG("black");
        bgNormal.setColor(new Color(1, 1, 1, 0.66f));
    }

}
