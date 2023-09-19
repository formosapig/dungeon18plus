package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Varier;

import static com.qqhouse.dungeon18plus.core.Action.Type.POTION;
import static com.qqhouse.dungeon18plus.core.Action.Type.SKILL;
import static com.qqhouse.dungeon18plus.core.Action.Type.UPGRADE;
import static com.qqhouse.dungeon18plus.struct.Varier.*;

public enum Action {
	
	/*
	 * Upgrades
	 */
	
	// life series
	POOR_LIFE_UP(UPGRADE, "icon32_life", STAR_6, LIFE_15),
	LESS_LIFE_UP(UPGRADE, "icon32_life", STAR_8, LIFE_25),
	LIFE_UP(UPGRADE, "icon32_life", STAR_10, LIFE_50),
	MORE_LIFE_UP(UPGRADE, "icon32_life", STAR_8, LIFE_50),
	
	// attack series
	LESS_ATTACK_UP(UPGRADE, "icon32_attack", STAR_12, ATTACK_4),
	ATTACK_UP(UPGRADE, "icon32_attack", STAR_10, ATTACK_4),
	MORE_ATTACK_UP(UPGRADE, "icon32_attack", STAR_10, ATTACK_5),
	
	// defense series
	LESS_DEFENSE_UP(UPGRADE, "icon32_defense", STAR_12, DEFENSE_4),
	DEFENSE_UP(UPGRADE, "icon32_defense", STAR_10, DEFENSE_4),
	MORE_DEFENSE_UP(UPGRADE, "icon32_defense", STAR_10, DEFENSE_5),
	
	// speed series
	LESS_SPEED_UP(UPGRADE, "icon32_speed", STAR_10, SPEED_1),
	SPEED_UP(UPGRADE, "icon32_speed", STAR_10, SPEED_2),
	MORE_SPEED_UP(UPGRADE, "icon32_speed", STAR_8, SPEED_2),
	
	// barbarian
	ANCESTOR_COURAGE(UPGRADE, "icon32_yellow_sword", STAR_6, LIFE_15, ATTACK_2),
	ANCESTOR_STRONG(UPGRADE, "icon32_yellow_shield", STAR_6, LIFE_15, DEFENSE_2),
	ANCESTOR_WISDOM(UPGRADE, "icon32_yellow_shoe", STAR_6, LIFE_15, SPEED_1),
	
	// berserker
	PERSIST(UPGRADE, "icon32_blue_heart", STAR_6, LIFE_20, DEFENSE_1),
	BERSERK(UPGRADE, "icon32_attack", STAR_18, ATTACK_10, DEFENSE_N1),
	
	// dragoon
	CHARGE(UPGRADE, "icon32_green_sword", STAR_15, ATTACK_3, SPEED_1),
	HARD_DEFENSE(UPGRADE, "icon32_defense", STAR_15, DEFENSE_10, SPEED_N1),

	// thief
	RUNAWAY(UPGRADE, "icon32_speed", STAR_13, ATTACK_N1, SPEED_5),
	
	// assassin
	BLOOD_ATTACK(UPGRADE, "icon32_black_sword", STAR_11, ATTACK_5),
	BLOOD_DEFENSE(UPGRADE, "icon32_black_shield", STAR_11, DEFENSE_5),
	
	// crusader
	SUPERIOR_ARMOR(UPGRADE, "icon32_purple_armor", STAR_12, LIFE_15, ATTACK_2, DEFENSE_2),
	
	// fairy
	HOLY_ATTACK(UPGRADE, "icon32_white_sword", STAR_9, ATTACK_6),
	HOLY_DEFENSE(UPGRADE, "icon32_white_shield", STAR_9, DEFENSE_6),
	HOLY_SPEED(UPGRADE, "icon32_white_shoe", STAR_9, SPEED_2),
	
	// skeleton king
	DARK_FORCE(UPGRADE, "icon32_black_sword", SOUL_99, ATTACK_15),
	DARK_GUARD(UPGRADE, "icon32_black_shield", SOUL_99, DEFENSE_15),
	DARK_SPEED(UPGRADE, "icon32_black_shoe", SOUL_99, SPEED_6),
	
	// mana power
	//MANA_POWER("icon32_purple_armor", Operation.COST_STAR_5, Operation2.ADD_MANA_POWER, Operation2.ADD_MANA_10);
	
	// magic defense
	MAGICIAN_DEFENSE(UPGRADE, "icon32_purple_armor", STAR_12, DEFENSE_2, SPEED_1),

    /*
     * potion series
     * 效用應該等同於精靈魔法劍 ....
     */
    // 30%, life +
    LIFE_POTION(POTION, "item_yellow_potion", COUNT, LIFE_100P),
    // 25%, attack x 2! super strong.
    ATTACK_POTION(POTION, "item_red_potion", COUNT, ATTACK_50),
    // 25%, defense = 999. no one can hurt you.
    DEFENSE_POTION(POTION, "item_blue_potion", COUNT, DEFENSE_50),
    // 15%, global minimum speed.
    SPEED_POTION(POTION, "item_green_potion", COUNT, SPEED_S8),
    // 5%, combo +1
    CYAN_POTION(POTION, "item_cyan_potion", COUNT, COMBO_1),

	/*
	 * skill
	 */
	
	// barbarian
	WAR_CRY(SKILL, "icon32_yellow_sword", Varier.NONE),
	
	// berserker
	FRENZY(SKILL, "icon32_attack", Varier.NONE),
	TWO_HAND_HOLD(SKILL, "item_great_sword", Varier.NONE),

    // test
    HIT(SKILL, "item_star", Varier.NONE),

	/*
	 * none
	 */
	NONE(SKILL, "", null);

    public @interface Type {
        int UPGRADE = 1;
        int POTION  = 2;
        int SKILL   = 3;
    }

	// attributes.
    @Type
    public final int type;
	public final String key;
	public final Varier cost;
	public final Varier[] effects;
	
	Action(@Type int type, String key, Varier cost, Varier... effects) {
	    this.type = type;
		this.key = key;
		this.cost = cost;
		this.effects = effects;
	}
	
}
