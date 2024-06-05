package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class PurePreviewView extends AssetGroup {

    private QQImage blockee;
    private QQText name, help;

    public PurePreviewView(Assets assets) {
        super(assets);
        setPadding(Game.Size.BLOCKEE_PADDING);
        //setBackground(assets.getNinePatchBG("blessed"));
    }

    public void reset() {//String blockeeKey, String alignmentKey) {
        blockee = new QQImage();
        addChild(blockee);

        // name
        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        //name.setText(assets.geti18n(blockeeKey));
        //name.setSize(this.width - 64, 24);
        //name.setPosition(64, 32);
        addChild(name);

        // help
        help = new QQText(assets.getFont(Game.Font.HELP14));
        //help.setText(assets.geti18n(blockeeKey + "_help"));
        //help.setTruncate("...");
        help.setAlign(Align.left);
        //help.setWrap(true);
        //help.setPosition(64, 8);
        //help.setWrapWidth();
        addChild(help);

        // background
        //bgNormal = assets.getNinePatchBG(alignmentKey);
    }

    public void update(String blockeeKey, String nameKey, String helpKey, String alignmentKey) {
        // data
        blockee.setImage(assets.getBlockee(blockeeKey));
        name.setText(assets.geti18n(nameKey));
        help.setText(assets.geti18n(helpKey), true);

        // background
        bgNormal = assets.getNinePatchBG(alignmentKey);
    }


    @Override
    public void resetWrapHeight() {
        // calculate height
        if (0 == this.width)
            return;

        // decide this.height
        this.height = Math.max(topPadding + bottomPadding + 24 + help.getHeight(), 64);
        Game.trace(this, "resetWrapHeight h=%.0f", this.height);
        //Gdx.app.error("PurePreviewView.resetWrapHeight", "this.height = " + this.height);

        if (null != parent)
            parent.onChildSizeChanged(this);

    }

    /*
        IsParent series
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        // set name, help's width after get this.width
        // 64 = leftPadding(8) + blockee Size (48) + blockeePadding (8)
        float w = this.width - 64 - rightPadding;
        name.setSize(w, 24);
        help.setSize(w, QQView.WRAP_CONTENT);
    }

    @Override
    public void onChildSizeChanged(QQView child) {
        if (wrapHeight) {
            final float preHeight = this.height;
            resetWrapHeight();
            if (preHeight != this.height) {

                arrangeChildren();
            }
        }
    }

    @Override
    public void arrangeChildren() {
        Game.trace(this, "arrangeChildren %.0f,%.0f", this.width, this.height);
        if (0 >= this.width || 0 >= this.height)
            return;

        // blockee's position
        blockee.setPosition(leftPadding, this.height - 48 - topPadding);

        // name's position...
        name.setPosition(64, this.height - 32);

        // help's position
        help.setPosition(64, bottomPadding);
    }
}