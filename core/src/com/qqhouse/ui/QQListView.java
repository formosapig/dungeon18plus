package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.qqhouse.dungeon18plus.Game;

import java.util.ArrayList;

/*
  * 像是 android 的 listview 一樣, 需要 adapter
  * 先做 vertical 的, 就是垂直的 list view.
  * 考慮超出邊界就不用畫, 以及處理 click, long click, scroll ....
  *



*/
public class QQListView extends QQView implements QQView.IsParent, QQView.IsTouchable {

    public QQListView() {
        //super(master);
        calculateAnchorY = true;
        anchorY = 0;
        scrollY = 0;
        previousScrollY = 0;
        maxScrollY = 0;
    }

    private boolean calculateAnchorY;
    private float anchorY;
    private float touchY = -1, scrollY, maxScrollY, previousScrollY; // vertical only.
    private IsTouchable hitBeforeScroll = null;

    /*
        add child view...
     */
    public void addView(QQView view) {
        // when add to parent, recalculate size....
        //Gdx.app.error("QQListView", "view matchWidth" + view.matchWidth + "@" + view);
        if (view.matchWidth) {//view.width == QQView.MATCH_PARENT) {
            view.setSize(this.width - leftPadding - rightPadding, view.height);
        }
        if (view.matchHeight) { //view.height == QQView.MATCH_PARENT) {
            view.setSize(view.width, this.height - topPadding - bottomPadding);
        }
        childrenView.add(view);
        rearrangeChildren();
        float totalHeight = - Game.Size.WIDGET_MARGIN;
        for (QQView v : childrenView) {
            totalHeight += (Game.Size.WIDGET_MARGIN + v.height); // 2 = widget margin...
        }
        maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
    }

    private void rearrangeChildren() {
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



    @Override
    public void dispose() {
        for (QQView view : childrenView) {
            view.dispose();
        }
    }

    /*
        touch down event.
        1. list view will keep touch down event.
        2. transfer event to child, like button needs touch down event to change background.
     */
    private Vector2 touchDownPos;// = new Vector2();
    // get (x, y) relative to my position
    @Override
    public boolean touchDown(float relativeX, float relativeY) {
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
        // 1. trace this event to exit scroll mode ...
        //touchDownPos = null; // ??
        //previousScrollY = scrollY;
        //Gdx.app.error("QQListView.java", "previousScrollY = " + previousScrollY);
        touchY = -1;

        // 2. tell child touch up
        QQView target = null;
        for (QQView child : childrenView) {
            float childRelativeX = relativeX - child.getX();
            float childRelativeY = relativeY - child.getY();
            target = child.hit(childRelativeX, childRelativeY);
            if (null != target) {
                return target.touchUp(childRelativeX, childRelativeY);
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
        //Gdx.app.error("QQListView", String.format("%4.2f,%4.2f,%4.2f",scrollY,relativeY,touchY));
        if (0 > touchY)
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
        // 1. do scroll ...
        scrollY += 20 * amountY;
        if (scrollY < 0) scrollY = 0;
        if (scrollY > maxScrollY) scrollY = maxScrollY;
        //Gdx.app.error("QQListView", "scrolled : " + scrollY);
        rearrangeChildren();
        return false;
    }

    //private Rectangle scissorArea;
    private Camera camera;
    public void setCamera(Camera camera) {
        this.camera = camera;


        // for gl scissor test use....
        //Rectangle scissors = new Rectangle();
        //Rectangle clipBounds = new Rectangle(x, y, width, height);
        //ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);

    }

    /*
        IsParent series
     */
    private ArrayList<QQView> childrenView = new ArrayList<>();

    @Override
    public void arrangeChildren() {

    }

    @Override
    public void addChild(QQView view) {
        childrenView.add(view);
    }

    @Override
    public void removeChild(QQView view) {}

    @Override
    public void onParentSizeChanged(float width, float height) {

    }

    @Override
    public void onChildSizeChanged(QQView child) {

    }


    @Override
    public void drawChildren(SpriteBatch batch, float relativeX, float relativeY) {

        //batch.flush();

        // test using openGL scissor test
        //Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);

        // 尺寸不對, 這是螢幕的尺寸, 但我們擁有的是繪圖的尺寸...
        //Gdx.gl.glScissor((int)x, (int)y, 350, 400/*(int)height*/);

        //Gdx.app.error("TEST", "ListView :" + x + "," + y + "," + width + "," + height);

        // flush previous draw...
        batch.flush();

        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, width, height);
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
        if (ScissorStack.pushScissors(scissors)) {
            for (QQView view : childrenView)
                view.draw(batch, relativeX, relativeY);
            batch.flush();
            ScissorStack.popScissors();
        }

        //batch.flush();

        //Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);

    }

    @Override
    public void cancelTouching() {
        // child does not care...
        touchY = -1;
    }
}
