package com.qqhouse.dungeon18plus.struct.loot;

import java.util.Random;

public enum Dice {
    D0M0P1(0, 0, 1),    // 1
    D5M2P16(5, 2, 16),    // 16, 18, 20, 22, 24
    D4M4P28(4, 4, 28),    // 28, 32, 36, 40
    D3M5P35(3, 5, 35),    // 35, 40, 45


    NONE(0, 0, 0);

    public final int seed;
    public final int gap;
    public final int base;

    Dice(int seed, int gap, int base) {
        this.seed = seed;
        this.gap = gap;
        this.base = base;
    }

    public int roll(Random random) {
        if (0 < seed)
            return random.nextInt(seed) * gap + base;
        else
            return base;
    }
}
