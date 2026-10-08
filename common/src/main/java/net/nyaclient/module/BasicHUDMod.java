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

import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;

import java.awt.*;

public abstract class BasicHUDMod extends HUDMod {
    public BasicHUDMod(String term, String iconPath, ModCategory category, float x, float y) {
        super(term, iconPath, category, x, y);
    }

    protected abstract String getString();

    @Override
    public float getWidth() {
        return NekoFontRenderer.getTextWidth(9, getString()) + 5;
    }

    @Override
    public float getHeight() {
        return NekoFontRenderer.getTextHeight(9) + 5;
    }

    @Override
    public void render() {
        NekoRenderer.drawRoundedRect(x, y, getWidth(), getHeight(), 3, new Color(0, 0, 0, 110));
        NekoRenderer.drawOutlineRounded(x, y, getWidth(), getHeight(), 1, 3, new Color(255, 255, 255, 40));
        NekoFontRenderer.renderCenteredText(9, x+(getWidth()/2f), y+(getHeight()/2f), getString(), Color.WHITE);
    }
}
