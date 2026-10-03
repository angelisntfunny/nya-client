/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.screen.impl;

import net.nyaclient.NyaClient;
import net.nyaclient.module.IHUDMod;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.Services;
import net.nyaclient.screen.ScreenRenderer;
import net.nyaclient.screen.impl.modmenu.ModMenu;

import java.awt.*;

public class HUDPositionerRenderer extends ScreenRenderer {
    @Override
    public void init(float width, float height) {

    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta, float width, float height) {
        NekoRenderer.drawRect(0, 0, width, height, new Color(0, 0, 0, 110));

        NyaClient.getInstance().getModManager().getHUDMods().forEach(IHUDMod::render);
        NyaClient.getInstance().getModManager().getHUDMods().forEach(m -> m.drag(mouseX, mouseY));

        NekoRenderer.drawRoundedRect(width / 2 - 45, height / 2 - 15, 90, 30, 5, new Color(0, 0, 0, 80));
        NekoFontRenderer.renderCenteredText(15, width / 2.0f, height / 2.0f, "Mods", new Color(255, 255, 255, 255));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button, float width, float height) {
        if (mouseX > (double) width / 2 - 45 && mouseX < (double) width / 2 + 45 &&
                mouseY > (double) height / 2 - 15 && mouseY < (double) height / 2 + 15) {
            Services.MINECRAFT_BRIDGE.setScreen(new ModMenu());
            return true;
        }

        return false;
    }
}
