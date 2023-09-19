package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.gamedata.DataCore.DataPart;

import java.nio.ByteBuffer;

class SystemData_1_6_3 extends DataPart {

    int dungeonId;

	SystemData_1_6_3() {
		super(0xF4366441);
		dungeonId = 0;  // not initialized.
	}
	
	@Override
	byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.putInt(dungeonId);
		return doWrite(buffer);
	}

	@Override
	void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		dungeonId = buffer.getInt();
	}

}
