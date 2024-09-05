package com.qqhouse.dungeon18plus.struct.hero;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.Ability;

public class Veteran extends Ability implements /*Parcelable,*/Comparable<Veteran> {
	public HeroClass heroClass;
	// dead in wilderness will make soul -1, remove character forever when soul = 0
	public int soul;
	public Item equipment;
	public int mastery;
	
	public Veteran() {}
	
	public Veteran(ColosseumHero hero) {
		this.copy(hero);
		this.heroClass = hero.heroClass;
		this.soul = hero.round;
		this.equipment = Item.NONE;
	}
	
	public Veteran(HeroClass heroClass, int life, int attack, int magic, int attackNum, int defense, int speed, boolean isMagic, int round) {
		this.heroClass = heroClass;
		this.life = life;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.soul = round;
	}

    /*
     * comparable
     */
	@Override
	public int compareTo(Veteran another) {
		// compare mHero class
		final int second = this.heroClass.compareTo(another.heroClass);
		if (second != 0)
			return second;
		// compare equipment.
		final int first = this.equipment.compareTo(another.equipment);
		if (first != 0)
			return first;
		// compare life
		final int comLife = another.life - this.life;
		if (comLife != 0)
			return comLife;
		// compare soul
		return another.soul - this.soul;
	}

}
