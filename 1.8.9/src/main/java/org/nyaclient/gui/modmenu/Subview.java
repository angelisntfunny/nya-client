package org.nyaclient.gui.modmenu;

public abstract class Subview {
    public float x, y, width, height;

    protected Subview(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void handleMouse(float mouseX, float mouseY);
    public abstract void draw(float mouseX, float mouseY, float deltaTime);
    public abstract void onClick(float mouseX, float mouseY, int button);
}
