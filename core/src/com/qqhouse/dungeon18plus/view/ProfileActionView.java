package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileActionView extends AssetGroup {

    private final ArrayList<ActionView> actions;
    private final int innerMargin;

    public ProfileActionView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        actions = new ArrayList<>();
    }

    public void update(HeroClassRecord record) {
        childrenView.clear();

        for (Action act : record.heroClass.actions) {
            ActionView av = new ActionView(assets);
            av.setSize(MATCH_PARENT, 40);
            av.setPadding(4, 4, 8, 8);
            av.update(act);
            addChild(av);
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
