package com.qqhouse.dungeon18plus.view;

import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.UniqueSkill;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.struct.Operation2;
import com.qqhouse.dungeon18plus.struct.campaign.UniqueSkillData;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.Locale;

public class ExtendSoulSizeButton extends AssetGroupButton {

    // QQText : soul Count / max soul count extend size
    // QQItem : Golden Coin (with count)
    private QQText title;
    private ItemView goldenCoin;

    public ExtendSoulSizeButton(Assets assets) {
        super(assets);

        title = new QQText(assets.getFont(Game.Font.NAME20));
        title.setColor(Game.Colour.RARE);
        addChild(title);

        goldenCoin = new ItemView(assets.getIcon("item/golden_coin"), assets.getFont(Game.Font.ITEM_COUNT), assets.getBackground("black"));
        goldenCoin.setSize(32, 32);
        addChild(goldenCoin);

        setBackground(assets.getBackgroundSet("special"));
    }

    public void update(HeroClassRecord record) {
        title.setText(String.format(Locale.US, "%d / %d extend size", record.getSoulCount() , record.maxSoulSize));
        final int price = record.getExtendSoulSizePrice();
        goldenCoin.setText(Integer.MAX_VALUE == price ? "-" : Integer.toString(price));
        setEnabled(record.coin >= price);
    }

    @Override
    public void resetWrapHeight() {
        height = 48;

        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        title.setSize(width - leftPadding - rightPadding - 32 - 4, 32);
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;

        title.setPosition(leftPadding, bottomPadding);
        goldenCoin.setPosition(width - rightPadding - 32, bottomPadding);
    }

}
