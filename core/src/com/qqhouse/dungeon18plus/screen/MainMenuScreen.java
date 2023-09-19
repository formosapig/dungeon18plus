package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Dungeon18Plus;
import com.badlogic.gdx.Input;
import com.qqhouse.dungeon18plus.core.WildernessManager;

public class MainMenuScreen implements Screen {

    private final Dungeon18Plus game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    // ui components
    //private Button btnDungeon;

    //Texture img;

    public MainMenuScreen(final Dungeon18Plus game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 640);
        viewport = new StretchViewport(480, 640, camera);
        //viewport = new FitViewport(480, 640, camera);
        //viewport = new FillViewport(480, 640, camera);
        stage = new Stage(viewport, game.batch);
    }

    private ClickListener buttonClick = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            String key = event.getListenerActor().getName();
            switch (key) {
                case "DUNGEON":
                    game.setScreen(new DungeonScreen(game));
                    break;
                case "TOWER":
                    game.setScreen(new TowerScreen(game));
                    break;
                case "COLOSSEUM":
                    game.setScreen(new ColosseumScreen(game));
                    break;
                case "CASTLE":
                    game.setScreen(new CastleScreen(game));
                    break;
                case "WILDERNESS":
                    game.setScreen(new WildernessScreen(game));
                    break;
                case "LIBRARY":
                    game.setScreen(new LibraryScreen(game));
                    break;
                case "VERSION":
                    // premium version or version code...
                    break;
                default:
                    throw new GdxRuntimeException("No Such Button.");
            }
        }
    };

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // skin...
        Skin skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));

        // dungeon...
        Button btnDungeon = new TextButton("Dungeon", skin);
        btnDungeon.setName("DUNGEON");
        btnDungeon.setPosition(180,520);
        btnDungeon.setSize(120, 72);
        btnDungeon.addListener(buttonClick);
        stage.addActor(btnDungeon);

        // mage's tower ...
        Button btnTower = new TextButton("Tower", skin);
        btnTower.setName("TOWER");
        btnTower.setPosition(180, 440);
        btnTower.setSize(120, 72);
        btnTower.addListener(buttonClick);
        stage.addActor(btnTower);

        // sword master's colosseum
        Button btnColosseum = new TextButton("Colosseum", skin);
        btnColosseum.setName("COLOSSEUM");
        btnColosseum.setPosition(180, 360);
        btnColosseum.setSize(120, 72);
        btnColosseum.addListener(buttonClick);
        stage.addActor(btnColosseum);

        // skeleton king's castle
        Button btnCastle = new TextButton("Castle", skin);
        btnCastle.setName("CASTLE");
        btnCastle.setPosition(180, 280);
        btnCastle.setSize(120, 72);
        btnCastle.addListener(buttonClick);
        stage.addActor(btnCastle);

        // wilderness
        Button btnWilderness = new TextButton("Wilderness", skin);
        btnWilderness.setName("WILDERNESS");
        btnWilderness.setPosition(180, 200);
        btnWilderness.setSize(120, 72);
        btnWilderness.addListener(buttonClick);
        stage.addActor(btnWilderness);

        // library
        Button btnLibrary = new TextButton("Library", skin);
        btnLibrary.setName("LIBRARY");
        btnLibrary.setPosition(180, 120);
        btnLibrary.setSize(120, 72);
        btnLibrary.addListener(buttonClick);
        stage.addActor(btnLibrary);

        // version
        Button btnVersion = new TextButton("2.0.0", skin);
        btnVersion.setName("VERSION");
        btnVersion.setPosition(392, 8);
        btnVersion.setSize(80, 48);
        btnVersion.addListener(buttonClick);
        stage.addActor(btnVersion);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        //camera.update();
        //game.batch.setProjectionMatrix(camera.combined);
        stage.draw();

        //game.batch.begin();

        // draw button.
        //btnDungeon.draw(game.batch, 1);
        //game.batch.draw(img, 0, 0);
        //game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        //btnDungeon.clear();
        //img.dispose();
    }
}
