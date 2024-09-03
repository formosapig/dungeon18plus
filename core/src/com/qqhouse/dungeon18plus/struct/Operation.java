package com.qqhouse.dungeon18plus.struct;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.Game;

import java.util.Map;
import java.util.TreeMap;

public class Operation {
	// none
	public static final int NONE         = 0x00000000;
	
	/*
	 *  主要類型
	 *  
	 *  DAMAGE : 對敵人造成傷害...
	 *  ASSIST : 輔助, 幫自已加血,加攻,加防,復活等等...
	 *  DEBUFF : 狀態, 對敵人下毒,冰凍,石化,減防等等...
	 *  
	 */
	public static final int DAMAGE       = 0x00000001; 
	public static final int ASSIST       = 0x00000002;
	public static final int DEBUFF       = 0x00000004;
	
	/*
	 * 屬性作用方式
	 * 
	 * NON : 一舨 +-value
	 * TO : 值 = value
	 * RATE : 值 += value / 100 (百分比的增減)
	 * 
	 */
	public static final int TO           = 0x00000010; 
	public static final int RATE         = 0x00000020;

	/*
	 *  屬性類別
	 *  
	 *  LIF : life
	 *  ATK : attack
	 *  DEF : defense
	 *  SPD : speed
	 *  RES : resurrection
	 *  QUK : quick, effect cool down.
	 *  GRD : guard, taunt + resist damage %
	 *  
	 *  FIX : 固定傷害 for DAMAGE 類型
	 *  WND : wound , 依受傷製造傷害 for Damage 類型
	 *  
	 */
	public static final int LIFE         = 0x00000100; 
	public static final int ATTACK       = 0x00000200;
	public static final int DEFENSE      = 0x00000400;
	public static final int SPEED        = 0x00000800; 
	public static final int RESURRECTION = 0x00001000;
	public static final int COOL_DOWN    = 0x00002000;
	public static final int GUARD        = 0x00004000;
	public static final int FIX          = 0x00008000;
	public static final int WOUND        = 0x00010000;
	public static final int EXPERIENCE	 = 0x00020000;
	
	// resource
	public static final int KEY          = 0x00040000;
	public static final int COIN         = 0x00080000;
	public static final int STAR         = 0x00100000;
	// wilderness : soul = 0 -> death
    // dungeon : skeleton king's skill point.	
	public static final int SOUL		 = 0x00200000; 
	public static final int RAGE         = 0x00400000;
	// giant drop
	public static final int GOLDEN_COIN  = 0x00800000;
	public static final int GIANT_SOUL	 = 0x01000000;
	// r.i.p.
	public static final int DEATH        = 0x02000000;
	
	private static final int PURE_TYPE = 
			LIFE | ATTACK | DEFENSE | SPEED | RESURRECTION |
			COOL_DOWN | GUARD | FIX | WOUND | EXPERIENCE | KEY |
			COIN | STAR | SOUL | RAGE | GOLDEN_COIN | GIANT_SOUL | DEATH;
	
	
	
	
	
	
	/*
	 * extend types
	 */
	public static final int DG_LIF = LIFE | DAMAGE;
	public static final int DG_ATK = ATTACK | DAMAGE;
	public static final int DG_DEF = DEFENSE | DAMAGE;
	public static final int DG_SPD = SPEED | DAMAGE;
	public static final int DG_FIX = FIX | DAMAGE;
	public static final int DG_WND = WOUND | DAMAGE;
	
	public static final int AS_LIF = ASSIST | LIFE;
	public static final int AS_LI2 = ASSIST | TO | LIFE;
	public static final int AS_LIR = ASSIST | RATE | LIFE;
	public static final int AS_ATK = ASSIST | ATTACK;
	public static final int AS_ATR = ASSIST | RATE | ATTACK;
	public static final int AS_DEF = ASSIST | DEFENSE;
	public static final int AS_DER = ASSIST | RATE | DEFENSE;
	public static final int AS_SPD = ASSIST | SPEED;
	public static final int AS_SP2 = ASSIST | TO | SPEED;
	public static final int AS_SPR = ASSIST | RATE | SPEED;
	public static final int AS_RER = ASSIST | RESURRECTION | RATE;
	public static final int AS_QUK = ASSIST | COOL_DOWN;
	public static final int AS_GRT = ASSIST | TO | GUARD;
	
