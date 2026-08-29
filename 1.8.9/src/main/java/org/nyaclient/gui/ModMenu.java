package org.nyaclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.nyaclient.mixin.GameRendererAccessor;

import java.awt.*;
import java.io.IOException;

public class ModMenu extends Screen {
    private final MinecraftClient mc;
    private Window window;

    public ModMenu() {
        this.mc = MinecraftClient.getInstance();
    }

    @Override
    public void init() {
        window = new Window(mc);

        Identifier blurShader = new Identifier("minecraft", "shaders/post/blur.json");
        ((GameRendererAccessor) mc.gameRenderer).setShader(blurShader);

        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        DrawableHelper.fill(0, 0, window.getWidth(), window.getHeight(), new Color(0, 0, 0, 110).getRGB());
        DrawableHelper.fill(window.getWidth() / 2 - 45,window.getHeight() / 2 - 15,window.getWidth() / 2 + 45,window.getHeight() / 2 + 15, new Color(32, 32, 32, 255).getRGB());
        super.render(mouseX, mouseY, tickDelta);
    }

    @Override
    public void removed() {
        if (mc.gameRenderer != null) {
            mc.gameRenderer.disableShader();
        }
        super.removed();
    }
}
