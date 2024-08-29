package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;

public class QQScrollAccelerator {

    public interface ScrollCallback {
        boolean doScroll(float shift);
    }

    private float startPos = -1;
    private float time;
    private float velocity;
    // 注意 desktop 模組的 FPS 高達 1000, 所以加減速的效果會變異...
    private static final float acceleration = 20f;
    private ScrollCallback callback;

    public QQScrollAccelerator(ScrollCallback callback) {
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

    public boolean touchDownAndCancel(float pos) {
        boolean cancel = Math.abs(velocity) > 0;
        startPos = pos;
        time = 0;
        velocity = 0;
        return cancel;
    }

    public void touchUp(float pos) {
        if (-1 != startPos) {
            velocity = (pos - startPos) / time;
            startPos = -1;
            time = 0;
        }
    }
}
