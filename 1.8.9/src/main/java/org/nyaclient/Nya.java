package org.nyaclient;

import net.fabricmc.api.ModInitializer;

public class Nya implements ModInitializer {
	@Override
	public void onInitialize() {
		NyaClient.getInstance().start();
	}
}
