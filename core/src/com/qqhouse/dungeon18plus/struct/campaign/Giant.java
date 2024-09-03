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
		action = race.getAction(time, life);
		if (null != action)
			startCoolDown(action.coolDown);
	}
	
	
}
