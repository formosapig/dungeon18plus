package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Monster;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class MonsterView extends AssetGroup /*implements QQView.IsParent*/ {

    private QQImage icon;
    private QQText level;

    public MonsterView(Assets assets) {
        super(assets);
    }

    public void reset(Monster monster) {
        icon = new QQImage(assets.getBlockee(monster.type.icon));
        icon.setPosition(8, 8);
        addChild(icon);

        level = new QQText(assets.getFont(Game.Font.LEVEL16), new NinePatch(assets.getTexture("background", "zako_level"), 4, 4, 4, 4), 0.75f);
        level.setColor(Game.Colour.ZAKO_LEVEL);
        level.setPadding(4);
        level.setSize(QQView.WRAP_CONTENT, QQView.WRAP_CONTENT);
        level.setPosition(4, 40);
        level.setText(Integer.toString(monster.level));
        addChild(level);

        setBackground(assets.getNinePatchBG(monster.type.align.key));
    }

    /*
        IsParent series
     */
    /*
    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        for (QQView child : childrenView)
            child.draw(batch, originX, originY);
    }
    */
}
