package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.core.EventType;
import com.qqhouse.dungeon18plus.gamedata.DataCore.DataPart;
import com.qqhouse.dungeon18plus.struct.Monster;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
 * size : 20 type x 30 level x 2 data x 4 byte = 4800 bytes
 * 
 * format : [ type.code , level ] x n
 */
class MonsterData_1_6_1 extends DataPart {

	final ArrayList<Monster> monsters;

	MonsterData_1_6_1() {
		super(501);
		monsters = new ArrayList<>();
	}

	@Override
	byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(4096); // 8 * 512
		for (Monster monster : monsters) {
		    buffer.putInt(monster.type.code);
		    buffer.putInt(monster.level);
        }
		return doWrite(buffer);
	}

	@Override
	void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		monsters.clear();
		while (buffer.hasRemaining()) {
            monsters.add(new Monster(EventType.find(buffer.getInt()), buffer.getInt()));
        }
	}
	
}
