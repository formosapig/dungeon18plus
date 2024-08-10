package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileActionView extends AssetGroup {

    private final QQText title;
    private final ArrayList<ActionView> actions;
    private final int innerMargin;

    public ProfileActionView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        title.setText(assets.geti18n("profile_action"));
        addChild(title);

        actions = new ArrayList<>();
    }

    public void update(HeroClassRecord record) {
        for (int i = childrenView.size() - 1; i > 0; --i)
            childrenView.remove(i);

        for (Action act : record.heroClass.actions) {
            ActionView av = new ActionView(assets);
            av.setSize(MATCH_PARENT, 48);
            av.setPadding(8);//4, 4, 8, 8);
            av.update(act);
            addChild(av);
        }
    }

    public void update(ArrayList<ActionSlot> actions) {
        for (int i = childrenView.size() - 1; i > 0; --i)
            childrenView.remove(i);

        for (ActionSlot slot : actions) {
            ActionView av = new ActionView(assets);
            av.setSize(MATCH_PARENT, 48);
            av.setPadding(8);//4, 4, 8, 8);
            av.update(slot.action);
            addChild(av);
        }
    }

    @Override
    public void setBackground(NinePatch bg) {
        for (int i = 1; i < childrenView.size(); ++i) {
            QQView child = childrenView.get(i);
            child.setBackground(bg);
        }
    }

    @Override
    public void resetWrapHeight() {
        float h = topPadding + 32 + 4 + bottomPadding;

        for (int i = 1; i < childrenView.size(); ++i) {
            QQView child = childrenView.get(i);
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

        title.setSize(width, 32);

        for (int i = 1; i < childrenView.size(); ++i) {
            QQView child = childrenView.get(i);
            if (child.isMatchWidth())
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        title.setPosition(leftPadding, height - topPadding - 32);

        float anchorY = height - topPadding - 32 - 4;
        for (int i = 1; i < childrenView.size(); ++i) {
            QQView child = childrenView.get(i);
            child.setPosition(leftPadding, anchorY - child.getHeight());
            anchorY -= child.getHeight() + innerMargin;
        }
    }

}
