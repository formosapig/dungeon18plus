package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQView;

public class EventView extends QQButton implements QQView.IsParentView {

    public EventView(String buttonKey) {
        super(buttonKey);
    }


    @Override
    public void drawChildrenView(SpriteBatch batch, float originX, float originY) {

    }
}
