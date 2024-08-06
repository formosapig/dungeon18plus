package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;

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
        item.setIcon(assets.getIcon(veteran.equipment.icon));
        item.setSize(32, 32);
        addChild(item);

        setBackground(assets.getBackgroundSet(veteran.heroClass.alignment.key));
    }

    public void reset(Legion legion) {
        icon = new QQImage(assets.getBlockee(legion.heroClass.key));
        icon.setSize(48, 48);
        addChild(icon);

        item = new ItemView();
        item.setIcon(assets.getIcon(legion.action.skill.icon));
        item.setSize(32, 32);
        addChild(item);

        life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        life.setPercent(100);
        //life.setSize(MATCH_PARENT, 8);
        //life.setPosition(leftPadding, bottomPadding);
        addChild(life);

        setBackground(assets.getBackgroundSet(legion.heroClass.alignment.key));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        if (null != life)
            life.setSize(width - leftPadding - rightPadding, 8);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition((width - 48) / 2, bottomPadding);
        if (null != item)
            item.setPosition(width - rightPadding - item.getWidth(), bottomPadding);
        if (null != life)
            life.setPosition(leftPadding, bottomPadding + 48 + 4);

    }
}
