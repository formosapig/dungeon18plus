package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class ProfileView extends AssetGroup {

    private final QQLinear root;
    private final QQText titleBiography;
    private final QQText biography;
    private final ProfileBaseAttributeView baseAttribute;
    private final ProfileRestrictionView restriction;

    public ProfileView(Assets assets) {
        super(assets);

        root = new QQLinear(Game.Size.WIDGET_MARGIN);
        //root.setPadding(8);
        root.setSize(MATCH_PARENT, WRAP_CONTENT);
        //root.setBackground(assets.getNinePatchBG("help"));
        //group.setPosition(0, 100);
        addChild(root);

        // titles ...
        titleBiography = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBiography.setPadding(4);
        titleBiography.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        //titleBiography.setPosition(8, 100);
        titleBiography.setAlign(Align.left);
        root.addChild(titleBiography);

        biography = new QQText(assets.getFont(Game.Font.HELP14));
        biography.setPadding(8);
        biography.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        //titleBiography.setPosition(8, 100);
        biography.setAlign(Align.left);
        root.addChild(biography);

        // base attribute
        QQText titleBaseAttribute = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleBaseAttribute.setPadding(4);
        titleBaseAttribute.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleBaseAttribute.setAlign(Align.left);
        titleBaseAttribute.setText(assets.geti18n("profile_base_attribute"));
        root.addChild(titleBaseAttribute);

        baseAttribute = new ProfileBaseAttributeView(assets, Game.Size.INNER_MARGIN);
        baseAttribute.setPadding(8);
        baseAttribute.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(baseAttribute);

        // soul

        QQText titleSoul = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleSoul.setPadding(4);
        titleSoul.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleSoul.setAlign(Align.left);
        titleSoul.setText(assets.geti18n("profile_soul"));
        titleSoul.setVisible(false);
        root.addChild(titleSoul);

        // restriction

        QQText titleRestriction = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleRestriction.setPadding(4);
        titleRestriction.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleRestriction.setAlign(Align.left);
        titleRestriction.setText(assets.geti18n("profile_restriction"));
        root.addChild(titleRestriction);

        restriction = new ProfileRestrictionView(assets, Game.Size.INNER_MARGIN);
        //restriction.setPadding(4);
        restriction.setSize(MATCH_PARENT, WRAP_CONTENT);
        root.addChild(restriction);


        QQText titleUpgrade = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleUpgrade.setPadding(4);
        titleUpgrade.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleUpgrade.setAlign(Align.left);
        titleUpgrade.setText(assets.geti18n("profile_upgrade"));
        root.addChild(titleUpgrade);

        QQText titleFeat = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleFeat.setPadding(4);
        titleFeat.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleFeat.setAlign(Align.left);
        titleFeat.setText(assets.geti18n("profile_feat"));
        root.addChild(titleFeat);

        QQText titleMastery = new QQText(assets.getFont(Game.Font.NAME20), assets.getNinePatchBG("underline"));
        titleMastery.setPadding(4);
        titleMastery.setSize(QQView.MATCH_PARENT, 24);//QQView.WRAP_CONTENT);
        titleMastery.setAlign(Align.left);
        titleMastery.setText(assets.geti18n("profile_mastery"));
        titleMastery.setVisible(false);
        root.addChild(titleMastery);

    }

    @Override
    public void resetWrapHeight() {
        height = root.getHeight();
        if (null != parent)
            parent.onChildSizeChanged(this);
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

        restriction.update(record);
        restriction.setBackground(assets.getNinePatchBG(record.heroClass.alignment.key));
    }





}
