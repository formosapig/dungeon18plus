package com.qqhouse.dungeon18plus.core;


import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.EventResult;
import com.qqhouse.dungeon18plus.struct.Fightable;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.dungeon18plus.struct.Varier;
import com.qqhouse.dungeon18plus.struct.hero.Hero;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static com.qqhouse.dungeon18plus.core.Action.Type.POTION;
import static com.qqhouse.dungeon18plus.core.Action.Type.SKILL;
import static com.qqhouse.dungeon18plus.core.Action.Type.UPGRADE;

class GameManager<H extends Hero> /*implements HeroActionAdapter.ActionSlotSource*/ {

    // random
    final Random mRandom = new Random();
    
    // constructor
    GameManager() {}

    /*
     * member ...
     */
    protected H mHero;
    protected ArrayList<ActionSlot> mActionSlots;

    /*
     * mHero series
     */
    public H getHero() {
        return mHero;
    }

    protected void buildUpHero(HeroClass heroClass, HeroClassRecord record) {
        mHero.heroClass = heroClass;

        mHero.buildUp(
			heroClass.startLife,
			heroClass.startAttack,
			heroClass.startDefense,
			heroClass.startSpeed,
			heroClass.startKey,
			heroClass.startCoin,
			heroClass.startStar);

		// limit
		int min = (null != record) ? record.getMinLifeLimit() : heroClass.minLifeLimit;
		int max = (null != record) ? record.getMaxLifeLimit() : heroClass.maxLifeLimit;
        int maxLife = createMaxValue(min, max, 50);
		min = (null != record) ? record.getMinAttackLimit() : heroClass.minAttackLimit;
		max = (null != record) ? record.getMaxAttackLimit() : heroClass.maxAttackLimit;
        int maxAttack = createMaxValue(min, max, 5);
		min = (null != record) ? record.getMinDefenseLimit() : heroClass.minDefenseLimit;
		max = (null != record) ? record.getMaxDefenseLimit() : heroClass.maxDefenseLimit;
        int maxDefense = createMaxValue(min, max, 5);
		if (HeroClass.CRUSADER == heroClass) {
			if (maxDefense < maxAttack)
                maxAttack = maxDefense;
			else
                maxDefense = maxAttack;
		}
		min = (null != record) ? record.getMinSpeedLimit() : heroClass.minSpeedLimit;
		max = (null != record) ? record.getMaxSpeedLimit() : heroClass.maxSpeedLimit;
        if (max < min)
            max = min;
        int minSpeed = createMaxValue(min, max, 1);
        mHero.setLimit(maxLife, maxAttack, maxDefense, minSpeed);

        // apply soul effect
        if (null != record && !record.souls.isEmpty()) {
            applySoulBonus(record);
        }

        // actions
        //mHero.actions = new ArrayList<>(Arrays.asList(heroClass.actions));

        mHero.feats = heroClass.feat;

	}

	protected void setupActionSlot(HeroClass heroClass) {
        mActionSlots = new ArrayList<>();
        for (Action act : heroClass.actions) {
            mActionSlots.add(new ActionSlot(act));
        }
    }

    // TODO 暫時先這樣寫,日後要改良
    // 兩個陣列是 mapping 的, 上面的 loot 要對照下面的 action
    private final Item[] specialLoots = new Item[] {Item.YELLOW_POTION, Item.RED_POTION, Item.BLUE_POTION, Item.GREEN_POTION, Item.CYAN_POTION};
    private final Action[] specialActions = new Action[] {Action.LIFE_POTION, Action.ATTACK_POTION, Action.DEFENSE_POTION, Action.SPEED_POTION, Action.CYAN_POTION};

    // for special potion only ...
    protected void setupActionSlotOfSpecialItem() {
        for (int i = 0; i < specialLoots.length; ++i) {
            int count = 0;//GameData.getInstance().getSpecialItemCount(specialLoots[i]);
            if (count > 0) {
                mActionSlots.add(new ActionSlot(specialActions[i], count));
            }
        }
    }

    // drink special potion ...
    private void drinkSpecialPotion(Action act) {
        for (int i = 0; i < specialActions.length; ++i) {
            if (act == specialActions[i]) {
                //GameData.getInstance().setSpecialItem(specialLoots[i], -1);
            }
        }
    }


    private void applySoulBonus(HeroClassRecord record) {
        for (SoulCount sc : record.souls) {
            for (Varier var : sc.soul.getInfluence(sc.count)) {
                mHero.upgradeAbility(var, null);
            }
        }
    }
    
    private int createMaxValue(int min, int max, int range) {
		final int rangeCount = ((max - min) / range);
		return min + mRandom.nextInt(rangeCount  + 1) * range;
    }

    public boolean canDoAction(int index) {
        return canDoAction(this.mActionSlots.get(index));
    }


