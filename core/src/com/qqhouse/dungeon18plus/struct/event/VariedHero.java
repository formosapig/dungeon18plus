package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.HeroClass;

public class VariedHero extends BattleEvent {

    private HeroClass mHeroClass;

    public VariedHero(EventType type, HeroClass heroClass) {
        super(type);
        mHeroClass = heroClass;
    }

    @Override
    public String getIcon() {
        return mHeroClass.icon;
    }

    @Override
    public boolean isKnight() {
        return mHeroClass == HeroClass.EARTH_KNIGHT
                || mHeroClass == HeroClass.FIRE_KNIGHT
                || mHeroClass == HeroClass.WATER_KNIGHT
                || mHeroClass == HeroClass.WIND_KNIGHT;
    }

    @Override
    public HeroClass getHeroClass() {
        return mHeroClass;
    }
}
