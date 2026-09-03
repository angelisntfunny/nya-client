package org.nyaclient.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.apache.commons.io.IOUtils;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.system.MemoryUtil;

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

    public static void renderText(float size, float x, float y, String text) {
        if (fontId == -1) return;

        Window window = new Window(MinecraftClient.getInstance());

        int framebufferWidth = MinecraftClient.getInstance().getFramebuffer().viewportWidth;
        int width = window.getWidth();
        int height = window.getHeight();

        float pixelRatio = (float) framebufferWidth / (float) width;
        GlStateManager.pushMatrix();
        NanoVG.nvgBeginFrame(NanoVGManager.getNvgContext(), width, height, pixelRatio);

        NanoVG.nvgFontFace(NanoVGManager.getNvgContext(), fontName);
        NanoVG.nvgFontSize(NanoVGManager.getNvgContext(), size);
        NanoVG.nvgText(NanoVGManager.getNvgContext(), x, y, text);

        NanoVG.nvgEndFrame(NanoVGManager.getNvgContext());
        GlStateManager.popMatrix();
    }

    public static void renderText(float x, float y, String text) {
        renderText(24.0F, x, y, text);
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
