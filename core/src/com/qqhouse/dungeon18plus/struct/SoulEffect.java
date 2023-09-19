package com.qqhouse.dungeon18plus.struct;

public class SoulEffect {
	public int life;
	public int attack;
	public int defense;
	public int speed;
	public int key;
	public int coin;
	public int star;
	
	public void plus(SoulEffect another) {
		if (null != another) {
			this.life += another.life;
			this.attack += another.attack;
			this.defense += another.defense;
			this.speed += another.speed;
			this.key += another.key;
			this.coin += another.coin;
			this.star += another.star;
		}
	}
	
}
