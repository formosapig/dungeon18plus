package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Varier;
import static com.qqhouse.dungeon18plus.struct.Varier.*;

public enum Item {
    // NONE
    NONE( 0x55D42055, "", "", "", Type.NONE, 0),

    // SOUL
    SOUL( 0x2F5D69C7, "icon32_soul", "", "", Type.NONE, 0),

    // STAR
    STAR( 0x71F2B299, "item_star", "star", "star_help", Type.NONE, 0),

    // KEY
    KEY( 0x42A9564D, "item_key", "key", "key_help", Type.NONE,0),

    // COIN
    COPPER_COIN( 0x2CD45554, "item_copper_coin", "copper_coin", "coin_help", Type.NONE, 0),
    SILVER_COIN( 0x6B178FC4, "item_silver_coin", "silver_coin", "coin_help", Type.NONE, 0),
    GOLDEN_COIN( 0x51EF4A9D, "item_golden_coin", "golden_coin", "coin_help", Type.NONE, 0),

    // GEM
    YELLOW_GEM( 0x9D314920, "item_yellow_gem", "yellow_gem", "gem_help", Type.GEM, 0),
    RED_GEM( 0x9985f510, "item_red_gem", "red_gem", "gem_help", Type.GEM, 0),
    BLUE_GEM( 0xE226C9C6, "item_blue_gem", "blue_gem", "gem_help", Type.GEM, 0),
    GREEN_GEM( 0xC62689DF, "item_green_gem", "green_gem", "gem_help", Type.GEM, 0),
    WHITE_GEM( 0x08EE509C, "item_white_gem", "white_gem", "white_gem_help", Type.GEM, 0),

    // CRYSTAL
    LIFE_CRYSTAL(0x7C93D214, "item_yellow_crystal", "item_life_crystal", "item_crystal_help",
            Type.CRYSTAL, 0, LIFE_100),
    ATTACK_CRYSTAL( 0x8D6A6D32,"item_red_crystal", "item_attack_crystal", "item_crystal_help",
            Type.CRYSTAL, 0, ATTACK_2),
    DEFENSE_CRYSTAL( 0x3CC34697, "item_blue_crystal", "item_defense_crystal", "item_crystal_help",
            Type.CRYSTAL, 0, DEFENSE_2),
    SPEED_CRYSTAL( 0xCC852142, "item_green_crystal", "item_speed_crystal", "item_crystal_help",
            Type.CRYSTAL, 0, SPEED_1),
    POWER_CRYSTAL( 0x6D91DC4F, "item_white_crystal", "item_power_crystal", "item_power_crystal_help",
            Type.CRYSTAL, 0, LIFE_100, ATTACK_2, DEFENSE_2, SPEED_1),

