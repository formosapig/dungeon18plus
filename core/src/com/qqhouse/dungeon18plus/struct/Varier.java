package com.qqhouse.dungeon18plus.struct;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.Game;

import java.util.Locale;

public class Varier {

/*    @IntDef(flag = true, value = {
            Type.NONE,

            // 改變極限的標記
            Type.LIMIT,

            // 基本屬性
            Type.LIFE,Type.ATTACK,Type.COMBO, Type.EXTRA, Type.DEFENSE, Type.SPEED, Type.HEAL, Type.DAMAGE,
            Type.KEY, Type.COIN, Type.STAR, Type.EXP, Type.SOUL, Type.COUNT,

            // 屬性改變的方式
            Type.OFFSET, Type.PERCENT, Type.SET
    })*/
    public @interface Type {

        int NONE    = 0;

        // change limit value, combine life, attack, defense, speed
        int LIMIT   = 1;

        // type.
        int LIFE    = 1 << 1;
        int ATTACK  = 1 << 2;
        int COMBO   = 1 << 3;
        int EXTRA   = 1 << 4;
        int DEFENSE = 1 << 5;
        int SPEED   = 1 << 6;

        int HEAL    = 1 << 7; // recover current life.
        int DAMAGE  = 1 << 8; // battle , damage.

        // resource
        int KEY     = 1 << 10;

        int COIN    = 1 << 12;
        int STAR    = 1 << 13;
        int EXP     = 1 << 14;
        int SOUL    = 1 << 15;
        int COUNT   = 1 << 16; // count item number...

        int PURE = LIFE | ATTACK | COMBO | EXTRA | DEFENSE | SPEED | HEAL | DAMAGE | KEY | COIN | STAR | SOUL | COUNT;

        // style. priority SET_TO > PERCENT > OFFSET
        int OFFSET  = 1 << 28;
        int PERCENT = 1 << 29;
        int SET     = 1 << 30;
    }

    /*
     * some static value...
     */

    public static final Varier NONE           = new Varier(Type.NONE, 0);

    public static final Varier COUNT          = new Varier(Type.COUNT, 1); // use 1

    public static final Varier LIFE_N99       = new Varier(Type.LIFE | Type.OFFSET, -99);
    public static final Varier LIFE_N50       = new Varier(Type.LIFE | Type.OFFSET, -50);
    public static final Varier LIFE_N40       = new Varier(Type.LIFE | Type.OFFSET, -40);
    public static final Varier LIFE_N20       = new Varier(Type.LIFE | Type.OFFSET, -20);
    public static final Varier LIFE_15        = new Varier(Type.LIFE | Type.OFFSET, 15);
    public static final Varier LIFE_20        = new Varier(Type.LIFE | Type.OFFSET, 20);
    public static final Varier LIFE_25        = new Varier(Type.LIFE | Type.OFFSET, 25);
    public static final Varier LIFE_50        = new Varier(Type.LIFE | Type.OFFSET, 50);
    public static final Varier LIFE_60        = new Varier(Type.LIFE | Type.OFFSET, 60);
    public static final Varier LIFE_75        = new Varier(Type.LIFE | Type.OFFSET, 75);
    public static final Varier LIFE_100       = new Varier(Type.LIFE | Type.OFFSET, 100);
    public static final Varier LIFE_150       = new Varier(Type.LIFE | Type.OFFSET, 150);
    public static final Varier LIFE_200       = new Varier(Type.LIFE | Type.OFFSET, 200);
    public static final Varier LIFE_250       = new Varier(Type.LIFE | Type.OFFSET, 250);
    public static final Varier LIFE_500       = new Varier(Type.LIFE | Type.OFFSET, 500);
    // add 50% life
    public static final Varier LIFE_100P      = new Varier(Type.LIFE | Type.PERCENT, 100);
    // ring of life, set life = 1500
    public static final Varier LIFE_S999      = new Varier(Type.LIFE | Type.SET, 999);
    public static final Varier LIFE_S1500     = new Varier(Type.LIFE | Type.SET, 1500);


