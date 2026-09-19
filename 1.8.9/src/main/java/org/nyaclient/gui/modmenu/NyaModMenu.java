package org.nyaclient.gui.modmenu;

import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.nyaclient.gui.modmenu.screens.ModsSubview;
import org.nyaclient.gui.modmenu.screens.SettingsSubview;
import org.nyaclient.mixin.GameRendererAccessor;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

// TODO: add icons
public class NyaModMenu extends Screen {
    private final int windowWidth = 175;
    private final int windowHeight = 120;

    private Subview screen;

    private enum Icon {
        MODS("icons/modmenu/component.png"),
        SETTINGS("icons/modmenu/cog.png"),
        //ACCOUNTS("icons/modmenu/accounts.png"), // removed bc spotify made playback api paid. fuck spotify, navidrome better.
        COLORS("icons/modmenu/brush.png"),
        EXIT("icons/modmenu/door.png");

        @SuppressWarnings("FieldCanBeLocal")
        private final ByteBuffer imageBuffer;
        @Getter
        private int handle = 0;

        Icon(String path) {
            try {
                imageBuffer = FontRenderer.ioResourceToByteBuffer("/assets/nyaclient/" + path);
                handle = NanoVG.nvgCreateImageMem(NanoVGManager.getNvgContext(), 0, imageBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return StringUtils.capitalize(super.toString().toLowerCase());
        }
    }

    private Window window;
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public NyaModMenu() {
        // not sure if this is necessary but i don't wanna fuck around and find out
        window = new Window(MinecraftClient.getInstance());
    }

    @Override
    public void init() {
        window = new Window(MinecraftClient.getInstance());
        Identifier blurShader = new Identifier("minecraft", "shaders/post/blur.json");
        ((GameRendererAccessor) mc.gameRenderer).setShader(blurShader);
        if (screen == null) {
            screen = new ModsSubview(0, 0, 0, 0);
        }

        screen.x = (float) window.getWidth() / 2 - windowWidth + 45;
        screen.width = windowWidth * 2.0f - 45;
        screen.y = window.getHeight() / 2.0f - windowHeight + 45;
        screen.height = windowHeight * 2.0f - 45;

        super.init();
    }

    private void drawIcon(Icon icon, int i) {
        int handle = icon.handle;

        if (handle != 0) {
            try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
                NVGPaint img = NVGPaint.malloc(stack);

                float imgWidth = 20;
                float imgHeight = 20;

                NanoVG.nvgImagePattern(NanoVGManager.getNvgContext(), (float) window.getWidth() / 2 - windowWidth + 10, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 2) - 32, imgWidth, imgHeight, 0F, handle, 1F, img);
                NanoVG.nvgBeginPath(NanoVGManager.getNvgContext());
                NanoVG.nvgRect(NanoVGManager.getNvgContext(), (float) window.getWidth() / 2 - windowWidth + 10, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 2) - 32, imgWidth, imgHeight);
                NanoVG.nvgFillPaint(NanoVGManager.getNvgContext(), img);
                NanoVG.nvgFill(NanoVGManager.getNvgContext());
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        if (this.screen != null) screen.handleMouse(mouseX, mouseY);

        DrawableHelper.fill(0, 0, window.getWidth(), window.getHeight(), new Color(0, 0, 0, 110).getRGB());

        NanoVGManager.render(() -> {
            NanoVGManager.drawRoundedRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight, windowWidth * 2, windowHeight * 2, 5, new Color(23, 23, 23));

            // separator lines
            NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth + 40, (float) window.getHeight() / 2 - windowHeight, 5, windowHeight * 2, new Color(26, 26, 26));
            NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight + 40, windowWidth * 2.0f, 5, new Color(26, 26, 26));

            for (int i = 0; i < Icon.values().length; i++) {
                Icon icon = Icon.values()[i];
                NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 1), 40, 5, new Color(26, 26, 26));
                FontRenderer.renderCenteredText(6, (float) window.getWidth() / 2 - windowWidth + 20, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 2) - 5, icon.toString(), new Color(255, 255, 255));

                drawIcon(icon, i);
            }

            FontRenderer.renderText((float) window.getWidth() / 2 - windowWidth + 50, (float) window.getHeight() / 2 - windowHeight + 30, "Nya");

            // i think this might be the WORST way anyone has ever abstracted code
            if (screen != null) {
                screen.draw(mouseX, mouseY, tickDelta);
            }
        });
        super.render(mouseX, mouseY, tickDelta);
    }

    @Override
    public void handleMouse() {
        super.handleMouse();

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {

        screen.onClick(mouseX, mouseY, button);
        if (button == 0) {
            for (int i = 0; i < Icon.values().length; i++) {
                Icon icon = Icon.values()[i];

                float x = (float) window.getWidth() / 2 - windowWidth + 45;
                float width = windowWidth * 2.0f - 45;
                float y = window.getHeight() / 2.0f - windowHeight + 45;
                float height = windowHeight * 2.0f - 45;



                if (mouseX >= window.getWidth() / 2 - windowWidth && mouseX <= window.getWidth() / 2 - windowWidth + 40 &&
                    mouseY >= window.getHeight() / 2 - windowHeight + 40 * (i + 1) + 5 && mouseY <= window.getHeight() / 2 - windowHeight + 40 * (i + 2)) {
                    if (icon == Icon.MODS) {
                        if (!(screen instanceof ModsSubview)) screen = new ModsSubview(x, y, width, height);
                    } else if (icon == Icon.SETTINGS) {
                        if (!(screen instanceof SettingsSubview)) screen = new SettingsSubview(x, y, width, height);
                    } else if (icon == Icon.EXIT) {
                        client.setScreen(null);
                    }

                    break;
                }
            }
        }

        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void removed() {
        if (mc.gameRenderer != null) {
            mc.gameRenderer.disableShader();
        }
        super.removed();
    }

    @Override
    public boolean shouldPauseGame() {
        return false;
    }
}
