package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;

public abstract class QQScreen extends InputAdapter {

    protected final SaveGame savedGame;
    private Viewport viewport;

    public QQScreen(SaveGame savedGame, Viewport viewport) {
        this.savedGame = savedGame;
        this.viewport = viewport;
        childrenView = new SnapshotArray<>(true, 4, QQView.class);
    }

    // input processor

    public Vector2 screenToStageCoordinates (Vector2 screenCoords) {
        viewport.unproject(screenCoords);
        return screenCoords;
    }

    public Vector2 stageToScreenCoordinates (Vector2 stageCoords) {
        viewport.project(stageCoords);
        stageCoords.y = Gdx.graphics.getHeight() - stageCoords.y;
        return stageCoords;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // detect touch down.
        //Gdx.app.error("TEST", "touchDown : " + screenX + "," + screenY);
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));
        //Gdx.app.error("TEST", "touchDown : " + screenPos.x + "," + screenPos.y);

        QQView[] views = childrenView.items;//.begin();
        QQView target = null;
        for (int i = 0, n = childrenView.size; i < n; ++i) {
            target = views[i].hit(screenPos.x, screenPos.y);
            if (null != target) {
                break;
            }
        }
        if (null != target) {
            return target.touchDown(screenPos.x, screenPos.y);
        }
        //childrenView.end();

        return false;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        //Gdx.app.error("TEST", "touchDragged : " + screenX + "," + screenY);
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));
        return false;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));

        QQView[] views = childrenView.items;
        QQView target = null;
        for (int i = 0, n = childrenView.size; i < n; ++i) {
            target = views[i].hit(screenPos.x, screenPos.y);
            if (null != target) {
                break;
            }
        }
        if (null != target) {
            return target.touchUp(screenPos.x, screenPos.y);
        }

        return false;
    }

    public boolean touchCancelled (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    abstract public void onEnter();

    abstract public void onLeave();

    /*
    children ...
     */
    private SnapshotArray<QQView> childrenView;

    public void addView(QQView view) {
        childrenView.add(view);
    }

    public void addView(QQView view, float x, float y) {
        view.setPosition(x, y);
        childrenView.add(view);
    }

    public void removeView(QQView view) {
        childrenView.removeValue(view, true);
    }

    public final void act(float delta) {
        QQView[] views = childrenView.begin();
        for (int i = 0, n = childrenView.size; i < n; ++i) {
            views[i].act(delta);
        }
        childrenView.end();
    }

    public final void draw(SpriteBatch batch) {
        QQView[] views = childrenView.begin();
        for (int i = 0, n = childrenView.size; i < n; ++i) {
            views[i].draw(batch);
        }
        childrenView.end();
    }

    public void pause() {}

    public void resume() {}

    /*
        resource
     */
    protected I18NBundle lanBundle;
    public I18NBundle getLanguageBundle() {
        return lanBundle;
    }

    // font should controlled by screen instance.
    protected BitmapFont createFont(int fontSize, Color color, String fontCharacters) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NotoSansTC-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize; // default = 16
        parameter.color = color;//new Color(0x9E8064FF);
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + fontCharacters;// default = a~z A~Z 1~9, 0, some normal character
        // 在 parameter 內設 filter, 可以取代 font.getRegion().getTexture().setFilter ...
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
        // 加了這行, 字體變漂亮了... 在手機上的效果無法確定....
        //font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return font;
    }
}
