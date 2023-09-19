package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Dungeon18Plus;

public class TowerScreen implements Screen {

    private final Dungeon18Plus game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    // ui components
    //private Button btnDungeon;

    //Texture img;

    public TowerScreen(final Dungeon18Plus game) {
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
