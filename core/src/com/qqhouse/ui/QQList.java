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

public class QQList extends QQView implements QQView.IsParent, QQView.IsTouchable {


    public static abstract class Adapter {

        private QQList list;

        protected void setList(QQList list) {
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

    }

    public QQList() {
        calculateAnchorY = true;
        anchorY = 0;
        scrollY = 0;
        previousScrollY = 0;
        maxScrollY = 0;
        touchY = -1;
    }

    private boolean calculateAnchorY;
    private float anchorY;
    private float touchY, scrollY, maxScrollY, previousScrollY; // vertical only.
    private IsTouchable hitBeforeScroll = null;

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
        rearrangeChildren();
        float totalHeight = - Game.Size.WIDGET_MARGIN;
        for (QQView v : childrenView) {
            totalHeight += (Game.Size.WIDGET_MARGIN + v.height); // 2 = widget margin...
        }
        maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
    }

    public void insert(int index) {
        // 要插入之前所有的人往下移...
        for (int i = index, s = childrenView.size(); i < s; ++i) {
            QQView view = childrenView.get(i);
            view.applyAnimation(new MoveVerticalAnimation(-view.getHeight() /0.2f, view.getHeight(), 0.0f).listener(new QQAnimation.AnimationListener() {
                @Override
                public void onAnimationStart(QQView target) {
                    increaseAnimationLock();
                    //Gdx.app.error("QQList", "insert(move).start" + animationLock);
                }

                @Override
                public void onAnimationEnd(QQView target) {
                    decreaseAnimationLock();
                    //Gdx.app.error("QQList", "insert(move).end" + animationLock);

                }
            }));
        }

        // 插入並且做插入的動畫...
        QQView view = adapter.getView(index);
        childrenView.add(index, view);
        if (wrapHeight) {
            height = childrenView.size() * view.height
                    + (childrenView.size() - 1) * Game.Size.WIDGET_MARGIN
                    + topPadding
                    + bottomPadding;
        }
        // set default position before animation
        float y = height - topPadding - view.getHeight() * (index + 1) - 2 * index + scrollY;
        view.setPosition(-this.width, y);

        view.applyAnimation(new InsertAnimation(this.width / 0.2f, 0f, 0.2f).listener(
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
                    }
                }
        ));
    }

    public void remove(int index) {
        //Gdx.app.error("QQList", "start remove.");
        removeTarget = childrenView.remove(index);
        removeTarget.applyAnimation(new RemoveAnimation(this.width / 0.2f, this.width).listener(
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

                        //target = null;
                        if (wrapHeight) {
                            height = childrenView.size() * removeTarget.height
                                    + (childrenView.size() - 1) * Game.Size.WIDGET_MARGIN
                                    + topPadding
                                    + bottomPadding;
                        }
                        removeTarget = null;
                    }
                }
        ));

        // 先做一個永遠是下面往上移的版本來看看 ...

        for (int i = index, s = childrenView.size(); i < s; ++i) {
            QQView view = childrenView.get(i);
            view.applyAnimation(new MoveVerticalAnimation(view.getHeight() /0.2f, view.getHeight(), 0.2f).listener(new QQAnimation.AnimationListener() {
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
            }));
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
    private int animationLock = 0;
    private void increaseAnimationLock() {
        animationLock++;
        //Gdx.app.error("QQList", "animation lock++ : " + animationLock );
    }
    private void decreaseAnimationLock() {
        animationLock--;
        //Gdx.app.error("QQList", "animation lock-- : " + animationLock );
        if (0 == animationLock) {
            rearrangeChildren();
            float totalHeight = - Game.Size.WIDGET_MARGIN;
            for (QQView v : childrenView) {
                totalHeight += (Game.Size.WIDGET_MARGIN + v.height); // 2 = widget margin...
            }
            maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
        } else if (0 > animationLock) {
            throw new GdxRuntimeException("animation lock error.");
        }
    }

    @Override
    public void act(float delta) {
        for (QQView view : childrenView)
            view.act(delta);
        if (null != removeTarget)
            removeTarget.act(delta);
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
                float x = target.getX() + delta * velocity;
                target.setPosition(x, target.getY());
                if (x >= endX)
                    return false;
            }
            return true;
        }

        public InsertAnimation listener(QQAnimation.AnimationListener listener) {
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

        public RemoveAnimation listener(QQAnimation.AnimationListener listener) {
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
            //Gdx.app.error("QQList", "move up : " + moveUpVelocity + "," + distance + "," + delay);
        }

        @Override
        public boolean goOn(float delta) {
            // delay first
            if (delay >= 0) {
                delay -= delta;
            } else {
                float y = target.getY() + delta * velocity;
                distance -= Math.abs(delta * velocity);
                target.setPosition(target.getX(), y);
                if (distance <= 0)
                    return false;
            }
            return true;
        }

        public MoveVerticalAnimation listener(QQAnimation.AnimationListener listener) {
            addListener(listener);
            return this;
        }

    }

    private Vector2 touchDownPos;// = new Vector2();
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        if (0 < animationLock)
            return false;
        // 1. keep touch down position for scroll
        //touchDownPos = new Vector2(relativeX, relativeY);
        touchY = relativeY;

        // 2. walk through all child and find out hit one, send touch down to it.
        QQView target = null;
        for (QQView child : childrenView) {
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                if (target instanceof IsTouchable) {
                    hitBeforeScroll = (IsTouchable) target;
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
        if (0 < animationLock)
            return false;
        touchY = -1;
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
                if (target.touchUp(childRelativeX, childRelativeY)) {
                    if (null != listener) {
                        listener.onClick(i);
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
        if (0 < animationLock || 0 > touchY)
            return false;
        // 1. do scroll ...
        scrollY += relativeY - touchY;
        touchY = relativeY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        rearrangeChildren();
        if (null != hitBeforeScroll) {
            hitBeforeScroll.cancelTouching();
        }


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
        if (0 < animationLock)
            return false;
        // 1. do scroll ...
        scrollY += 60 * amountY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        //Gdx.app.error("QQList", "scrollY = " + scrollY + "@" + this);
        //Gdx.app.error("QQListView", "scrolled : " + scrollY);
        rearrangeChildren();
        return false;
    }

    @Override
    public void cancelTouching() {
        touchY = -1;
    }


    /*
        click listener
     */
    private QQClickListener listener;
    public void addQQClickListener(QQClickListener listener) {
        this.listener = listener;
    }



    //private Rectangle scissorArea;
    private Camera camera;
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    private void rearrangeChildren() {
        //Gdx.app.error("QQList", "rearrangeChildren()");
        float anchorY = /*y +*/ height - topPadding;

        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView view = childrenView.get(i);
            float posY = anchorY - view.height;

            view.setPosition(/*x + */leftPadding, posY + scrollY);

            anchorY = posY - Game.Size.WIDGET_MARGIN;
        }
        // 好像不應該放在這邊....
        //maxScrollY = -anchorY;
    }


    /*
        IsParent series
     */
    private ArrayList<QQView> childrenView = new ArrayList<>();
    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
        // recalculate height
        if (wrapHeight) {
            height = childrenView.size() * view.height
                    + (childrenView.size() - 1) * Game.Size.WIDGET_MARGIN
                    + topPadding
                    + bottomPadding;
        }
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {

        batch.flush();
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, width, height);
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
        if (ScissorStack.pushScissors(scissors)) {
            for (QQView view : childrenView) {
                //if (view.getY() <= height || view.getY() >= 0) {
                // draw views in visible range.
                if (view.isVisible())
                    view.draw(batch, originX, originY);
                //}
            }
            batch.flush();
            ScissorStack.popScissors();
        }
        // remove target will not be cut..
        if (null != removeTarget)
            removeTarget.draw(batch, originX, originY);

    }
}
