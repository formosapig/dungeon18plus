//package com.qqhouse.dungeon18plus.core;

//import com.qqhouse.dungeon18plus.struct.Ability;

//public enum Loot {

//    // NONE
//    NONE(            0x55D42055,   Loot.TYPE_NONE,     0,  0,  0,   0,  0, "", "", ""),

//    // STAR
//    STAR(            0x71F2B299,   Loot.TYPE_STAR,     0,  0,  0,   0,  0, "star", "star", "star_help"),

//    // KEY
//    KEY(             0x42A9564D,    Loot.TYPE_KEY,     0,  0,  0,   0,  0, "key", "key", "key_help"),

//    // COIN
//    COPPER_COIN(     0x2CD45554,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "copper_coin", "copper_coin", "coin_help"),
//    SILVER_COIN(     0x6B178FC4,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "silver_coin", "silver_coin", "coin_help"),
//    GOLDEN_COIN(     0x51EF4A9D,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "golden_coin", "golden_coin", "coin_help"),

//    // GEM
//    YELLOW_GEM(      0x9D314920,    Loot.TYPE_GEM,   100,  0,  0,   0,  0, "yellow_gem", "yellow_gem", "gem_help"),
//    RED_GEM(         0x9985f510,    Loot.TYPE_GEM,     0,  2,  0,   0,  0, "red_gem", "red_gem", "gem_help"),
//    BLUE_GEM(        0xE226C9C6,    Loot.TYPE_GEM,     0,  0,  2,   0,  0, "blue_gem", "blue_gem", "gem_help"),
//    GREEN_GEM(       0xC62689DF,    Loot.TYPE_GEM,     0,  0,  0,  -1,  0, "green_gem", "green_gem", "gem_help"),
//    WHITE_GEM(       0x08EE509C,    Loot.TYPE_GEM,   100,  2,  2,  -1,  0, "white_gem", "white_gem", "white_gem_help"),

//    // CRYSTAL
//    LIFE_CRYSTAL(    0x7C93D214,Loot.TYPE_CRYSTAL,   100,  0,  0,   0,  0, "yellow_crystal", "yellow_crystal", "crystal_help"),
//    ATTACK_CRYSTAL(  0x8D6A6D32,Loot.TYPE_CRYSTAL,     0,  2,  0,   0,  0, "red_crystal", "red_crystal", "crystal_help"),
//    DEFENSE_CRYSTAL( 0x3CC34697,Loot.TYPE_CRYSTAL,     0,  0,  2,   0,  0, "blue_crystal", "blue_crystal", "crystal_help"),
//    SPEED_CRYSTAL(   0xCC852142,Loot.TYPE_CRYSTAL,     0,  0,  0,  -1,  0, "green_crystal", "green_crystal", "crystal_help"),
//    POWER_CRYSTAL(   0x6D91DC4F,Loot.TYPE_CRYSTAL,   100,  2,  2,  -1,  0, "white_crystal", "white_crystal", "white_crystal_help"),


//    // POTION
//    YELLOW_POTION(   0x628CC786, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "yellow_potion", "yellow_potion", "potion_help"),
//    RED_POTION(      0x5F9D5113, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "red_potion", "red_potion", "potion_help"),
//    BLUE_POTION(     0x2C912503, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "blue_potion", "blue_potion", "potion_help"),
//    GREEN_POTION(    0x119914C1, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "green_potion", "green_potion", "potion_help"),
//    PURPLE_POTION(   0x551CB91B, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "purple_potion", "purple_potion", "purple_potion_help"),
//    CYAN_POTION(     0x522D42A8, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "cyan_potion", "cyan_potion", "potion_help"),
//    BLACK_POTION(    0x95DFDE69, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "black_potion", "black_potion", "black_potion_help"),
//    WHITE_POTION(    0x7BD2A32A, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "white_potion", "white_potion", "white_potion_help"),

