package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;

public class ActionView extends QQButton {

    /*
        fixed icon.
        fixed cost icon.
        fixed cost value ?
     */

    public ActionView(BackgroundSet set, Texture icon, BitmapFont costFont, Texture costIcon, int costValue) {
        super(set);
        this.icon = icon;
        cost = new QQIconText(costFont, costIcon);
        cost.setSize(36, 16);
        cost.setAlign(Align.right);
        cost.setText(Integer.toString(costValue));
    }

    private Texture icon;
    private QQIconText cost;
    private float iconShiftX, costShiftX;

    @Override
    public void setSize(float w, float h) {
        super.setSize(w, h);
        iconShiftX = (w - 32) / 2;
        costShiftX = (w - cost.getWidth()) / 2;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw icon
        batch.draw(icon, originX + iconShiftX, originY + 24, 32, 32);

        // draw IconText
        cost.draw(batch, originX + costShiftX, originY + 8);
    }

}
