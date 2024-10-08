package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Variety;
import static com.qqhouse.dungeon18plus.struct.Variety.*;

public enum Item {
    // NONE
    NONE( 0x55D42055, "", "", "", Type.NONE, 0),

    // SOUL
    SOUL( 0x2F5D69C7, "item/soul", "soul", "soul_help", Type.NONE, 0),

    // STAR
    STAR( 0x71F2B299, "item/star", "star", "star_help", Type.NONE, 0, STAR_1),

    // KEY
    KEY( 0x42A9564D, "item/key", "key", "key_help", Type.NONE,0, KEY_1),

    // COIN
    COPPER_COIN( 0x2CD45554, "item/copper_coin", "copper_coin", "coin_help", Type.NONE, 0, COIN_1),
    SILVER_COIN( 0x6B178FC4, "item/silver_coin", "silver_coin", "coin_help", Type.NONE, 0),
    GOLDEN_COIN( 0x51EF4A9D, "item/golden_coin", "golden_coin", "coin_help", Type.NONE, 0),

    // GEM
    YELLOW_GEM( 0x9D314920, "item/yellow_gem", "yellow_gem", "gem_help", Type.GEM, 0),
    RED_GEM( 0x9985f510, "item/red_gem", "red_gem", "gem_help", Type.GEM, 0),
    BLUE_GEM( 0xE226C9C6, "item/blue_gem", "blue_gem", "gem_help", Type.GEM, 0),
    GREEN_GEM( 0xC62689DF, "item/green_gem", "green_gem", "gem_help", Type.GEM, 0),
    WHITE_GEM( 0x08EE509C, "item/white_gem", "white_gem", "white_gem_help", Type.GEM, 0),

    // CRYSTAL
    LIFE_CRYSTAL(0x7C93D214, "item/yellow_crystal", "life_crystal", "crystal_help",
            Type.CRYSTAL, 0, LIFE_100),
    ATTACK_CRYSTAL( 0x8D6A6D32,"item/red_crystal", "attack_crystal", "crystal_help",
            Type.CRYSTAL, 0, ATTACK_2),
    DEFENSE_CRYSTAL( 0x3CC34697, "item/blue_crystal", "defense_crystal", "crystal_help",
            Type.CRYSTAL, 0, DEFENSE_2),
    SPEED_CRYSTAL( 0xCC852142, "item/green_crystal", "speed_crystal", "crystal_help",
            Type.CRYSTAL, 0, SPEED_1),
    POWER_CRYSTAL( 0x6D91DC4F, "item/white_crystal", "power_crystal", "power_crystal_help",
            Type.CRYSTAL, 0, LIFE_50, ATTACK_1, DEFENSE_1, SPEED_1),

