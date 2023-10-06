package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Help;
import com.qqhouse.io.QQSaveGame;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
 * size : 128 help x 4 bytes = 512 bytes
 * 
 * format : help.code x n
 */
class HelpData_1_6_2 extends QQSaveGame.DataPart {

	final ArrayList<Help> understand;
	 
	HelpData_1_6_2() {
		super(0xA9C37D4F);
		understand = new ArrayList<>();
	}
	
	@Override
	public byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(512);
		for (Help h : understand)
			buffer.putInt(h.code);
		return doWrite(buffer);
	}

	@Override
	public void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		understand.clear();
		while (buffer.hasRemaining())
			understand.add(Help.find(buffer.getInt()));
	}

	@Override
	public void afterRead() {
		if (Game.Debug.TEST_HELP) {
            understand.clear();
        }
	}
}