    public static final Varier ATTACK_N1      = new Varier(Type.ATTACK | Type.OFFSET, -1);
    public static final Varier ATTACK_1       = new Varier(Type.ATTACK | Type.OFFSET, 1);
    public static final Varier ATTACK_2       = new Varier(Type.ATTACK | Type.OFFSET, 2);
    public static final Varier ATTACK_3       = new Varier(Type.ATTACK | Type.OFFSET, 3);
    public static final Varier ATTACK_4       = new Varier(Type.ATTACK | Type.OFFSET, 4);
    public static final Varier ATTACK_5       = new Varier(Type.ATTACK | Type.OFFSET, 5);
    public static final Varier ATTACK_6       = new Varier(Type.ATTACK | Type.OFFSET, 6);
    public static final Varier ATTACK_7       = new Varier(Type.ATTACK | Type.OFFSET, 7);
    public static final Varier ATTACK_8       = new Varier(Type.ATTACK | Type.OFFSET, 8);
    public static final Varier ATTACK_9       = new Varier(Type.ATTACK | Type.OFFSET, 9);
    public static final Varier ATTACK_10      = new Varier(Type.ATTACK | Type.OFFSET, 10);
    public static final Varier ATTACK_11      = new Varier(Type.ATTACK | Type.OFFSET, 11);
    public static final Varier ATTACK_13      = new Varier(Type.ATTACK | Type.OFFSET, 13);
    public static final Varier ATTACK_15      = new Varier(Type.ATTACK | Type.OFFSET, 15);
    public static final Varier ATTACK_16      = new Varier(Type.ATTACK | Type.OFFSET, 16);
    public static final Varier ATTACK_17      = new Varier(Type.ATTACK | Type.OFFSET, 17);
    public static final Varier ATTACK_18      = new Varier(Type.ATTACK | Type.OFFSET, 18);
    public static final Varier ATTACK_19      = new Varier(Type.ATTACK | Type.OFFSET, 19);
    public static final Varier ATTACK_20      = new Varier(Type.ATTACK | Type.OFFSET, 20);
    public static final Varier ATTACK_22      = new Varier(Type.ATTACK | Type.OFFSET, 22);
    public static final Varier ATTACK_23      = new Varier(Type.ATTACK | Type.OFFSET, 23);
    public static final Varier ATTACK_24      = new Varier(Type.ATTACK | Type.OFFSET, 24);
    public static final Varier ATTACK_25      = new Varier(Type.ATTACK | Type.OFFSET, 25);
    public static final Varier ATTACK_50      = new Varier(Type.ATTACK | Type.OFFSET, 50);
    public static final Varier ATTACK_100     = new Varier(Type.ATTACK | Type.OFFSET, 100);
    // ring of attack, add 10% attack
    public static final Varier ATTACK_15P     = new Varier(Type.ATTACK | Type.PERCENT, 15);

    public static final Varier COMBO_1        = new Varier(Type.COMBO | Type.OFFSET, 1);

    public static final Varier DEFENSE_N5     = new Varier(Type.DEFENSE | Type.OFFSET, -5);
    public static final Varier DEFENSE_N1     = new Varier(Type.DEFENSE | Type.OFFSET, -1);
    public static final Varier DEFENSE_1      = new Varier(Type.DEFENSE | Type.OFFSET, 1);
    public static final Varier DEFENSE_2      = new Varier(Type.DEFENSE | Type.OFFSET, 2);
    public static final Varier DEFENSE_3      = new Varier(Type.DEFENSE | Type.OFFSET, 3);
    public static final Varier DEFENSE_4      = new Varier(Type.DEFENSE | Type.OFFSET, 4);
    public static final Varier DEFENSE_5      = new Varier(Type.DEFENSE | Type.OFFSET, 5);
    public static final Varier DEFENSE_6      = new Varier(Type.DEFENSE | Type.OFFSET, 6);
    public static final Varier DEFENSE_7      = new Varier(Type.DEFENSE | Type.OFFSET, 7);
    public static final Varier DEFENSE_8      = new Varier(Type.DEFENSE | Type.OFFSET, 8);
    public static final Varier DEFENSE_9      = new Varier(Type.DEFENSE | Type.OFFSET, 9);
    public static final Varier DEFENSE_10     = new Varier(Type.DEFENSE | Type.OFFSET, 10);
    public static final Varier DEFENSE_11     = new Varier(Type.DEFENSE | Type.OFFSET, 11);
    public static final Varier DEFENSE_13     = new Varier(Type.DEFENSE | Type.OFFSET, 13);
    public static final Varier DEFENSE_14     = new Varier(Type.DEFENSE | Type.OFFSET, 14);
    public static final Varier DEFENSE_15     = new Varier(Type.DEFENSE | Type.OFFSET, 15);
    public static final Varier DEFENSE_17     = new Varier(Type.DEFENSE | Type.OFFSET, 17);
    public static final Varier DEFENSE_18     = new Varier(Type.DEFENSE | Type.OFFSET, 18);
    public static final Varier DEFENSE_19     = new Varier(Type.DEFENSE | Type.OFFSET, 19);
    public static final Varier DEFENSE_20     = new Varier(Type.DEFENSE | Type.OFFSET, 20);
    public static final Varier DEFENSE_22     = new Varier(Type.DEFENSE | Type.OFFSET, 22);
    public static final Varier DEFENSE_23     = new Varier(Type.DEFENSE | Type.OFFSET, 23);
    public static final Varier DEFENSE_24     = new Varier(Type.DEFENSE | Type.OFFSET, 24);
    public static final Varier DEFENSE_50     = new Varier(Type.DEFENSE | Type.OFFSET, 50);
    public static final Varier DEFENSE_100    = new Varier(Type.DEFENSE | Type.OFFSET, 100);
    // ring of defense, add 10% defense
    public static final Varier DEFENSE_15P    = new Varier(Type.DEFENSE | Type.PERCENT, 15);

