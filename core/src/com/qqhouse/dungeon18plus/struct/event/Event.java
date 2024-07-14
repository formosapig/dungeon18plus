package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.loot.CountableLoot;

import org.jetbrains.annotations.NotNull;

public class Event {

    public EventType type;
    public int costType;
    public int costValue;
    public Item loot;
    public int lootCount;

    public Event(EventType type) {
        this.type = type;
        costType = Game.Cost.NONE;
        costValue = 0;
        loot = Item.NONE;
        lootCount = 0;
    }

    public int getLevel() { return 0; }

    public String getIcon() { return type.icon; }

    // battle event
    public void resetBattleAbility() {}

    // colosseum shop only.
    public boolean isMasteryEquipment() { return false; }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (!(other instanceof Event))
            return false;

        Event otherEvent = (Event) other;

        if (this.type != otherEvent.type)
            return false;

//        if (this.heroClass != otherEvent.heroClass)
//            return false;

        return true;

    }

    // for dungeon monster use
    public void enhance(Ability contrast) { throw new RuntimeException("do not call this."); }

    // dungeon boss -> score
    public int getScore() { return -1; }
    public int getTurn() { return -1; }



    /*
     * all can use
     */
    @SuppressWarnings("unchecked")
    public <E extends Event> E setLoot(Item loot) {
        this.loot = loot;
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> E setLoot(@NotNull CountableLoot loot) {
        this.loot = loot.loot;
        this.lootCount = loot.amount;
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> E setCost(int costType, int costValue) {
        this.costType = costType;
        this.costValue = costValue;
        return (E) this;
    }


    /*
     * VariedHero override
     */
    public HeroClass getHeroClass() { return null; }
    public boolean isKnight() { return false; }

}
