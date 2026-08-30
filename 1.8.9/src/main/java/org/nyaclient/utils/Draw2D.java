package org.nyaclient.utils;

import net.minecraft.client.gui.DrawableHelper;

public class Draw2D {
    public static void drawOutline(int left, int top, int right, int bottom, int thickness, int color) {
        DrawableHelper.fill(left, top, right, top + thickness, color);
        DrawableHelper.fill(left, bottom - thickness, right, bottom, color);
        DrawableHelper.fill(left, top + thickness, left + thickness, bottom - thickness, color);
        DrawableHelper.fill(right - thickness, top + thickness, right, bottom - thickness, color);
    }
}
