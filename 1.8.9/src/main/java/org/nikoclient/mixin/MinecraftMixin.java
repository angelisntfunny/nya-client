package org.nikoclient.mixin;

import net.minecraft.client.MinecraftClient;
import org.nikoclient.utils.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
    @Inject(method = "initializeGame", at = @At("TAIL"))
    private void initializeGame(CallbackInfo ci) {
        Logger.info("Game started");
    }
}
