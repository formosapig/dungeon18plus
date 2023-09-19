package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Soul;

public class SoulCount implements Comparable<SoulCount> {
	public final Soul soul;
	public int count;
	
	public SoulCount(Soul soul, int count) {
		this.soul = soul;
		this.count = count;
	}

	@Override
	public int compareTo(SoulCount another) {
		return this.soul.compareTo(another.soul);
	}
	
}
