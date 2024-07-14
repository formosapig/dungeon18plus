package com.qqhouse.dungeon18plus.struct.loot;

import com.badlogic.gdx.Gdx;
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

    DOOR_1(
            new LootSlot(Item.MITHRIL_DAGGER, 15),
            new LootSlot(Item.WIND_DAGGER, 20),
            new LootSlot(Item.WOODEN_DAGGER, 25),
            new LootSlot(Item.AGED_DAGGER, 40)
    ),

    DOOR_2(
            new LootSlot(Item.MITHRIL_SWORD, 15),
            new LootSlot(Item.GREAT_SWORD, 20),
            new LootSlot(Item.WOODEN_SWORD, 25),
            new LootSlot(Item.AGED_SWORD, 40)
    ),

    DOOR_3(
            new LootSlot(Item.MITHRIL_STAFF, 15),
            new LootSlot(Item.COPPER_COIN, Dice.D4M4P28, 20), // coin 28, 32, 36, 40
            new LootSlot(Item.WOODEN_STAFF, 25),
            new LootSlot(Item.COPPER_COIN, Dice.D0M0P1, 40) // coin 1
    ),

    DOOR_4(
            new LootSlot(Item.MITHRIL_SHIELD, 15),
            new LootSlot(Item.TOWER_SHIELD, 20),
            new LootSlot(Item.WOODEN_SHIELD, 25),
            new LootSlot(Item.AGED_SHIELD, 40)
    ),

    DOOR_5(
            new LootSlot(Item.COPPER_COIN, Dice.D3M5P35, 15), // coin 35, 40, 45
            new LootSlot(Item.COPPER_COIN, Dice.D4M4P28, 20), // coin 28, 32, 36, 40
            new LootSlot(Item.WOODEN_RING, 25),
            new LootSlot(Item.AGED_RING, 40)
    ),

    DOOR_6(
            new LootSlot(Item.MITHRIL_BOOTS, 15),
            new LootSlot(Item.COPPER_COIN, Dice.D4M4P28, 20),
            new LootSlot(Item.COPPER_COIN, Dice.D5M2P16, 25),
            new LootSlot(Item.AGED_BOOTS, 40)
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

    CAT_SITH(
            new LootSlot(Item.RING_OF_SPEED, 0),
            new LootSlot(Item.WIND_BOOTS, 5),
            new LootSlot(Item.WIND_DAGGER, 15),
            new LootSlot(Item.POWER_CRYSTAL, 80)
    ),

    WAILING_WALL(
            new LootSlot(Item.BLACK_SHIELD, 5),
            new LootSlot(Item.BLACK_RING, 0),
            new LootSlot(Item.TOWER_SHIELD, 15),
            new LootSlot(Item.POWER_CRYSTAL, 80)
    ),

    DEMON(
            new LootSlot(Item.SHADOW_DAGGER, 5),
            new LootSlot(Item.IRON_DAGGER_1, 10),
            new LootSlot(Item.RED_RING, 10),
            new LootSlot(Item.POWER_CRYSTAL, 75)
    ),

    EARTH_KNIGHT(
            new LootSlot(Item.WHITE_SHIELD, 5),
            new LootSlot(Item.IRON_SHIELD, 15),
            new LootSlot(Item.IRON_RING, 15),
            new LootSlot(Item.POWER_CRYSTAL, 65)
    ),

    FIRE_KNIGHT(
            new LootSlot(Item.FIRE_SWORD, 5),
            new LootSlot(Item.IRON_DAGGER, 15),
            new LootSlot(Item.IRON_SWORD, 15),
            new LootSlot(Item.POWER_CRYSTAL, 65)
    ),

    WATER_KNIGHT(
            new LootSlot(Item.ICE_SWORD, 5),
            new LootSlot(Item.IRON_SWORD, 15),
            new LootSlot(Item.IRON_SHIELD, 15),
            new LootSlot(Item.POWER_CRYSTAL, 65)
    ),

    WIND_KNIGHT(
            new LootSlot(Item.THUNDER_SWORD, 5),
            new LootSlot(Item.IRON_DAGGER, 15),
            new LootSlot(Item.IRON_BOOTS, 15),
            new LootSlot(Item.POWER_CRYSTAL, 65)
    ),

    STEEL_CYCLOPS(
            new LootSlot(Item.IRON_SWORD_1, 25),
            new LootSlot(Item.IRON_SHIELD_1, 25),
            new LootSlot(Item.IRON_RING_1, 25),
            new LootSlot(Item.IRON_BOOTS_1, 25)
    ),

    SKELETON_KING(
            new LootSlot(Item.SKULL_SWORD, 5),
            new LootSlot(Item.SKULL_SHIELD, 5),
            new LootSlot(Item.IRON_RING_1, 15),
            new LootSlot(Item.POWER_CRYSTAL, 75)
    ),

    NOVICE(
            new LootSlot(Item.RING_OF_GODDESS, 5),
            new LootSlot(Item.HOLY_SHIELD, 5),
            new LootSlot(Item.WHITE_RING, 10),
            new LootSlot(Item.TRAINING_RING, 80)
    ),

    BARBARIAN(
            new LootSlot(Item.HOLY_SHIELD, 5),
            new LootSlot(Item.YELLOW_RING, 10),
            new LootSlot(Item.MITHRIL_STAFF, 15),
            new LootSlot(Item.TRAINING_STAFF, 70)
    ),

    BERSERKER(
            new LootSlot(Item.HOLY_SWORD, 5),
            new LootSlot(Item.RED_RING, 10),
            new LootSlot(Item.MITHRIL_SWORD, 15),
            new LootSlot(Item.TRAINING_SWORD, 70)
    ),

    DRAGOON(
            new LootSlot(Item.HOLY_SHIELD, 5),
            new LootSlot(Item.BLUE_RING, 10),
            new LootSlot(Item.MITHRIL_SHIELD, 15),
            new LootSlot(Item.TRAINING_SHIELD, 70)
    ),

    THIEF(
            new LootSlot(Item.HOLY_SWORD, 5),
            new LootSlot(Item.GREEN_RING, 10),
            new LootSlot(Item.MITHRIL_DAGGER, 15),
            new LootSlot(Item.TRAINING_DAGGER, 70)
    ),

    ASSASSIN(
            new LootSlot(Item.HOLY_SWORD, 5),
            new LootSlot(Item.SHADOW_DAGGER, 5),
            new LootSlot(Item.MITHRIL_DAGGER, 15),
            new LootSlot(Item.TRAINING_DAGGER, 75)
    ),

    CRUSADER(
            new LootSlot(Item.WIND_DAGGER_1, 10),
            new LootSlot(Item.GREAT_SWORD_1, 10),
            new LootSlot(Item.TOWER_SHIELD_1, 10),
            new LootSlot(Item.TRAINING_SHIELD, 70)
    ),

    NONE();

    private final LootSlot[] loots;

    LootStash(LootSlot... loots) {
        this.loots = loots;
    }

    public CountableLoot drop(Random random) {
        return drop(random, 0);
    }

    public CountableLoot drop(Random random, int dropRatePlus) {
        int seed = random.nextInt(100);
        for (LootSlot slot : loots) {
            int trueRate = slot.rate * (100 + dropRatePlus) / 100;
            //Gdx.app.error("LootStash", this + "Item = " + slot.loot + " rate = " + trueRate);
            seed -= trueRate;
            if (seed < 0) {
            //if ((seed -= (slot.rate * (100 + dropRatePlus) / 100)) < 0) {
                int count = slot.getCount(random);
                //Gdx.app.error("LootStash", this + " item = " + slot.loot + "rate = " + trueRate + " count = " + count);
                return new CountableLoot(slot.loot, count);//slot.getCount(random));
            }
        }
        return CountableLoot.NONE;
    }
}
