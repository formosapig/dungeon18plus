package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;

public class QQDialog extends QQGroup {

    // FIXME 不能使用專案專屬資源, 因為QQ系列算是 SDK..
    private Assets assets;
    public QQDialog(Assets assets, QQView customView, boolean isModal) {
        this.assets = assets;

        final QQView dialogMask = new QQView();

        // FIXME SDK 不能依存於專案, assets 不是能隨時存取到的資源...
        dialogMask.bgNormal = new NinePatch(assets.getBackground("black"), 4, 4, 4, 4);
        dialogMask.bgNormal.setColor(new Color(1, 1, 1, 0.66f));
        dialogMask.setPosition(0, 0);
        dialogMask.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);

        // add click listener to dialogMask , any click on it will dismiss dialog.
        if (!isModal) {
            dialogMask.addPressListener(new QQView.PressListener() {
                @Override
                public void onPress(QQView view) {
                    if (view == dialogMask) {
                        dismiss();
                    }
                }

                @Override
                public void onLongPress(QQView view) {}
            });
        }

        addChild(dialogMask);



        // calculate view's size
        if (customView.matchWidth)
            customView.setSize(Game.Size.WIDTH - 10 - 10, customView.getHeight());

        // set position
        customView.setPosition((Game.Size.WIDTH - customView.getWidth())/2,
                (Game.Size.HEIGHT - customView.getHeight())/2);

        addChild(customView);


    }

    // remove dialog from parent.
    public void dismiss() {
        if (null != parent) {
            parent.removeChild(this);
        }
    }

    /*
        handle all touch events, because this is dialog.
     */



}
