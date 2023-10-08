package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQProgress extends QQView {

    private NinePatch progress; // TODO 改成貼 texture
    private int percent;

    public QQProgress(NinePatch bg, NinePatch progress) {
        this.bgNormal = bg;
        this.progress = progress;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (percent > 0 && percent <= 100) {
            float w = width * percent / 100;
            progress.draw(batch, originX, originY, w, height);
        }
    }

}
