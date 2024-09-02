package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.Campaigner;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;

public class GiantView extends AssetGroupButton  {

    private QQImage icon;
    private QQIconText action;
    private QQProgress life;

    public GiantView(Assets assets) {
        super(assets);
        setPadding(8);
        setSize(MATCH_PARENT, 128);
    }

    public void reset(Campaigner giant) {
        icon = new QQImage(assets.getBlockee(giant.iconKey));
        icon.setSize(96, 96);
        addChild(icon);

        action = new QQIconText(assets.getFont(Game.Font.LOOT_INFO));
        action.setColor(Game.Colour.SPEED);
        action.setSize(54, 24);
        addChild(action);

        //life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        life = new QQProgress(assets.getNinePatch("progress_back"), assets.getNinePatch("progress_yellow_primary"), assets.getNinePatch("progress_yellow_secondary"));
        life.setPercent(100);
        life.setSecondaryProgress(0);
        life.setSize(MATCH_PARENT, 10);
        life.setPosition(leftPadding, bottomPadding);
        addChild(life);

        setBackground(assets.getBackgroundSet(giant.bgKey));
    }

    public void update(Campaigner giant) {
        if (null != action && null != giant.action) {
            action.setIcon(assets.getIcon(giant.action.skill.icon));
            action.setText(Integer.toString(giant.coolDown));
        }

        if (null != life) {
            life.setPercent(giant.life * 100 / giant.maxLife);
            life.setSecondaryProgress(giant.nextLife * 100 / giant.maxLife);
        }
    }



    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition((width - 96) / 2, bottomPadding + Game.Size.WIDGET_MARGIN + 8 + Game.Size.WIDGET_MARGIN);

        if (null != action)
            action.setPosition(width - rightPadding - action.getWidth(), height - topPadding - action.getHeight());
    }
}
