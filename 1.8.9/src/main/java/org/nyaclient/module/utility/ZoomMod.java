package org.nyaclient.module.utility;

import org.lwjgl.input.Keyboard;
import org.nyaclient.event.Subscribe;
import org.nyaclient.event.impl.EventUpdate;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

public class ZoomMod extends AbstractMod {
    public static ZoomMod instance;
    public ZoomMod() {
        super("Zoom", "zoom", ModCategory.UTILITY, Keyboard.KEY_C);
        instance = this;
    }

    @Subscribe
    private void onUpdate(EventUpdate event) {
        if (!Keyboard.isKeyDown(key)) {
            this.setEnabled(false);
            mc.options.smoothCameraEnabled = false;
        } else {
            mc.options.smoothCameraEnabled = true;
        }
    }

    public float getFOV() {
        return 30F;
    }
}