//    // DAGGER
//    WOODEN_DAGGER(   0x22DAFA3E, Loot.TYPE_DAGGER,    15,  1,  0,  -1, 30, UltimateSkill.WOODEN_DAGGER, "wooden_dagger", "wooden_dagger", "wooden_help"),
//    IRON_DAGGER(     0x05BB4ADB, Loot.TYPE_DAGGER,     0,  4,  0,  -1, 40, UltimateSkill.IRON_DAGGER, "iron_dagger", "iron_dagger", ""),
//    IRON_DAGGER_1(   0xEB5020CE, Loot.TYPE_DAGGER,     0,  6,  0,  -2, 50, UltimateSkill.IRON_DAGGER_1, "iron_dagger", "iron_dagger_1", "iron_1_help", Enchant.REFINED),
//    WIND_DAGGER(     0xF3FE45BB, Loot.TYPE_DAGGER,     0,  2,  0,  -3, 50, UltimateSkill.WIND_DAGGER, "wind_dagger", "wind_dagger", ""),
//    MITHRIL_DAGGER(  0x334C939B, Loot.TYPE_DAGGER,    50,  3,  0,  -2, 50, UltimateSkill.MITHRIL_DAGGER, "mithril_dagger", "mithril_dagger", "mithril_help"),
//    SHADOW_DAGGER(   0x7BDC7012, Loot.TYPE_DAGGER,   -50, 16,  0,  -2, 75, UltimateSkill.SHADOW_DAGGER, "shadow_dagger", "shadow_dagger", "shadow_dagger_help", Enchant.CURSED),
//    YGGDRASIL_DAGGER(0x18837AC0, Loot.TYPE_DAGGER | Loot.TYPE_EXTRA,
//			                                         150, 10,  0,  -5,110, UltimateSkill.YGGDRASIL_DAGGER, "yggdrasil_dagger", "yggdrasil_dagger", "yggdrasil_help"),

//    // SWORD
//    WOODEN_SWORD(    0x78311C02,  Loot.TYPE_SWORD,    15,  3,  0,   0, 30, UltimateSkill.WOODEN_SWORD, "wooden_sword", "wooden_sword", "wooden_help"),
//    IRON_SWORD(      0xBBDDD8D6,  Loot.TYPE_SWORD,     0,  6,  0,   0, 40, UltimateSkill.IRON_SWORD, "iron_sword", "iron_sword", ""),
//    IRON_SWORD_1(    0xBA875A26,  Loot.TYPE_SWORD,     0, 10,  0,   0, 50, UltimateSkill.IRON_SWORD_1, "iron_sword", "iron_sword_1", "iron_1_help", Enchant.REFINED),
//    GREAT_SWORD(     0xA58D8E6E,  Loot.TYPE_SWORD,     0,  9,  0,   3, 50, UltimateSkill.GREAT_SWORD, "great_sword", "great_sword", ""),
//    MITHRIL_SWORD(   0xE8B343FA,  Loot.TYPE_SWORD,    50,  7,  0,   0, 50, UltimateSkill.MITHRIL_SWORD, "mithril_sword", "mithril_sword", "mithril_help"),
//    FIRE_SWORD(      0xCD5765FC,  Loot.TYPE_SWORD,     0, 20, -5,   0, 75, UltimateSkill.FIRE_SWORD, "fire_sword", "fire_sword", "fire_sword_help"),
//    ICE_SWORD(       0x15FE199F,  Loot.TYPE_SWORD,     0, 10,  8,   1, 75, UltimateSkill.ICE_SWORD, "ice_sword", "ice_sword", "ice_sword_help"),
//    THUNDER_SWORD(   0x129359BD,  Loot.TYPE_SWORD,     0,  8,  0,  -6, 75, UltimateSkill.THUNDER_SWORD, "thunder_sword", "thunder_sword", "thunder_sword_help"),
//    HOLY_SWORD(      0xF6DF6BDE,  Loot.TYPE_SWORD,    50, 18,  0,   0, 90, UltimateSkill.HOLY_SWORD, "holy_sword", "holy_sword", "holy_help", Enchant.BLESSED),
//    SKULL_SWORD(     0xC2730049,  Loot.TYPE_SWORD,   -99, 22,  0,   0, 90, UltimateSkill.SKULL_SWORD, "skull_sword", "skull_sword", "skull_sword_help", Enchant.CURSED),
//    YGGDRASIL_SWORD( 0x4E6DBC58,  Loot.TYPE_SWORD | Loot.TYPE_EXTRA,
//			                                         150, 20,  0,   0,110, UltimateSkill.YGGDRASIL_SWORD, "yggdrasil_sword", "yggdrasil_sword", "yggdrasil_help"),

