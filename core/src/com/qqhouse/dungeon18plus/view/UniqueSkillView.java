package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
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
public class UniqueSkillView extends AssetGroupButton {

    private ItemView item;
    private QQText txtMastery;
    private QQIconText op1;
    private QQIconText[] itOps;
    private QQIconText coolDown;

    public UniqueSkillView(Assets assets) {
        super(assets);
        setPadding(4);
        //setPadding(4, 4, 4, 4Game.Size.BLOCKEE_PADDING);
    }

    public void reset(EquipmentMastery mastery, boolean isMastery, Ability base) {
        // initial all item.
        if (null != mastery) {
            item = new ItemView(assets.getItem(mastery.equipment.icon));
            if (mastery.equipment.isBlessed())
                item.setStatus(assets.getBackground("blessed"));
            else if (mastery.equipment.isCursed())
                item.setStatus(assets.getBackground("cursed"));
            else if (mastery.equipment.isRefined())
                item.setStatus(assets.getBackground("refined"));
            item.setSize(32, 32);
            item.setPosition(8, 8);
            //item.setPosition(leftPadding, bottomPadding);
            addChild(item);


            // mastery
            txtMastery = new QQText(assets.getFont(Game.Font.DIGITAL16));
            txtMastery.setText(String.format(Locale.US, "%03d", mastery.mastery));
            txtMastery.setColor(Game.Colour.RARE);
            txtMastery.setAlign(Align.left);
            addChild(txtMastery);

            UniqueSkillData data = mastery.equipment.skill.get(mastery.mastery);

            itOps = new QQIconText[data.operations.length];
            int index = 0;

            for (Operation2 op : data.operations) {
                String opStr = "";
                if (op.isDamage()) {
                    opStr = Integer.toString(op.getDamageDisplay(base));
                    if (op.isAll())
                        opStr += "xA";
                    else if (1 < op.target)
                        opStr += "x" + op.target;
                } else if (op.isAssist()) {
                    opStr = Integer.toString(op.value);
                    if (op.isTo())
                        opStr = "=" + opStr;
                    else {
                        if (op.value > 0)
                            opStr = "+" + opStr;
                    }
                    if (op.isRate())
                        opStr = opStr + "%";
                    if (op.isAll())
                        opStr += "xA";
                    else if (0 < op.target)
                        opStr += "x" + op.target;

                }

                itOps[index] = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon16(op.getIconName()));
                itOps[index].setText(opStr);
                itOps[index].setColor(op.getIconColor());
                addChild(itOps[index]);
                index++;
            }

            coolDown = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon16("time"));
            coolDown.setColor(Game.Colour.SPEED);
            coolDown.setText(Integer.toString(data.coolDown));
            addChild(coolDown);

            setBackground(assets.getBackgroundSet(isMastery ? "special" : "ordinary"));
        }
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;
        txtMastery.setSize(QQView.WRAP_CONTENT, 16);
        txtMastery.setPosition(44, 6);

        int OpsWidth = 0;
        for (int i = 0; i < itOps.length; ++i) {
            itOps[i].setSize(QQView.WRAP_CONTENT, 16);
            OpsWidth += itOps[i].getWidth() + Game.Size.INNER_MARGIN;
        }
        int startX = (int)(this.width - OpsWidth) / 2;
        for (int i = 0; i < itOps.length; ++i) {
            itOps[i].setPosition(startX, 16);
            startX += itOps[i].getWidth() + 2;
        }

        //help.setSize(this.width - rightPadding - 64, 24);
        coolDown.setSize(50, 16);
        coolDown.setPosition(this.width - 8 - coolDown.getWidth(), 16);
    }

}
