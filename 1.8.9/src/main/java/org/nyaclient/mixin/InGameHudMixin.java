package org.nyaclient.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.nyaclient.NyaClient;
import org.nyaclient.module.HUDMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void renderTail(float tickDelta, CallbackInfo ci) {
        NyaClient.getInstance().getModManager().getHUDMods().forEach(HUDMod::render);
    }
}
