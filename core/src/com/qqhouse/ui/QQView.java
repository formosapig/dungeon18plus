package com.qqhouse.ui;

public abstract class QQView {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    abstract public void dispose();

    /*
    chain function
     */

    public QQView position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public QQView size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

}
