package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileMasteryView extends AssetGroup {

    private final ArrayList<MasteryView> masteries;
    private final int innerMargin;
    private float childWidth, childHeight;
    private final int cowNum = 6;

    public ProfileMasteryView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;
        masteries = new ArrayList<>();
        childHeight = 48;
    }

    public void update(HeroClassRecord record) {
        childrenView.clear();

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
        int rows = (childrenView.size() - 1) / cowNum + 1;
        height = topPadding + bottomPadding + rows * childHeight + (rows - 1) * innerMargin;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        childWidth = (width - innerMargin * 3) / cowNum;
        for (QQView child : childrenView)
            child.setSize(childWidth, childHeight);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        float gapWidth = childWidth + innerMargin;
        float gapHeight = childHeight + innerMargin;

        for (int i = 0, s = childrenView.size(); i < s; i++) {
            childrenView.get(i).setPosition(leftPadding + (i % cowNum) * gapWidth, height - topPadding - ((int)(i / cowNum) + 1) * gapHeight);

        }
    }

}
