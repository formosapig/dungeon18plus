package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQText;

public class BossKillView extends QQGroup {

    private Texture icon;
    private QQIconText score;
    private QQText help;
    private Assets assets;

    public BossKillView(Assets assets) {
        this.assets = assets;
    }

    public void reset(BossKill kill) {
        icon = assets.getBlockee(kill.boss.icon);

        score = new QQIconText(assets.getFont(Game.Font.DIGITAL16),
                assets.getIcon32("rank"));
        score.setText(Integer.toString(kill.score));
        score.setPosition(262, 34);
        score.setColor(Game.Colour.RANK);
        score.setAlign(Align.right);
        score.setSize(50, 24);
        //score.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(score);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.formati18n("kill_turn", kill.turn));
        help.setSize(248, 24);
        help.setAlign(Align.left);
        help.setPosition(64, 6);
        //help.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(help);

        bgNormal = new NinePatch(assets.getBackground(kill.boss.align.key), 4, 4, 4, 4);
        //Gdx.app.error("BossKillView", "boss kill view width = " + width);
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {

        // draw icon
        batch.draw(icon, originX + 8, originY + 8);//(int)(originX + iconShiftX), (int)(originY + iconShiftY), 48, 48);

        // draw name
        //fntName.setColor(Game.Colour.RARE);
        //fntName.draw(batch, name, originX + nameShiftX, originY + nameShiftY);

        // draw desc
        //fntDesc.draw(batch, desc, originX + descShiftX, originY + descShiftY, 0, 50, descW, Align.topLeft, true, null);
    }

}