    public boolean canDoAction(ActionSlot slot) {
        final Action act = slot.action;
        switch (act.type) {
            case UPGRADE:
                return mHero.canPayCost(act.cost) && mHero.canUpgradeAbility(act.effects);
            case SKILL:
                return mHero.canPayCost(act.cost) && mHero.canUseSkillAction();
            case POTION:
                // speed check. 等於 8 也不用喝了.
                if (act == Action.SPEED_POTION && mHero.getBody().speed <= 8) {
                    return false;
                }
                // other wise.
                return !mHero.potionResistance() && slot.count >= 1;
            default:
                throw new RuntimeException(String.format(Locale.US, "unhandle action type : %08X", act.type));
        }
    }

    // check if no action can do ...
    protected boolean someActionCanDo() {
        for (ActionSlot slot : mActionSlots) {
            if (slot.action.type != Action.Type.POTION && canDoAction(slot)) {
                return true;
            }
        }
        return false;
    }

    protected void doAction(int index, EventResult result) {
        final ActionSlot slot = mActionSlots.get(index);
        final Action act = slot.action;

        // pay cost series.
        final Varier cost = act.cost;
        if (cost.isCount()) {
            slot.count--;
            drinkSpecialPotion(act);
            if (slot.count == 0) {
                mActionSlots.remove(index);
            }
        } else {
            mHero.payCost(cost);
        }

        // do action series.
        switch (act.type) {
            case UPGRADE:
                for (Varier var : act.effects) {
                    mHero.upgradeAbility(var, result);
                }
            break;
            case SKILL:
                processSkillAction(mHero, act);
                break;
            case POTION: {
                mHero.drinkPotionWithBuffers(act.effects);
            }
            break;
        }
    }

    protected boolean processSkillAction(H hero, Action action) {return false;};

    /*
     * get loot ~~~
     */
    protected void gainLoot(Item loot, EventResult result) {

        // check....
        if (loot == Item.KEY || loot == Item.COPPER_COIN || loot == Item.SILVER_COIN || loot == Item.GOLDEN_COIN || loot == Item.STAR) {
            throw new RuntimeException(String.format(Locale.US, "%s should handled before call GameManager.gianLoot().", loot));
        }

        for (Varier var : loot.upgrade) {
            // feat : undead, holy one
            // life will not add or sub.
            if ((Feat.UNDEAD.in(mHero.feats) || Feat.HOLY_ONE.in(mHero.feats)) && var.isLife()) {
                continue;
            }
            // feat : purification
            // cursed item will not decrease life...???
            if (Feat.PURIFICATION.in(mHero.feats) && var.isLife() && var.value < 0) {
                continue;
            }
            // feat : forging TODO can refine item -> refined new item .... in special menu...
            // sword : attack + 2
            // shield : defense +2
            if (Feat.FORGING.in(mHero.feats)) {
                if (loot.isSword() && var.isAttack() && var.isOffset()) {
                    // 可以頂替掉的啊 ~~
                    var = new Varier(Varier.Type.ATTACK | Varier.Type.OFFSET, var.value + 2);
                }
                if (loot.isShield() && var.isDefense() && var.isOffset()) {
                    var = new Varier(Varier.Type.DEFENSE | Varier.Type.OFFSET, var.value + 2);
                }
            }
            if (loot.isPotion())
                // potion will only add battle buffer.
                mHero.addBattleBuffer(var);
            else
                // upgrade on body.. no on buffer.
                mHero.upgradeAbility(var, result);

        }
    }

    // battle.
    int battle(Fightable attacker1, Fightable defender1) {
        Ability attacker = attacker1.getAbility();
        Ability defender = defender1.getAbility();

        int attackerDamage;

        // none magic age. min damage = 1;
        attackerDamage = attacker.attack > defender.defense ? attacker.attack - defender.defense : 1;

        // attack num
        attackerDamage *= (1 + attacker.combo);

        // if can not attack
        if (0 == attackerDamage) {
            return Game.Setting.GLOBAL_MAX_DAMAGE;
        }

        int totalTime = ((defender.life - 1) / attackerDamage + 1) * attacker.speed;

        int defenderDamage;

        // none magic age. min damage = 1;
        defenderDamage = defender.attack > attacker.defense ? defender.attack - attacker.defense : 1;

        // attack num
        defenderDamage *= (1 + defender.combo);

        int loseLife = defenderDamage * (totalTime / defender.speed);

        if (loseLife > Game.Setting.GLOBAL_MAX_DAMAGE) {
            loseLife = Game.Setting.GLOBAL_MAX_DAMAGE;
        }

        return loseLife;
    }

    // change action ...
    protected void changeAction(Action source, Action target) {
        // change target -> source
        for (ActionSlot slot : mActionSlots) {
            if (slot.action == source) {
                slot.action = target;
                return;
            }
        }
    }

    /*
     * action adapter
     */

    public int getActionSlotCount() {
        return mActionSlots.size();
    }

    public ActionSlot getActionSlot(int position) {
        return mActionSlots.get(position);
    }

    public String getActionSlotBGRes(int position) {
        final ActionSlot slot = mActionSlots.get(position);
        if (slot.action.type == Action.Type.POTION)
            return "btn_shop";
        else
            return mHero.heroClass.getBackgroundBtn();
    }

    public boolean isActionSlotEnable(int position) {
        return canDoAction(mActionSlots.get(position));
    }
}
