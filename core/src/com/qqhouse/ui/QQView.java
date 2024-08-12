package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.qqhouse.ui.animation.QQAnimation;

public class QQView {

    public interface IsParent {
        void addChild(QQView child);
        void removeChild(QQView child);
        void onParentSizeChanged(float width, float height);
        void onChildSizeChanged(QQView child);
        void arrangeChildren();
        void drawChildren(SpriteBatch batch, float originX, float originY);
    }

    public interface IsTouchable {
        void cancelTouching();
    }

    public static final int WRAP_CONTENT = -1;  // 保有 view 的 size
    public static final int MATCH_PARENT  = -2;  // 最大的填充 parent 的 size

    public QQView hit(float relativeX, float relativeY) {
        if (!visible)
            return null;
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
        animation.setTarget(this);
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
        if (!visible)
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
    protected float maxWidth, maxHeight; // > 0 = set, should consider this.

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float w, float h) {
        //Game.trace(this, "QQView.setSize w:%.0f, h:%.0f", w, h);
        //Gdx.app.error("QQView.setSize", String.format(Locale.US, "%s w:%4.2f, h:%4.2f", this, w, h));
        boolean tellChildren = false;
        boolean callParent = false;
        // width
        if (w == QQView.WRAP_CONTENT) {
            //calculateContentWidth();
            wrapWidth = true;
        } else if (w == QQView.MATCH_PARENT) {
            matchWidth = true;
        } else if (0 < w && this.width != w) {
            this.width = w;
            tellChildren = true;
            //if (this instanceof IsParent) {
            //    ((IsParent) this).onParentSizeChanged(this.width, this.height);
            //    //((IsParent) this).arrangeChildren();
            //}
        }

        // height
        if (h == QQView.WRAP_CONTENT) {
            //calculateContentHeight();
            wrapHeight = true;
        } else if (h == QQView.MATCH_PARENT) {
            matchHeight = true;
        } else if (0 < h && this.height != h) {
            this.height = h;
            tellChildren = true;
            //if (this instanceof IsParent) {
            //    ((IsParent) this).onParentSizeChanged(this.width, this.height);
                //((IsParent) this).arrangeChildren();
            //}
        }

        // tell children parent size changed.
        if (tellChildren && this instanceof IsParent)
            ((IsParent) this).onParentSizeChanged(this.width, this.height);


        // wrap content...
        if (wrapWidth && 0 == this.width) {
            resetWrapWidth();
            callParent = true;
            //if (null != parent)
            //    parent.onChildSizeChanged(this);
        }
        if (wrapHeight && 0 == this.height) {
            resetWrapHeight();
            callParent = true;
            //if (null != parent)
            //    parent.onChildSizeChanged(this);
        }

        if (callParent && null != parent)
            parent.onChildSizeChanged(this);


        if (0 < this.width && 0 < this.height && this instanceof IsParent) {
            ((IsParent) this).arrangeChildren();
        }
        //Game.trace(this, "QQView.setSize width:%.0f, height:%.0f", this.width, this.height);
    }

    protected void resetWrapWidth() {}
    protected void resetWrapHeight() {}
    public void setMaxWidth(float w) {this.maxWidth = w;}
    public void setMaxHeight(float h) {this.maxHeight = h;}

    public float getX() {return x;}
    public float getY() {return y;}
    public float getWidth() {return visible ? width : 0;}
    public float getHeight() {return visible ? height : 0;}

    /*
        padding. top, bottom, left, right
     */
    protected float topPadding, bottomPadding, leftPadding, rightPadding;
    public void setPadding(float top, float bottom, float left, float right) {
        topPadding = top;
        bottomPadding = bottom;
        leftPadding = left;
        rightPadding = right;
        if (this instanceof IsParent) {
            ((IsParent) this).arrangeChildren();
        }
    }

    public void setPadding(float all) {
        setPadding(all, all, all, all);
    }

    public void dispose() {}

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
    public void setVisible(boolean visible) {
        this.visible = visible;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }
    public boolean isVisible() {return this.visible;}

    /*
        wrap content / match parent
     */
    protected boolean wrapWidth = false, wrapHeight = false;
    protected boolean matchWidth = false, matchHeight = false;
    public void setWrapWidth(boolean wrapWidth) {this.wrapWidth = wrapWidth;}
    public void setMatchHeight(boolean matchHeight) {this.matchHeight = matchHeight;}
    public boolean isMatchWidth() { return matchWidth; }

    /*
        IsParent ...
     */
    protected IsParent parent = null;
    public void setParent(IsParent parent) {this.parent = parent;}

    /*
        PressListener
     */
    protected QQPressListener pressListener = null;
    public void addPressListener(QQPressListener listener) {this.pressListener = listener;}

}
