package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class TitleBarView2 extends AssetGroup {

    private QQImage icon;
    private QQText name;
    private QQIconText resource;

    public TitleBarView2(Assets assets) {
        super(assets);
    }

    public void reset(String iconKey, String nameKey) {
        if (null != iconKey) {
            icon = new QQImage(assets.getBlockee(iconKey));
            icon.setSize(32, 32); // 預設是 32 x 32 的 blockee...
            icon.setPosition(leftPadding, bottomPadding);
            addChild(icon);
        }

        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setText(assets.geti18n(nameKey));
        addChild(name);
    }

    public void reset(String iconKey, String nameKey, String resourceKey, Color color, String value) {
        if (null != iconKey) {
            icon = new QQImage(assets.getBlockee(iconKey));
            icon.setSize(32, 32); // 預設是 32 x 32 的 blockee...
            icon.setPosition(leftPadding, bottomPadding);
            addChild(icon);
        }

        if (null == resourceKey)
            resource = new QQIconText(assets.getFont(Game.Font.DIGITAL16));
        else
            resource = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16(resourceKey));
        resource.setColor(color);
        resource.setText(value);
        addChild(resource);

        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setText(assets.geti18n(nameKey));
        addChild(name);
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        super.onParentSizeChanged(width, height);
        if (0 < width) {
            name.setSize(QQView.WRAP_CONTENT, 32);
            if (null != resource)
                resource.setSize(QQView.WRAP_CONTENT, 16);
        }
    }

    @Override
    public void arrangeChildren() {
        if (null != icon)
            icon.setPosition(leftPadding, bottomPadding);

        if (null != resource)
            resource.setPosition(this.width - resource.getWidth() - rightPadding, bottomPadding + 8);

        if (null != name)
            name.setPosition((this.width - name.getWidth()) / 2, bottomPadding);
    }
}
