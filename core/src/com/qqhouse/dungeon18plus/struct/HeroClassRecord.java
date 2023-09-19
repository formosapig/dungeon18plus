package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.core.Soul;

import java.util.ArrayList;
import java.util.Collections;

// 資料物件應該管理資料的儲存以及取得,含有相關羅輯很正常.
public class HeroClassRecord implements Comparable<HeroClassRecord> {

	// only used in mHero class record.
	private static final int FLAG_DUNGEON         		= 0x1;
	private static final int FLAG_ENDLESS_DUNGEON 		= 0x2;
	private static final int FLAG_COLOSSEUM       		= 0x4;
	
	// basic
	public HeroClass heroClass;
	
	// flag
	public int flag;
	
	// dungeon
	public int highLevel;
	public int highScore;
	
	// colosseum
	public int maxRound;
	
	// coin, I come back again.
	public int coin;
	
	// max soul size, can extend to 8
	public int maxSoulSize;
	
	// mirror count.
	public int yellowMirror, redMirror, blueMirror, greenMirror;
		
	// mastery data
	public final ArrayList<EquipmentMastery> equips = new ArrayList<>();
	
	// soul data
	public final ArrayList<SoulCount> souls = new ArrayList<>();
	
	
	// construct
	public HeroClassRecord() {}
	
	// default unlock dungeon mode.
	public HeroClassRecord(HeroClass heroClass) {
		this.heroClass = heroClass;
		//this.flag |= FLAG_DUNGEON;
		this.flag = 0;	// nothing unlock in default.
		this.highLevel = 0;
		this.highScore = 0;
		this.maxRound = 0;
		this.maxSoulSize = G.HERO_DEFAULT_SOUL_SIZE;
	}
	
	public HeroClassRecord(HeroClass heroClass, int flag, int highLevel, int highScore, int maxRound, int maxSoulSize) {
		this.heroClass = heroClass;
		this.flag = flag;
		this.highLevel = highLevel;
		this.highScore = highScore;
		this.maxRound = maxRound;
		this.maxSoulSize = maxSoulSize;
	}
	
	// update
	public void updateLevel(int level) {
		if (level > this.highLevel)
			this.highLevel = level;
	}
	
	public void updateScore(int score) {
		if (score > this.highScore)
			this.highScore = score;
	}
	
	public void updateRound(int round) {
		if (round > this.maxRound)
			this.maxRound = round;
	}
	
	public void updateMastery(Item equip, int mastery) {
		// update data.
		for (int i = 0, s = equips.size(); i < s; ++i) {
			EquipmentMastery em = equips.get(i);
			if (em.equipment == equip) {
				// can not exceed max
				if (G.SPECIFIC_MASTERY_MAX < mastery)
					mastery = G.SPECIFIC_MASTERY_MAX;
				if (em.mastery < mastery)
					em.mastery = mastery;
				return;
			}
		}
		// not found, check to add
		for (Item item : heroClass.masteryEquipment) {
			if (item == equip) {
				equips.add(new EquipmentMastery(equip, mastery));
				Collections.sort(equips);
				return;
			}
		}
	}
	
	public int getMastery(Item equip) {
		for (int i = 0, s = equips.size(); i < s; ++i) {
			EquipmentMastery em = equips.get(i);
			if (em.equipment == equip) {
				return em.mastery;
			}
		}
		return G.MASTERY_NOT_FOUND;
	}
	
	public int getSoulCount() {
		int result = 0;
		for (SoulCount sc : souls)
			result += sc.count;
		return result;
	}
	
	public void addSoul(Soul soul) {
		// soul size is full
		if (maxSoulSize <= getSoulCount())
			return;
		
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
	
	public void removeSoul(Soul soul) {
		for (int i = 0, s = souls.size(); i < s; ++i) {
			SoulCount data = souls.get(i);
			if (data.soul == soul) {
				data.count--;
				if (0 >= data.count)
					souls.remove(i);
				return;
			}
		}
	}
	
	public void removeSoulAt(int index) {
		if (0 > index || souls.size() <= index)
			return;
		SoulCount data = souls.get(index);
		data.count--;
		if (0 >= data.count)
			souls.remove(index);
	}
	
	// unlock
	public void unlockDungeon() {
		flag |= FLAG_DUNGEON;
	}
	
	public void unlockEndlessDungeon() {
		flag |= FLAG_ENDLESS_DUNGEON;
	}
	
	public void unlockColosseum() {
		flag |= FLAG_COLOSSEUM;
	}
	
	public boolean isEndlessDungeon() {
		return (flag & FLAG_ENDLESS_DUNGEON) != 0;
	}
	
	public boolean checkUnlock(boolean isColosseum) {
		if (isColosseum)
			return (flag & FLAG_COLOSSEUM) != 0;
		else
			return (flag & FLAG_DUNGEON) != 0;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof HeroClassRecord)) return false;
		return this.heroClass.equals(((HeroClassRecord) other).heroClass);
	}
	
	@Override
	public int compareTo(HeroClassRecord another) {
		return this.heroClass.compareTo(another.heroClass);
	}

}
