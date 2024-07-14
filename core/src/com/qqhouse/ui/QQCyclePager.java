package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.Viewport;

public class QQCyclePager extends QQLinear implements QQView.IsTouchable {

    public static abstract class Adapter {
        private QQCyclePager cyclePager;
        protected void setCyclePager(QQCyclePager cyclePager) { this.cyclePager = cyclePager; }

        public abstract int getSize();
        public abstract QQView getView(int index, QQView view);
        public abstract void updateView(int index, QQView view);

    }

    private float touchX = -1;
    private float shiftX, velocityX, maxShiftX; // +shift , -shift
    private final Viewport viewport;
    private IsTouchable hitBeforeScroll;
    private int pageNum = 1;
    private int currentPage;

    public QQCyclePager(Viewport viewport, int innerMargin) {
        super(false, innerMargin);
        this.viewport = viewport;
    }

    private void calculateMaxShiftX() {
        maxShiftX = width + innerMargin;
        Gdx.app.error("QQCyclePager", "maxShiftX = " + maxShiftX);
    }

    private void changeCurrentPage(int page) {
        currentPage = page;
        // set visible of childrenView
        // disable all other child
        for (QQView v : childrenView)
            v.setVisible(false);

        // -1 currentPos 1
        for (int index = currentPage - 1; index <= currentPage + 1; ++index) {
            QQView child = childrenView.get((index + adapter.getSize()) % adapter.getSize());
            child.setVisible(true);
        }

    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        calculateMaxShiftX();
    }

    @Override
    public void resetWrapWidth() {
        if (childrenView.isEmpty())
            return;
        // all children width = 0
        boolean allZero = true;
        for (QQView child : childrenView) {
            if (0 < child.getWidth()) {
                allZero = false;
                break;
            }
        }
        if (allZero)
            return;

        float preWidth = width;
        float w = leftPadding + rightPadding;
        for (QQView child : childrenView)
            w += child.getWidth() + innerMargin;
        w -= innerMargin;
        if (0 < maxWidth && w >= maxWidth)
            w = maxWidth;
        width = w;
        if (null != parent && Float.compare(preWidth, width) == 0)
            parent.onChildSizeChanged(this);
    }

    /*
        Adapter series
     */
    private Adapter adapter;
    public void setAdapter(QQCyclePager.Adapter adapter) {
        if (null != this.adapter)
            throw new GdxRuntimeException("QQViewPager can only set adapter once.");
        this.adapter = adapter;
        adapter.setCyclePager(this);
        for (int i = 0, s = adapter.getSize(); i < s; ++i) {
            QQView child = adapter.getView(i, null);
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

        changeCurrentPage(0);
        calculateMaxShiftX();

        arrangeChildren();
    }

    public void updateAll() {
        childrenView.clear();
        for (int i = 0, s = adapter.getSize(); i < s; ++i) {
            QQView child = adapter.getView(i, null);
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
        calculateMaxShiftX();
    }

    @Override
    public void act(float delta) {
        // move shiftX to 0
        if (Float.compare(shiftX, 0) != 0 && Float.compare(velocityX, 0) != 0) {
            if (Math.abs(shiftX) < Math.abs(velocityX)) {
                shiftX = 0;
                velocityX = 0;
            } else
                shiftX += velocityX;
            arrangeChildren();
        }

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

    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        // 1. keep touch down position for scroll
        //touchDownPos = new Vector2(relativeX, relativeY);
        touchX = relativeX;

        // reset velocityX
        velocityX = 0;

        // 2. walk through all child and find out hit one, send touch down to it.
        QQView target;
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
        touchX = -1;
        // long press reset
        longPressIndex =  -1;
        //longPressCounter = 0;

        // calculate velocity of shift x
        if (Math.abs(shiftX) > 0)
            velocityX = shiftX / 0.5f; // move to 0 at 0.5 sec

        // 1. trace this event to exit scroll mode ...
        //touchDownPos = null; // ??
        //previousScrollY = scrollY;
        //Gdx.app.error("QQListView.java", "previousScrollY = " + previousScrollY);


        // 2. tell child touch up
        QQView target;
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
        // no count
        if (0 > touchX)
            return false;


        // TODO 有時候會有一個位移等於零的 touchDragged 事件發生...真奇怪.
        // TODO 當位移大於某一個值時, 才觸發 scroll 事件...
        //Gdx.app.error("QQList", "touchDragged : " + (relativeY - touchY));
        // 1. do scroll ...
        float moveDelta = relativeX - touchX;// - relativeX;// - touchX;
        if (3 >= Math.abs(moveDelta))
            return true;
        shiftX += moveDelta;
        touchX = relativeX;
        //if (shiftX < 0) scrollX = 0;
        //if (scrollX > maxScrollX) scrollX = maxScrollX;
        arrangeChildren();

        // cancel touching and long press counter..
        if (null != hitBeforeScroll && Math.abs(moveDelta) > 0.01f) {
            //Gdx.app.error("QQList", "cancelTouching@" + hitBeforeScroll);
            hitBeforeScroll.cancelTouching();
        }
        longPressIndex = -1;

        //return false;
        // handled, skip swift right event.
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // cancel long press
        longPressIndex = -1;

        // 1. do scroll ...
        shiftX += (64 + innerMargin) * amountY;
        //if (scrollX < 0) scrollX = 0;
        //if (scrollX > maxScrollX) scrollX = maxScrollX;
        arrangeChildren();
        return false;
    }

    @Override
    public void cancelTouching() {
        touchX = -1;
        // calculate velocity of shift x
        if (Math.abs(shiftX) > 0)
            velocityX = shiftX / 0.5f; // move to 0 at 0.5 sec
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

    private QQCyclePager.PressListener listener;
    public void setPressListener(QQCyclePager.PressListener listener) {
        this.listener = listener;
    }

    /*
        IsParent series
     */
    @Override
    public void onParentSizeChanged(float width, float height) {
        super.onParentSizeChanged(width, height);
        calculateMaxShiftX();
    }


    @Override
    public void arrangeChildren() {
        if (0 >= this.width || 0 >= this.height)
            return;

        // -1 currentPos 1
        for (int index = currentPage - 1; index <= currentPage + 1; ++index) {
            QQView child = childrenView.get((index + adapter.getSize()) % adapter.getSize());
            float childX = width / 2 + shiftX - child.width / 2 + maxShiftX * index;
            child.setPosition(childX, bottomPadding);
            Gdx.app.error("QQCyclePager", "childX = " + childX);
        }
    }

    @Override
    public void addChild(QQView child) {
        throw new GdxRuntimeException("QQCyclePager can not call addChild().");
    }

    @Override
    public void removeChild(QQView child) {
        throw new GdxRuntimeException("QQCyclePager can not call removeChild().");
    }

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
