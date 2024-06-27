package com.qqhouse.dungeon18plus.struct;

public class Drop <T> {

    public final T dropOne;
    public final int dropCount;

    public final int dropRate;

    public Drop(T one, int count, int rate) {
        this.dropOne = one;
        this.dropCount = count;
        this.dropRate = rate;
    }

    public T kick(Integer seed) {
        if (seed < dropRate)
            return dropOne;
        seed -= dropRate;
        return null;
    }

}
