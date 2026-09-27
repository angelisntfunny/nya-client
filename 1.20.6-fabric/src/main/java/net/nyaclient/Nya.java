package net.nyaclient;

import net.fabricmc.api.ClientModInitializer;

public class Nya implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NyaClient.initialize();

        NyaClient.LOGGER.info("Hello from {}!", "fabric");
    }
}
