package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.EventType;

// TODO 1109 要改名, 以後也有別的方式可以取得分數.
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
