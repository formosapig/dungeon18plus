package com.qqhouse.dungeon18plus.core;

public enum Feat {
	NONE("", "", ""),
	EXPERIENCE("star", "experience", "experience_help"),
	QUICK_LEARNER("star", "quick_learner", "quick_learner_help"),
	APPRENTICE("key", "apprentice", "apprentice_help"),
	GEM_SEEKER("key", "gem_seeker", "gem_seeker_help"),
	RICH("coin", "rich", "rich_help"),
	LUCKY("key", "lucky", "lucky_help"),
	RAGE("damage", "rage", "rage_help"),
	BLOCK("damage", "block", "block_help"),
	SHOPPING("coin", "shopping", "shopping_help"),
	BARGAIN("coin", "bargain", "bargain_help"),
	UNLOCK("key", "unlock", "unlock_help"),
	DISARM_TRAP("key", "disarm_trap", "disarm_trap_help"),
	STEAL("key", "steal", "steal_help"),
	PURIFICATION("key", "purification", "purification_help"),
	EVASION("key", "evasion", "evasion_help"),
	LIFE_LEECH("damage", "life_leech", "life_leech_help"),
	DARK_PRESENCE("soul", "dark_presence", "dark_presence_help"),
	UNDEAD("soul", "undead", "undead_help"),
	YELLOW_MIRROR("yellow_mirror", "yellow_mirror", "mirror_help"),
	RED_MIRROR("red_mirror", "red_mirror", "mirror_help"),
	BLUE_MIRROR("blue_mirror", "blue_mirror", "mirror_help"),
	GREEN_MIRROR("green_mirror", "green_mirror", "mirror_help"),
	BURST_DOOR("damage", "burst_door", "burst_door_help"),
	FORGING("coin", "forging", "forging_help"),
	EQUIPMENT_STASH("star", "equipment_stash", "equipment_stash_help"),
	// fairy
	HOLY_ONE("win", "holy_one", "holy_one_help"),	// gain star from kill zako, boss...
	HEAL("life", "heal", "heal_help"),	// always recover life to maxLife...
	ENDLESS_PURSE("coin", "feat_endless_purse", "feat_endless_purse_help"),
	
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
