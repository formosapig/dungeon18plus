package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;

import java.util.Random;

public enum LootSlot {

    // dungeon mode, veteran.
    VETERAN_NOVICE(Type.DUNGEON_VETERAN, HeroClass.NOVICE.code,
            Item.RING_OF_GODDESS,
            Item.TRAINING_RING),
    VETERAN_BARBARIAN(),


    NONE();

    // random
    private final Random random = new Random();

    // drop type
    public @interface Type {
        int NONE                   = 0;
        int DUNGEON_VETERAN        = 1;
    }

    // attributes.
    @Type
    private final int type;
    private final int code;
    // Item or Item[]
    private final Object rareDrop;
    private final Object normalDrop;

    LootSlot() {
        type = 0;
        code = 0;
        rareDrop = null;
        normalDrop = null;
    }

    LootSlot(@Type int type, int code, Item[] rareDrop, Item[] normalDrop) {
        this.type = type;
        this.code = code;
        this.rareDrop = rareDrop;
        this.normalDrop = normalDrop;
    }

    LootSlot(@LootSlot.Type int type, int code, Item rareDrop, Item normalDrop) {
        this.type = type;
        this.code = code;
        this.rareDrop = rareDrop;
        this.normalDrop = normalDrop;
    }

    public Item kick() {
        return kick(5);
    }


    public Item kick(int rareDropRate) {
        int seed = random.nextInt(100);
        if (seed < rareDropRate)
            return null;//rareDrop[0];
        return null;//normalDrop[0];
    }

    public static LootSlot find(@Type int type, int code) {
        for (LootSlot lootSlot : LootSlot.values())
            if (lootSlot.type == type && lootSlot.code == code)
                return lootSlot;
        throw new RuntimeException("invalid type and code : " + type + "," + code);
    }
}
