package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Item;

public class EquipmentMastery implements Comparable<EquipmentMastery> {
	public final Item equipment;
	public int mastery;
	
	public EquipmentMastery(Item equip, int mastery) {
		this.equipment = equip;
		this.mastery = mastery;
	}

	/*
	 * Comparable
	 */
	@Override
	public int compareTo(EquipmentMastery another) {
		return equipment.compareTo(another.equipment);
	}

}
