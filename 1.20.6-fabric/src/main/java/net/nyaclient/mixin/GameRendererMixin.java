package net.nyaclient.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.nyaclient.NyaClient;
import net.nyaclient.event.impl.EventFOV;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getFov", at = @At("TAIL"), cancellable = true)
    private void getFov(Camera camera, float f, boolean bl, CallbackInfoReturnable<Double> cir) {
        EventFOV event = new EventFOV();
        NyaClient.getInstance().getEventBus().call(event);
        if(event.cancelled()) cir.setReturnValue(event.fov());
    }
}
