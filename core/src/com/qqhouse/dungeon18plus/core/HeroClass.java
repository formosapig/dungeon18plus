package com.qqhouse.dungeon18plus.core;

import static com.qqhouse.dungeon18plus.core.Action.*;
import static com.qqhouse.dungeon18plus.core.Feat.*;
import static com.qqhouse.dungeon18plus.core.Item.*;

public enum HeroClass {
	
//	NONE(0xCA5080DB, // code
//		0, // icon
//		0, // name
//		0, // help
//		new Loot[] {}, // mastery equipment
//		new Upgrade[] {Upgrade.NONE}, // upgrade
//		Feat.combine(), // feat
//		0, 0, 0, 0, // start attribute
//		0, 0, 0, 0, // min limit
//		0, 0, 0, 0, // max limit
//		0, 0, 0), // key, coin, star
	
	// novice
	NOVICE(0x038E37D6, // code
		"novice", // icon
		"novice", // name
		"novice_help", // help
		new Item[] {WOODEN_DAGGER, WOODEN_SWORD, HOLY_SWORD, WOODEN_STAFF, WOODEN_SHIELD, HOLY_SHIELD, WOODEN_RING, WHITE_RING, RING_OF_GODDESS}, // mastery equipment
		new Action[] {LIFE_UP, ATTACK_UP, DEFENSE_UP, SPEED_UP}, // action
		Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
		 250,  25,  10, 40, // start attribute
		1000, 200, 200,  7, // min limit
		2000, 255, 255,  9, // max limit
	      30,  30,  30),    // key, coin, star
	
	// normal mHero
	BARBARIAN(0xD415E422, // code
		"barbarian", // icon
		"barbarian", // name
		"barbarian_help", // help
		new Item[] {WOODEN_SWORD, IRON_SWORD, WOODEN_STAFF, MITHRIL_STAFF, IRON_SHIELD, IRON_RING, YELLOW_RING, IRON_BOOTS}, // mastery equipment
		new Action[] {ANCESTOR_COURAGE, ANCESTOR_STRONG, ANCESTOR_WISDOM, /*WAR_CRY*/}, // action
		Feat.combine(EXPERIENCE, GEM_SEEKER, RICH), // feat
		 300,  25,  10, 40, // start attribute
		1750, 215, 200,  8, // min limit
		2000, 235, 220,  9, // max limit
		  20,  20,  20),    // key, coin, star
		    
	BERSERKER(0x0689A7FF, // code
		"blockee_berserker", // icon
		"berserker", // name
		"berserker_help", // help
		new Item[] {WOODEN_SWORD, IRON_SWORD, GREAT_SWORD, MITHRIL_SWORD, WOODEN_STAFF, WOODEN_SHIELD, RED_RING, MITHRIL_BOOTS}, // mastery equipment
		new Action[] {PERSIST, BERSERK, SPEED_UP, /*FRENZY, TWO_HAND_HOLD*/}, // action
		Feat.combine(EXPERIENCE, LUCKY, RAGE), // feat
		 250,  32,   8, 40, // start attribute
		1400, 255, 190,  8, // min limit
		1600, 255, 210,  8, // max limit
		  20,  20,  30),    // key, coin, star
		    
	DRAGOON(0xF19F7C0A, // code
		"blockee_dragoon", // icon
		"dragoon", // name
		"dragoon_help", // help
		new Item[] {IRON_DAGGER, WOODEN_SHIELD, TOWER_SHIELD, MITHRIL_SHIELD, BLACK_SHIELD, IRON_RING, BLUE_RING, IRON_BOOTS}, // mastery equipment
		new Action[] {Action.LESS_LIFE_UP, Action.CHARGE, Action.HARD_DEFENSE}, // action
		Feat.combine(EXPERIENCE, BARGAIN, BURST_DOOR, BLOCK), // feat
		 250,  25,  22, 44, // start attribute
		1350, 200, 255, 10, // min limit
		1750, 220, 255, 12, // max limit
		  10,  40,  20),    // key, coin, star

