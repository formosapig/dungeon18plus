package com.qqhouse.dungeon18plus.struct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StringBuilder;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQIconText;

public class Operation2 extends Operation {
	/*
	 * 對象 
	 * 
	 * ME(0) : 對自已
	 * 1 ~ n : 對已方 1 ~ n 個
	 * ALL(-1) : 對某一方全部.
	 * 
	 * 
	 */
	public static final int ME           = 0; 
	public static final int ALL          = -1;
	
	/*
	 * special define.
	 */
	public static final Operation2 ADD_MY_LIFE_15 = new Operation2(ASSIST | LIFE, 15, ME);
	public static final Operation2 ADD_MY_LIFE_20 = new Operation2(ASSIST | LIFE, 20, ME);
	public static final Operation2 ADD_MY_LIFE_25 = new Operation2(ASSIST | LIFE, 25, ME);
	public static final Operation2 ADD_MY_LIFE_50 = new Operation2(ASSIST | LIFE, 50, ME);

	public static final Operation2 DOUBEL_MY_LIFE = new Operation2(ASSIST | LIFE | RATE, 100, ME);
	
	public static final Operation2 SUB_MY_ATTACK_1 = new Operation2(ASSIST | ATTACK, -1, ME);
	public static final Operation2 ADD_MY_ATTACK_2 = new Operation2(ASSIST | ATTACK, 2, ME);
	public static final Operation2 ADD_MY_ATTACK_3 = new Operation2(ASSIST | ATTACK, 3, ME);
	public static final Operation2 ADD_MY_ATTACK_4 = new Operation2(ASSIST | ATTACK, 4, ME);
	public static final Operation2 ADD_MY_ATTACK_5 = new Operation2(ASSIST | ATTACK, 5, ME);
	public static final Operation2 ADD_MY_ATTACK_10 = new Operation2(ASSIST | ATTACK, 10, ME);
	public static final Operation2 ADD_MY_ATTACK_15 = new Operation2(ASSIST | ATTACK, 15, ME);
    public static final Operation2 ADD_MY_ATTACK_50 = new Operation2(ASSIST | ATTACK, 50, ME);
	public static final Operation2 ADD_MY_ATTACK_50_RATE = new Operation2(ASSIST | ATTACK | RATE, 50, ME);
	
	public static final Operation2 SUB_MY_DEFENSE_1 = new Operation2(ASSIST | DEFENSE, -1, ME);
	public static final Operation2 ADD_MY_DEFENSE_1 = new Operation2(ASSIST | DEFENSE, 1, ME);
	public static final Operation2 ADD_MY_DEFENSE_2 = new Operation2(ASSIST | DEFENSE, 2, ME);
	public static final Operation2 ADD_MY_DEFENSE_4 = new Operation2(ASSIST | DEFENSE, 4, ME);
	public static final Operation2 ADD_MY_DEFENSE_5 = new Operation2(ASSIST | DEFENSE, 5, ME);
	public static final Operation2 ADD_MY_DEFENSE_10 = new Operation2(ASSIST | DEFENSE, 10, ME);
	public static final Operation2 ADD_MY_DEFENSE_15 = new Operation2(ASSIST | DEFENSE, 15, ME);
    public static final Operation2 ADD_MY_DEFENSE_50 = new Operation2(ASSIST | DEFENSE, 50, ME);

	public static final Operation2 SUB_MY_SPEED_1 = new Operation2(ASSIST | SPEED, 1, ME);
	public static final Operation2 ADD_MY_SPEED_1 = new Operation2(ASSIST | SPEED, -1, ME);
	public static final Operation2 ADD_MY_SPEED_2 = new Operation2(ASSIST | SPEED, -2, ME);
	public static final Operation2 ADD_MY_SPEED_5 = new Operation2(ASSIST | SPEED, -5, ME);
	public static final Operation2 ADD_MY_SPEED_6 = new Operation2(ASSIST | SPEED, -6, ME);
    public static final Operation2 SET_MY_SPEED_8 = new Operation2(ASSIST | SPEED | TO, 8, ME);
	
	// field
	public int target;
	
	/*
	 * constructor
	 */
	public Operation2() {}
	
	public Operation2(int type, int value, int target) {
		this.type = type;
		this.value = value;
		this.target = target;
	}
	
	/*
	 * is series
	 */
	public boolean isAll() {
		return this.target == ALL;
	}
	
	public boolean isMe() {
		return this.target == ME;
	}

	/*
		Icon String / String Color
	 */
	public String getIconName() {
		if (isDamage())
			return "damage";

		switch (this.getPureType()) {
			case LIFE: return "life";
			case ATTACK: return "attack";
			case DEFENSE: return "defense";
			case SPEED: return "speed";
			case RESURRECTION: return "resurrection";
			case COOL_DOWN: return "time";
			case GUARD: return "guard";
			case SOUL: return "cost_soul";
			case STAR: return "cost_star";
			case GOLDEN_COIN: return "cost_coin";
			case DAMAGE: return "damage";
			default :
				throw new GdxRuntimeException("no such type.");
		}
	}

	public Color getIconColor() {
		if (isDamage())
			return Game.Colour.DAMAGE;
		switch (this.getPureType()) {
			case LIFE: return Game.Colour.LIFE;
			case ATTACK: return Game.Colour.ATTACK;
			case DEFENSE: return Game.Colour.DEFENSE;
			case SPEED: return Game.Colour.SPEED;
			case RESURRECTION: return Game.Colour.LIFE;
			case COOL_DOWN: return Game.Colour.SPEED;
			case GUARD: return Game.Colour.GUARD;
			case SOUL: return Game.Colour.SOUL;
			case STAR: return Game.Colour.RARE;
			case GOLDEN_COIN: return Game.Colour.RARE;
			case DAMAGE: return Game.Colour.DAMAGE;
			default :
				throw new GdxRuntimeException("no such type.");

		}
	}

	/*
		toString ...
	 */
	public String getType() {
		if (isDamage())
			return "DMG";
		if (isLife())
			return "LIF";
		if (isAttack())
			return "ATK";
		if (isDefense())
			return "DEF";
		if (isSpeed())
			return "SPD";
		if (isGuard())
			return "GRD";
		if (isQuick())
			return "QUK";
		if (isResurrection())
			return "RER";
		return "x";
	}

	public String getText(Ability base) {
		StringBuilder builder = new StringBuilder();
		// attribute
		if (isDamage()) {
			builder.append(getDamageDisplay(base));
		} else if (isAssist()) {
			if (isTo())
				builder.append("=");
			else {
				if (0 < value)
					builder.append("+");
			}
			builder.append(value);
			if (isRate())
				builder.append("%");
		} else if (isDebuff()) {
			if (isTo())
				builder.append("=");
			else {
				if (0 < value)
					builder.append("+");
			}
			builder.append(value);
			if (isRate())
				builder.append("%");
		}
		// target number
		if (isAll())
			builder.append("xA");
		else if (0 < target && (!isDamage() || 1 != target))
			builder.append("x").append(target);
		return builder.toString();
	}
}
