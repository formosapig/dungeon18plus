package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.EventType;

public final class BossKill {
	public final EventType boss;
	public final int score;
	public final int turn;

	public BossKill(EventType boss, int score, int turn) {
		this.boss = boss;
		this.score = score;
		this.turn = turn;
	}
}
