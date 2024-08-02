package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.SoulEffect;
import com.qqhouse.dungeon18plus.struct.Variety;

import java.util.Locale;
import java.util.Random;

// soul of giant...
public enum Soul {
	
	/*
	 *           r1x8    r3x8    r6x8
	 * LIFE       100     300     600
	 * ATTACK       4      12      24
	 * DEFENSE      4      12      24
	 * SPEED       -2      -6     -12
	 * KEY          5      15      30  // key x 10 ~~
	 * COIN        20      60     120
	 * STAR        10      30      60
	 *
	 * new rank system.
	 * 0 inferior (0ld 1 ~ 2)
	 * 1 standard (old 3 ~ 4)
	 * 2 good     (old 5 ~ 6)
	 * 3 superior (old 7 ~ 8)
	 *
	 * add name, help (icon, name, help)
	 *
	 * renew status....
	 *
	 *
	 *
	 * Drop Rate
	 * R1 :  20%
	 * R2 :  10% 
	 * R3 :   5%
	 * R4 :   2%
	 * R5 :   1%
	 * R6 :   0.5%
	 * R7 :   0.2%
	 * R8 :   0.1% 
	 * ...
	 * 
	 */
	
	// broken, complete, shiny
	
	// NONE
	NONE(0x84331E1D, 0, 0, ""),
	
	/*
	 * BAG series : drop golden coin.
	 * small : 1 ~ 3	2
	 * medium : 4 ~ 6	5
	 * big : 8 ~ 12		10
	 * huge : 20 ~ 30	25
	 */
	// R1 : 
	SMALL_BAG( 0xF586B7F7, 1, 1, "golden_coin") {
		@Override
		public int getGoldenCoin() {
			return new Random().nextInt(3) + 1;
		}
	},
	
	// R2 :
	MEDIUM_BAG( 0x2F91B821, 2, 1, "golden_coin") {
		@Override
		public int getGoldenCoin() {
			return new Random().nextInt(3) + 4;
		}
	},
	
	// R4 :
	BIG_BAG( 0x2D5E1F4B, 4, 1, "golden_coin") {
		@Override
		public int getGoldenCoin() {
			return new Random().nextInt(5) + 8;
		}
	},
	
	// R7 
	HUGE_BAG( 0xCDB26EE8, 7, 1, "golden_coin") {
		@Override
		public int getGoldenCoin() {
			return new Random().nextInt(11) + 20;
		}
	},
	
	/*
	 * NEGATIVE_STAR
	 * LV   LIF SPD STAR
	 * 1 :  -20  +1   +3
	 * 2 :  -20  +1   +6
	 * 3 :  -20  +1  +11
	 * R1 : [10 star]
	 *      3 / 8 = 0.375
	 *      0.375 - (-0.2 -0.5) = 1.075
	 *      1.075 * 10 = 10.75 (star)
	 */
	// unfamiliar
    // Everything seems to be so unfamiliar, you need more practice.
	NEGATIVE_STAR( 0x748D17B4, 1, 3, "broken_black_soul") {
		@Override
        public Variety[] getInfluence(int count) {
		    return new Variety[] {
		        Variety.LIFE_N20,
                Variety.SPEED_N1,
                new Variety(Variety.Type.STAR | Variety.Type.OFFSET, count * count + 2)
            };
        }
	},

