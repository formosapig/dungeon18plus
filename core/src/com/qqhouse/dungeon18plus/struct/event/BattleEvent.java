package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.Fightable;

public class BattleEvent extends Event implements Fightable {

    protected Ability mBattleAbility;

    public BattleEvent(EventType type) {
        super(type);
        mBattleAbility = new Ability();
    }

    // set battle ability ...
    @SuppressWarnings("unchecked")
    public <E extends BattleEvent> E setAbility(Ability src) {
        mBattleAbility.copy(src);
        return (E)this;
    }

    @Override
    public Ability getAbility() {
        return mBattleAbility;
    }

    // todo 這樣 enhance 很醜.
    public void enhanceLife(int life) {
        if (mBattleAbility.life < life) {
            mBattleAbility.life = life;
        }
    }

    public void enhanceAttack(int attack) {
        if (mBattleAbility.attack < attack) {
            mBattleAbility.attack = attack;
        }
    }

    public void enhanceDefense(int defense) {
        if (mBattleAbility.defense < defense) {
            mBattleAbility.defense = defense;
        }
    }

    public void enhanceSpeed(int speed) {
        if (mBattleAbility.speed > speed) {
            mBattleAbility.speed = speed;
        }
        if (mBattleAbility.speed < 1) {
            mBattleAbility.speed = 1;
        }
    }
}
