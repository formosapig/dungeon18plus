package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class QQScreen extends InputAdapter {




    private Viewport viewport;

    public QQScreen(Viewport viewport) {
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
}
