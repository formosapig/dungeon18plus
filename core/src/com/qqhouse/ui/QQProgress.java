package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QQProgress extends QQView {

    private NinePatch progress;
    private NinePatch secondaryProgress;
    private int percent, secondaryPercent;

    public QQProgress(NinePatch bg, NinePatch progress) {
        this.bgNormal = bg;
        this.progress = progress;
    }

    public QQProgress(NinePatch bg, NinePatch progress, NinePatch secondaryProgress) {
        this.bgNormal = bg;
        this.progress = progress;
        this.secondaryProgress = secondaryProgress;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setSecondaryProgress(int percent) {
        secondaryPercent = percent;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        if (null != secondaryProgress && secondaryPercent > 0 && secondaryPercent <= 100) {
            float w = (width - 2) * secondaryPercent / 100;
            secondaryProgress.draw(batch, originX + 1, originY, w, height);
        }
        if (percent > 0 && percent <= 100) {
            float w = (width - 2) * percent / 100;
            progress.draw(batch, originX + 1, originY, w, height);
        }
    }

}
