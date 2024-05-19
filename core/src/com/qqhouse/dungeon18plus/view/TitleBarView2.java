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
        //setPadding(4);
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
        //resource.setSize(QQView.WRAP_CONTENT, 32);
        //resource.setPosition(this.width - resource.getWidth() - rightPadding, bottomPadding);
        resource.setColor(color);
        resource.setText(value);
        addChild(resource);

        name = new QQText(assets.getFont(Game.Font.NAME20));
        //name.setSize(resource.getX() - innerMargin * 2 -(leftPadding + 32), 32);
        //name.setPosition(leftPadding + 32 + innerMargin, bottomPadding);
        name.setText(assets.geti18n(nameKey));
        addChild(name);
    }

    @Override
    public void arrangeChildren() {
        if (null != icon)
            icon.setPosition(leftPadding, bottomPadding);

        if (null != resource) {
            resource.setSize(QQView.WRAP_CONTENT, 16);
            resource.setPosition(this.width - resource.getWidth() - rightPadding, bottomPadding + 8);
        }

        if (null != name) {
            //name.setSize(resource.getX() - innerMargin * 2 - (leftPadding + 32), 32);
            //name.setPosition(leftPadding + 32 + innerMargin, bottomPadding);
            name.setSize(QQView.WRAP_CONTENT, 32);
            name.setPosition((this.width - name.getWidth()) / 2, bottomPadding);
        }
    }
}
