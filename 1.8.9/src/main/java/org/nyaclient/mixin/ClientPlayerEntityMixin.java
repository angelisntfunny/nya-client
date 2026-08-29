package org.nyaclient.mixin;

import net.minecraft.entity.player.ClientPlayerEntity;
import org.lwjgl.input.Keyboard;
import org.nyaclient.NyaClient;
import org.nyaclient.event.impl.EventUpdate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void onUpdate(CallbackInfo ci) {
        NyaClient.getInstance().getEventBus().call(new EventUpdate());
    }
}