    public static final Varier SPEED_N9       = new Varier(Type.SPEED | Type.OFFSET, 9);
    public static final Varier SPEED_N7       = new Varier(Type.SPEED | Type.OFFSET, 7);
    public static final Varier SPEED_N6       = new Varier(Type.SPEED | Type.OFFSET, 6);
    public static final Varier SPEED_N5       = new Varier(Type.SPEED | Type.OFFSET, 5);
    public static final Varier SPEED_N4       = new Varier(Type.SPEED | Type.OFFSET, 4);
    public static final Varier SPEED_N3       = new Varier(Type.SPEED | Type.OFFSET, 3);
    public static final Varier SPEED_N2       = new Varier(Type.SPEED | Type.OFFSET, 2);
    public static final Varier SPEED_N1       = new Varier(Type.SPEED | Type.OFFSET, 1);
    public static final Varier SPEED_1        = new Varier(Type.SPEED | Type.OFFSET, -1);
    public static final Varier SPEED_2        = new Varier(Type.SPEED | Type.OFFSET, -2);
    public static final Varier SPEED_3        = new Varier(Type.SPEED | Type.OFFSET, -3);
    public static final Varier SPEED_4        = new Varier(Type.SPEED | Type.OFFSET, -4);
    public static final Varier SPEED_5        = new Varier(Type.SPEED | Type.OFFSET, -5);
    public static final Varier SPEED_6        = new Varier(Type.SPEED | Type.OFFSET, -6);
    public static final Varier SPEED_7        = new Varier(Type.SPEED | Type.OFFSET, -7);
    public static final Varier SPEED_8        = new Varier(Type.SPEED | Type.OFFSET, -8);
    public static final Varier SPEED_10       = new Varier(Type.SPEED | Type.OFFSET, -10);
    public static final Varier SPEED_20       = new Varier(Type.SPEED | Type.OFFSET, -20);

    // set speed = 8, if speed < 8 then ?
    // TODO check if speed < 8
    public static final Varier SPEED_S8       = new Varier(Type.SPEED | Type.SET, 8);
    // ring of speed, speed / 2
    public static final Varier SPEED_66P      = new Varier(Type.SPEED | Type.PERCENT, -66);

    // key series
    public static final Varier KEY_1          = new Varier(Type.KEY | Type.OFFSET, 1);
    public static final Varier KEY_2          = new Varier(Type.KEY | Type.OFFSET, 2);
    public static final Varier KEY_3          = new Varier(Type.KEY | Type.OFFSET, 3);
    public static final Varier KEY_4          = new Varier(Type.KEY | Type.OFFSET, 4);
    public static final Varier KEY_5          = new Varier(Type.KEY | Type.OFFSET, 5);
    public static final Varier KEY_6          = new Varier(Type.KEY | Type.OFFSET, 6);
    public static final Varier KEY_7          = new Varier(Type.KEY | Type.OFFSET, 7);
    public static final Varier KEY_9          = new Varier(Type.KEY | Type.OFFSET, 9);
    public static final Varier KEY_10         = new Varier(Type.KEY | Type.OFFSET, 10);
    public static final Varier KEY_11         = new Varier(Type.KEY | Type.OFFSET, 11);

    // coin series
    public static final Varier COIN_1         = new Varier(Type.COIN | Type.OFFSET, 1);
    public static final Varier COIN_10        = new Varier(Type.COIN | Type.OFFSET, 10);
    public static final Varier COIN_80        = new Varier(Type.COIN | Type.OFFSET, 80);

