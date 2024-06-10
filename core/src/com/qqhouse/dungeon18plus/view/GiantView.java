package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.dungeon18plus.struct.campaign.Campaigner;
import com.qqhouse.dungeon18plus.struct.campaign.Giant;
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

        action = new QQIconText(assets.getFont(Game.Font.LOOT_INFO));
        action.setColor(Game.Colour.SPEED);
        action.setSize(54, 24);
        addChild(action);

        life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        life.setPercent(100);
        life.setSize(MATCH_PARENT, 8);
        life.setPosition(leftPadding, bottomPadding);
        addChild(life);

        setBackground(assets.getNinePatchBG("ordinary"));
    }

    public void update(Campaigner giant) {
        if (null != action && null != giant.action) {
            action.setIcon(assets.getItem(giant.action.skill.icon));
            action.setText(Integer.toString(giant.coolDown));
        }

        if (null != life) {
            life.setPercent(giant.life * 100 / giant.maxLife);
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
