package org.nyaclient.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import org.nyaclient.module.visuals.Fullbright;
import org.nyaclient.module.utility.ZoomMod;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Redirect(
            method = "updateLightmap",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;gamma:F", opcode = Opcodes.GETFIELD)
    )
    private float modifyGamma(GameOptions instance) {
        if (Fullbright.instance.isEnabled()) {
            return 1000F;
        }
        return instance.gamma;
    }

    @Redirect(
            method = "getFov",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;fov:F", opcode = Opcodes.GETFIELD)
    )
    private float modifyFov(GameOptions instance) {
        if (ZoomMod.instance.isEnabled()) {
            return ZoomMod.instance.getFOV();
        }
        return instance.fov;
    }
}
