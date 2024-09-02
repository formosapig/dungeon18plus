package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQCustomDialog;
import com.qqhouse.ui.QQView;

public class AssetDialog extends QQCustomDialog {

    protected final Assets assets;
    public AssetDialog(Assets assets) {
        super(Game.Size.WIDTH, Game.Size.HEIGHT, Game.Size.DIALOG_MARGIN);
        this.assets = assets;

        // set background
        bgNormal = assets.getNinePatchBG("black");
        bgNormal.setColor(new Color(1, 1, 1, 0.66f));
    }

    // 因為 assets 不屬於 library ,所以目前還無法實作內置 Dialog ...
    // 主要是需要一張黑色的背景
    public AssetDialog(Assets assets, boolean isModal, QQView customView) {
        this(assets);
        setModal(isModal);
        setCustomView(customView);
    }
}
