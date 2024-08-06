package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;

import static com.qqhouse.dungeon18plus.struct.Operation.*;
import static com.qqhouse.dungeon18plus.struct.Operation2.ALL;
import static com.qqhouse.dungeon18plus.struct.Operation2.ME;

public enum UniqueSkill {

	/*
	 * 基礎
	 * cool down
	 * 
	 * dagger CD = 100 (快)
	 * sword  CD = 200 (中)
	 * staff  CD = 300 (慢)
	 * shield CD = 200 (中)
	 * ring   CD = 100 (快)
	 * 
	 * 
	 * Damage series ( CD = 100 )
	 * 
	 * Start  A:200 D:200 Damage = 200 ~ 500
	 * Middle A:250 D:250 Damage = 800 ~ 1500
	 * Last   A:300 D:300 Damage = 2000 ~ 4000
	 * 
	 * Life recover series ( CD = 100 ) [Ring of Life CD:999 L:+100% = full recovery]
	 * 
	 * Start  L:1000 Heal = 100 ~ 200
	 * Middle L:1500 Heal = 300 ~ 500
	 * Last   L:2000 Heal = 600 ~ 1000
	 * 
	 * Attack/Defense plus series ( CD = 100 ) [Ring of Attack CD:999 A:+25% to all]
	 * 
	 * Start  A:200 plus = 20
	 * Middle A:250 plus = 25
	 * Last   A:300 plus = 30
	 * 
	 * Speed plus series ( CD = 100 ) 
	 * 
	 */
	
	/*
	 * Black Slime
	 */
	// life+25%
	HEAL_25( "item/broken_yellow_soul",         0x687BF639, 360, 360, new OpSt(AS_LIR,   25,   25,  ME,  ME)),
	// attack all with almost half life
	BLACK_MUD( "item/broken_black_soul",        0x37E9D61B, 480, 480, new OpSt(DG_ATK,  250,  250, ALL, ALL)),

	/*
	 * Red Slime
	 */
	// 全部攻擊同一隻會打死人，會被攻擊力影響，Angry之後變得更恐怖了,佰無法配上 Angry.
	QUICK_10( "item/broken_green_soul",         0x3A11753B, 220, 220, new OpSt(DG_ATK,  125,  125,  10,  10)),
	// 全程使用普攻打死 2 -> 3 隻的程度
	ANGRY( "item/broken_red_soul",              0x7F6AC39D, 240, 240, new OpSt(AS_ATK,   15,   15,  ME,  ME),
			                                                          new OpSt(AS_SPD,   -5,   -5,  ME,  ME)),
	// Life 90%, 不會被攻擊力影響
	HEAVY_STRIKE( "item/broken_black_soul",     0x03539E76, 350, 350, new OpSt(DG_DEF,  775,  775,   1,   1)),

    /*
	 * Skeleton Fighter
	 */
	// 普攻 3 -> 4 隻的差別
	ATTACK_UP( "icon/attack",                   0x1F255EAF, 125, 125, new OpSt(AS_ATK,   16,   16,  ME,  ME)),
	// 防禦增加一點點
	DEFENSE_UP( "icon/defense",                 0x6972C4F3, 125, 125, new OpSt(AS_DEF,   10,   10,  ME,  ME)),
	// 速度增加
	SPEED_UP( "icon/speed",                     0x5EFB585F, 130, 130, new OpSt(AS_SPD,   -2,   -2,  ME,  ME)),
	
	/*
	 * Green Pumpkin
	 */
	// 大爆發, 依被傷害的血量無差別攻擊人
	PAIN_BOMB( "broken_yellow_soul",            0x4FA61DE2, 110, 110, new OpSt(DG_WND,    3,    3, ALL, ALL)),
	
	/*
	 * Bloody Werewolf 
	 */
	// 標準爪擊
	CLAW( "icon/attack",                        0x9358B9A3, 125, 125, new OpSt(DG_ATK, 1250, 1250,   2,   2)),
	// 爪擊/加攻擊力
	POWER_CLAW( "item/broken_red_soul",         0x7DB2B012, 135, 135, new OpSt(DG_ATK, 1250, 1250,   2,   2),
			                                                          new OpSt(AS_ATK,   20,   20,  ME,  ME)),
	// 爪擊/回血
	LEECH_CLAW( "item/broken_yellow_soul",      0x7B79384E, 150, 150, new OpSt(DG_ATK, 1250, 1250,   2,   2),
			                                                          new OpSt(AS_LIF, 1000, 1000,  ME,  ME)),
	
