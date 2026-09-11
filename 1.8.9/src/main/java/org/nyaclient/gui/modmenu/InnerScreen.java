package org.nyaclient.gui.modmenu;

public abstract class InnerScreen {
    public float x, y, width, height;

    protected InnerScreen(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(float mouseX, float mouseY);
    public abstract void onClick(float mouseX, float mouseY, int button);
}
