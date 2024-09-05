package com.qqhouse.dungeon18plus.struct.campaign;


public class CampaignScore implements Comparable<CampaignScore> {

    public final String iconKey;
    public final String lootIconKey;
    public final int soul;
    public final int damage;
    public final int guard;
    public final int heal;

    public CampaignScore(String icon, String lootIconKey, int soul, int damage, int guard, int heal) {
        this.iconKey = icon;
        this.lootIconKey = lootIconKey;
        this.soul = soul;
        this.damage = damage;
        this.guard = guard;
        this.heal = heal;
    }

    @Override
    public int compareTo(CampaignScore other) {
        // damage
        if (this.damage != other.damage)
            return this.damage - other.damage;

        // guard
        if (this.guard != other.guard)
            return this.guard - other.guard;

        // heal
        return this.heal - other.heal;
    }
}
