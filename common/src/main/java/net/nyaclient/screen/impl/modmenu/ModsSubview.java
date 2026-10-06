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
import net.nyaclient.module.IMod;
import net.nyaclient.module.utility.ZoomMod;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.Services;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

import java.awt.*;

public class ModsSubview extends Subview {
    private float scrollOffset = 0;
    private final float contentHeight = Math.round((float) NyaClient.getInstance().getModManager().getMods().size() / 3 + 0.5) * 80 + 5;

    private double targetScroll = 0.0;

    public ModsSubview(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void init(float width, float height) {

    }

    private void drawMods() {
        int i = 1;
        float renderX = x + 8;
        float renderY = y + 5;
        float modWidth = (width - 30) / 3;
        for (IMod mod : NyaClient.getInstance().getModManager().getMods()) {
            NekoRenderer.drawRoundedRect(renderX, renderY, modWidth, 75, 5, new Color(26, 26, 26));
            NekoFontRenderer.renderCenteredText(8, renderX + (modWidth/2f), renderY + 55, mod.getName(), new Color(255, 255, 255));

            NekoRenderer.drawRoundedRectSpecifyRadius(renderX, renderY + 65, modWidth, 80 - 65, 0, 0, 5, 5, (
                    mod instanceof ZoomMod ? new Color(76, 76, 76) :
                    mod.isEnabled() ? new Color(22, 255, 22, 170) :
                        new Color(255, 22, 22, 110)));
            NekoFontRenderer.renderCenteredText(8, renderX + modWidth/2f, renderY + 65 + NekoFontRenderer.getTextHeight(8, "E")/3f + 5, mod.isEnabled() ? Services.MINECRAFT_BRIDGE.translate("term.enabled") : Services.MINECRAFT_BRIDGE.translate("term.disabled"), new Color(255, 255, 255));

            int handle = mod.getHandle();

            if (handle != 0) {
                try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
                    NVGPaint img = NVGPaint.malloc(stack);

                    float imgWidth = modWidth / 3;
                    float imgHeight = modWidth / 3;

                    float centerX = renderX + ((modWidth / 2) - (imgWidth / 2));
                    float centerY = renderY + ((modWidth / 2) - (imgHeight / 2)) - 20;

                    float angle = 0;
                    float alpha = 1;

                    NanoVG.nvgImagePattern(NekoRenderer.getContext(), centerX, centerY, imgWidth, imgHeight, angle, handle, alpha, img);
                    NanoVG.nvgBeginPath(NekoRenderer.getContext());
                    NanoVG.nvgRect(NekoRenderer.getContext(), centerX, centerY, imgWidth, imgHeight);
                    NanoVG.nvgFillPaint(NekoRenderer.getContext(), img);
                    NanoVG.nvgFill(NekoRenderer.getContext());

                    NanoVG.nvgBeginPath(NekoRenderer.getContext());
                    NanoVG.nvgRect(NekoRenderer.getContext(), centerX, centerY, imgWidth, imgHeight);
                    NanoVG.nvgFill(NekoRenderer.getContext());
                }
            } else {
                System.out.println("uh oh");
            }

            renderX += 5 + modWidth;
            if (i % 3 == 0) {
                renderX = x + 8;
                renderY = renderY + 75 + 5;
            }
            i++;
        }
    }

    public void handleMouse(float mouseX, float mouseY) {
        if (!(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height)) return;
        double dWheel = Services.LWJGL_BRIDGE.getdWheel();

        if (dWheel == 0) {
            return;
        }

        double scrollSpeed = 15;
        if (dWheel > 0) {
            targetScroll -= scrollSpeed;
        } else {
            targetScroll += scrollSpeed;
        }

        int maxScroll = Math.max(0, (int) contentHeight - (int) height);
        targetScroll = Math.clamp(targetScroll, 0, maxScroll);
    }

    @Override
    public void render(float mouseX, float mouseY, float deltaTime, float width, float height) {
        long vg = NekoRenderer.getContext();

        double lerpFactor = 1.0;
        double factor = 1.0 - Math.pow(1.0 - lerpFactor, deltaTime * 60);

        scrollOffset = (float) (scrollOffset + (targetScroll - scrollOffset) * factor);

        NanoVG.nvgSave(vg);
        NanoVG.nvgScissor(vg, x, y, width, height);
        NanoVG.nvgTranslate(vg, 0, -scrollOffset);

        drawMods();

        NanoVG.nvgRestore(vg);

        handleMouse(mouseX, mouseY);
    }


    public void onClick(double mouseX, double mouseY, int button, float width, float height) {
        if (mouseX < x || mouseX > x + this.width ||
                mouseY < y || mouseY > y + this.height) {
            return;
        }

        int i = 0;

        float renderX = x + 8;
        float renderY = y + 5;

        float modWidth = (this.width - 30) / 3f;
        float modHeight = 80f;

        float contentMouseY = (float) mouseY + scrollOffset;

        for (IMod mod : NyaClient.getInstance().getModManager().getMods()) {
            boolean insideToggle =
                    mouseX >= renderX &&
                            mouseX <= renderX + modWidth &&
                            contentMouseY >= renderY + 65 &&
                            contentMouseY <= renderY + modHeight;

            boolean insideSettings =
                    mouseX >= renderX &&
                            mouseX <= renderX + modWidth &&
                            contentMouseY >= renderY &&
                            contentMouseY <= renderY + 65;

            if (insideToggle && !(mod instanceof ZoomMod)) {
                mod.toggle();
                return;
            }

            if (insideSettings) {
                ModMenu.setSubview(new ModSettingsSubview(x, y, width, height, mod));
            }

            i++;

            renderX += modWidth + 5;

            if (i % 3 == 0) {
                renderX = x + 8;
                renderY += modHeight + 5;
            }
        }
    }


}
