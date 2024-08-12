package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.BossKill;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

public class BossKillView extends AssetGroup {

    private QQText score;
    private QQText help;

    // 僅有一個地方使用, 初始化時把 padding 也設定好...
    public BossKillView(Assets assets, BossKill kill) {
        super(assets);
        setPadding(4, 4, 4, Game.Size.BLOCKEE_PADDING);

        QQImage icon = new QQImage(assets.getBlockee(kill.boss.icon));
        icon.setSize(40, 40); // scale...
        icon.setPosition(leftPadding + 4, bottomPadding + 4);
        addChild(icon);

        score = new QQText(assets.getFont(Game.Font.DIGITAL16));
        score.setText(Integer.toString(kill.score));
        score.setSize(QQView.WRAP_CONTENT, 24);
        score.setColor(Game.Colour.RANK);
        score.setAlign(Align.right);
        addChild(score);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.formati18n("kill_turn", kill.turn));
        help.setAlign(Align.left);
        help.setPosition(60, 4);
        addChild(help);
    }

    public void reset(BossKill kill) {

        //Gdx.app.error("BossKillView.reset()", "width = " + this.width);
        QQImage icon = new QQImage(assets.getBlockee(kill.boss.icon));
        //icon.setBackground(assets.getNinePatchBG(kill.boss.align.key));
        //icon.setSize(56, 56);
        icon.setSize(40, 40); // scale...
        icon.setPosition(leftPadding + 4, bottomPadding + 4);
        addChild(icon);

        score = new QQText(assets.getFont(Game.Font.DIGITAL16));
        score.setText(Integer.toString(kill.score));
        //score.setPosition(262, 34);
        score.setSize(QQView.WRAP_CONTENT, 24);
        score.setColor(Game.Colour.RANK);
        score.setAlign(Align.right);
        //score.setSize(50, 24);
        //score.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(score);

        help = new QQText(assets.getFont(Game.Font.HELP14));
        help.setText(assets.formati18n("kill_turn", kill.turn));
        //help.setSize(248, 24);
        help.setAlign(Align.left);
        help.setPosition(60, 4);
        //help.setBackground(new NinePatch(assets.getBackground("blessed"), 4, 4, 4, 4));
        addChild(help);

        //bgNormal = new NinePatch(assets.getBackground(kill.boss.align.key), 4, 4, 4, 4);
        //Gdx.app.error("BossKillView", "boss kill view width = " + width);
        //setBackground(assets.getNinePatchBG(kill.boss.align.key));
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 >= width || 0 >= height)
            return;
        help.setSize(width - rightPadding - 60, 24);
    }

    @Override
    public void arrangeChildren() {
        if (0 >= width || 0 >= height)
            return;
        score.setPosition(width - rightPadding - score.getWidth(), 28);
    }

}
