package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.struct.Operation;
import com.qqhouse.dungeon18plus.struct.campaign.CampaignResult;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQView;

public class CampaignResultView extends AssetGroup {

    private final QQLinear[] results;
    private final QQImage[] icons;
    private final QQIconText[] values;


    public CampaignResultView(Assets assets) {
        super(assets);
        results = new QQLinear[3];
        icons = new QQImage[3];
        values = new QQIconText[3];
    }

    public void reset(CampaignResult result) {
        for (int i = 0; i < 3; ++i) {
            if (!"".equals(result.icon[i])) {
                results[i] = new QQLinear(false, Game.Size.WIDGET_MARGIN);
                //results[i].setAlign(Align.center);
                results[i].setPadding(8);
                results[i].setBackground(assets.getNinePatch("campaign_left_result"));
                addChild(results[i]);

                // icons...
                icons[i] = new QQImage(assets.getBlockee(result.icon[i]));
                icons[i].setSize(24, 24);
                results[i].addChild(icons[i]);

                // icon and value, check special type...
                switch (result.type[i]) {
                    case Operation.GIANT_SOUL: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon(Soul.find(result.value[i]).iconKey));
                        values[i].setSize(QQView.WRAP_CONTENT, 24);
                        values[i].setText("");
                        //addChild(values[i]);
                    }
                    break;
                    case Operation.DEATH: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.DIGITAL16));
                        values[i].setColor(Game.Colour.COUNT); // white.
                        values[i].setText(assets.geti18n("rip"));
                        values[i].setSize(QQView.WRAP_CONTENT, 24);
                        //addChild(values[i]);
                    }
                    break;
                    case Operation.DAMAGE: {
                        //Gdx.app.error("CampaignResult", "damage");
                        values[i] = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon("icon16/damage"));// + Operation.getIconName(result.type[i])));
                        values[i].setColor(Game.Colour.DAMAGE);//Operation.getIconColor(result.type[i]));
                        values[i].setText(Integer.toString(result.value[i]));//result.value[i] > 0 ? ("+" + result.value[i]) : Integer.toString(result.value[i]));
                        values[i].setSize(QQView.WRAP_CONTENT, 24);
                    } break;
                    default: {
                        values[i] = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon("icon16/" + Operation.getIconName(result.type[i])));
                        values[i].setColor(Operation.getIconColor(result.type[i]));
                        values[i].setText(result.value[i] > 0 ? ("+" + result.value[i]) : Integer.toString(result.value[i]));
                        values[i].setSize(QQView.WRAP_CONTENT, 24);
                    }
                    break;
                }
                //values[i].setBackground(assets.getNinePatchBG("blessed"));
                results[i].addChild(values[i]);


            }
        }
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - 4) / 3;
        for (int i = 0; i < 3; ++i) {
            if (null != results[i])
                results[i].setSize(splitWidth, 40);
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;

        float splitWidth = (width - 4) / 3;

        for (int i = 0; i < 3; ++i) {
            if (null != results[i])
                results[i].setPosition(i * (splitWidth + 2), 0);
        }

    }
}
