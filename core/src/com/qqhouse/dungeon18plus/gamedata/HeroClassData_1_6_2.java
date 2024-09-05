package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.core.Soul;
import com.qqhouse.io.QQSaveGame;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;

import java.nio.ByteBuffer;
import java.util.ArrayList;

class HeroClassData_1_6_2 extends QQSaveGame.DataPart {

    final ArrayList<HeroClassRecord> records;

    HeroClassData_1_6_2() {
        super(0x6931A544);
        records = new ArrayList<>();
    }

    @Override
    public byte[] write() {
        // 20 mHero class x 48 each * 4 bytes.
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        for (HeroClassRecord record : records) {
            buffer.putInt(record.heroClass.code);
            buffer.putInt(record.gameMode);
            buffer.putInt(record.highLevel);
            buffer.putInt(record.highScore);
            buffer.putInt(record.maxRound);
            buffer.putInt(record.coin);
            buffer.putInt(record.maxSoulSize);
            buffer.putInt(record.yellowMirror);
            buffer.putInt(record.redMirror);
            buffer.putInt(record.blueMirror);
            buffer.putInt(record.greenMirror);
            // mastery
            int size = record.equips.size();
            buffer.putInt(size);
            if (0 < size) {
                for (EquipmentMastery em : record.equips) {
                    buffer.putInt(em.equipment.code);
                    buffer.putInt(em.mastery);
                }
            }
            // souls
            size = record.souls.size();
            buffer.putInt(size);
            if (0 < size) {
                for (SoulCount sc : record.souls) {
                    buffer.putInt(sc.soul.code);
                    buffer.putInt(sc.count);
                }
            }
        }
        return doWrite(buffer);
    }

    @Override
    public void read(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        records.clear();
        while (buffer.hasRemaining()) {
            HeroClassRecord record = new HeroClassRecord();
            record.heroClass = HeroClass.find(buffer.getInt());
            record.gameMode = buffer.getInt();
            record.highLevel = buffer.getInt();
            record.highScore = buffer.getInt();
            record.maxRound = buffer.getInt();
            record.coin = buffer.getInt();
            record.maxSoulSize = buffer.getInt();
            record.yellowMirror = buffer.getInt();
            record.redMirror = buffer.getInt();
            record.blueMirror = buffer.getInt();
            record.greenMirror = buffer.getInt();
            // read mastery
            int size = buffer.getInt();
            record.equips.clear();
            for (int i = 0; i < size; ++i) {
                record.equips.add(new EquipmentMastery(Item.find(buffer.getInt()), buffer.getInt()));
            }
            // souls
            size = buffer.getInt();
            record.souls.clear();
            for (int i = 0; i < size; ++i) {
                record.souls.add(new SoulCount(Soul.find(buffer.getInt()), buffer.getInt()));
            }

            records.add(record);
        }
    }

	//@Override
	//public void afterRead() {
	//	for (HeroClassRecord record : records) {
	//		record.equips.clear();
	//	}
	//}
}
