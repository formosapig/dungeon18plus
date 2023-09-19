package com.qqhouse.dungeon18plus.core;

public enum Feat {
	NONE("", "", ""),
	EXPERIENCE("item_star", "experience", "experience_help"),
	QUICK_LEARNER("item_star", "quick_learner", "quick_learner_help"),
	APPRENTICE("item_key", "apprentice", "apprentice_help"),
	GEM_SEEKER("item_key", "gem_seeker", "gem_seeker_help"),
	RICH("item_golden_coin", "rich", "rich_help"),
	LUCKY("item_key", "lucky", "lucky_help"),
	RAGE("icon32_damage", "rage", "rage_help"),
	BLOCK("icon32_damage", "block", "block_help"),
	SHOPPING("item_golden_coin", "shopping", "shopping_help"),
	BARGAIN("item_golden_coin", "bargain", "bargain_help"),
	UNLOCK("item_key", "unlock", "unlock_help"),
	DISARM_TRAP("item_key", "disarm_trap", "disarm_trap_help"),
	STEAL("item_key", "steal", "steal_help"),
	PURIFICATION("item_key", "purification", "purification_help"),
	EVASION("item_key", "evasion", "evasion_help"),
	LIFE_LEECH("icon32_damage", "life_leech", "life_leech_help"),
	DARK_PRESENCE("icon32_soul", "dark_presence", "dark_presence_help"),
	UNDEAD("icon32_soul", "undead", "undead_help"),
	YELLOW_MIRROR("item_yellow_mirror", "yellow_mirror", "mirror_help"),
	RED_MIRROR("item_red_mirror", "red_mirror", "mirror_help"),
	BLUE_MIRROR("item_blue_mirror", "blue_mirror", "mirror_help"),
	GREEN_MIRROR("item_green_mirror", "green_mirror", "mirror_help"),
	BURST_DOOR("icon32_damage", "burst_door", "burst_door_help"),
	FORGING("item_golden_coin", "forging", "forging_help"),
	EQUIPMENT_STASH("item_star", "equipment_stash", "equipment_stash_help"),
	// fairy
	HOLY_ONE("win", "holy_one", "holy_one_help"),	// gain star from kill zako, boss...
	HEAL("icon32_life", "heal", "heal_help"),	// always recover life to maxLife...
	ENDLESS_PURSE("item_copper_coin", "feat_endless_purse", "feat_endless_purse_help"),
	
	// colosseum feat
	COLOSSEUM(Feat.APPRENTICE, Feat.RAGE, Feat.SHOPPING, Feat.BARGAIN, Feat.PURIFICATION, Feat.FORGING,
			Feat.YELLOW_MIRROR, Feat.RED_MIRROR, Feat.BLUE_MIRROR, Feat.GREEN_MIRROR);
	
	// fields.
	public final String icon;
	public final String name;
	public final String help;
	
	private long mBitValue;
	
	public long or(long bitValue) {
		return bitValue | mBitValue;
	}
	
	public long and(long bitValue) {
		return bitValue & mBitValue;
	}
	
	public boolean in(long bitValue) {
		return (bitValue & mBitValue) != 0L;
	}
	
	Feat(String icon, String name, String help) {
		this.icon = icon;
		this.name = name;
		this.help = help;
		
		int shift = this.ordinal() - 1;
		if (0 > shift)
			mBitValue = 0;
		else
			mBitValue = 1 << shift;
	}
	
	Feat(Feat... feats) {
		this.icon = "";
		this.name = "";
		this.help = "";
		for (Feat feat : feats)
			this.mBitValue = feat.or(this.mBitValue);
	}
	
	public static long combine(Feat... feats) {
		long result = 0;
		for (Feat feat : feats)
			result = feat.or(result);
		return result;
	}
	
}
