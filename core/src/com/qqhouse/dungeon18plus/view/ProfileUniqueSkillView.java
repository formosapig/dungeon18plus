package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileUniqueSkillView extends AssetGroup {

    private final QQText title;
    private final int innerMargin;

    public ProfileUniqueSkillView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatch("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        title.setText(assets.geti18n("profile_unique_skill"));
        addChild(title);
    }

    public void update(GiantRecord record) {
        for (int i = childrenView.size() - 1; i > 0; --i)
            childrenView.remove(i);

        for (UniqueSkill skill : record.skills) {
            UniqueSkillView v = new UniqueSkillView(assets);
            v.setSize(MATCH_PARENT, 48);
            v.setPadding(8);
            v.reset(skill.get(Game.Setting.GENERAL_MASTERY_MAX), record.race.attr);
            addChild(v);
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
