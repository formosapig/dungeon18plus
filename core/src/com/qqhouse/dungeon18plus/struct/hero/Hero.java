package com.qqhouse.dungeon18plus.struct.hero;

import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.EventResult;
import com.qqhouse.dungeon18plus.struct.Fightable;
import com.qqhouse.dungeon18plus.struct.Varier;

import java.util.ArrayList;
import java.util.Locale;

import static com.qqhouse.dungeon18plus.struct.Varier.Type.ATTACK;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.COIN;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.COMBO;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.DAMAGE;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.DEFENSE;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.KEY;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.LIFE;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.NONE;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.SOUL;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.SPEED;
import static com.qqhouse.dungeon18plus.struct.Varier.Type.STAR;

// XXX feat 也采用熟練度感覺不錯嗎? 不好, 可能用等級制會好一點.
// XXX feat 設定的限制? 方案 1. 最大等級 方案.  2. 最大分數. 3. 其他限制的方式
public class Hero extends Ability implements Fightable {

	public HeroClass heroClass;

	// resource
	public int key;
	public int coin;
	public int star;
	public int soul;
	
	// cap
	//public int maxLife;
    //public int maxAttack;
    //public int maxDefense;
    //public int minSpeed;
	
	//public ArrayList<Action> actions;
	public long feats;

	// buffer series, maybe potion or spell
	private ArrayList<Varier> mBattleBuffer = new ArrayList<>();

	private Ability mLimit;
	private Ability mBody;

	/*
	 * setup initial status
	 */
	public void buildUp(int life, int attack, int defense, int speed, int key, int coin, int star) {
	    mBody = new Ability();
		this.mBody.life = life;
		this.mBody.attack = attack;
		this.mBody.defense = defense;
		this.mBody.speed = speed;
		this.key = key;
		this.coin = coin;
		this.star = star;
		calculateBuffer();
	}

	public void setLimit(int life, int attack, int defense, int speed) {
	    mLimit = new Ability(life, attack, 0, 0, defense, speed);
    }

	// get limit
    public Ability getLimit() { return mLimit; }

	// get body
    public Ability getBody() {
	    return mBody;
    }

	/*
	 * cost series...
	 */
	// TODO cost 並不僅僅只是單純的減一個值, 也有可能是減半之類的, 需要再加強.
	public boolean canPayCost(Varier cost) {
        switch(cost.getPureType()) {
            case NONE:   return true;
            case STAR:   return star >= cost.value;
            case SOUL:   return soul >= cost.value;
            case KEY:    return key >= cost.value;
            case COIN:   return coin >= cost.value;
            case DAMAGE: return life > cost.value;
            default:
                throw new RuntimeException("unhandle cost type : " + cost.type);
        }
    }

    public void payCost(Varier cost) {
		switch (cost.getPureType()) {
            case NONE:
                break;
            case STAR:
                this.star -= cost.value;
                break;
            case SOUL:
                this.soul -= cost.value;
                break;
            case KEY:
                this.key -= cost.value;
                break;
            case COIN:
                this.coin -= cost.value;
                break;
            case DAMAGE:
                this.life -= cost.value;
                break;
            default:
                throw new RuntimeException("unhandle cost type : " + cost.type);
        }
    }

    // return false if no ability can upgrade...
    public boolean canUpgradeAbility(Varier[] upgrades) {
	    if (0 == upgrades.length) {
            return true;
        }

        Ability tempBody = new Ability(mBody);

	    for (Varier var : upgrades) {
            applyVarier(tempBody, mBody, var, true, null);
        }

        return !tempBody.equals(mBody);
    }

    public void upgradeAbility(Varier var, EventResult result) {
	    if (var.isLimit()) {
            applyVarier(mLimit, mLimit, var, false, null);
        } else {
            applyVarier(mBody, mBody, var, true, result);
	    }
        calculateBuffer();
    }

