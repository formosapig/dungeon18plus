package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Item;

public class AmountItem {
    public final Item item;
    public int amount;

    public AmountItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public AmountItem(Item item) {
        this.item = item;
        this.amount = 0;
    }
}
