package com.qqhouse.ui.animation;

public final class QQInsertAnimation extends QQAnimation {
    //private final float removeSpeed = Game.WIDTH / 0.33f;

    private float velocity, endX, delay;
    public QQInsertAnimation(float velocity, float endX, float delay) {
        this.velocity = velocity;
        this.endX = endX;
        this.delay = delay;
    }

    @Override
    public boolean goOn(float delta) {
        if (delay > 0) {
            delay -= delta;
        } else {
            //Gdx.app.error("QQList.InsertAnimation.goOn", "go on.");
            float x = target.getX() + delta * velocity;
            target.setPosition(x, target.getY());
            if (x >= endX)
                return false;
        }
        return true;
    }

    /*
        chaining method
     */
    public QQInsertAnimation listener(AnimationListener listener) {
        addListener(listener);
        return this;
    }
}
