package org.nyaclient;

import lombok.Getter;
import lombok.SneakyThrows;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.nyaclient.event.EventBus;
import org.nyaclient.module.ModManager;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

public class NyaClient {
    NyaClient() {}

    private static NyaClient INSTANCE;

    @Getter private EventBus eventBus;
    @Getter private ModManager modManager;
    @Getter private KeyBinding modsConfigKeybind;

    @SneakyThrows
    public void onLoad() {
        NanoVGManager.init();
        FontRenderer.initFont(NanoVGManager.getNvgContext(), "/assets/nyaclient/Inter.ttf");
    }

    public static NyaClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NyaClient();
        }

        return INSTANCE;
    }

    public void start() {
        eventBus = new EventBus();
        modManager = new ModManager();
        modsConfigKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nyaclient.modsconfigkeybind",
                Keyboard.KEY_RSHIFT,
                "category.nyaclient"
        ));
    }
}
