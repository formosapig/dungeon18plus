package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.qqhouse.dungeon18plus.Game;

import java.util.ArrayList;
import java.util.Locale;


// TODO QQList should extends QQList ?
public class QQGrid extends QQView implements QQView.IsParent, QQView.IsTouchable {

    public static abstract class Adapter {
        private QQGrid list;
        protected void setList(QQGrid list) {
            this.list = list;
        }
        public void insert(int index) {
            list.insert(index);
        };
        public void remove(int index) {
            list.remove(index);
        };
        public void updateAll() {
            list.updateAll();
        }

        public abstract int getSize();
        public abstract QQView getView(int index);
        public abstract void updateView(int index, QQView view);

        // callback
        public void onAnimationEnd() {}
    }

    private float touchY = -1, scrollY, maxScrollY, previousScrollY; // vertical only.
    private float changeScrollY; // scrollY need to change after rearrange children.
    private IsTouchable hitBeforeScroll;// = null;
    // animation period in second
    private static final float animVPeriod = 0.11f;    // in sec
    private static final float animHPeriod = 0.23f;

    /*
        Grid series
     */
    private int numColumns;
    public void setNumColumns(int numColumns) {this.numColumns = numColumns;}

    /*
        scroll series.
     */

    private float getTotalHeight() {
        if (childrenView.isEmpty())
            return 0;
        QQView firstChild = childrenView.get(0);
        int numRows = ((childrenView.size() - 1) / numColumns) + 1;
        return numRows * firstChild.height + (numRows - 1) * Game.Size.WIDGET_MARGIN;
    }

    private void calculateMaxScrollY() {
        maxScrollY = getTotalHeight() - (this.height - this.topPadding - this.bottomPadding);
        if (maxScrollY < 0) {
            maxScrollY = 0;
        }
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
        // 假設都插入或新增好了
        // FIXME 1109 會有 - 的 height 出現, 因為沒有任何 child ..
        float h = topPadding + bottomPadding;
        for (QQView child : childrenView)
            h += child.height + Game.Size.WIDGET_MARGIN;
        h -= Game.Size.WIDGET_MARGIN;
        if (0 < maxHeight && h >= maxHeight)
            h = maxHeight;
        this.height = h;
        Gdx.app.error("QQList.resetWrapHeight", "height = " + this.height + "maxHeight = " + maxHeight);
        if (null != parent)
            parent.arrangeChildren();
    }


