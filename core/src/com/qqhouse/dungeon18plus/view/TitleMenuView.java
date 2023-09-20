package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.qqhouse.dungeon18plus.core.GameAlignment;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

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
    public void draw(SpriteBatch batch) {
        // draw background, button need this.
        super.draw(batch);
        // draw blockee
        batch.draw(txrBlockee, 8 + x, 8 + y);
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
