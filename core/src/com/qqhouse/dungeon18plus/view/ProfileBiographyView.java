package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileBiographyView extends AssetGroup {

    private final QQText title;
    private final QQText biography;
    private final int innerMargin;

    public ProfileBiographyView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        addChild(title);

        biography = new QQText(assets.getFont(Game.Font.HELP14));
        biography.setPadding(8);
        biography.setAlign(Align.left);
        addChild(biography);
    }

    public void update(String strTitle, String strBiography) {
        title.setText(strTitle);
        biography.setText(strBiography, true);
    }

    @Override
    public void setBackground(NinePatch bg) {
        biography.setBackground(bg);
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + title.getHeight() + innerMargin + biography.getHeight() + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        title.setSize(width - leftPadding - rightPadding, 32);
        biography.setSize(width - leftPadding - rightPadding, QQView.WRAP_CONTENT);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        title.setPosition(leftPadding, bottomPadding + biography.getHeight() + innerMargin);
        biography.setPosition(leftPadding, bottomPadding);
    }

}
