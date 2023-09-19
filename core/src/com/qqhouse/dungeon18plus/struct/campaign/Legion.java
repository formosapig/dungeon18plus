package com.qqhouse.dungeon18plus.struct.campaign;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;

public class Legion extends Campaigner {
	
	public final HeroClass heroClass;
	public int heroFrom;
	
	// summary
	public int round;
	// loot icon
	public String lootIcon;

	// battle summary.
	private int totalDamage;
	private int totalGuard;
	private int totalHeal;

	public Legion(Veteran old) {
		this.heroClass = old.heroClass;
		this.copy(old);
		this.alive = true;
		this.totalDamage = 0;
		this.totalGuard = 0;
		this.totalHeal = 0;
	}

	@Override
	public void recordTotalDamage(int damage) {
		totalDamage += damage;
	}

	@Override
	public void recordTotalGuard(int guard) {
		totalGuard += guard;
	}

	@Override
	public void recordTotalHeal(int heal) {
		totalHeal += heal;
	}

	@Override
	public int getTotalDamage() { return totalDamage; }
	public int getTotalGuard() { return totalGuard; }
	public int getTotalHeal() { return totalHeal; }
}
