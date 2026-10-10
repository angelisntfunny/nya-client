/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.module;

import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.Services;

import java.awt.*;

public abstract class HUDMod extends AbstractMod implements IHUDMod {
    protected float x, y;

    protected boolean dragging;
    protected float lastX, lastY;

    public HUDMod(String term, String iconPath, ModCategory category, float x, float y) {
        super(term, iconPath, category);
        this.x = x;
        this.y = y;
    }

    public abstract float getWidth();
    public abstract float getHeight();

    public abstract void render();

    @Override
    public boolean drag(int mouseX, int mouseY) {
        if (this.dragging || ((mouseX >= this.getX() && mouseX <= this.getX() + getWidth()) &&
                (mouseY >= this.getY() && mouseY <= this.getY() + getHeight()))) {
            NekoRenderer.drawOutline(x - 5, y - 5, x + getWidth() + 5, y + getHeight() + 5, 2, new Color(0, 0, 0, 112));
        }
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;

            if (!Services.LWJGL_BRIDGE.isMousePressed(0))
                this.dragging = false;

            return true;
        }

        if ((mouseX >= this.getX() && mouseX <= this.getX() + getWidth()) &&
                (mouseY >= this.getY() && mouseY <= this.getY() + getHeight())) {
            if (Services.LWJGL_BRIDGE.isMousePressed(0) && !this.dragging) {
                this.lastX = this.x - mouseX;
                this.lastY = this.y - mouseY;
                this.dragging = true;
                return true;
            }
        }

        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
