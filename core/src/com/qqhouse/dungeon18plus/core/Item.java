package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Varier;
import static com.qqhouse.dungeon18plus.struct.Varier.*;

public enum Item {
    // NONE
    NONE( 0x55D42055, "", "", "", Type.NONE, 0),

    // SOUL
    SOUL( 0x2F5D69C7, "soul", "soul", "soul_help", Type.NONE, 0),

    // STAR
    STAR( 0x71F2B299, "star", "star", "star_help", Type.NONE, 0),

    // KEY
    KEY( 0x42A9564D, "key", "key", "key_help", Type.NONE,0),

    // COIN
    COPPER_COIN( 0x2CD45554, "copper_coin", "copper_coin", "coin_help", Type.NONE, 0, COIN_1),
    SILVER_COIN( 0x6B178FC4, "silver_coin", "silver_coin", "coin_help", Type.NONE, 0),
    GOLDEN_COIN( 0x51EF4A9D, "golden_coin", "golden_coin", "coin_help", Type.NONE, 0),

    // GEM
    YELLOW_GEM( 0x9D314920, "yellow_gem", "yellow_gem", "gem_help", Type.GEM, 0),
    RED_GEM( 0x9985f510, "red_gem", "red_gem", "gem_help", Type.GEM, 0),
    BLUE_GEM( 0xE226C9C6, "blue_gem", "blue_gem", "gem_help", Type.GEM, 0),
    GREEN_GEM( 0xC62689DF, "green_gem", "green_gem", "gem_help", Type.GEM, 0),
    WHITE_GEM( 0x08EE509C, "white_gem", "white_gem", "white_gem_help", Type.GEM, 0),

    // CRYSTAL
    LIFE_CRYSTAL(0x7C93D214, "yellow_crystal", "life_crystal", "crystal_help",
            Type.CRYSTAL, 0, LIFE_100),
    ATTACK_CRYSTAL( 0x8D6A6D32,"red_crystal", "attack_crystal", "crystal_help",
            Type.CRYSTAL, 0, ATTACK_2),
    DEFENSE_CRYSTAL( 0x3CC34697, "blue_crystal", "defense_crystal", "crystal_help",
            Type.CRYSTAL, 0, DEFENSE_2),
    SPEED_CRYSTAL( 0xCC852142, "green_crystal", "speed_crystal", "crystal_help",
            Type.CRYSTAL, 0, SPEED_1),
    POWER_CRYSTAL( 0x6D91DC4F, "white_crystal", "power_crystal", "power_crystal_help",
            Type.CRYSTAL, 0, LIFE_100, ATTACK_2, DEFENSE_2, SPEED_1),

