package com.qqhouse.dungeon18plus.view;

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
        score.setPosition(0, 0);
        score.setSize(100, 20);
        addChild(score);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText("killasdf andsf " + kill.turn);
        help.setSize(200, 20);
        help.setPosition(60, 10);
        addChild(help);

        bgNormal = new NinePatch(assets.getBackground(kill.boss.align.key), 4, 4, 4, 4);
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {

        // draw icon
        batch.draw(icon, 8, 8);//(int)(originX + iconShiftX), (int)(originY + iconShiftY), 48, 48);

        // draw name
        //fntName.setColor(Game.Colour.RARE);
        //fntName.draw(batch, name, originX + nameShiftX, originY + nameShiftY);

        // draw desc
        //fntDesc.draw(batch, desc, originX + descShiftX, originY + descShiftY, 0, 50, descW, Align.topLeft, true, null);
    }

}
