package com.qqhouse.dungeon18plus.struct.campaign;

import com.qqhouse.dungeon18plus.core.GiantRace;

public class Giant extends Campaigner {
	public final GiantRace race;
	
	public Giant(GiantRace race) {
		this.race = race;
		this.copy(race.attr);
		this.nextLife = this.life;
		this.maxLife = this.life;
		this.alive = true;
	}
	
	// decide action...
	public void decideAction(int time) {
		this.action = this.race.getAction(time, this.life);
		if (null != this.action)
			this.coolDown = this.action.coolDown;
	}
	
	
}
