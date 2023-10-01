package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/*
  * 像是 android 的 listview 一樣, 需要 adapter
  * 先做 vertical 的, 就是垂直的 list view.
  * 考慮超出邊界就不用畫, 以及處理 click, long click, scroll ....
  *



*/
public class QQListView extends QQView implements QQView.ChildrenVisitor {

    public QQListView(QQScreen master) {
        super(master);
        childrenView = new ArrayList<QQView>();
        calculateAnchorY = true;
        anchorY = 0;
    }

    private final ArrayList<QQView> childrenView;
    private boolean calculateAnchorY;
    private float anchorY;

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
    }

    private void rearrangeChildren() {
        float anchorY = y + height - topPadding;

        for (int i = 0, s = childrenView.size(); i < s; ++i) {
            QQView view = childrenView.get(i);
            float posY = anchorY - view.height;

            view.setPosition(x + leftPadding, posY);

            anchorY = posY - 4;
        }
    }

    @Override
    public void visitDraw(SpriteBatch batch) {
        for (QQView view : childrenView) {
            view.draw(batch);
        }
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
    private Vector2 touchDownPos = new Vector2();
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

        // 2. tell child to check if exit hit area.
        for (QQView child : childrenView) {
            child.touchDragged(x, y);
        }

        return false;
    }



}