//    // STAFF
//    WOODEN_STAFF(    0x4B7E45E5,  Loot.TYPE_STAFF,    15,  5,  0,   2, 30, UltimateSkill.WOODEN_STAFF, "wooden_staff", "wooden_staff", "wooden_help"),
//    MITHRIL_STAFF(   0x901BB6AA,  Loot.TYPE_STAFF,    50, 11,  0,   3, 50, UltimateSkill.MITHRIL_STAFF, "mithril_staff", "mithril_staff", "mithril_help"),
//    YGGDRASIL_STAFF( 0x75C22964,  Loot.TYPE_STAFF | Loot.TYPE_EXTRA,
//			                                         150, 25,  0,   5,110, UltimateSkill.YGGDRASIL_STAFF, "yggdrasil_staff", "yggdrasil_staff", "yggdrasil_help"),

//    // SHIELD
//    WOODEN_SHIELD(   0xABF3BE7A, Loot.TYPE_SHIELD,    15,  0,  3,   0, 30, UltimateSkill.WOODEN_SHIELD, "wooden_shield", "wooden_shield", "wooden_help"),
//    IRON_SHIELD(     0xEF197405, Loot.TYPE_SHIELD,     0,  0,  6,   0, 40, UltimateSkill.IRON_SHIELD, "iron_shield", "iron_shield", ""),
//    IRON_SHIELD_1(   0x0A26C71B, Loot.TYPE_SHIELD,     0,  0, 10,   0, 50, UltimateSkill.IRON_SHIELD_1, "iron_shield", "iron_shield_1", "iron_1_help", Enchant.REFINED),
//    TOWER_SHIELD(    0xD8ABCEFD, Loot.TYPE_SHIELD,     0,  0,  9,   3, 50, UltimateSkill.TOWER_SHIELD, "tower_shield", "tower_shield", ""),
//    MITHRIL_SHIELD(  0x334D3822, Loot.TYPE_SHIELD,    50,  0,  7,   0, 50, UltimateSkill.MITHRIL_SHIELD, "mithril_shield", "mithril_shield", "mithril_help"),
//    BLACK_SHIELD(    0x18263078, Loot.TYPE_SHIELD,     0,  0, 20,   6, 75, UltimateSkill.BLACK_SHIELD, "black_shield", "black_shield", "black_shield_help"),
//    WHITE_SHIELD(    0x5C8ECAE9, Loot.TYPE_SHIELD,    20,  0, 14,  -1, 75, UltimateSkill.WHITE_SHIELD, "white_shield", "white_shield", "white_shield_help"),
//    HOLY_SHIELD(     0x4167C340, Loot.TYPE_SHIELD,    50,  0, 18,   0, 90, UltimateSkill.HOLY_SHIELD, "holy_shield", "holy_shield", "holy_help", Enchant.BLESSED),
//    SKULL_SHIELD(    0x3DBC6F30, Loot.TYPE_SHIELD,   -99,  0, 22,   0, 90, UltimateSkill.SKULL_SHIELD, "skull_shield", "skull_shield", "skull_shield_help", Enchant.CURSED),
//    YGGDRASIL_SHIELD(0x353FD420, Loot.TYPE_SHIELD | Loot.TYPE_EXTRA,
//			                                         150,  0, 20,   0,110, UltimateSkill.YGGDRASIL_SHIELD, "yggdrasil_shield", "yggdrasil_shield", "yggdrasil_help"),

//    DEVELOPER_BUCKLER(0x3E6546E0,Loot.TYPE_SHIELD | Loot.TYPE_EXTRA,
//                                                       0,  0,  0,   0,  1, "buckler", "developer_buckler", "developer_buckler_help"),