    // star series (cost use)
    public static final Varier STAR_1         = new Varier(Type.STAR | Type.OFFSET, 1);
    public static final Varier STAR_5         = new Varier(Type.STAR | Type.OFFSET, 5);
    public static final Varier STAR_6         = new Varier(Type.STAR | Type.OFFSET, 6);
    public static final Varier STAR_8         = new Varier(Type.STAR | Type.OFFSET, 8);
    public static final Varier STAR_9         = new Varier(Type.STAR | Type.OFFSET, 9);
    public static final Varier STAR_10        = new Varier(Type.STAR | Type.OFFSET, 10);
    public static final Varier STAR_11        = new Varier(Type.STAR | Type.OFFSET, 11);
    public static final Varier STAR_12        = new Varier(Type.STAR | Type.OFFSET, 12);
    public static final Varier STAR_13        = new Varier(Type.STAR | Type.OFFSET, 13);
    public static final Varier STAR_15        = new Varier(Type.STAR | Type.OFFSET, 15);
    public static final Varier STAR_18        = new Varier(Type.STAR | Type.OFFSET, 18);
    public static final Varier STAR_20        = new Varier(Type.STAR | Type.OFFSET, 20);

    public static final Varier SOUL_99        = new Varier(Type.SOUL | Type.OFFSET, 99);

    /*
     * limit series for power ring...
     */
    public static final Varier MAX_LIFE_500     = new Varier(Type.LIMIT | Type.LIFE | Type.OFFSET, 500);
    public static final Varier MAX_LIFE_S1500   = new Varier(Type.LIMIT | Type.LIFE | Type.SET, 1500);
    public static final Varier MAX_ATTACK_15P   = new Varier(Type.LIMIT | Type.ATTACK | Type.PERCENT, 15);
    public static final Varier MAX_DEFENSE_15P  = new Varier(Type.LIMIT | Type.DEFENSE | Type.PERCENT, 15);
    public static final Varier MIN_SPEED_1      = new Varier(Type.LIMIT | Type.SPEED | Type.OFFSET, -1);

    @Type
    public final int type;
    public final int value;

    public Varier(@Type int type, int value) {
        this.type = type;
        this.value = value;
    }

    @Type
    public int getPureType() {
        return (type & Type.PURE);
    }

    public boolean isOffset() {
        return (type & Type.OFFSET) != 0;
    }

    public boolean isPercent() {
        return (type & Type.PERCENT) != 0;
    }

    public boolean isSet() {
        return (type & Type.SET) != 0;
    }

    public boolean isLimit() { return (type & Type.LIMIT) != 0; }

    public boolean isLife() {
        return (type & Type.LIFE) != 0;
    }

    public boolean isAttack() {
        return (type & Type.ATTACK) != 0;
    }

    public boolean isDefense() {
        return (type & Type.DEFENSE) != 0;
    }

    public boolean isSpeed() {
        return (type & Type.SPEED) != 0;
    }

    public boolean isCount() { return (type & Type.COUNT) != 0; }


    // get icon
    public String getIconKey() {
        switch (getPureType()) {
            case Type.LIFE: return "icon32_life";
            case Type.ATTACK: return "icon32_attack";
            case Type.DEFENSE: return "icon32_defense";
            case Type.SPEED: return "icon32_speed";
            case Type.HEAL: return "icon32_resurrection";
            case Type.DAMAGE: return "icon32_damage";
            case Type.KEY: return "item_key";
            case Type.COIN: return "item_copper_coin";
            case Type.STAR: return "item_star";
            default:
                throw new RuntimeException(String.format(Locale.US, "unhandle type : %08X.", type));
        }
    }

    public String getIcon16Key() {
        switch (getPureType()) {
            case Type.LIFE: return "life";
            case Type.ATTACK: return "attack";
            case Type.DEFENSE: return "defense";
            case Type.SPEED: return "speed";
            case Type.HEAL: return "resurrection";
            case Type.DAMAGE: return "damage";
            case Type.KEY: return "cost_key";
            case Type.COIN: return "cost_coin";
            case Type.STAR: return "cost_star";
            case Type.SOUL: return "cost_soul";
            default:
                throw new RuntimeException(String.format(Locale.US, "unhandle type : %08X.", type));
        }
    }
    public Color getColor() {
        switch (getPureType()) {
            case Type.LIFE:
            case Type.HEAL: return Game.Colour.LIFE;
            case Type.ATTACK: return Game.Colour.ATTACK;
            case Type.DEFENSE: return Game.Colour.DEFENSE;
            case Type.SPEED: return Game.Colour.SPEED;
            case Type.DAMAGE: return Game.Colour.DAMAGE;
            case Type.KEY:
            case Type.COIN:
            case Type.STAR:
            case Type.SOUL: return Game.Colour.RARE;
            default:
                throw new RuntimeException(String.format(Locale.US, "unhandle type : %08X.", type));
        }
    }

    public String getText(boolean withPlus) {
        if (isSet())
            return "=" + value;
        else if (isPercent())
            return (withPlus && 0 < value ? "+" : "") + value + "%";
        else
            return (withPlus && 0 < value ? "+" : "") + value;
    }

}
