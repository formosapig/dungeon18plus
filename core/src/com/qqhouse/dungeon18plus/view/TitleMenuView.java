package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.ui.QQButton;

public class TitleMenuView extends QQButton {

    private Texture blockee;
    private BitmapFont font;
    private String menu;
    private float menuShiftX, menuShiftY;

    public TitleMenuView(BackgroundSet set, Texture blockee, BitmapFont font, String menu) {
        super(set);
        this.blockee = blockee;
        this.font = font;
        this.menu = menu;

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, menu);

        menuShiftX = 60 + (172 - glyphLayout.width) / 2;
        menuShiftY = 8 + 48 / 2 + glyphLayout.height / 2; // good!
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw blockee
        batch.draw(blockee, originX + 8, originY + 8);

        font.draw(batch, menu, (int)(originX + menuShiftX), (int)(originY + menuShiftY));
    }


    @Override
    public void dispose() {
        // button...?
        super.dispose();
    }
}
