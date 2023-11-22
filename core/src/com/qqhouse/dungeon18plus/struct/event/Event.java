package com.qqhouse.dungeon18plus.struct.event;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.Ability;

public class Event /*extends Ability*/ {
	
	public EventType type;
	//public HeroClass heroClass; // for gladiator and old mHero (same type but different icon)
	//public int level;
	public int costType;
	public int costValue;
	public Item loot;
	public int itemCount;	// if loot == copper coin
	//public int turn;
	//public int score;

	public Event(EventType type) {
	    this.type = type;
	    costType = Game.Cost.NONE;
	    costValue = 0;
	    loot = Item.NONE;
	    itemCount = 0;
    }

    public int getLevel() { return 0; }

    public String getIcon() { return type.icon; }

//	// for old mHero and gladiator
//	public int getIcon() {
//		return null != heroClass ? heroClass.icon : type.icon;
//	}

	// battle event
	public void resetBattleAbility() {}

	//public void resetAbility() { this.copy(this.type.getAbility(this.level)); }

	// for zako and boss
	//private void copyAttribute() {
	//	this.copy(this.type.getAbility(this.level));
	//}
	
//	// builder pattern
//	public static Event.Builder create(EventType type) {
//		gBuilder.createNewEvent(type);
//		return gBuilder;
//	}
//
//	public static Event.Builder create(EventType type, HeroClass heroClass) {
//		gBuilder.createNewEvent(type, heroClass);
//		return gBuilder;
//	}
//
//	@Nullable
//    public static <E extends Event> E createEvent(Class<E> clazz) {
//	    try {
//            return clazz.newInstance();
//        } catch (Exception e) {
//	        e.printStackTrace();
//	        return null;
//        }
//    }
//
//
//	// event builder
//	private final static Event.Builder gBuilder = new Event.Builder();
//	public static class Builder {
//
//		private Event mEvent;
//
//		void createNewEvent(EventType type) {
//			if (type.needHeroClass())
//				throw new RuntimeException("user another constructor.");
//			mEvent = new Event(type);
//			mEvent.heroClass = null;
//		    mEvent.loot = Item.NONE;	// must give default value.
//		}
//
//		void createNewEvent(EventType type, HeroClass heroClass) {
//			if (!type.needHeroClass())
//				throw new RuntimeException("user another constructor.");
//			mEvent = new Event(type);
//			mEvent.heroClass = heroClass;
//		    mEvent.loot = Item.NONE;	// must give default value.
//		}
//
//		//public Event.Builder level(int level) {
//		//	mEvent.level = level;
//		//	return gBuilder;
//		//}
//
//		public Event.Builder cost(int type, int value) {
//			mEvent.costType = type;
//			mEvent.costValue = value;
//			return gBuilder;
//		}
//
//		public Event.Builder loot(Item loot) {
//			mEvent.loot = loot;
//			return gBuilder;
//		}
//
//		public Event.Builder copyAttribute() {
//			//mEvent.copyAttribute();
//			return gBuilder;
//		}
//
//		public Event done() {
//			return mEvent;
//		}
//	}

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

//	    if (this.heroClass != otherEvent.heroClass)
//	        return false;

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
