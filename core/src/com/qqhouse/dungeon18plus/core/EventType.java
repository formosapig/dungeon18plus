package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Ability;

public enum EventType {
	// ZAKO
	SKELETON(0xBC4CCEDA,
		EventType.TYPE_ZAKO,
		"blockee_skeleton",
		"btn_zako",
		"skeleton",
		"skeleton_help") {
			@Override
			public Ability getAbility(int level) {
				// L:50 A:B_A D:B_D S:B_S
				Ability base = BASE[level];
				return new Ability(40, base.attack, 0, 0, base.defense, base.speed);
			}},
	SQULETON(0xE490F428,
		EventType.TYPE_ZAKO,
		"blockee_squleton",
		"btn_zako",
		"squleton",
		"squleton_help") {
			@Override
			public Ability getAbility(int level) {
				// L:50 A:B_A D:B_D S:B_S
				Ability base = BASE[level];
				return new Ability(100 + level * 20, base.attack, base.magic, 0, base.defense, base.speed);
			}},
	SKELETON_FIGHTER(0xAA5D1760,
		EventType.TYPE_ZAKO,
		"blockee_skeleton_fighter",
		"btn_zako",
		"skeleton_fighter",
		"skeleton_fighter_help") {
			@Override
			public Ability getAbility(int level) {
				// L:50 A:B_A D:B_D S:B_S TODO think...
				Ability base = BASE[level];
				return new Ability(100 + level * 20, base.attack, 0, 0, base.defense, base.speed);
			}},
	SLIME(0x950F1DB0,
		EventType.TYPE_ZAKO,
		"blockee_slime",
		"btn_zako",
		"slime",
		"slime_help") {
			@Override
			public Ability getAbility(int level) {
				// L:35 + lv*5 A:CAL D:5 + lv * 5 S:B_S * 0.9
				Ability base = BASE[level];
				int life = 35 + level * 5;
				int defense = 5 + level * 5;
				int damage = base.attack - defense;
				if (damage < 1) damage = 1;
				int time = ((life - 1) / damage + 1) * 10 / 9;
				int attack = 30 / time + base.defense + 1;
				return new Ability(life, attack, 0, 0, defense, base.speed * 9 / 10);
			}},
	BLACK_SLIME(0xD63031CC,
		EventType.TYPE_ZAKO,
		"blockee_black_slime",
		"btn_zako",
		"black_slime",
		"black_slime_help") {
			@Override
			public Ability getAbility(int level) {
				// L:40 + lv*10 A:CAL D:4 + lv * 6 S:B_S * 0.9
				Ability base = BASE[level];
				int life = 40 + level * 10;
				int defense = 4 + level * 6;
				int damage = base.attack - defense;
				if (damage < 1) damage = 1;
				int time = ((life - 1) / damage + 1) * 10 / 9;
				int attack = 50 / time + base.defense + 1;
				return new Ability(life, attack, 0, 0, defense, base.speed * 9 / 10);
			}},
	CYCLOPS(0xC0CABC68,
		EventType.TYPE_ZAKO,
		"blockee_cyclops",
		"btn_zako",
		"cyclops",
		"cyclops_help") {
			@Override
			public Ability getAbility(int level) {
				// L:50 A:B_A D:B_D S:B_S TODO think...
				Ability base = BASE[level];
				return new Ability(50, base.attack, 0, 0, base.defense, base.speed);
			}},
	PUMPKIN(0x073DD736,
		EventType.TYPE_ZAKO,	
		"blockee_pumpkin",
		"btn_zako",
		"pumpkin",
		"pumpkin_help") {
			@Override
			public Ability getAbility(int level) {
				// L:lv * 50 A:CAL D:B_A - 10 S: 50 + level * 2
				Ability base = BASE[level];
				int life = level * 50;
				int speed = 50 + level * 2;
				int time = ((life - 1) / 10 + 1) * base.speed / speed;
				int attack = 40 / time + base.defense + 1;
				return new Ability(life, attack, 0, 0, base.attack - 10, speed);
			}},
	WEREWOLF(0xEF932C35,
		EventType.TYPE_ZAKO,
		"blockee_werewolf",
		"btn_zako",
		"werewolf",
		"werewolf_help") {
			@Override
			public Ability getAbility(int level) {
				// L:30 A:B_D + 25 D:B_A - 10 S:B_S + 1
				Ability base = BASE[level];
				return new Ability(30, base.defense + 20, 0, 0, base.attack - 10, base.speed + 1);
			}},
	YETI(0x342ABE0D,
		EventType.TYPE_ZAKO,
		"blockee_yeti",
		"btn_zako",
		"yeti",
		"yeti_help") {
			@Override
			public Ability getAbility(int level) {
				// L:CAL A:B_D D:B_A + level / 2 S:B_S + 12
				Ability base = BASE[level];
				int life = 40 * (base.speed + 8) / base.speed;
				return new Ability(life, base.defense + 1, 0, 0, base.attack + level / 2, base.speed + 8);
			}},
	GRIFFON(0x1D0CF942,
		EventType.TYPE_ZAKO,
		"blockee_griffon",
		"btn_zako",
		"griffon",
		"griffon_help") {
			@Override
			public Ability getAbility(int level) {
				// L: 4 + level 50 A:B_D+level + 34 D:B_A - L S:B_S - 3
				Ability base = BASE[level];
				return new Ability(4 + level, base.defense + 24 + level, 0, 0, base.attack - 4 - level, base.speed - 3);
			}},
	// BOSS
	CAT_SITH(0xEC056EA1,
		EventType.TYPE_BOSS,
		"blockee_cat_sith",
		"btn_boss",
		"cat_sith",
		"cat_sith_help") {
			@Override
			public Ability getAbility(int level) {
				// L:1 A:CAL D:0 S:1
				Ability base = BASE[level];
				return new Ability(1, (base.life / base.speed + 1 + base.defense), 0, 0, 0, 1);
			}},
	WAILING_WALL(0xE16DA30F,
		EventType.TYPE_BOSS,
		"blockee_wailing_wall",
		"btn_boss",
		"wailing_wall",
		"wailing_wall_help") {
			@Override
			public Ability getAbility(int level) {
				// L:9999 A:0 D:B-A + level / 2, S:CAL
				Ability base = BASE[level];
				return new Ability(9999, 0, 0, 0, base.attack + level / 2,  base.speed * 9999 / base.life);
			}},
	DEMON(0x3C6CFB03,
		EventType.TYPE_BOSS,
		"blockee_demon",
		"btn_boss",
		"demon",
		"demon_help") {
			@Override
			public Ability getAbility(int level) {
				// L:240 A:999 D:B-A - 50 S:B-S * 5
				Ability base = BASE[level];
				return new Ability(240, base.life * 2, 0, 0, base.attack - 50, base.speed * 5);
			}},
	EARTH_KNIGHT(0x257E2D9F,
		EventType.TYPE_BOSS,
		"blockee_earth_knight",
		"btn_boss",
		"earth_knight",
		"earth_knight_help") {
			@Override
			public Ability getAbility(int level) {
				// L:500 A:B-A D:B-D S:B-S
				Ability base = BASE[level];
				return new Ability(base.life, base.attack, base.magic, base.combo, base.defense, base.speed);
			}},
	FIRE_KNIGHT(0x6A1B9E64,
		EventType.TYPE_BOSS,
		"blockee_fire_knight",
		"btn_boss",
		"fire_knight",
		"fire_knight_help") {
			@Override
			public Ability getAbility(int level) {
				// L:500 A:B-A D:B-D S:B-S
				Ability base = BASE[level];
				return new Ability(base.life, base.attack, base.magic, base.combo, base.defense, base.speed);
			}},
	WATER_KNIGHT(0x503D5A8D,
		EventType.TYPE_BOSS,
		"blockee_water_knight",
		"btn_boss",
		"water_knight",
		"water_knight_help") {
			@Override
			public Ability getAbility(int level) {
				// L:500 A:B-A D:B-D S:B-S
				Ability base = BASE[level];
				return new Ability(base.life, base.attack, base.magic, base.combo, base.defense, base.speed);
			}},
	WIND_KNIGHT(0x1BA7D67D,
		EventType.TYPE_BOSS,
		"blockee_wind_knight",
		"btn_boss",
		"wind_knight",
		"wind_knight_help") {
			@Override
			public Ability getAbility(int level) {
				// L:500 A:B-A D:B-D S:B-S
				Ability base = BASE[level];
				return new Ability(base.life, base.attack, base.magic, base.combo, base.defense, base.speed);
			}},
	STEEL_CYCLOPS(0x69729BD7,
		EventType.TYPE_BOSS,
		"blockee_steel_cyclops",
		"btn_boss",
		"steel_cyclops",
		"steel_cyclops_help") {
		@Override
			public Ability getAbility(int level) {
				// L:999 A:B-D + 5 D:B-A + level / 2 S:B-S * 10
				Ability base = BASE[level];
				return new Ability(base.life, base.defense + 5, 0, 0, base.attack + level / 2, base.speed * 10);
			}},
	SKELETON_KING(0x142236F6,
		EventType.TYPE_BOSS,
		"blockee_skeleton_king",
		"btn_boss",
		"skeleton_king",
		"skeleton_king_help") {
			@Override
			public Ability getAbility(int level) {
				// L:999 A:B-A D:B-D S:B-S
				Ability base = BASE[level];
				return new Ability(base.life, base.attack, base.magic, base.combo, base.defense, base.speed);
			}},
	// GEAR			
	DOOR(0x265CC0D3,
		EventType.TYPE_DOOR,
		"blockee_door",
		"btn_fairy",
		"door",
		"door_help"),
	TRAP(0x0F391D1B,
		EventType.TYPE_TRAP,
		"blockee_chomper",
		"btn_zako",
		"trap",
		"trap_help"),
	// NPC
	MERCHANT(0x56391E1E,
		EventType.TYPE_SHOP,
		"blockee_merchant",
		"btn_shop",
		"merchant",
		"merchant_help"),
	FAIRY(0x522FDB3F,
		EventType.TYPE_NONE,
		"blockee_fairy",
		"btn_fairy",
		"fairy",
		"fairy_help"),
	VETERAN(0x3BB09970,
		EventType.TYPE_HERO,
		"blockee_novice",
		"btn_brave",
		"old_hero",
		"old_hero_help"),
	// COLOSSEUM
	GLADIATOR(0x3DA88728,
		EventType.TYPE_HERO,
		"blockee_novice",
		"btn_boss",
		"gladiator",
		"gladiator_help"),
	// END GAME			
	WIN_NORMAL(0x1CCBAB13,
		EventType.TYPE_END_GAME,
		"blockee_arena",
		"btn_fairy",
		"win_normal",
		"win_normal_help"),
	WIN_GOLDEN(0xE9BF7F03,
		EventType.TYPE_END_GAME,
		"blockee_golden_arena",
		"btn_fairy",
		"win_golden",
		"win_golden_help"),
	GAME_OVER(0xE6D00890,
		EventType.TYPE_END_GAME,
		"blockee_guard",
		"btn_fairy",
		"game_over",
		"game_over_help"),
	COLOSSEUM_MASTER(0x73856D3A,
		EventType.TYPE_END_GAME,
		"blockee_sword_master",
		"btn_fairy",
		"colosseum_master",
		"colosseum_master_help");

	
	// type
	private static final int TYPE_NONE 	   = 0x0;
	private static final int TYPE_ZAKO	   = 0x1;
	private static final int TYPE_BOSS	   = 0x2;
	private static final int TYPE_DOOR	   = 0x4;
	private static final int TYPE_TRAP	   = 0x10;
	private static final int TYPE_SHOP     = 0x20;
	private static final int TYPE_HERO     = 0x40;
	private static final int TYPE_END_GAME = 0x80;
	// combine type
	private static final int TYPE_MONSTER = TYPE_ZAKO | TYPE_BOSS;
	private static final int TYPE_SHOW_DETAIL = TYPE_ZAKO | TYPE_BOSS | TYPE_HERO;
	private static final int TYPE_SHOW_EQUIPMENT_DATA = TYPE_SHOP;
					
