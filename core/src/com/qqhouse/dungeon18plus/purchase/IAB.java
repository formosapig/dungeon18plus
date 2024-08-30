package com.qqhouse.dungeon18plus.purchase;

public enum IAB {
    PREMIUM("com.qqhouse.dungeon18plus.premium"),

    FAIRY("com.qqhouse.dungeon18plus.fairy"),

    FIRE_KNIGHT("com.qqhouse.dungeon18plus.fire_knight"),

    WATER_KNIGHT("com.qqhouse.dungeon18plus.water_knight");

    public final String sku;

    IAB(String sku) {
        this.sku = sku;
    }
}
