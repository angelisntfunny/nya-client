package org.nyaclient.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.apache.commons.io.IOUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class FontRenderer {
    @SuppressWarnings("FieldCanBeLocal") // no this field can't be local
    private static ByteBuffer fontBuffer;
    private static int fontId = -1;
    private static final String fontName = "Inter.ttf";

    public static void initFont(long nvgContext, String resourcePath) throws IOException {
        fontBuffer = ioResourceToByteBuffer(resourcePath);

        fontId = NanoVG.nvgCreateFontMem(nvgContext, fontName, fontBuffer, false);

        if (fontId == -1) {
            throw new RuntimeException("Failed to register font.");
        }
    }

    public static void renderCenteredText(float size, float centerX, float centerY, String text, Color color) {
        if (fontId == -1) {
            return;
        }

        long vg = NanoVGManager.getNvgContext();

        NanoVG.nvgFontFace(vg, fontName);
        NanoVG.nvgFontSize(vg, size);

        float[] bounds = new float[4];

        NanoVG.nvgTextBounds(vg, 0, 0, text, bounds);

        float x = centerX - (bounds[0] + bounds[2]) / 2.0f;
        float y = centerY - (bounds[1] + bounds[3]) / 2.0f;

        NanoVG.nvgFillColor(vg, NanoVGManager.getColor(color));
        NanoVG.nvgText(vg, x, y, text);
    }



    public static void renderText(float size, float x, float y, String text, Color color) {
        if (fontId == -1) return;

        MinecraftClient client = MinecraftClient.getInstance();
        Window window = new Window(MinecraftClient.getInstance());

        int framebufferWidth = client.getFramebuffer().viewportWidth;
        int width = window.getWidth();

        float pixelRatio = (float) framebufferWidth / (float) width;

//        size = pixelRatio * size;

        NanoVG.nvgFillColor(NanoVGManager.getNvgContext(), NanoVGManager.getColor(color));
        NanoVG.nvgFontFace(NanoVGManager.getNvgContext(), fontName);
        NanoVG.nvgFontSize(NanoVGManager.getNvgContext(), size);
        NanoVG.nvgText(NanoVGManager.getNvgContext(), x, y, text);
    }

    public static void renderText(float x, float y, String text) {
        renderText(24.0F, x, y, text, Color.WHITE);
    }

    public static float getTextWidth(float size, String text) {
        if (fontId == -1) return 0;

        MinecraftClient client = MinecraftClient.getInstance();
        Window window = new Window(MinecraftClient.getInstance());

        int framebufferWidth = client.getFramebuffer().viewportWidth;
        int width = window.getWidth();

        float pixelRatio = (float) framebufferWidth / (float) width;
//        size *= pixelRatio;

        NanoVG.nvgFontFace(NanoVGManager.getNvgContext(), fontName);
        NanoVG.nvgFontSize(NanoVGManager.getNvgContext(), size);

        float[] bounds = new float[4];
        NanoVG.nvgTextBounds(NanoVGManager.getNvgContext(), 0, 0, text, bounds);

        return bounds[2] - bounds[0]; // right - left
    }

    public static float getTextHeight(float size, String text) {
        if (fontId == -1) return 0;

        MinecraftClient client = MinecraftClient.getInstance();
        Window window = new Window(MinecraftClient.getInstance());

        int framebufferWidth = client.getFramebuffer().viewportWidth;
        int width = window.getWidth();

        float pixelRatio = (float) framebufferWidth / (float) width;
        size *= pixelRatio;

        NanoVG.nvgFontFace(NanoVGManager.getNvgContext(), fontName);
        NanoVG.nvgFontSize(NanoVGManager.getNvgContext(), size);

        float[] bounds = new float[4];
        NanoVG.nvgTextBounds(NanoVGManager.getNvgContext(), 0, 0, text, bounds);

        return bounds[3] - bounds[1]; // bottom - top
    }

    public static float getTextHeight(String text) {
        return getTextHeight(24.0F, text);
    }

    public static float getTextWidth(String text) {
        return getTextWidth(24.0F, text);
    }


    private static ByteBuffer ioResourceToByteBuffer(String resource) throws IOException {
        try (InputStream source = FontRenderer.class.getResourceAsStream(resource)) {
            if (source == null) {
                throw new RuntimeException("source is null");
            }
            byte[] bytes = IOUtils.toByteArray(source);
            ByteBuffer nativeBuffer = MemoryUtil.memAlloc(bytes.length);
            nativeBuffer.put(bytes).flip();
            return nativeBuffer;
        }
    }
}
