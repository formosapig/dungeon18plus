package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.core.Item;

public class EventInfo {
	
	public String eventIcon;	// may change with gladiator/old mHero
	public EventType event;
	public int eventExp;	// need calculate
	public Item loot;
	public boolean isUnknown;
	public int lootKey;
	public int lootCoin;
	public int lootStar;
	
	public void clear() {
		// event not clear
		// loot must clear
		loot = Item.NONE;
		isUnknown = true;
		lootKey = 0;
		lootCoin = 0;
		lootStar = 0;
	}
	
}
