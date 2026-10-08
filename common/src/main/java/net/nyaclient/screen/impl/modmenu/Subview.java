/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.screen.impl.modmenu;

import net.nyaclient.enums.Key;

public abstract class Subview {
    public float x, y, width, height;

    protected Subview(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void init(float width, float height);
    public abstract void render(float mouseX, float mouseY, float deltaTime, float width, float height);
    public abstract void onClick(double mouseX, double mouseY, int button, float width, float height);

    public void onKey(Key key) {}
}
