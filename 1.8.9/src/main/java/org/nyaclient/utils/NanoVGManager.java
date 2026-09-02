package org.nyaclient.utils;

import lombok.Getter;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GLContext;

public class NanoVGManager {
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

}
