package com.qqhouse.dungeon18plus.struct;

import com.badlogic.gdx.graphics.Color;
import com.qqhouse.dungeon18plus.Game;

import java.util.Locale;

public class Variety {

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

        int NONE        = 0;

        // change limit value, combine life, attack, defense, speed
        int LIMIT       = 1;

        // type.
        int LIFE        = 1 << 1;
        int ATTACK      = 1 << 2;
        int COMBO       = 1 << 3;
        int EXTRA       = 1 << 4;
        int DEFENSE     = 1 << 5;
        int SPEED       = 1 << 6;

        int HEAL        = 1 << 7; // recover current life.
        int DAMAGE      = 1 << 8; // battle , damage.

        // resource
        int KEY         = 1 << 10;

        int COIN        = 1 << 12;
        int STAR        = 1 << 13;
        int EXP         = 1 << 14;
        int SOUL        = 1 << 15;
        int COUNT       = 1 << 16; // count item number...

        int PURE = LIFE | ATTACK | COMBO | EXTRA | DEFENSE | SPEED | HEAL | DAMAGE | KEY | COIN | STAR | SOUL | COUNT;

        // 特殊情況, 只增不減
        int UNABATED    = 1 << 20;


        // style. priority SET_TO > PERCENT > OFFSET
        int OFFSET      = 1 << 28;
        int PERCENT     = 1 << 29;
        int SET         = 1 << 30;
    }

    /*
     * some static value...
     */

    public static final Variety NONE           = new Variety(Type.NONE, 0);

    public static final Variety COUNT          = new Variety(Type.COUNT, 1); // use 1

    public static final Variety LIFE_N99       = new Variety(Type.LIFE | Type.OFFSET, -99);
    public static final Variety LIFE_N50       = new Variety(Type.LIFE | Type.OFFSET, -50);
    public static final Variety LIFE_N40       = new Variety(Type.LIFE | Type.OFFSET, -40);
    public static final Variety LIFE_N20       = new Variety(Type.LIFE | Type.OFFSET, -20);
    public static final Variety LIFE_15        = new Variety(Type.LIFE | Type.OFFSET, 15);
    public static final Variety LIFE_20        = new Variety(Type.LIFE | Type.OFFSET, 20);
    public static final Variety LIFE_25        = new Variety(Type.LIFE | Type.OFFSET, 25);
    public static final Variety LIFE_50        = new Variety(Type.LIFE | Type.OFFSET, 50);
    public static final Variety LIFE_60        = new Variety(Type.LIFE | Type.OFFSET, 60);
    public static final Variety LIFE_75        = new Variety(Type.LIFE | Type.OFFSET, 75);
    public static final Variety LIFE_100       = new Variety(Type.LIFE | Type.OFFSET, 100);
    public static final Variety LIFE_150       = new Variety(Type.LIFE | Type.OFFSET, 150);
    public static final Variety LIFE_200       = new Variety(Type.LIFE | Type.OFFSET, 200);
    public static final Variety LIFE_250       = new Variety(Type.LIFE | Type.OFFSET, 250);
    public static final Variety LIFE_500       = new Variety(Type.LIFE | Type.OFFSET, 500);
    // add 50% life
    public static final Variety LIFE_100P      = new Variety(Type.LIFE | Type.PERCENT, 100);
    // ring of life, set life = 1400
    public static final Variety LIFE_S1500     = new Variety(Type.LIFE | Type.SET, 1500);


    public static final Variety ATTACK_N1      = new Variety(Type.ATTACK | Type.OFFSET, -1);
    public static final Variety ATTACK_1       = new Variety(Type.ATTACK | Type.OFFSET, 1);
    public static final Variety ATTACK_2       = new Variety(Type.ATTACK | Type.OFFSET, 2);
    public static final Variety ATTACK_3       = new Variety(Type.ATTACK | Type.OFFSET, 3);
    public static final Variety ATTACK_4       = new Variety(Type.ATTACK | Type.OFFSET, 4);
    public static final Variety ATTACK_5       = new Variety(Type.ATTACK | Type.OFFSET, 5);
    public static final Variety ATTACK_6       = new Variety(Type.ATTACK | Type.OFFSET, 6);
    public static final Variety ATTACK_7       = new Variety(Type.ATTACK | Type.OFFSET, 7);
    public static final Variety ATTACK_8       = new Variety(Type.ATTACK | Type.OFFSET, 8);
    public static final Variety ATTACK_9       = new Variety(Type.ATTACK | Type.OFFSET, 9);
    public static final Variety ATTACK_10      = new Variety(Type.ATTACK | Type.OFFSET, 10);
    public static final Variety ATTACK_11      = new Variety(Type.ATTACK | Type.OFFSET, 11);
    public static final Variety ATTACK_13      = new Variety(Type.ATTACK | Type.OFFSET, 13);
    public static final Variety ATTACK_15      = new Variety(Type.ATTACK | Type.OFFSET, 15);
    public static final Variety ATTACK_16      = new Variety(Type.ATTACK | Type.OFFSET, 16);
    public static final Variety ATTACK_17      = new Variety(Type.ATTACK | Type.OFFSET, 17);
    public static final Variety ATTACK_18      = new Variety(Type.ATTACK | Type.OFFSET, 18);
    public static final Variety ATTACK_19      = new Variety(Type.ATTACK | Type.OFFSET, 19);
    public static final Variety ATTACK_20      = new Variety(Type.ATTACK | Type.OFFSET, 20);
    public static final Variety ATTACK_22      = new Variety(Type.ATTACK | Type.OFFSET, 22);
    public static final Variety ATTACK_23      = new Variety(Type.ATTACK | Type.OFFSET, 23);
    public static final Variety ATTACK_24      = new Variety(Type.ATTACK | Type.OFFSET, 24);
    public static final Variety ATTACK_25      = new Variety(Type.ATTACK | Type.OFFSET, 25);
    public static final Variety ATTACK_50      = new Variety(Type.ATTACK | Type.OFFSET, 50);
    public static final Variety ATTACK_100     = new Variety(Type.ATTACK | Type.OFFSET, 100);
    // ring of attack, add 10% attack
    public static final Variety ATTACK_12P     = new Variety(Type.ATTACK | Type.PERCENT, 12);

    public static final Variety COMBO_1        = new Variety(Type.COMBO | Type.OFFSET, 1);

    public static final Variety DEFENSE_N5     = new Variety(Type.DEFENSE | Type.OFFSET, -5);
    public static final Variety DEFENSE_N1     = new Variety(Type.DEFENSE | Type.OFFSET, -1);
    public static final Variety DEFENSE_1      = new Variety(Type.DEFENSE | Type.OFFSET, 1);
    public static final Variety DEFENSE_2      = new Variety(Type.DEFENSE | Type.OFFSET, 2);
    public static final Variety DEFENSE_3      = new Variety(Type.DEFENSE | Type.OFFSET, 3);
    public static final Variety DEFENSE_4      = new Variety(Type.DEFENSE | Type.OFFSET, 4);
    public static final Variety DEFENSE_5      = new Variety(Type.DEFENSE | Type.OFFSET, 5);
    public static final Variety DEFENSE_6      = new Variety(Type.DEFENSE | Type.OFFSET, 6);
    public static final Variety DEFENSE_7      = new Variety(Type.DEFENSE | Type.OFFSET, 7);
    public static final Variety DEFENSE_8      = new Variety(Type.DEFENSE | Type.OFFSET, 8);
    public static final Variety DEFENSE_9      = new Variety(Type.DEFENSE | Type.OFFSET, 9);
    public static final Variety DEFENSE_10     = new Variety(Type.DEFENSE | Type.OFFSET, 10);
    public static final Variety DEFENSE_11     = new Variety(Type.DEFENSE | Type.OFFSET, 11);
    public static final Variety DEFENSE_13     = new Variety(Type.DEFENSE | Type.OFFSET, 13);
    public static final Variety DEFENSE_14     = new Variety(Type.DEFENSE | Type.OFFSET, 14);
    public static final Variety DEFENSE_15     = new Variety(Type.DEFENSE | Type.OFFSET, 15);
    public static final Variety DEFENSE_17     = new Variety(Type.DEFENSE | Type.OFFSET, 17);
    public static final Variety DEFENSE_18     = new Variety(Type.DEFENSE | Type.OFFSET, 18);
    public static final Variety DEFENSE_19     = new Variety(Type.DEFENSE | Type.OFFSET, 19);
    public static final Variety DEFENSE_20     = new Variety(Type.DEFENSE | Type.OFFSET, 20);
    public static final Variety DEFENSE_22     = new Variety(Type.DEFENSE | Type.OFFSET, 22);
    public static final Variety DEFENSE_23     = new Variety(Type.DEFENSE | Type.OFFSET, 23);
    public static final Variety DEFENSE_24     = new Variety(Type.DEFENSE | Type.OFFSET, 24);
    public static final Variety DEFENSE_50     = new Variety(Type.DEFENSE | Type.OFFSET, 50);
    public static final Variety DEFENSE_100    = new Variety(Type.DEFENSE | Type.OFFSET, 100);
    // ring of defense, add 10% defense
    public static final Variety DEFENSE_12P    = new Variety(Type.DEFENSE | Type.PERCENT, 12);

    public static final Variety SPEED_N9       = new Variety(Type.SPEED | Type.OFFSET, 9);
    public static final Variety SPEED_N7       = new Variety(Type.SPEED | Type.OFFSET, 7);
    public static final Variety SPEED_N6       = new Variety(Type.SPEED | Type.OFFSET, 6);
    public static final Variety SPEED_N5       = new Variety(Type.SPEED | Type.OFFSET, 5);
    public static final Variety SPEED_N4       = new Variety(Type.SPEED | Type.OFFSET, 4);
    public static final Variety SPEED_N3       = new Variety(Type.SPEED | Type.OFFSET, 3);
    public static final Variety SPEED_N2       = new Variety(Type.SPEED | Type.OFFSET, 2);
    public static final Variety SPEED_N1       = new Variety(Type.SPEED | Type.OFFSET, 1);
    public static final Variety SPEED_1        = new Variety(Type.SPEED | Type.OFFSET, -1);
    public static final Variety SPEED_2        = new Variety(Type.SPEED | Type.OFFSET, -2);
    public static final Variety SPEED_3        = new Variety(Type.SPEED | Type.OFFSET, -3);
    public static final Variety SPEED_4        = new Variety(Type.SPEED | Type.OFFSET, -4);
    public static final Variety SPEED_5        = new Variety(Type.SPEED | Type.OFFSET, -5);
    public static final Variety SPEED_6        = new Variety(Type.SPEED | Type.OFFSET, -6);
    public static final Variety SPEED_7        = new Variety(Type.SPEED | Type.OFFSET, -7);
    public static final Variety SPEED_8        = new Variety(Type.SPEED | Type.OFFSET, -8);
    public static final Variety SPEED_10       = new Variety(Type.SPEED | Type.OFFSET, -10);
    public static final Variety SPEED_20       = new Variety(Type.SPEED | Type.OFFSET, -20);

    // set speed = 8, if speed < 8 then ?
    // TODO check if speed < 8
    public static final Variety SPEED_S8       = new Variety(Type.SPEED | Type.SET, 8);
    // ring of speed, speed / 2
    public static final Variety SPEED_66P      = new Variety(Type.SPEED | Type.PERCENT, -66);

    // key series
    public static final Variety KEY_1          = new Variety(Type.KEY | Type.OFFSET, 1);
    public static final Variety KEY_2          = new Variety(Type.KEY | Type.OFFSET, 2);
    public static final Variety KEY_3          = new Variety(Type.KEY | Type.OFFSET, 3);
    public static final Variety KEY_4          = new Variety(Type.KEY | Type.OFFSET, 4);
    public static final Variety KEY_5          = new Variety(Type.KEY | Type.OFFSET, 5);
    public static final Variety KEY_6          = new Variety(Type.KEY | Type.OFFSET, 6);
    public static final Variety KEY_7          = new Variety(Type.KEY | Type.OFFSET, 7);
    public static final Variety KEY_9          = new Variety(Type.KEY | Type.OFFSET, 9);
    public static final Variety KEY_10         = new Variety(Type.KEY | Type.OFFSET, 10);
    public static final Variety KEY_11         = new Variety(Type.KEY | Type.OFFSET, 11);

    // coin series
    public static final Variety COIN_1         = new Variety(Type.COIN | Type.OFFSET, 1);
    public static final Variety COIN_2         = new Variety(Type.COIN | Type.OFFSET, 2);
    public static final Variety COIN_3         = new Variety(Type.COIN | Type.OFFSET, 3);
    public static final Variety COIN_10        = new Variety(Type.COIN | Type.OFFSET, 10);
    public static final Variety COIN_80        = new Variety(Type.COIN | Type.OFFSET, 80);

    // star series (cost use)
    public static final Variety STAR_1           = new Variety(Type.STAR | Type.OFFSET, 1);
    public static final Variety STAR_2           = new Variety(Type.STAR | Type.OFFSET, 2);
    public static final Variety STAR_4           = new Variety(Type.STAR | Type.OFFSET, 4);
    public static final Variety STAR_5           = new Variety(Type.STAR | Type.OFFSET, 5);
    public static final Variety STAR_6           = new Variety(Type.STAR | Type.OFFSET, 6);
    public static final Variety STAR_8           = new Variety(Type.STAR | Type.OFFSET, 8);
    public static final Variety STAR_9           = new Variety(Type.STAR | Type.OFFSET, 9);
    public static final Variety STAR_10          = new Variety(Type.STAR | Type.OFFSET, 10);
    public static final Variety STAR_11          = new Variety(Type.STAR | Type.OFFSET, 11);
    public static final Variety STAR_12          = new Variety(Type.STAR | Type.OFFSET, 12);
    public static final Variety STAR_13          = new Variety(Type.STAR | Type.OFFSET, 13);
    public static final Variety STAR_15          = new Variety(Type.STAR | Type.OFFSET, 15);
    public static final Variety STAR_18          = new Variety(Type.STAR | Type.OFFSET, 18);
    public static final Variety STAR_20          = new Variety(Type.STAR | Type.OFFSET, 20);

    public static final Variety SOUL_99          = new Variety(Type.SOUL | Type.OFFSET, 99);

    /*
     * limit series for power ring...
     */
    public static final Variety MAX_LIFE_U1500   = new Variety(Type.LIMIT | Type.UNABATED | Type.LIFE | Type.SET, 1500);
    public static final Variety MAX_ATTACK_12P   = new Variety(Type.LIMIT | Type.ATTACK | Type.PERCENT, 12);
    public static final Variety MAX_DEFENSE_12P  = new Variety(Type.LIMIT | Type.DEFENSE | Type.PERCENT, 12);
    public static final Variety MIN_SPEED_1      = new Variety(Type.LIMIT | Type.SPEED | Type.OFFSET, -1);

    @Type
    public final int type;
    public final int value;

    public Variety(@Type int type, int value) {
        this.type = type;
        this.value = value;
    }

    @Type
    public int getPureType() {
        return (type & Type.PURE);
    }

    public boolean isUnabated() { return (type & Type.UNABATED) != 0; }

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
            case Type.LIFE: return "life";
            case Type.ATTACK: return "attack";
            case Type.DEFENSE: return "defense";
            case Type.SPEED: return "speed";
            case Type.HEAL: return "resurrection";
            case Type.DAMAGE: return "damage";
            case Type.KEY: return "key";
            case Type.COIN: return "coin";
            case Type.STAR: return "star";
            default:
                throw new RuntimeException(String.format(Locale.US, "unhandled type : %08X.", type));
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
                throw new RuntimeException(String.format(Locale.US, "unhandled type : %08X.", type));
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
