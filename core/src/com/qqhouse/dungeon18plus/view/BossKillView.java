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
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;

// TODO 要改名, 跟 struct/BossKill 一起.
public class BossKillView extends AssetGroup {

    private QQText score;
    private QQText help;

    public BossKillView(Assets assets) {
        super(assets);
        setPadding(4, 4, 4, Game.Size.BLOCKEE_PADDING);
    }

    public void reset(BossKill kill) {
        //Gdx.app.error("BossKillView.reset()", "width = " + this.width);
        QQImage icon = new QQImage(assets.getBlockee(kill.boss.icon));
        icon.setBackground(assets.getNinePatchBG(kill.boss.align.key));
        //icon.setSize(32, 32);
        icon.setPosition(leftPadding, bottomPadding);
        addChild(icon);

        score = new QQText(assets.getFont(Game.Font.DIGITAL16));
        score.setText(Integer.toString(kill.score));
        //score.setPosition(262, 34);
        score.setColor(Game.Colour.RANK);
        score.setAlign(Align.right);
        //score.setSize(50, 24);
        //score.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(score);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.formati18n("kill_turn", kill.turn));
        //help.setSize(248, 24);
        help.setAlign(Align.left);
        help.setPosition(64, 6);
        //help.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(help);

        //bgNormal = new NinePatch(assets.getBackground(kill.boss.align.key), 4, 4, 4, 4);
        //Gdx.app.error("BossKillView", "boss kill view width = " + width);
    }

    @Override
    public void arrangeChildren() {
        if (0 == width || 0 == height)
            return;
        //Gdx.app.error("BossKillView.arrangeChildren()", "width = " + this.width);
        score.setSize(50, 24);
        score.setPosition(this.width - rightPadding - score.getWidth(), 34);

        help.setSize(this.width - rightPadding - 64, 24);
    }

}
