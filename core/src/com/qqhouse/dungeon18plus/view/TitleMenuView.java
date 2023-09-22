package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQScreen;

public class TitleMenuView extends QQButton {

    private String key;
    private Texture txrBlockee;
    private String menuStr;
    private float menuShiftX, menuShiftY;

    public TitleMenuView(QQScreen master, String buttonKey) {
        super(master, buttonKey);
    }

    public TitleMenuView setResource(String key, String blockeeKey) {
        this.key = key;

        txrBlockee = new Texture(Gdx.files.internal("blockee\\" + blockeeKey + ".png"));
        // 效果較好 ?
        txrBlockee.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        menuStr = master.getLanguageBundle().get(key);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(master.getFont(), menuStr);

        menuShiftX = 60 + (172 - glyphLayout.width) / 2;
        menuShiftY = 8 + 48 / 2 + glyphLayout.height / 2; // good!
        return this;
    }
    @Override
    public void drawForeground(SpriteBatch batch) {
        //Color c = batch.getColor();
        // 可以改變 alpha 值唷~~~~ 或是套用整個紅色之類的,很好玩...

        //batch.setColor(1f,0.8f,0.8f,1);//c.r, c.g, c.b, 0.1f);

        // draw blockee
        batch.draw(txrBlockee, 8 + x, 8 + y);

        //batch.setColor(1,1,1,1);//c.r, c.g, c.b, 1);
        // draw font...
        master.getFont().draw(batch, menuStr, (int)(menuShiftX + x), (int)(menuShiftY + y));
        //batch.draw();
    }


    @Override
    public void dispose() {
        txrBlockee.dispose();
        super.dispose();
    }
}
