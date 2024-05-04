package com.qqhouse.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.io.QQSaveGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public abstract class QQGameMachine implements ApplicationListener {

    /*
        constructor
     */
    private static final int EMPTY_STATE = -1;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    protected Viewport viewport;
    protected QQSaveGame savedGame;
    private boolean debug = false;
    private BitmapFont font;
    private StringBuilder builder;
    private long screenStartTime;
    private long screenStartJavaHeap;
    private Stack<QQScreen> screens;
    private float width;

    protected void initial(int width, int height) {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new StretchViewport(width, height, camera);

        this.width = width;

        screens = new Stack<>();


        if (debug) {
            font = new BitmapFont();
            builder = new StringBuilder();
        }
    }

    private QQScreen current;

    protected void setRoot(QQScreen screen) {
        current = screen;
        screens.push(screen);

        current.onEnter();
        Gdx.input.setInputProcessor(current);
        //Gdx.app.error("QQgameMachine", "Screen Stack : " + screens.size());
    }

    protected void push(QQScreen screen) {
        Gdx.input.setInputProcessor(null);
        if (null != current) {
            current.onLeave();
        }

        current = screen;
        current.setSwipeRightCallback(callback, width);
        screens.push(screen);

        current.onEnter();
        Gdx.input.setInputProcessor(current);
        //Gdx.app.error("QQgameMachine", "Screen Stack : " + screens.size());
    }

    protected void swap(QQScreen screen) {
        Gdx.input.setInputProcessor(null);
        if (null != current) {
            current.onLeave();
        }

        screens.pop();

        current = screen;
        current.setSwipeRightCallback(callback, width);
        screens.push(screen);

        current.onEnter();
        Gdx.input.setInputProcessor(current);
        //Gdx.app.error("QQgameMachine", "Screen Stack : " + screens.size());
    }

    protected void popup() {
        Gdx.input.setInputProcessor(null);
        if (null != current) {
            current.onLeave();
        }

        screens.pop();

        current = screens.peek();

        current.onEnter();
        Gdx.input.setInputProcessor(current);
        //Gdx.app.error("QQgameMachine", "Screen Stack : " + screens.size());
    }

    private QQScreen.SwipeRightCallback callback = new QQScreen.SwipeRightCallback() {
        @Override
        public void onSwipeRight() {
            popup();
        }
    };

    /*
        Application Listener
     */
    @Override
    public void resize(int width, int height) {
        //Gdx.app.error("QQGameMachine", "width, height =" + width + "," + height);
        //Gdx.app.error("QQGameMachine", "screenX, screenY =" + viewport.getScreenX() + "," + viewport.getScreenY());
        //viewport.setScreenY(Gdx.graphics.getSafeInsetBottom());
        //viewport.setScreenX(Gdx.graphics.getSafeInsetLeft());
        // camera 的世界座標 0,0 在左下角
        // viewport 的世界座標 0,0 在左上角 ... 笑死...
        viewport.setScreenBounds(Gdx.graphics.getSafeInsetLeft(),
                Gdx.graphics.getSafeInsetTop(),
                width - Gdx.graphics.getSafeInsetLeft() - Gdx.graphics.getSafeInsetRight(),
                height - Gdx.graphics.getSafeInsetTop() - Gdx.graphics.getSafeInsetBottom());
        viewport.apply(false);
        //Gdx.app.error("QQGameMachine", "screenX, screenY =" + viewport.getScreenX() + "," + viewport.getScreenY());

        //viewport.update(width - Gdx.graphics.getSafeInsetLeft() - Gdx.graphics.getSafeInsetRight(),
        //        height - Gdx.graphics.getSafeInsetTop() - Gdx.graphics.getSafeInsetBottom());
        //Gdx.app.error("QQGameMachine", "screenX, screenY =" + viewport.getScreenX() + "," + viewport.getScreenY());

    }

    //private boolean firstRender = false;

    @Override
    public void render() {
        // TODO what is this ? I can't understand.
        current.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        // draw
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        current.draw(batch);

        if (null != font) {
            builder.clear();
            builder.append("FPS:").append(Gdx.graphics.getFramesPerSecond())
                    .append(" Java:").append(Gdx.app.getJavaHeap() / 1024)
                    .append(" Native:").append(Gdx.app.getNativeHeap() / 1024);
            font.draw(batch, builder, 4, 20);
        }

        batch.end();
    }

    @Override
    public void pause() {
        current.pause();
        //mCurrentScreen.pause();
        savedGame.save();
    }

    @Override
    public void resume() {
        current.resume();
        //mCurrentScreen.resume();
        savedGame.load();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //changeState(EMPTY_STATE);
    }
}
