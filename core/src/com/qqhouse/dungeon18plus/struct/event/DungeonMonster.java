package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.struct.Ability;

public class DungeonMonster extends BattleEvent {

    private int mLevel;

    public DungeonMonster(EventType type, int level) {
        super(type);
        this.mLevel = level;
        this.mBattleAbility = new Ability(this.type.getAbility(level));
    }

    @Override
    public void resetBattleAbility() {
        mBattleAbility.copy(type.getAbility(mLevel));
    }

    @Override
    public int getLevel() { return mLevel; }

    public int getExp(int heroLevel) {
        int exp = mLevel;
        if (mLevel > heroLevel)
            exp += (mLevel - heroLevel) * (mLevel - heroLevel);
        return exp;
    }

    @Override
    public void enhance(Ability contrast) {
        mBattleAbility.copy(type.getAbility(mLevel));
        switch (type) {
            case SQULETON:
                mBattleAbility.attack = contrast.attack;
                mBattleAbility.defense = contrast.defense;
                mBattleAbility.speed = contrast.speed;
                break;
            default:
                // do nohting.
                break;
        }
    }



}
