/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.screen.impl.modmenu;

import net.nyaclient.NyaClient;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.Services;
import net.nyaclient.screen.ScreenRenderer;
import net.nyaclient.util.IOUtils;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ModMenu extends ScreenRenderer {
    private final int windowWidth = 175;
    private final int windowHeight = 120;

    private Subview screen;

    private enum Icon {
        MODS("icons/component.png"),
        EXIT("icons/door.png");

        @SuppressWarnings("FieldCanBeLocal")
        private final ByteBuffer imageBuffer;
        private final int handle;

        Icon(String path) {
            try {
                imageBuffer = IOUtils.ioResourceToByteBuffer("/assets/nyaclient/" + path);
                handle = NanoVG.nvgCreateImageMem(NekoRenderer.getContext(), 0, imageBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public int getHandle() {
            return handle;
        }

        @Override
        public String toString() {
            return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
        }
    }

    @Override
    public void init(float width, float height) {
        if (screen == null) {
            screen = new ModsSubview(0, 0, 0, 0);
        }

        screen.x = width / 2 - windowWidth + 45;
        screen.width = windowWidth * 2.0f - 45;
        screen.y = height / 2.0f - windowHeight + 45;
        screen.height = windowHeight * 2.0f - 45;
        screen.init(width, height);
    }

    private void drawIcon(Icon icon, int i, float width, float height) {
        int handle = icon.getHandle();

        if (handle == 0) {
            NyaClient.LOGGER.error("Failed to load icon: {}", icon);
            return;
        }

        long vg = NekoRenderer.getContext();

        float imgWidth = 20;
        float imgHeight = 20;

        float imageX = width / 2f - windowWidth + 10;
        float imageY = height / 2f - windowHeight + 40 * (i + 2) - 32;

        try (org.lwjgl.system.MemoryStack stack =
                     org.lwjgl.system.MemoryStack.stackPush()) {

            NVGPaint img = NVGPaint.malloc(stack);

            NanoVG.nvgImagePattern(
                    vg,
                    imageX,
                    imageY,
                    imgWidth,
                    imgHeight,
                    0f,
                    handle,
                    1f,
                    img
            );

            NanoVG.nvgBeginPath(vg);
            NanoVG.nvgRect(vg, imageX, imageY, imgWidth, imgHeight);
            NanoVG.nvgFillPaint(vg, img);
            NanoVG.nvgFill(vg);
        }
    }


    @Override
    public void render(int mouseX, int mouseY, float tickDelta, float width, float height) {
        if (this.screen == null) {
            init(width, height);
        }

        NekoRenderer.drawRect(0, 0, width, height, new Color(0, 0, 0, 110));

        NekoRenderer.drawRoundedRect(width / 2 - windowWidth, height / 2 - windowHeight, windowWidth * 2, windowHeight * 2, 5, new Color(23, 23, 23));

        NekoRenderer.drawRect(width / 2 - windowWidth + 40, height / 2 - windowHeight, 5, windowHeight * 2, new Color(26, 26, 26));
        NekoRenderer.drawRect(width / 2 - windowWidth, height / 2 - windowHeight + 40, windowWidth * 2.0f, 5, new Color(26, 26, 26));

        for (int i = 0; i < Icon.values().length; i++) {
            Icon icon = Icon.values()[i];
            NekoRenderer.drawRect(width / 2 - windowWidth, height / 2 - windowHeight + 40 * (i + 1), 40, 5, new Color(26, 26, 26));
            NekoFontRenderer.renderCenteredText(6, width / 2 - windowWidth + 20, height / 2 - windowHeight + 40 * (i + 2) - 5, icon.toString(), new Color(255, 255, 255));

            drawIcon(icon, i, width, height);
        }

        NekoFontRenderer.renderText(24,width / 2 - windowWidth + 70, height / 2 - windowHeight + 25, "Nya", Color.WHITE);

        if (screen != null) {
            screen.render(mouseX, mouseY, tickDelta, width, height);
        } else {
            throw new IllegalStateException("you've gotten into a logically impossible state, how?");
        }
    }

    @Override
    public boolean mouseClicked(
            double mouseX,
            double mouseY,
            int button,
            float width,
            float height
    ) {
        if (button != 0) {
            return false;
        }

        float sidebarX = width / 2f - windowWidth;
        float sidebarY = height / 2f - windowHeight;

        for (int i = 0; i < Icon.values().length; i++) {
            Icon icon = Icon.values()[i];

            float itemTop = sidebarY + 40 * (i + 1);
            float itemBottom = sidebarY + 40 * (i + 2);

            if (mouseX >= sidebarX &&
                    mouseX <= sidebarX + 40 &&
                    mouseY >= itemTop &&
                    mouseY <= itemBottom) {

                if (icon == Icon.MODS) {
                    screen = new ModsSubview(
                            width / 2f - windowWidth + 45,
                            height / 2f - windowHeight + 45,
                            windowWidth * 2f - 45,
                            windowHeight * 2f - 45
                    );
                } else if (icon == Icon.EXIT) {
                    Services.MINECRAFT_BRIDGE.setScreen(null);
                }

                return true;
            }
        }

        if (screen != null) {
            screen.onClick(mouseX, mouseY, button, width, height);
        }

        return false;
    }
}
