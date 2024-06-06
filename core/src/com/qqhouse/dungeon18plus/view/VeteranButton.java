package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQView;

public class VeteranButton extends AssetGroupButton {

    /*
        QQImage : blockee
        QQImage : equipment
        QQIconText[] : operations
        QQIconText : cool down
        QQIconText : round
        QQProgress : life
     */

    private QQImage hero;
    private ItemView equip;
    private QQIconText[] oper;
    private QQIconText coolDown, round;
    private QQProgress life;

    public VeteranButton(Assets assets) {
        super(assets);
        oper = new QQIconText[UniqueSkill.MAX_OPERATIONS];
    }

    public void reset(Veteran veteran) {
        hero = new QQImage(assets.getBlockee(veteran.heroClass.key));
        addChild(hero);

        // Unique Skill series ...
        equip = ItemView.create();
        equip.setSize(32, 32);
        equip.setPosition(leftPadding + 48 + 8, 25);
        addChild(equip);

        for (int i = 0; i < oper.length; ++i) {
            oper[i] = new QQIconText(assets.getFont(Game.Font.LOOT_INFO));
            addChild(oper[i]);
        }

        coolDown = new QQIconText(assets.getFont(Game.Font.LOOT_INFO), assets.getIcon16("time"));
        coolDown.setColor(Game.Colour.SPEED);
        coolDown.setText("000");
        coolDown.setSize(QQView.WRAP_CONTENT, 16);
        addChild(coolDown);

        round = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("cost_soul"));
        round.setText(Integer.toString(veteran.round));
        round.setSize(QQView.WRAP_CONTENT, 16);
        addChild(round);

        life = new QQProgress(assets.getNinePatchBG("black"), assets.getNinePatchBG("yellow"));
        //life.setSize(200, 8);
        life.setPosition(104, 8);
        life.setPercent(veteran.life * 100 / Game.HERO_MAX_LIFE);
        addChild(life);

        setBackground(assets.getBackgroundSet(veteran.heroClass.alignment.key));
    }

    public void updateUniqueSkill(Veteran veteran) {
        //skill.update(veteran.equipment, veteran.mastery, veteran);
        equip.setIcon(assets.getItem(veteran.equipment.icon));
        if (veteran.equipment.isBlessed())
            equip.setStatus(assets.getBackground("blessed"));
        else if (veteran.equipment.isCursed())
            equip.setStatus(assets.getBackground("cursed"));
        else if (veteran.equipment.isRefined())
            equip.setStatus(assets.getBackground("refined"));
        else
            equip.setStatus(null);

        // mastery operations
        for (int i = 0; i < oper.length; ++i) {
            oper[i].setText("");
            oper[i].setIcon(null);
        }

        UniqueSkillData data = veteran.equipment.skill.get(veteran.mastery);

        int index = 0;

        for (Operation2 op : data.operations) {
            String opStr = "";
            if (op.isDamage()) {
                opStr = Integer.toString(op.getDamageDisplay(veteran));
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
            oper[index].setText(opStr);
            oper[index].setIcon(assets.getIcon16(op.getIconName()));
            oper[index].setColor(op.getIconColor());
            //oper[index].setSize(QQView.WRAP_CONTENT, 16);
            index++;
        }

        coolDown.setText(Integer.toString(data.coolDown));

        // call arrange children ?
        arrangeChildren();
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;

        hero.setPosition(8, 8);

        round.setPosition(64, 5);

        coolDown.setPosition(this.width - rightPadding - coolDown.getWidth(), 33);

        life.setSize(this.width - rightPadding - 104, 10);

        if (null != oper) {
            int OpsWidth = 0;
            for (int i = 0; i < oper.length; ++i) {
                oper[i].setSize(QQView.WRAP_CONTENT, 16);
                OpsWidth += oper[i].getWidth() + Game.Size.INNER_MARGIN;
            }
            //(width - leftPadding - rightPadding - coolDown - 48 - OpsWidth) / 2 + leftPadding + 48


            int startX = (int) (this.width - coolDown.getWidth() + 48 + leftPadding - rightPadding - OpsWidth + 8 + 32) / 2;
            for (int i = 0; i < oper.length; ++i) {
                oper[i].setPosition(startX, 33);
                startX += oper[i].getWidth() + 2;
            }
        }


    }

}
