package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.Texture;
import com.qqhouse.ui.QQButton;
import com.qqhouse.ui.QQIconText;

public class ActionView extends QQButton {

    /*
        fixed icon.
        fixed cost icon.
        fixed cost value ?
     */

    public ActionView(String buttonKey) {
        super(buttonKey);
        //cost = new QQIconText();
    }

    private Texture icon;
    private QQIconText cost;



}
