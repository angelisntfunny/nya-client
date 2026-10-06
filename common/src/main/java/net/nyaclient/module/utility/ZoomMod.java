package net.nyaclient.module.utility;

import net.nyaclient.event.Subscribe;
import net.nyaclient.event.impl.EventFOV;
import net.nyaclient.event.impl.EventUpdate;
import net.nyaclient.keyboard.Key;
import net.nyaclient.module.AbstractMod;
import net.nyaclient.module.ModCategory;
import net.nyaclient.option.BooleanValue;
import net.nyaclient.platform.Services;

import java.util.List;

public class ZoomMod extends AbstractMod {
    private final BooleanValue SET_SMOOTH_CAMERA = new BooleanValue("Set Smooth Camera", true);

    private final float TARGET_ZOOM_FOV = 30.0f;
    private float currentFov;

    public ZoomMod() {
        super("mods.zoom", "icons/zoom.png", ModCategory.Utility);
        this.key = Key.KEY_C;
        this.values = List.of(SET_SMOOTH_CAMERA);
    }

    @Override
    public void onEnable() {
        this.currentFov = Services.MINECRAFT_BRIDGE.getFov();
        super.onEnable();
        if (SET_SMOOTH_CAMERA.get())
            Services.MINECRAFT_BRIDGE.setCinematicCamera(true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (SET_SMOOTH_CAMERA.get())
            Services.MINECRAFT_BRIDGE.setCinematicCamera(false);
    }

    @Subscribe
    private void onUpdate(EventUpdate event) {
        if (!Services.LWJGL_BRIDGE.isKeyDown(this.key)) this.setEnabled(false);
    }

    @Subscribe
    private void onFOV(EventFOV event) {
        event.setCancelled(true);
        currentFov = currentFov + (TARGET_ZOOM_FOV - currentFov) * 0.05f;
        event.setFov(currentFov);
    }
}
