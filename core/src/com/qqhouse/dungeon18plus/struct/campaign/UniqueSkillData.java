package com.qqhouse.dungeon18plus.struct.campaign;

import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.Operation2;

public final class UniqueSkillData {

	// need this.
	public final UniqueSkill skill;
	public final int coolDown;
	public final Operation2[] operations;
	
	// auto trigger.
	public final boolean auto;
	
	// can use or not. TODO I don't like to put this field here.
	public boolean isMastery;
	public int mastery;
	
	public UniqueSkillData(UniqueSkill skill, int coolDown, boolean auto, Operation2... operations) {
		this.skill = skill;
		this.coolDown = coolDown;
		this.auto = auto;
		this.operations = operations;
	}

}
