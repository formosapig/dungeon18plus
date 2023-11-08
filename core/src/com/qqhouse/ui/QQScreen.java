package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.Assets;

public abstract class QQScreen extends InputAdapter implements QQView.IsParent {

    protected final SaveGame savedGame;
    protected final Assets assets;
    private Viewport viewport;


    public QQScreen(SaveGame savedGame, Viewport viewport, Assets assets) {
        this.savedGame = savedGame;
        this.viewport = viewport;
        this.assets = assets;
        childrenView = new SnapshotArray<>(true, 4, QQView.class);
    }

    public Camera getCamera() {
        return viewport.getCamera();
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

    private QQView.IsTouchable touchDownTarget = null;

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // detect touch down.
        //Gdx.app.error("TEST", "touchDown : " + screenX + "," + screenY);
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));
        //Gdx.app.error("TEST", "touchDown : " + screenPos.x + "," + screenPos.y);

        // 後加入的疊在上面, 所以先收到事件...
        QQView[] views = childrenView.items;//.begin();
        QQView target = null;
        for (int i = childrenView.size - 1; i >= 0; --i) {
            // 傳入相對於 (0, 0) 的座標...
            QQView v = views[i];
            target = v.hit(screenPos.x - v.getX(), screenPos.y - v.getY());
            if (null != target) {
                if (target instanceof QQView.IsTouchable)
                    touchDownTarget = (QQView.IsTouchable) target;
                break;
            }
        }
        if (null != target) {
            // 傳入相對於 (0, 0) 的座標...
            return target.touchDown(screenPos.x - target.getX(), screenPos.y - target.getY());
        }
        //childrenView.end();

        return false;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        //Gdx.app.error("QQScreen", "touchDragged : " + screenX + "," + screenY);
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));
        //Gdx.app.error("QQScreen", "touchDragged : " + screenPos.x + "," + screenPos.y);

        // tell all child dragged ....

        QQView[] views = childrenView.items;
        for (int i = childrenView.size - 1; i >= 0; --i) {
            // 傳入相對於 (0, 0) 的座標...
            QQView v = views[i];
            if (v.touchDragged(screenPos.x - v.getX(), screenPos.y - v.getY()))
                break;
        }
        return false;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        //Gdx.app.error("QQScreen", "touchUp()");
        Vector2 screenPos = screenToStageCoordinates(new Vector2(screenX, screenY));

        QQView[] views = childrenView.items;
        QQView target = null;
        for (int i = childrenView.size - 1; i >= 0; --i) {
            // 傳入相對於 (0, 0) 的座標...
            QQView v = views[i];
            target = views[i].hit(screenPos.x - v.getX(), screenPos.y - v.getY());
            if (null != target) {
                break;
            }
        }
        if (target != touchDownTarget && null != touchDownTarget) {
            touchDownTarget.cancelTouching();
            touchDownTarget = null;
        }
        if (null != target) {
            // 傳入相對於 (0, 0) 的座標...
            return target.touchUp(screenPos.x - target.getX(), screenPos.y - target.getY());
        }

        return false;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        //Gdx.app.error("QQScreen", "amountX : " + amountX + ", amountY : " + amountY);
        QQView[] views = childrenView.items;
        for (int i = childrenView.size - 1; i >= 0; --i) {
            // 傳入相對於 (0, 0) 的座標...
            QQView v = views[i];
            if (v.scrolled(amountX, amountY))
                break;
        }
        return false;
    }

    public boolean touchCancelled (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    abstract public void onEnter();

    abstract public void onLeave();

    /*
        children ... implements QQView.IsParent...
     */
    private SnapshotArray<QQView> childrenView;

    // just add.
    public void addChild(QQView view) {
        childrenView.add(view);
        view.parent = this;
    }
    public void removeChild(QQView view) {
        view.parent = null;
        childrenView.removeValue(view, true);
    }
    // do nothing.
    public void arrangeChildren() {}
    public void drawChildren(SpriteBatch batch, float originX, float originY) {}

    // extra use
    public void removeAllChildren() {
        childrenView.clear();
    }

    //public void addView(QQView view) {
    //    childrenView.add(view);
    //}

    //public void addView(QQView view, float x, float y) {
    //    view.setPosition(x, y);
    //    childrenView.add(view);
    //}

    //public void removeView(QQView view) {
    //    childrenView.removeValue(view, true);
    //}

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
            // screen base is 0, 0
            views[i].draw(batch, 0, 0);
        }
        childrenView.end();
    }

    public void pause() {}

    public void resume() {}

    /*
        Dialog series...
     */
    public void openDialog(QQDialog dialog) {
        // 1. set dialog size to fit screen
        dialog.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        dialog.setPosition(0, 0); // ...

        // 2. set parent
        addChild(dialog);


    }

    public void openDialog(QQCustomDialog dialog) {
        // 1. set dialog size to fit screen
        //dialog.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);
        //dialog.setPosition(0, 0); // ...

        // 2. set parent
        addChild(dialog);


    }

    private void openDialog(QQView customView, boolean modal) {
        //final QQView dialogMask = new QQView();

        // FIXME SDK 不能依存於專案, assets 不是能隨時存取到的資源...
        //dialogMask.bgNormal = new NinePatch(assets.getBackground("black"), 4, 4, 4, 4);
        //dialogMask.bgNormal.setColor(new Color(1, 1, 1, 0.66f));
        //dialogMask.setPosition(0, 0);
        //dialogMask.setSize(Game.Size.WIDTH, Game.Size.HEIGHT);

        // add click listener to dialogMask , any click on it will dismiss dialog.
        //if (!modal) {
        //    dialogMask.addPressListener(new QQView.PressListener() {
        //        @Override
        //        public void onPress(QQView view) {
        //            if (view == dialogMask) {
        //                removeView(dialogMask);
        //            }
        //        }

        //        @Override
        //        public void onLongPress(QQView view) {}
        //    });
        //}

        //addView(dialogMask);



        // calculate view's size
        //if (customView.matchWidth)
        //    customView.setSize(Game.Size.WIDTH - 10 - 10, customView.getHeight());

        // set position
        //customView.setPosition((Game.Size.WIDTH - customView.getWidth())/2,
        //        (Game.Size.HEIGHT - customView.getHeight())/2);

        //addView(customView);


    }


}
