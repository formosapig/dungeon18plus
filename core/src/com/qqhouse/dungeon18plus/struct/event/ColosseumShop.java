package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;

public class ColosseumShop extends Event {

    private boolean mMasteryEquipment = false;

    public ColosseumShop(Item equip) {
        super(EventType.PEDDLER);
        this.loot = equip;
        this.costType = Game.Cost.COIN;
        this.costValue = equip.price;
    }

    public void setMasterEquipment() { mMasteryEquipment = true; }

    @Override
    public boolean isMasteryEquipment() { return mMasteryEquipment; }

}
