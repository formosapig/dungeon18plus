package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQView;

/*
 *  Title Bar 用在一些特別的地方, 包含了
 *  Icon  : 32 x 32 pixel, 通常是 blockee
 *  Title : String, 一個字串
 *  Gold Coin : ???, 在 Soul countrol view 之類的地方使用.../
 *
 */
public class TitleBarView extends QQView {

    private Texture icon; // fixed size = 32 x 32
    private BitmapFont font; // font to draw...
    private String title; // info.

    public TitleBarView(Texture icon, BitmapFont font, String title) {
        this.icon = icon;
        this.font = font;
        this.title = title;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        // draw icon with fixed size.
        if (null != icon) {
            batch.draw(icon, originX + leftPadding, originY + bottomPadding, 32, 32);
        }

        // print title in central...
        font.setColor(Color.WHITE);
        font.draw(batch, title, originX + leftPadding + 32 + 4, originY + bottomPadding + (32 + 18) / 2);


    }


    @Override
    public void dispose() {
        icon.dispose();
    }
}
