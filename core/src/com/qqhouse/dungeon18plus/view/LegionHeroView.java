package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class LegionHeroView extends AssetGroupButton {

    private QQImage icon;
    private ItemView item;
    private QQProgress life;

    public LegionHeroView(Assets assets) {
        super(assets);
        setPadding(8);
    }

    public void reset(Veteran veteran) {
        icon = new QQImage(assets.getBlockee(veteran.heroClass.key));
        icon.setSize(48, 48);
        addChild(icon);

        item = new ItemView();
        item.setIcon(assets.getItem(veteran.equipment.icon));
        item.setSize(32, 32);
        addChild(item);

        setBackground(assets.getBackgroundSet(veteran.heroClass.alignment.key));
    }

    public void reset(Legion legion) {

    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition((width - 48) / 2, bottomPadding);
        if (null != item)
            item.setPosition(width - rightPadding - item.getWidth(), bottomPadding);

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
