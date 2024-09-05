package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.Game;

import java.util.HashMap;
import java.util.Map;

public class MasteryManager {

    private static Map<HeroClass, Map<Item, Integer>> allData = new HashMap<>();

    static {
        // Novice
        Map<Item, Integer> noviceMap = new HashMap<>();
        noviceMap.put(Item.WOODEN_DAGGER, 21);
        noviceMap.put(Item.WOODEN_SWORD, 21);
        noviceMap.put(Item.HOLY_SWORD, 16);
        noviceMap.put(Item.WOODEN_STAFF, 21);
        noviceMap.put(Item.WOODEN_SHIELD, 21);
        noviceMap.put(Item.HOLY_SHIELD, 16);
        noviceMap.put(Item.WOODEN_RING, 21);
        noviceMap.put(Item.WHITE_RING, 18);
        noviceMap.put(Item.RING_OF_GODDESS, 16);
        allData.put(HeroClass.NOVICE, noviceMap);

        // Barbarian
        Map<Item, Integer> barbarianMap = new HashMap<>();
        barbarianMap.put(Item.WOODEN_SWORD, 17);
        barbarianMap.put(Item.IRON_SWORD, 15);
        barbarianMap.put(Item.WOODEN_STAFF, 19);
        barbarianMap.put(Item.MITHRIL_STAFF, 17);
        barbarianMap.put(Item.IRON_SHIELD, 15);
        barbarianMap.put(Item.IRON_RING, 15);
        barbarianMap.put(Item.YELLOW_RING, 18);
        barbarianMap.put(Item.IRON_BOOTS, 15);
        allData.put(HeroClass.BARBARIAN, barbarianMap);

        // Berserker
        Map<Item, Integer> berserkerMap = new HashMap<>();
        berserkerMap.put(Item.WOODEN_SWORD, 19);
        berserkerMap.put(Item.IRON_SWORD, 20);
        berserkerMap.put(Item.GREAT_SWORD, 18);
        berserkerMap.put(Item.MITHRIL_SWORD, 17);
        berserkerMap.put(Item.WOODEN_STAFF, 15);
        berserkerMap.put(Item.WOODEN_SHIELD, 13);
        berserkerMap.put(Item.RED_RING, 18);
        berserkerMap.put(Item.MITHRIL_BOOTS, 15);
        allData.put(HeroClass.BERSERKER, berserkerMap);

        // Dragoon
        Map<Item, Integer> dragoonMap = new HashMap<>();
        dragoonMap.put(Item.IRON_DAGGER, 17);
        dragoonMap.put(Item.WOODEN_SHIELD, 19);
        dragoonMap.put(Item.IRON_SHIELD, 20);
        dragoonMap.put(Item.TOWER_SHIELD, 18);
        dragoonMap.put(Item.MITHRIL_SHIELD, 19);
        dragoonMap.put(Item.BLACK_SHIELD, 17);
        dragoonMap.put(Item.IRON_RING, 20);
        dragoonMap.put(Item.BLUE_RING, 18);
        dragoonMap.put(Item.IRON_BOOTS, 17);
        allData.put(HeroClass.DRAGOON, dragoonMap);

        // Thief
        Map<Item, Integer> thiefMap = new HashMap<>();
        thiefMap.put(Item.WOODEN_DAGGER, 19);
        thiefMap.put(Item.IRON_DAGGER, 20);
        thiefMap.put(Item.WIND_DAGGER, 18);
        thiefMap.put(Item.MITHRIL_DAGGER, 18);
        thiefMap.put(Item.BLACK_SHIELD, 15);
        thiefMap.put(Item.WOODEN_RING, 19);
        thiefMap.put(Item.GREEN_RING, 18);
        thiefMap.put(Item.IRON_BOOTS, 20);
        thiefMap.put(Item.WIND_BOOTS, 17);
        allData.put(HeroClass.THIEF, thiefMap);

        // Assassin
        Map<Item, Integer> assassinMap = new HashMap<>();
        assassinMap.put(Item.WOODEN_DAGGER, 18);
        assassinMap.put(Item.IRON_DAGGER, 18);
        assassinMap.put(Item.SHADOW_DAGGER, 17);
        assassinMap.put(Item.SKULL_SWORD, 16);
        assassinMap.put(Item.BLACK_SHIELD, 12);
        assassinMap.put(Item.SKULL_SHIELD, 16);
        assassinMap.put(Item.BLACK_RING, 18);
        assassinMap.put(Item.SKULL_RING, 16);
        assassinMap.put(Item.MITHRIL_BOOTS, 18);
        allData.put(HeroClass.ASSASSIN, assassinMap);

        // Crusader
        Map<Item, Integer> crusaderMap = new HashMap<>();
        crusaderMap.put(Item.IRON_SWORD, 16);
        crusaderMap.put(Item.IRON_SWORD_1, 16);
        crusaderMap.put(Item.MITHRIL_SWORD, 14);
        crusaderMap.put(Item.IRON_SHIELD, 16);
        crusaderMap.put(Item.IRON_SHIELD_1, 16);
        crusaderMap.put(Item.MITHRIL_SHIELD, 14);
        crusaderMap.put(Item.IRON_RING, 16);
        crusaderMap.put(Item.IRON_RING_1, 16);
        crusaderMap.put(Item.BLACK_RING, 12);
        crusaderMap.put(Item.WHITE_RING, 12);
        crusaderMap.put(Item.IRON_BOOTS, 16);
        crusaderMap.put(Item.IRON_BOOTS_1, 16);
        crusaderMap.put(Item.MITHRIL_BOOTS, 14);
        allData.put(HeroClass.CRUSADER, crusaderMap);

        // Earth Knight
        Map<Item, Integer> earthKnightMap = new HashMap<>();
        earthKnightMap.put(Item.IRON_DAGGER_1, 17);
        earthKnightMap.put(Item.IRON_SWORD_1, 18);
        earthKnightMap.put(Item.MITHRIL_STAFF, 20);
        earthKnightMap.put(Item.IRON_SHIELD_1, 19);
        earthKnightMap.put(Item.MITHRIL_SHIELD, 18);
        earthKnightMap.put(Item.WHITE_SHIELD, 20);
        earthKnightMap.put(Item.IRON_RING_1, 20);
        earthKnightMap.put(Item.YELLOW_RING, 20);
        earthKnightMap.put(Item.RING_OF_LIFE, 17);
        earthKnightMap.put(Item.IRON_BOOTS_1, 17);
        allData.put(HeroClass.EARTH_KNIGHT, earthKnightMap);

        // Fire Knight
        Map<Item, Integer> fireKnightMap = new HashMap<>();
        fireKnightMap.put(Item.IRON_DAGGER_1, 19);
        fireKnightMap.put(Item.IRON_SWORD_1, 20);
        fireKnightMap.put(Item.MITHRIL_SWORD, 20);
        fireKnightMap.put(Item.FIRE_SWORD, 20);
        fireKnightMap.put(Item.MITHRIL_STAFF, 17);
        fireKnightMap.put(Item.IRON_SHIELD_1, 17);
        fireKnightMap.put(Item.IRON_RING_1, 18);
        fireKnightMap.put(Item.RED_RING, 20);
        fireKnightMap.put(Item.RING_OF_ATTACK, 17);
        fireKnightMap.put(Item.IRON_BOOTS_1, 18);
        allData.put(HeroClass.FIRE_KNIGHT, fireKnightMap);

        // Water Knight
        Map<Item, Integer> waterKnightMap = new HashMap<>();
        waterKnightMap.put(Item.IRON_DAGGER_1, 18);
        waterKnightMap.put(Item.IRON_SWORD_1, 17);
        waterKnightMap.put(Item.MITHRIL_SWORD, 18);
        waterKnightMap.put(Item.ICE_SWORD, 20);
        waterKnightMap.put(Item.IRON_SHIELD_1, 20);
        waterKnightMap.put(Item.MITHRIL_SHIELD, 20);
        waterKnightMap.put(Item.IRON_RING_1, 19);
        waterKnightMap.put(Item.BLUE_RING, 20);
        waterKnightMap.put(Item.RING_OF_DEFENSE, 17);
        waterKnightMap.put(Item.IRON_BOOTS_1, 19);
        allData.put(HeroClass.WATER_KNIGHT, waterKnightMap);
                
        // Wind Knight
        Map<Item, Integer> windKnightMap = new HashMap<>();
        windKnightMap.put(Item.IRON_DAGGER_1, 20);
        windKnightMap.put(Item.MITHRIL_DAGGER, 20);
        windKnightMap.put(Item.IRON_SWORD_1, 19);
        windKnightMap.put(Item.THUNDER_SWORD, 20);
        windKnightMap.put(Item.IRON_SHIELD_1, 18);
        windKnightMap.put(Item.IRON_RING_1, 17);
        windKnightMap.put(Item.GREEN_RING, 20);
        windKnightMap.put(Item.RING_OF_SPEED, 17);
        windKnightMap.put(Item.IRON_BOOTS_1, 20);
        windKnightMap.put(Item.MITHRIL_BOOTS, 20);
        allData.put(HeroClass.WIND_KNIGHT, windKnightMap);
    }

    public static int getMastery(HeroClass hero, Item item) {
        Map<Item, Integer> map = allData.get(hero);
        if (null != map) {
            if (map.containsKey(item))
                return map.get(item);
        }
        return Game.Setting.GENERAL_MASTERY_MAX;
    }

}