	/*
	 * Snow Yeti
	 */
	// 吹雪:全體小傷害
	SNOWDRIFT( "item/broken_blue_soul",         0xB9D9CD5F, 170, 170, new OpSt(DG_DEF,   50,   50, ALL, ALL),
			                                                          new OpSt(AS_DEF,   25,   25,  ME,  ME)),
	
	/*
	 * Thunder Bird
	 */
	// 風之槍 快速低傷, 減速, 增強其他招式威力
	SPEAR_OF_WIND( "item/broken_green_soul",    0xA51E98D1,  65,  65, new OpSt(DG_ATK,  125,  125,   1,   1),
			                                                          new OpSt(AS_SPD,   -2,   -2,  ME,  ME)),
	// 音波 全體攻擊, 減少生命力
	SONIC_ATTACK( "item/broken_yellow_soul",    0x70E70390, 130, 130, new OpSt(DG_ATK,   85,   85, ALL, ALL),
			                                                          new OpSt(AS_LIR,   -5,   -5,  ME,  ME)),
	// 螺旋羽毛 x10, 減少防禦力
	SPIRAL_FEATHER( "item/broken_blue_soul",    0x57A742B5, 150, 150, new OpSt(DG_ATK,   75,   75,  10,  10),
			                                                          new OpSt(AS_DER,   -5,   -5,  ME,  ME)),
	// 閃電球 x3, 傷害依存速度, 速度回復原狀
	THUNDER_BALL( "item/complete_cyan_soul",    0xB219B473, 300, 300, new OpSt(DG_SPD,25000,25000,   3,   3),
			                                                          new OpSt(AS_SP2,   25,   25,  ME,  ME)),
	// 連鎖閃電 x6, 傷害依存速度, 速度回復原狀
	CHAIN_LIGHTNING( "item/complete_green_soul",0x97AE8A66, 330, 330, new OpSt(DG_SPD,12500,12500,   6,   6),
                                                                      new OpSt(AS_SP2,   25,   25,  ME,  ME)),
	
	/*
	 * Steel Cyclops
	 */
	// 狂毆 大力打二下 (打掉80%血)
	BASH( "item/complete_red_soul",             0xDB8A3EA1, 150, 150, new OpSt(DG_ATK,  766,  766,   2,   2)),
	// 全體攻擊
	STOMP( "item/complete_blue_soul",           0xC46C79D6, 150, 150, new OpSt(DG_DEF,  433,  433, ALL, ALL)),
	// 凝視 8000 ~ 0
	GAZE( "item/complete_black_soul",           0x0BCA69A7, 150, 150, new OpSt(DG_LIF,    5,    5,   1,   1)),
	
	/*
	 * Stone Face
	 */
	// life +100%
	HEAL_100( "item/complete_yellow_soul",      0xEF3D438A, 120, 120, new OpSt(AS_LIR,  100,  100,  ME,  ME)),
	// 降攻
	ATTACK_DOWN( "item/complete_red_soul",      0x7CE8BE01, 100, 100, new OpSt(DB_ATK,  -75,  -75, ALL, ALL)),
	// 降防
	DEFENSE_DOWN( "item/complete_blue_soul",    0x796C6159, 100, 100, new OpSt(DB_DEF,  -50,  -50, ALL, ALL)),
	// 減速
	SPEED_DOWN( "item/complete_green_soul",     0xAA3393A7, 100, 100, new OpSt(DB_SPD,   10,   10, ALL, ALL)),
	// Bubble, 自爆招式
	BUBBLE( "item/complete_black_soul",         0x8FC8699A, 150, 150, new OpSt(DG_FIX, 2500, 2500, ALL, ALL),
			                                                          new OpSt(AS_LI2, 9600, 9600,  ME,  ME)),
	
	/*
	 * Black Mimic
	 */
	// swallow
	SWALLOW( "item/complete_black_soul",        0xD12A11E4,  40,  40, new OpSt(DG_ATK, 1200, 1200,   1,   1)),
	
