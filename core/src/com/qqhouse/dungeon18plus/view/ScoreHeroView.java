package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.event.BattleEvent;
import com.qqhouse.dungeon18plus.struct.event.Event;
import com.qqhouse.dungeon18plus.struct.hero.ScoreHero;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;
import java.util.Locale;

public class ScoreHeroView extends AssetGroup {

    public ScoreHeroView(Assets assets) {
        super(assets);
    }

    private QQImage icon;
    private QQText level, ranking;
    private QQIconText score;
    private AbilityView ability; // ability of foe or something...


    public void reset(ScoreHero hero, int rankingNum) {

        // icon
        icon = new QQImage(assets.getBlockee(hero.heroClass.key));
        icon.setPosition(8, 8);
        addChild(icon);

        // level
        if (0 < hero.level) {
            level = new QQText(assets.getFont(Game.Font.LEVEL16), assets.getNinePatchBG("level"), 0.75f);
            level.setColor(Game.Colour.RANK);
            level.setPadding(4);
            level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
            //level.setSize(24, 16);
            level.setPosition(4, 40);
            level.setText(Integer.toString(hero.level));
            addChild(level);
        }

        // ranking
        ranking = new QQText(assets.getFont(Game.Font.EVENT_COST));
        ranking.setSize(64, 32);
        ranking.setPosition(68, 26);
        ranking.setAlign(Align.left);
        ranking.setColor(Game.Colour.RARE);
        ranking.setText(Integer.toString(rankingNum));
        addChild(ranking);

        // score
        score = new QQIconText(assets.getFont(Game.Font.EVENT_COST));//, assets.getIcon("icon/rank"));
        score.setColor(Game.Colour.RANK);
        score.setPadding(4);
        score.setSize(88, 32);
        score.setAlign(Align.right);
        score.setPosition(Game.Size.WIDTH - 8 - 88, 26);
        score.setText(Integer.toString(hero.score));
        //score.setBackground(assets.getNinePatchBG("blessed"));
        addChild(score);

        // ability view
        ability = new AbilityView(assets);
        ability.setSize(200, 16);
        ability.setPosition(Game.Size.WIDTH - 200 - 8, 6);
        ability.update(hero);
        addChild(ability);

        //Gdx.app.error("EventView", "event :" + event.type + ",bg : " + event.type.align.key);
        setBackground(assets.getNinePatchBG(hero.heroClass.alignment.key));
    }

}
