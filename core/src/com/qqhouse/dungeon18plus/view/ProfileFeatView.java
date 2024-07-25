package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class ProfileFeatView extends AssetGroup {

    private final ArrayList<FeatView> feats;
    private final int innerMargin;

    public ProfileFeatView(Assets assets, int innerMargin) {
        super(assets);

        this.innerMargin = innerMargin;

        feats = new ArrayList<>();
    }

    public void update(HeroClassRecord record) {
        childrenView.clear();

        int featCount = Long.bitCount(record.heroClass.feat);
        for (Feat feat : Feat.values()) {
            if (feat.in(record.heroClass.feat)) {
                //Gdx.app.error("ProfileFeatView", "feat = " + feat);
                FeatView fv = new FeatView(assets);
                fv.setSize(MATCH_PARENT, WRAP_CONTENT);
                fv.setPadding(8);//4, 4, 8, 8);
                fv.update(feat);
                addChild(fv);
                // check feat count
                featCount --;
                if (featCount == 0)
                    break;
            }
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
