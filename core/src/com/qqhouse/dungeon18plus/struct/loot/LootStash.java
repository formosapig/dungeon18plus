package com.qqhouse.dungeon18plus.struct.loot;

import com.qqhouse.dungeon18plus.core.Item;

import java.util.Random;

public enum LootStash {
    DUNGEON_DOOR(
            new LootSlot(Item.MITHRIL_DAGGER, 1),
            new LootSlot(Item.MITHRIL_SWORD, 1),
            new LootSlot(Item.MITHRIL_STAFF, 1),
            new LootSlot(Item.MITHRIL_SHIELD, 1),
            new LootSlot(Item.MITHRIL_BOOTS, 1),
            new LootSlot(Item.GREAT_SWORD, 2),
            new LootSlot(Item.WIND_DAGGER, 2),
            new LootSlot(Item.TOWER_SHIELD, 2),
            new LootSlot(Item.WOODEN_DAGGER, 4),
            new LootSlot(Item.WOODEN_SWORD, 4),
            new LootSlot(Item.WOODEN_STAFF, 4),
            new LootSlot(Item.WOODEN_SHIELD, 4),
            new LootSlot(Item.WOODEN_RING, 4)
    ),

    DUNGEON_SKELETON_FIGHTER(
            // drop              rate
            // skull sword          2
            // shadow dagger        3
            // mithril sword        5
            // great sword         20
            // iron daggers        30
            // wooden sword        40
            new LootSlot(Item.SKULL_SWORD, 2),
            new LootSlot(Item.SHADOW_DAGGER, 3),
            new LootSlot(Item.MITHRIL_SWORD, 5),
            new LootSlot(Item.GREAT_SWORD, 20),
            new LootSlot(Item.IRON_DAGGER, 30),
            new LootSlot(Item.WOODEN_SWORD, 40)
    ),

    DUNGEON_SQULETON(
            // drop             rate
            // skull ring          2
            // white ring          3
            // black ring          5
            // wooden ring        10
            // yellow ring        10
            // red ring           10
            // blue ring          10
            // green ring         10
            // iron ring          40
            new LootSlot(Item.SKULL_RING, 2),
            new LootSlot(Item.WHITE_RING, 3),
            new LootSlot(Item.BLACK_RING, 5),
            new LootSlot(Item.WOODEN_RING, 10),
            new LootSlot(Item.YELLOW_RING, 10),
            new LootSlot(Item.RED_RING, 10),
            new LootSlot(Item.BLUE_RING, 10),
            new LootSlot(Item.GREEN_RING, 10),
            new LootSlot(Item.IRON_RING, 40)
    ),

    DUNGEON_VETERAN_NOVICE(
            // drop             rate
            // ring of goddess     5
            // star               10 3R2+1 = 1, 3, 5
            // training ring      30
            // training sword     30

    ),

    NONE();

    private final LootSlot[] loots;
    private final AmountLoot rest;

    LootStash(AmountLoot rest, LootSlot... loots) {
        this.rest = rest;
        this.loots = loots;
    }

    LootStash(LootSlot... loots) {
        this.rest = AmountLoot.NONE;
        this.loots = loots;
    }

    public AmountLoot drop(Random random) {
        return drop(random, 0);
    }

    public AmountLoot drop(Random random, int dropRatePlus) {
        int seed = random.nextInt(100);
        for (LootSlot slot : loots) {
            if ((seed -= (slot.size + dropRatePlus)) < 0) {
                return new AmountLoot(slot.loot, slot.getCount(random));
            }
        }
        return rest;
    }
}