	// always can apply until max status.
	// TODO remove EventResult, use other ....
	private void applyVarier(Ability target, Ability source, Varier var, boolean checkLimit, EventResult result) {
	    //Log.e(Game.Debug.TAG, String.format(Locale.US, "limit = %s", mLimit));
		switch (var.getPureType()) {
			case LIFE: {
				int change = 0;
				// base calculate
				if (var.isOffset()) {
					change = var.value;
				} else if (var.isPercent()) {
					change = source.life * var.value / 100;
				} else if (var.isSet()) {
					change = var.value - source.life;
				}
				// limit check
				if (0 > change && (source.life + change) < 1) {
					change = 1 - source.life;
				}
				if (checkLimit && (0 < change) && (mLimit.life - source.life) < change) {
					change = mLimit.life - source.life;
				}
				// event result
				if (null != result) {
					result.life += change;
				}
				target.life += change;
            }
			break;
			case ATTACK: {
				int change = 0;
				// base calculate
				if (var.isOffset()) {
					change = var.value;
				} else if (var.isPercent()) {
					change = source.attack * var.value / 100;
				} else if (var.isSet()) {
					change = var.value - source.attack;
				}
				// limit check
				if (0 > change && (source.attack + change) < 1) {
					change = -source.attack;
				}
				if (checkLimit && (change > 0) && (mLimit.attack - source.attack) < change) {
					change = mLimit.attack - source.attack;
				}
				// event result
				if (null != result) {
					result.attack += change;
				}
				target.attack += change;
			}
			break;
            case COMBO: {
                int change = 0;
                // base calculate
                if (var.isOffset()) {
                    change = var.value;
                } else if (var.isPercent()) {
                    change = source.combo * var.value / 100;
                } else if (var.isSet()) {
                    change = var.value - source.combo;
                }
                // limit check
                if (change < 0 && (source.combo + change) < 0) {
                    change = -source.combo;
                }
                // event result
                // TODO not apply yet.
//                if (null != result) {
//                    result.combo = change;
//                }
                target.combo += change;
            }
            break;
			case DEFENSE: {
				int change = 0;
				// base calculate
				if (var.isOffset()) {
					change = var.value;
				} else if (var.isPercent()) {
					change = source.defense * var.value / 100;
				} else if (var.isSet()) {
					change = var.value - source.defense;
				}
				// limit check
				if (0 > change && (source.defense + change) < 1) {
					change = -source.defense;
				}
				if (checkLimit && (change > 0) && (mLimit.defense - source.defense) < change) {
					change = mLimit.defense - source.defense;
				}
				// event result
				if (null != result) {
					result.defense += change;
				}
				target.defense += change;
			}
			break;
			case SPEED: {
				int change = 0;
				// base calculate
				if (var.isOffset()) {
					change = var.value;
				} else if (var.isPercent()) {
					change = source.speed * var.value / 100;
				} else if (var.isSet()) {
					change = var.value - source.speed;
				}
				// limit check
				if (checkLimit && (change < 0) && (mLimit.speed - source.speed) > change) {
					change = mLimit.speed - source.speed;
				}
				// global speed limit
                if ((change < 0) && (Game.Setting.GLOBAL_HERO_MIN_SPEED - source.speed) > change) {
                    change = Game.Setting.GLOBAL_HERO_MIN_SPEED - source.speed;
                }
				// event result
				if (null != result) {
					result.speed += change;
				}
				target.speed += change;
			}
			break;
			case KEY: {
				int change = var.value;
				// base calculate
				key += change;
				//if (result != null) {
				//	result.key += change;
				//}
			}
			break;
            case COIN: {
                int change = var.value;
                // base calculate
                coin += change;
                if (result != null) {
                    result.coin += change;
                }
            }
            break;
            case STAR: {
                int change = var.value;
                // base calculate
                star += change;
                if (result != null) {
                    result.star += change;
                }
            }
            break;
			default:
				throw new RuntimeException(String.format(Locale.US, "unhandled type : %08X", var.type));
		}
	}

	/*
	 * skill action
	 *
	 */
	public boolean canUseSkillAction() {return false;}

	/*
	 * apply buffer
	 */
	public void addBattleBuffer(Varier buffer) {
        mBattleBuffer.add(buffer);
        calculateBuffer();
	}

	public void clearBattleBuffer() {
	    mPotionResistance = false;
	    mBattleBuffer.clear();
	    calculateBuffer();
    }

	// apply buffer ...
	private void calculateBuffer() {
		this.copy(this.mBody);
		// apply buffer ...
        for (Varier var : mBattleBuffer) {
            applyVarier(this, mBody, var, false, null);
        }
	}

	/*
	 * display series
	 */
	public String displayLife() {
	    String result = String.format(Locale.US, "%d%s", this.life, (this.life >= mLimit.life ? "!" : ""));
	    //if (mBody.life != this.life) {
	    //    result += String.format(Locale.US, "%n(%+d)", (this.life - this.mBody.life));
        //}
	    return result;
	}

	public String displayLifeBuffer() {
        if (mBody.life != this.life) {
            return String.format(Locale.US, "(%+d)", (this.life - this.mBody.life));
        } else {
            return "";
        }
    }


	public String displayAttack() {
		String result = String.format(Locale.US, "%d%s",
				this.attack,
				(this.attack >= mLimit.attack ? "!" : ""));
		// combo
		if (0 < this.combo) {
			result += String.format(Locale.US, "x%d", (this.combo + 1));
		}
		return result;
	}

	public String displayAttackBuffer() {
        // buffer
        if (mBody.attack != this.attack) {
            return String.format(Locale.US, "(%+d)", (this.attack - this.mBody.attack));
        } else {
            return "";
        }
    }

	public String displayDefense() {
		return String.format(Locale.US, "%d%s", this.defense, (this.defense >= mLimit.defense ? "!" : ""));
	}

	public String displayDefenseBuffer() {
        if (mBody.defense != this.defense) {
            return String.format(Locale.US, "(%+d)", (this.defense - this.mBody.defense));
        } else {
            return "";
        }
    }

	public String displaySpeed() {
		return String.format(Locale.US, "%d%s", this.speed, (this.speed <= mLimit.speed ? "!" : ""));
	}

	public String displaySpeedBuffer() {
        if (mBody.speed != this.speed) {
            return String.format(Locale.US, "(%+d)", (this.speed - this.mBody.speed));
        } else {
            return "";
        }
    }

	// potion resist

	private boolean mPotionResistance;

	public boolean potionResistance() {
	    return mPotionResistance;
    }

    public void drinkPotionWithBuffers(Varier[] buffer) {
	    mPotionResistance = true;
	    for (Varier var : buffer)
	        addBattleBuffer(var);
    }

    @Override
    public Ability getAbility() {
        return this;
    }
}
