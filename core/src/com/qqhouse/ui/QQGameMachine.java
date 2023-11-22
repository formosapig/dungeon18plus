package com.qqhouse.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.io.QQSaveGame;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class QQGameMachine implements ApplicationListener {

    /*
        constructor
     */
    private static final int EMPTY_STATE = -1;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    protected Viewport viewport;
    protected QQSaveGame savedGame;
    private BitmapFont font;

    protected void initial(int width, int height, boolean debug) {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new StretchViewport(width, height, camera);

        // insert empty screen...
        mScreens = new HashMap<Integer, QQScreen>();

        if (debug) {
            font = new BitmapFont();
        }
    }

    /*
        state machine series...
     */
    private Map<Integer, QQScreen> mScreens;
    private int mState;
    private QQScreen mCurrentScreen;
    private QQScreen current;

    protected void addState(int state, QQScreen screen) {
        if (mScreens.containsKey(state)) {
            Gdx.app.error("Game Engine", "duplicate state.");
            return;
        }
        mScreens.put(state, screen);
    }

    // change current state to target state
    public void changeState(int targetState) {
        // current state onLeave
        Gdx.input.setInputProcessor(null);
        if (null != mCurrentScreen) {
            mCurrentScreen.onLeave();
        }

        // change state
        mState = targetState;
        mCurrentScreen = mScreens.get(mState);

        // current state onEnter
        if (null != mCurrentScreen) {
            mCurrentScreen.onEnter();
            Gdx.input.setInputProcessor(mCurrentScreen);
        }
    }

    protected void changeScreen(QQScreen screen) {
        Gdx.input.setInputProcessor(null);
        if (null != current) {
            current.onLeave();
        }

        current = screen;

        current.onEnter();
        Gdx.input.setInputProcessor(current);
    }

    // get game state.
    public int getState() {
        return mState;
    }

    public QQScreen getScreen() { return mCurrentScreen; }

    /*
        Application Listener
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
            String info = String.format(Locale.US, "FPS:%d Mem:%dK, %dK",
                    Gdx.graphics.getFramesPerSecond(),
                    Gdx.app.getJavaHeap() / 1024,
                    Gdx.app.getNativeHeap() / 1024);
            font.draw(batch, info, 4, 16);
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
