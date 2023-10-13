package com.qqhouse.ui;

public abstract class QQAnimation {

    public interface AnimationListener {
        void onAnimationStart();
        void onAnimationEnd();
    }

    protected QQView target;
    private AnimationListener listener;
    private boolean running;

    public void start() {
        if (null != listener)
            listener.onAnimationStart();
        running = true;
    }

    public void stop() {
        if (null != listener)
            listener.onAnimationEnd();
        running = false;
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
