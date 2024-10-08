package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.struct.campaign.Legion;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQFixedProgress;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;

public class LegionHeroView extends AssetGroupButton {

    private QQImage icon;
    private ItemView item;
    private QQFixedProgress coolDown;
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
        item.setItem(assets, veteran.equipment);
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

        //life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        life = new QQProgress(assets.getNinePatch("progress_back"), assets.getNinePatch("progress_yellow_primary"), assets.getNinePatch("progress_yellow_secondary"));
        life.setPercent(100);
        life.setSecondaryProgress(0);
        //life.setSize(MATCH_PARENT, 8);
        //life.setPosition(leftPadding, bottomPadding);
        addChild(life);

        coolDown = new QQFixedProgress(assets.getFixedProgressParameter("time"));//assets.getProgress(), assets.get..., 3, 3, 26);
        coolDown.setPercent(100);
        coolDown.setVisible(false);
        addChild(coolDown);

        setBackground(assets.getBackgroundSet(legion.heroClass.alignment.key));

        // default is not enabled.
        //setEnabled(false);
    }

    public void update(Legion legion) {
        life.setPercent(legion.life * 100 / legion.maxLife);
        life.setSecondaryProgress(legion.nextLife * 100 / legion.maxLife);
        //setEnabled((0 >= legion.coolDown) && !legion.action.auto);

        coolDown.setPercent(0 == legion.maxCoolDown ? 0 : (legion.maxCoolDown - legion.coolDown) * 100 / legion.maxCoolDown);

        item.setVisible(0 >= legion.coolDown);
        coolDown.setVisible(0 < legion.coolDown);

        //cooldown.setPercent(100);
        //item.setCoolDown(50);
        //Gdx.app.error("LegionHeroView.java", "legion = " + legion + " CD : " + legion.coolDown);
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        if (null != life)
            life.setSize(width - leftPadding - rightPadding, 12);
        if (null != coolDown)
            coolDown.setSize(10, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition(leftPadding/*(width - 48) / 2*/, bottomPadding);
        if (null != item)
            item.setPosition(width - rightPadding - item.getWidth(), bottomPadding);
        if (null != coolDown)
            coolDown.setPosition(width - rightPadding - coolDown.getWidth(), bottomPadding);
        if (null != life)
            life.setPosition(leftPadding, bottomPadding + 48 - 12);

    }
}
