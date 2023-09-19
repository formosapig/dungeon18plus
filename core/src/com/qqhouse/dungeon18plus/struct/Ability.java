package com.qqhouse.dungeon18plus.struct;

import java.util.Locale;

public class Ability /*implements Parcelable*/ {

    public int life;
    public int attack;
    public int magic;
    public int combo;
    public int defense;
    public int speed;
    //public boolean isMagic;

    public Ability() {}

	public Ability(int life, int attack, int magic, int combo, int defense, int speed) {
		
		this.life = life;
		this.attack = attack;
		this.magic = magic;
		this.combo = combo;
		this.defense = defense;
		this.speed = speed;
		
	}
	
	public Ability(Ability src) {
		if (null != src) {
			this.life = src.life;
			this.attack = src.attack;
			this.magic = src.magic;
			this.combo = src.combo;
			this.defense = src.defense;
			this.speed = src.speed;
		}
	}
	
	public void clear() {
		this.life = 0;
		this.attack = 0;
		this.magic = 0;
		this.combo = 0;
		this.defense = 0;
		this.speed = 0;
	}
	
	public void copy(Ability src) {
		if (null != src) {
			this.life = src.life;
			this.attack = src.attack;
			this.magic = src.magic;
			this.combo = src.combo;
			this.defense = src.defense;
			this.speed = src.speed;
		}
	}

	public void plus(Ability src) {
		if (null != src) {
			this.life += src.life;
			this.attack += src.attack;
			this.magic += src.magic;
			this.combo += src.combo;
			this.defense += src.defense;
			this.speed += src.speed;
		}
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Ability)) return false;
        Ability otherAbility = (Ability) obj;
        return otherAbility.life == this.life
            && otherAbility.attack == this.attack
            && otherAbility.combo == this.combo
            && otherAbility.defense == this.defense
            && otherAbility.speed == this.speed;
    }

    @Override
	public String toString() {
		return String.format(Locale.US, "[L:%4d, %s:%3dx%2d, D:%3d, S:%3d]", life, (magic > 0 ? "M" : "A"), (magic > 0 ? magic : attack), combo, defense, speed);
	}

	/*
	 * Parcelable
	 */
	/*public Ability(Parcel in) {
		this.life = in.readInt();
		this.attack = in.readInt();
		this.magic = in.readInt();
		this.combo = in.readInt();
		this.defense = in.readInt();
		this.speed = in.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.life);
		dest.writeInt(this.attack);
		dest.writeInt(this.magic);
		dest.writeInt(this.combo);
		dest.writeInt(this.defense);
		dest.writeInt(this.speed);
	}
	
	public static final Parcelable.Creator<Ability> CREATOR = new Parcelable.Creator<Ability>() {
        public Ability createFromParcel(Parcel in) {
            return new Ability(in);
        }

        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };*/

}
