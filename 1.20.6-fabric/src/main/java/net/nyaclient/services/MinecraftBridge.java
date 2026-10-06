/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.services;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.services.IMinecraftBridge;
import net.nyaclient.screen.ScreenRenderer;

public class MinecraftBridge implements IMinecraftBridge {
    @Override
    public boolean inGame() {
        return Minecraft.getInstance().player != null && Minecraft.getInstance().level != null;
    }

    @Override
    public float getWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    @Override
    public float getHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    public void setScreen(ScreenRenderer renderer) {
        if (renderer == null) {
            Minecraft.getInstance().setScreen(null);
            return;
        }

        Minecraft.getInstance().setScreen(new Screen(Component.empty()) {
            @Override
            protected void init() {
                super.init();
                renderer.init(width, height);
            }

            @Override
            public void render(GuiGraphics guiGraphics, int i, int j, float f) {
                super.render(guiGraphics, i, j, f);
                NekoRenderer.render(() -> renderer.render(i, j, f, Minecraft.getInstance().getWindow().getGuiScaledWidth(), Minecraft.getInstance().getWindow().getGuiScaledHeight()));
            }

            @Override
            public boolean mouseClicked(double d, double e, int i) {
                return renderer.mouseClicked(d, e, i, width, height);
            }

            @Override
            public void removed() {
                super.removed();
                renderer.close();
            }
        });
    }

    @Override
    public String translate(String key) {
        return I18n.get(key);
    }

    @Override
    public void setCinematicCamera(boolean state) {
        Minecraft.getInstance().options.smoothCamera = state;
    }

    @Override
    public int getFov() {
        return Minecraft.getInstance().options.fov().get();
    }
}
