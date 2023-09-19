package com.qqhouse.dungeon18plus.core;

public enum Help {
	
	// global
	HELP_OF_HELP(                0x9D50D49F, "help_of_help"),
	SWIPE_HERO(                  0xB2A59E40, "help_swipe"),
	
	// dungeon
	DUNGEON_TUTOR(               0x1E740514, "blockee_skeleton_king", "help_dungeon_tutor"),
	RESOLVE_EVENT(               0xF83CF956, "blockee_slime", "help_resolve_event"),
	EVENT_INFO(                  0x43D93E36, "blockee_merchant", "help_event_info"),
	CLOSE_EVENT_INFO(            0xF02DF11F, "blockee_merchant", "help_close_event_info"),
	OPEN_HERO_PROFILE(           0x2A29F1C6, "blockee_novice", "help_open_hero_profile"),
	CLOSE_HERO_PROFILE(          0xDB9CC271, "blockee_novice", "help_close_hero_profile"),
	USE_SHORTCUT(                0xB3D67336, "icon32_attack", "help_use_shortcut"),
	
	// colosseum
	COLOSSEUM_TUTOR(             0x2BEB3F9A, "blockee_sword_master", "help_colosseum_tutor"),
	FOUR_KNIGHT(                 0x668361D6, "blockee_earth_knight", "help_four_knight"),
	
	// select equipment
	MASTER_TUTOR(                0xA9D98FA8, "blockee_sword_master", "help_master_tutor"),
	UNIQUE_SKILL_DAMAGE(         0xC30C1A42, "icon32_damage", "help_unique_skill_damage"),
	UNIQUE_SKILL_ALL(            0xFD8CC0CA, "help_unique_skill_all"),
	UNIQUE_SKILL_LIFE(           0xC8D9E21A, "icon32_life", "help_unique_skill_life"),
	UNIQUE_SKILL_ATTACK(         0xAE9DAF74, "icon32_attack", "help_unique_skill_attack"),
	UNIQUE_SKILL_DEFENSE(        0xF1481B91, "icon32_defense", "help_unique_skill_defense"),
	UNIQUE_SKILL_SPEED(          0xDB26C890, "icon32_speed", "help_unique_skill_speed"),
	UNIQUE_SKILL_COOL_DOWN(      0x365517EB, "icon32_time", "help_unique_skill_cool_down"),
	UNIQUE_SKILL_RESURRECTION(   0x1B2E1041, "icon32_resurrection", "help_unique_skill_resurrection"),
	UNIQUE_SKILL_GUARD(          0x5CD62BA6, "icon32_guard", "help_unique_skill_guard"),
	CONFIRM_EQUIPMENT_SELECTION( 0xE50FF904, "help_confirm_equipment_selection"),
	
	// barrack
	BARRACK_TUTOR(               0xB1244C36, "help_barrack_tutor"),
	JOIN_VETERAN(                0xAED358BF, "help_join_veteran"),
	QUIT_VETERAN(                0x7A9BC37D, "help_quit_veteran"),
	
	// legion trainer
	LEGION_TRAINER(              0x1124AE87, "help_legion_trainer"),
	ADD_LEGION(                  0x6B9E12D3, "help_add_legion"),
	RETURN_BARRACK(              0x46F8880D, "help_return_barrack"),
	
	// soul master
	EXTEND_SOUL(                 0xE88FDFD8, "item_golden_coin", "help_extend_soul"),
	REMOVE_SOUL(                 0xCF0F8ACF, "icon32_collapse", "help_remove_soul"),
	
	// wilderness
	WILDERNESS_TUTOR(            0x7A1D46D7, "blockee_black_slime", "help_wilderness_tutor"),
	USE_UNIQUE_SKILL(            0xC687061B, "item_fire_sword", "help_use_unique_skill"),
	END_CAMPAIGN(                0x97149155, "blockee_crusader", "help_end_campaign"),
	
	END(0x8A8Dc92D, "");
	
	
	public final int code;
	public final String icon;
	public final String desc;
	
	Help(int code, String desc) {
		this.code = code;
		this.icon = "";
		this.desc = desc;
	}
	
	Help(int code, String icon, String desc) {
		this.code = code;
		this.icon = icon;
		this.desc = desc;
	}
	
	// find by code
	public static Help find(int code) {
		for (Help h : Help.values()) {
			if (h.code == code)
				return h;
		}
		throw new RuntimeException("invalid code : " + code);
	}
}
