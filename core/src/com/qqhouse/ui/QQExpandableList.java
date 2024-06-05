package com.qqhouse.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Game;

public class QQExpandableList extends QQList1 implements QQView.IsTouchable {


    public QQExpandableList(Viewport viewport, int innerMargin) {
        super(viewport, innerMargin);
    }

    /*
                adapter ...
             */
    public static abstract class Adapter {
        private QQExpandableList list;
        public void setList(QQExpandableList list) {this.list = list;}

        public abstract int getGroupCount();
        public abstract QQView getGroupView(int groupPosition, boolean isExpanded);
        public abstract int getChildrenCount(int groupPosition);
        public abstract QQView getChildView(int groupPosition, int childPosition,
                                            boolean isLastChild);
    }

    // max value to scroll
    private float maxScrollY;


    // calculate all child height ...
    // if group is collapse, all child of this group sould set visible to false...
    private void calculateMaxScrollY() {
        float totalHeight = 0;
        for (QQView child : childrenView) {
            if (child.isVisible() || 0 == child.getHeight()) {
                totalHeight += child.getHeight() + innerMargin;
            }
        }
        // 至少有一點高度時才需要減去最後一個 margin
        if (0 < totalHeight)
            totalHeight -= innerMargin;
        maxScrollY = totalHeight - (this.height - this.topPadding - this.bottomPadding);
        if (maxScrollY <0)
            maxScrollY = 0;
    }

    @Override
    public void setSize(float w, float h) {
        super.setSize(w, h); // will trigger arrangeChildren() ...
        calculateMaxScrollY();
    }

    @Override
    public void resetWrapHeight() {
        float h = topPadding + bottomPadding;
        for (QQView child : childrenView) {
            if (child.isVisible())
                h += child.height + innerMargin;
        }
        if (0 < h)
            h -= Game.Size.WIDGET_MARGIN;
        // if maxHeight is set...
        if (0 < maxHeight && h >= maxHeight)
            h = maxHeight;
        this.height = h;

        // tell parent arrange children
        if (null != parent)
            parent.arrangeChildren();
    }

    /*
        Adapter series
     */
    private QQExpandableList.Adapter adapter;
    public void setAdapter(QQExpandableList.Adapter adapter) {
        this.adapter = adapter;
        adapter.setList(this);
        // add view...
        for (int g = 0, gs = adapter.getGroupCount(); g < gs; ++g) {
            // get group view, default is expanded.
            addChild(adapter.getGroupView(g, true));
            // child of this group
            for (int c = 0, cs = adapter.getChildrenCount(g); c < cs; ++c) {
                addChild(adapter.getChildView(g, c, c == (cs - 1)));
            }
        }

        arrangeChildren();
        calculateMaxScrollY();
    }




    @Override
    public void cancelTouching() {

    }
}
