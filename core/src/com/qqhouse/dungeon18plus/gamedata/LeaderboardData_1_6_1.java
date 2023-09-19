package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.DataCore.DataPart;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

class LeaderboardData_1_6_1 extends DataPart {

    final ArrayList<ScoreHero> heroes;

	LeaderboardData_1_6_1() {
		super(601);
		heroes = new ArrayList<>();
	}

	// TODO 移除所有基本 class 的 write / read ,因為一改版,就得保留舊的新增新的, 這些羅輯都留在 GameData 包內就好了.

	@Override
    byte[] write() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (ScoreHero hero : heroes) {
            buffer.putInt(hero.heroClass.code);
            buffer.putInt(hero.life);
            buffer.putInt(hero.attack);
            buffer.putInt(hero.combo);
            buffer.putInt(hero.combo);
            buffer.putInt(hero.defense);
            buffer.putInt(hero.speed);
            buffer.putInt(0);//mHero.isMagic ? 1 : 0);
            buffer.putInt(hero.level);
            buffer.putInt(hero.score);
        }
        buffer.flip();
        byte[] b = new byte[buffer.limit()];
        buffer.get(b);
        return b;
    }

	@Override
	void read(byte[] data) {
		
		ArrayList<ScoreHero> scores = GameData.getInstance().getLeaderboardData();
		scores.clear();
		
		ByteBuffer buffer = ByteBuffer.wrap(data);
		while (buffer.hasRemaining()) {
			ScoreHero hero = new ScoreHero();
			hero.heroClass = HeroClass.find(buffer.getInt());
			// Ability
			hero.life = buffer.getInt();
			hero.attack = buffer.getInt();
			hero.magic = buffer.getInt();
			hero.combo = buffer.getInt();
			hero.defense = buffer.getInt();
			hero.speed = buffer.getInt();
			/*mHero.isMagic = (1 == */buffer.getInt();
			hero.level = buffer.getInt();
			hero.score = buffer.getInt();
			hero.fromAnotherDungeon = G.DEFAULT_DUNGEON_NUMBER;
			scores.add(hero);
		}
		Collections.sort(scores);
	}

}
