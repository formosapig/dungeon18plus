package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Dungeon18Plus;

public class LibraryScreen implements Screen {
    private final Dungeon18Plus game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    // ui components
    //private Button btnDungeon;

    //Texture img;

    public LibraryScreen(final Dungeon18Plus game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 640);
        viewport = new StretchViewport(480, 640, camera);
        //viewport = new FitViewport(480, 640, camera);
        //viewport = new FillViewport(480, 640, camera);
        stage = new Stage(viewport, game.batch);
    }
    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                Gdx.app.log("TEST", "touchDown.");
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                Gdx.app.log("TEST", "tap.");
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                Gdx.app.log("TEST", "longPress.");
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                Gdx.app.log("TEST", "fling : " + velocityX + "," + velocityY);
                if (velocityX > 200) {
                    game.setScreen(new MainMenuScreen(game));
                }
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                Gdx.app.log("TEST", "pan.");
                return false;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                Gdx.app.log("TEST", "panStop.");
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                Gdx.app.log("TEST", "zoom.");
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                Gdx.app.log("TEST", "pinch.");
                return false;
            }

            @Override
            public void pinchStop() {
                Gdx.app.log("TEST", "pinchStop.");
            }
        }));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
