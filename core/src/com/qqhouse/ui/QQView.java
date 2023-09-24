package com.qqhouse.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Iterator;

public abstract class QQView {

    protected interface ChildrenVisitor {
        abstract void visitDraw(SpriteBatch batch);
    }
    protected ChildrenVisitor visitor = null;

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

    /*
        background series...
     */
    protected boolean pressed = false;
    protected boolean enable = true;
    protected boolean touchable = true;
    protected NinePatch bgNormal = null;
    protected NinePatch bgPressed = null;
    protected NinePatch bgDisable = null;

    protected void drawBackground(SpriteBatch batch) {
        if (!enable && null != bgDisable) {
            bgDisable.draw(batch, x, y, width, height);
            return;
        }
        if (pressed && null != bgPressed) {
            bgPressed.draw(batch, x, y, width, height);
            return;
        }
        if (null != bgNormal)
            bgNormal.draw(batch, x, y, width, height);
    }

    protected void drawForeground(SpriteBatch batch) {}

    public final void draw(SpriteBatch batch) {
        drawBackground(batch);
        drawForeground(batch);
        if (this instanceof ChildrenVisitor) {
        //if (null != visitor) {
            ((ChildrenVisitor)this).visitDraw(batch);
        }
    }

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

    /*
        padding. top, bottom, left, right
     */
    protected float topPadding, bottomPadding, leftPadding, rightPadding;
    public void setPadding(float top, float bottom, float left, float right) {
        topPadding = top;
        bottomPadding = bottom;
        leftPadding = left;
        rightPadding = right;
    }

    public void setPadding(float all) {
        topPadding = all;
        bottomPadding = all;
        leftPadding = all;
        rightPadding = all;
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

    public QQView padding(float all) {
        topPadding = all;
        bottomPadding = all;
        leftPadding = all;
        rightPadding = all;
        return this;
    }

}
