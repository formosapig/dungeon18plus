package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.ui.animation.QQAnimation;
import com.qqhouse.ui.animation.QQInsertAnimation;
import com.qqhouse.ui.animation.QQMoveVerticalAnimation;
import com.qqhouse.ui.animation.QQRemoveAnimation;

import java.util.Locale;

public class QQList1 extends QQLinear implements QQView.IsTouchable {

    protected float touchY = -1, scrollY, maxScrollY, previousScrollY; // vertical only.
    private float velocityStartY = -1, velocityStartDelta, velocityY; // velocity of scrollY
    private IsTouchable hitBeforeScroll;
    protected final Viewport viewport;

    public QQList1(Viewport viewport, int innerMargin) {
        super(innerMargin);
        this.viewport = viewport;
    }

    private void calculateMaxScrollY() {
        float totalHeight = 0;
        for (QQView child : childrenView) {
            totalHeight += child.height + innerMargin;
        }
        totalHeight -= innerMargin;
        maxScrollY = totalHeight - (height - topPadding - bottomPadding);
        if (0 > maxScrollY)
            maxScrollY = 0;
    }

    /*
        set size should trigger rearrange ...
     */
    @Override
    public void setSize(float w, float h) {
        super.setSize(w, h);
        calculateMaxScrollY();
    }

    @Override
    public void resetWrapHeight() {
        if (childrenView.isEmpty())
            return;
        // all children height = 0
        boolean allZero = true;
        for (QQView child : childrenView) {
            if (0 < child.getHeight()) {
                allZero = false;
                break;
            }
        }
        if (allZero)
            return;

        float preHeight = height;
        float h = topPadding + bottomPadding;
        for (QQView child : childrenView)
            h += child.getHeight() + innerMargin;
        h -= innerMargin;
        if (0 < maxHeight && h >= maxHeight)
            h = maxHeight;
        height = h;
        if (null != parent && Float.compare(preHeight, height) == 0)
            parent.onChildSizeChanged(this);
    }

    /*
        Adapter series
     */
    protected QQListAdapter adapter;
    public void setAdapter(QQListAdapter adapter) {
        if (null != this.adapter)
            throw new GdxRuntimeException("QQList can only set adapter once.");
        this.adapter = adapter;
        adapter.setList(this);
        for (int i = 0, s = adapter.getSize(); i < s; ++i) {
            QQView child = adapter.getView(i);
            childrenView.add(child);
            child.setParent(this);
            if (child.matchWidth) {
                if (wrapWidth)
                    throw new GdxRuntimeException("wrap width with match width child.");
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
            }
            if (child.matchHeight) {
                if (wrapHeight)
                    throw new GdxRuntimeException("wrap height with match height child.");
                child.setSize(child.getWidth(), height - topPadding - bottomPadding);
            }
        }

        // resetWrapHeight / resetWrapWidth
        if (wrapHeight && isVertical)
            resetWrapHeight();
        if (wrapWidth && !isVertical)
            resetWrapWidth();

        arrangeChildren();
        calculateMaxScrollY();
    }

    public void updateAll() {
        // 在 act 中呼叫了 update all , 然後清除全部的 view 又全部加回來,就引發了
        // ConcurrentModificationException
        childrenView.clear();
        for (int i = 0, s = adapter.getSize(); i < s; ++i) {
            QQView child = adapter.getView(i);
            childrenView.add(child);
            child.setParent(this);
            if (child.matchWidth) {
                if (wrapWidth)
                    throw new GdxRuntimeException("wrap width with match width child.");
                child.setSize(width - leftPadding - rightPadding, child.getHeight());
            }
            if (child.matchHeight) {
                if (wrapHeight)
                    throw new GdxRuntimeException("wrap height with match height child.");
                child.setSize(child.getWidth(), height - topPadding - bottomPadding);
            }
        }

        // resetWrapHeight / resetWrapWidth
        if (wrapHeight && isVertical)
            resetWrapHeight();
        if (wrapWidth && !isVertical)
            resetWrapWidth();

        arrangeChildren();
        calculateMaxScrollY();

    }

    /*
        scroll accelerator
     */

    private QQListScrollAccelerator accelerator = new QQListScrollAccelerator(new QQListScrollAccelerator.ScrollCallback() {
        @Override
        public boolean doScroll(float shift) {
            // do Y shift.
            boolean result = false;
            scrollY += shift;
            if (scrollY < 0) {
                scrollY = 0;
                result = true;
            }
            if (scrollY > maxScrollY) {
                scrollY = maxScrollY;
                result = true;
            }
            arrangeChildren();
            return result;
        }
    });

    @Override
    public void act(float delta) {
        accelerator.act(delta);
        // long press series
        if (-1 != longPressIndex) {
            longPressCounter += delta;
            if (0.5f <= longPressCounter) {
                if (null != listener)
                    listener.onLongPress(longPressIndex);
                longPressIndex = -1;
            }
        }
    }

    private Vector2 touchDownPos;// = new Vector2();
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        // 1. keep touch down position for scroll
        //touchDownPos = new Vector2(relativeX, relativeY);
        touchY = relativeY;
        accelerator.touchDown(relativeY);

