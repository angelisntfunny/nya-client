package org.nyaclient.gui.modmenu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.nyaclient.mixin.GameRendererAccessor;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.awt.*;

// TODO: add icons
public class NyaModMenu extends Screen {
    private final int windowWidth = 175;
    private final int windowHeight = 120;

    private enum Icon {
        MODS,
        SETTINGS,
        ACCOUNTS,
        COLORS,
        EXIT;

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
        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        DrawableHelper.fill(0, 0, window.getWidth(), window.getHeight(), new Color(0, 0, 0, 110).getRGB());

        NanoVGManager.render(() -> {
            NanoVGManager.drawRoundedRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight, windowWidth * 2, windowHeight * 2, 5, new Color(23, 23, 23));

            // separator lines
            NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth + 40, (float) window.getHeight() / 2 - windowHeight, 5, windowHeight * 2, new Color(26, 26, 26));
            NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight + 40, windowWidth * 2.0f, 5, new Color(26, 26, 26));

            for (int i = 0; i < 5; i++) {
                NanoVGManager.drawRect((float) window.getWidth() / 2 - windowWidth, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 1), 40, 5, new Color(26, 26, 26));
                FontRenderer.renderCenteredText(6, (float) window.getWidth() / 2 - windowWidth + 20, (float) window.getHeight() / 2 - windowHeight + 40 * (i + 2) - 5, Icon.values()[i].toString(), new Color(255, 255, 255));
            }

            FontRenderer.renderText((float) window.getWidth() / 2 - windowWidth + 50, (float) window.getHeight() / 2 - windowHeight + 30, "Nya");
        });
        super.render(mouseX, mouseY, tickDelta);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {

        if (button == 0) {
            for (int i = 0; i < 5; i++) {
                Icon icon = Icon.values()[i];
                if (mouseX >= window.getWidth() / 2 - windowWidth && mouseX <= window.getWidth() / 2 - windowWidth + 40 &&
                    mouseY >= window.getHeight() / 2 - windowHeight + 40 * (i + 1) + 5 && mouseY <= window.getHeight() / 2 - windowHeight + 40 * (i + 2)) {
                    System.out.println(icon.toString());
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
