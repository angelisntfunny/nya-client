/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.platform;

import net.nyaclient.NyaClient;
import net.nyaclient.platform.services.ILWJGLBridge;
import net.nyaclient.platform.services.IMinecraftBridge;
import net.nyaclient.platform.services.INanoVGBridge;

import java.util.ServiceLoader;

public class Services {
    public static final INanoVGBridge NANOVG_BRIDGE;
    public static final ILWJGLBridge LWJGL_BRIDGE;
    public static final IMinecraftBridge MINECRAFT_BRIDGE;

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        NyaClient.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
    
    static {
        NANOVG_BRIDGE = load(INanoVGBridge.class);
        LWJGL_BRIDGE = load(ILWJGLBridge.class);
        MINECRAFT_BRIDGE = load(IMinecraftBridge.class);
    }
}
