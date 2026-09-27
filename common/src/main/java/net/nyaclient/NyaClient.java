package net.nyaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NyaClient {
    private static NyaClient INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("nya~!");

    public static void initialize() {
        // ugly ass code
        INSTANCE = new NyaClient();
        INSTANCE.init();
    }

    public void init() {
        LOGGER.info("Hello from {}!", "common");
    }

    public static NyaClient getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("NyaClient.getInstance() called before INSTANCE was set.");
        }
        return INSTANCE;
    }
}
