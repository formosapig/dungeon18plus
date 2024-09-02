package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.core.UniqueSkill;

import java.util.ArrayList;
import java.util.Collections;

public final class GiantRecord implements Comparable<GiantRecord> {
		
	/*
	 * flag define
	 */
	private static final int FLAG_UNLOCK  = 0x00000001;
	private static final int FLAG_LIFE    = 0x00000100;
	private static final int FLAG_ATTACK  = 0x00000200;
	private static final int FLAG_DEFENSE = 0x00000400;
	private static final int FLAG_SPEED   = 0x00000800;
	private static final int FLAG_DISPLAY = FLAG_LIFE | FLAG_ATTACK | FLAG_DEFENSE | FLAG_SPEED;
	
	public final GiantRace race;
	// experience to display attribute.
	public int exp;
	// flag.
	public int flag;
	// how fast defeat this giant
	public int fastWin;
	// kill how many mHero
	public int killCount;
	// giant skills
	public final ArrayList<UniqueSkill> skills = new ArrayList<>();
	// giant souls
	public final ArrayList<SoulCount> souls = new ArrayList<>();

	public GiantRecord(GiantRace race) {
		this.race = race;
	}
	
	public void defeat(int time) {
		if (time < Game.Setting.CAMPAIGN_MAX_TIME && fastWin < time) {
			fastWin = time;
		}
	}
	
	// add experience
	public void addExperience(int exp) {
		this.exp += exp;
		if (100 < this.exp)
			this.exp = 100;
		// unlock display.
		int displayUnlock = 0;
		if (10 > this.exp)
			return;
		else if (30 > this.exp)
			displayUnlock = 1;
		else if (60 > this.exp)
			displayUnlock = 2;
		else if (100 > this.exp)
			displayUnlock = 3;
		else
			displayUnlock = 4;
		
		int displayOpen = displayUnlock - Integer.bitCount(this.flag & FLAG_DISPLAY);
		ArrayList<Integer> toOpen = new ArrayList<>();
		toOpen.add(0);	// life
		toOpen.add(1);	// attack
		toOpen.add(2);  // defense
		toOpen.add(3);  // speed
		Collections.shuffle(toOpen);
		while (displayOpen > 0) {
			int openType = toOpen.remove(0);
			switch (openType) {
			case 0:
				if (0 == (this.flag & FLAG_LIFE)) {
					this.flag |= FLAG_LIFE;
					displayOpen--;
				}
				break;
			case 1:
				if (0 == (this.flag & FLAG_ATTACK)) {
					this.flag |= FLAG_ATTACK;
					displayOpen--;
				}
				break;
			case 2:
				if (0 == (this.flag & FLAG_DEFENSE)) {
					this.flag |= FLAG_DEFENSE;
					displayOpen--;
				}
				break;
			case 3:
				if (0== (this.flag & FLAG_SPEED)) {
					this.flag |= FLAG_SPEED;
					displayOpen--;
				}
				break;
			}
		}
		
	}
	
	// add kill count
	public void addKillCount(int killCount) {
		this.killCount += killCount;
	}
	
	// add skill.
	public void addSkill(UniqueSkill skill) {
		if (!skills.contains(skill)) {
			skills.add(skill);
			Collections.sort(skills);
		}
	}
	
	// add soul.
	public void addSoul(Soul soul) {
		// add soul
		for (int i = 0, s = souls.size(); i < s; ++i) {
			SoulCount data = souls.get(i);
			if (data.soul == soul) {
				data.count++;
				if (data.count > soul.maxCount)
					data.count = soul.maxCount;
				return;
			}
		}
		// not found, add new data.
		SoulCount newData = new SoulCount(soul, 1);
		souls.add(newData);
		Collections.sort(souls);
	}
	
	/*
	 * unlock series
	 */
	public boolean isUnlocked() {
		return (this.flag & FLAG_UNLOCK) != 0;
	}
	
	public void unlock() {
		this.flag |= FLAG_UNLOCK;
	}
	
	/*
	 * display series
	 */
	public boolean displayLife() {
		return (this.flag & FLAG_LIFE) != 0;
	}
	
	public boolean displayAttack() {
		return (this.flag & FLAG_ATTACK) != 0;
	}

	public boolean displayDefense() {
		return (this.flag & FLAG_DEFENSE) != 0;
	}
	
	public boolean displaySpeed() {
		return (this.flag & FLAG_SPEED) != 0;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof GiantRecord)) return false;
		return this.race.equals(((GiantRecord)other).race);
	}

	@Override
	public int compareTo(GiantRecord another) {
		return this.race.compareTo(another.race);
	}

}
