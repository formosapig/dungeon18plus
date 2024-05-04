package com.qqhouse.ui;

public class QQViewPager extends QQGroup2 {

    public static abstract class Adapter {

        public abstract int getSize();
        public abstract QQView getView(int index);
        public abstract void updateView(int index, QQView view);


    }








}
