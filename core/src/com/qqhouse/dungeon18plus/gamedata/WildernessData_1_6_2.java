package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.io.QQSaveGame;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
 * size : 10 giant x 30 data x 4 byte = 1200 bytes.
 *  
 * format : [giant code]  
 *  
 */
class WildernessData_1_6_2 extends QQSaveGame.DataPart {

	final ArrayList<GiantRecord> giants;
	
	WildernessData_1_6_2() {
		super(0xF4F136A2);
		giants = new ArrayList<>();
	}
	
	@Override
	public byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		for (GiantRecord giant : giants) {
            buffer.putInt(giant.race.code);
    		buffer.putInt(giant.exp);
    		buffer.putInt(giant.flag);
    		buffer.putInt(giant.fastWin);
    		buffer.putInt(giant.killCount);
    		// ultimate skills
    		buffer.putInt(giant.skills.size());
    		for (UniqueSkill us : giant.skills) {
                buffer.putInt(us.code);
            }
    		// soul counts
    		buffer.putInt(giant.souls.size());
    		for (SoulCount sc : giant.souls) {
    			buffer.putInt(sc.soul.code);
    			buffer.putInt(sc.count);
    		}
        }
		return doWrite(buffer);
	}

	@Override
	public void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		giants.clear();
		while (buffer.hasRemaining()) {
		    GiantRecord record = new GiantRecord(GiantRace.find(buffer.getInt()));
    		record.exp = buffer.getInt();
    		record.flag = buffer.getInt();
    		record.fastWin = buffer.getInt();
    		record.killCount = buffer.getInt();
    		// ultimate skills
    		int size = buffer.getInt();
        	record.skills.clear();
    		for (int i = 0; i < size; ++i) {
                record.skills.add(UniqueSkill.find(buffer.getInt()));
            }
    		// soul counts
    		size = buffer.getInt();
    		record.souls.clear();
    		for (int i = 0; i < size; ++i) {
    			record.souls.add(new SoulCount(Soul.find(buffer.getInt()), buffer.getInt()));
    		}

            giants.add(record);
        }
	}

	@Override
	protected void afterRead() {
		if (Game.Debug.TEST_ALL_GIANT)
			for (GiantRecord gr : giants)
				gr.unlock();
	}
}