    // cursed crystal, for fairy to recycle
    YELLOW_CRYSTAL(0x0564B06E, "item_yellow_crystal", "item_yellow_crystal", "item_cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    RED_CRYSTAL( 0xECBD93A3,"item_red_crystal", "item_red_crystal", "item_cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    BLUE_CRYSTAL( 0xB9C8E347, "item_blue_crystal", "item_blue_crystal", "item_cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    GREEN_CRYSTAL( 0x9B836DC3, "item_green_crystal", "item_green_crystal", "item_cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    WHITE_CRYSTAL( 0xD67942F1, "item_white_crystal", "item_white_crystal", "item_cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_20),


    // POTION
    YELLOW_POTION( 0x628CC786, "item_yellow_potion", "item_yellow_potion", "item_potion_help",
            Type.POTION, 0),
    RED_POTION( 0x5F9D5113, "item_red_potion", "item_red_potion", "item_potion_help",
            Type.POTION, 0),
    BLUE_POTION( 0x2C912503, "item_blue_potion", "item_blue_potion", "item_potion_help",
            Type.POTION, 0),
    GREEN_POTION( 0x119914C1, "item_green_potion", "item_green_potion", "item_potion_help",
            Type.POTION, 0),
    //    PURPLE_POTION(   0x551CB91B, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_purple_potion", "purple_potion", "purple_potion_help),
    CYAN_POTION( 0x522D42A8, "item_cyan_potion", "item_cyan_potion", "item_potion_help",
            Type.POTION, 0),
//    BLACK_POTION(    0x95DFDE69, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_black_potion", "black_potion", "black_potion_help),
//    WHITE_POTION(    0x7BD2A32A, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "item_white_potion", "white_potion", "white_potion_help),

    // DAGGER
    WOODEN_DAGGER( 0x22DAFA3E, "item_wooden_dagger", "wooden_dagger", "wooden_help",
            Type.NORMAL | Type.DAGGER, 30, UltimateSkill.WOODEN_DAGGER, LIFE_15, ATTACK_1, SPEED_1 ),
    IRON_DAGGER( 0x05BB4ADB, "item_iron_dagger", "iron_dagger", "",
            Type.NORMAL | Type.DAGGER, 40, UltimateSkill.IRON_DAGGER, ATTACK_4, SPEED_1),
    IRON_DAGGER_1( 0xEB5020CE, "item_iron_dagger", "iron_dagger_1", "iron_1_help",
            Type.REFINED | Type.DAGGER, 50, UltimateSkill.IRON_DAGGER_1, ATTACK_6, SPEED_2),
    WIND_DAGGER( 0xF3FE45BB, "item_wind_dagger", "wind_dagger", "",
            Type.NORMAL | Type.DAGGER, 50, UltimateSkill.WIND_DAGGER, ATTACK_2, SPEED_3),
    MITHRIL_DAGGER( 0x334C939B, "item_mithril_dagger", "mithril_dagger", "mithril_help",
            Type.NORMAL | Type.DAGGER, 50, UltimateSkill.MITHRIL_DAGGER, LIFE_50, ATTACK_3, SPEED_2),
    SHADOW_DAGGER(0x7BDC7012, "item_shadow_dagger", "shadow_dagger", "shadow_dagger_help",
            Type.CURSED | Type.DAGGER, 75, UltimateSkill.SHADOW_DAGGER, LIFE_N50, ATTACK_16, SPEED_2),
    YGGDRASIL_DAGGER(0x18837AC0, "item_yggdrasil_dagger", "yggdrasil_dagger", "yggdrasil_help",
            Type.BLESSED | Type.DAGGER | Type.PREMIUM, 110, UltimateSkill.YGGDRASIL_DAGGER, LIFE_150, ATTACK_10, SPEED_5),

    // SWORD
    WOODEN_SWORD(0x78311C02, "item_wooden_sword", "wooden_sword", "wooden_help",
            Type.NORMAL | Type.SWORD, 30, UltimateSkill.WOODEN_SWORD, LIFE_15, ATTACK_3),
    IRON_SWORD(0xBBDDD8D6, "item_iron_sword", "iron_sword", "",
            Type.NORMAL | Type.SWORD, 40, UltimateSkill.IRON_SWORD, ATTACK_6),
    IRON_SWORD_1(0xBA875A26, "item_iron_sword", "iron_sword_1", "iron_1_help",
            Type.REFINED | Type.SWORD, 50, UltimateSkill.IRON_SWORD_1, ATTACK_10),
    GREAT_SWORD(0xA58D8E6E, "item_great_sword", "great_sword", "",
            Type.NORMAL | Type.SWORD, 50, UltimateSkill.GREAT_SWORD, ATTACK_9, SPEED_N3),
    MITHRIL_SWORD(0xE8B343FA, "item_mithril_sword", "mithril_sword", "mithril_help",
            Type.NORMAL | Type.SWORD, 50, UltimateSkill.MITHRIL_SWORD, LIFE_50, ATTACK_7),
    FIRE_SWORD(0xCD5765FC, "item_fire_sword", "fire_sword", "fire_sword_help",
            Type.NORMAL | Type.SWORD, 75, UltimateSkill.FIRE_SWORD, ATTACK_20, DEFENSE_N5),
    ICE_SWORD(0x15FE199F, "item_ice_sword", "ice_sword", "ice_sword_help",
            Type.NORMAL | Type.SWORD, 75, UltimateSkill.ICE_SWORD, ATTACK_10, DEFENSE_8, SPEED_N1),
    THUNDER_SWORD(0x129359BD, "item_thunder_sword", "thunder_sword", "thunder_sword_help",
            Type.NORMAL | Type.SWORD, 75, UltimateSkill.THUNDER_SWORD, ATTACK_8, SPEED_6),
    HOLY_SWORD(0xF6DF6BDE, "item_holy_sword", "holy_sword", "holy_help",
            Type.BLESSED | Type.SWORD, 90, UltimateSkill.HOLY_SWORD, LIFE_50, ATTACK_18),
    SKULL_SWORD(0xC2730049, "item_skull_sword", "skull_sword", "skull_sword_help",
            Type.CURSED | Type.SWORD, 90, UltimateSkill.SKULL_SWORD, LIFE_N99, ATTACK_22),
    YGGDRASIL_SWORD(0x4E6DBC58, "item_yggdrasil_sword", "yggdrasil_sword", "yggdrasil_help",
            Type.BLESSED | Type.SWORD | Type.PREMIUM, 110, UltimateSkill.YGGDRASIL_SWORD, LIFE_150, ATTACK_20),

    // STAFF
    WOODEN_STAFF(0x4B7E45E5, "item_wooden_staff", "wooden_staff", "wooden_help",
            Type.NORMAL | Type.STAFF, 30, UltimateSkill.WOODEN_STAFF, LIFE_15, ATTACK_5, SPEED_N2),
    MITHRIL_STAFF(0x901BB6AA, "item_mithril_staff", "mithril_staff", "mithril_help",
            Type.NORMAL | Type.STAFF, 50, UltimateSkill.MITHRIL_STAFF, LIFE_50, ATTACK_11, SPEED_N3),
    YGGDRASIL_STAFF( 0x75C22964, "item_yggdrasil_staff", "yggdrasil_staff", "yggdrasil_help",
            Type.BLESSED | Type.STAFF | Type.PREMIUM, 110, UltimateSkill.YGGDRASIL_STAFF, LIFE_150, ATTACK_25, SPEED_N5),

    // SHIELD
    WOODEN_SHIELD(0xABF3BE7A, "item_wooden_shield", "wooden_shield", "wooden_help",
            Type.NORMAL | Type.SHIELD, 30, UltimateSkill.WOODEN_SHIELD, LIFE_15, DEFENSE_3),
    IRON_SHIELD(0xEF197405, "item_iron_shield", "iron_shield", "",
            Type.NORMAL | Type.SHIELD, 40, UltimateSkill.IRON_SHIELD, DEFENSE_6),
    IRON_SHIELD_1(0x0A26C71B, "item_iron_shield", "iron_shield_1", "iron_1_help",
            Type.REFINED | Type.SHIELD, 50, UltimateSkill.IRON_SHIELD_1, DEFENSE_10),
    TOWER_SHIELD(0xD8ABCEFD, "item_tower_shield", "tower_shield", "",
            Type.NORMAL | Type.SHIELD, 50, UltimateSkill.TOWER_SHIELD, DEFENSE_9, SPEED_N3),
    MITHRIL_SHIELD(0x334D3822, "item_mithril_shield", "mithril_shield", "mithril_help",
            Type.NORMAL | Type.SHIELD, 50, UltimateSkill.MITHRIL_SHIELD, LIFE_50, DEFENSE_7),
    BLACK_SHIELD(0x18263078, "item_black_shield", "black_shield", "black_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UltimateSkill.BLACK_SHIELD, DEFENSE_20, SPEED_N6),
    WHITE_SHIELD(0x5C8ECAE9, "item_white_shield", "white_shield", "white_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UltimateSkill.WHITE_SHIELD, LIFE_20, DEFENSE_14, SPEED_1),
    HOLY_SHIELD(0x4167C340, "item_holy_shield", "holy_shield", "holy_help",
            Type.BLESSED | Type.SHIELD, 90, UltimateSkill.HOLY_SHIELD, LIFE_50, DEFENSE_18),
    SKULL_SHIELD(0x3DBC6F30, "item_skull_shield", "skull_shield", "skull_shield_help",
            Type.CURSED | Type.SHIELD, 90, UltimateSkill.SKULL_SHIELD, LIFE_N99, DEFENSE_22),
    YGGDRASIL_SHIELD(0x353FD420,"item_yggdrasil_shield", "yggdrasil_shield", "yggdrasil_help",
            Type.BLESSED | Type.SHIELD | Type.PREMIUM, 110, UltimateSkill.YGGDRASIL_SHIELD, LIFE_150, DEFENSE_20),
    // special item, for memory.
    DEVELOPER_BUCKLER(0x3E6546E0, "item_buckler", "developer_buckler", "developer_buckler_help",
            Type.SHIELD | Type.SPECIAL, 1),

    // RING
    WOODEN_RING(0x0E8A8E97, "item_wooden_ring", "wooden_ring", "wooden_help",
            Type.NORMAL | Type.RING, 30, UltimateSkill.WOODEN_RING, LIFE_15, DEFENSE_1, SPEED_2),
    IRON_RING(0xF249BA82, "item_iron_ring", "iron_ring", "",
            Type.NORMAL | Type.RING, 40, UltimateSkill.IRON_RING, ATTACK_3, DEFENSE_3),
    IRON_RING_1(0x40E46204, "item_iron_ring", "iron_ring_1", "iron_1_help",
            Type.REFINED | Type.RING, 50, UltimateSkill.IRON_RING_1, ATTACK_5, DEFENSE_5),
    YELLOW_RING(0x359E6775, "item_yellow_ring", "yellow_ring", "",
            Type.NORMAL | Type.RING, 60, UltimateSkill.YELLOW_RING, LIFE_250),
    RED_RING(0x1FC9667C, "item_red_ring", "red_ring", "",
            Type.NORMAL | Type.RING, 60, UltimateSkill.RED_RING, ATTACK_10),
    BLUE_RING(0x63A51AB8, "item_blue_ring", "blue_ring", "",
            Type.NORMAL | Type.RING, 60, UltimateSkill.BLUE_RING, DEFENSE_10),
    GREEN_RING(0x5F9BD7D9, "item_green_ring", "green_ring", "",
            Type.NORMAL | Type.RING, 60, UltimateSkill.GREEN_RING, SPEED_5),
    BLACK_RING(0xA37D6B01, "item_black_ring", "black_ring", "black_ring_help",
            Type.CURSED | Type.RING, 60, UltimateSkill.BLACK_RING, LIFE_N40, ATTACK_4, DEFENSE_4, SPEED_2),
    WHITE_RING(0x8868001E, "item_white_ring", "white_ring", "",
            Type.NORMAL | Type.RING, 60, UltimateSkill.WHITE_RING, ATTACK_4, DEFENSE_4,SPEED_2),
    RING_OF_GODDESS(0x5839ADBB, "item_ring_of_goddess", "ring_of_goddess", "holy_help",
            Type.BLESSED | Type.RING, 90, UltimateSkill.RING_OF_GODDESS, LIFE_50, ATTACK_8, DEFENSE_8, SPEED_1),
    SKULL_RING(0x3D12A612, "item_skull_ring", "skull_ring", "skull_ring_help",
            Type.CURSED | Type.RING, 90, UltimateSkill.SKULL_RING, LIFE_N99, ATTACK_10, DEFENSE_10, SPEED_2),
    YGGDRASIL_RING(  0x7137FA06, "item_yggdrasil_ring", "yggdrasil_ring", "yggdrasil_help",
            Type.BLESSED | Type.RING | Type.PREMIUM, 110, UltimateSkill.YGGDRASIL_RING, LIFE_150, ATTACK_5, DEFENSE_5, SPEED_5),
    RING_OF_LIFE(0x39906A7C, "item_ring_of_life", "ring_of_life", "ring_of_life_help",
            Type.NORMAL | Type.RING, 125, UltimateSkill.RING_OF_LIFE, LIFE_S999),
    RING_OF_ATTACK(0x7C583139, "item_ring_of_attack", "ring_of_attack", "ring_of_attack_help",
            Type.NORMAL | Type.RING, 125, UltimateSkill.RING_OF_ATTACK, MAX_ATTACK_10P, ATTACK_10P),
    RING_OF_DEFENSE(0x66E11E0F, "item_ring_of_defense", "ring_of_defense", "ring_of_defense_help",
            Type.NORMAL | Type.RING, 125, UltimateSkill.RING_OF_DEFENSE, MAX_DEFENSE_10P, DEFENSE_10P),
    RING_OF_SPEED(0xAA187161, "item_ring_of_speed", "ring_of_speed", "ring_of_speed_help",
            Type.NORMAL | Type.RING, 125, UltimateSkill.RING_OF_SPEED, MIN_SPEED_1, SPEED_50P),

    // BOOTS
    IRON_BOOTS(0x8EC27250, "item_iron_boots", "iron_boots", "",
            Type.NORMAL | Type.BOOTS, 40, UltimateSkill.IRON_BOOTS, SPEED_3),
    IRON_BOOTS_1(0x1A5D6F5C, "item_iron_boots", "iron_boots_1", "iron_1_help",
            Type.REFINED | Type.BOOTS, 50, UltimateSkill.IRON_BOOTS_1, SPEED_5),
    MITHRIL_BOOTS(0xD67E50EF, "item_mithril_boots", "mithril_boots", "mithril_help",
            Type.NORMAL | Type.BOOTS, 50, UltimateSkill.MITHRIL_BOOTS, LIFE_50, ATTACK_1, DEFENSE_1, SPEED_3),
    WIND_BOOTS(0xD29E268B, "item_wind_boots", "wind_boots", "wind_boots_help",
            Type.NORMAL | Type.BOOTS, 75, UltimateSkill.WIND_BOOTS, DEFENSE_5, SPEED_8),

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

    }

    public final int code;
    public final String icon;
    public final String name;
    public final String help;
    @Type
    private final int type;
    public final int price;
    public final UltimateSkill skill;
    public final Varier[] upgrade;

    Item(int code, String icon, String name, String help,
         @Type int type, int price, UltimateSkill skill, Varier... upgrade) {
        this.code = code;
        this.icon = icon;
        this.name = name;
        this.help = help;
        this.type = type;
        this.price = price;
        this.skill = skill;
        this.upgrade = upgrade;
    }

    // without ultimate skill
    Item(int code, String icon, String name, String help,
         @Type int type, int price, Varier... upgrade) {
        this.code = code;
        this.icon = icon;
        this.name = name;
        this.help = help;
        this.type = type;
        this.price = price;
        this.skill = UltimateSkill.NONE;
        this.upgrade = upgrade;
    }

    public static Item find(int code) {
        for (Item item : Item.values())
            if (item.code == code)
                return item;
        throw new RuntimeException("invalid code : " + code);
    }

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

    public boolean isPowerRing() {
        return this == RING_OF_LIFE
                || this == RING_OF_ATTACK
                || this == RING_OF_DEFENSE
                || this == RING_OF_SPEED;
    }

    // filter out, if filter = equipment , return true if not equipment.
    public boolean filterOut(int filter) { return (type & filter) == 0; }
}