//    // RING
//    WOODEN_RING(     0x0E8A8E97,   Loot.TYPE_RING,    15,  0,  1,  -2, 30, UltimateSkill.WOODEN_RING, "wooden_ring", "wooden_ring", "wooden_help"),
//    IRON_RING(       0xF249BA82,   Loot.TYPE_RING,     0,  3,  3,   0, 40, UltimateSkill.IRON_RING, "iron_ring", "iron_ring", ""),
//    IRON_RING_1(     0x40E46204,   Loot.TYPE_RING,     0,  5,  5,   0, 50, UltimateSkill.IRON_RING_1, "iron_ring", "iron_ring_1", "iron_1_help", Enchant.REFINED),
//    YELLOW_RING(     0x359E6775,   Loot.TYPE_RING,   250,  0,  0,   0, 60, UltimateSkill.YELLOW_RING, "yellow_ring", "yellow_ring", ""),
//    RED_RING(        0x1FC9667C,   Loot.TYPE_RING,     0, 10,  0,   0, 60, UltimateSkill.RED_RING, "red_ring", "red_ring", ""),
//    BLUE_RING(       0x63A51AB8,   Loot.TYPE_RING,     0,  0, 10,   0, 60, UltimateSkill.BLUE_RING, "blue_ring", "blue_ring", ""),
//    GREEN_RING(      0x5F9BD7D9,   Loot.TYPE_RING,     0,  0,  0,  -5, 60, UltimateSkill.GREEN_RING, "green_ring", "green_ring", ""),
//    BLACK_RING(      0xA37D6B01,   Loot.TYPE_RING,   -40,  4,  4,  -2, 60, UltimateSkill.BLACK_RING, "black_ring", "black_ring", "black_ring_help", Enchant.CURSED),
//    WHITE_RING(      0x8868001E,   Loot.TYPE_RING,     0,  4,  4,  -2, 60, UltimateSkill.WHITE_RING, "white_ring", "white_ring", ""),
//    RING_OF_GODDESS( 0x5839ADBB,   Loot.TYPE_RING,    50,  8,  8,  -1, 90, UltimateSkill.RING_OF_GODDESS, "ring_of_goddess", "ring_of_goddess", "holy_help", Enchant.BLESSED),
//    SKULL_RING(      0x3D12A612,   Loot.TYPE_RING,   -99, 10, 10,  -2, 90, UltimateSkill.SKULL_RING, "skull_ring", "skull_ring", "skull_ring_help", Enchant.CURSED),
//    YGGDRASIL_RING(  0x7137FA06,   Loot.TYPE_RING | Loot.TYPE_EXTRA,
//			                                         150,  5,  5,  -5,110, UltimateSkill.YGGDRASIL_RING, "yggdrasil_ring", "yggdrasil_ring", "yggdrasil_help"),
//    RING_OF_LIFE(    0x39906A7C,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_LIFE, "ring_of_life", "ring_of_life", "ring_of_life_help"),
//    RING_OF_ATTACK(  0x7C583139,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_ATTACK, "ring_of_attack", "ring_of_attack", "ring_of_attack_help"),
//    RING_OF_DEFENSE( 0x66E11E0F,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_DEFENSE, "ring_of_defense", "ring_of_defense", "ring_of_defense_help"),
//    RING_OF_SPEED(   0xAA187161,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_SPEED, "ring_of_speed", "ring_of_speed", "ring_of_speed_help"),

//    // BOOTS
//    IRON_BOOTS(      0x8EC27250,  Loot.TYPE_BOOTS,     0,  0,  0,  -3, 40, UltimateSkill.IRON_BOOTS, "iron_boots", "iron_boots", ""),
//    IRON_BOOTS_1(    0x1A5D6F5C,  Loot.TYPE_BOOTS,     0,  0,  0,  -5, 50, UltimateSkill.IRON_BOOTS_1, "iron_boots", "iron_boots_1", "iron_1_help", Enchant.REFINED),
//    MITHRIL_BOOTS(   0xD67E50EF,  Loot.TYPE_BOOTS,    50,  1,  1,  -3, 50, UltimateSkill.MITHRIL_BOOTS, "mithril_boots", "mithril_boots", "mithril_help"),
//    WIND_BOOTS(      0xD29E268B,  Loot.TYPE_BOOTS,     0,  0,  5,  -8, 75, UltimateSkill.WIND_BOOTS, "wind_boots", "wind_boots", "wind_boots_help"),

