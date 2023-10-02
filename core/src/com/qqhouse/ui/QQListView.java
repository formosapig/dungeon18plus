package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

import java.util.ArrayList;

/*
  * 像是 android 的 listview 一樣, 需要 adapter
  * 先做 vertical 的, 就是垂直的 list view.
  * 考慮超出邊界就不用畫, 以及處理 click, long click, scroll ....
  *



*/
public class QQListView extends QQView implements QQView.ChildrenVisitor {

    public QQListView() {
        //super(master);
        childrenView = new ArrayList<QQView>();
        calculateAnchorY = true;
        anchorY = 0;
        scrollY = 0;
        previousScrollY = 0;
        maxScrollY = 0;
    }

    private final ArrayList<QQView> childrenView;
    private boolean calculateAnchorY;
    private float anchorY;
    private float scrollY, maxScrollY, previousScrollY; // vertical only.

    /*
        add child view...
     */
    public void addView(QQView view) {
        // when add to parent, recalculate size....
        if (view.width == QQView.FILL_PARENT) {
            view.setSize(this.width - leftPadding - rightPadding, view.height);
        }
        if (view.height == QQView.FILL_PARENT) {
            view.setSize(view.width, this.height - topPadding - bottomPadding);
        }
        childrenView.add(view);
        rearrangeChildren();
        float totalHeight = -4;
        for (QQView v : childrenView) {
            totalHeight += 4 + v.height; // 4 = widget margin...
        }
        maxScrollY = totalHeight - height + bottomPadding + topPadding; // padding not counting.
    }

    private void rearrangeChildren() {
        float anchorY = y + height - topPadding;

        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView view = childrenView.get(i);
            float posY = anchorY - view.height;

            view.setPosition(x + leftPadding, posY + scrollY);

            anchorY = posY - 4;
        }
        // 好像不應該放在這邊....
        //maxScrollY = -anchorY;
    }

    @Override
    public void visitDraw(SpriteBatch batch) {

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
            for (QQView view : childrenView) {
                view.draw(batch);
            }
            batch.flush();
            ScissorStack.popScissors();
        }

        //batch.flush();

        //Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);

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
    @Override
    public boolean touchDown(float x, float y) {
        // 1. keep touch down position for scroll
        touchDownPos = new Vector2(x, y);

        // 2. walk through all child and find out hit one, send touch down to it.
        QQView target = null;
        for (QQView child : childrenView) {
            target = child.hit(x, y);
            if (null != target) {
                return target.touchDown(x, y);
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
    public boolean touchUp(float x, float y) {
        // 1. trace this event to exit scroll mode ...
        touchDownPos = null; // ??
        previousScrollY = scrollY;

        // 2. tell child touch up
        QQView target = null;
        for (QQView child : childrenView) {
            target = child.hit(x, y);
            if (null != target) {
                return target.touchUp(x, y);
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
    public boolean touchDragged(float x, float y) {
        // 1. do scroll ...
        if (null != touchDownPos) {
            scrollY = previousScrollY + y - touchDownPos.y;
            if (scrollY < 0) scrollY = 0;
            if (scrollY > maxScrollY) scrollY = maxScrollY;
            rearrangeChildren();
        }

        // 2. tell child to check if exit hit area.
        for (QQView child : childrenView) {
            child.touchDragged(x, y);
        }

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


}
