package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
}