	/*
	 * dagger
	 */
	WOODEN_DAGGER( "item/wooden_dagger",        0xB9506F7C, 150, 100, new OpSt(DG_ATK,  200,  200,   2,   2)),
	IRON_DAGGER( "item/iron_dagger",            0xFCD413D6, 125, 125, new OpSt(DG_ATK,  220,  280,   2,   2)),
	IRON_DAGGER_1( "item/iron_dagger", "bg_refined",
                                                0xFA1394CA, 140, 140, new OpSt(DG_ATK,  240,  320,   2,   2)),
	WIND_DAGGER( "item/wind_dagger",            0xC5D6209C, 180, 120, new OpSt(DG_ATK,  185,  235,   2,   6)),
	MITHRIL_DAGGER( "item/mithril_dagger",      0xAC55CB93, 150, 150, new OpSt(DG_ATK,  200,  200,   2,   2),
                                                                      new OpSt(AS_LIF,  100,  200,  ME,  ME)),
	SHADOW_DAGGER( "item/shadow_dagger", "bg_cursed",
                                                0xEEEE9AE9, 135, 125, new OpSt(DG_WND,  135,  150,   2,   2),
			                                                          new OpSt(AS_LIR,  -50,  -25,  ME,  ME)),
	YGGDRASIL_DAGGER( "item/yggdrasil_dagger",  0xEBE1C9D5, 150, 100, new OpSt(DG_ATK,  200,  300,   2,   3),
			                                                          new OpSt(AS_LIF,  100,  200,   1,   3)),
	
	/*
	 * sword
	 */
	WOODEN_SWORD( "item/wooden_sword",          0xD1DA6D83, 200, 135, new OpSt(DG_ATK,  350,  350,   1,   1)),
	IRON_SWORD( "item/iron_sword",              0x155E11DD, 160, 160, new OpSt(DG_ATK,  400,  500,   1,   1)),
	IRON_SWORD_1( "item/iron_sword", "bg_refined",
                                                0xFB916ACD, 180, 180, new OpSt(DG_ATK,  450,  600,   1,   1)),
	GREAT_SWORD( "item/great_sword",            0x5690C2C0, 230, 140, new OpSt(DG_ATK,  300,  850,   1,   1)),
	MITHRIL_SWORD( "item/mithril_sword",        0x3BF6A14C, 200, 200, new OpSt(DG_ATK,  350,  350,   1,   1),
			                                                          new OpSt(AS_LIF,  100,  200,  ME,  ME)),
	FIRE_SWORD( "item/fire_sword",              0x08D2F989, 320, 320, new OpSt(DG_ATK,  900, 1250,   1,   1),
			                                                          new OpSt(AS_DER, -100,  -50,  ME,  ME)),
	ICE_SWORD( "item/ice_sword",                0xEDDAE947, 280, 280, new OpSt(DG_ATK,  600,  950,   1,   1),
                                                                      new OpSt(AS_DEF,   20,   40,  ME,  ME)),
	THUNDER_SWORD( "item/thunder_sword",        0x38863E59, 320, 240, new OpSt(DG_ATK,  200,  250,   6,   8)),
	HOLY_SWORD( "item/holy_sword", "bg_blessed",
                                                0x1DCEC245, 500, 400, new OpSt(DG_FIX,    0, 1000, ALL, ALL),
                                                                      new OpSt(AS_LIR,   25,   50,  ME,  ME)),
	SKULL_SWORD( "item/skull_sword", "bg_cursed",
                                                0x15BC3C88, 500, 400, new OpSt(DG_WND,  180,  200,   1,   8),
			                                                          new OpSt(AS_LIR,  -50,  -25,  ME,  ME)),
	YGGDRASIL_SWORD( "item/yggdrasil_sword",    0x59CCC717, 200, 135, new OpSt(DG_ATK,  350,  500,   1,   2),
                                                                      new OpSt(AS_LIF,  100,  200,   1,   3)),
	
	/*
	 * staff
	 */
	WOODEN_STAFF( "item/wooden_staff",          0x3F619D0B, 350, 220, new OpSt(DG_LIF,  120,  120,   1,   1)),
	MITHRIL_STAFF( "item/mithril_staff",        0x9B74E27C, 350, 350, new OpSt(DG_LIF,  120,  120,   1,   1),
			                                                          new OpSt(AS_LIF,  100,  200,  ME,  ME)),
	YGGDRASIL_STAFF( "item/yggdrasil_staff",    0x8109B870, 350, 220, new OpSt(DG_LIF,  120,  200,   1,   2),
                                                                      new OpSt(AS_LIF,  100,  200,   1,   3)),
	
