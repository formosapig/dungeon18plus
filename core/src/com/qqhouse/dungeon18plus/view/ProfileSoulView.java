package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileSoulView extends AssetGroup {

    private final ArrayList<SoulView> souls;
    private final int innerMargin;

    public ProfileSoulView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        souls = new ArrayList<>();
    }

    public void update(HeroClassRecord record) {
        childrenView.clear();

        for (SoulCount sc : record.souls) {
            SoulView sv = new SoulView(assets);
            sv.setSize(MATCH_PARENT, 48);
            sv.setPadding(8);//4, 4, 8, 8);
            sv.update(sc);
            addChild(sv);
        }
    }

    @Override
    public void setBackground(NinePatch bg) {
        for (QQView child : childrenView)
            child.setBackground(bg);
    }

    @Override
    public void resetWrapHeight() {
        float h = topPadding + bottomPadding;

        for (QQView child : childrenView) {
            h += child.getHeight();
            h += innerMargin;
        }
        h -= innerMargin;

        height = h;

        if (null != parent)
            parent.onChildSizeChanged(this);
        //arrangeChildren();
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        for (QQView child : childrenView) {
            if (child.isMatchWidth())
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        float anchorY = height - topPadding;
        for (QQView child : childrenView) {
            child.setPosition(leftPadding, anchorY - child.getHeight());
            anchorY -= child.getHeight() + innerMargin;
        }
    }

}