//    // elemental magic sword.
//    /*
//     * 效用, 當剣聖裝備後, 取得異於凡人的力量...
//     * 黃色 : 生命, life +1800
//     * 紅色 : 攻擊力, attack = 380
//     * 藍色 : 防禦力, defense +180
//     * 綠色 : 速度, speed = 6 (Global Speed Minimum)
//     * 紫色 : 魔力, ????
//     * 青色 : 時間, combo +1
//     * 白色 : 神聖, ???
//     * 黑色 : 黑暗, ???
//     *
//     * (red)    fire : salamander / salamando
//     * (blue)   water : undine
//     * (green)  air : sylphid / sylph
//     * (yellow) earth : gnome
//     * (purple) magic : mana
//     * (cyan)   time : tick
//     * (white)  light : lumina
//     * (black)  dark : shade
//     *
//     */
//    SALAMANDO(       0xAC88FACD,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    UNDINE(          0x08A67EAD,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    SYLPH(           0x1BA0ADF0,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    GNOME(           0xD9400A39,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    MANA(            0x010A1FEC,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    CLOCK(           0xA5410FCE,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    LUMINA(          0x9028A1FE,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),
//    SHADE(           0x414A3491,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, "", "", ""),

//    // end
//    END(             0x169819E5,   Loot.TYPE_NONE,     0,  0,  0,   0,  0, "", "", "");

//    // type define.
//    private static final int TYPE_NONE		= 0<<0;
//    private static final int TYPE_STAR		= 1<<0;
//    private static final int TYPE_KEY		= 1<<1;
//    private static final int TYPE_COIN		= 1<<2;
//    private static final int TYPE_GEM		= 1<<3;
//    private static final int TYPE_POTION    = 1<<4;
//    private static final int TYPE_DAGGER    = 1<<5;
//    private static final int TYPE_SWORD		= 1<<6;
//    private static final int TYPE_STAFF		= 1<<7;
//    private static final int TYPE_SHIELD    = 1<<8;
//    private static final int TYPE_RING		= 1<<9;
//    private static final int TYPE_BOOTS		= 1<<10;
//    private static final int TYPE_EXTRA		= 1<<11;    // extra item.
//    private static final int TYPE_EVENT		= 1<<12;    // event item.
//    private static final int TYPE_CRYSTAL   = 1<<13;
//    private static final int TYPE_EQUIPMENT = TYPE_DAGGER | TYPE_SWORD | TYPE_STAFF | TYPE_SHIELD | TYPE_RING | TYPE_BOOTS;
//    private static final int TYPE_FORGING   = TYPE_DAGGER | TYPE_SWORD | TYPE_SHIELD;

//    // without action
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost,
//         String icon, String name, String help) {
//		this.icon = icon;
//		this.name = name;
//		this.help = help;
//		this.enchant = Enchant.NONE;
//		this.code = code;
//		this.type = type;
//		this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//		this.cost = cost;
//		this.action = UltimateSkill.NONE;
//    }

//    // with action
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost,
//         UltimateSkill action, String icon, String name, String help) {
//		this.icon = icon;
//		this.name = name;
//		this.help = help;
//		this.enchant = Enchant.NONE;
//		this.code = code;
//		this.type = type;
//		this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//		this.cost = cost;
//		this.action = action;
//    }

//    // some equipment has state like normal / refined / cursed / blessed....
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost,
//         UltimateSkill action, String icon, String name, String help, Enchant enchant) {
//        this.icon = icon;
//        this.name = name;
//        this.help = help;
//        this.enchant = enchant;
//        this.code = code;
//        this.type = type;
//        this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//        this.cost = cost;
//        this.action = action;
//    }


//    public final String icon;
//    public final String name;
//    public final String help;
//    public final Enchant enchant;
//    public final int code;
//    private final int type;
//    public final Ability upgrade;
//    public final int cost;
//    public final UltimateSkill action;

//    public static Loot find(int code) {
//		for (Loot l : Loot.values()) {
//			if (l.code == code)
//				return l;
//		}
//		throw new RuntimeException("invalid code : " + code);
//    }

//    public boolean isPotion() {
//		return (type & Loot.TYPE_POTION) != 0;
//    }

//    public boolean isEquipment() {
//		return (type & Loot.TYPE_EQUIPMENT) != 0;
//    }

//    public boolean canForging() {
//		return (type & Loot.TYPE_FORGING) != 0;
//    }

//    public boolean isPureSpeed() {
//		return this.equals(Loot.IRON_BOOTS) || this.equals(Loot.GREEN_RING);
//    }

//    public boolean isNotPremium() {
//		return (type & Loot.TYPE_EXTRA) != 0;
//    }

//    public boolean isPowerRing() {
//		return this == RING_OF_LIFE
//			|| this == RING_OF_ATTACK
//			|| this == RING_OF_DEFENSE
//			|| this == RING_OF_SPEED;
//    }

//}