    // cursed crystal, for fairy to recycle
    YELLOW_CRYSTAL(0x0564B06E, "item/yellow_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    RED_CRYSTAL( 0xECBD93A3,"item/red_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    BLUE_CRYSTAL( 0xB9C8E347, "item/blue_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    GREEN_CRYSTAL( 0x9B836DC3, "item/green_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    WHITE_CRYSTAL( 0xD67942F1, "item/white_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_20),

    CURSED_CRYSTAL( 0x4A3FECCE, "item/black_crystal", "cursed_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, KEY_1, STAR_9),



    // POTION
    YELLOW_POTION( 0x628CC786, "item/yellow_potion", "yellow_potion", "potion_help",
            Type.POTION, 5, LIFE_500),
    RED_POTION( 0x5F9D5113, "item/red_potion", "red_potion", "potion_help",
            Type.POTION, 5, ATTACK_50),
    BLUE_POTION( 0x2C912503, "item/blue_potion", "blue_potion", "potion_help",
            Type.POTION, 5, DEFENSE_50),
    GREEN_POTION( 0x119914C1, "item/green_potion", "green_potion", "potion_help",
            Type.POTION, 5, SPEED_20),
    //    PURPLE_POTION(   0x551CB91B, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "purple_potion", "purple_potion", "purple_potion_help),
    CYAN_POTION( 0x522D42A8, "item/cyan_potion", "cyan_potion", "potion_help",
            Type.POTION, 0, COMBO_1),
//    BLACK_POTION(    0x95DFDE69, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "black_potion", "black_potion", "black_potion_help),
//    WHITE_POTION(    0x7BD2A32A, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "white_potion", "white_potion", "white_potion_help),

    // DAGGER
    AGED_DAGGER( 0x05BB4ADB, "item/iron_dagger", "aged_dagger", "aged_help",
            Type.CURSED | Type.DAGGER | Type.NOT_FOR_SALE | Type.AGED, 1, ATTACK_N1, SPEED_N1),
    TRAINING_DAGGER( 0x9CD6C0D7, "item/wooden_dagger", "training_dagger", "training_help",
            Type.NORMAL | Type.DAGGER | Type.NOT_FOR_SALE, 1, ATTACK_1, SPEED_1, KEY_5 ),
    WOODEN_DAGGER( 0x22DAFA3E, "item/wooden_dagger", "wooden_dagger", "wooden_help",
            Type.NORMAL | Type.DAGGER, 30, UniqueSkill.WOODEN_DAGGER, LIFE_15, ATTACK_1, SPEED_1, KEY_5 ),
    IRON_DAGGER( 0xA80F1C4A, "item/iron_dagger", "iron_dagger", null,
            Type.NORMAL | Type.DAGGER, 40, UniqueSkill.IRON_DAGGER, ATTACK_4, SPEED_1),
    IRON_DAGGER_1( 0xEB5020CE, "item/iron_dagger", "iron_dagger_1", "iron_1_help",
            Type.REFINED | Type.DAGGER, 50, UniqueSkill.IRON_DAGGER_1, ATTACK_5, SPEED_2),
    WIND_DAGGER( 0xF3FE45BB, "item/wind_dagger", "wind_dagger", null,
            Type.NORMAL | Type.DAGGER, 50, UniqueSkill.WIND_DAGGER, ATTACK_1, SPEED_3, KEY_7),
    MITHRIL_DAGGER( 0x334C939B, "item/mithril_dagger", "mithril_dagger", "mithril_help",
            Type.NORMAL | Type.DAGGER, 50, UniqueSkill.MITHRIL_DAGGER, LIFE_50, ATTACK_3, SPEED_2, KEY_3),
    IRON_DAGGER_2( 0xCEB28D7B, "item/iron_dagger", "iron_dagger_2", "iron_2_help",
            Type.REFINED | Type.DAGGER, 66, ATTACK_7, SPEED_3),
    WIND_DAGGER_1( 0x0E038F13, "item/wind_dagger", "wind_dagger_1", "crusader_forge_help",
            Type.REFINED | Type.DAGGER, 75, ATTACK_6, SPEED_4, KEY_11),
    ELVEN_DAGGER( 0x2BFAA035, "item/mithril_dagger", "elven_dagger", "elven_help",
            Type.BLESSED | Type.DAGGER, 75, LIFE_75, ATTACK_4, SPEED_5, KEY_4),
    SHADOW_DAGGER(0x7BDC7012, "item/shadow_dagger", "shadow_dagger", "shadow_dagger_help",
            Type.CURSED | Type.DAGGER, 75, UniqueSkill.SHADOW_DAGGER, LIFE_N50, ATTACK_16, SPEED_1, KEY_2),
    YGGDRASIL_DAGGER(0x18837AC0, "item/yggdrasil_dagger", "yggdrasil_dagger", "yggdrasil_help",
            Type.BLESSED | Type.DAGGER | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_DAGGER, LIFE_150, ATTACK_13, SPEED_4, KEY_5),

    // SWORD
    AGED_SWORD(0x5CAEC4F6, "item/iron_sword", "aged_sword", "aged_help",
            Type.CURSED | Type.SWORD | Type.NOT_FOR_SALE | Type.AGED, 1, ATTACK_N1),
    TRAINING_SWORD(0xBBE61C40, "item/wooden_sword", "training_sword", "training_help",
            Type.NORMAL | Type.SWORD | Type.NOT_FOR_SALE, 1, ATTACK_2, STAR_10),
    WOODEN_SWORD(0x78311C02, "item/wooden_sword", "wooden_sword", "wooden_help",
            Type.NORMAL | Type.SWORD, 30, UniqueSkill.WOODEN_SWORD, LIFE_15, ATTACK_4),
    IRON_SWORD(0xBBDDD8D6, "item/iron_sword", "iron_sword", null,
            Type.NORMAL | Type.SWORD, 40, UniqueSkill.IRON_SWORD, ATTACK_6),
    IRON_SWORD_1(0xBA875A26, "item/iron_sword", "iron_sword_1", "iron_1_help",
            Type.REFINED | Type.SWORD, 50, UniqueSkill.IRON_SWORD_1, ATTACK_9),
    GREAT_SWORD(0xA58D8E6E, "item/great_sword", "great_sword", null,
            Type.NORMAL | Type.SWORD, 50, UniqueSkill.GREAT_SWORD, ATTACK_10, SPEED_N4),
    MITHRIL_SWORD(0xE8B343FA, "item/mithril_sword", "mithril_sword", "mithril_help",
            Type.NORMAL | Type.SWORD, 50, UniqueSkill.MITHRIL_SWORD, LIFE_50, ATTACK_7),
    IRON_SWORD_2(0xA296DEEB, "item/iron_sword", "iron_sword_2", "iron_2_help",
            Type.REFINED | Type.SWORD, 66, ATTACK_13),
    GREAT_SWORD_1(0x9BDB2E93, "item/great_sword", "great_sword_1", "crusader_forge_help",
            Type.REFINED | Type.SWORD, 75, ATTACK_17, SPEED_N2),
    ELVEN_SWORD(0xCC37D7CB, "item/mithril_sword", "elven_sword", "elven_help",
            Type.BLESSED | Type.SWORD, 75, LIFE_75, ATTACK_13, SPEED_1),
    FIRE_SWORD(0xCD5765FC, "item/fire_sword", "fire_sword", "fire_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.FIRE_SWORD, ATTACK_20, DEFENSE_N5),
    ICE_SWORD(0x15FE199F, "item/ice_sword", "ice_sword", "ice_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.ICE_SWORD, ATTACK_9, DEFENSE_7, SPEED_N2),
    THUNDER_SWORD(0x129359BD, "item/thunder_sword", "thunder_sword", "thunder_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.THUNDER_SWORD, ATTACK_3, SPEED_6),
    HOLY_SWORD(0xF6DF6BDE, "item/holy_sword", "holy_sword", "holy_help",
            Type.BLESSED | Type.SWORD, 90, UniqueSkill.HOLY_SWORD, LIFE_50, ATTACK_18, STAR_10),
    SKULL_SWORD(0xC2730049, "item/skull_sword", "skull_sword", "skull_sword_help",
            Type.CURSED | Type.SWORD, 90, UniqueSkill.SKULL_SWORD, LIFE_N99, ATTACK_23),
    YGGDRASIL_SWORD(0x4E6DBC58, "item/yggdrasil_sword", "yggdrasil_sword", "yggdrasil_help",
            Type.BLESSED | Type.SWORD | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_SWORD, LIFE_150, ATTACK_20, SPEED_1),

    // STAFF
    TRAINING_STAFF(0xD0DBC509, "item/wooden_staff", "training_staff", "training_help",
            Type.NORMAL | Type.STAFF | Type.NOT_FOR_SALE, 1, ATTACK_3, SPEED_N2, COIN_10),
    WOODEN_STAFF(0x4B7E45E5, "item/wooden_staff", "wooden_staff", "wooden_help",
            Type.NORMAL | Type.STAFF, 30, UniqueSkill.WOODEN_STAFF, LIFE_15, ATTACK_5, SPEED_N2),
    MITHRIL_STAFF(0x901BB6AA, "item/mithril_staff", "mithril_staff", "mithril_help",
            Type.NORMAL | Type.STAFF, 50, UniqueSkill.MITHRIL_STAFF, LIFE_50, ATTACK_9, SPEED_N3),
    ELVEN_STAFF(0xEAF4F321, "item/mithril_staff", "elven_staff", "elven_help",
            Type.BLESSED | Type.STAFF, 75, LIFE_75, ATTACK_16, SPEED_N3),
    YGGDRASIL_STAFF( 0x75C22964, "item/yggdrasil_staff", "yggdrasil_staff", "yggdrasil_help",
            Type.BLESSED | Type.STAFF | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_STAFF, LIFE_150, ATTACK_24, SPEED_N4),

    // SHIELD
    AGED_SHIELD(0xF4A3D658, "item/iron_shield", "aged_shield", "aged_help",
            Type.CURSED | Type.SHIELD | Type.NOT_FOR_SALE | Type.AGED, 1, DEFENSE_N1),
    TRAINING_SHIELD(0x0B21512E, "item/wooden_shield", "training_shield", "training_help",
            Type.NORMAL | Type.SHIELD | Type.NOT_FOR_SALE, 1, DEFENSE_2, STAR_10),
    WOODEN_SHIELD(0xABF3BE7A, "item/wooden_shield", "wooden_shield", "wooden_help",
            Type.NORMAL | Type.SHIELD, 30, UniqueSkill.WOODEN_SHIELD, LIFE_15, DEFENSE_4),
    IRON_SHIELD(0xEF197405, "item/iron_shield", "iron_shield", null,
            Type.NORMAL | Type.SHIELD, 40, UniqueSkill.IRON_SHIELD, DEFENSE_6),
    IRON_SHIELD_1(0x0A26C71B, "item/iron_shield", "iron_shield_1", "iron_1_help",
            Type.REFINED | Type.SHIELD, 50, UniqueSkill.IRON_SHIELD_1, DEFENSE_9),
    TOWER_SHIELD(0xD8ABCEFD, "item/tower_shield", "tower_shield", null,
            Type.NORMAL | Type.SHIELD, 50, UniqueSkill.TOWER_SHIELD, DEFENSE_10, SPEED_N4),
    MITHRIL_SHIELD(0x334D3822, "item/mithril_shield", "mithril_shield", "mithril_help",
            Type.NORMAL | Type.SHIELD, 50, UniqueSkill.MITHRIL_SHIELD, LIFE_50, DEFENSE_7),
    IRON_SHIELD_2(0x109A983D, "item/iron_shield", "iron_shield_2", "iron_2_help",
            Type.REFINED | Type.SHIELD, 66, DEFENSE_13),
    TOWER_SHIELD_1(0xCF8FD117, "item/tower_shield", "tower_shield_1", "crusader_forge_help",
            Type.REFINED | Type.SHIELD, 75, DEFENSE_17, SPEED_N2),
    ELEVEN_SHIELD(0x52A976EC, "item/mithril_shield", "elven_shield", "elven_help",
            Type.BLESSED | Type.SHIELD, 75, LIFE_75, DEFENSE_13, SPEED_1),
    BLACK_SHIELD(0x18263078, "item/black_shield", "black_shield", "black_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UniqueSkill.BLACK_SHIELD, DEFENSE_19, SPEED_N7),
    WHITE_SHIELD(0x5C8ECAE9, "item/white_shield", "white_shield", "white_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UniqueSkill.WHITE_SHIELD, LIFE_20, DEFENSE_13, SPEED_1),
    HOLY_SHIELD(0x4167C340, "item/holy_shield", "holy_shield", "holy_help",
            Type.BLESSED | Type.SHIELD, 90, UniqueSkill.HOLY_SHIELD, LIFE_50, DEFENSE_18, STAR_10),
    SKULL_SHIELD(0x3DBC6F30, "item/skull_shield", "skull_shield", "skull_shield_help",
            Type.CURSED | Type.SHIELD, 90, UniqueSkill.SKULL_SHIELD, LIFE_N99, DEFENSE_23),
    YGGDRASIL_SHIELD(0x353FD420,"item/yggdrasil_shield", "yggdrasil_shield", "yggdrasil_help",
            Type.BLESSED | Type.SHIELD | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_SHIELD, LIFE_150, DEFENSE_20, SPEED_1),
    // special item, for memory.
    DEVELOPER_BUCKLER(0x3E6546E0, "item/buckler", "developer_buckler", "developer_buckler_help",
            Type.SHIELD | Type.NOT_FOR_SALE | Type.SPECIAL, 1),

    // RING
    AGED_RING(0x5EFB771E, "item/iron_ring", "aged_ring", "aged_help",
            Type.CURSED | Type.RING | Type.NOT_FOR_SALE | Type.AGED, 1, ATTACK_N1, DEFENSE_N1),
    TRAINING_RING(0x78A8FA4A, "item/wooden_ring", "training_ring", "training_help",
            Type.NORMAL | Type.RING | Type.NOT_FOR_SALE, 1, DEFENSE_1, SPEED_1, KEY_2, COIN_2, STAR_2),
    WOODEN_RING(0x0E8A8E97, "item/wooden_ring", "wooden_ring", "wooden_help",
            Type.NORMAL | Type.RING, 30, UniqueSkill.WOODEN_RING, LIFE_15, SPEED_2),
    IRON_RING(0xF249BA82, "item/iron_ring", "iron_ring", null,
            Type.NORMAL | Type.RING, 40, UniqueSkill.IRON_RING, ATTACK_3, DEFENSE_3),
    IRON_RING_1(0x40E46204, "item/iron_ring", "iron_ring_1", "iron_1_help",
            Type.REFINED | Type.RING, 50, UniqueSkill.IRON_RING_1, ATTACK_4, DEFENSE_5),
    YELLOW_RING(0x359E6775, "item/yellow_ring", "yellow_ring", "ring_help",
            Type.NORMAL | Type.RING, 60, UniqueSkill.YELLOW_RING, LIFE_500),
    RED_RING(0x1FC9667C, "item/red_ring", "red_ring", "ring_help",
            Type.NORMAL | Type.RING, 60, UniqueSkill.RED_RING, ATTACK_10),
    BLUE_RING(0x63A51AB8, "item/blue_ring", "blue_ring", "ring_help",
            Type.NORMAL | Type.RING, 60, UniqueSkill.BLUE_RING, DEFENSE_10),
    GREEN_RING(0x5F9BD7D9, "item/green_ring", "green_ring", "ring_help",
            Type.NORMAL | Type.RING, 60, UniqueSkill.GREEN_RING, SPEED_5),
    BLACK_RING(0xA37D6B01, "item/black_ring", "black_ring", "black_ring_help",
            Type.CURSED | Type.RING, 60, UniqueSkill.BLACK_RING, LIFE_N40, ATTACK_5, DEFENSE_5, SPEED_1),
    WHITE_RING(0x8868001E, "item/white_ring", "white_ring", "ring_help",
            Type.NORMAL | Type.RING, 60, UniqueSkill.WHITE_RING, LIFE_25, ATTACK_5, DEFENSE_5),
    IRON_RING_2(0x5D179F43, "item/iron_ring", "iron_ring_2", "iron_2_help",
            Type.REFINED | Type.RING, 66, ATTACK_7, DEFENSE_6),
    RING_OF_GODDESS(0x5839ADBB, "item/ring_of_goddess", "ring_of_goddess", "holy_help",
            Type.BLESSED | Type.RING, 90, UniqueSkill.RING_OF_GODDESS, LIFE_50, ATTACK_8, DEFENSE_10, STAR_10),
    SKULL_RING(0x3D12A612, "item/skull_ring", "skull_ring", "skull_ring_help",
            Type.CURSED | Type.RING, 90, UniqueSkill.SKULL_RING, LIFE_N99, ATTACK_11, DEFENSE_8, SPEED_2),
    YGGDRASIL_RING(  0x7137FA06, "item/yggdrasil_ring", "yggdrasil_ring", "yggdrasil_help",
            Type.BLESSED | Type.RING | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_RING, LIFE_150, ATTACK_5, DEFENSE_5, SPEED_6),
    RING_OF_LIFE(0x39906A7C, "item/ring_of_life", "ring_of_life", "ring_of_life_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_LIFE, MAX_LIFE_U1500, LIFE_S1500),
    RING_OF_ATTACK(0x7C583139, "item/ring_of_attack", "ring_of_attack", "ring_of_attack_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_ATTACK, MAX_ATTACK_12P, ATTACK_12P),
    RING_OF_DEFENSE(0x66E11E0F, "item/ring_of_defense", "ring_of_defense", "ring_of_defense_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_DEFENSE, MAX_DEFENSE_12P, DEFENSE_12P),
    RING_OF_SPEED(0xAA187161, "item/ring_of_speed", "ring_of_speed", "ring_of_speed_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_SPEED, MIN_SPEED_1, SPEED_66P),

    // BOOTS
    AGED_BOOTS(0x12474E4D, "item/iron_boots", "aged_boots", "aged_help",
            Type.CURSED | Type.BOOTS | Type.NOT_FOR_SALE | Type.AGED, 1,  SPEED_N2),
    IRON_BOOTS(0x8EC27250, "item/iron_boots", "iron_boots", null,
            Type.NORMAL | Type.BOOTS, 40, UniqueSkill.IRON_BOOTS, SPEED_3),
    IRON_BOOTS_1(0x1A5D6F5C, "item/iron_boots", "iron_boots_1", "iron_1_help",
            Type.REFINED | Type.BOOTS, 50, UniqueSkill.IRON_BOOTS_1, DEFENSE_1, SPEED_4),
    MITHRIL_BOOTS(0xD67E50EF, "item/mithril_boots", "mithril_boots", "mithril_help",
            Type.NORMAL | Type.BOOTS, 50, UniqueSkill.MITHRIL_BOOTS, LIFE_50, DEFENSE_1, SPEED_3),
    IRON_BOOTS_2(0x2198046A, "item/iron_boots", "iron_boots_2", "iron_2_help",
            Type.REFINED | Type.BOOTS, 66, DEFENSE_3, SPEED_5),
    ELVEN_BOOTS(0x34E4283E, "item/mithril_boots", "elven_boots", "elven_help",
            Type.BLESSED | Type.BOOTS, 75, LIFE_75, DEFENSE_3, SPEED_6),
    WIND_BOOTS(0xD29E268B, "item/wind_boots", "wind_boots", "wind_boots_help",
            Type.NORMAL | Type.BOOTS, 75, UniqueSkill.WIND_BOOTS, DEFENSE_1, SPEED_7),

    // elemental magic sword.
    /*
     * 效用, 當剣聖裝備後, 取得異於凡人的力量...
     * 黃色 : 生命, life +1800
     * 紅色 : 攻擊力, attack = 380
     * 藍色 : 防禦力, defense +180
     * 綠色 : 速度, speed = 6 (Global Speed Minimum)
     * 紫色 : 魔力, ????
     * 青色 : 時間, combo +1
     * 白色 : 神聖, ???
     * 黑色 : 黑暗, ???
     *
     * (red)    fire : salamander / salamando
     * (blue)   water : undine
     * (green)  air : sylphid / sylph
     * (yellow) earth : gnome
     * (purple) magic : mana
     * (cyan)   time : tick
     * (white)  light : lumina
     * (black)  dark : shade
     *
     */
    SALAMANDO(0xAC88FACD, "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    UNDINE(0x08A67EAD,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    SYLPH(0x1BA0ADF0,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    GNOME(0xD9400A39,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    MANA(0x010A1FEC,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    CLOCK(0xA5410FCE,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    LUMINA(0x9028A1FE,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),
    SHADE(0x414A3491,  "", "", "",
            Type.ELEMENTAL_MAGIC_SWORD | Type.SPECIAL, 999),

    // end
    END(0x169819E5, "", "", "", Type.NONE, 0);

    public @interface Type {
        int NONE = 0;

        // status
        int UNKNOWN = 1 << 1; // in door.
        int NORMAL  = 1 << 2; // normal
        int REFINED = 1 << 3;
        int CURSED  = 1 << 4;
        int BLESSED = 1 << 5;

        int STATUS = REFINED | CURSED | BLESSED;

        // item kind.
        int GEM     = 1 << 8; // gem / crystal, use to gain ability upgrade
        int CRYSTAL = 1 << 9;
        int POTION  = 1 << 10;

        int DAGGER   = 1 << 11;
        int SWORD    = 1 << 12;
        int AXE      = 1 << 13;
        int SPEAR    = 1 << 14;
        int KATANA   = 1 << 15;
        int STAFF    = 1 << 16;
        int ELEMENTAL_MAGIC_SWORD
                     = 1 << 17;
        int SHIELD   = 1 << 18;
        int RING     = 1 << 19;
        int HELMET   = 1 << 20;
        int GAUNTLET = 1 << 21;
        int BOOTS    = 1 << 22;

        int EQUIPMENT = DAGGER | SWORD | AXE | SPEAR | KATANA | STAFF | ELEMENTAL_MAGIC_SWORD |
                        SHIELD | RING | HELMET | GAUNTLET | BOOTS;
        int FORGING = DAGGER | SWORD | AXE | SPEAR | SHIELD;

        // premium item ?
        int PREMIUM = 1 << 24; // premium only.
        int SPECIAL = 1 << 25; // developer's buckler
        int NOT_FOR_SALE
                    = 1 << 26; // not for sale

        // aged item
        int AGED    = 1 << 27; // aged item in door

    }

    public final int code;
    public final String icon;
    public final String name;
    public final String help;
    public final String unknown; // information when unknown.
    @Type
    private final int type;
    public final int price;
    public final UniqueSkill skill;
    public final Variety[] upgrade;

    Item(int code, String icon, String name, String help,
         @Type int type, int price, UniqueSkill skill, Variety... upgrade) {
        this.code = code;
        this.icon = icon;
        this.name = name;
        this.help = help;
        this.unknown = null;
        this.type = type;
        this.price = price;
        this.skill = skill;
        this.upgrade = upgrade;
    }

    // without unique skill
    Item(int code, String icon, String name, String help,
         @Type int type, int price, Variety... upgrade) {
        this.code = code;
        this.icon = icon;
        this.name = name;
        this.help = help;
        this.unknown = null;
        this.type = type;
        this.price = price;
        this.skill = UniqueSkill.NONE;
        this.upgrade = upgrade;
    }

    public static Item find(int code) {
        for (Item item : Item.values())
            if (item.code == code)
                return item;
        throw new RuntimeException("invalid code : " + code);
    }

    public boolean hasStatus() { return (type & Type.STATUS) != 0; }

    public boolean isCursed() { return (type & Type.CURSED) != 0; }

    public boolean isBlessed() { return (type & Type.BLESSED) != 0; }

    public boolean isRefined() { return (type & Type.REFINED) != 0; }

    public boolean isCrystal() { return (type & Type.CRYSTAL) != 0; }

    public boolean isPotion() { return (type & Type.POTION) != 0; }

    public boolean isEquipment() {
        return (type & Type.EQUIPMENT) != 0;
    }

    public boolean isSword() { return (type & Type.SWORD) != 0; }

    public boolean isShield() { return (type & Type.SHIELD) != 0; }

    public boolean canForging() {
        return (type & Type.FORGING) != 0;
    }

    public boolean isPureSpeed() {
        return this.equals(Item.IRON_BOOTS) || this.equals(Item.GREEN_RING);
    }

    public boolean isNotPremium() { return (type & Type.PREMIUM) == 0; }

    public boolean isNotSpecial() { return (type & Type.SPECIAL) == 0; }

    public boolean isForSale() { return (type & Type.NOT_FOR_SALE) == 0; }

    public boolean isPowerRing() {
        return this == RING_OF_LIFE
                || this == RING_OF_ATTACK
                || this == RING_OF_DEFENSE
                || this == RING_OF_SPEED;
    }

    public boolean isAged() { return (type & Type.AGED) != 0; }

    // filter out, if filter = equipment , return true if not equipment.
    public boolean filterOut(int filter) { return (type & filter) == 0; }
}
