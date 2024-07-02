package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;

import java.util.Random;

public class LootSlot {
    public final Item loot;
    public final int size;
    private final Dice dice;

    public LootSlot(Item loot, Dice count, int size) {
        this.loot = loot;
        this.dice = count;
        this.size = size;
    }

    public LootSlot(Item loot, int size) {
        this.loot = loot;
        this.dice = null;
        this.size = size;
    }

    public int getCount(Random random) {
        if (null == dice)
            return 1;
        else
            return dice.roll(random);
    }
}
