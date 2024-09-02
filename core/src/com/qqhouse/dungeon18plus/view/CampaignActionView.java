package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignAction;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.Locale;

public class CampaignActionView extends AssetGroupButton {

    private QQImage icon, skill;
    private QQText info;
    private QQIconText time;


    public CampaignActionView(Assets assets) {
        super(assets);
    }

    public void reset(CampaignAction action) {
        icon = new QQImage(assets.getBlockee(action.iconKey));
        icon.setSize(32, 32);
        addChild(icon);

        // no skill icon and display info
        if (null != action.skillIconKey) {
            skill = new QQImage(assets.getIcon(action.skillIconKey));
            skill.setSize(32, 32);
            addChild(skill);
        }

        // info
        if (null != action.infoKey) {
            info = new QQText(assets.getFont(Game.Font.NAME20));
            info.setText(assets.geti18n(action.infoKey));
            info.setColor(Game.Colour.RARE);
            info.setSize(QQView.WRAP_CONTENT, 32);
            addChild(info);
        }

        // time
        time = new QQIconText(assets.getFont(Game.Font.TIME_TICK), assets.getIcon("icon/gray_time"), 24);
        time.setColor(Game.Colour.CALENDAR);
        time.setText(Integer.toString(action.time));
        time.setSize(/*QQView.WRAP_CONTENT*/60, 32);
        time.setAlign(Align.right);
        //time.setBackground(assets.getNinePatch("chaotic_down"));//assets.getNinePatchBG("blessed"));//assets.getBackground("blessed"));
        addChild(time);

        // background
        setBackground(assets.getNinePatch(action.bgKey));
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        if (null != icon)
            icon.setPosition(leftPadding, bottomPadding);

        if (null != skill)
            skill.setPosition(leftPadding + 32 + 8, bottomPadding);

        if (null != info)
            info.setPosition(leftPadding + 32 + 8 + 32 + 8, bottomPadding);

        if (null != time)
            time.setPosition(width - rightPadding - time.getWidth(), bottomPadding);

    }

}
