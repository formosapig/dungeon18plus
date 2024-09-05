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
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.Locale;

/*
        Equipment : ItemView
        mastery : TextView

        attribute x 1 ~ 3 : TextView
        CD : TextView

 */
public class UniqueSkillButton extends AssetGroupButton {

    private ItemView item;
    private QQText txtMastery;
    private QQIconText[] itOps;
    private QQIconText coolDown;

    public UniqueSkillButton(Assets assets) {
        super(assets);
        //setPadding(4);
        itOps = new QQIconText[UniqueSkill.MAX_OPERATIONS];
    }

    public void reset(EquipmentMastery mastery, boolean isMastery, Ability base) {
        // initial all item.
        if (null != mastery) {
            item = new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
            //item = ItemView.create(assets, mastery.equipment);
            item.setSize(32, 32);
            item.setPosition(8, 8);
            //item.setPosition(leftPadding, bottomPadding);
            item.setEquipmentMastery(assets, mastery);
            addChild(item);


            // mastery
            //txtMastery = new QQText(assets.getFont(Game.Font.DIGITAL16));
            //txtMastery.setText(String.format(Locale.US, "%03d", mastery.mastery));
            //txtMastery.setColor(Game.Colour.RARE);
            //txtMastery.setAlign(Align.left);
            //addChild(txtMastery);

            UniqueSkillData data = mastery.equipment.skill.get(mastery.mastery);

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

            setBackground(assets.getBackgroundSet(isMastery ? "special" : "ordinary"));
        }
    }


    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        //txtMastery.setSize(QQView.WRAP_CONTENT, 16);
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
        if (null != txtMastery) {

            txtMastery.setPosition(44, 6);
        }

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
