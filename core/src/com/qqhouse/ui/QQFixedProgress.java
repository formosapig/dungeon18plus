package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class QQFixedProgress extends QQView {

    public static class Parameter {
        public TextureRegion back;
        public TextureRegion progress;
        public int leftGap;
        public int bottomGap;
        public int maxWidth;
    }

    private TextureRegion back, progress;
    private int percent;
    private int leftGap, bottomGap, maxWidth; // for progress.

    public QQFixedProgress(Parameter parameter) {
        back = parameter.back;
        progress = parameter.progress;
        leftGap = parameter.leftGap;
        bottomGap = parameter.bottomGap;
        maxWidth = parameter.maxWidth;
    }

    public void setPercent(int percent) {
        if (0 <= percent && 100 >= percent)
            this.percent = percent;
    }

    @Override
    public void drawForeground(SpriteBatch batch, float originX, float originY) {
        batch.draw(back, originX, originY);
        batch.draw(progress, originX + leftGap, originY + bottomGap, progress.getRegionWidth(), maxWidth * percent / 100);
    }

}
