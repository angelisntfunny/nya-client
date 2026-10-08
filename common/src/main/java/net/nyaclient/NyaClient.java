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

import net.nyaclient.event.EventBus;
import net.nyaclient.module.IMod;
import net.nyaclient.module.ModManager;
import net.nyaclient.neko.NekoRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

public class NyaClient {
    private static NyaClient INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("nya~!");

//    public static final Gson GSON = new GsonBuilder()
//            .setPrettyPrinting()
//            .registerTypeAdapterFactory(
//                    RuntimeTypeAdapterFactory.of(Value.class, "type")
//                            .registerSubtype(BooleanValue.class, "boolean")
//            )
//            .excludeFieldsWithoutExposeAnnotation()
//            .create();

    private EventBus EVENT_BUS;
    private ModManager MOD_MANAGER;

    private boolean shutdown = false;

    public static void initialize() throws Exception {
        INSTANCE = new NyaClient();
        INSTANCE.init();
    }

    public void init() throws Exception {
        EVENT_BUS = new EventBus();
        MOD_MANAGER = new ModManager();

        LOGGER.info("[core] initialized successfully");
    }

    public static void initRendering() throws IOException {
        NekoRenderer.init();
    }

    public void shutdown() {
        if (shutdown)
            return;

        shutdown = true;
        NyaClient.getInstance().getModManager().getMods().forEach(IMod::cleanup);
        LOGGER.info("[core] finished shutdown");
    }

    public static NyaClient getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("NyaClient.getInstance() called before INSTANCE was set.");
        }
        return INSTANCE;
    }

    public EventBus getEventBus() {
        return EVENT_BUS;
    }

    public ModManager getModManager() {
        return MOD_MANAGER;
    }
}
