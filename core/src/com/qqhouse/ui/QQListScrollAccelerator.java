package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;

public class QQListScrollAccelerator {

    public interface ScrollCallback {
        boolean doScroll(float shift);
    }

    private float startPos = -1;
    private float time;
    private float velocity;
    private static final float acceleration = 0.5f;
    private ScrollCallback callback;

    public QQListScrollAccelerator(ScrollCallback callback) {
        this.callback = callback;
    }

    public void act(float delta) {
        // velocity
        if (-1 != startPos) {
            time += delta;
        }
        if (Math.abs(velocity) > 0) {
            float shift = velocity * delta;
            //Gdx.app.error("QQListScrollAccelerator", "velocity : " + velocity);
            //Gdx.app.error("QQListScrollAccelerator", "shift : " + shift);
            if (acceleration >= Math.abs(velocity)) {
                velocity = 0;
            } else {
                // velocity decrease...
                if (0 > velocity)
                    velocity += acceleration;
                else
                    velocity -= acceleration;
            }
            if (null != callback && callback.doScroll(shift))
                velocity = 0;
        }
    }

    public void touchDown(float pos) {
        startPos = pos;
        time = 0;
        velocity = 0;
    }

    public void touchUp(float pos) {
        if (-1 != startPos) {
            velocity = (pos - startPos) / time;
            startPos = -1;
            time = 0;
        }
    }

}
