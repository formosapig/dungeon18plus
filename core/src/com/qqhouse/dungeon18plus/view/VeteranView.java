package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Feat;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.hero.ColosseumHero;
import com.qqhouse.dungeon18plus.struct.hero.DungeonHero;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQProgress;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class VeteranView extends AssetGroupButton {

    /*
        QQIcon : blockee
        UniqueSkillView : unique skill
        QQIconText : round
        QQProgress : life
     */

    private QQImage hero;
    private UniqueSkillView skill;
    private QQIconText round;
    private QQProgress life;

    public VeteranView(Assets assets) {
        super(assets);
    }

    public void reset(Veteran veteran) {
        hero = new QQImage(assets.getBlockee(veteran.heroClass.key));
        addChild(hero);

        skill = new UniqueSkillView(assets);
        skill.reset(null, false, veteran);
        skill.setSize(this.getWidth() - 8 - 8 - 48, 32);
        addChild(skill);

        round = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16("cost_soul"));
        round.setText(Integer.toString(veteran.round));
        round.setSize(QQView.WRAP_CONTENT, 16);
        addChild(round);

        setBackground(assets.getBackgroundSet(veteran.heroClass.alignment.key));
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;

        hero.setPosition(8, 8);

        skill.setPosition(60, 32);

        round.setPosition(60, 6);





    }

}
