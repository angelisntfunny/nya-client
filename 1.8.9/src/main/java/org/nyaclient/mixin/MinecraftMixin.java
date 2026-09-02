package org.nyaclient.mixin;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.nyaclient.NyaClient;
import org.nyaclient.event.impl.EventKey;
import org.nyaclient.gui.HUDPositioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {

    @Inject(method = "initializeGame", at = @At("TAIL"))
    private void initializeGame(CallbackInfo ci) {
        NyaClient.getInstance().onLoad();
    }

    @Inject(method = "handleKeyInput", at = @At("HEAD"))
    private void handleKeyInput(CallbackInfo ci) {
        final int i = (Keyboard.getEventKey() == 0) ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
        if (i != 0 && !Keyboard.isRepeatEvent() && MinecraftClient.getInstance().currentScreen == null && Keyboard.getEventKeyState()) {
            NyaClient.getInstance().getEventBus().call(new EventKey(i));
            NyaClient.getInstance().getModManager().onKey(i);

            if (NyaClient.getInstance().getModsConfigKeybind().isPressed()) {
                MinecraftClient.getInstance().setScreen(new HUDPositioner());
            }
        }
    }
}