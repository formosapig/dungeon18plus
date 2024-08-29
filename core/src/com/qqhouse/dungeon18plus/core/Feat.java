package com.qqhouse.dungeon18plus.core;

public enum Feat {
	NONE("", "", ""),
	EXPERIENCE("item/star", "experience", "experience_help"),
	QUICK_LEARNER("item/star", "quick_learner", "quick_learner_help"),
	APPRENTICE("item/key", "apprentice", "apprentice_help"),
	CRYSTAL_HUNTER("item/key", "crystal_hunter", "crystal_hunter_help"),
	RICH("item/copper_coin", "rich", "rich_help"),
	LUCKY("item/key", "lucky", "lucky_help"),
	RAGE("icon/damage", "rage", "rage_help"),
	BLOCK("icon/damage", "block", "block_help"),
	SHOPPING("item/copper_coin", "shopping", "shopping_help"),
	BARGAIN("item/copper_coin", "bargain", "bargain_help"),
	UNLOCK("item/key", "unlock", "unlock_help"),
	DISARM_TRAP("item/key", "disarm_trap", "disarm_trap_help"),
	STEAL("item/key", "steal", "steal_help"),
	PURIFICATION("item/key", "purification", "purification_help"),
	EVASION("item/key", "evasion", "evasion_help"),
	LIFE_LEECH("icon/damage", "life_leech", "life_leech_help"),
	DARK_PRESENCE("item/soul", "dark_presence", "dark_presence_help"),
	UNDEAD("item/soul", "undead", "undead_help"),
	YELLOW_MIRROR("item/yellow_mirror", "yellow_mirror", "mirror_help"),
	RED_MIRROR("item/red_mirror", "red_mirror", "mirror_help"),
	BLUE_MIRROR("item/blue_mirror", "blue_mirror", "mirror_help"),
	GREEN_MIRROR("item/green_mirror", "green_mirror", "mirror_help"),
	BURST_DOOR("icon/damage", "burst_door", "burst_door_help"),
	FORGING("item/copper_coin", "forging", "forging_help"),
	EQUIPMENT_STASH("item/star", "equipment_stash", "equipment_stash_help"),
	// fairy
	HOLY_ONE("icon/win", "holy_one", "holy_one_help"),	// gain star from kill zako, boss...
	HEAL("icon/life", "heal", "heal_help"),	// always recover life to maxLife...
	ENDLESS_PURSE("item/copper_coin", "feat_endless_purse", "feat_endless_purse_help"),
	
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