	public static final int DB_ATK = DEBUFF | ATTACK;
	public static final int DB_DEF = DEBUFF | DEFENSE;
	public static final int DB_SPD = DEBUFF | SPEED;
	
	public int type;
	public int value;
	
	/*
	 * constructor 
	 */
	public Operation() {}
	
	public Operation(int type, int value) {
		this.type = type;
		this.value = value;
	}
		
	/*
	 * is series
	 */
	public boolean isDamage() {
		return (this.type & DAMAGE) != 0;
	}
	
	public boolean isAssist() {
		return (this.type & ASSIST) != 0;
	}
	
	public boolean isDebuff() {
		return (this.type & DEBUFF) != 0;
	}
	
	public boolean isTo() {
		return (this.type & TO) != 0;
	}
	
	public boolean isRate() {
		return (this.type & RATE) != 0;
	}
	
	public boolean isLife() {
		return (type & LIFE) != 0;
	}
	
	public boolean isAttack() {
		return (type & ATTACK) != 0;
	}
	
	public boolean isDefense() {
		return (type & DEFENSE) != 0;
	}
	
	public boolean isSpeed() {
		return (type & SPEED) != 0;
	}
	
	public boolean isResurrection() {
		return (this.type & RESURRECTION) != 0;
	}

	public boolean isQuick() {
		return (type & COOL_DOWN) != 0;
	}
	
	public boolean isGuard() {
		return (type & GUARD) != 0;
	}

	
	/*
	 * get pure attribute
	 */
	public int getPureType() {
		return this.type & PURE_TYPE;
	}
	
	// get damage display value, for unique skill view and so on...
	public int getDamageDisplay(Ability base) {
		switch (getPureType()) {
		// about 75% life 
		case LIFE: return base.life * value / 133; 
		case ATTACK: return base.attack * value / 100;
		case DEFENSE: return base.defense * value / 100;
		case SPEED: return value / base.speed;
		case FIX: return value;
		// about 40% life
		case WOUND: return base.life * value / 250; 
		default: return 0;
		}
	}
	
	/*
	 * get Icon and text color
	 */
	public String getIcon() {
		return typeIcons.get(getPureType());
	}
	
	public int getColor() {
		return typeColors.get(getPureType());
	}

	/*
	 * icon and color resources
	 */
	public static Map<Integer, String> typeIcons;
	public static Map<Integer, Integer> typeColors;
	
	static {
		typeIcons = new TreeMap<Integer, String>();
		typeIcons.put(LIFE, "icon16_life");
		typeIcons.put(ATTACK, "icon16_attack");
		typeIcons.put(DEFENSE, "icon16_defense");
		typeIcons.put(SPEED, "icon16_speed");
		typeIcons.put(RESURRECTION, "icon16_resurrection");
		typeIcons.put(COOL_DOWN, "icon16_time");
		typeIcons.put(GUARD, "icon16_guard"); // use white shield.
		typeIcons.put(SOUL, "cost_soul");
		typeIcons.put(STAR, "cost_star");
		typeIcons.put(GOLDEN_COIN, "item_golden_coin"); // use bigger coin to match giant soul.
		typeIcons.put(DAMAGE, "icon16_damage");

		// XXX 注意!要跟顏色的設定一致. 似乎沒有使用到
		typeColors = new TreeMap<Integer, Integer>();
		typeColors.put(LIFE, 0xFFFFFF60);
		typeColors.put(ATTACK, 0xFFFF6060);
		typeColors.put(DEFENSE, 0xFF8080FF);
		typeColors.put(SPEED, 0xFF60FF60);
		typeColors.put(RESURRECTION, 0xFFFFFF60);	// use life's color
		typeColors.put(COOL_DOWN, 0xFF60FF60);
		typeColors.put(GUARD, 0xFFF0F0F0);
		typeColors.put(SOUL, 0xFFB0A0C0);
		typeColors.put(STAR, 0xFF9E8064);
		typeColors.put(GOLDEN_COIN, 0xFF9E8064);
		//typeColors.put(DAMAGE, ContextCompat.getColor(Game.getContext(), R.color.damage));
	}


	/*
		Icon String / String Color
	 */
	public static String getIconName(int type) {
		switch (type) {
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

	public static Color getIconColor(int type) {
		switch (type) {
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
	
}
