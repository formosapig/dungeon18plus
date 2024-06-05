package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.struct.Ability;
import com.qqhouse.dungeon18plus.struct.Varier;
import com.qqhouse.ui.QQIconText;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQView;

public class VarierView extends AssetGroup {

    private final int VARIER_FILTER = Varier.Type.LIFE | Varier.Type.ATTACK | Varier.Type.DEFENSE | Varier.Type.SPEED |
            Varier.Type.COIN | Varier.Type.KEY | Varier.Type.STAR;
    private QQLinear container;

    public VarierView(Assets assets) {
        super(assets);
        container = new QQLinear(false, Game.Size.INNER_MARGIN);
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

    public void update(Varier... variers) {
        // FIXME 1111 不需要反覆的創建 QQIconText...
        container.removeAllChildren();
        //childrenView.clear();

        for (Varier varier : variers) {
            if ((varier.type & VARIER_FILTER) == 0 || varier.isLimit())
                continue;

            QQIconText itVarier = new QQIconText(assets.getFont(Game.Font.DIGITAL16), assets.getIcon16(varier.getIcon16Key()));
            itVarier.setText(varier.getText(true));
            itVarier.setColor(varier.getColor());
            itVarier.setAlign(Align.right);
            itVarier.setSize(QQView.WRAP_CONTENT, 16);
            container.addChild(itVarier);
        }

    }

    @Override
    public void onParentSizeChanged(float w, float h) {
        container.setSize(w, h);
    }

}
