package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.io.QQSaveGame;

import org.intellij.lang.annotations.JdkConstants;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;

/*
 * size : 51 loot x 4 byte = 204 bytes.
 * 
 * format : loot.code x n
 */
class EquipmentData_1_6_1 extends QQSaveGame.DataPart {

	final ArrayList<Item> equipments;
	
	EquipmentData_1_6_1() {
		super(401);
		equipments = new ArrayList<>();
	}
	
	@Override
	public byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(512);	// 128 equipments.
		for (Item equip : equipments)
			buffer.putInt(equip.code);
		return doWrite(buffer);
	}

	@Override
	public void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		equipments.clear();
		while (buffer.hasRemaining())
			equipments.add(Item.find(buffer.getInt()));
	}

	//@Override
	//public void afterRead() {
	//	equipments.clear();
	//	for (Item item : Item.values())
	//		if (item.isEquipment() && item.isNotSpecial() && item.isNotPremium() && item.isForSale())
	//			equipments.add(item);
	//}
}
