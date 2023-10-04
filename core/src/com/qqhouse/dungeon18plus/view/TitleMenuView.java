package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQScreen;

public class TitleMenuView extends QQButton {

    private Texture blockee;
    private BitmapFont font;
    private String menu;
    private float menuShiftX, menuShiftY;

    public TitleMenuView(String buttonKey, Texture blockee, BitmapFont font, String menu) {
        super(buttonKey);
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
        //Color c = batch.getColor();
        // 可以改變 alpha 值唷~~~~ 或是套用整個紅色之類的,很好玩...

        //batch.setColor(1f,0.8f,0.8f,1);//c.r, c.g, c.b, 0.1f);

        // draw blockee
        batch.draw(blockee, originX + 8, originY + 8);

        //batch.setColor(1,1,1,1);//c.r, c.g, c.b, 1);
        // draw font...
        //master.getFont().getData().setScale(0.71f, 0.5f);
        font.draw(batch, menu, (int)(originX + menuShiftX), (int)(originY + menuShiftY));
        //batch.draw();
    }


    @Override
    public void dispose() {
        // button...?
        super.dispose();
    }
}