	/*
	 * shield
	 */
	WOODEN_SHIELD( "item/wooden_shield",        0x4BB856C3, 200, 135, new OpSt(AS_GRT,    5,    5,  ME,  ME)),
	IRON_SHIELD( "item/iron_shield",            0x32499E81, 160, 160, new OpSt(AS_GRT,    6,   10,  ME,  ME)),
	IRON_SHIELD_1( "item/iron_shield", "bg_refined",
                                                0x2F2B30A6, 180, 180, new OpSt(AS_GRT,    8,   12,  ME,  ME)),
	TOWER_SHIELD( "item/tower_shield",          0x73C8A16B, 230, 140, new OpSt(AS_GRT,   20,   20,  ME,  ME),
			                                                          new OpSt(AS_SPR,   50,   25,  ME,  ME)),
	MITHRIL_SHIELD( "item/mithril_shield",      0x58F9A9A3, 200, 200, new OpSt(AS_GRT,    5,    5,  ME,  ME),
			                                                          new OpSt(AS_LIF,  100,  200,  ME,  ME)),
	BLACK_SHIELD( "item/black_shield",          0xAF04E9B4, 410, 410, new OpSt(DG_DEF,  750, 1100,   1,   1),
			                                                          new OpSt(AS_SPR,   50,   25,  ME,  ME)),
	WHITE_SHIELD( "item/white_shield",          0x9499BFA7, 280, 210, new OpSt(AS_GRT,    5,   15,  ME,  ME),
			                                                          new OpSt(AS_DEF,   10,   20, ALL, ALL)),
	HOLY_SHIELD( "item/holy_shield", "bg_blessed",
                                                0xD82F00C8, 500, 400, new OpSt(AS_GRT,   15,   30,  ME,  ME),
			                                                          new OpSt(AS_LIR,   25,   50,  ME,  ME)),
	SKULL_SHIELD( "item/skull_shield", "bg_cursed",
                                                0xBDCF9495, 500, 400, new OpSt(DG_DEF,  300, 1100,   1,   3),
			                                                          new OpSt(AS_LIR,  -50,  -25,  ME,  ME)),
	YGGDRASIL_SHIELD( "item/yggdrasil_shield",  0xBADA3F35, 200, 135, new OpSt(AS_GRT,    5,   20,  ME,  ME),
			                                                          new OpSt(AS_LIF,  100,  200,   1,   3)),

	/*
	 * ring
	 */
	WOODEN_RING( "item/wooden_ring",            0x8B67CA6F, 150, 100, new OpSt(AS_LIF,  100,  500,   1,   1)),
	IRON_RING( "item/iron_ring",                0x712B97C9, 125, 125, new OpSt(AS_ATK,   15,   30,  ME,  ME),
			                                                          new OpSt(AS_DEF,   15,   30,  ME,  ME)),
	IRON_RING_1( "item/iron_ring", "bg_refined",
                                                0xB6678B8A, 140, 140, new OpSt(AS_ATK,   20,   40,  ME,  ME),
			                                                          new OpSt(AS_DEF,   20,   40,  ME,  ME)),
	YELLOW_RING( "item/yellow_ring",            0x9C2B58E5, 180, 180, new OpSt(AS_LIR,   25,   50,   2,   4)),
	RED_RING( "item/red_ring",                  0xDF221709, 180, 180, new OpSt(AS_ATK,   20,   40,   2,   4)),
	BLUE_RING( "item/blue_ring",                0xCB4BD892, 180, 180, new OpSt(AS_DEF,   20,   40,   2,   4)),
	GREEN_RING( "item/green_ring",              0x15D9D305, 180, 180, new OpSt(AS_SPD,   -2,   -4,   2,   4)),
	BLACK_RING( "item/black_ring", "bg_cursed",
                                                0xF64B99AE, 360, 360, new OpSt(DG_WND,  120,  150,   2,   4)),
	WHITE_RING( "item/white_ring",              0xD931C938, 360, 360, new OpSt(DG_FIX,  300,  500, ALL, ALL)),
	RING_OF_GODDESS( "item/ring_of_goddess", "bg_blessed",
                                                0xBFE64A83, 500, 400, new OpSt(DG_FIX,  400,  800, ALL, ALL),
			                                                          new OpSt(AS_LIR,   25,   50,  ME,  ME)),
	SKULL_RING( "item/skull_ring", "bg_cursed",
                                                0x02502272, 500, 400, new OpSt(DG_WND,  180,  200,   3,   6),
			                                                          new OpSt(AS_LIR,  -50,  -25,  ME,  ME)),
	YGGDRASIL_RING( "item/yggdrasil_ring",      0xEA592569, 150, 100, new OpSt(AS_LIF,  100,  200,   1,   3),
			                                                          new OpSt(AS_RER,   50,  100,   1,   2)),
	RING_OF_LIFE( "item/ring_of_life",          0xE67ED9F2, 999, 999, new OpSt(AS_LI2,  800, 2000, ALL, ALL)),
	RING_OF_ATTACK( "item/ring_of_attack",      0x2A027E4C, 999, 999, new OpSt(AS_ATR,   20,   40, ALL, ALL)),
	RING_OF_DEFENSE( "item/ring_of_defense",    0x107C4A56, 999, 999, new OpSt(AS_DER,   20,   40, ALL, ALL)),
	RING_OF_SPEED( "item/ring_of_speed",        0x53FFEEB0, 999, 999, new OpSt(AS_QUK,  -25,  -50, ALL, ALL)),
	
