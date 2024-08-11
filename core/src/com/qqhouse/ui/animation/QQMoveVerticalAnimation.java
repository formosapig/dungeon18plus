package com.qqhouse.ui.animation;

public final class QQMoveVerticalAnimation extends QQAnimation {

    private float velocity, distance, delay;

    public QQMoveVerticalAnimation(float velocity, float distance, float delay) {
        this.velocity = velocity;
        this.distance = distance;
        this.delay = delay;
        //Gdx.app.error("QQList.MVA.()", "distance = " + distance);
    }

    @Override
    public boolean goOn(float delta) {
        // delay first
        if (delay >= 0) {
            delay -= delta;
        } else {
            distance -= Math.abs(delta * velocity);
            //Gdx.app.error("QQList.goOn", "shift = " + Math.abs(delta * velocity));
            if (distance <= 0) {
                // TODO 改一個寫法, 感覺計算太複雜了. 效果OK
                float shift = Math.abs(delta * velocity);
                float rate = (shift + distance) / shift;
                //Gdx.app.error("QQList.goOn", "distance = " + distance + ",rate = " + rate);
                float y = target.getY() + (delta * velocity) * rate;
                target.setPosition(target.getX(), y);
                return false;
            }
            float y = target.getY() + delta * velocity;
            target.setPosition(target.getX(), y);
            //Gdx.app.error("QQList.MVA.GoOn", "" + MoveVerticalAnimation.this + "distance = " + distance);
        }
        return true;
    }

    public QQMoveVerticalAnimation listener(AnimationListener listener) {
        addListener(listener);
        return this;
    }
}