	/*
	 * STAR
	 * LV   STAR
	 * 1 :     1
	 * 2 :     4
	 * 3 :     7
	 * 4 :    12
	 * 5 :    17
	 * 6 :    24
	 * 7 :    31
	 * 8 :    40
	 * R4 : [40 star]
	 *      8 / 8 = 1
	 *      1 * 40 = 40 (star)
	 */
	STAR( 0x9480E2D8, 4, 8, "complete_black_soul") {
		@Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {new Variety(Variety.Type.STAR | Variety.Type.OFFSET, count * (count + 2) / 2)};
        }
	},

	/*
	 * NEGATIVE_COIN
	 * LV   ATK DEF COIN
	 * 1 :   -1  -1   +4
	 * 2 :   -1  -1  +10
	 * 3 :   -1  -1  +18 
	 * R1 : [20 coin]
	 *      3 / 8 = 0.375
	 *      0.375 - ( -0.25 - 0.25) = 0.875
	 *      0.875 * 20 = 17.5 (coin) 
	 */
	NEGATIVE_COIN( 0x59C3FED9, 1, 3, "broken_black_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    Variety.ATTACK_N1,
                    Variety.DEFENSE_N1,
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, count * (count + 3))
            };
        }
	},

	/*
	 * COIN
	 * LV   COIN
	 * 1 :     3
	 * 2 :     8
	 * 3 :    15
	 * 4 :    24
	 * 5 :    35
	 * 6 :    48
	 * 7 :    63
	 * 8 :    80
	 * R4 : [80 coin]
	 *      8 / 8 = 1
	 *      1 * 80 = 80 (coin)
	 */
	COIN( 0x7C78490B, 4, 8, "complete_black_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, count * (count + 2))
            };
        }
	},
	
	/*
	 * NEGATIVE_LUCKY
	 * LV   LIFE COIN STAR
	 * 1 :   -80   +3   +1
	 * 2 :   -75   +7   +3
	 * 3 :   -70  +11   +5 
	 * 4 :   -65  +15   +7
	 * 5 :   -60  +19   +9
	 * R2 : [20 coin, 10 star]
	 *    (2 * 5) / 8 = 1.25
	 *    1.25 - (-0.6) = 1.85
	 *    1.85 / 2 = 0.925
	 *    0.95 * 20 = 18.5 (coin) ~> 19
	 *    0.95 * 10 = 9.5  (star) ~> 9
	 */
	NEGATIVE_LUCKY( 0x987CA3CB, 2, 5, "complete_black_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.LIFE | Variety.Type.OFFSET, count * 5 - 85),
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, count * 4 - 1),
                    new Variety(Variety.Type.STAR | Variety.Type.OFFSET, count * 2 - 1)
            };
        }
	},
	
	/*
	 * KEY
	 * LV   KEY
	 * 1 :     4        (3)
	 * 2 :     8        (7)
	 * 3 :     11       (11)
	 * 4 :     15       (15)
	 * 5 :     19       (18)
	 * R6 : [30 key]
	 *      1 / 8 = 0.125     0.125 * 30 = 3.75
	 *      2 / 8 = 0.25      0.25 * 30 = 7.5
	 *      3 / 8 = 0.375     0.375 * 30 = 11.25
	 *      4 / 8 = 0.5       0.5 * 30 = 15
	 *      5 / 8 = 0.625     0.625 * 30 = 18.75
	 */
	KEY( 0xC2BC6C6E, 6, 5, "complete_black_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.KEY | Variety.Type.OFFSET, (count * 30) / 8)
            };
        }
	},
	
	/*
	 * NEGATIVE_LIFE
	 * LV   LIFE COIN STAR
	 * 1 :   +50   -3   -3
	 * 2 :  +100   -4   -4
	 * 3 :  +150   -5   -5 
	 * R2 : [200 life]
	 *      (2 * 3) / 8 = 0.75
	 *      0.75 - (-0.25-0.5) = 1.5
	 *      1.5 * 100 = 150 (life) 
	 */
	NEGATIVE_LIFE( 0x830591A0, 2, 3, "broken_yellow_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.LIFE | Variety.Type.OFFSET, count * 50),
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, -(count + 2)),
                    new Variety(Variety.Type.STAR | Variety.Type.OFFSET, -(count + 2))
            };
        }
	},
	
	/*
	 * LONG_LIFE
	 * LV   Life  Att  Def  Spd
	 * 1 :  +200   -1   -1    5
	 * 2 :  +225   -1   -1    4
	 * 3 :  +250   -1   -1    3
	 * 4 :  +275   -1   -1    2
	 * 5 :  +300   -1   -1    1
	 * R3 :
	 *     (3 * 5) / 8 = 1.875
	 *     1.875 - (-0.25-0.25-0.5) = 2.875
	 *     2.875 * 100 = 287.5
	 * 
	 */
	LONG_LIFE( 0xAA388930, 3, 5, "broken_yellow_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.LIFE | Variety.Type.OFFSET, count * 25 + 175),
                    Variety.ATTACK_N1,
                    Variety.DEFENSE_N1,
                    new Variety(Variety.Type.SPEED | Variety.Type.OFFSET, 6 - count)
            };
        }
	},

	/*
	 * LIFE
	 * LV   LIFE
	 * 1 :   +30
	 * 2 :   +80
	 * 3 :  +150
	 * R4 :
	 *     (4 * 3) / 8 = 1.5
	 *     1.5 * 100 = 150  
	 */
	LIFE( 0xF3CA11D7, 4, 3, "complete_yellow_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.LIFE | Variety.Type.OFFSET, count * (count + 2) * 10)
            };
        }

    },

	/*
	 * NEGATIVE_ATTACK
	 * LV   ATTK STAR
	 * 1 :    +2   -8
	 * 2 :    +5  -10
	 * 3 :    +8  -12
	 * R2 :
	 *      (2 * 3) / 8 = 0.75
	 *      0.75 - (-1.2 ) = 1.95
	 *      1.95 * 4 = 7.8 
	 */
	NEGATIVE_ATTACK( 0x80161B2D, 2, 3, "broken_red_soul") {
		@Override
        public Variety[] getInfluence(int count) {
		    return new Variety[] {
		        new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, count * 3 - 1),
                new Variety(Variety.Type.STAR | Variety.Type.OFFSET, (count + 3) * -2)
            };
        }
	},
	
	/*
	 * BERSERK
	 * LV   ATTK DEFE
	 * 1 :    +4   -4
	 * 2 :    +6   -5
	 * 3 :    +9   -6
	 * 4 :   +13   -7
	 * R3 :
	 *     (3 * 4) / 8 = 1.5
	 *     1.5 - (-1.75) = 3.25
	 *     3.25 * 4 = 13 
	 */
	BERSERK( 0xDC7776BB, 3, 4, "broken_red_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, count * (count + 1) / 2 + 3),
                    new Variety(Variety.Type.DEFENSE | Variety.Type.OFFSET, - count - 3)
            };
        }

    },
	
	/*
	 * ATTACK
	 * LV   ATTK
	 * 1 :    +5
	 * 2 :    +6
	 * 3 :    +7
	 * 4 :    +8
	 * R4 :
	 *      (4 * 4) / 8 = 2
	 *      2 * 4 = 8
	 *   
	 */
	ATTACK( 0x22EA9185, 4, 4, "complete_red_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, count + 4)
            };
        }

    },

	/*
	 * NEGATIVE_DEFENSE
	 * LV   DEFE COIN
	 * 1 :    +2  -16
	 * 2 :    +5  -20
	 * 3 :    +8  -24
	 * R2 :
	 *      (2 * 3) / 8 = 0.75
	 *      0.75 - (-1.25) = 2
	 *      2 * 4 = 8
	 * 
	 */
	NEGATIVE_DEFENSE( 0x4C3C74BA, 2, 3, "broken_blue_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.DEFENSE | Variety.Type.OFFSET, count * 3 - 1),
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, (count + 3) * -4)
            };
        }

    },
	
	/*
	 * HARD_DEFENSE
	 * LV   DEFE SPED
	 * 1 :    +2    1
	 * 2 :    +6    3
	 * 3 :   +12    5
	 * 4 :   +20    7
	 * R3 :
	 *      (3 * 4) / 8 = 1.5
	 *      1.5 - (-3.5) = 5
	 *      5 * 4 = 20 
	 */
	HARD_DEFENSE( 0x10C0E49C, 3, 4, "broken_blue_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.DEFENSE | Variety.Type.OFFSET, count * (count + 1)),
                    new Variety(Variety.Type.SPEED | Variety.Type.OFFSET, count * 2 - 1)
            };
        }

    },
	
	/*
	 * DEFENSE
	 * LV   DEFE
	 * 1 :    +1
	 * 2 :    +2
	 * 3 :    +4
	 * 4 :    +8
	 * R4 :
	 *      (4 * 4) / 8 = 2
	 *      2 * 4 = 8
	 */
	DEFENSE( 0xDC894F5B, 4, 4, "complete_blue_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.DEFENSE | Variety.Type.OFFSET, 1 << (count - 1))
            };
        }
	},
	
	/*
	 * NEGATIVE_SPEED
	 * LV   SPED COIN STAR
	 * 1 :    -1   -3   -3
	 * 2 :    -2   -4   -4
	 * 3 :    -3   -5   -5
	 * R2 :
	 *      (2 * 3) / 8 = 0.75
	 *      0.75 -(-0.25-0.5) = 1.5
	 *      1.5 * 2 = 3 
	 */
	NEGATIVE_SPEED( 0x31E2E774, 2, 3, "broken_green_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.SPEED | Variety.Type.OFFSET, -count),
                    new Variety(Variety.Type.COIN | Variety.Type.OFFSET, -(count + 2)),
                    new Variety(Variety.Type.STAR | Variety.Type.OFFSET, -(count + 2))
            };
        }
	},
	
	/*
	 * RUNAWAY
	 * LV   ATTA SPED
	 * 1 :    -3   -2
	 * 2 :    -5   -4
	 * 3 :    -7   -6
	 * R3 :
	 *      (3 * 3) / 8 = 1.125
	 *      1.125 - (-1.75) = 2.875
	 *      2.875 * 2= 5.75
	 */
	RUNAWAY( 0xC3A77D4E, 3, 3, "broken_green_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, count * -2 - 1),
                    new Variety(Variety.Type.SPEED | Variety.Type.OFFSET, count * -2)
            };
        }
	},
	
	/*
	 * SPEED
	 * LV   SPEE
	 * 1 :    -1
	 * 2 :    -2
	 * 3 :    -3
	 * 4 :    -4
	 * 5 :    -5
	 * R4 :
	 *      4 * 5 / 8 = 2.5
	 *      2.5 * 2 = 5
	 */
	SPEED( 0x0A1A981B, 4, 5, "complete_green_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.SPEED | Variety.Type.OFFSET, - count)
            };
        }
	},
	/*
	 * PROTECT
	 * LV   LIFE DEFE
	 * 1 :    25    1
	 * 2 :    50    2
	 * R4 :
	 *      4 * 2 / 8 = 1
	 *      0.5 * 100 = 50
	 *      0.5 * 4   = 2
	 */
	PROTECT( 0xF23B16C6, 4, 2, "complete_cyan_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.LIFE | Variety.Type.OFFSET, count * 25),
                    new Variety(Variety.Type.DEFENSE | Variety.Type.OFFSET, count),
            };
        }
	},

	/*
	 * CHARGE
	 * LV   ATTA SPEE
	 * 1 :     2   -1
	 * 2 :     3   -1
	 * 3 :     4   -1
	 * R4 :
	 *      4 * 3 / 8 = 1.5
	 *      1 * 4   = 4 (attack)
	 *      0.5 * 2 = 1 (speed)
	 * 
	 */
	CHARGE( 0x38AE3194, 4, 3, "complete_purple_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            return new Variety[] {
                    new Variety(Variety.Type.ATTACK | Variety.Type.OFFSET, count + 1),
                    Variety.SPEED_1
            };
        }
	},

	/*
	 * BRAVE
	 * LV   LIFE ATTA STAR
	 * 1 :     0    0   10
	 * 2 :     0    0   10
	 * 3 :     0    0   10
	 * 4 :     0    0   10
	 * 5 :     0    0   10
	 * 6 :     0    0   10
	 * 7 :     0    0   10
	 * 8 :   200    8   10
	 * R5 :
	 *      5 * 8 / 8 = 5
	 *      2 * 100 = 200 
	 *      2 * 4   = 8
	 *      1 * 10  = 10
	 * 
	 */
	BRAVE(0x21EE5B97, 5, 8, "broken_white_soul") {
        @Override
        public Variety[] getInfluence(int count) {
		    if (8 <= count) {
		        return new Variety[] {Variety.LIFE_200, Variety.ATTACK_8, Variety.STAR_10};
            } else {
		        return new Variety[] {Variety.STAR_10};
            }
        }
	},

	/*
	 * DREAM
	 * LV   ATTA DEFE SPEED COIN
	 * 1 :     1    1   -1     0
	 * 2 :     1    1   -1     0
	 * 3 :     1    1   -1     0
	 * 4 :     1    1   -1     0
	 * 5 :     1    1   -1     0
	 * 6 :     1    1   -1     0
	 * 7 :     1    1   -1     0
	 * 8 :     1    1   -1    80
	 * R5 :
	 *      5 * 8 / 8 = 5
	 *      0.25 x 4 = 1
	 *      0.25 x 4 = 1
	 *      0.5 x 2 = 1
	 *      4 x 20 = 80
	 * 
	 */
	DREAM(0x643AD8E5, 5, 8, "broken_white_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            if (8 <= count) {
                return new Variety[] {Variety.ATTACK_1, Variety.DEFENSE_1, Variety.SPEED_1, Variety.COIN_80};
            } else {
                return new Variety[] {Variety.ATTACK_1, Variety.DEFENSE_1, Variety.SPEED_1};
            }
        }

    },

	/*
	 * HOPE
	 * LV   LIFE KEY  COIN STAR
	 * 1 :   150    0    0    0
	 * 2 :   150    0    0    0
	 * 3 :   150    0    0    0
	 * 4 :   150    0    0    0
	 * 5 :   150    0    0    0
	 * 6 :   150    0    0    0
	 * 7 :   150    0    0    0
	 * 8 :   150   10   10   10
	 * R5 :
	 *      5 x 8 / 8 = 5
	 *      1.5 x 100 = 150
	 *      2 x 5 = 10
	 *      1 x 10 = 10
	 *      0.5 x 20 = 10
	 */
	HOPE(0x4DA9FA4F, 5, 8, "broken_white_soul") {
        @Override
        public Variety[] getInfluence(int count) {
            if (8 <= count) {
                return new Variety[] {Variety.LIFE_150, Variety.KEY_10, Variety.COIN_10, Variety.STAR_10};
            } else {
                return new Variety[] {Variety.LIFE_150};
            }
        }
	},
	
	// end.
	END(0x1809A963, 0, 0, "");

	public final int code;
	public final int rank;
	public final int maxCount;
	public final String iconKey;

	// constructor
	Soul(int code, int rank, int maxCount, String soulIconKey) {
		this.code = code;
		this.rank = rank;
		this.maxCount = maxCount;
		this.iconKey = soulIconKey;
	}

	// not override able...
    public final SoulEffect getEffect(int count) {
        SoulEffect sc = new SoulEffect();

        for (Variety var : getInfluence(count)) {
            switch (var.getPureType()) {
                case Variety.Type.LIFE:
                    sc.life += var.value;
                    break;
                case Variety.Type.ATTACK:
                    sc.attack += var.value;
                    break;
                case Variety.Type.DEFENSE:
                    sc.defense += var.value;
                    break;
                case Variety.Type.SPEED:
                    sc.speed += var.value;
                    break;
                case Variety.Type.KEY:
                    sc.key += var.value;
                    break;
                case Variety.Type.COIN:
                    sc.coin += var.value;
                    break;
                case Variety.Type.STAR:
                    sc.star += var.value;
                    break;
                default:
                    throw new RuntimeException(String.format(Locale.US, "invalid soul variety type : %08X", var.type));
            }
        }

        return sc;
    }

	// override
	public Variety[] getInfluence(int level) {
		// return empty array.
		return new Variety[] {};
	}

	// override
	public int getGoldenCoin() {
		return 0;
	}
	
	public boolean isMoneyBag() {
		return this.equals(SMALL_BAG) || this.equals(MEDIUM_BAG) || this.equals(BIG_BAG) || this.equals(HUGE_BAG);
	}

	// 1 / 1000
	public int dropRate() {
		switch (this.rank) {
		case 1: return 400; // 40.0%
		case 2: return 250; // 25.0%
		case 3: return 150; // 15.0%
		case 4: return 100; // 10.0%
		case 5: return  50; //  5.0%
		case 6: return  25; //  2.5%
		case 7: return  10; //  1.0%
		case 8: return   5; //  0.5%
		default: return  0;
		}
	}
	
	
	public static Soul find(int code) {
		for (Soul s : Soul.values()) {
			if (s.code == code)
				return s;
		}
		throw new RuntimeException("invalid code : " + code);
	}
}
