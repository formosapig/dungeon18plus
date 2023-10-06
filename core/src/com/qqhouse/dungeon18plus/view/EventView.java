package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQView;

public class EventView extends QQButton implements QQView.IsParentView {

    public EventView(String buttonKey) {
        super(buttonKey);
    }

    private Texture icon;
    private ItemView item; // loot
    private QQIconText cost; // big font ?!
    private AbilityView ability; // ability of foe or something...



    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {

    }
}
