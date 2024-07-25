package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileView extends AssetGroup {

    private final QQLinear root;
    private final QQText titleBiography, titleBaseAttribute, titleRestriction, titleAction, titleFeat, titleSoul, titleMastery;
    private final QQText biography;
    private final ProfileBaseAttributeView baseAttribute;
    private final ProfileRestrictionView restriction;
    private final ProfileActionView action;
    private final ProfileFeatView feat;
    private final ProfileSoulView soul;
    private final ProfileMasteryView mastery;

    public ProfileView(Assets assets) {
        super(assets);

        root = new QQLinear(Game.Size.WIDGET_MARGIN);
        root.setSize(MATCH_PARENT, WRAP_CONTENT);
        addChild(root);

        // titles ...
        titleBiography = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBiography.setPadding(8);
        titleBiography.setSize(QQView.MATCH_PARENT, 32);
        titleBiography.setAlign(Align.left);
        root.addChild(titleBiography);

        biography = new QQText(assets.getFont(Game.Font.HELP14));
        biography.setPadding(8);
        biography.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //titleBiography.setPosition(8, 100);
        biography.setAlign(Align.left);
        root.addChild(biography);

        // base attribute
        titleBaseAttribute = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBaseAttribute.setPadding(8);
        titleBaseAttribute.setSize(QQView.MATCH_PARENT, 32);
        titleBaseAttribute.setAlign(Align.left);
        titleBaseAttribute.setText(assets.geti18n("profile_base_attribute"));
        root.addChild(titleBaseAttribute);

        baseAttribute = new ProfileBaseAttributeView(assets, Game.Size.INNER_MARGIN);
        baseAttribute.setPadding(8);
        baseAttribute.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(baseAttribute);

        // restriction
        titleRestriction = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleRestriction.setPadding(8);//, 4, 4, 4);
        titleRestriction.setSize(QQView.MATCH_PARENT, 32);//QQView.WRAP_CONTENT);
        titleRestriction.setAlign(Align.left);
        titleRestriction.setText(assets.geti18n("profile_restriction"));
        root.addChild(titleRestriction);

        restriction = new ProfileRestrictionView(assets, Game.Size.INNER_MARGIN);
        //restriction.setPadding(4);
        restriction.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(restriction);

        // action
        titleAction = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleAction.setPadding(8);//, 8, 4, 4);
        titleAction.setSize(QQView.MATCH_PARENT, 32);//QQView.WRAP_CONTENT);
        titleAction.setAlign(Align.left);
        titleAction.setText(assets.geti18n("profile_action"));
        root.addChild(titleAction);

        action = new ProfileActionView(assets, Game.Size.INNER_MARGIN);
        action.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(action);

        // feat
        titleFeat = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleFeat.setPadding(8);
        titleFeat.setSize(QQView.MATCH_PARENT, 32);
        titleFeat.setAlign(Align.left);
        titleFeat.setText(assets.geti18n("profile_feat"));
        root.addChild(titleFeat);

        feat = new ProfileFeatView(assets, Game.Size.INNER_MARGIN);
        feat.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(feat);

        // soul
        titleSoul = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleSoul.setPadding(4);
        titleSoul.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleSoul.setAlign(Align.left);
        titleSoul.setText(assets.geti18n("profile_soul"));
        titleSoul.setVisible(false);
        root.addChild(titleSoul);

        soul = new ProfileSoulView(assets, Game.Size.INNER_MARGIN);
        soul.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(soul);

        // mastery
        titleMastery = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleMastery.setPadding(8);
        titleMastery.setSize(QQView.MATCH_PARENT, 32);
        titleMastery.setAlign(Align.left);
        titleMastery.setText(assets.geti18n("profile_mastery"));
        //titleMastery.setVisible(false);
        root.addChild(titleMastery);

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

        if (record.isGameModeAvailable(Game.Mode.DUNGEON) || record.isGameModeAvailable(Game.Mode.COLOSSEUM)) {
            titleBiography.setText(assets.geti18n("profile_biography"));

            NinePatch bg = assets.getNinePatchBG(record.heroClass.alignment.key);

            biography.setText(assets.geti18n(record.heroClass.key + "_help"), true);
            biography.setBackground(bg);
            biography.setVisible(true);

            titleBaseAttribute.setVisible(true);
            baseAttribute.update(record);
            baseAttribute.setBackground(bg);
            baseAttribute.setVisible(true);

            titleRestriction.setVisible(true);
            restriction.update(record);
            restriction.setBackground(bg);
            restriction.setVisible(true);

            titleAction.setVisible(true);
            action.update(record);
            action.setBackground(bg);
            action.setVisible(true);

            titleFeat.setVisible(true);
            feat.update(record);
            feat.setBackground(bg);
            feat.setVisible(true);

            if (!record.souls.isEmpty()) {
                titleSoul.setVisible(true);
                soul.update(record);
                soul.setBackground(bg);
                soul.setVisible(true);
            } else {
                titleSoul.setVisible(false);
                soul.setVisible(false);
            }

            if (!record.equips.isEmpty()) {
                titleMastery.setVisible(true);
                mastery.update(record);
                mastery.setVisible(true);
            } else {
                titleMastery.setVisible(false);
                mastery.setVisible(false);
            }
        } else {
            titleBiography.setText(assets.geti18n("profile_unlock_condition"));

            NinePatch bg = assets.getNinePatchBG(record.heroClass.alignment.key);

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

            biography.setText(help, true);
            biography.setBackground(bg);
            biography.setVisible(true);

            titleBaseAttribute.setVisible(false);
            baseAttribute.setVisible(false);

            titleRestriction.setVisible(false);
            restriction.setVisible(false);

            titleAction.setVisible(false);
            action.setVisible(false);

            titleFeat.setVisible(false);
            feat.setVisible(false);

            titleSoul.setVisible(false);
            soul.setVisible(false);

            titleMastery.setVisible(false);
            mastery.setVisible(false);
        }
    }
}
