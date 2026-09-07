package org.nyaclient.utils;

import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.awt.*;
import java.util.HashMap;

public class NanoVGManager {
    private static final HashMap<Color, NVGColor> COLORS = new HashMap<>();

    @Getter
    private static long nvgContext = 0;

    public static void init() {
        if (GLContext.getCapabilities().OpenGL30) {
            int flags = NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES;

            nvgContext = NanoVGGL3.nvgCreate(flags);

            if (nvgContext == 0) {
                throw new RuntimeException("Could not init NanoVG.");
            }
        }
    }

    public static void render(Runnable task) {
        MinecraftClient client = MinecraftClient.getInstance();
        Window window = new Window(client);

        long nvg = NanoVGManager.getNvgContext();

        float pixelRatio = window.getScaleFactor();
        float guiWidth = (float) window.getScaledWidth();
        float guiHeight = (float) window.getScaledHeight();

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

        NanoVG.nvgBeginFrame(nvg, guiWidth, guiHeight, pixelRatio);

        task.run();

        NanoVG.nvgEndFrame(nvg);
        GL11.glPopAttrib();
    }

    public static NVGColor getColor(Color awtcolor) {
        if (COLORS.containsKey(awtcolor)) return COLORS.get(awtcolor);

        NVGColor color = NVGColor.create();
        NanoVG.nvgRGBA((byte) awtcolor.getRed(), (byte) awtcolor.getGreen(), (byte) awtcolor.getBlue(), (byte) awtcolor.getAlpha(), color);

        COLORS.put(awtcolor, color);

        return COLORS.get(awtcolor);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius, Color awtcolor) {
        NanoVG.nvgBeginPath(nvgContext);
        NanoVG.nvgRoundedRect(nvgContext, x, y, width, height, radius);
        NanoVG.nvgFillColor(nvgContext, getColor(awtcolor));
        NanoVG.nvgFill(nvgContext);
        NanoVG.nvgClosePath(nvgContext);
    }

}