    /*
        Adapter series
     */
    private Adapter adapter;
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        adapter.setList(this);
        for (int i = 0, s = adapter.getSize(); i < s; ++i) {
            addChild(adapter.getView(i));
        }
        arrangeChildren();
        calculateMaxScrollY();
    }

    private int insertIndex = -1;
    public void insert(int index) {
        insertIndex = index;
        float preDelay = 0;
        if (removeIndex >= 0) {
            changeScrollY = 0;
            //Gdx.app.error("QQList.insert", "remove then insert...");
            // remove then inser...
            // 1. cancel all animation ...
            for (QQView child : childrenView) {
                if (child.removeAnimation()) {
                    decreaseAnimationLock();
                }
            }

            // 2. decide range to move
            if (insertIndex < removeIndex) {
                //  insert     V
                //             V
                //  remove -1  V
                //  remove
                moveDownward(insertIndex, removeIndex - 1, animHPeriod);
                preDelay = animVPeriod;
            } else if (removeIndex < insertIndex) {
                // remove
                // remove +1 ^
                //           ^
                // insert    ^
                Gdx.app.error("QQList", String.format(Locale.US, "MoveUpward rI : %d , iI : %d", removeIndex, insertIndex));
                moveUpward(removeIndex, insertIndex - 1, animHPeriod);
                preDelay = animVPeriod;
            } else {
                // remove insert no move....
            }
        } else {
            moveDownward(index, childrenView.size() - 1, 0);
        }

        // 要插入之前所有的人往下移...
        //for (int i = index, s = childrenView.size(); i < s; ++i) {
        //    QQView view = childrenView.get(i);
        //    view.applyAnimation(new MoveVerticalAnimation(-view.getHeight() / animPeriod, view.getHeight(), 0.0f).listener(new QQAnimation.AnimationListener() {
        //        @Override
        //        public void onAnimationStart(QQView target) {
        //            increaseAnimationLock();
                    //Gdx.app.error("QQList", "insert(move).start" + animationLock);
        //        }

        //        @Override
        //        public void onAnimationEnd(QQView target) {
        //            decreaseAnimationLock();
                    //Gdx.app.error("QQList", "insert(move).end" + animationLock);
        //        }
        //    }));
        //}

        // 插入並且做插入的動畫...
        // 不可以直接呼叫 addChild 因為要做動畫....
        QQView insertOne = adapter.getView(index);
        childrenView.add(index, insertOne);
        // FIXME 不能直接 arrangeChild ...這一段要修...
        if (wrapHeight) {
            resetWrapHeight();
            // TODO should call calculateMaxScrollY ?
        }

        // set default position before animation
        float insertOneY = getIndexChildY(index);
        if (this.height - topPadding >= insertOneY && bottomPadding <= (insertOneY + insertOne.height)) {
            insertOne.setPosition(-this.width, insertOneY);
            insertOne.applyAnimation(new InsertAnimation(this.width / animHPeriod, 0f, animHPeriod + preDelay).listener(
                    new QQAnimation.AnimationListener() {
                        @Override
                        public void onAnimationStart(QQView target) {
                            increaseAnimationLock();
                            //Gdx.app.error("QQList", "insert.start" + animationLock);
                        }

                        @Override
                        public void onAnimationEnd(QQView target) {
                            decreaseAnimationLock();
                            //Gdx.app.error("QQList", "insert.end" + animationLock);
                            insertIndex = -1;
                        }
                    }
            ));
        } else {
            // out of vision, no animation
            insertOne.setPosition(leftPadding, insertOneY);
        }

    }

    // get child y of index, no matter what other child is animating or none.
    public float getIndexChildY(int index) {
        float y = height - topPadding;
        for (int i = 0; i <= index; ++i) {
            y -= (childrenView.get(i).getHeight() + Game.Size.WIDGET_MARGIN);
        }
        return y + Game.Size.WIDGET_MARGIN + scrollY;
    }

    private int removeIndex = -1;
    public void remove(int index) {
        //Gdx.app.error("QQList", "start remove.");
        // FIXME remove not resetWrapHeight ...
        removeIndex = index;
        removeTarget = childrenView.remove(index);
        removeTarget.applyAnimation(new RemoveAnimation(this.width / animHPeriod, this.width).listener(
                new QQAnimation.AnimationListener() {
                    @Override
                    public void onAnimationStart(QQView target) {
                        increaseAnimationLock();
                        //Gdx.app.error("QQList", "remove.start" + animationLock);
                    }

                    @Override
                    public void onAnimationEnd(QQView target) {
                        decreaseAnimationLock();
                        //Gdx.app.error("QQList", "remove.end" + animationLock);
                        removeIndex = -1;

                        //target = null;
                        if (wrapHeight) {
                            resetWrapHeight();
                        }
                        removeTarget = null;
                    }
                }
        ));

        // 先做一個永遠是下面往上移的版本來看看 ...
        float gap = removeTarget.getHeight() + Game.Size.WIDGET_MARGIN;
        if (scrollY > gap && (maxScrollY - scrollY) < gap) {
            moveDownward(0, index - 1, animHPeriod);
            changeScrollY = -gap;
        } else {
            Gdx.app.error("QQList", String.format(Locale.US, "MoveUpward index : %d", index));

            moveUpward(index, childrenView.size() - 1, animHPeriod);
        }

    }

    // 由上往下填滿被移掉的 view
    private void moveDownward(int start, int end, float delay) {
        for (int i = start; i <= end; ++i) {
            QQView child = childrenView.get(i);
            float gap = child.getHeight() + Game.Size.WIDGET_MARGIN;
            child.applyAnimation(new MoveVerticalAnimation(-gap / animVPeriod, gap, delay)
                    .listener(new QQAnimation.AnimationListener() {
                        @Override
                        public void onAnimationStart(QQView target) {
                            increaseAnimationLock();
                            //Gdx.app.error("QQList", "remove(move).start" + animationLock);
                        }

                        @Override
                        public void onAnimationEnd(QQView target) {
                            decreaseAnimationLock();
                            //Gdx.app.error("QQList", "remove(move).end" + animationLock);
                        }
                    })
            );
        }
    }

    // 由下往上填滿被移掉的 view
    private void moveUpward(int start, int end, float delay) {
        for (int i = start; i <= end; ++i) {
            QQView child = childrenView.get(i);
            float gap = child.getHeight() + Game.Size.WIDGET_MARGIN;
            child.applyAnimation(new MoveVerticalAnimation(gap / animVPeriod, gap, delay)
                    .listener(new QQAnimation.AnimationListener() {
                        @Override
                        public void onAnimationStart(QQView target) {
                            increaseAnimationLock();
                            //Gdx.app.error("QQList", "remove(move).start" + animationLock);
                        }

                        @Override
                        public void onAnimationEnd(QQView target) {
                            decreaseAnimationLock();
                            //Gdx.app.error("QQList", "remove(move).end" + animationLock);
                        }
                    })
            );
        }
    }

    public void updateAll() {
        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            adapter.updateView(i, childrenView.get(i));
        }
    }

    /*
        animation series
     */
    private QQView removeTarget;
    private int animLock = 0;
    private void increaseAnimationLock() {
        animLock++;
        //Gdx.app.error("QQList", "animation lock++ : " + animationLock );
    }

    private void decreaseAnimationLock() {
        animLock--;
        //Gdx.app.error("QQList", "animation lock-- : " + animationLock );
        if (0 == animLock) {
            // if changeScrollY != 0
            if (0 != changeScrollY) {
                scrollY += changeScrollY;
                changeScrollY = 0;
            }
            arrangeChildren();
            float totalHeight = - Game.Size.WIDGET_MARGIN;
            for (QQView v : childrenView) {
                totalHeight += (Game.Size.WIDGET_MARGIN + v.height); // 2 = widget margin...
            }
            maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
            if (maxScrollY < 0)
                maxScrollY = 0;
            // on animation end
            if (null != adapter) {
                Gdx.app.error("QQList", String.format(Locale.US, "adapter.onAnimationEnd %d", childrenView.size()));
                adapter.onAnimationEnd();
            }
        } else if (0 > animLock) {
            throw new GdxRuntimeException("animation lock error.");
        }
    }

    @Override
    public void act(float delta) {
        for (QQView view : childrenView)
            view.act(delta);
        if (null != removeTarget)
            removeTarget.act(delta);
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

    //private final float removeSpeed = Game.WIDTH / 0.33f;
    private static final class InsertAnimation extends QQAnimation {

        private float velocity, endX, delay;
        public InsertAnimation(float velocity, float endX, float delay) {
            this.velocity = velocity;
            this.endX = endX;
            this.delay = delay;
        }

        @Override
        public boolean goOn(float delta) {
            if (delay > 0) {
                delay -= delta;
            } else {
                //Gdx.app.error("QQList.InsertAnimation.goOn", "go on.");
                float x = target.getX() + delta * velocity;
                target.setPosition(x, target.getY());
                if (x >= endX)
                    return false;
            }
            return true;
        }

        public InsertAnimation listener(AnimationListener listener) {
            addListener(listener);
            return this;
        }
    }

    private static final class RemoveAnimation extends QQAnimation {

        private float removeVelocity, endX;
        public RemoveAnimation(float removeVelocity, float endX) {
            this.removeVelocity = removeVelocity;
            this.endX = endX;
        }

        @Override
        public boolean goOn(float delta) {
            float x = target.getX() + delta * removeVelocity;
            target.setPosition(x, target.getY());
            return x < endX;
        }

        public RemoveAnimation listener(AnimationListener listener) {
            addListener(listener);
            return this;
        }
    }

    private static final class MoveVerticalAnimation extends QQAnimation {

        private float velocity, distance, delay;
        public MoveVerticalAnimation(float velocity, float distance, float delay) {
            this.velocity = velocity;
            this.distance = distance;
            this.delay = delay;
            //Gdx.app.error("QQList.MVA.()", "distance = " + distance);
        }

        @Override
        public boolean goOn(float delta) {
            // delay first
            if (delay >= 0) {
                delay -= delta;
            } else {
                distance -= Math.abs(delta * velocity);
                //Gdx.app.error("QQList.goOn", "shift = " + Math.abs(delta * velocity));
                if (distance <= 0) {
                    // TODO 改一個寫法, 感覺計算太複雜了. 效果OK
                    float shift = Math.abs(delta * velocity);
                    float rate = (shift + distance) / shift;
                    //Gdx.app.error("QQList.goOn", "distance = " + distance + ",rate = " + rate);
                    float y = target.getY() + (delta * velocity) * rate;
                    target.setPosition(target.getX(), y);
                    return false;
                }
                float y = target.getY() + delta * velocity;
                target.setPosition(target.getX(), y);
                //Gdx.app.error("QQList.MVA.GoOn", "" + MoveVerticalAnimation.this + "distance = " + distance);
            }
            return true;
        }

        public MoveVerticalAnimation listener(AnimationListener listener) {
            addListener(listener);
            return this;
        }

    }

    private Vector2 touchDownPos;// = new Vector2();
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        if (0 < animLock) {
            Gdx.app.error("QQList.touchDown", "animLock = " + animLock);
            return false;
        }
        // 1. keep touch down position for scroll
        //touchDownPos = new Vector2(relativeX, relativeY);
        touchY = relativeY;

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
        if (0 < animLock) {
            Gdx.app.error("QQList.touchUp", "animLock = " + animLock);
            return false;
        }
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
        if (0 < animLock || 0 > touchY)
            return false;
        // TODO 有時候會有一個位移等於零的 touchDragged 事件發生...真奇怪.
        // TODO 當位移大於某一個值時, 才觸發 scroll 事件...
        Gdx.app.error("QQList", "touchDragged : " + (relativeY - touchY));
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
        if (0 < animLock)
            return false;
        // cancel long press
        longPressIndex = -1;

        // 1. do scroll ...
        scrollY += (64 + Game.Size.WIDGET_MARGIN) * amountY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        //Gdx.app.error("QQList", "scrollY = " + scrollY + "@" + this);
        //Gdx.app.error("QQListView", "scrolled : " + scrollY);
        arrangeChildren();
        return false;
    }

    @Override
    public void cancelTouching() {
        touchY = -1;
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



    //private Rectangle scissorArea;
    private Camera camera;
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /*
        IsParent series
     */
    private final ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {

        //Gdx.app.error("QQList", "arrangeChildren : " + width + "," + height);
        if (0 >= this.width || 0 >= this.height || childrenView.isEmpty())
            return;

        // from top to bottom...
        float anchorY = this.height - topPadding + scrollY;
        int column = 0;
        float columnWidth = (width - leftPadding - rightPadding - childrenView.get(0).width) / (numColumns - 1);
        for (QQView child : childrenView) {

            // match width
            if (child.matchWidth && 0 >= child.width)
                child.setSize(this.width - leftPadding - rightPadding, child.getHeight());

            if (0 == column) {
                anchorY -= child.height;
            }

            // reset position
            child.setPosition(leftPadding + column * columnWidth, anchorY/* + scrollY*/);

            if (numColumns == ++column) {

                // widget margin
                anchorY -= Game.Size.WIDGET_MARGIN;
                column = 0;
            }
        }

        // calculate maxScrollY

    }

    @Override
    public void addChild(QQView child) {
        childrenView.add(child);
        child.setParent(this);
        // calculate child size
        if (child.matchWidth || child.matchHeight) {
            throw new GdxRuntimeException("match parent does not support in QQGrid.");
        }

        // recalculate height
        if (wrapHeight)
            resetWrapHeight();
    }

    @Override
    public void removeChild(QQView child) {}

    @Override
    public void onParentSizeChanged(float width, float height) {}

    @Override
    public void onChildSizeChanged(QQView child) {

    }

    private Rectangle scissors;
    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        batch.flush();
        // TODO height will change .... so scissors will cut wrong area.
        //if (null == scissors) {
            scissors = new Rectangle();
            Rectangle clipBounds = new Rectangle(originX, originY, width, height);
            // QQList 變成 sub view 時, 座標又變換了....
            //Rectangle clipBounds = new Rectangle(x, y, width, height);
            ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
            //Gdx.app.error("QQList", "clipBounds = " + width + "," + height);
        //}
        if (ScissorStack.pushScissors(scissors)) {
            for (QQView view : childrenView)
                view.draw(batch, originX, originY);
            // remove target should be cut..
            if (null != removeTarget)
                removeTarget.draw(batch, originX, originY);
            batch.flush();
            ScissorStack.popScissors();
        }

    }
}
