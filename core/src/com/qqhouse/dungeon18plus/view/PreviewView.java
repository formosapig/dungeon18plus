package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class PreviewView extends QQButton implements QQView.IsParent {

    private Texture icon;
    private BitmapFont fntName, fntDesc;
    private String name1, desc;

    private Texture blockee;
    private QQText level, name, help;
    private QQIconText scoreOrRound;
    private Assets assets;

    /*
        Icon : blockee icon
        Level : blockee level (if needs), at upper left of icon. Game.Font.DIGITAL18
        Name : horizontal central, next to icon. Game.Font.NAME20
        Score / round : at upper right, may overlap name field. Game.Font.DIGITAL18
        help : description of hero class, multi line, max lines = 3... Game.Font.HELP14
     */

    private float iconShiftX, iconShiftY, nameShiftX, nameShiftY, descShiftX, descShiftY, descW;

    //public PreviewView(BackgroundSet set, Texture icon, BitmapFont fntName, String name, BitmapFont fntDesc, String desc) {
    //    super(set);
    //    this.icon = icon;
    //    fntName.setColor(Game.Colour.RARE);
    //    this.fntName = fntName;
    //    this.name = name;
    //    this.fntDesc = fntDesc;
    //    this.desc = desc;
        //this.fntName.setColor(Game.color.RARE);
    //    this.fntDesc.setColor(Color.WHITE);
    //}

    public PreviewView(Assets assets) {
        this.assets = assets;
    }

    public void reset(HeroClassRecord record, int gameMode) {
        // set background
        setBackground(assets.getBackgroundSet(record.heroClass.alignment.key));

        // icon
        blockee = assets.getBlockee(record.heroClass.key);

        // level
        if (Game.Mode.DUNGEON == gameMode && 0 < record.highLevel) {
            level = new QQText(assets.getFont(Game.Font.LEVEL16), new NinePatch(assets.getTexture("background", "level"), 4, 4, 4, 4), 0.75f);
            level.setColor(Game.Colour.RANK);
            level.setPadding(4);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
            level.setPosition(4, 40);
            level.setText(Integer.toString(record.highLevel));
            addChild(level);
        }

        // name
        name = new QQText(assets.getFont(Game.Font.NAME20));
        name.setColor(Game.Colour.RARE);
        name.setText(assets.geti18n(record.heroClass.key));
        //name.setSize(this.width - 64, 24);
        name.setPosition(64, 32);
        addChild(name);

        // scoreOrRound
        if (Game.Mode.DUNGEON == gameMode && 0 < record.highScore) {
            scoreOrRound = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("rank"));
            scoreOrRound.setSize(64, 16);
            //scoreOrRound.setPosition(this.width - 64 - 4, 40);
            scoreOrRound.setAlign(Align.right);
            scoreOrRound.setColor(Game.Colour.RANK);
            scoreOrRound.setText(Integer.toString(record.highScore));
            addChild(scoreOrRound);
        } else if (Game.Mode.COLOSSEUM == gameMode && 0 < record.maxRound) {
            scoreOrRound = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("cost_soul"));
            scoreOrRound.setSize(64, 16);
            //scoreOrRound.setPosition(...);
            scoreOrRound.setAlign(Align.right);
            scoreOrRound.setColor(Game.Colour.ROUND);
            scoreOrRound.setText(Integer.toString(record.maxRound));
            addChild(scoreOrRound);
        }

        // help
        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.geti18n(record.heroClass.key + "_help"));



    }


    @Override
    protected void resetWrapHeight() {
        //Gdx.app.error("PreviewView", "width = " + width + "@" + this);
        // if width not set, can not calculate height of font with multi line.
        if (0 == width) // QQView.MATCH_PARENT == width)
            return;

        // 1. calculate help height...



        float height = topPadding + bottomPadding;

        // font : Name (26, 20, 38)
        GlyphLayout glName = new GlyphLayout();
        glName.setText(fntName, "name");
        height += 20;//glyphLayout.height;
        //Gdx.app.error("TEST", "name height = " + glyphLayout.height);
        // widget margin
        height += 4;



        // font : Desc (16, 12, 23)
        GlyphLayout glDesc = new GlyphLayout();
        descW = (int)(this.width - leftPadding - rightPadding - 8 - 48);
        glDesc.setText(fntDesc, desc, 0, 50, Color.WHITE, descW, Align.topLeft, true, null);
        height += glDesc.height - 12 + 16;
        //Gdx.app.error("TEST", "desc height = " + glDesc.height);
        //Gdx.app.error("TEST", "desc glyph = " + glDesc.glyphCount);

        this.height = height;
        //Gdx.app.error("TEST", "size = " + this.width + "," + this.height);

        // icon position
        iconShiftX = leftPadding;
        iconShiftY = this.height - topPadding - 48;
        //Gdx.app.error("TEST", "icon shift = " + iconShiftX + "," + iconShiftY);

        nameShiftX = iconShiftX + 48 + 8 + (descW - glName.width) / 2;
        nameShiftY = this.height - topPadding;
        //Gdx.app.error("TEST", "name shift = " + nameShiftX + "," + nameShiftY);

        descShiftX = iconShiftX + 48 + 8;
        descShiftY = this.height - topPadding - 20 - 4;
        //Gdx.app.error("TEST", "desc shift = " + descShiftX + "," + descShiftY);


    }


    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {

        // draw icon
        //batch.draw(icon, (int)(originX + iconShiftX), (int)(originY + iconShiftY), 48, 48);

        // draw name
        //fntName.setColor(Game.Colour.RARE);
        //fntName.draw(batch, name, originX + nameShiftX, originY + nameShiftY);

        // draw desc
        //fntDesc.draw(batch, desc, originX + descShiftX, originY + descShiftY, 0, 50, descW, Align.topLeft, true, null);
    }

    @Override
    public void dispose() {}

    /*
        IsParent series
     */
    private ArrayList<QQView> childrenViews = new ArrayList<>();
    @Override
    public void addChild(QQView view) {
        childrenViews.add(view);
    }

    @Override
    public void removeChild(QQView view) {

    }

    @Override
    public void arrangeChildren() {
        if (0 >= this.width || 0 >= this.height)
            return;

        //Gdx.app.error("PreviewView", "arrangeChildren" + this.width + "," + this.height);
        name.setSize(this.width - 64, 24);
        if (null != scoreOrRound) {
            scoreOrRound.setPosition(this.width - 64 - 4, 40);
        }
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView v : childrenViews)
            v.draw(batch, originX, originY);
    }
}
