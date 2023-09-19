package com.qqhouse.dungeon18plus.gamedata;

import java.nio.ByteBuffer;

import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.DataCore.DataPart;
import com.qqhouse.dungeon18plus.gamedata.DataCore.DoNotSaveThis;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;

class HeroClassData_1_6_1 extends DataPart implements DoNotSaveThis {

	HeroClassData_1_6_1() {super(1000);}
	
	@Override
	void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		while (buffer.hasRemaining()) {
			final HeroClass heroClass = HeroClass.find(buffer.getInt());
			final int flag = buffer.getInt();
			final int highLevel = buffer.getInt();
			final int highScore = buffer.getInt();
			final int maxRound = buffer.getInt();
			
			final HeroClassRecord record = GameData.getInstance().getHeroClassRecord(heroClass);
			
			// private static final int FLAG_DUNGEON         		= 0x1;
			if ((flag & 0x1) != 0)
				record.unlockDungeon();
			// private static final int FLAG_ENDLESS_DUNGEON 		= 0x2;
			// private static final int FLAG_COLOSSEUM       		= 0x4;
			if ((flag & 0x4) != 0)
				record.unlockColosseum();
			
			// limit series.
			// private static final int FLAG_EXCEED_LIFE_LIMIT 	= 0x10000;
			if ((flag & 0x10000) != 0 && G.HERO_MAX_MIRROR > record.yellowMirror)
				record.yellowMirror++;
			// private static final int FLAG_EXCEED_ATTACK_LIMIT 	= 0x20000;
			if ((flag & 0x20000) != 0 && G.HERO_MAX_MIRROR > record.redMirror)
				record.redMirror++;
			// private static final int FLAG_EXCEED_DEFENSE_LIMIT 	= 0x40000;
			if ((flag & 0x40000) != 0 && G.HERO_MAX_MIRROR > record.blueMirror)
				record.blueMirror++;
			// private static final int FLAG_EXCEED_SPEED_LIMIT 	= 0x80000;
			if ((flag & 0x80000) != 0 && G.HERO_MAX_MIRROR > record.greenMirror)
				record.greenMirror++;
			
			
			record.updateLevel(highLevel);
			record.updateScore(highScore);
			record.updateRound(maxRound);
		}
	}

}
