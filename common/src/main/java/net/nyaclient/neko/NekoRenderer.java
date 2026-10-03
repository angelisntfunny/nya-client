/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.neko;

import net.nyaclient.platform.Services;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// i chose the name neko for the rendering engine because i like cats :]
public class NekoRenderer {
    private static long context = 0;
    private static final Map<Color, NVGColor> COLORS = new HashMap<>();

    public static void init() throws IOException {
        context = Services.NANOVG_BRIDGE.init();
        NekoFontRenderer.initFont();
    }

    public static void render(Runnable runnable) {
        Services.NANOVG_BRIDGE.render(runnable, context);
    }

    public static long getContext() {
        return context;
    }

    public static NVGColor getColor(Color awtcolor) {
        if (COLORS.containsKey(awtcolor)) return COLORS.get(awtcolor);

        NVGColor color = NVGColor.create();
        NanoVG.nvgRGBA((byte) awtcolor.getRed(), (byte) awtcolor.getGreen(), (byte) awtcolor.getBlue(), (byte) awtcolor.getAlpha(), color);

        COLORS.put(awtcolor, color);

        return COLORS.get(awtcolor);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius, Color awtcolor) {
        NanoVG.nvgBeginPath(context);
        NanoVG.nvgRoundedRect(context, x, y, width, height, radius);
        NanoVG.nvgFillColor(context, getColor(awtcolor));
        NanoVG.nvgFill(context);
        NanoVG.nvgClosePath(context);
    }

    public static void drawRect(float x, float y, float width, float height, Color awtcolor) {
        NanoVG.nvgBeginPath(context);
        NanoVG.nvgRect(context, x, y, width, height);
        NanoVG.nvgFillColor(context, getColor(awtcolor));
        NanoVG.nvgFill(context);
        NanoVG.nvgClosePath(context);
    }

    public static void drawOutline(float left, float top, float right, float bottom, float thickness, Color color) {
        drawRect(left, top, right - left, thickness, color);
        drawRect(left, bottom - thickness, right - left, thickness, color);
        drawRect(left, top + thickness, thickness, bottom - top - (thickness * 2), color);
        drawRect(right - thickness, top + thickness, thickness, bottom - top - (thickness * 2), color);
    }

    public static void drawRoundedRectSpecifyRadius(float x, float y, float width, float height, float topLeft, float topRight, float bottomRight, float bottomLeft, Color color) {
        NanoVG.nvgBeginPath(context);
        NanoVG.nvgRoundedRectVarying(context, x, y, width, height, topLeft, topRight, bottomRight, bottomLeft);
        NanoVG.nvgFillColor(context, getColor(color));
        NanoVG.nvgFill(context);
        NanoVG.nvgClosePath(context);
    }
}