	// data				
	public final int code;
	public final String icon;
	public final String bg;	// background.
	public final String name;
	public final String help;
	private final int type;
	
	EventType(int code, int type, String icon, String bg, String name, String help) {
		this.code = code;
		this.type = type;
		this.icon = icon;
		this.bg = bg;
		this.name = name;
		this.help = help;
	}
	
	public Ability getAbility(int level) {
		return null;
	}
	
	public boolean isZako() {
		return (this.type & EventType.TYPE_ZAKO) != 0;
	}
	
	public boolean isBoss() {
		return (this.type & EventType.TYPE_BOSS) != 0;
	}
	
	public boolean isMonster() {
		return (this.type & EventType.TYPE_MONSTER) != 0;
	}
	
	public boolean showDetail() {
		return (this.type & EventType.TYPE_SHOW_DETAIL) != 0;
	}
	
	public boolean showEquipmentData() {
		return (this.type & EventType.TYPE_SHOW_EQUIPMENT_DATA) != 0;
	}
	
	public boolean endGame() {
		return (this.type & EventType.TYPE_END_GAME) != 0;
	}
	
	public boolean needHeroClass() {
		return (this.type & EventType.TYPE_HERO) != 0;
	}
	
	public static EventType find(int code) {
		for (EventType e : EventType.values()) {
			if (e.code == code)
				return e;
		}
		throw new RuntimeException("invalid code : " + code);
	}
	
