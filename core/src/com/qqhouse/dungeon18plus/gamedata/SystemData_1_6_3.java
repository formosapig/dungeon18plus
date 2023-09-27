package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.io.QQSaveGame;

import java.nio.ByteBuffer;

class SystemData_1_6_3 extends QQSaveGame.DataPart {

    int dungeonId;

	SystemData_1_6_3() {
		super(0xF4366441);
		dungeonId = 0;  // not initialized.
	}
	
	@Override
	public byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.putInt(dungeonId);
		return doWrite(buffer);
	}

	@Override
	public void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		dungeonId = buffer.getInt();
	}

}
