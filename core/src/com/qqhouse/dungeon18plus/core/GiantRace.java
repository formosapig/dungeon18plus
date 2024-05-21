package com.qqhouse.dungeon18plus.core;

import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;

import java.util.Random;

// XXX 0422 下一個版本,有連線對戰時,再來新增一團的怪啦,只打一隻怎麼會爽.
public enum GiantRace {
	
	/*
	 * Rank 1 :
	 *   H-Life : 1500
	 *   H-Attack : 200
	 *   H-Defense : 200
	 *   H-Speed : 8
	 *   H-DPS100 : 500
	 *   
	 *   Game-Life : H-DPS100 * 10 * 8
	 *   Game-Attack : H-Life * 2 * Game-Speed / 1000 + H-Defense = Game-Speed * 3 + H-Defense
	 *   Game-Defense : H-Attack
	 *   Game-Speed : setting.
	 *   
	 *   
	 * Rank 2 :
	 *   H-Life : 2000
	 *   H-Attack : 250
	 *   H-Defense : 250
	 *   H-Speed : 7
	 *   H-DPS100 : 1000
	 *   
	 *   Game-Life : Game-DPS100 * 10 * 8 ~ 80,000
	 *   Game-Attack : H-Life * 3 * Game-Speed / 1000 + H-Defense = Game-Speed * 6 + H-Defense
	 *   Game-Defense : H-Attack
	 *   Game-Speed : setting.
	 *   
	 * Rank 3 :
	 *   H-Life : 2500
	 *   H-Attack : 300
	 *   H-Defense : 300
	 *   H-Speed : 6
	 *   H-DPS100 : 2000
	 * 
	 *   Game-Life : Game-DPS100 * 10 * 8 ~ 160,000
	 *   Game-Attack : H-Life * 4 * Game-Speed / 1000 + H-Defense = Game-Speed * 10 + H-Defense
	 *   Game-Defense : H-Attack
	 *   Game-Speed : setting.
	 * 
	 * 
	 * Rank 1 : (各種各樣能力,低階石頭,負作用一堆)
	 *   # 以下二擇
	 *   # black slime : [NECOIN, NELIFE, NEATTACK, UNLOCKY]
	 *   # red slime : [NESTAR, NEDEFENSE, NESPEED, UNLOCKY]
	 * 
	 * Rank 2 : (各種能力,沒有負作用的石頭,通常只能合成一階,開始有複合能力的石頭)
	 *   # skeleton fighter : [PROTECT, CHARGE]
	 *   # 以下四擇
	 *   # green pumpkin : [LIFE, FULL_LIFE]
	 *   # red werewolf : [ATTACK, BERSERKER]
	 *   # snow yeti : [DEFENSE, HARD_DEFENSE]
	 *   # green bird : [SPEED, RUNAWAY]
	 * 
	 * Rank 3 : 
	 *   # 以下三擇
	 *   # steel cyclops : [STAR...] 
	 *   # stone face : [COIN]
	 *   # black mimic : [KEY]
	 *
	 * 1. life 很長,一兩次打不完.打完後需要時間修整才能再打.
	 * 2. 限時,避免時間拖太久.
	 * 
	 * 
	 */
	
	
	/*
	 * black slime
	 *   description : 動作慢，普攻強.
	 *   drop : NEGATIVE_LUCKY, NEGATIVE_COIN, NEGATIVE_LIFE, NEGATIVE_DEFENSE, SMALL_BAG
	 *   action :
	 *       @ HEAL_25 : life < 50% -> 100% use
	 *       @ BLACK_MUD : time < 500 -> 100% use
	 *   
	 */
	BLACK_SLIME(0x07A9CDE9, "blockee_black_slime", "black_slime", "black_slime_giant_help",
		new Ability(36500, 365, 0, 1, 200, 55), Soul.NEGATIVE_LUCKY, Soul.NEGATIVE_COIN, Soul.NEGATIVE_LIFE, Soul.NEGATIVE_DEFENSE, Soul.SMALL_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			// HEAL_25
			if (life * 2 <= this.attr.life)
				return UniqueSkill.HEAL_25.get(100);
			
			// BLACK_MUD
			if (500 >= time)
				return UniqueSkill.BLACK_MUD.get(100);
			
			return null;
		}
	},
		
	/*
	 * red slime
	 *   description : 攻擊快且弱，[重擊 Life90%]，[亂打10下]，[憤怒加攻速]
	 *   drop : NEGATIVE_LUCKY, NEGATIVE_STAR, NEGATIVE_ATTACK, NEGATIVE_SPEED, SMALL_BAG
	 *   action :
	 *       @ QUICK_10 : time 999 - 900 之間都沒有使用絕招的話, 在 time = 900 時 必定使用.
	 *       @ ANGRY : life < 30%, time = 67n 時 100% use
	 *       @ HEAVY_STRIKE : time = 25n 時 25% use
	 *       
	 */
	RED_SLIME(0x7F63C412, "blockee_red_slime", "red_slime", "red_slime_giant_help",
		new Ability(42000, 275, 0, 1, 200, 25), Soul.NEGATIVE_LUCKY, Soul.NEGATIVE_STAR, Soul.NEGATIVE_ATTACK, Soul.NEGATIVE_SPEED, Soul.SMALL_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			// QUICK_10
			if (899 == time)
				return UniqueSkill.QUICK_10.get(100);
			
			// ANGRY
			if (life < attr.life * 0.3 && ((time % 67) == 0))
				return UniqueSkill.ANGRY.get(100);

			// HEAVY_STRIKE
			if (((time % 25) == 0) && 25 > new Random().nextInt(100))
				return UniqueSkill.HEAVY_STRIKE.get(100);
			
			return null;
		}
	},
				
	/*
	 * skeleton fighter :
	 *   description : 只會快速的單體攻擊，[增加攻擊力]，[增加防禦力]一直亂用。
	 *   drop : PROTECT, CHARGE, NEGATIVE_LIFE, NEGATIVE_ATTACK, NEGATIVE_DEFENSE, NEGATIVE_SPEED, SMALL_BAG, SMALL_BAG
	 *   action :
	 *     @ ATTACK_UP, DEFENSE_UP, SPEED_UP : time = 165n use ATTACK_UP or DEFENSE_UP or SPEED_UP
	 */
	SKELETON_FIGHTER(0x3E4FED1E, "blockee_skeleton_fighter", "skeleton_fighter", "skeleton_fighter_giant_help",
		new Ability(80000, 298, 0, 1, 250, 8), Soul.PROTECT, Soul.CHARGE, Soul.NEGATIVE_LIFE, Soul.NEGATIVE_ATTACK,
		Soul.NEGATIVE_DEFENSE, Soul.NEGATIVE_SPEED, Soul.SMALL_BAG, Soul.SMALL_BAG) 
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			
			if (0 == time % 165) {
				final int seed = new Random().nextInt(3);
				if (0 == seed)
					return UniqueSkill.ATTACK_UP.get(100);
				else if (1 == seed)
					return UniqueSkill.DEFENSE_UP.get(100);
				else
					return UniqueSkill.SPEED_UP.get(100);
			}
			
			return null;
		}
	},
		
	/*
	 * green pumpkin
	 *   description : 血很長(LIFE:150%), 不會打人(SPD:1000, ATK:0), 快到時間間放大絕而已 [大爆發]
	 *   drop : LIFE, BIG_BAG, LONG_LIFE
	 *   action : 
	 *     @ PAIN_BOMB : time = 250 -> 100% use (250 - 110) 在 140 時放. 
	 */
	GREEN_PUMPKIN(0x043AFC43, "blockee_green_pumpkin", "green_pumpkin", "green_pumpkin_giant_help",
		new Ability(120000, 0, 0, 1, 250, 1000), Soul.LIFE, Soul.BIG_BAG, Soul.LONG_LIFE)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			if (250 == time)
				return UniqueSkill.PAIN_BOMB.get(100);
			
			return null;
		}
	},
		
	/*
	 * bloody werewolf 
	 *   description : 技能攻擊為主,血量略少,有三種不同爪擊.[雙爪][雙爪+攻][雙爪+血]
	 *   drop : ATTACK, BERSERK, SMALL_BAG
	 *   action : 不斷使用
	 *     @ CLAW : 50%
	 *     @ POWER_CLAW : 25%
	 *     @ LEECH_CLAW : 25% 
	 */
	BLOODY_WEREWOLF(0xE90E15AC, "blockee_bloody_werewolf", "bloody_werewolf", "bloody_werewolf_giant_help",
		new Ability(64000, 100, 0, 1, 250, 10), Soul.ATTACK, Soul.BERSERK, Soul.SMALL_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			final int seed = new Random().nextInt(100);
			
			if (25 > seed)
				return UniqueSkill.CLAW.get(100);
			else if (50 > seed)
				return UniqueSkill.POWER_CLAW.get(100);
			else
				return UniqueSkill.LEECH_CLAW.get(100);
		}
	},

	/*
	 * blue yeti
	 *   description : 防極高(D:200%) [吹雪DMGxA]
	 *   drop : DEFENSE, HARD_DEFENSE, MEDIUM_BAG, SMALL_BAG
	 *   action :
	 *     @ SNOWDRIT : time = 175n -> 100% use 
	 */
	BLUE_YETI(0xE562C19C, "blockee_blue_yeti", "blue_yeti", "blue_yeti_giant_help",
		new Ability(80000, 292, 0, 1, 500, 42), Soul.DEFENSE, Soul.HARD_DEFENSE,
		Soul.MEDIUM_BAG, Soul.SMALL_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			if (time % 175 == 0)
				return UniqueSkill.SNOWDRIFT.get(100);
			return null;
		}
	},

	/*
	 * thunder bird
	 *   description : 標準能力, 技能雜亂, 三種技能-spd 大招基於 spd ,放出大招後回復成原來的 spd
	 *   drop : SPEED, RUNAWAY, MEDIUM_BAG
	 *   action :
	 *     @ THUNDER_BALL, CHAIN_LIGHTNING : life < 50 , 100% use.
	 *     @ SPEAR_OF_WIND : 90% use
	 *     @ SONIC_ATTACK : 5% use
	 *     @ SPIRAL_FEATHER : 5% use
	 */
	THUNDER_BIRD(0x177896AA, "blockee_thunder_bird", "thunder_bird", "thunder_bird_giant_help",
		new Ability(80000, 400, 0, 1, 250, 25), Soul.SPEED, Soul.RUNAWAY, Soul.MEDIUM_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			final Random mRand = new Random();
			
			if (life * 2 < this.attr.life)
				return mRand.nextBoolean()
					? UniqueSkill.THUNDER_BALL.get(100)
					: UniqueSkill.CHAIN_LIGHTNING.get(100);
			
			final int seed = mRand.nextInt(100);
			if (5 > seed)
				return UniqueSkill.SPIRAL_FEATHER.get(100);
			else if (10 > seed)
				return UniqueSkill.SONIC_ATTACK.get(100);
			else
				return UniqueSkill.SPEAR_OF_WIND.get(100);
		}
	},

	/*
	 * steel cyclops :
	 *   description : 基本屬性強大,各種大招可以放
	 *   drop : BRAVE, STAR, LONG_LIFE, BERSERK, NEGATIVE_STAR, NEGATIVE_DEFENSE, NEGATIVE_SPEED,
	 *          BIG_BAG, MEDIUM_BAG, SMALL_BAG
	 *   action : 
	 *   
	 */
	STEEL_CYCLOPS(0x3B6076AB, "blockee_steel_cyclops", "steel_cyclops", "steel_cyclops_giant_help",
		new Ability(160000, 300, 0, 1, 300, 150), Soul.BRAVE, Soul.STAR, Soul.LONG_LIFE, Soul.BERSERK,
		Soul.NEGATIVE_STAR, Soul.NEGATIVE_DEFENSE, Soul.NEGATIVE_SPEED, Soul.BIG_BAG, Soul.MEDIUM_BAG, Soul.SMALL_BAG) 
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			final int seed = new Random().nextInt(100);
			
			if (4 > seed)
				return UniqueSkill.GAZE.get(100);
			else if (52 > seed)
				return UniqueSkill.STOMP.get(100);
			else
				return UniqueSkill.BASH.get(100);
		}
	},
		
	/*
	 * stone face (x20) : 
	 *   description : 純普攻,但會一堆 debuff, 有時會全體攻擊, 要強化 buffer 才能打
	 *   drop : DREAM, COIN, LONG_LIFE, BERSERK, HARD_DEFENSE, NEGATIVE_COIN, NEGATIVE_SPEED, MEDIUM_BAG
	 *          SMALL_BAG
	 *   action :
	 *     @ HEAL_100 : life < 10% -> 100% use
	 *     @ BUBBLE : time = 200 5% use
	 *     @ ATTACK_DOWN : time = 110n 
	 *     @ DEFENSE_DOWN : time = 110n
	 *     @ SPEED_DOWN : time = 110n
	 */
    STONE_FACE(0x5ED6867B, "blockee_stone_face", "stone_face", "stone_face_giant_help",
		new Ability(96000, 300, 0, 1, 300, 25), Soul.DREAM, Soul.COIN, Soul.LONG_LIFE, Soul.BERSERK,
		Soul.HARD_DEFENSE, Soul.NEGATIVE_COIN, Soul.NEGATIVE_SPEED, Soul.MEDIUM_BAG, Soul.SMALL_BAG) 
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			if (life * 10 < this.attr.life)
				return UniqueSkill.HEAL_100.get(100);
			
			final Random mRand = new Random();
			
			if (0 == time % 200 && 5 > mRand.nextInt(100))
				return UniqueSkill.BUBBLE.get(100);
			
			if (0 == time % 110) {
				final int seed = mRand.nextInt(3);
				
				if (0 == seed)
					return UniqueSkill.ATTACK_DOWN.get(100);
				else if (1 == seed)
					return UniqueSkill.DEFENSE_DOWN.get(100);
				else
					return UniqueSkill.SPEED_DOWN.get(100);
			}
			
			return null;
		}
	},
		
	/*
	 * black mimic :
	 *   description : 血不多,血條也很少,攻擊強到靠背,可能順間死光.不是秒它就是被秒殺.
	 *   drop : HOPE, KEY, HUGE_BAG
	 *   action :
	 *     @SWALLOW : just use, use, use.... 
	 */
	BLACK_MIMIC(0x571C10A0, "blockee_black_mimic", "black_mimic", "black_mimic_giant_help",
		new Ability(32000, 250, 0, 1, 500, 100), Soul.HOPE, Soul.KEY, Soul.HUGE_BAG)
	{
		@Override
		public UniqueSkillData getAction(int time, int life) {
			return UniqueSkill.SWALLOW.get(100);
		}
	};
	
	// identify
	public final int code;
	
	// res
	public final String icon;
	public final String name;
	public final String help;
	
	// attr
	public final Ability attr;

	// actions
	public final Soul[] drops;
	
	GiantRace(int code, String icon, String name, String help, Ability attr, Soul... drops) {
		this.code = code;
		this.icon = icon;
		this.name = name;
		this.help = help;
		this.attr = attr;
		this.drops = drops;
	}
	
	public abstract UniqueSkillData getAction(int time, int life);
	
	public static GiantRace find(int code) {
		for (GiantRace race : GiantRace.values()) {
			if (code == race.code)
				return race;
		}
		throw new RuntimeException("invalid GiantRace id : " + code);
	}
}
