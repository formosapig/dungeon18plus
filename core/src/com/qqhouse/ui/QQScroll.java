package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.Viewport;

public class QQScroll extends QQView implements QQView.IsParent, QQView.IsTouchable {

    /*
     *   vertical = default, scroll vertically
     *   horizontal, scroll horizontally
     */
    protected boolean isVertical = true;
    private final Viewport viewport;
    private float touchY = -1, scrollY, maxScrollY;
    private float touchX = -1, scrollX, maxScrollX;

    public QQScroll(Viewport viewport) { this.viewport = viewport; }

    public QQScroll(Viewport viewport, boolean isVertical) {
        this.viewport = viewport;
        this.isVertical = isVertical;
    }

    public void scrollToTop() {
        scrollY = 0;
        arrangeChildren();
    }

    private void calculateMaxScrollY() {
        maxScrollY = childView.height - (height - topPadding - bottomPadding);
        if (0 > maxScrollY)
            maxScrollY = 0;
    }

    private void calculateMaxScrollX() {
        maxScrollX = childView.width - (width - leftPadding - rightPadding);
        if (0 > maxScrollX)
            maxScrollX = 0;
    }

    /*
     * resetWrapWidth / resetWrapHeight
     */
    @Override
    public void resetWrapWidth() {
        if (null == childView)
            return;
    }

    @Override
    public void resetWrapHeight() {
        if (null == childView)
            return;
    }

    /*
        touch series
     */
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        if (isVertical)
            touchY = relativeY;
        else
            touchX = relativeX;

        QQView target = null;
        float childRelativeX = relativeX - childView.getX();
        float childRelativeY = relativeY - childView.getY();
        target = childView.hit(childRelativeX, childRelativeY);
        if (null != target) {
            //if (target instanceof IsTouchable) {
                //hitBeforeScroll = (IsTouchable) target;
                //longPressCounter = 0;
                //longPressIndex = i;
            //}
            return target.touchDown(childRelativeX, childRelativeY);
        }
        return false;
    }

    /*
        touch up event.
     */
    @Override
    public boolean touchUp(float relativeX, float relativeY) {

        touchY = -1;


        QQView target = null;
        float childRelativeX = relativeX - childView.getX();
        float childRelativeY = relativeY - childView.getY();
        target = childView.hit(childRelativeX, childRelativeY);
        if (null != target) {
            if (target.touchUp(childRelativeX, childRelativeY)) {
                    //if (null != listener) {
                    //    listener.onPress(i);
                    //}
                    return true;

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
        if (0 > touchY && 0 > touchX)
            return false;
        // TODO 有時候會有一個位移等於零的 touchDragged 事件發生...真奇怪.
        // TODO 當位移大於某一個值時, 才觸發 scroll 事件...
        //Gdx.app.error("QQList", "touchDragged : " + scrollY);
        // 1. do scroll ...
        if (isVertical) {
            float moveDelta = relativeY - touchY;
            if (3 >= Math.abs(moveDelta))
                return false;
            scrollY += moveDelta;
            touchY = relativeY;
            if (scrollY < 0) scrollY = 0;
            if (scrollY > maxScrollY) scrollY = maxScrollY;
            arrangeChildren();
        }

        // cancel touching and long press counter..
        //if (null != hitBeforeScroll && Math.abs(moveDelta) > 0.01f) {
            //Gdx.app.error("QQList", "cancelTouching@" + hitBeforeScroll);
        //    hitBeforeScroll.cancelTouching();
        //}
        //longPressIndex = -1;


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
        //if (0 < animLock)
        //    return false;
        // cancel long press
        //longPressIndex = -1;

        // 1. do scroll ...
        //scrollY += (64 + innerMargin) * amountY;
        //if (scrollY < 0) scrollY = 0;
        //if (scrollY > maxScrollY) scrollY = maxScrollY;
        //arrangeChildren();
        return false;
    }


    /*
        IsParent
     */
    private QQView childView;

    @Override
    public void addChild(QQView child) {
        if (null != childView)
            throw new GdxRuntimeException("only one child view for QQScroll.");

        childView = child;
        childView.setParent(this);
        // calculate child size
        if (child.matchWidth) {
            if (wrapWidth)
                throw new GdxRuntimeException("wrap width with match width child.");
            if (0 < width) {
                childView.setSize(width - leftPadding - rightPadding, childView.getHeight());
            }
        }
        if (child.matchHeight) {
            if (wrapHeight)
                throw new GdxRuntimeException("wrap height with match height child.");
            if (0 < height)
                childView.setSize(child.getWidth(), height - topPadding - bottomPadding);
        }

        // calculate my size
        if (wrapWidth)
            resetWrapWidth();
        if (wrapHeight)
            resetWrapHeight();
        // arrange child...
        arrangeChildren();
    }

    @Override
    public void removeChild(QQView child) {
        throw new GdxRuntimeException("can not remove child view for QQScroll.");
    }

    /*
     * IsParent series
     *
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        if (null == childView)
            return;

        if (isVertical) {
            if (childView.matchWidth && 0 < width)
                childView.setSize(width - leftPadding - rightPadding, childView.getHeight());
            //calculateMaxScrollY();
        } else {
            if (childView.matchHeight && 0 < height)
                childView.setSize(childView.getWidth(), height - topPadding - bottomPadding);
        }
    }

    @Override
    public void onChildSizeChanged(QQView child) {
        if (isVertical)
            calculateMaxScrollY();
        else
            calculateMaxScrollX();
        //Gdx.app.error("QQScroll", "onChildSizeChanged : " + child.height);
        arrangeChildren();
    }

    @Override
    public void arrangeChildren() {
        if (null == childView)
            return;

        if (0 >= width || 0 >= height)
            return;

        if (isVertical) {
            childView.setPosition(leftPadding, height - topPadding - childView.height + scrollY);
        } else {
            childView.setPosition(leftPadding, bottomPadding);
        }

        //Gdx.app.error("QQScroll", "arrangeChildren : " + childView.getHeight() + " / scrollY = " + scrollY);

    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        batch.flush();
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(originX + leftPadding, originY + bottomPadding, width - leftPadding - rightPadding, height - topPadding - bottomPadding);
        ScissorStack.calculateScissors(viewport.getCamera(), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight(), batch.getTransformMatrix(), clipBounds, scissors);

        if (ScissorStack.pushScissors(scissors)) {
            childView.draw(batch, originX, originY);
            batch.flush();
            ScissorStack.popScissors();
        }
    }

    // QQView IsTouchable

    @Override
    public void cancelTouching() {

    }
}
