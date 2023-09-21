package com.qqhouse.ui;

/*
  * 像是 android 的 listview 一樣, 需要 adapter
  * 先做 vertical 的, 就是垂直的 list view.
  * 考慮超出邊界就不用畫, 以及處理 click, long click, scroll ....
  *



*/
public class QQListView extends QQView {

    public interface Adapter {
        public int count();
        public QQView getView(int index);
    }

    public QQListView(QQScreen master) {
        super(master);
    }






    @Override
    public void dispose() {

    }
}