	THIEF(0x392C6342, // code
		"blockee_thief", // icon
		"thief", // name
		"thief_help", // help
		new Item[] {WOODEN_DAGGER, IRON_DAGGER, WIND_DAGGER, MITHRIL_DAGGER, BLACK_SHIELD, WOODEN_RING, GREEN_RING, IRON_BOOTS, WIND_BOOTS}, // mastery equipment
		new Action[] {Action.LESS_LIFE_UP, Action.LESS_ATTACK_UP, Action.DEFENSE_UP, Action.RUNAWAY}, // action
		Feat.combine(EXPERIENCE, UNLOCK, DISARM_TRAP, STEAL), // feat
		 250,  25,  12, 36, // start attribute
		1350, 190, 220,  6, // min limit
		1500, 210, 240,  6, // max limit
		  40,  10,  20),    // key, coin, star
	
	// special mHero
	ASSASSIN(0x1C5EA8F8, // code
		"blockee_assassin", // icon
		"assassin", // name
		"assassin_help", // help
		new Item[] {WOODEN_DAGGER, IRON_DAGGER, SHADOW_DAGGER, SKULL_SWORD, SKULL_SHIELD, BLACK_RING, SKULL_RING, MITHRIL_BOOTS}, // mastery equipment
		new Action[] {Action.BLOOD_ATTACK, Action.BLOOD_DEFENSE, Action.MORE_SPEED_UP}, // action
		Feat.combine(EXPERIENCE, PURIFICATION, EVASION, LIFE_LEECH), // feat
		 200,  27,  12, 38, // start attribute
		1000, 210, 210,  7, // min limit
		1250, 245, 235,  8, // max limit
		  30,  20,  20),    // key, coin, star
		
	CRUSADER(0x676DCBC6, // code
		"blockee_crusader", // icon
		"crusader", // name
		"crusader_help", // help
		new Item[] {WOODEN_SWORD, IRON_SWORD, MITHRIL_SWORD, MITHRIL_STAFF, WOODEN_SHIELD, IRON_SHIELD, BLACK_SHIELD, IRON_BOOTS}, // mastery equipment
		new Action[] {Action.SUPERIOR_ARMOR, Action.LESS_SPEED_UP}, // upgrade
		Feat.combine(EXPERIENCE, SHOPPING, FORGING), // feat
		 250,  20,  20, 48, // start attribute
		1150, 210, 210,  9, // min limit
		1400, 240, 240, 11, // max limit
		  30,  50,  20),    // key, coin, star
	
	SWORD_MASTER(0x99F6C985, // code
			"blockee_sword_master", // icon
			"sword_master", // name
			"sword_master_help", // help
			new Item[] {WOODEN_SWORD, IRON_SWORD, MITHRIL_SWORD, MITHRIL_STAFF, WOODEN_SHIELD, IRON_SHIELD, BLACK_SHIELD, IRON_BOOTS}, // mastery equipment
			new Action[] {Action.SUPERIOR_ARMOR, Action.LESS_SPEED_UP}, // upgrade
			Feat.combine(EXPERIENCE, SHOPPING, FORGING), // feat
			 250,  20,  20, 48, // start attribute
			1150, 210, 210,  9, // min limit
			1400, 240, 240, 11, // max limit
			  30,  50,  20),    // key, coin, star
		
	// magician
	CLERIC(0x09E8E5E3, // code
			"blockee_cleric", // icon
			"cleric", // name
			"cleric_help", // help
			new Item[] {}, // mastery equipment
			new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
			Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
			 250,  25,  10, 40, // start attribute
			1200, 200, 200,  7, // min limit
			2000, 210, 220,  9, // max limit
			  30,  30,  30),    // key, coin, star
			
	RED_MAGE(0x50E8E6E6, // code
			"blockee_red_mage", // icon
			"red_mage", // name
			"red_mage_help", // help
			new Item[] {}, // mastery equipment
			new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
			Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
			 500,  25,  10, 40, // start attribute
			1000, 200, 200,  7, // min limit
			2000, 255, 255,  9, // max limit
			3, 3, 3), // key, coin, star),
			
	BLUE_MAGE(0x386503A8, // code
			"blockee_blue_mage", // icon
			"blue_mage", // name
			"blue_mage_help", // help
			new Item[] {}, // mastery equipment
			new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
			Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
			 500,  25,  10, 40, // start attribute
			1000, 200, 200,  7, // min limit
			2000, 255, 255,  9, // max limit
			3, 3, 3), // key, coin, star),
			
