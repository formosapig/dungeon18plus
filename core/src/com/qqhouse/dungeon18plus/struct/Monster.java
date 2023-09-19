package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.EventType;

public class Monster implements Comparable<Monster>  {

	public EventType type;
	public int level;
	
	public Monster() {}
	
	public Monster(EventType type, int level) {
		this.type = type;
		this.level = level;
	}
	
	// for array list contains check
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Monster)) return false;
		
		return this.compareTo((Monster)other) == 0;
	}
	
	@Override
	public int compareTo(Monster another) {
		final int result = this.type.compareTo(another.type);
		if (result == 0)
				return this.level - another.level;
		else
			return result;
	}

}
