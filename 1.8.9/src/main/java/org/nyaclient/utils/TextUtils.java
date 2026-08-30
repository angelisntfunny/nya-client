package org.nyaclient.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;

/**
 * To make the text system easily changeable for whenever I add custom fonts.
 */
public class TextUtils {
    private static final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void drawCenteredString(int x, int y, String text, int color) {
        mc.textRenderer.drawWithShadow(text, (float) x - (float) getStringWidth(text) / 2, y - (float) getFontHeight() / 2, color);
    }

    public static void drawString(int x, int y, String text, int color) {
        mc.textRenderer.drawWithShadow(text, x, y, color);
    }

    public static int getFontHeight() {
        return mc.textRenderer.fontHeight;
    }
    public static int getStringWidth(String text) {
        return mc.textRenderer.getStringWidth(text);
    }
}
