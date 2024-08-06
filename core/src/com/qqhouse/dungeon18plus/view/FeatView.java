package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import org.jetbrains.annotations.NotNull;

public class FeatView extends AssetGroup {

    private final QQImage icon;
    private final QQText name, help;

    public FeatView(Assets assets) {
        super(assets);

        icon = new QQImage();
        icon.setSize(32, 32);
        addChild(icon);

        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        addChild(name);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setAlign(Align.left);
        addChild(help);
    }

    public void update(@NotNull Feat feat) {
        icon.setImage(assets.getIcon(feat.icon));
        name.setText(assets.geti18n(feat.name));
        help.setText(assets.geti18n(feat.help), true);
    }

    @Override
    public void resetWrapHeight() {
        if (0 == this.width)
            return;

        this.height = topPadding + name.getHeight() + 2 + help.getHeight() + bottomPadding;

        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    /*
        IsParent series
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width)
            return;
        float w = width - leftPadding - 32 - 8 - rightPadding;
        // name
        name.setSize(w, 24);
        // help
        help.setSize(w, QQView.WRAP_CONTENT);
    }

//    @Override
//    public void onChildSizeChanged(QQView child) {
//        if (wrapHeight) {
//            final float preHeight = this.height;
//            resetWrapHeight();
//            if (preHeight != this.height) {
//                arrangeChildren();
//            }
//        }
//    }

    @Override
    public void arrangeChildren() {
        //Gdx.app.error("ItemDetailView", "arrangeChildren = " + this.width + "," + this.height);
        if (0 == width || 0 == height)
            return;
        float l = leftPadding + 32 + 8;
        // item icon
        icon.setPosition(leftPadding, height - topPadding - 32);
        // name
        name.setPosition(l, bottomPadding + help.getHeight() + 2);
        // help
        help.setPosition(l, bottomPadding);
    }
}