	// basic attribute.
	private static final Ability[] BASE = {
		//           LIFE, ATT, MAG,  AN, DEF, SPD,
		new Ability(    0,   0,   0,   0,   0,   0),	// level  0
		new Ability(  100,  25,   0,   0,  10,  40),	// level  1
		new Ability(  150,  32,   0,   0,  16,  38),	// level  2
		new Ability(  200,  39,   0,   0,  23,  36),	// level  3
		new Ability(  250,  46,   0,   0,  30,  34),	// level  4
		new Ability(  300,  53,   0,   0,  37,  32),	// level  5
		new Ability(  350,  60,   0,   0,  43,  30),	// level  6
		new Ability(  400,  67,   0,   0,  50,  28),	// level  7
		new Ability(  450,  74,   0,   0,  57,  26),	// level  8
		new Ability(  500,  81,   0,   0,  64,  24),	// level  9
		new Ability(  550,  88,   0,   0,  71,  22),	// level 10
		new Ability(  600,  95,   0,   0,  77,  20),	// level 11
		new Ability(  650, 102,   0,   0,  84,  18),	// level 12
		new Ability(  700, 109,   0,   0,  91,  16),	// level 13
		new Ability(  750, 116,   0,   0,  98,  14),	// level 14
		new Ability(  800, 123,   0,   0, 105,  12),	// level 15
		new Ability(  850, 130,   0,   0, 111,  10),	// level 16
		new Ability(  900, 137,   0,   0, 118,   8),	// level 17
		new Ability(  950, 144,   0,   0, 125,   8),	// level 18
		new Ability( 1000, 152,   0,   0, 132,   8),	// level 19
		new Ability( 1050, 159,   0,   0, 139,   8),	// level 20
		new Ability( 1100, 167,   0,   0, 146,   7),	// level 21  8, 7
		new Ability( 1200, 175,   0,   0, 153,   7),	// level 22
		new Ability( 1300, 183,   0,   0, 160,   7),	// level 23
		new Ability( 1400, 191,   0,   0, 167,   7),	// level 24
		new Ability( 1500, 199,   0,   0, 174,   7),	// level 25
		new Ability( 1600, 207,   0,   0, 181,   7),	// level 26
		new Ability( 1700, 215,   0,   0, 188,   7),	// level 27
		new Ability( 1800, 223,   0,   0, 195,   7),	// level 28
		new Ability( 1900, 231,   0,   0, 202,   7),	// level 29
		new Ability( 2000, 239,   0,   0, 209,   7),	// level 30
		new Ability( 2200, 249,   0,   0, 217,   6),	// level 31 10, 8
		new Ability( 2400, 259,   0,   0, 225,   6),	// level 32
		new Ability( 2600, 269,   0,   0, 233,   6),	// level 33
		new Ability( 2800, 279,   0,   0, 241,   6),	// level 34
		new Ability( 3000, 289,   0,   0, 249,   6),	// level 35
		new Ability( 3200, 299,   0,   0, 257,   6),	// level 36
		new Ability( 3400, 309,   0,   0, 265,   6),	// level 37
		new Ability( 3600, 319,   0,   0, 273,   6),	// level 38
		new Ability( 3800, 329,   0,   0, 281,   6),	// level 39
		new Ability( 4000, 339,   0,   0, 289,   6),	// level 40
	};
}