	GREEN_MAGE(0x82B269EC, // code
			"blockee_green_mage", // icon
			"green_mage", // name
			"green_mage_help", // help
			new Item[] {}, // mastery equipment
			new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
			Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
			 500,  25,  10, 40, // start attribute
			1000, 200, 200,  7, // min limit
			2000, 255, 255,  9, // max limit
			3, 3, 3), // key, coin, star),

	// special mHero , may buy equipments...
	MERCHANT(0x1E17343B, // code
			"blockee_merchant", // icon
			"merchant", // name
			"merchant_help", // help
			new Item[] {}, // mastery equipment
			new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
			Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
			 500,  25,  10, 40, // start attribute
			1000, 200, 200,  7, // min limit
			2000, 255, 255,  9, // max limit
			3, 3, 3), // key, coin, star),
			
	// secret mHero
	FAIRY(0x533F7D6E, // code
		"blockee_fairy", // icon
		"fairy", // name
		"fairy_help", // help
		new Item[] {}, // mastery equipment
		new Action[] {Action.HOLY_ATTACK, Action.HOLY_DEFENSE, Action.HOLY_SPEED}, // upgrade
		Feat.combine(HOLY_ONE, HEAL, ENDLESS_PURSE), // feat
		  99,  22,   0, 32, // start attribute
		  99, 999, 999,  4, // min limit
		  99, 999, 999,  6, // max limit
	      30,  99,   0),    // key, coin, star
			
