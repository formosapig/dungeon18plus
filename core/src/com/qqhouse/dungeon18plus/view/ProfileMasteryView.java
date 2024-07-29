package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileMasteryView extends AssetGroup {

    private final QQText title;
    private final ArrayList<MasteryView> masteries;
    private final int innerMargin;
    private float childWidth, childHeight;
    private final int cowNum = 6;

    public ProfileMasteryView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        title = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        title.setPadding(8);
        title.setAlign(Align.left);
        title.setText(assets.geti18n("profile_mastery"));
        addChild(title);

        masteries = new ArrayList<>();
        childHeight = 48;
    }

    public void update(HeroClassRecord record) {
        for (int i = childrenView.size() - 1; i > 0; --i)
            childrenView.remove(i);

        for (EquipmentMastery em : record.equips) {
            MasteryView mv = new MasteryView(assets);
            mv.update(em);
            mv.setPadding(8);
            mv.setSize(childWidth, childHeight);
            mv.setBackground(assets.getNinePatchBG("special"));
            addChild(mv);
        }
    }

    @Override
    public void resetWrapHeight() {
        if (childrenView.isEmpty())
            return;
        int rows = (childrenView.size() - 2) / cowNum + 1;
        height = topPadding + bottomPadding + rows * childHeight + (rows - 1) * innerMargin + 32 + 4;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        title.setSize(width, 32);

        childWidth = (width - innerMargin * 3) / cowNum;
        for (int i = 1; i < childrenView.size(); ++i) {
            QQView child = childrenView.get(i);
            child.setSize(childWidth, childHeight);
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        float gapWidth = childWidth + innerMargin;
        float gapHeight = childHeight + innerMargin;

        title.setPosition(leftPadding, height - topPadding - 32);

        for (int i = 0, s = childrenView.size() - 1; i < s; i++) {
            QQView child = childrenView.get(i + 1);
            child.setPosition(leftPadding + (i % cowNum) * gapWidth, height - topPadding - ((int)(i / cowNum) + 1) * gapHeight - 32 - 4);
        }
    }

}
