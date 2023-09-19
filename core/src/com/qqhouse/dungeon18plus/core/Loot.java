//package com.qqhouse.dungeon18plus.core;
//
//import com.qqhouse.dungeon18plus.R;
//import com.qqhouse.dungeon18plus.struct.Ability;
//
//public enum Loot {
//
//    // NONE
//    NONE(            0x55D42055,   Loot.TYPE_NONE,     0,  0,  0,   0,  0, 0, 0, 0),
//
//    // STAR
//    STAR(            0x71F2B299,   Loot.TYPE_STAR,     0,  0,  0,   0,  0, "item_star, R.string.star, R.string.star_help),
//
//    // KEY
//    KEY(             0x42A9564D,    Loot.TYPE_KEY,     0,  0,  0,   0,  0, "item_key, R.string.key, R.string.key_help),
//
//    // COIN
//    COPPER_COIN(     0x2CD45554,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "item_copper_coin, R.string.copper_coin, R.string.coin_help),
//    SILVER_COIN(     0x6B178FC4,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "item_silver_coin, R.string.silver_coin, R.string.coin_help),
//    GOLDEN_COIN(     0x51EF4A9D,   Loot.TYPE_COIN,     0,  0,  0,   0,  0, "item_golden_coin, R.string.golden_coin, R.string.coin_help),
//
//    // GEM
//    YELLOW_GEM(      0x9D314920,    Loot.TYPE_GEM,   100,  0,  0,   0,  0, "item_yellow_gem, R.string.yellow_gem, R.string.gem_help),
//    RED_GEM(         0x9985f510,    Loot.TYPE_GEM,     0,  2,  0,   0,  0, "item_red_gem, R.string.red_gem, R.string.gem_help),
//    BLUE_GEM(        0xE226C9C6,    Loot.TYPE_GEM,     0,  0,  2,   0,  0, "item_blue_gem, R.string.blue_gem, R.string.gem_help),
//    GREEN_GEM(       0xC62689DF,    Loot.TYPE_GEM,     0,  0,  0,  -1,  0, "item_green_gem, R.string.green_gem, R.string.gem_help),
//    WHITE_GEM(       0x08EE509C,    Loot.TYPE_GEM,   100,  2,  2,  -1,  0, "item_white_gem, R.string.white_gem, R.string.white_gem_help),
//
//    // CRYSTAL
//    LIFE_CRYSTAL(  0x7C93D214,Loot.TYPE_CRYSTAL,   100,  0,  0,   0,  0, "item_yellow_crystal, R.string.item_yellow_crystal, R.string.item_crystal_help),
//    ATTACK_CRYSTAL(     0x8D6A6D32,Loot.TYPE_CRYSTAL,     0,  2,  0,   0,  0, "item_red_crystal, R.string.item_red_crystal, R.string.item_crystal_help),
//    DEFENSE_CRYSTAL(    0x3CC34697,Loot.TYPE_CRYSTAL,     0,  0,  2,   0,  0, "item_blue_crystal, R.string.item_blue_crystal, R.string.item_crystal_help),
//    SPEED_CRYSTAL(   0xCC852142,Loot.TYPE_CRYSTAL,     0,  0,  0,  -1,  0, "item_green_crystal, R.string.item_green_crystal, R.string.item_crystal_help),
//    POWER_CRYSTAL(   0x6D91DC4F,Loot.TYPE_CRYSTAL,   100,  2,  2,  -1,  0, "item_white_crystal, R.string.item_white_crystal, R.string.item_white_crystal_help),
//
//
//    // POTION
//    YELLOW_POTION(   0x628CC786, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_yellow_potion, R.string.item_yellow_potion, R.string.item_potion_help),
//    RED_POTION(      0x5F9D5113, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_red_potion, R.string.item_red_potion, R.string.item_potion_help),
//    BLUE_POTION(     0x2C912503, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_blue_potion, R.string.item_blue_potion, R.string.item_potion_help),
//    GREEN_POTION(    0x119914C1, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_green_potion, R.string.item_green_potion, R.string.item_potion_help),
////    PURPLE_POTION(   0x551CB91B, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_purple_potion, R.string.purple_potion, R.string.purple_potion_help),
//    CYAN_POTION(     0x522D42A8, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_cyan_potion, R.string.item_cyan_potion, R.string.item_potion_help),
////    BLACK_POTION(    0x95DFDE69, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_black_potion, R.string.black_potion, R.string.black_potion_help),
////    WHITE_POTION(    0x7BD2A32A, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_white_potion, R.string.white_potion, R.string.white_potion_help),
//
//    // DAGGER
//    WOODEN_DAGGER(   0x22DAFA3E, Loot.TYPE_DAGGER,    15,  1,  0,  -1, 30, UltimateSkill.WOODEN_DAGGER, "item_wooden_dagger, R.string.wooden_dagger, R.string.wooden_help),
//    IRON_DAGGER(     0x05BB4ADB, Loot.TYPE_DAGGER,     0,  4,  0,  -1, 40, UltimateSkill.IRON_DAGGER, "item_iron_dagger, R.string.iron_dagger, 0),
//    IRON_DAGGER_1(   0xEB5020CE, Loot.TYPE_DAGGER,     0,  6,  0,  -2, 50, UltimateSkill.IRON_DAGGER_1, "item_iron_dagger, R.string.iron_dagger_1, R.string.iron_1_help, "bg_refined),
//    WIND_DAGGER(     0xF3FE45BB, Loot.TYPE_DAGGER,     0,  2,  0,  -3, 50, UltimateSkill.WIND_DAGGER, "item_wind_dagger, R.string.wind_dagger, 0),
//    MITHRIL_DAGGER(  0x334C939B, Loot.TYPE_DAGGER,    50,  3,  0,  -2, 50, UltimateSkill.MITHRIL_DAGGER, "item_mithril_dagger, R.string.mithril_dagger, R.string.mithril_help),
//    SHADOW_DAGGER(   0x7BDC7012, Loot.TYPE_DAGGER,   -50, 16,  0,  -2, 75, UltimateSkill.SHADOW_DAGGER, "item_shadow_dagger, R.string.shadow_dagger, R.string.shadow_dagger_help, "bg_cursed),
//    YGGDRASIL_DAGGER(0x18837AC0, Loot.TYPE_DAGGER | Loot.TYPE_EXTRA,
//			                                         150, 10,  0,  -5,110, UltimateSkill.YGGDRASIL_DAGGER, "item_yggdrasil_dagger, R.string.yggdrasil_dagger, R.string.yggdrasil_help),
//
//    // SWORD
//    WOODEN_SWORD(    0x78311C02,  Loot.TYPE_SWORD,    15,  3,  0,   0, 30, UltimateSkill.WOODEN_SWORD, "item_wooden_sword, R.string.wooden_sword, R.string.wooden_help),
//    IRON_SWORD(      0xBBDDD8D6,  Loot.TYPE_SWORD,     0,  6,  0,   0, 40, UltimateSkill.IRON_SWORD, "item_iron_sword, R.string.iron_sword, 0),
//    IRON_SWORD_1(    0xBA875A26,  Loot.TYPE_SWORD,     0, 10,  0,   0, 50, UltimateSkill.IRON_SWORD_1, "item_iron_sword, R.string.iron_sword_1, R.string.iron_1_help, "bg_refined),
//    GREAT_SWORD(     0xA58D8E6E,  Loot.TYPE_SWORD,     0,  9,  0,   3, 50, UltimateSkill.GREAT_SWORD, "item_great_sword, R.string.great_sword, 0),
//    MITHRIL_SWORD(   0xE8B343FA,  Loot.TYPE_SWORD,    50,  7,  0,   0, 50, UltimateSkill.MITHRIL_SWORD, "item_mithril_sword, R.string.mithril_sword, R.string.mithril_help),
//    FIRE_SWORD(      0xCD5765FC,  Loot.TYPE_SWORD,     0, 20, -5,   0, 75, UltimateSkill.FIRE_SWORD, "item_fire_sword, R.string.fire_sword, R.string.fire_sword_help),
//    ICE_SWORD(       0x15FE199F,  Loot.TYPE_SWORD,     0, 10,  8,   1, 75, UltimateSkill.ICE_SWORD, "item_ice_sword, R.string.ice_sword, R.string.ice_sword_help),
//    THUNDER_SWORD(   0x129359BD,  Loot.TYPE_SWORD,     0,  8,  0,  -6, 75, UltimateSkill.THUNDER_SWORD, "item_thunder_sword, R.string.thunder_sword, R.string.thunder_sword_help),
//    HOLY_SWORD(      0xF6DF6BDE,  Loot.TYPE_SWORD,    50, 18,  0,   0, 90, UltimateSkill.HOLY_SWORD, "item_holy_sword, R.string.holy_sword, R.string.holy_help, "bg_blessed),
//    SKULL_SWORD(     0xC2730049,  Loot.TYPE_SWORD,   -99, 22,  0,   0, 90, UltimateSkill.SKULL_SWORD, "item_skull_sword, R.string.skull_sword, R.string.skull_sword_help, "bg_cursed),
//    YGGDRASIL_SWORD( 0x4E6DBC58,  Loot.TYPE_SWORD | Loot.TYPE_EXTRA,
//			                                         150, 20,  0,   0,110, UltimateSkill.YGGDRASIL_SWORD, "item_yggdrasil_sword, R.string.yggdrasil_sword, R.string.yggdrasil_help),
//
//    // STAFF
//    WOODEN_STAFF(    0x4B7E45E5,  Loot.TYPE_STAFF,    15,  5,  0,   2, 30, UltimateSkill.WOODEN_STAFF, "item_wooden_staff, R.string.wooden_staff, R.string.wooden_help),
//    MITHRIL_STAFF(   0x901BB6AA,  Loot.TYPE_STAFF,    50, 11,  0,   3, 50, UltimateSkill.MITHRIL_STAFF, "item_mithril_staff, R.string.mithril_staff, R.string.mithril_help),
//    YGGDRASIL_STAFF( 0x75C22964,  Loot.TYPE_STAFF | Loot.TYPE_EXTRA,
//			                                         150, 25,  0,   5,110, UltimateSkill.YGGDRASIL_STAFF, "item_yggdrasil_staff, R.string.yggdrasil_staff, R.string.yggdrasil_help),
//
//    // SHIELD
//    WOODEN_SHIELD(   0xABF3BE7A, Loot.TYPE_SHIELD,    15,  0,  3,   0, 30, UltimateSkill.WOODEN_SHIELD, "item_wooden_shield, R.string.wooden_shield, R.string.wooden_help),
//    IRON_SHIELD(     0xEF197405, Loot.TYPE_SHIELD,     0,  0,  6,   0, 40, UltimateSkill.IRON_SHIELD, "item_iron_shield, R.string.iron_shield, 0),
//    IRON_SHIELD_1(   0x0A26C71B, Loot.TYPE_SHIELD,     0,  0, 10,   0, 50, UltimateSkill.IRON_SHIELD_1, "item_iron_shield, R.string.iron_shield_1, R.string.iron_1_help, "bg_refined),
//    TOWER_SHIELD(    0xD8ABCEFD, Loot.TYPE_SHIELD,     0,  0,  9,   3, 50, UltimateSkill.TOWER_SHIELD, "item_tower_shield, R.string.tower_shield, 0),
//    MITHRIL_SHIELD(  0x334D3822, Loot.TYPE_SHIELD,    50,  0,  7,   0, 50, UltimateSkill.MITHRIL_SHIELD, "item_mithril_shield, R.string.mithril_shield, R.string.mithril_help),
//    BLACK_SHIELD(    0x18263078, Loot.TYPE_SHIELD,     0,  0, 20,   6, 75, UltimateSkill.BLACK_SHIELD, "item_black_shield, R.string.black_shield, R.string.black_shield_help),
//    WHITE_SHIELD(    0x5C8ECAE9, Loot.TYPE_SHIELD,    20,  0, 14,  -1, 75, UltimateSkill.WHITE_SHIELD, "item_white_shield, R.string.white_shield, R.string.white_shield_help),
//    HOLY_SHIELD(     0x4167C340, Loot.TYPE_SHIELD,    50,  0, 18,   0, 90, UltimateSkill.HOLY_SHIELD, "item_holy_shield, R.string.holy_shield, R.string.holy_help, "bg_blessed),
//    SKULL_SHIELD(    0x3DBC6F30, Loot.TYPE_SHIELD,   -99,  0, 22,   0, 90, UltimateSkill.SKULL_SHIELD, "item_skull_shield, R.string.skull_shield, R.string.skull_shield_help, "bg_cursed),
//    YGGDRASIL_SHIELD(0x353FD420, Loot.TYPE_SHIELD | Loot.TYPE_EXTRA,
//			                                         150,  0, 20,   0,110, UltimateSkill.YGGDRASIL_SHIELD, "item_yggdrasil_shield, R.string.yggdrasil_shield, R.string.yggdrasil_help),
//
//    DEVELOPER_BUCKLER(0x3E6546E0,Loot.TYPE_SHIELD | Loot.TYPE_EXTRA,
//                                                       0,  0,  0,   0,  1, "item_buckler, R.string.developer_buckler, R.string.developer_buckler_help),
//
//    // RING
//    WOODEN_RING(     0x0E8A8E97,   Loot.TYPE_RING,    15,  0,  1,  -2, 30, UltimateSkill.WOODEN_RING, "item_wooden_ring, R.string.wooden_ring, R.string.wooden_help),
//    IRON_RING(       0xF249BA82,   Loot.TYPE_RING,     0,  3,  3,   0, 40, UltimateSkill.IRON_RING, "item_iron_ring, R.string.iron_ring, 0),
//    IRON_RING_1(     0x40E46204,   Loot.TYPE_RING,     0,  5,  5,   0, 50, UltimateSkill.IRON_RING_1, "item_iron_ring, R.string.iron_ring_1, R.string.iron_1_help, "bg_refined),
//    YELLOW_RING(     0x359E6775,   Loot.TYPE_RING,   250,  0,  0,   0, 60, UltimateSkill.YELLOW_RING, "item_yellow_ring, R.string.yellow_ring, 0),
//    RED_RING(        0x1FC9667C,   Loot.TYPE_RING,     0, 10,  0,   0, 60, UltimateSkill.RED_RING, "item_red_ring, R.string.red_ring, 0),
//    BLUE_RING(       0x63A51AB8,   Loot.TYPE_RING,     0,  0, 10,   0, 60, UltimateSkill.BLUE_RING, "item_blue_ring, R.string.blue_ring, 0),
//    GREEN_RING(      0x5F9BD7D9,   Loot.TYPE_RING,     0,  0,  0,  -5, 60, UltimateSkill.GREEN_RING, "item_green_ring, R.string.green_ring, 0),
//    BLACK_RING(      0xA37D6B01,   Loot.TYPE_RING,   -40,  4,  4,  -2, 60, UltimateSkill.BLACK_RING, "item_black_ring, R.string.black_ring, R.string.black_ring_help, "bg_cursed),
//    WHITE_RING(      0x8868001E,   Loot.TYPE_RING,     0,  4,  4,  -2, 60, UltimateSkill.WHITE_RING, "item_white_ring, R.string.white_ring, 0),
//    RING_OF_GODDESS( 0x5839ADBB,   Loot.TYPE_RING,    50,  8,  8,  -1, 90, UltimateSkill.RING_OF_GODDESS, "item_ring_of_goddess, R.string.ring_of_goddess, R.string.holy_help, "bg_blessed),
//    SKULL_RING(      0x3D12A612,   Loot.TYPE_RING,   -99, 10, 10,  -2, 90, UltimateSkill.SKULL_RING, "item_skull_ring, R.string.skull_ring, R.string.skull_ring_help, "bg_cursed),
//    YGGDRASIL_RING(  0x7137FA06,   Loot.TYPE_RING | Loot.TYPE_EXTRA,
//			                                         150,  5,  5,  -5,110, UltimateSkill.YGGDRASIL_RING, "item_yggdrasil_ring, R.string.yggdrasil_ring, R.string.yggdrasil_help),
//    RING_OF_LIFE(    0x39906A7C,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_LIFE, "item_ring_of_life, R.string.ring_of_life, R.string.ring_of_life_help),
//    RING_OF_ATTACK(  0x7C583139,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_ATTACK, "item_ring_of_attack, R.string.ring_of_attack, R.string.ring_of_attack_help),
//    RING_OF_DEFENSE( 0x66E11E0F,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_DEFENSE, "item_ring_of_defense, R.string.ring_of_defense, R.string.ring_of_defense_help),
//    RING_OF_SPEED(   0xAA187161,   Loot.TYPE_RING,     0,  0,  0,   0,125, UltimateSkill.RING_OF_SPEED, "item_ring_of_speed, R.string.ring_of_speed, R.string.ring_of_speed_help),
//
//    // BOOTS
//    IRON_BOOTS(      0x8EC27250,  Loot.TYPE_BOOTS,     0,  0,  0,  -3, 40, UltimateSkill.IRON_BOOTS, "item_iron_boots, R.string.iron_boots, 0),
//    IRON_BOOTS_1(    0x1A5D6F5C,  Loot.TYPE_BOOTS,     0,  0,  0,  -5, 50, UltimateSkill.IRON_BOOTS_1, "item_iron_boots, R.string.iron_boots_1, R.string.iron_1_help, "bg_refined),
//    MITHRIL_BOOTS(   0xD67E50EF,  Loot.TYPE_BOOTS,    50,  1,  1,  -3, 50, UltimateSkill.MITHRIL_BOOTS, "item_mithril_boots, R.string.mithril_boots, R.string.mithril_help),
//    WIND_BOOTS(      0xD29E268B,  Loot.TYPE_BOOTS,     0,  0,  5,  -8, 75, UltimateSkill.WIND_BOOTS, "item_wind_boots, R.string.wind_boots, R.string.wind_boots_help),
//
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
//    SALAMANDO(       0xAC88FACD,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    UNDINE(          0x08A67EAD,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    SYLPH(           0x1BA0ADF0,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    GNOME(           0xD9400A39,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    MANA(            0x010A1FEC,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    CLOCK(           0xA5410FCE,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    LUMINA(          0x9028A1FE,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//    SHADE(           0x414A3491,   Loot.TYPE_EVENT,    0,  0,  0,   0,  0, 0, 0, 0),
//
//    // end
//    END(             0x169819E5,   Loot.TYPE_NONE,     0,  0,  0,   0,  0, 0, 0, 0);
//
//    // type define.
//    private static final int TYPE_NONE		= 0x0;
//    private static final int TYPE_STAR		= 0x1;
//    private static final int TYPE_KEY		= 0x2;
//    private static final int TYPE_COIN		= 0x4;
//    private static final int TYPE_GEM		= 0x8;
//    private static final int TYPE_POTION    = 0x10;
//    private static final int TYPE_DAGGER    = 0x20;
//    private static final int TYPE_SWORD		= 0x40;
//    private static final int TYPE_STAFF		= 0x80;
//    private static final int TYPE_SHIELD    = 0x100;
//    private static final int TYPE_RING		= 0x200;
//    private static final int TYPE_BOOTS		= 0x400;
//    private static final int TYPE_EXTRA		= 0x1000;    // extra item.
//    private static final int TYPE_EVENT		= 0x2000;    // event item.
//    private static final int TYPE_EQUIPMENT = TYPE_DAGGER | TYPE_SWORD | TYPE_STAFF | TYPE_SHIELD | TYPE_RING | TYPE_BOOTS;
//    private static final int TYPE_FORGING    = TYPE_DAGGER | TYPE_SWORD | TYPE_SHIELD;
//
//    private static final int TYPE_CRYSTAL   = 0x4000;
//
//    // without action
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost, int icon, int name, int help) {
//		this.icon = icon;
//		this.name = name;
//		this.help = help;
//		this.status_bg = 0;
//		this.code = code;
//		this.type = type;
//		this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//		this.cost = cost;
//		this.action = UltimateSkill.NONE;
//    }
//
//    // with action
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost, UltimateSkill action, int icon, int name, int help) {
//		this.icon = icon;
//		this.name = name;
//		this.help = help;
//		this.status_bg = 0;
//		this.code = code;
//		this.type = type;
//		this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//		this.cost = cost;
//		this.action = action;
//    }
//
//    // some equipment has state like normal / refined / cursed / blessed....
//    Loot(int code, int type, int life, int attack, int defense, int speed, int cost, UltimateSkill action, int icon, int name, int help, int status_bg) {
//        this.icon = icon;
//        this.name = name;
//        this.help = help;
//        this.status_bg = status_bg;
//        this.code = code;
//        this.type = type;
//        this.upgrade = new Ability(life, attack, 0, 1, defense, speed);
//        this.cost = cost;
//        this.action = action;
//    }
//
//
//    public final int icon;
//    public final int name;
//    public final int help;
//    public final int status_bg;
//    public final int code;
//    private final int type;
//    public final Ability upgrade;
//    public final int cost;
//    public final UltimateSkill action;
//
//    public static Loot find(int code) {
//		for (Loot l : Loot.values()) {
//			if (l.code == code)
//				return l;
//		}
//		throw new RuntimeException("invalid code : " + code);
//    }
//
//    public boolean isPotion() {
//		return (type & Loot.TYPE_POTION) != 0;
//    }
//
//    public boolean isEquipment() {
//		return (type & Loot.TYPE_EQUIPMENT) != 0;
//    }
//
//    public boolean canForging() {
//		return (type & Loot.TYPE_FORGING) != 0;
//    }
//
//    public boolean isPureSpeed() {
//		return this.equals(Loot.IRON_BOOTS) || this.equals(Loot.GREEN_RING);
//    }
//
//    public boolean isNotPremium() {
//		return (type & Loot.TYPE_EXTRA) != 0;
//    }
//
//    public boolean isPowerRing() {
//		return this == RING_OF_LIFE
//			|| this == RING_OF_ATTACK
//			|| this == RING_OF_DEFENSE
//			|| this == RING_OF_SPEED;
//    }
//
//}
