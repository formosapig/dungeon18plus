package com.qqhouse.dungeon18plus.core;

import com.badlogic.gdx.Gdx;
import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.ActionSlot;
import com.qqhouse.dungeon18plus.struct.event.VariedHero;
import com.qqhouse.dungeon18plus.struct.hero.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

class GladiatorCreator extends GameManager<Hero> {

	private ArrayList<Item>	mAllEquipment;

	private GladiatorCreator() {

        mAllEquipment = new ArrayList<>();
        for (Item loot : Item.values()) {
            if (loot.isEquipment() && !loot.isPowerRing() && loot.isNotSpecial()) {
                mAllEquipment.add(loot);
            }
        }
    }

	private void reset(HeroClass heroClass, int round) {
	    mHero = new Hero();
	    buildUpHero(heroClass, null);

	    // upgrade only.
        mActionSlots = new ArrayList<>();
        for (Action act : heroClass.actions) {
            if (act.type == Action.Type.UPGRADE) {
                mActionSlots.add(new ActionSlot(act));
            }
        }

        // reset limit
        final int roundPlus = round / 10;	// 0 ~ 3
        int speedMinus = -(roundPlus + 1) / 2;
        if ((mHero.getLimit().speed + speedMinus) < G.Setting.GLOBAL_HERO_MIN_SPEED) {
            speedMinus = G.Setting.GLOBAL_HERO_MIN_SPEED - mHero.getLimit().speed;
        }
        mHero.getLimit().plus(new Ability(
                250 * roundPlus,
                25 * roundPlus,
                0,
                0,
                25 * roundPlus,
                speedMinus
        ));

        mHero.star = round * 35;
        mHero.coin = round * 18;
        if (G.Debug.CREATE_GLADIATOR) {
            Gdx.app.error(G.Debug.TAG, String.format(Locale.US, "gladiator : %s(c:%d,s:%d)", heroClass, mHero.coin, mHero.star));
        }
	}

    private void autoShopping() {

	    Collections.shuffle(mAllEquipment);
        for (Item equip : mAllEquipment) {
            int price = equip.price;
            if (mHero.coin >= price) {
                mHero.coin -= price;
                gainLoot(equip, null);
                if (G.Debug.CREATE_GLADIATOR) {
                    Gdx.app.error(G.Debug.TAG, String.format(Locale.US, "equip : %s(%d)-> %s", equip, mHero.coin, mHero));
                }
            }
            if (mHero.coin < 30)
                break;
        }
    }

    private void autoUpgrade() {

	    final int slotCount = mActionSlots.size();
        final int[] doActionRate = new int[slotCount];

        // setup pickup array.
	    for (int i = 0; i < slotCount; ++i) {
	        doActionRate[i] = mRandom.nextInt(100) + 1;
	        if (i > 0) {
	            doActionRate[i] += doActionRate[i - 1];
            }
        }

        // do pickup loop
        while ((mHero.star > 0) && (doActionRate[slotCount - 1] > 0)) {

            // pick up.
            int pickup = 0;
            final int seed = mRandom.nextInt(doActionRate[slotCount - 1]) + 1;
            for (int i = 0; i < slotCount; ++i) {
                if (seed <= doActionRate[i]) {
                    pickup = i;
                    break;
                }
            }

            // do action or result do action rate
            final ActionSlot slot = getActionSlot(pickup);
            if (canDoAction(slot)) {
                doAction(pickup, null);
                if (G.Debug.CREATE_GLADIATOR) {
                    Gdx.app.error(G.Debug.TAG, String.format(Locale.US, "act : %s(%d) -> %s", slot.action, mHero.star, mHero));
                }
            } else {
                int featDiscount = pickup == 0
                        ? doActionRate[pickup]
                        : doActionRate[pickup] - doActionRate[pickup - 1];
                for (int i = pickup; i < slotCount; ++i) {
                    doActionRate[i] -= featDiscount;
                }
            }

        }
    }

	private static final GladiatorCreator creator = new GladiatorCreator();

	public static void create(VariedHero gladiator, HeroClass heroClass, int round) {

	    // reset new hero
	    creator.reset(heroClass, round);

		// use coin
        creator.autoShopping();

		// use star
        creator.autoUpgrade();

		// apply mHero attribute to gladiator.
		gladiator.setAbility(creator.getHero());
	}
}
