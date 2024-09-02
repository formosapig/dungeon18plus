package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

public class MasteryView extends AssetGroup {
    private final ItemView equipment;
    private final QQText mastery;

    public MasteryView(Assets assets) {
        super(assets);

        equipment = new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        equipment.setSize(32, 32);
        addChild(equipment);

        mastery = new QQText(assets.getFont(Game.Font.DIGITAL16));
        mastery.setColor(Game.Colour.MASTERY);
        mastery.setAlign(Align.bottom);
        mastery.setVisible(false);
        addChild(mastery);
    }

    public void update(EquipmentMastery em) {
        equipment.setEquipmentMastery(assets, em);
        //equipment.setIcon(assets.getItem(em.equipment.icon));
        mastery.setText(Integer.toString(em.mastery / 10));
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + 32 + bottomPadding;
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        mastery.setSize(width - leftPadding - rightPadding - 32 - 2, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        equipment.setPosition((width - 32) / 2/*leftPadding*/, bottomPadding);
        mastery.setPosition(leftPadding + 32 + 2, bottomPadding);
    }

}
