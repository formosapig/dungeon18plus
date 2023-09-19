package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Item;

public class ItemAmount {
    public final Item item;
    public int amount;

    public ItemAmount(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }
}
