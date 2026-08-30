package org.nyaclient.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;

public class TextUtils {
    private static final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void drawCenteredString(int x, int y, String text, int color) {
        mc.textRenderer.drawWithShadow(text, (float) x - (float) mc.textRenderer.getStringWidth(text) / 2, y - mc.textRenderer.fontHeight/2, color);
    }
}
