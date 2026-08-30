package org.nyaclient.module;

import lombok.Getter;
import org.lwjgl.input.Mouse;
import org.nyaclient.utils.Draw2D;

import java.awt.*;

public abstract class HUDMod extends AbstractMod {
    @Getter protected int x, y;

    protected boolean dragging;
    protected int lastX, lastY;

    public HUDMod(String name, String description, ModCategory category, int key, int x, int y) {
        super(name, description, category, key);
        this.x = x;
        this.y = y;
    }

    public abstract int getWidth();
    public abstract int getHeight();

    public abstract void render();

    public void drag(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;

            if (!Mouse.isButtonDown(0))
                this.dragging = false;
        }

        if ((mouseX >= this.getX() && mouseX <= this.getX() + getWidth()) &&
            (mouseY >= this.getY() && mouseY <= this.getY() + getHeight())) {
            Draw2D.drawOutline(x - 5, y - 5, x + getWidth() + 5, y + getHeight() + 5, 2, 0x70000000);
            if (Mouse.isButtonDown(0) && !this.dragging) {
                this.lastX = this.x - mouseX;
                this.lastY = this.y - mouseY;
                this.dragging = true;
            }
        }
    }
}
