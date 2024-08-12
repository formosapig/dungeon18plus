package com.qqhouse.ui.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQView;
import com.qqhouse.ui.animation.QQAnimation;
import com.qqhouse.ui.animation.QQInsertAnimation;
import com.qqhouse.ui.animation.QQMoveVerticalAnimation;
import com.qqhouse.ui.animation.QQRemoveAnimation;

import java.util.Locale;

public class QQAnimationList extends QQList1 implements QQView.IsTouchable {

    private float changeScrollY; // scrollY need to change after rearrange children.
    private static final float animVPeriod = 0.11f;    // in sec
    private static final float animHPeriod = 0.23f;

    public QQAnimationList(Viewport viewport, int innerMargin) {
        super(viewport, innerMargin);
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
                //Gdx.app.error("QQList", String.format(Locale.US, "MoveUpward rI : %d , iI : %d", removeIndex, insertIndex));
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
        if (this.height - topPadding >= insertOneY && bottomPadding <= (insertOneY + insertOne.getHeight())) {
            insertOne.setPosition(-this.width, insertOneY);
            insertOne.applyAnimation(new QQInsertAnimation(this.width / animHPeriod, 0f, animHPeriod + preDelay).listener(
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
            y -= (childrenView.get(i).getHeight() + innerMargin);
        }
        return y + innerMargin + scrollY;
    }

    private int removeIndex = -1;
    public void remove(int index) {
        //Gdx.app.error("QQList", "start remove.");
        // FIXME remove not resetWrapHeight ...
        removeIndex = index;
        removeTarget = childrenView.remove(index);
        removeTarget.applyAnimation(new QQRemoveAnimation(this.width / animHPeriod, this.width).listener(
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
        float gap = removeTarget.getHeight() + innerMargin;
        if (scrollY > gap && (maxScrollY - scrollY) < gap) {
            moveDownward(0, index - 1, animHPeriod);
            changeScrollY = -gap;
        } else {
            //Gdx.app.error("QQList", String.format(Locale.US, "MoveUpward index : %d", index));

            moveUpward(index, childrenView.size() - 1, animHPeriod);
        }

    }

    // 由上往下填滿被移掉的 view
    private void moveDownward(int start, int end, float delay) {
        for (int i = start; i <= end; ++i) {
            QQView child = childrenView.get(i);
            float gap = child.getHeight() + innerMargin;
            child.applyAnimation(new QQMoveVerticalAnimation(-gap / animVPeriod, gap, delay)
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
            float gap = child.getHeight() + innerMargin;
            child.applyAnimation(new QQMoveVerticalAnimation(gap / animVPeriod, gap, delay)
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
            float totalHeight = - innerMargin;
            for (QQView v : childrenView) {
                totalHeight += (innerMargin + v.getHeight()); // 2 = widget margin...
            }
            maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
            if (maxScrollY < 0)
                maxScrollY = 0;
            // on animation end
            if (null != adapter) {
                //Gdx.app.error("QQAnimationList", String.format(Locale.US, "adapter.onAnimationEnd %d", childrenView.size()));
                adapter.onAnimationEnd();
            }
        } else if (0 > animLock) {
            throw new GdxRuntimeException("animation lock error.");
        }
    }

    // with insert, remove supported. adapter size not change through updateAll
    // FIXME consider update all function.
    @Override
    public void updateAll() {
        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            adapter.updateView(i, childrenView.get(i));
        }
    }

    @Override
    public void act(float delta) {
        for (QQView view : childrenView)
            view.act(delta);
        if (null != removeTarget)
            removeTarget.act(delta);
        super.act(delta);
    }

    @Override
    public boolean touchDown(float relativeX, float relativeY) {
        if (0 < animLock) {
            //Gdx.app.error("QQList.touchDown", "animLock = " + animLock);
            return false;
        }
        return super.touchDown(relativeX, relativeY);
    }

    @Override
    public boolean touchUp(float relativeX, float relativeY) {
        if (0 < animLock) {
            //Gdx.app.error("QQList.touchUp", "animLock = " + animLock);
            return false;
        }
        return super.touchUp(relativeX, relativeY);
    }

    @Override
    public boolean touchDragged(float relativeX, float relativeY) {
        if (0 < animLock || 0 > touchY)
            return false;
        return super.touchDragged(relativeX, relativeY);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (0 < animLock)
            return false;
        return super.scrolled(amountX, amountY);
    }

    @Override
    public void drawChildren(SpriteBatch batch, float originX, float originY) {
        batch.flush();
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(originX, originY, width, height);
        // QQList 變成 sub view 時, 座標又變換了....
        //Rectangle clipBounds = new Rectangle(x, y, width, height);
        //ScissorStack.calculateScissors(viewport.getCamera(), batch.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.calculateScissors(viewport.getCamera(), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight(), batch.getTransformMatrix(), clipBounds, scissors);

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
