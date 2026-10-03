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

import net.nyaclient.util.IOUtils;
import org.lwjgl.nanovg.NanoVG;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

public class NekoFontRenderer {
    @SuppressWarnings("FieldCanBeLocal")
    private static ByteBuffer fontBuffer;
    private static int fontId = -1;
    private static final String FONT_PATH = "InterVariable.ttf";

    public static void initFont() throws IOException {
        if (fontId != -1) throw new IllegalStateException("font cannot be initialized twice!");

        fontBuffer = IOUtils.ioResourceToByteBuffer("/assets/nyaclient/" + FONT_PATH);

        fontId = NanoVG.nvgCreateFontMem(NekoRenderer.getContext(), FONT_PATH, fontBuffer, false);

        if (fontId == -1) {
            throw new IllegalStateException("Failed to register font.");
        }
    }


    public static void renderCenteredText(float size, float centerX, float centerY, String text, Color color) {
        NanoVG.nvgTextAlign(NekoRenderer.getContext(), NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);

        NanoVG.nvgFillColor(NekoRenderer.getContext(), NekoRenderer.getColor(color));
        NanoVG.nvgFontFace(NekoRenderer.getContext(), FONT_PATH);
        NanoVG.nvgFontSize(NekoRenderer.getContext(), size);
        NanoVG.nvgText(NekoRenderer.getContext(), centerX, centerY, text);
    }

    public static void renderText(float size, float x, float y, String text, Color color) {
        if (fontId == -1) return;

        NanoVG.nvgFillColor(NekoRenderer.getContext(), NekoRenderer.getColor(color));
        NanoVG.nvgFontFace(NekoRenderer.getContext(), FONT_PATH);
        NanoVG.nvgFontSize(NekoRenderer.getContext(), size);
        NanoVG.nvgText(NekoRenderer.getContext(), x, y, text);
    }

    public static float getTextWidth(float size, String text) {
        if (fontId == -1) return 0;

        NanoVG.nvgFontFace(NekoRenderer.getContext(), FONT_PATH);
        NanoVG.nvgFontSize(NekoRenderer.getContext(), size);

        float[] bounds = new float[4];
        NanoVG.nvgTextBounds(NekoRenderer.getContext(), 0, 0, text, bounds);

        return bounds[2] - bounds[0]; // right - left
    }

    public static float getTextHeight(float size, String text) {
        if (fontId == -1) return 0;

        NanoVG.nvgFontFace(NekoRenderer.getContext(), FONT_PATH);
        NanoVG.nvgFontSize(NekoRenderer.getContext(), size);

        float[] bounds = new float[4];
        NanoVG.nvgTextBounds(NekoRenderer.getContext(), 0, 0, text, bounds);

        return bounds[3] - bounds[1]; // bottom - top
    }

    public static float getTextHeight(float size) {
        return getTextHeight(size, "E");
    }
}
