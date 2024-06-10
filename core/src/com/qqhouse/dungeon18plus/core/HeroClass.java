package com.qqhouse.dungeon18plus.core;

import static com.qqhouse.dungeon18plus.core.Action.*;
import static com.qqhouse.dungeon18plus.core.Feat.*;
import static com.qqhouse.dungeon18plus.core.GameAlignment.*;
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
		"novice",      // key
		ORDINARY,      // 菜鳥是平凡人 (mortal, 人被殺就會死.)
		new Item[] {WOODEN_DAGGER, WOODEN_SWORD, HOLY_SWORD, WOODEN_STAFF, WOODEN_SHIELD, HOLY_SHIELD, WOODEN_RING, WHITE_RING, RING_OF_GODDESS}, // mastery equipment
		new Action[] {LIFE_UP, ATTACK_UP, DEFENSE_UP, SPEED_UP}, // action
		Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
		 250,  25,  10, 40, // start attribute
		1000, 200, 200,  7, // min limit
		2000, 255, 255,  9, // max limit
	      30,  30,  30),    // key, coin, star
	
	// normal mHero
	BARBARIAN(0xD415E422, // code
		"barbarian",      // key
		NEUTRAL,	      // 野蠻人是中立的
		new Item[] {WOODEN_SWORD, IRON_SWORD, WOODEN_STAFF, MITHRIL_STAFF, IRON_SHIELD, IRON_RING, YELLOW_RING, IRON_BOOTS}, // mastery equipment
		new Action[] {ANCESTOR_COURAGE, ANCESTOR_STRONG, ANCESTOR_WISDOM, /*WAR_CRY*/}, // action
		Feat.combine(EXPERIENCE, GEM_SEEKER, RICH), // feat
		 300,  25,  10, 40, // start attribute
		1750, 215, 200,  8, // min limit
		2000, 235, 220,  9, // max limit
		  20,  20,  20),    // key, coin, star
		    
	BERSERKER(0x0689A7FF, // code
		"berserker",      // key
        CHAOTIC,          // 狂戰士是混亂的...
		new Item[] {WOODEN_SWORD, IRON_SWORD, GREAT_SWORD, MITHRIL_SWORD, WOODEN_STAFF, WOODEN_SHIELD, RED_RING, MITHRIL_BOOTS}, // mastery equipment
		new Action[] {PERSIST, BERSERK, SPEED_UP, /*FRENZY, TWO_HAND_HOLD*/}, // action
		Feat.combine(EXPERIENCE, LUCKY, RAGE), // feat
		 250,  32,   8, 40, // start attribute
		1400, 255, 190,  8, // min limit
		1600, 255, 210,  8, // max limit
		  20,  20,  30),    // key, coin, star
		    
	DRAGOON(0xF19F7C0A, // code
		"dragoon",      // key
        LAWFUL,         // hero
		new Item[] {IRON_DAGGER, WOODEN_SHIELD, TOWER_SHIELD, MITHRIL_SHIELD, BLACK_SHIELD, IRON_RING, BLUE_RING, IRON_BOOTS}, // mastery equipment
		new Action[] {Action.LESS_LIFE_UP, Action.CHARGE, Action.HARD_DEFENSE}, // action
		Feat.combine(EXPERIENCE, BARGAIN, BURST_DOOR, BLOCK), // feat
		 250,  25,  22, 44, // start attribute
		1350, 200, 255, 10, // min limit
		1750, 220, 255, 12, // max limit
		  10,  40,  20),    // key, coin, star

	THIEF(0x392C6342, // code
		"thief",      // key
        NEUTRAL,      // 盜賊是中立的...
		new Item[] {WOODEN_DAGGER, IRON_DAGGER, WIND_DAGGER, MITHRIL_DAGGER, BLACK_SHIELD, WOODEN_RING, GREEN_RING, IRON_BOOTS, WIND_BOOTS}, // mastery equipment
		new Action[] {Action.LESS_LIFE_UP, Action.LESS_ATTACK_UP, Action.DEFENSE_UP, Action.RUNAWAY}, // action
		Feat.combine(EXPERIENCE, UNLOCK, DISARM_TRAP, STEAL), // feat
		 250,  25,  12, 36, // start attribute
		1350, 190, 220,  6, // min limit
		1500, 210, 240,  6, // max limit
		  40,  10,  20),    // key, coin, star
	
	// special mHero
	ASSASSIN(0x1C5EA8F8, // code
		"assassin",      // key
        LAWFUL,         // 暗殺者是混亂陣營/守序陣營????
		new Item[] {WOODEN_DAGGER, IRON_DAGGER, SHADOW_DAGGER, SKULL_SWORD, SKULL_SHIELD, BLACK_RING, SKULL_RING, MITHRIL_BOOTS}, // mastery equipment
		new Action[] {Action.BLOOD_ATTACK, Action.BLOOD_DEFENSE, Action.MORE_SPEED_UP}, // action
		Feat.combine(EXPERIENCE, PURIFICATION, EVASION, LIFE_LEECH), // feat
		 200,  27,  12, 38, // start attribute
		1000, 210, 210,  7, // min limit
		1250, 245, 235,  8, // max limit
		  30,  20,  20),    // key, coin, star
		
	CRUSADER(0x676DCBC6, // code
		"crusader",      // key
        LAWFUL,          // 守序, 沒問題.
		new Item[] {WOODEN_SWORD, IRON_SWORD, MITHRIL_SWORD, MITHRIL_STAFF, WOODEN_SHIELD, IRON_SHIELD, BLACK_SHIELD, IRON_BOOTS}, // mastery equipment
		new Action[] {Action.SUPERIOR_ARMOR, Action.LESS_SPEED_UP}, // upgrade
		Feat.combine(EXPERIENCE, SHOPPING, FORGING), // feat
		 250,  20,  20, 48, // start attribute
		1150, 210, 210,  9, // min limit
		1400, 240, 240, 11, // max limit
		  30,  50,  20),    // key, coin, star
	
	SWORD_MASTER(0x99F6C985, // code
		"sword_master",      // key
        NEUTRAL,             // 劍聖是中立的...?
		new Item[] {WOODEN_SWORD, IRON_SWORD, MITHRIL_SWORD, MITHRIL_STAFF, WOODEN_SHIELD, IRON_SHIELD, BLACK_SHIELD, IRON_BOOTS}, // mastery equipment
		new Action[] {Action.SUPERIOR_ARMOR, Action.LESS_SPEED_UP}, // upgrade
		Feat.combine(EXPERIENCE, SHOPPING, FORGING), // feat
		 250,  20,  20, 48, // start attribute
		1150, 210, 210,  9, // min limit
		1400, 240, 240, 11, // max limit
		  30,  50,  20),    // key, coin, star
		
	// magician
	CLERIC(0x09E8E5E3, // code
	    "cleric",      // key
        LAWFUL,        // 守序, 沒問題
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
	    Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
	     250,  25,  10, 40, // start attribute
	    1200, 200, 200,  7, // min limit
	    2000, 210, 220,  9, // max limit
	      30,  30,  30),    // key, coin, star
			
	RED_MAGE(0x50E8E6E6, // code
	    "red_mage",      // key
        NEUTRAL,         // 魔法師類的都中立居多... neutral
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
	    Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
	     500,  25,  10, 40, // start attribute
	    1000, 200, 200,  7, // min limit
	    2000, 255, 255,  9, // max limit
	       3,   3,   3), // key, coin, star),
			
	BLUE_MAGE(0x386503A8, // code
	    "blue_mage",      // key
        NEUTRAL,          // mage = neutral
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
	    Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
	     500,  25,  10, 40, // start attribute
	    1000, 200, 200,  7, // min limit
	    2000, 255, 255,  9, // max limit
	       3,   3,   3), // key, coin, star),
			
	GREEN_MAGE(0x82B269EC, // code
	    "green_mage",      // key
        NEUTRAL,           // mage = neutral
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
	    Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
	     500,  25,  10, 40, // start attribute
	    1000, 200, 200,  7, // min limit
	    2000, 255, 255,  9, // max limit
	       3,   3,   3), // key, coin, star),

	// special mHero , may buy equipments...
	MERCHANT(0x1E17343B, // code
	    "merchant",      // key
        SPECIAL,         // merchant is special.
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
	    Feat.combine(EXPERIENCE, QUICK_LEARNER, APPRENTICE), // feat
	     500,  25,  10, 40, // start attribute
	    1000, 200, 200,  7, // min limit
	    2000, 255, 255,  9, // max limit
	       3,   3,   3), // key, coin, star),
			
	// secret mHero
	FAIRY(0x533F7D6E, // code
	    "fairy",      // key
        NEUTRAL,      // fairy is neutral
	    new Item[] {}, // mastery equipment
	    new Action[] {Action.HOLY_ATTACK, Action.HOLY_DEFENSE, Action.HOLY_SPEED, Action.DOPPELGANGER}, // upgrade
	    Feat.combine(HOLY_ONE, HEAL, ENDLESS_PURSE), // feat
	      99,  22,   0, 32, // start attribute
	      99, 999, 999,  4, // min limit
	      99, 999, 999,  6, // max limit
	      30,  99,   0),    // key, coin, star
			
	// legion boss
	EARTH_KNIGHT(0x67E95111, // code
		"earth_knight",      // key
        NEUTRAL,             // 四大騎士在國王失蹤後都是中立的.
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_STAFF, IRON_SHIELD_1, MITHRIL_SHIELD, WHITE_SHIELD, IRON_RING_1, YELLOW_RING, RING_OF_LIFE, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.ATTACK_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // action
		Feat.combine(YELLOW_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2250, 255, 255,  8, // max limit
		   0,   0,   0),    // key, coin, star
			
	FIRE_KNIGHT(0xAC80E2E9, // code
		"fire_knight",      // key
        NEUTRAL,            // 四大騎士在國王失蹤後都是中立的.
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_SWORD, FIRE_SWORD, MITHRIL_STAFF, IRON_SHIELD_1, IRON_RING_1, RED_RING, RING_OF_ATTACK, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.DEFENSE_UP, Action.SPEED_UP}, // upgrade
		Feat.combine(RED_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2000, 275, 255,  8, // max limit
		   0,   0,   0),    // key, coin, star

	WATER_KNIGHT(0x9188D2A6, // code
		"water_knight",      // key
        NEUTRAL,             // 四大騎士在國王失蹤後都是中立的.
		new Item[] {IRON_DAGGER_1, IRON_SWORD_1, MITHRIL_SWORD, ICE_SWORD, IRON_SHIELD_1, MITHRIL_SHIELD, IRON_RING_1, BLUE_RING, RING_OF_DEFENSE, IRON_BOOTS_1}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.SPEED_UP}, // upgrade
		Feat.combine(BLUE_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  7, // min limit
		2000, 255, 275,  8, // max limit
		   0,   0,   0),    // key, coin, star
			
	WIND_KNIGHT(0x8E995C33, // code
		"wind_knight",      // key
        NEUTRAL,            // 四大騎士在國王失蹤後都是中立的.
		new Item[] {IRON_DAGGER_1, MITHRIL_DAGGER, IRON_SWORD_1, THUNDER_SWORD, IRON_SHIELD_1, IRON_RING_1, GREEN_RING, RING_OF_SPEED, IRON_BOOTS_1, MITHRIL_BOOTS, WIND_BOOTS}, // mastery equipment
		new Action[] {Action.LIFE_UP, Action.ATTACK_UP, Action.DEFENSE_UP}, // upgrade
		Feat.combine(GREEN_MIRROR, EQUIPMENT_STASH), // feat
		 250,  25,  10, 40, // start attribute
		2000, 255, 255,  6, // min limit
		2000, 255, 255,  7, // max limit
		   0,   0,   0),    // key, coin, star
	
	VALKYRIE(0xDA65601B, // code
        "valkyrie",      // key
        NEUTRAL,         // 女武神是中立的
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
        "skeleton_king",      // key
        CHAOTIC,              // 骷髏王是混亂的, 國王也是...
		new Item[] {}, // mastery equipment
		new Action[] {Action.DARK_FORCE, Action.DARK_GUARD, Action.DARK_SPEED}, // action
		Feat.combine(DARK_PRESENCE, UNDEAD), // feat
		 999,  25,  10, 40, // start attribute
		 999, 350, 350,  8, // min limit
		 999, 350, 350,  8, // max limit
		   0,   0,   0);    // key, coin, star

	HeroClass(int code, String key, GameAlignment alignment, Item[] masteryEquipment, Action[] actions, long feat,
			int startLife, int startAttack, int startDefense, int startSpeed,
			int minLifeLimit, int minAttackLimit, int minDefenseLimit, int minSpeedLimit,
			int maxLifeLimit, int maxAttackLimit, int maxDefenseLimit, int maxSpeedLimit,
			int startKey, int startCoin, int startStar) {
		this.code = code;
		this.key = key;
		this.alignment = alignment;
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
	public final int code;	// save, load.
	public final String key; // key to icon, name, help and so on...
	public final GameAlignment alignment; // alignment lawful、neutral、chaotic、special、ordinary
	
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
	
	//public String getBackgroundRes() {
	//	if (DARK_PRESENCE.in(this.feat)) {
	//		return "bg_boss";
	//	} else if (HOLY_ONE.in(this.feat) || this.isKnight() || this == VALKYRIE) {
	//		return "bg_fairy";
	//	} else if (this == MERCHANT)
	//		return "bg_shop";
	//	else
	//		return "bg_brave";
	//}
	
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
