package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Dungeon18Plus;
import com.qqhouse.dungeon18plus.Game;

public class Scene2DDungeonScreen implements Screen {

    private final Dungeon18Plus game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    // ui components
    //private Button btnDungeon;

    //Texture img;



    public Scene2DDungeonScreen(final Dungeon18Plus game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Game.Size.WIDTH, Game.Size.HEIGHT);
        viewport = new StretchViewport(Game.Size.WIDTH, Game.Size.HEIGHT, camera);
        //viewport = new FitViewport(480, 640, camera);
        //viewport = new FillViewport(480, 640, camera);
        stage = new Stage(viewport, game.batch);
        stage.setDebugAll(true);
        //stage.addListener(buttonGesture);
    }

    // gesture detect
    private ActorGestureListener buttonGesture = new ActorGestureListener() {
        @Override
        public void tap (InputEvent event, float x, float y, int count, int button) {
            Gdx.app.log("TEST", "Target : " + event.getListenerActor().getName() + " tap.");
        }

        @Override
        public boolean longPress (Actor actor, float x, float y) {
            Gdx.app.log("TEST", "Target : " + actor.getName() + " long press.");
            return true;
        }

        @Override
        public void fling (InputEvent event, float velocityX, float velocityY, int button) {
            Gdx.app.log("TEST", "Target : " + event.getListenerActor().getName() + " fling.");
        }
    };


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // skin...
        Skin skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));

        //Actor

        // hero
        Button btnHero = new TextButton("Hero View Here", skin);
        btnHero.setName("HERO");
        btnHero.setSize(Game.Size.WIDTH - Game.Size.UI_MARGIN * 2, 80);
        btnHero.setPosition(Game.Size.UI_MARGIN, Game.Size.HEIGHT - btnHero.getHeight() - Game.Size.UI_MARGIN);
        btnHero.addListener(buttonGesture);
        stage.addActor(btnHero);

        // events with scroll bar ...

        // create something long.
        VerticalGroup group = new VerticalGroup().pad(4).space(4).grow();
        Table group1 = new Table(skin);
        //group.setFillParent(true);
        //group.setSize(G.WIDTH - G.UI_MARGIN * 2, 352);
        //group.setPosition(G.UI_MARGIN, 80);
        //group.

        for (int i = 0; i < 20; ++i) {
            TextButton btn = new TextButton("Event" + (i + 1), skin);
            //btn.setSize(G.WIDTH - G.UI_MARGIN * 2, 80);
            //btn.setPosition(0, i * (80 + G.UI_MARGIN));

            //btn.setFillParent(true);
            //btn.setFillParent(true);
            group1.add(btn).height(80).width(Game.Size.WIDTH - Game.Size.UI_MARGIN * 2).padTop(2).padBottom(2).row();
            //group.addActor(btn);
        }
        //stage.addActor(group);


        ScrollPane eventPane = new ScrollPane(group1);
        eventPane.setSize(Game.Size.WIDTH - Game.Size.UI_MARGIN * 2, 472);
        eventPane.setPosition(Game.Size.UI_MARGIN, 80);
        stage.addActor(eventPane);

        // message window....
        /*
        set label style ... should test this.
        NinePatch temp = new NinePatch(new Texture(""), 10, 10, 10, 10);
        skin.add("background", temp);

        Label.LabelStyle style = new Label.LabelStyle();
        style.background = skin.getDrawable("background");
        Label label = new Label("This is message window.", style);
        */



        Label label = new Label("This is message window.", skin);
        label.setSize(Game.Size.WIDTH - Game.Size.UI_MARGIN * 2, 24);
        label.setPosition(Game.Size.UI_MARGIN, 52);
        stage.addActor(label);


        // action bar ...
        for (int i = 0; i < 6; ++i) {
            Button btn = new TextButton("Act" + (i + 1), skin);
            btn.setName("Act" + (i + 1));
            btn.setSize(75.3333f, 48);
            btn.setPosition(Game.Size.UI_MARGIN + i * 79.3333f, Game.Size.UI_MARGIN);
            btn.addListener(buttonGesture);
            stage.addActor(btn);
        }

    }

    BitmapFont font = new BitmapFont();

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        //game.batch.begin();
        //font.draw(game.batch, "Dungeon Mode", 180, 320);
        //game.batch.end();
        stage.act(delta);
        stage.draw();
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

    }

    @Override
    public void dispose() {stage.dispose();}
}
