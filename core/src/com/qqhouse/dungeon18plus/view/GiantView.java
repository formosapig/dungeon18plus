package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.campaign.Campaigner;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class GiantView extends AssetGroup  {

    private QQImage icon;
    private QQIconText action;
    private QQProgress life;

    public GiantView(Assets assets) {
        super(assets);
        setPadding(8);
        setSize(MATCH_PARENT, 128);
    }

    public void reset(Campaigner giant) {
        icon = new QQImage(assets.getBlockee(giant.icon));
        icon.setSize(96, 96);
        addChild(icon);

        life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        life.setPercent(100);
        life.setSize(MATCH_PARENT, 8);
        life.setPosition(leftPadding, bottomPadding);
        addChild(life);

        setBackground(assets.getNinePatchBG("ordinary"));
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition((width - 96) / 2, bottomPadding + Game.Size.WIDGET_MARGIN + 8 + Game.Size.WIDGET_MARGIN);

    }
    /*
        IsParent series
     */
    /*
    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView child : childrenView)
            child.draw(batch, originX, originY);
    }
    */
}