        // 2. walk through all child and find out hit one, send touch down to it.
        QQView target = null;
        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView child = childrenView.get(i);
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                //Gdx.app.error("QQList", "touchDown : " + i + ",@" + target);
                if (target instanceof IsTouchable) {
                    hitBeforeScroll = (IsTouchable) target;
                    longPressCounter = 0;
                    longPressIndex = i;
                }
                return target.touchDown(childRelativeX, childRelativeY);
            }
        }
        return false;
    }

    /*
        touch up event.
        1. list view will trace this event to cancel scroll mode. reset scrollPos to zero ?
        2. walk through all child and find out hit one, send touch up to it.
     */
    @Override
    public boolean touchUp(float relativeX, float relativeY) {
        accelerator.touchUp(relativeY);

        touchY = -1;
        // long press reset
        longPressIndex =  -1;
        //longPressCounter = 0;

        // 1. trace this event to exit scroll mode ...
        //touchDownPos = null; // ??
        //previousScrollY = scrollY;
        //Gdx.app.error("QQListView.java", "previousScrollY = " + previousScrollY);


        // 2. tell child touch up
        QQView target = null;
        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView child = childrenView.get(i);
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                //Gdx.app.error("QQList", "touchUp : " + i + ",@" + target);
                if (target.touchUp(childRelativeX, childRelativeY)) {
                    if (null != listener) {
                        listener.onPress(i);
                    }
                    return true;
                }
            }
        }

        return false;
    }
    /*
        touch dragged event.
        1. do scroll by dragged distance ....
        2. tell child dragged if exit hit area ?
     */
    @Override
    public boolean touchDragged(float relativeX, float relativeY) {
        if (0 > touchY)
            return false;
        // TODO 有時候會有一個位移等於零的 touchDragged 事件發生...真奇怪.
        // TODO 當位移大於某一個值時, 才觸發 scroll 事件...
        //Gdx.app.error("QQList", "touchDragged : " + scrollY);
        // 1. do scroll ...
        float moveDelta = relativeY - touchY;
        if (3 >= Math.abs(moveDelta))
            return false;
        scrollY += moveDelta;
        touchY = relativeY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        arrangeChildren();

        // cancel touching and long press counter..
        if (null != hitBeforeScroll && Math.abs(moveDelta) > 0.01f) {
            //Gdx.app.error("QQList", "cancelTouching@" + hitBeforeScroll);
            hitBeforeScroll.cancelTouching();
        }
        longPressIndex = -1;


        //if (null != touchDownPos) {
        //    scrollY = previousScrollY + relativeY - touchDownPos.y;
        //    if (scrollY < 0) scrollY = 0;
        //    if (scrollY > maxScrollY) scrollY = maxScrollY;
        //    rearrangeChildren();

        // do scroll, so cancel touching.
        //    if (null != hitBeforeScroll) {
        //        hitBeforeScroll.cancelTouching();
        //    }
        //}

        // 2. tell child to check if exit hit area.
        //for (QQView child : childrenView) {
        //    child.touchDragged(relativeX - child.getX(), relativeY - child.getY());
        //}

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // cancel long press
        longPressIndex = -1;

        // 1. do scroll ...
        scrollY += (64 + innerMargin) * amountY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        arrangeChildren();
        return false;
    }

    @Override
    public void cancelTouching() {
        touchY = -1;
    }

    public void scrollDown() {
        scrollY = maxScrollY;
        arrangeChildren();
    }

    /*
        press listener return index
     */
    private float longPressCounter = 0;
    private int longPressIndex = -1;

    public interface PressListener {
        void onPress(int index);
        void onLongPress(int index);
    }

    private PressListener listener;
    public void addListener(PressListener listener) {
        this.listener = listener;
    }
    public void setPressListener(PressListener listener) { this.listener = listener; }

    /*
        IsParent series
     */
    @Override
    public void arrangeChildren() {
        if (0 >= this.width || 0 >= this.height)
            return;

        // from top to bottom...
        float anchorY = this.height - topPadding + scrollY;
        for (QQView child : childrenView) {
            // match width
            if (child.matchWidth && 0 >= child.width)
                child.setSize(this.width - leftPadding - rightPadding, child.getHeight());

            anchorY -= child.height;

            // reset position
            child.setPosition(leftPadding, anchorY/* + scrollY*/);

            // widget margin
            anchorY -= innerMargin;
        }
    }

    @Override
    public void addChild(QQView child) {
        throw new GdxRuntimeException("QQList can not call addChild().");
    }

    @Override
    public void removeChild(QQView child) {
        throw new GdxRuntimeException("QQList can not call removeChild().");
    }

    //@Override
    //public void onParentSizeChanged(float w, float h) {
        //super.onParentSizeChanged(w, h);
        //calculateMaxScrollY();
    //}

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        batch.flush();
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(originX, originY, width, height);
        ScissorStack.calculateScissors(viewport.getCamera(), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight(), batch.getTransformMatrix(), clipBounds, scissors);

        if (ScissorStack.pushScissors(scissors)) {
            for (QQView view : childrenView)
                view.draw(batch, originX, originY);
            batch.flush();
            ScissorStack.popScissors();
        }

    }
}
