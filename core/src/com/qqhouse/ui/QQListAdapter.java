package com.qqhouse.ui;

import com.qqhouse.ui.animation.QQAnimationList;

public abstract class QQListAdapter {
    private QQList1 list;

    protected void setList(QQList1 list) {
        this.list = list;
    }

    public void insert(int index) {
        if (list instanceof QQAnimationList)
            ((QQAnimationList) list).insert(index);
    }

    public void remove(int index) {
        if (list instanceof QQAnimationList)
            ((QQAnimationList) list).remove(index);
    }

    public void updateAll() {
        list.updateAll();
    }

    public abstract int getSize();
    public abstract QQView getView(int index);
    public abstract void updateView(int index, QQView view);

    // callback
    public void onAnimationEnd() {}
}
