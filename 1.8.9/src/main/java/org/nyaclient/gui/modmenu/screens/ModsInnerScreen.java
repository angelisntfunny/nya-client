package org.nyaclient.gui.modmenu.screens;

import net.minecraft.client.resource.language.I18n;
import org.lwjgl.input.Mouse;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.nyaclient.NyaClient;
import org.nyaclient.gui.modmenu.InnerScreen;
import org.nyaclient.module.IMod;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.awt.*;


public class ModsInnerScreen extends InnerScreen {
    public ModsInnerScreen(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    private int scrollOffset = 0;
    private final float contentHeight = Math.round((float) NyaClient.getInstance().getModManager().getMods().size() / 3 + 0.5) * 80 + 5;

    private void drawMods() {
        int i = 1;
        float renderX = x + 8;
        float renderY = y + 5;
        float modWidth = (width - 30) / 3;
        for (IMod mod : NyaClient.getInstance().getModManager().getMods()) {
            NanoVGManager.drawRoundedRect(renderX, renderY, modWidth, 75, 5, new Color(26, 26, 26));
            FontRenderer.renderCenteredText(8, renderX + (modWidth/2f), renderY + 55, mod.getName(), new Color(255, 255, 255));

            NanoVGManager.drawRoundedRectSpecifyRadius(renderX, renderY + 65, modWidth, 80 - 65, 0, 0, 5, 5, mod.isEnabled() ? new Color(22, 255, 22, 170) : new Color(255, 22, 22, 110));
            FontRenderer.renderCenteredText(8, renderX + modWidth/2f, renderY + 65 + FontRenderer.getTextHeight(8, "E")/3f, mod.isEnabled() ? I18n.translate("term.enabled") : I18n.translate("term.disabled"), new Color(255, 255, 255));


            int handle = mod.getIconHandle();

            if (handle != 0) {
                try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
                    NVGPaint img = NVGPaint.malloc(stack);

                    float imgWidth = modWidth / 3;
                    float imgHeight = modWidth / 3;

                    float centerX = renderX + ((modWidth / 2) - (imgWidth / 2));
                    float centerY = renderY + ((modWidth / 2) - (imgHeight / 2)) - 20;

                    float angle = 0;
                    float alpha = 1;

                    NanoVG.nvgImagePattern(NanoVGManager.getNvgContext(), centerX, centerY, imgWidth, imgHeight, angle, handle, alpha, img);
                    NanoVG.nvgBeginPath(NanoVGManager.getNvgContext());
                    NanoVG.nvgRect(NanoVGManager.getNvgContext(), centerX, centerY, imgWidth, imgHeight);
                    NanoVG.nvgFillPaint(NanoVGManager.getNvgContext(), img);
                    NanoVG.nvgFill(NanoVGManager.getNvgContext());

                    NanoVG.nvgBeginPath(NanoVGManager.getNvgContext());
                    NanoVG.nvgRect(NanoVGManager.getNvgContext(), centerX, centerY, imgWidth, imgHeight);
                    NanoVG.nvgFill(NanoVGManager.getNvgContext());
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

    @Override
    public void handleMouse() {
        int dWheel = Mouse.getDWheel();

        if (dWheel == 0) {
            return;
        }

        int scrollSpeed = 15;
        if (dWheel > 0) {
            scrollOffset -= scrollSpeed;
        } else {
            scrollOffset += scrollSpeed;
        }

        int maxScroll = Math.max(0, (int) contentHeight - (int) height);
        scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
    }


    @Override
    public void draw(float mouseX, float mouseY) {
        long vg = NanoVGManager.getNvgContext();

        NanoVG.nvgSave(vg);
        NanoVG.nvgScissor(vg, x, y, width, height);
        NanoVG.nvgTranslate(vg, 0, -scrollOffset);

        drawMods();

        NanoVG.nvgRestore(vg);
    }


    @Override
    public void onClick(float mouseX, float mouseY, int button) {

        int i = 1;
        float renderX = x + 8;
        float renderY = y + 5;
        float modWidth = (width - 30) / 3;
        for (IMod mod : NyaClient.getInstance().getModManager().getMods()) {
            if (mouseX >= renderX && mouseX <= renderX + modWidth && mouseY >= renderY && mouseY <= renderY + 75) {
                mod.toggle();
                break;
            }


            renderX += 5 + modWidth;
            if (i % 3 == 0) {
                renderX = x + 8;
                renderY = renderY + 75 + 5;
            }
            i++;
        }
    }
}
