package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.gamedata.GameData;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;

import java.util.ArrayList;
import java.util.Collections;

public class LegionManager {

	// legion data
	final private ArrayList<Veteran> legion;
	
	// barrack data
	final private ArrayList<Veteran> barrack;
	
	public LegionManager() {
		
		// read legion data from GameData....
		legion = GameData.getInstance().getLegionData();
		
		// read veteran data from GameData...
		barrack = GameData.getInstance().getBarrackData();
		
	}

	// legion -> barrack
	public boolean removeLegion(int position) {
		barrack.add(legion.remove(position));
		Collections.sort(barrack);
		return true;
	}
	
	// barrack -> legion
	public boolean addLegion(int position) {
		if (true/*canAddFromBarrackPosition(position)*/) {
			// can add.
			legion.add(barrack.remove(position));
			return true;
		}
		return false;
	}
}