	// legion boss
	EARTH_KNIGHT(0x67E95111, // code
		"blockee_earth_knight", // icon
		"earth_knight", // name
		"earth_knight_help", // help
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_STAFF, IRON_SHIELD_1, MITHRIL_SHIELD, WHITE_SHIELD, IRON_RING_1, YELLOW_RING, RING_OF_LIFE, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
		Feat.combine(YELLOW_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2250, 255, 255,  8, // max limit
		   0,   0,   0),    // key, coin, star
			
	FIRE_KNIGHT(0xAC80E2E9, // code
		"blockee_fire_knight", // icon
		"fire_knight", // name
		"fire_knight_help", // help
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_SWORD, FIRE_SWORD, MITHRIL_STAFF, IRON_SHIELD_1, IRON_RING_1, RED_RING, RING_OF_ATTACK, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // upgrade
		Feat.combine(RED_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2000, 275, 255,  8, // max limit
		   0,   0,   0),    // key, coin, star

	WATER_KNIGHT(0x9188D2A6, // code
		"blockee_water_knight", // icon
		"water_knight", // name
		"water_knight_help", // help
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_SWORD, ICE_SWORD, IRON_SHIELD_1, MITHRIL_SHIELD, IRON_RING_1, BLUE_RING, RING_OF_DEFENSE, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.SPEED_UP}, // upgrade
		Feat.combine(BLUE_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2000, 255, 275,  8, // max limit
		   0,   0,   0),    // key, coin, star
			
	WIND_KNIGHT(0x8E995C33, // code
		"blockee_wind_knight", // icon
		"wind_knight", // name
		"wind_knight_help", // help
		new Item[] {IRON_DAGGER_1, MITHRIL_DAGGER, IRON_SWORD_1, THUNDER_SWORD, IRON_SHIELD_1, IRON_RING_1, GREEN_RING, RING_OF_SPEED, IRON_BOOTS_1, MITHRIL_BOOTS, WIND_BOOTS}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP}, // upgrade
		Feat.combine(GREEN_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  6, // min limit
		2000, 255, 255,  7, // max limit
		   0,   0,   0),    // key, coin, star
	
	VALKYRIE(0xDA65601B, // code
			"blockee_valkyrie", // icon
			"valkyrie", // name
			"valkyrie_help", // help
			new Item[] {},//IRON_DAGGER_1, MITHRIL_DAGGER, IRON_SWORD_1, THUNDER_SWORD, IRON_SHIELD_1, IRON_RING_1, GREEN_RING, RING_OF_SPEED, IRON_BOOTS_1, MITHRIL_BOOTS, WIND_BOOTS}, // mastery equipment
			new Action[] {},
			Feat.combine(GREEN_MIRROR, EQUIPMENT_STASH), // feat
			 250,  25,  10, 40, // start attribute
			2000, 255, 255,  6, // min limit
			2000, 255, 255,  7, // max limit
			3, 3, 3), // key, coin, star),

	// TODO 0411 骷髏王昇級需求太高,需要給他開個小灶
	// boss
	SKELETON_KING(0x184D3093, // code
		"blockee_skeleton_king", // icon
		"skeleton_king", // name
		"skeleton_king_help", // help
		new Item[] {}, // mastery equipment
		new Action[] {Action.DARK_FORCE, Action.DARK_GUARD, Action.DARK_SPEED}, // action
		Feat.combine(DARK_PRESENCE, UNDEAD), // feat
		 999,  25,  10, 40, // start attribute
		 999, 350, 350,  8, // min limit
		 999, 350, 350,  8, // max limit
		   0,   0,   0);    // key, coin, star

	HeroClass(int code, String icon, String name, String help, Item[] masteryEquipment, Action[] actions, long feat,
			int startLife, int startAttack, int startDefense, int startSpeed,
			int minLifeLimit, int minAttackLimit, int minDefenseLimit, int minSpeedLimit,
			int maxLifeLimit, int maxAttackLimit, int maxDefenseLimit, int maxSpeedLimit,
			int startKey, int startCoin, int startStar) {
		this.code = code;
		this.icon = icon;
		this.name = name;
		this.help = help;
		this.masteryEquipment = masteryEquipment;
		this.actions = actions;
		this.feat = feat;
		this.startLife = startLife;
		this.startAttack = startAttack;
		this.startDefense = startDefense;
		this.startSpeed = startSpeed;
		this.minLifeLimit = minLifeLimit;
		this.minAttackLimit = minAttackLimit;
		this.minDefenseLimit = minDefenseLimit;
		this.minSpeedLimit = minSpeedLimit;
		this.maxLifeLimit = maxLifeLimit;
		this.maxAttackLimit = maxAttackLimit;
		this.maxDefenseLimit = maxDefenseLimit;
		this.maxSpeedLimit = maxSpeedLimit;
		this.startKey = startKey;
		this.startCoin = startCoin;
		this.startStar = startStar;
	}
	
	// res
	public final String icon;
	public final String name;
	public final String help;
	public final int code;	// save, load.
	
	// mastery equipment
	public final Item[] masteryEquipment;

	// action
	public final Action[] actions;
	
	// feat
	public final long feat;
	
	// attribute (start, limit[min] ~ limit[max] )
	public final int startLife, startAttack, startDefense, startSpeed;
	public final int minLifeLimit, minAttackLimit, minDefenseLimit, minSpeedLimit;
	public final int maxLifeLimit, maxAttackLimit, maxDefenseLimit, maxSpeedLimit;
	
	// key, coin, star (start)
	public final int startKey, startCoin, startStar;
	
	public static HeroClass find(int code) {
		for (HeroClass h : HeroClass.values()) {
			if (h.code == code)
				return h;
		}
		throw new RuntimeException("invalid code : " + code);
	}
	
	public boolean isKnight() {
		return this == EARTH_KNIGHT || this == FIRE_KNIGHT || this == WATER_KNIGHT || this == WIND_KNIGHT;
	}
	
	public String getBackgroundRes() {
		if (DARK_PRESENCE.in(this.feat)) {
			return "bg_boss";
		} else if (HOLY_ONE.in(this.feat) || this.isKnight() || this == VALKYRIE) {
			return "bg_fairy";
		} else if (this == MERCHANT)
			return "bg_shop";
		else
			return "bg_brave";
	}
	
	public String getBackgroundBtn() {
		if (DARK_PRESENCE.in(this.feat)) {
			return "btn_boss";
		} else if (HOLY_ONE.in(this.feat) || this.isKnight() || this == VALKYRIE) {
			return "btn_fairy";
		} else if (this == MERCHANT)
			return "btn_shop";
		else
			return "btn_brave";
	}
	
}
