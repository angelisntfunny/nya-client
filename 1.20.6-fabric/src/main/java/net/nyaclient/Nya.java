/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.nyaclient.event.impl.EventRender;
import net.nyaclient.event.impl.EventUpdate;
import net.nyaclient.module.IHUDMod;
import net.nyaclient.neko.NekoRenderer;

public class Nya implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        try {
            NyaClient.initialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        NyaClient.LOGGER.info("[client] initialized successfully!");

        if (Math.random() < 0.1) { // 10% chance
            NyaClient.LOGGER.info("[client] you just lost the game lmao");
        }

        ClientTickEvents.END_CLIENT_TICK.register(client -> NyaClient.getInstance().getEventBus().call(new EventUpdate()));

        HudRenderCallback.EVENT.register((graphics, delta) -> {
            NekoRenderer.render(() -> NyaClient.getInstance().getModManager().getHUDMods().forEach(IHUDMod::render));
            NyaClient.getInstance().getEventBus().call(new EventRender(delta/*rune*/, Minecraft.getInstance().getWindow().getGuiScaledWidth(), Minecraft.getInstance().getWindow().getGuiScaledHeight()));
        });

    }
}
