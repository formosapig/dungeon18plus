package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.qqhouse.ui.QQView;

public class TitleMenuView extends QQView {

    private Texture txrBlockee;
    private String menu;
    private NinePatch npBackground;
    private BitmapFont font;
    private float font_width, font_height;

    public TitleMenuView(String blockeeName, String menu, String background) {
        txrBlockee = new Texture(Gdx.files.internal("blockee\\" + blockeeName + ".png"));
        this.menu = menu;
        npBackground = new NinePatch(new Texture(Gdx.files.internal("btn\\" + background + ".png")), 4, 4, 4, 4);
        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NotoSansTC-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 28; // font size
        parameter.color = new Color(0x9E8064FF);
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "地下城巫師塔圓形競技場荒原魔王城圖書館";
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important

        // 加了這行, 字體變漂亮了... 在手機上的效果無法確定....
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, menu);
        font_width = glyphLayout.width;
        font_height = glyphLayout.height;
        Gdx.app.error("TEST", "Print " + menu + " w : " + glyphLayout.width + " h : " + glyphLayout.height);
        //float w = glyphLayout.width;
    }

    @Override
    public boolean touchDown(float x, float y) {
        Gdx.app.error("TEST", "touchDown : " + this.menu);
        return true;
    }

    @Override
    public boolean touchUp(float x, float y) {
        Gdx.app.error("TEST", "touchUp : " + this.menu);
        return true;
    }

    public void draw(SpriteBatch batch) {
        // draw gradient (漸層)
        ShapeRenderer render;


        // draw background
        npBackground.draw(batch, x, y, width, height);
        // draw blockee
        batch.draw(txrBlockee, 8 + x, 8 + y);
        // draw font...

        font.draw(batch, menu, (int)60 + (172 - font_width) / 2 + x, (int)41 + y);//font_height);//(180 - font_width) / 2 + x, 8 + (48 - font_height) / 2 + y);
        //batch.draw();


    }


    @Override
    public void dispose() {
        txrBlockee.dispose();
        npBackground.getTexture().dispose();
    }
}
