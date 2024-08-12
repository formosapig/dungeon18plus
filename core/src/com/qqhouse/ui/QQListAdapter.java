package com.qqhouse.ui;

import com.qqhouse.ui.animation.QQAnimationList;

public abstract class QQListAdapter {
    private QQList1 list;

    protected void setList(QQList1 list) {
        this.list = list;
    }

    // for animation list
    public void insert(int index) {
        if (list instanceof QQAnimationList)
            ((QQAnimationList) list).insert(index);
    }
    // for animation list
    public void remove(int index) {
        if (list instanceof QQAnimationList)
            ((QQAnimationList) list).remove(index);
    }
    // for animation list
    public void updateAll() {
        list.updateAll();
    }

    // must implement for list
    public abstract int getSize();
    public abstract QQView getView(int index);

    // for animation list ...
    public void updateView(int index, QQView view) {}

    // callback
    public void onAnimationEnd() {}
}
