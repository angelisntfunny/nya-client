package org.nyaclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.lwjgl.input.Keyboard;
import org.nyaclient.NyaClient;
import org.nyaclient.Settings;
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

        if (!Settings.BACKPORT_E_TAPPING.getValue()) return;

        KeyBinding[] movementKeys = {
                MinecraftClient.getInstance().options.forwardKey,
                MinecraftClient.getInstance().options.backKey,
                MinecraftClient.getInstance().options.leftKey,
                MinecraftClient.getInstance().options.rightKey,
                MinecraftClient.getInstance().options.sprintKey
        };

        for (KeyBinding movementKey : movementKeys) {
            if (movementKey.getCode() <= 0) return;
            if (MinecraftClient.getInstance().currentScreen != null) {
                for (KeyBinding keybind : movementKeys) {
                    ((KeyBindingInterface) keybind).setKeyPressed(false);
                }
                break;
            }
            ((KeyBindingInterface) movementKey).setKeyPressed(Keyboard.isKeyDown(movementKey.getCode()));
        }
    }
}
