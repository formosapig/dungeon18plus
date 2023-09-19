package com.qqhouse.dungeon18plus.struct.hero;

import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.Ability;

public class Veteran extends Ability implements /*Parcelable,*/Comparable<Veteran> {
	public HeroClass heroClass;
	public int round;
	public Item equipment;
	public int mastery;
	
	public Veteran() {}
	
	public Veteran(ColosseumHero hero) {
		this.copy(hero);
		this.heroClass = hero.heroClass;
		this.round = hero.round;
		this.equipment = Item.NONE;
	}
	
	public Veteran(HeroClass heroClass, int life, int attack, int magic, int attackNum, int defense, int speed, boolean isMagic, int round) {
		this.heroClass = heroClass;
		this.life = life;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.round = round;
	}

	/*
	 * Parcelable
	 */
	/*
	private Veteran(Parcel in) {
		super(in);
		this.heroClass = HeroClass.find(in.readInt());
		this.round = in.readInt();
		this.equipment = Item.find(in.readInt());
		this.mastery = in.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeInt(this.heroClass.code);
		dest.writeInt(this.round);
		dest.writeInt(this.equipment.code);
		dest.writeInt(this.mastery);
	}
	
	public static final Parcelable.Creator<Veteran> CREATOR = new Parcelable.Creator<Veteran>() {
        public Veteran createFromParcel(Parcel in) {
            return new Veteran(in); 
        }

        public Veteran[] newArray(int size) {
            return new Veteran[size];
        }
    };*/

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
		return another.round - this.round;
	}

}
