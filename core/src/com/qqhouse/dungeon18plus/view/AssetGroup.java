package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.ui.QQGroup;

public class AssetGroup extends QQGroup {

    protected Assets assets;
    public AssetGroup(Assets assets) {
        this.assets = assets;
    }
    public AssetGroup(Assets assets, int direct) {
        super(direct);
        this.assets = assets;
    }
    public AssetGroup(Assets assets, int direct, int margin) {
        super(direct, margin);
        this.assets = assets;
    }

}
