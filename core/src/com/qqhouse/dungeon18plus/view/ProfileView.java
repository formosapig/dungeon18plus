package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileView extends AssetGroup {

    private final QQText titleBiography;
    private final QQText biography;
    private final ProfileBaseAttributeView baseAttribute;

    public ProfileView(Assets assets) {
        super(assets);

        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setPadding(8);
        group.setSize(MATCH_PARENT, MATCH_PARENT);
        group.setBackground(assets.getNinePatchBG("help"));
        addChild(group);

        // titles ...
        titleBiography = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBiography.setPadding(4);
        titleBiography.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        //titleBiography.setPosition(8, 100);
        titleBiography.setAlign(Align.left);
        group.addChild(titleBiography);

        biography = new QQText(assets.getFont(Game.Font.HELP14));
        biography.setPadding(4);
        biography.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //titleBiography.setPosition(8, 100);
        biography.setAlign(Align.left);
        group.addChild(biography);

        QQText titleBaseAttribute = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBaseAttribute.setPadding(4);
        titleBaseAttribute.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleBaseAttribute.setAlign(Align.left);
        titleBaseAttribute.setText(assets.geti18n("profile_base_attribute"));
        group.addChild(titleBaseAttribute);

        baseAttribute = new ProfileBaseAttributeView(assets);
        baseAttribute.setPadding(4);
        baseAttribute.setSize(MATCH_PARENT, WRAP_CONTENT);
        group.addChild(baseAttribute);

        QQText titleSoul = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleSoul.setPadding(4);
        titleSoul.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleSoul.setAlign(Align.left);
        titleSoul.setText(assets.geti18n("profile_soul"));
        titleSoul.setVisible(false);
        group.addChild(titleSoul);

        QQText titleRestriction = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleRestriction.setPadding(4);
        titleRestriction.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleRestriction.setAlign(Align.left);
        titleRestriction.setText(assets.geti18n("profile_restriction"));
        group.addChild(titleRestriction);

        QQText titleUpgrade = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleUpgrade.setPadding(4);
        titleUpgrade.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleUpgrade.setAlign(Align.left);
        titleUpgrade.setText(assets.geti18n("profile_upgrade"));
        group.addChild(titleUpgrade);

        QQText titleFeat = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleFeat.setPadding(4);
        titleFeat.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleFeat.setAlign(Align.left);
        titleFeat.setText(assets.geti18n("profile_feat"));
        group.addChild(titleFeat);

        QQText titleMastery = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleMastery.setPadding(4);
        titleMastery.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleMastery.setAlign(Align.left);
        titleMastery.setText(assets.geti18n("profile_mastery"));
        titleMastery.setVisible(false);
        group.addChild(titleMastery);

    }




    // create profile view with HeroClassRecord
    public void update(HeroClassRecord record) {
        titleBiography.setText((record.isGameModeAvailable(Game.Mode.DUNGEON) || record.isGameModeAvailable(Game.Mode.COLOSSEUM)) ? 
            assets.geti18n("profile_biography") :
            assets.geti18n("profile_unlock_condition"));

        biography.setText(assets.geti18n(record.heroClass.key + "_help"), true);
        biography.setBackground(assets.getNinePatchBG(record.heroClass.alignment.key));

        baseAttribute.update(record);
        baseAttribute.setBackground(assets.getNinePatchBG(record.heroClass.alignment.key));
    }





}
