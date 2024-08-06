package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Variety;

import static com.qqhouse.dungeon18plus.core.Action.Type.POTION;
import static com.qqhouse.dungeon18plus.core.Action.Type.SKILL;
import static com.qqhouse.dungeon18plus.core.Action.Type.UPGRADE;
import static com.qqhouse.dungeon18plus.struct.Variety.*;

public enum Action {
	
	/*
	 * Upgrades
	 */
	
	// life series
	POOR_LIFE_UP(UPGRADE, "icon/life", STAR_6, LIFE_15),
	LESS_LIFE_UP(UPGRADE, "icon/life", STAR_8, LIFE_25),
	LIFE_UP(UPGRADE, "icon/life", STAR_10, LIFE_50),
	MORE_LIFE_UP(UPGRADE, "icon/life", STAR_8, LIFE_50),
	
	// attack series
	LESS_ATTACK_UP(UPGRADE, "icon/attack", STAR_12, ATTACK_4),
	ATTACK_UP(UPGRADE, "icon/attack", STAR_10, ATTACK_4),
	MORE_ATTACK_UP(UPGRADE, "icon/attack", STAR_10, ATTACK_5),
	
	// defense series
	LESS_DEFENSE_UP(UPGRADE, "icon/defense", STAR_12, DEFENSE_4),
	DEFENSE_UP(UPGRADE, "icon/defense", STAR_10, DEFENSE_4),
	MORE_DEFENSE_UP(UPGRADE, "icon/defense", STAR_10, DEFENSE_5),
	
	// speed series
	LESS_SPEED_UP(UPGRADE, "icon/speed", STAR_10, SPEED_1),
	SPEED_UP(UPGRADE, "icon/speed", STAR_10, SPEED_2),
	MORE_SPEED_UP(UPGRADE, "icon/speed", STAR_8, SPEED_2),
	
	// barbarian
	ANCESTOR_COURAGE(UPGRADE, "icon/yellow_sword", STAR_6, LIFE_15, ATTACK_2),
	ANCESTOR_STRONG(UPGRADE, "icon/yellow_shield", STAR_6, LIFE_15, DEFENSE_2),
	ANCESTOR_WISDOM(UPGRADE, "icon/yellow_shoe", STAR_6, LIFE_15, SPEED_1),
	
	// berserker
	PERSIST(UPGRADE, "icon/blue_heart", STAR_6, LIFE_20, DEFENSE_1),
	BERSERK(UPGRADE, "icon/attack", STAR_18, ATTACK_10, DEFENSE_N1),
	
	// dragoon
	CHARGE(UPGRADE, "icon/green_sword", STAR_15, ATTACK_3, SPEED_1),
	HARD_DEFENSE(UPGRADE, "icon/defense", STAR_15, DEFENSE_10, SPEED_N1),

	// thief
	RUNAWAY(UPGRADE, "icon/speed", STAR_13, ATTACK_N1, SPEED_5),
	
	// assassin
	BLOOD_ATTACK(UPGRADE, "icon/black_sword", STAR_11, ATTACK_5),
	BLOOD_DEFENSE(UPGRADE, "icon/black_shield", STAR_11, DEFENSE_5),
	
	// crusader
	SUPERIOR_ARMOR(UPGRADE, "icon/purple_armor", STAR_12, LIFE_15, ATTACK_2, DEFENSE_2),
	
	// fairy
	HOLY_ATTACK(UPGRADE, "icon/white_sword", STAR_9, ATTACK_6),
	HOLY_DEFENSE(UPGRADE, "icon/white_shield", STAR_9, DEFENSE_6),
	HOLY_SPEED(UPGRADE, "icon/white_shoe", STAR_9, SPEED_2),
	
	// skeleton king
	DARK_FORCE(UPGRADE, "icon/black_sword", SOUL_99, ATTACK_15),
	DARK_GUARD(UPGRADE, "icon/black_shield", SOUL_99, DEFENSE_15),
	DARK_SPEED(UPGRADE, "icon/black_shoe", SOUL_99, SPEED_6),
	
	// mana power
	//MANA_POWER("purple_armor", Operation.COST_STAR_5, Operation2.ADD_MANA_POWER, Operation2.ADD_MANA_10);
	
	// magic defense
	MAGICIAN_DEFENSE(UPGRADE, "icon/purple_armor", STAR_12, DEFENSE_2, SPEED_1),

    /*
     * potion series
     * 效用應該等同於精靈魔法劍 ....
     */
    // 30%, life +
    LIFE_POTION(POTION, "item/yellow_potion", COUNT, LIFE_100P),
    // 25%, attack x 2! super strong.
    ATTACK_POTION(POTION, "item/red_potion", COUNT, ATTACK_50),
    // 25%, defense = 999. no one can hurt you.
    DEFENSE_POTION(POTION, "item/blue_potion", COUNT, DEFENSE_50),
    // 15%, global minimum speed.
    SPEED_POTION(POTION, "item/green_potion", COUNT, SPEED_S8),
    // 5%, combo +1
    CYAN_POTION(POTION, "item/cyan_potion", COUNT, COMBO_1),

	/*
	 * skill
	 */
	
	// barbarian
	WAR_CRY(SKILL, "icon/yellow_sword", Variety.NONE),
	
	// berserker
	FRENZY(SKILL, "icon/attack", Variety.NONE),
	TWO_HAND_HOLD(SKILL, "item/great_sword", Variety.NONE),

	// fairy
	DOPPELGANGER(SKILL, "icon/black_shoe", KEY_9),

    // test
    HIT(SKILL, "item/star", Variety.NONE),

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
	public final Variety cost;
	public final Variety[] effects;
	
	Action(@Type int type, String key, Variety cost, Variety... effects) {
        this.type = type;
        this.key = key;
		this.cost = cost;
		this.effects = effects;
	}
	
}
