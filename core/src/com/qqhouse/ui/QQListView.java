package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
  * 像是 android 的 listview 一樣, 需要 adapter
  * 先做 vertical 的, 就是垂直的 list view.
  * 考慮超出邊界就不用畫, 以及處理 click, long click, scroll ....
  *



*/
public class QQListView extends QQView implements QQView.ChildrenVisitor {

    public interface Adapter {
        public int count();
        public QQView getView(int index);
    }

    public QQListView(QQScreen master) {
        super(master);
    }

    private Adapter adapter;
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }



    @Override
    public void visitDraw(SpriteBatch batch) {
        for (int i = 0, s = adapter.count(); i < s; ++i) {
            QQView view = adapter.getView(i);
            view.draw(batch);
        }
    }

    @Override
    public void dispose() {

    }
}
