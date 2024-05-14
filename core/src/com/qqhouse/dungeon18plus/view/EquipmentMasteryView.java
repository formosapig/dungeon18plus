package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

/*
        Equipment : ItemView
        mastery : TextView

        attibute x 1 ~ 3 : TextView
        CD : TextView

 */
public class EquipmentMasteryView extends AssetGroup {

    private ItemView item;

    public EquipmentMasteryView(Assets assets) {
        super(assets);
        setPadding(4, 4, 4, Game.Size.BLOCKEE_PADDING);
    }

    public void reset(EquipmentMastery mastery) {
        // initial all item.
        ItemView item = new ItemView(assets.getItem(mastery.equipment.icon));
        if (mastery.equipment.isBlessed())
            item.setStatus(assets.getBackground("blessed"));
        else if (mastery.equipment.isCursed())
            item.setStatus(assets.getBackground("cursed"));
        else if (mastery.equipment.isRefined())
            item.setStatus(assets.getBackground("refined"));
        item.setSize(32, 32);
        item.setPosition(leftPadding, bottomPadding);
        addChild(item);






    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;
        //Gdx.app.error("BossKillView.arrangeChildren()", "width = " + this.width);
        //score.setSize(50, 24);
        //score.setPosition(this.width - rightPadding - score.getWidth(), 34);

        //help.setSize(this.width - rightPadding - 64, 24);
    }

}
