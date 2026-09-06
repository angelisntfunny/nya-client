package org.nyaclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import org.nyaclient.module.utility.Fullbright;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Redirect(
            method = "updateLightmap",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;gamma:F")
    )
    private float modifyGamma(GameOptions instance) {
        if (Fullbright.instance.isEnabled()) {
            return 1000F;
        }
        return instance.gamma;
    }
}