    // cursed crystal, for fairy to recycle
    YELLOW_CRYSTAL(0x0564B06E, "yellow_crystal", "yellow_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    RED_CRYSTAL( 0xECBD93A3,"red_crystal", "red_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    BLUE_CRYSTAL( 0xB9C8E347, "blue_crystal", "blue_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    GREEN_CRYSTAL( 0x9B836DC3, "green_crystal", "green_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_5),
    WHITE_CRYSTAL( 0xD67942F1, "white_crystal", "white_crystal", "cursed_crystal_help",
            Type.CURSED | Type.CRYSTAL, 0, STAR_20),


    // POTION
    YELLOW_POTION( 0x628CC786, "yellow_potion", "yellow_potion", "potion_help",
            Type.POTION, 0),
    RED_POTION( 0x5F9D5113, "red_potion", "red_potion", "potion_help",
            Type.POTION, 0),
    BLUE_POTION( 0x2C912503, "blue_potion", "blue_potion", "potion_help",
            Type.POTION, 0),
    GREEN_POTION( 0x119914C1, "green_potion", "green_potion", "potion_help",
            Type.POTION, 0),
    //    PURPLE_POTION(   0x551CB91B, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "purple_potion", "purple_potion", "purple_potion_help),
    CYAN_POTION( 0x522D42A8, "cyan_potion", "cyan_potion", "potion_help",
            Type.POTION, 0),
//    BLACK_POTION(    0x95DFDE69, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "black_potion", "black_potion", "black_potion_help),
//    WHITE_POTION(    0x7BD2A32A, Loot.TYPE_POTION,     0,  0,  0,   0,  0, "white_potion", "white_potion", "white_potion_help),

    // DAGGER
    WOODEN_DAGGER( 0x22DAFA3E, "wooden_dagger", "wooden_dagger", "wooden_help",
            Type.NORMAL | Type.DAGGER, 30, UniqueSkill.WOODEN_DAGGER, LIFE_15, ATTACK_1, SPEED_1 ),
    IRON_DAGGER( 0x05BB4ADB, "iron_dagger", "iron_dagger", null,
            Type.NORMAL | Type.DAGGER, 40, UniqueSkill.IRON_DAGGER, ATTACK_4, SPEED_1),
    IRON_DAGGER_1( 0xEB5020CE, "iron_dagger", "iron_dagger_1", "iron_1_help",
            Type.REFINED | Type.DAGGER, 50, UniqueSkill.IRON_DAGGER_1, ATTACK_6, SPEED_2),
    WIND_DAGGER( 0xF3FE45BB, "wind_dagger", "wind_dagger", null,
            Type.NORMAL | Type.DAGGER, 50, UniqueSkill.WIND_DAGGER, ATTACK_2, SPEED_3),
    MITHRIL_DAGGER( 0x334C939B, "mithril_dagger", "mithril_dagger", "mithril_help",
            Type.NORMAL | Type.DAGGER, 50, UniqueSkill.MITHRIL_DAGGER, LIFE_50, ATTACK_3, SPEED_2),
    SHADOW_DAGGER(0x7BDC7012, "shadow_dagger", "shadow_dagger", "shadow_dagger_help",
            Type.CURSED | Type.DAGGER, 75, UniqueSkill.SHADOW_DAGGER, LIFE_N50, ATTACK_16, SPEED_2),
    YGGDRASIL_DAGGER(0x18837AC0, "yggdrasil_dagger", "yggdrasil_dagger", "yggdrasil_help",
            Type.BLESSED | Type.DAGGER | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_DAGGER, LIFE_150, ATTACK_10, SPEED_5),

    // SWORD
    WOODEN_SWORD(0x78311C02, "wooden_sword", "wooden_sword", "wooden_help",
            Type.NORMAL | Type.SWORD, 30, UniqueSkill.WOODEN_SWORD, LIFE_15, ATTACK_3),
    IRON_SWORD(0xBBDDD8D6, "iron_sword", "iron_sword", null,
            Type.NORMAL | Type.SWORD, 40, UniqueSkill.IRON_SWORD, ATTACK_6),
    IRON_SWORD_1(0xBA875A26, "iron_sword", "iron_sword_1", "iron_1_help",
            Type.REFINED | Type.SWORD, 50, UniqueSkill.IRON_SWORD_1, ATTACK_10),
    GREAT_SWORD(0xA58D8E6E, "great_sword", "great_sword", null,
            Type.NORMAL | Type.SWORD, 50, UniqueSkill.GREAT_SWORD, ATTACK_9, SPEED_N3),
    MITHRIL_SWORD(0xE8B343FA, "mithril_sword", "mithril_sword", "mithril_help",
            Type.NORMAL | Type.SWORD, 50, UniqueSkill.MITHRIL_SWORD, LIFE_50, ATTACK_7),
    FIRE_SWORD(0xCD5765FC, "fire_sword", "fire_sword", "fire_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.FIRE_SWORD, ATTACK_20, DEFENSE_N5),
    ICE_SWORD(0x15FE199F, "ice_sword", "ice_sword", "ice_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.ICE_SWORD, ATTACK_10, DEFENSE_8, SPEED_N1),
    THUNDER_SWORD(0x129359BD, "thunder_sword", "thunder_sword", "thunder_sword_help",
            Type.NORMAL | Type.SWORD, 75, UniqueSkill.THUNDER_SWORD, ATTACK_8, SPEED_6),
    HOLY_SWORD(0xF6DF6BDE, "holy_sword", "holy_sword", "holy_help",
            Type.BLESSED | Type.SWORD, 90, UniqueSkill.HOLY_SWORD, LIFE_50, ATTACK_18),
    SKULL_SWORD(0xC2730049, "skull_sword", "skull_sword", "skull_sword_help",
            Type.CURSED | Type.SWORD, 90, UniqueSkill.SKULL_SWORD, LIFE_N99, ATTACK_22),
    YGGDRASIL_SWORD(0x4E6DBC58, "yggdrasil_sword", "yggdrasil_sword", "yggdrasil_help",
            Type.BLESSED | Type.SWORD | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_SWORD, LIFE_150, ATTACK_20),

    // STAFF
    WOODEN_STAFF(0x4B7E45E5, "wooden_staff", "wooden_staff", "wooden_help",
            Type.NORMAL | Type.STAFF, 30, UniqueSkill.WOODEN_STAFF, LIFE_15, ATTACK_5, SPEED_N2),
    MITHRIL_STAFF(0x901BB6AA, "mithril_staff", "mithril_staff", "mithril_help",
            Type.NORMAL | Type.STAFF, 50, UniqueSkill.MITHRIL_STAFF, LIFE_50, ATTACK_11, SPEED_N3),
    YGGDRASIL_STAFF( 0x75C22964, "yggdrasil_staff", "yggdrasil_staff", "yggdrasil_help",
            Type.BLESSED | Type.STAFF | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_STAFF, LIFE_150, ATTACK_25, SPEED_N5),

    // SHIELD
    WOODEN_SHIELD(0xABF3BE7A, "wooden_shield", "wooden_shield", "wooden_help",
            Type.NORMAL | Type.SHIELD, 30, UniqueSkill.WOODEN_SHIELD, LIFE_15, DEFENSE_3),
    IRON_SHIELD(0xEF197405, "iron_shield", "iron_shield", null,
            Type.NORMAL | Type.SHIELD, 40, UniqueSkill.IRON_SHIELD, DEFENSE_6),
    IRON_SHIELD_1(0x0A26C71B, "iron_shield", "iron_shield_1", "iron_1_help",
            Type.REFINED | Type.SHIELD, 50, UniqueSkill.IRON_SHIELD_1, DEFENSE_10),
    TOWER_SHIELD(0xD8ABCEFD, "tower_shield", "tower_shield", null,
            Type.NORMAL | Type.SHIELD, 50, UniqueSkill.TOWER_SHIELD, DEFENSE_9, SPEED_N3),
    MITHRIL_SHIELD(0x334D3822, "mithril_shield", "mithril_shield", "mithril_help",
            Type.NORMAL | Type.SHIELD, 50, UniqueSkill.MITHRIL_SHIELD, LIFE_50, DEFENSE_7),
    BLACK_SHIELD(0x18263078, "black_shield", "black_shield", "black_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UniqueSkill.BLACK_SHIELD, DEFENSE_20, SPEED_N6),
    WHITE_SHIELD(0x5C8ECAE9, "white_shield", "white_shield", "white_shield_help",
            Type.NORMAL | Type.SHIELD, 75, UniqueSkill.WHITE_SHIELD, LIFE_20, DEFENSE_14, SPEED_1),
    HOLY_SHIELD(0x4167C340, "holy_shield", "holy_shield", "holy_help",
            Type.BLESSED | Type.SHIELD, 90, UniqueSkill.HOLY_SHIELD, LIFE_50, DEFENSE_18),
    SKULL_SHIELD(0x3DBC6F30, "skull_shield", "skull_shield", "skull_shield_help",
            Type.CURSED | Type.SHIELD, 90, UniqueSkill.SKULL_SHIELD, LIFE_N99, DEFENSE_22),
    YGGDRASIL_SHIELD(0x353FD420,"yggdrasil_shield", "yggdrasil_shield", "yggdrasil_help",
            Type.BLESSED | Type.SHIELD | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_SHIELD, LIFE_150, DEFENSE_20),
    // special item, for memory.
    DEVELOPER_BUCKLER(0x3E6546E0, "buckler", "developer_buckler", "developer_buckler_help",
            Type.SHIELD | Type.SPECIAL, 1),

    // RING
    WOODEN_RING(0x0E8A8E97, "wooden_ring", "wooden_ring", "wooden_help",
            Type.NORMAL | Type.RING, 30, UniqueSkill.WOODEN_RING, LIFE_15, DEFENSE_1, SPEED_2),
    IRON_RING(0xF249BA82, "iron_ring", "iron_ring", null,
            Type.NORMAL | Type.RING, 40, UniqueSkill.IRON_RING, ATTACK_3, DEFENSE_3),
    IRON_RING_1(0x40E46204, "iron_ring", "iron_ring_1", "iron_1_help",
            Type.REFINED | Type.RING, 50, UniqueSkill.IRON_RING_1, ATTACK_5, DEFENSE_5),
    YELLOW_RING(0x359E6775, "yellow_ring", "yellow_ring", null,
            Type.NORMAL | Type.RING, 60, UniqueSkill.YELLOW_RING, LIFE_250),
    RED_RING(0x1FC9667C, "red_ring", "red_ring", null,
            Type.NORMAL | Type.RING, 60, UniqueSkill.RED_RING, ATTACK_10),
    BLUE_RING(0x63A51AB8, "blue_ring", "blue_ring", null,
            Type.NORMAL | Type.RING, 60, UniqueSkill.BLUE_RING, DEFENSE_10),
    GREEN_RING(0x5F9BD7D9, "green_ring", "green_ring", null,
            Type.NORMAL | Type.RING, 60, UniqueSkill.GREEN_RING, SPEED_5),
    BLACK_RING(0xA37D6B01, "black_ring", "black_ring", "black_ring_help",
            Type.CURSED | Type.RING, 60, UniqueSkill.BLACK_RING, LIFE_N40, ATTACK_4, DEFENSE_4, SPEED_2),
    WHITE_RING(0x8868001E, "white_ring", "white_ring", null,
            Type.NORMAL | Type.RING, 60, UniqueSkill.WHITE_RING, ATTACK_4, DEFENSE_4,SPEED_2),
    RING_OF_GODDESS(0x5839ADBB, "ring_of_goddess", "ring_of_goddess", "holy_help",
            Type.BLESSED | Type.RING, 90, UniqueSkill.RING_OF_GODDESS, LIFE_50, ATTACK_8, DEFENSE_8, SPEED_1),
    SKULL_RING(0x3D12A612, "skull_ring", "skull_ring", "skull_ring_help",
            Type.CURSED | Type.RING, 90, UniqueSkill.SKULL_RING, LIFE_N99, ATTACK_10, DEFENSE_10, SPEED_2),
    YGGDRASIL_RING(  0x7137FA06, "yggdrasil_ring", "yggdrasil_ring", "yggdrasil_help",
            Type.BLESSED | Type.RING | Type.PREMIUM, 110, UniqueSkill.YGGDRASIL_RING, LIFE_150, ATTACK_5, DEFENSE_5, SPEED_5),
    RING_OF_LIFE(0x39906A7C, "ring_of_life", "ring_of_life", "ring_of_life_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_LIFE, LIFE_S999),
    RING_OF_ATTACK(0x7C583139, "ring_of_attack", "ring_of_attack", "ring_of_attack_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_ATTACK, MAX_ATTACK_10P, ATTACK_10P),
    RING_OF_DEFENSE(0x66E11E0F, "ring_of_defense", "ring_of_defense", "ring_of_defense_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_DEFENSE, MAX_DEFENSE_10P, DEFENSE_10P),
    RING_OF_SPEED(0xAA187161, "ring_of_speed", "ring_of_speed", "ring_of_speed_help",
            Type.NORMAL | Type.RING, 125, UniqueSkill.RING_OF_SPEED, MIN_SPEED_1, SPEED_50P),

    // BOOTS
    IRON_BOOTS(0x8EC27250, "iron_boots", "iron_boots", null,
            Type.NORMAL | Type.BOOTS, 40, UniqueSkill.IRON_BOOTS, SPEED_3),
    IRON_BOOTS_1(0x1A5D6F5C, "iron_boots", "iron_boots_1", "iron_1_help",
            Type.REFINED | Type.BOOTS, 50, UniqueSkill.IRON_BOOTS_1, SPEED_5),
    MITHRIL_BOOTS(0xD67E50EF, "mithril_boots", "mithril_boots", "mithril_help",
            Type.NORMAL | Type.BOOTS, 50, UniqueSkill.MITHRIL_BOOTS, LIFE_50, ATTACK_1, DEFENSE_1, SPEED_3),
    WIND_BOOTS(0xD29E268B, "wind_boots", "wind_boots", "wind_boots_help",
            Type.NORMAL | Type.BOOTS, 75, UniqueSkill.WIND_BOOTS, DEFENSE_5, SPEED_8),

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
    public final Varier[] upgrade;

    Item(int code, String icon, String name, String help,
         @Type int type, int price, UniqueSkill skill, Varier... upgrade) {
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

    // without ultimate skill
    Item(int code, String icon, String name, String help,
         @Type int type, int price, Varier... upgrade) {
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

    public boolean isPowerRing() {
        return this == RING_OF_LIFE
                || this == RING_OF_ATTACK
                || this == RING_OF_DEFENSE
                || this == RING_OF_SPEED;
    }

    // filter out, if filter = equipment , return true if not equipment.
    public boolean filterOut(int filter) { return (type & filter) == 0; }
}
