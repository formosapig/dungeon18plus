package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.GiantRecord;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileView extends AssetGroup {

    private final QQLinear root;
    private final ProfileBiographyView biography;
    private final ProfileBaseAttributeView baseAttribute;
    private final ProfileRestrictionView restriction;
    private final ProfileActionView action;
    private final ProfileFeatView feat;
    private final ProfileUniqueSkillView uniqueSkill;
    private final ProfileSoulView soul;
    private final ProfileMasteryView mastery;

    public ProfileView(Assets assets) {
        super(assets);

        root = new QQLinear(Game.Size.WIDGET_MARGIN);
        root.setSize(MATCH_PARENT, WRAP_CONTENT);
        addChild(root);

        // biography
        biography = new ProfileBiographyView(assets, Game.Size.WIDGET_MARGIN);
        biography.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(biography);

        // base attribute
        baseAttribute = new ProfileBaseAttributeView(assets, Game.Size.INNER_MARGIN);
        baseAttribute.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(baseAttribute);

        // restriction
        restriction = new ProfileRestrictionView(assets, Game.Size.INNER_MARGIN);
        restriction.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(restriction);

        // action
        action = new ProfileActionView(assets, Game.Size.INNER_MARGIN);
        action.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(action);

        // feat
        feat = new ProfileFeatView(assets, Game.Size.INNER_MARGIN);
        feat.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(feat);

        // unique skill
        uniqueSkill = new ProfileUniqueSkillView(assets, Game.Size.INNER_MARGIN);
        uniqueSkill.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(uniqueSkill);

        // soul
        soul = new ProfileSoulView(assets, Game.Size.INNER_MARGIN);
        soul.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(soul);

        // mastery
        mastery = new ProfileMasteryView(assets, Game.Size.INNER_MARGIN);
        mastery.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(mastery);
    }

    @Override
    public void resetWrapHeight() {
        this.height = root.getHeight();
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    // create profile view with HeroClassRecord
    public void update(HeroClassRecord record, SaveGame savedGame) {
        NinePatch bg = assets.getNinePatchBG(record.heroClass.alignment.key);
        uniqueSkill.setVisible(false);

        if (record.isGameModeAvailable(Game.Mode.DUNGEON) || record.isGameModeAvailable(Game.Mode.COLOSSEUM)) {
            biography.update(assets.geti18n("profile_biography"), assets.geti18n(record.heroClass.key + "_help"));
            biography.setBackground(bg);

            baseAttribute.update(record);
            baseAttribute.setBackground(bg);
            baseAttribute.setVisible(true);

            restriction.update(record);
            restriction.setBackground(bg);
            restriction.setVisible(true);

            action.update(record);
            action.setBackground(bg);
            action.setVisible(true);

            feat.update(record);
            feat.setBackground(bg);
            feat.setVisible(true);

            if (!record.souls.isEmpty()) {
                soul.update(record);
                soul.setBackground(bg);
                soul.setVisible(true);
            } else {
                soul.setVisible(false);
            }

            if (!record.equips.isEmpty()) {
                mastery.update(record);
                mastery.setVisible(true);
            } else {
                mastery.setVisible(false);
            }
        } else {
            String help = "";
            switch (record.heroClass) {
                case ASSASSIN:
                    help = assets.formati18n("assassin_unlock", savedGame.getMonsterCount(), 180);
                    break;
                case CRUSADER:
                    help = assets.formati18n("crusader_unlock", savedGame.getEquipmentCount(), 30);
                    break;
                case SWORD_MASTER:
                    help = assets.geti18n("sword_master_unlock");
                    break;
                case CLERIC:
                case RED_MAGE:
                case BLUE_MAGE:
                case GREEN_MAGE:
                    help = assets.geti18n("unlock_coming_soon");
                    break;
                case MERCHANT:
                    help = assets.geti18n("merchant_unlock");
                    break;
                case FAIRY:
                case FIRE_KNIGHT:
                case WATER_KNIGHT:
                    help = assets.geti18n("unlock_premium");
                    break;
                case EARTH_KNIGHT:
                case WIND_KNIGHT:
                    help = assets.geti18n("unlock_defeat_colosseum");
                    break;
                case VALKYRIE:
                    help = assets.geti18n("valkyrie_unlock");
                    break;
                case SKELETON_KING:
                    help = assets.geti18n("unlock_defeat_dungeon");
                    break;
                default:
                    help = "";
                    break;
            }

            biography.update(assets.geti18n("profile_unlock_condition"), help);
            biography.setBackground(bg);

            baseAttribute.setVisible(false);
            restriction.setVisible(false);
            action.setVisible(false);
            feat.setVisible(false);
            soul.setVisible(false);
            mastery.setVisible(false);
        }
    }

    // create profile view with HeroClassRecord
    public void update(GiantRecord record, SaveGame savedGame) {
        NinePatch bg = assets.getNinePatchBG(record.race.alignment.key);
        restriction.setVisible(false);
        action.setVisible(false);
        feat.setVisible(false);
        mastery.setVisible(false);

        if (record.isUnlocked()) {
            biography.update(assets.geti18n("profile_biography"), assets.geti18n(record.race.helpKey));
            biography.setBackground(bg);

            baseAttribute.update(record);
            baseAttribute.setBackground(bg);
            baseAttribute.setVisible(true);

            if (!record.skills.isEmpty()) {
                uniqueSkill.update(record);
                uniqueSkill.setBackground(bg);
                uniqueSkill.setVisible(true);
            } else
                uniqueSkill.setVisible(false);

            if (!record.souls.isEmpty()) {
                soul.update(record);
                soul.setBackground(bg);
                soul.setVisible(true);
            } else
                soul.setVisible(false);
        } else {
            biography.update(assets.geti18n("profile_unlock_condition"), assets.geti18n("unlock_not_found"));
            biography.setBackground(bg);

            baseAttribute.setVisible(false);
            uniqueSkill.setVisible(false);
            soul.setVisible(false);
        }
    }
}
