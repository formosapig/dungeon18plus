package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.SoulCount;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQButtonEx;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

public class SoulButton extends AssetGroup {
    private final ItemView soul;
    private final VarietyView upgrade;
    private final QQView bg;
    private final QQButtonEx remove;

    public SoulButton(Assets assets, SoulCount sc) {
        super(assets);

        bg = new QQView();
        addChild(bg);

        soul = ItemView.create(assets, sc);//new ItemView(assets.getFont(Game.Font.ITEM_COUNT), assets.getNinePatch("black"));
        addChild(soul);

        upgrade = new VarietyView(assets);
        addChild(upgrade);

        remove = new QQButtonEx(assets.getBackgroundSet("ordinary"));
        remove.setImage(assets.getIcon("icon/collapse"));
        remove.setPadding(8);
        addChild(remove);
    }

    public void update(SoulCount sc) {
        //soul.setSoulCount(sc);
        upgrade.update32(sc.soul.getInfluence(sc.count));
    }

    @Override
    public void setBackground(NinePatch bg) {
        this.bg.setBackground(bg);
    }

    @Override
    public void resetWrapHeight() {
        height = topPadding + 32 + bottomPadding;
    }

    //
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        float w = width - (32 + leftPadding + rightPadding) - Game.Size.INNER_MARGIN - (32 + leftPadding) - Game.Size.INNER_MARGIN;
        upgrade.setSize(w, 32);
        bg.setSize(width - (32 + leftPadding + rightPadding) - Game.Size.INNER_MARGIN, 32 + bottomPadding + topPadding);
        remove.setSize(32 + leftPadding + rightPadding, 32 + bottomPadding + topPadding);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        soul.setPosition(leftPadding, bottomPadding);
        upgrade.setPosition(leftPadding + 32 + Game.Size.INNER_MARGIN, bottomPadding);
        //bg.setPosition(0, 0);
        remove.setPosition(width - remove.getWidth(), 0);
    }

    /*
        QQPressListener
     */
    public void addQQClickListener(QQPressListener listener, int index) {
        remove.addQQClickListener(listener, index);
    }

}
