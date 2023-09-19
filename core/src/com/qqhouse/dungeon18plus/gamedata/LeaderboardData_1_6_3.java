package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.DataCore.DataPart;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
 * prepare for blue tooth version ...
 */
class LeaderboardData_1_6_3 extends DataPart {

	final ArrayList<ScoreHero> heroes;
	
	LeaderboardData_1_6_3() {
		super(0x52E99F1D);
		heroes = new ArrayList<>();
	}
	
	@Override
	byte[] write() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);	// 32 * 32
		for (ScoreHero hero : heroes) {
            // this
    		buffer.putInt(hero.heroClass.code);
    		// Ability
            buffer.putInt(hero.life);
    		buffer.putInt(hero.attack);
    		buffer.putInt(hero.magic);
    		buffer.putInt(hero.combo);
    		buffer.putInt(hero.defense);
    		buffer.putInt(hero.speed);
            buffer.putInt(0); // isMagic ...
    		// this
    		buffer.putInt(hero.level);
    		buffer.putInt(hero.score);
    		buffer.putLong(hero.fromAnotherDungeon);
        }
		return doWrite(buffer);
	}

	@Override
	void read(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		heroes.clear();
		while (buffer.hasRemaining()) {
            ScoreHero hero = new ScoreHero();
            // this
    		hero.heroClass = HeroClass.find(buffer.getInt());
    		// Ability
            hero.life = buffer.getInt();
    		hero.attack = buffer.getInt();
    		hero.magic = buffer.getInt();
    		hero.combo = buffer.getInt();
    		hero.defense = buffer.getInt();
    		hero.speed = buffer.getInt();
    		buffer.getInt(); // isMagic ...
    		// this
    		hero.level = buffer.getInt();
    		hero.score = buffer.getInt();
    		hero.fromAnotherDungeon = buffer.getLong();
            heroes.add(hero);
        }
	}

}
