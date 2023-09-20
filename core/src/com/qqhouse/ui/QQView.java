package com.qqhouse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class QQView {

    /*
        with screen
     */
    public QQView(QQScreen master) {
        this.master = master;
        master.addView(this);
    }

    protected QQScreen master;

    public QQView hit(float x, float y) {
        float shiftX = x - this.x;
        float shiftY = y - this.y;
        if (shiftX >= 0 && shiftX <= width && shiftY >= 0 && shiftY <= height)
            return this;
        else
            return null;
    }

    public boolean touchDown(float x, float y) {
        return false;
    }

    public boolean touchUp(float x, float y) {
        return false;
    }


    /*
        called by QQScreen
     */
    public void act(float delta) {};
    public void draw(SpriteBatch batch) {};

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
