package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.Item;

import java.util.Random;

public class LootSlot {
    public final Item loot;
    public final int rate;
    private final Dice dice;

    public LootSlot(Item loot, Dice count, int rate) {
        this.loot = loot;
        this.dice = count;
        this.rate = rate;
    }

    public LootSlot(Item loot, int rate) {
        this.loot = loot;
        this.dice = null;
        this.rate = rate;
    }

    public int getCount(Random random) {
        if (null == dice)
            return 1;
        else
            return dice.roll(random);
    }
}
