package com.qqhouse.ui.animation;

public final class QQRemoveAnimation extends QQAnimation {

    private float removeVelocity, endX;
    public QQRemoveAnimation(float removeVelocity, float endX) {
        this.removeVelocity = removeVelocity;
        this.endX = endX;
    }

    @Override
    public boolean goOn(float delta) {
        float x = target.getX() + delta * removeVelocity;
        target.setPosition(x, target.getY());
        return x < endX;
    }

    public QQRemoveAnimation listener(AnimationListener listener) {
        addListener(listener);
        return this;
    }
}
