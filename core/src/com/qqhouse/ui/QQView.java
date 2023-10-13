package com.qqhouse.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class QQView {

    public interface IsParent {
        void addChild(QQView view);
        void drawChildren(SpriteBatch batch, float originX, float originY);
    }

    public interface IsTouchable {
        void cancelTouching();
    }

    public static final int WRAP_CONTENT = -1;  // 保有 view 的 size
    public static final int FILL_PARENT  = -2;  // 最大的填充 parent 的 size

    public QQView hit(float relativeX, float relativeY) {
        if (relativeX >= 0 && relativeX <= width && relativeY >= 0 && relativeY <= height)
            return this;
        else
            return null;
    }

    public boolean touchDown(float x, float y) {return false;}

    public boolean touchUp(float x, float y) {return false;}

    public boolean touchDragged(float x, float y) {return false;}

    public boolean scrolled(float x, float y) {return false;}


    /*
        called by QQScreen
     */
    /*
        animation series
     */
    private QQAnimation animation;
    public void applyAnimation(QQAnimation animation) {
        this.animation = animation;
        animation.target = this;
        animation.start();
    }

    public void act(float delta) {
        if (null != animation)
            animation.act(delta);
    };

    /*
        background series...
     */
    protected boolean touchable = true;
    protected NinePatch bgNormal = null;

    // 相對 (0, 0) 的座標, 直接拿來畫即可...
    protected void drawBackground(SpriteBatch batch, float originX, float originY) {
        if (null != bgNormal) {
            bgNormal.draw(batch, originX, originY, width, height);
        }
    }

    protected void drawForeground(SpriteBatch batch, float originX, float originY) {}

    // 呼叫 draw 時, 傳入 parent 相對於原點的座標, 所以還需要加上 x, y 的位移值
    public final void draw(SpriteBatch batch, float parentX, float parentY) {
        drawBackground(batch, parentX + x, parentY + y);
        drawForeground(batch, parentX + x, parentY + y);
        if (this instanceof IsParent) {
            // 當自己成為 parent 時, 傳入加上 x, y 後的偏移值
            ((IsParent) this).drawChildren(batch, parentX + x, parentY + y);
        }
    }

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.width = width;
        if (width == QQView.WRAP_CONTENT) {
            calculateContentWidth();
        }
        this.height = height;
        if (height == QQView.WRAP_CONTENT) {
            calculateContentHeight();
        }
    }

    protected void calculateContentWidth() {}
    protected void calculateContentHeight() {}


    public float getX() {return x;}
    public float getY() {return y;}
    public float getWidth() {return width;}
    public float getHeight() {return height;}

    /*
        padding. top, bottom, left, right
     */
    protected float topPadding, bottomPadding, leftPadding, rightPadding;
    public void setPadding(float top, float bottom, float left, float right) {
        topPadding = top;
        bottomPadding = bottom;
        leftPadding = left;
        rightPadding = right;
    }

    public void setPadding(float all) {
        topPadding = all;
        bottomPadding = all;
        leftPadding = all;
        rightPadding = all;
    }


    public void dispose() {}

    /*
    chain function
     */

    public QQView position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public QQView size(float width, float height) {
        this.setSize(width, height);
        return this;
    }

    public QQView padding(float all) {
        topPadding = all;
        bottomPadding = all;
        leftPadding = all;
        rightPadding = all;
        return this;
    }

    /*
        visible
     */
    private boolean visible = true;
    public void setVisible(boolean visible) {this.visible = visible;}
    public boolean isVisible() {return this.visible;}

    protected boolean wrapWidth = false;
    public void setWrapWidth(boolean wrapWidth) {this.wrapWidth = wrapWidth;}

}
