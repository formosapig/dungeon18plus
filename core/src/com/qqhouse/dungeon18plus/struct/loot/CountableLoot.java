package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.Item;

public class CountableLoot {
    public final Item loot;
    public int amount;

    public static CountableLoot NONE = new CountableLoot(Item.NONE);

    public CountableLoot(Item loot, int amount) {
        this.loot = loot;
        this.amount = amount;
    }

    public CountableLoot(Item loot) {
        this.loot = loot;
        this.amount = 1;
    }
}
