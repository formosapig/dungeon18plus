package com.qqhouse.ui;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

public abstract class QQScreen extends InputAdapter {

    // use gesture detector to detect gesture....

    // empty screen with do nothing...
    public static final QQScreen EMPTY = new QQScreen() {
        @Override
        public void onEnter() {}

        @Override
        public void onLeave() {}

        @Override
        public void act(float delta) {}

        @Override
        public void draw(SpriteBatch batch) {}
    };

    abstract public void onEnter();

    abstract public void onLeave();

    abstract public void act(float delta);
    abstract public void draw(SpriteBatch batch);

    public void pause() {}

    public void resume() {}
}
