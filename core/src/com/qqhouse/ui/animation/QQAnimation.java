package com.qqhouse.ui.animation;

import com.qqhouse.ui.QQView;

public abstract class QQAnimation {

    public interface AnimationListener {
        void onAnimationStart(QQView target);
        void onAnimationEnd(QQView target);
    }

    protected QQView target;
    private AnimationListener listener;

    public void setTarget(QQView view) {
        target = view;
    }

    public void start() {
        if (null != listener)
            listener.onAnimationStart(target);
    }

    public void stop() {
        if (null != listener)
            listener.onAnimationEnd(target);
        target.removeAnimation();
    }

    public void act(float delta) {
        if (!goOn(delta))
            stop();
    }

    public abstract boolean goOn(float delta);

    public void addListener(AnimationListener listener) {
        this.listener = listener;
    }
}
