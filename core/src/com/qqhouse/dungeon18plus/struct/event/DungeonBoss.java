package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.core.EventType;

public class DungeonBoss extends DungeonMonster {

    private int mTurn;

    public DungeonBoss(EventType type, int level) {
        super(type, level);
        mTurn = 0;
    }

    public void addTurn() {
        mTurn++;
    }

    @Override
    public int getScore() {
        /*
         * turn score
         *  ~ 1   100
         *  ~ 3    75
         *  ~ 6    55
         * ~ 10    40
         * ~ 15    30
         * ~ 20    25
         * ~ 25    20
         * ~        0
         */
        if (mTurn <=  1) return 100;
        if (mTurn <=  3) return 75;
        if (mTurn <=  6) return 55;
        if (mTurn <= 10) return 40;
        if (mTurn <= 15) return 30;
        if (mTurn <= 20) return 25;
        if (mTurn <= 25) return 20;

        return 0;
    }

    @Override
    public int getTurn() { return mTurn; }

    // boss will not give exp.
    @Override
    public int getExp(int heroLevel) { return 0; }

}
