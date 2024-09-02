package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Action;
import com.qqhouse.ui.QQImage;

public class ProfileUpgradeView extends AssetGroup {

    private QQImage icon;
    private VarietyView upgrade;
    private ItemView cost;

    public ProfileUpgradeView(Assets assets) {
        super(assets);
        setPadding(Game.Size.BLOCKEE_PADDING / 2);
    }

    public void reset(Action act) {
        icon = new QQImage(assets.getIcon(act.key));
        icon.setSize(32, 32);
        //icon.setPosition(4, 4);

        upgrade = new VarietyView(assets);
        upgrade.update(act.effects);
        upgrade.setSize(MATCH_PARENT, 32);


        cost = new ItemView(assets.getIcon(act.cost.getIconKey()), assets.getFont(Game.Font.DIGITAL16), assets.getNinePatch("black"));
        cost.setText(Integer.toString(act.cost.value));
        cost.setSize(32, 32);
        //cost.setPosition(  ,4);

        this.setSize(MATCH_PARENT, 40);
    }



}
