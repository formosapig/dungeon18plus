package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;

public class ColosseumShop extends Event {

    private boolean mMasteryEquipment = false;

    public ColosseumShop(Item equip) {
        super(EventType.MERCHANT);
        this.loot = equip;
        this.costType = G.cost.coin;
        this.costValue = equip.price;
    }

    public void setMasterEquipment() { mMasteryEquipment = true; }

    @Override
    public boolean isMasteryEquipment() { return mMasteryEquipment; }

}