	/*
	 * boots
	 */
	IRON_BOOTS( "item/iron_boots",              0x50B2896E, 110, 110, new OpSt(DG_SPD, 2700, 3100,   3,   5)),
	IRON_BOOTS_1( "item/iron_boots", "bg_refined",
                                                0x176981AA, 125, 125, new OpSt(DG_SPD, 2900, 3300,   3,   5)),
	MITHRIL_BOOTS( "item/mithril_boots",        0xFE182408, 160, 160, new OpSt(DG_SPD, 2800, 2800,   3,   5),
			                                                          new OpSt(AS_LIF,  100,  200,  ME,  ME)),
	WIND_BOOTS( "item/wind_boots",              0xFB28AD95, 320, 240, new OpSt(DG_SPD, 3200, 4000, ALL, ALL)),
	
	/*
	 * none
	 */
	NONE( "", 0x3E488434, 0, 0);

	// operation2 num
	public static final int MAX_OPERATIONS = 2;

	// operation set
	private static class OpSt {
		final int type;
		final int valueMin;
		final int valueMax;
		final int targetMin;
		final int targetMax;
		OpSt(int type, int valueMin, int valueMax, int targetMin, int targetMax) {
			this.type = type;
			this.valueMin = valueMin;
			this.valueMax = valueMax;
			this.targetMin = targetMin;
			this.targetMax = targetMax;
		}
	}
	
	/*
	 * constructor
	 */
	UniqueSkill(String icon, int code, int coolDownMin, int coolDownMax, OpSt... operationSets) {
		this.icon = icon;
		this.bg = "";
		this.code = code;
		this.coolDownMin = coolDownMin;
		this.coolDownMax = coolDownMax;
		this.operationSets = operationSets;
	}

	UniqueSkill(String icon, String bg, int code, int coolDownMin, int coolDownMax, OpSt... operationSets) {
        this.icon = icon;
        this.bg = bg;
        this.code = code;
        this.coolDownMin = coolDownMin;
        this.coolDownMax = coolDownMax;
        this.operationSets = operationSets;
    }
	
	// Icon
	public final String icon;	// 必殺技的圖示
	public final String bg;		// 必殺技的背景
	public final int code;
	
	/*
	 * Cool Down
	 * use interpolation (內差法)
	 */
	public final int coolDownMin;
	public final int coolDownMax;

	public final OpSt[] operationSets;
	
	/*
	 * find
	 */
	public static UniqueSkill find(final int code) {
		for (UniqueSkill us : UniqueSkill.values())
			if (us.code == code)
				return us;
		throw new RuntimeException("invalid code : " + code);
	}
	
	/*
	 * get Action Data
	 */
	public UniqueSkillData get(final int mastery) {
		int coolDown = coolDownMin + (coolDownMax - coolDownMin) * mastery / 100;
		
		final int size = operationSets.length;
		Operation2[] opts = new Operation2[size];
		
		for (int i = 0; i < size; ++i) {
			opts[i] = new Operation2();
			opts[i].type = operationSets[i].type;
			opts[i].value = operationSets[i].valueMin + (operationSets[i].valueMax - operationSets[i].valueMin) * mastery / 100;
			opts[i].target = (operationSets[i].targetMin == Operation2.ALL)
					? Operation2.ALL
					: operationSets[i].targetMin + (operationSets[i].targetMax - operationSets[i].targetMin) * mastery / 100;
		}
		
		// check auto.
		final boolean isAuto = 
				this == WOODEN_DAGGER || 
				this == WOODEN_SWORD ||
				this == WOODEN_STAFF ||
				this == WOODEN_SHIELD ||
				this == WOODEN_RING ||
				this == YGGDRASIL_DAGGER ||
				this == YGGDRASIL_SWORD ||
				this == YGGDRASIL_STAFF ||
				this == YGGDRASIL_SHIELD ||
				this == YGGDRASIL_RING;
		
		return new UniqueSkillData(this, coolDown, isAuto, opts);
	}
	
}
