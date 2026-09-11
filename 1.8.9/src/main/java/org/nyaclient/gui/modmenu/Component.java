package org.nyaclient.gui.modmenu;

public abstract class Component {
    protected float x, y;

    protected Component(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(float mouseX, float mouseY);
    public abstract void onClick(float mouseX, float mouseY, int button);

    public abstract float getWidth();
    public abstract float getHeight();
}
