package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class PreviewView2 extends AssetButton implements QQView.IsParent {

    private QQImage icon;
    private QQText level, name, help;
    private QQIconText extra;

    /*
        icon : blockee icon
        level : blockee level (if needs), at upper left of icon. Game.Font.DIGITAL18
        name : horizontal central, next to icon. Game.Font.NAME20
        extra : at upper right, may overlap name field. Game.Font.DIGITAL18
        help : description of hero class, multi line, max lines = 3... Game.Font.HELP14
     */

    public PreviewView2(Assets assets) {
        super(assets);
        setPadding(8);
    }

    public void reset(String iconKey, String nameKey, String helpKey, String bgKey) {
        // set background
        setBackground(assets.getBackgroundSet(bgKey));

        // icon
        icon = new QQImage(assets.getBlockee(iconKey));
        //blockee.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        icon.setPosition(8, 8);
        addChild(icon);

        // name
        name = new QQText(assets.getFont(Game.Font.NAME20));
        //name.setBackground(assets.getNinePatchBG("refined"));
        name.setColor(Game.Colour.RARE);
        name.setText(assets.geti18n(nameKey));
        //name.setSize(this.width - 64, 24);
        //name.setPosition(64, 32);
        addChild(name);

        // help
        help = new QQText(assets.getFont(Game.Font.HELP14));
        //help.setBackground(assets.getNinePatchBG("refined"));
        help.setText(assets.geti18n(helpKey), true);
        help.setAlign(Align.left);
        help.setPosition(64, 8);
        //help.setWrapWidth();
        addChild(help);

    }

    @Override
    protected void resetWrapHeight() {
        // if width not set, can not calculate height of font with multi line.
        if (0 == width) // QQView.MATCH_PARENT == width)
            return;

        // Text will calculate their height ...
        float h = topPadding + bottomPadding + name.getHeight() + help.getHeight() + Game.Size.INNER_MARGIN;
        this.height = Math.max(48 + 8 + 8, h);
    }

    @Override
    public void dispose() {}

    /*
        IsParent series
     */
    private ArrayList<QQView> childrenViews = new ArrayList<>();
    @Override
    public void addChild(QQView view) {
        childrenViews.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 < width) {
            // name = fix height.
            name.setSize(width - 64 - 8, 24);
            // help...
            help.setSize(width - 64 - 8, QQView.WRAP_CONTENT);
        }
    }

    @Override
    public void onChildSizeChanged(QQView child) {}

    @Override
    public void arrangeChildren() {
        if (null == icon || null == name)
            return;
        icon.setPosition(leftPadding, height - topPadding - 48);
        name.setPosition(64, this.height - 32);
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView child : childrenViews)
            child.draw(batch, originX, originY);
    }
}
