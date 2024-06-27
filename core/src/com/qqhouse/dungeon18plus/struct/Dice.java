package com.qqhouse.dungeon18plus.struct;

import java.util.Random;

public class Dice {
    public final int seed;
    public final int gap;
    public final int base;

    public Dice(int seed, int gap, int base) {
        this.seed = seed;
        this.gap = gap;
        this.base = base;
    }

    public int roll(Random random) {
        return random.nextInt(seed) * gap + base;
    }
}
