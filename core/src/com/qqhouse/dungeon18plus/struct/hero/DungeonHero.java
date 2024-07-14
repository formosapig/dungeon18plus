package com.qqhouse.dungeon18plus.struct.hero;

import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.dungeon18plus.core.Feat;

public final class DungeonHero extends Hero {

    // berserker : use rage to release ultimate strike
    // monk : use rage to learn new kung-fu
    public int rage;

    // level and exp
    public int level;
    public int exp;
    public int maxExp;

    // used action
    public Action inUse = Action.NONE;

    @Override
    public boolean canUseSkillAction() {
        return inUse == Action.NONE;
    }

    public void resetSkillAction() {
        inUse = Action.NONE;
    }

    public int getDropRatePlus() {
        if (Feat.DARK_PRESENCE.in(feats))
            return -40;
        else if (Feat.LUCKY.in(feats))
            return 60;
        else if (Feat.HOLY_ONE.in(feats))
            return 100;
        else
            return 0;
    }
}
