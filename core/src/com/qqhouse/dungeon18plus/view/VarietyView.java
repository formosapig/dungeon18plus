package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.Variety;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQView;

public class VarietyView extends AssetGroup {

    private final int VARIETY_FILTER = Variety.Type.LIFE | Variety.Type.ATTACK | Variety.Type.DEFENSE | Variety.Type.SPEED |
            Variety.Type.COIN | Variety.Type.KEY | Variety.Type.STAR;
    private QQLinear container;

    public VarietyView(Assets assets) {
        super(assets);
        container = new QQLinear(false, Game.Size.WIDGET_MARGIN);//INNER_MARGIN);
        //container.setPosition(0, 0);
        addChild(container);
    }

    public void update(Ability ability) {
        //life.setText(Integer.toString(ability.life));
        //attack.setText(Integer.toString(ability.attack));
        //defense.setText(Integer.toString(ability.defense));
        //speed.setText(Integer.toString(ability.speed));
        //rearrange();

        // add ability Life / Attack / Defense / Speed


        //arrangeChildren();
    }

    public void update(Variety... variers) {
        // FIXME 1111 不需要反覆的創建 QQIconText...
        container.removeAllChildren();
        //childrenView.clear();

        for (Variety varier : variers) {
            if ((varier.type & VARIETY_FILTER) == 0 || varier.isLimit())
                continue;

            QQIconText itVarier = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon(varier.getIcon16Key()));
            itVarier.setText(varier.getText(true));
            itVarier.setColor(varier.getColor());
            itVarier.setAlign(Align.right);
            itVarier.setSize(QQView.WRAP_CONTENT, 16);
            container.addChild(itVarier);
        }

    }

    public void update32(Variety... varieties) {
        container.removeAllChildren();

        for (Variety var : varieties) {
            if ((var.type & VARIETY_FILTER) == 0 || var.isLimit())
                continue;

            QQIconText itVar = new QQIconText(assets.getFont(Game.Font.HERO_ABILITY), assets.getIcon(var.getIconKey()));
            itVar.setText(var.getText(true));
            itVar.setColor(var.getColor());
            itVar.setAlign(Align.right);
            itVar.setSize(QQView.WRAP_CONTENT, 32);
            container.addChild(itVar);
        }
    }

    @Override
    public void onParentSizeChanged(float w, float h) {
        container.setSize(w, h);
    }

}
