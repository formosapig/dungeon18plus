package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.io.QQSaveGame;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
 * format : veteran.
 * 
 * size : 12 x 20 x 4 = 960 (veteran in barrack)
 *        12 x 16 x 4 = 768 (legions.)
 */
class VeteranData_1_6_2 extends QQSaveGame.DataPart {

	final ArrayList<Veteran> barrack;
	final ArrayList<Veteran> legion;
	
	VeteranData_1_6_2() {
		super(0xAD30D702);
		barrack = new ArrayList<>();
		legion = new ArrayList<>();
	}
	
	@Override
	public byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);	// 32 x 32
		// write barrack data
		int size = barrack.size();
		buffer.putInt(size);
		for (Veteran hero : barrack) {
		    writeVeteran(buffer, hero);
        }
		// write legion data
		size = legion.size();
		buffer.putInt(size);
		for (Veteran hero : legion) {
            writeVeteran(buffer, hero);
        }
		buffer.flip();
		byte[] b = new byte[buffer.limit()];
		buffer.get(b);
		return b;
	}

	private void writeVeteran(ByteBuffer buffer, Veteran veteran) {
        // hero class
	    buffer.putInt(veteran.heroClass.code);
        // abillity
        buffer.putInt(veteran.life);
        buffer.putInt(veteran.attack);
        buffer.putInt(veteran.magic);
        buffer.putInt(veteran.combo);
        buffer.putInt(veteran.defense);
        buffer.putInt(veteran.speed);
        buffer.putInt(0); // isMagic ...
        // other
		buffer.putInt(veteran.soul);
		buffer.putInt(veteran.equipment.code);
		buffer.putInt(veteran.mastery);
    }

	@Override
	public void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		// read barrack data.
		barrack.clear();
		int size = buffer.getInt();
		for (int s = 0; s < size; ++s) {
            barrack.add(readVeteran(buffer));
        }
		// read legion data.
		legion.clear();
		size = buffer.getInt();
		for (int s = 0; s < size; ++s) {
            legion.add(readVeteran(buffer));
        }
	}

	private Veteran readVeteran(ByteBuffer buffer) {
	    Veteran veteran = new Veteran();
        // hero class
        veteran.heroClass = HeroClass.find(buffer.getInt());
        // ability
        veteran.life = buffer.getInt();
        veteran.attack = buffer.getInt();
        veteran.magic = buffer.getInt();
        veteran.combo = buffer.getInt();
        veteran.defense = buffer.getInt();
        veteran.speed = buffer.getInt();
        buffer.getInt(); // isMagic ...
        // other
		veteran.soul = buffer.getInt();
		veteran.equipment = Item.find(buffer.getInt());
		veteran.mastery = buffer.getInt();
		return veteran;
    }
	
}
