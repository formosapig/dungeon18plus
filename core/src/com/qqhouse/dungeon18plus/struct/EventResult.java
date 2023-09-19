package com.qqhouse.dungeon18plus.struct;

public class EventResult extends Ability {

    public int star;
    public int coin;

    public boolean isEmpty() {
		return life == 0 && attack == 0 && defense == 0 && speed == 0 && star == 0 && coin == 0;
    }

    public void clear() {
		life = 0;
		attack = 0;
		defense = 0;
		speed = 0;
		star = 0;
		coin = 0;
    }
    
    @Override
    public String toString() {
        return "R:" + life + "," + attack + "," + defense +"," + speed + "," + coin + "," + star;

    }
}
