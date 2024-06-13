package com.qqhouse.dungeon18plus.struct.campaign;

import com.qqhouse.dungeon18plus.struct.Ability;

public class Campaigner extends Ability {
	
	// icon, bg
	public String iconKey, bgKey;
	
	// guard info for our team
	public GuardInfo ourGuard;
	
	// for life bar
	public int maxLife;
	
	// for life bar decrease
	public int nextLife;
	
	// alive
	public boolean alive;
	
	// action series.
	public int coolDown;
	public UniqueSkillData action;

	// do nothing, just for override.
	public void recordTotalDamage(int damage) {}
	public void recordTotalGuard(int guard) {}
	public void recordTotalHeal(int heal) {}

	// get nothing, just for override
	public int getTotalDamage() { return 0; }
	public int getTotalGuard() { return 0; }
	public int getTotalHeal() { return 0; }
}
