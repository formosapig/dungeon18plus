package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.Item;

public class AmountLoot {
    public final Item loot;
    public int amount;

    public static AmountLoot NONE = new AmountLoot(Item.NONE);

    public AmountLoot(Item loot, int amount) {
        this.loot = loot;
        this.amount = amount;
    }

    public AmountLoot(Item loot) {
        this.loot = loot;
        this.amount = 1;
    }
}
