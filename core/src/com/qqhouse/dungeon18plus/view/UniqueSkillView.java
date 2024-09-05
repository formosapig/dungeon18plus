package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.Locale;

public class UniqueSkillView extends AssetGroup {

    private QQImage icon;
    private QQIconText[] itOps;
    private QQIconText coolDown;

    public UniqueSkillView(Assets assets) {
        super(assets);
        itOps = new QQIconText[UniqueSkill.MAX_OPERATIONS];
    }

    public void reset(UniqueSkillData data, Ability base) {

        icon = new QQImage(assets.getIcon(data.skill.icon));
        icon.setSize(32, 32);
        icon.setPosition(leftPadding, bottomPadding);
        addChild(icon);

        itOps = new QQIconText[data.operations.length];
        int index = 0;

            for (Operation2 op : data.operations) {
                itOps[index] = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/" + op.getIconName()));
                itOps[index].setText(op.getText(base));
                itOps[index].setColor(op.getIconColor());
                addChild(itOps[index]);
                index++;
            }

            coolDown = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon("icon16/time"));
            coolDown.setColor(Game.Colour.SPEED);
            coolDown.setText(Integer.toString(data.coolDown));
            coolDown.setAlign(Align.right);
            addChild(coolDown);
    }


    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        if (null != itOps) {
            //int OpsWidth = 0;
            for (int i = 0; i < itOps.length; ++i) {
                itOps[i].setSize(QQView.WRAP_CONTENT, 16);
                //OpsWidth += itOps[i].getWidth() + Game.Size.INNER_MARGIN;
            }
            //int startX = (int) (this.width - OpsWidth) / 2;
            //for (int i = 0; i < itOps.length; ++i) {
            //    itOps[i].setPosition(startX, 16);
            //    startX += itOps[i].getWidth() + 2;
            //}
        }
        coolDown.setSize(48, 16);
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;

        if (null != itOps) {
            int OpsWidth = 0;
            for (int i = 0; i < itOps.length; ++i) {
                //itOps[i].setSize(QQView.WRAP_CONTENT, 16);
                OpsWidth += itOps[i].getWidth() + Game.Size.INNER_MARGIN;
            }
            int startX = (int) (this.width - OpsWidth) / 2;
            for (int i = 0; i < itOps.length; ++i) {
                itOps[i].setPosition(startX, 16);
                startX += itOps[i].getWidth() + 2;
            }
        }

        if (null != coolDown) {

            coolDown.setPosition(this.width - 8 - coolDown.getWidth(), 16);
        }
    }

}
