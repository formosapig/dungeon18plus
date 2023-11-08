package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class QQView {

    public interface IsParent {
        void addChild(QQView view);
        void removeChild(QQView view);
        void arrangeChildren();
        void drawChildren(SpriteBatch batch, float originX, float originY);
    }

    public interface IsTouchable {
        void cancelTouching();
    }

    public interface PressListener {
        void onPress(QQView view);
        void onLongPress(QQView view);
    }

    public static final int WRAP_CONTENT = -1;  // 保有 view 的 size
    public static final int MATCH_PARENT  = -2;  // 最大的填充 parent 的 size

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
        if (null != this.animation) {
            // FIXME 出現這行然後當掉, 要研究一下為什麼...
            Gdx.app.error("QQView", "I am working.");
        }
        this.animation = animation;
        animation.target = this;
        animation.start();
    }

    public boolean removeAnimation() {
        if (null != this.animation) {
            this.animation = null;
            return true;
        }
        return false;
    }

    public void act(float delta) {
        if (null != animation)
            animation.act(delta);
    }

    /*
        background series...
     */
    protected boolean touchable = true;
    protected NinePatch bgNormal = null;

    public void setBackground(NinePatch bg) {
        bgNormal = bg;
    }

    // 相對 (0, 0) 的座標, 直接拿來畫即可...
    protected void drawBackground(SpriteBatch batch, float originX, float originY) {
        if (null != bgNormal) {
            bgNormal.draw(batch, originX, originY, width, height);
        }
    }

    protected void drawForeground(SpriteBatch batch, float originX, float originY) {}

    // 呼叫 draw 時, 傳入 parent 相對於原點的座標, 所以還需要加上 x, y 的位移值
    public final void draw(SpriteBatch batch, float parentX, float parentY) {
        if (!isVisible())
            return;
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

    public void setSize(float w, float h) {
        // width
        if (w == QQView.WRAP_CONTENT) {
            //calculateContentWidth();
            wrapWidth = true;
        } else if (w == QQView.MATCH_PARENT) {
            matchWidth = true;
        } else if (0 < w && this.width != w) {
            this.width = w;
            if (this instanceof IsParent && 0 < this.height) {
                ((IsParent) this).arrangeChildren();
            }
        }
        if (wrapWidth)
            resetWrapWidth();
        // height
        if (h == QQView.WRAP_CONTENT) {
            //calculateContentHeight();
            wrapHeight = true;
        } else if (h == QQView.MATCH_PARENT) {
            matchHeight = true;
        } else if (0 < h && this.height != h) {
            this.height = h;
            if (this instanceof IsParent && 0 < this.width) {
                ((IsParent) this).arrangeChildren();
            }
        }
        if (wrapHeight)
            resetWrapHeight();
    }

    protected void resetWrapWidth() {}
    protected void resetWrapHeight() {}


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

    public <T extends QQView> T position(float x, float y) {
        setPosition(x, y);
        return (T) this;
    }

    public <T extends QQView> T size(float width, float height) {
        setSize(width, height);
        return (T) this;
    }

    public QQView padding(float all) {
        topPadding = all;
        bottomPadding = all;
        leftPadding = all;
        rightPadding = all;
        return this;
    }

    /*
        alignment of child view or text
     */
    protected int align = Align.center;
    public void setAlign(int align) {
        this.align = align;
    }

    /*
        visible
     */
    private boolean visible = true;
    public void setVisible(boolean visible) {this.visible = visible;}
    public boolean isVisible() {return this.visible;}

    /*
        wrap content / match parent
     */
    protected boolean wrapWidth = false, wrapHeight = false;
    protected boolean matchWidth = false, matchHeight = false;
    public void setWrapWidth(boolean wrapWidth) {this.wrapWidth = wrapWidth;}
    public void setMatchHeight(boolean matchHeight) {this.matchHeight = matchHeight;}

    /*
        IsParent ...
     */
    protected IsParent parent = null;
    public void setParent(IsParent parent) {this.parent = parent;}

    /*
        PressListener
     */
    protected PressListener pressListener = null;
    public void addPressListener(PressListener listener) {this.pressListener = listener;}

}
