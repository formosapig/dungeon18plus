package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.struct.Operation;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.tools.QQTextUtils;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQView;

public class CampaignResultView extends AssetGroup {

    private final QQView[] background;
    private final QQImage[] icons;
    private final QQIconText[] values;

    // padding 做用於每一個 sub view ... 但是  2 subview 2 subview 2 subview 2

    public CampaignResultView(Assets assets) {
        super(assets);
        background = new QQView[3];
        icons = new QQImage[3];
        values = new QQIconText[3];
    }

    public void reset(CampaignResult result) {
        for (int i = 0; i < 3; ++i) {
            if (!QQTextUtils.isEmpty(result.icon[i])) {
                background[i] = new QQView();
                background[i].setBackground(assets.getNinePatch("campaign_left_result"));
                addChild(background[i]);

                // icons...
                icons[i] = new QQImage(assets.getBlockee(result.icon[i]));
                icons[i].setSize(24, 24);
                addChild(icons[i]);

                // icon and value, check special type...
                switch (result.type[i]) {
                    case Operation.GIANT_SOUL: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon(Soul.find(result.value[i]).iconKey));
                        values[i].setText("");
                    }
                    break;
                    case Operation.DEATH: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.CAMPAIGN));
                        values[i].setColor(Game.Colour.COUNT); // white.
                        values[i].setText(assets.geti18n("rip"));
                    }
                    break;
                    case Operation.DAMAGE: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon("icon/damage"), 24);// + Operation.getIconName(result.type[i])));
                        values[i].setColor(Game.Colour.DAMAGE);//Operation.getIconColor(result.type[i]));
                        values[i].setText(Integer.toString(result.value[i]));//result.value[i] > 0 ? ("+" + result.value[i]) : Integer.toString(result.value[i]));
                    } break;
                    default: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.CAMPAIGN), assets.getIcon(Operation.getIconName(result.type[i])), 24);
                        values[i].setColor(Operation.getIconColor(result.type[i]));
                        values[i].setText(result.value[i] > 0 ? ("+" + result.value[i]) : Integer.toString(result.value[i]));
                    }
                    break;
                }
                values[i].setSize(QQView.WRAP_CONTENT, 24);
                addChild(values[i]);
            }
        }
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - 8) / 3;
        for (int i = 0; i < 3; ++i) {
            if (null != background[i])
                background[i].setSize(splitWidth, 40);
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - 8) / 3;

        for (int i = 0; i < 3; ++i) {
            if (null != background[i]) {
                float shiftX = 2 + (splitWidth + 2) * i;
                background[i].setPosition(shiftX, 0);
                icons[i].setPosition(shiftX + leftPadding, bottomPadding);
                values[i].setPosition(shiftX + (splitWidth + leftPadding + icons[i].getWidth() - values[i].getWidth()) / 2, bottomPadding);
            }
        }

    }
}
