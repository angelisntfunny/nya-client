package org.nyaclient.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.nyaclient.NyaClient;
import org.nyaclient.module.visuals.VisualTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"))
    public void startFireOverlay(float tickDelta, CallbackInfo ci) {
        if (VisualTweaks.instance.isEnabled() && VisualTweaks.instance.lowFire) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, -0.25, 0);
        }
    }

    @Inject(method = "renderFireOverlay", at = @At("TAIL"))
    public void endFireOverlay(float tickDelta, CallbackInfo ci) {
        if (VisualTweaks.instance.isEnabled() && VisualTweaks.instance.lowFire) {
            // technically if the setting is changed after the head of the method and before the tail this could be bad but oh well
            GlStateManager.popMatrix();
        }
    }
}
