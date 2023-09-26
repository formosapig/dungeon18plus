package com.qqhouse.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.io.QQSaveGame;

import java.util.HashMap;
import java.util.Map;

public abstract class QQGameMachine implements ApplicationListener {

    /*
        constructor
     */
    private static final int EMPTY_STATE = -1;


    private SpriteBatch batch;
    private OrthographicCamera camera;
    protected Viewport viewport;
    protected QQSaveGame saveGame;
    //public QQGameMachine(int width, int height) {
    //    batch = new SpriteBatch();
    //    camera = new OrthographicCamera();
    //    camera.setToOrtho(false, width, height);
    //    viewport = new StretchViewport(width, height, camera);

        // insert empty screen...
        //mScreens = new HashMap<Integer, QQScreen>();
        //mScreens.put(EMPTY_STATE, QQScreen.EMPTY);
    //}

    protected void initial(int width, int height) {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new StretchViewport(width, height, camera);

        // insert empty screen...
        mScreens = new HashMap<Integer, QQScreen>();
    }

    /*
        state machine series...
     */
    private Map<Integer, QQScreen> mScreens;
    private int mState;
    private QQScreen mCurrentScreen;

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

    @Override
    public void render() {
        // act, calculate ui actions and so on....
        // minimum is 30 fps
        mCurrentScreen.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        // draw
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mCurrentScreen.draw(batch);
        batch.end();
    }

    @Override
    public void pause() {
        mCurrentScreen.pause();
        saveGame.save();
    }

    @Override
    public void resume() {
        mCurrentScreen.resume();
        saveGame.load();
    }

    @Override
    public void dispose() {
        batch.dispose();
        changeState(EMPTY_STATE);
    }
}
