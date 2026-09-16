package org.nyaclient.module.visuals;

import org.lwjgl.input.Keyboard;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

/**
 * @see org.nyaclient.mixin.TNTEntityRendererMixin
 */
public class TNTTimer extends AbstractMod {
    public static TNTTimer instance;
    public TNTTimer() {
        super("mods.tnttimer", "icons/mods/tnttimer.png", ModCategory.VISUALS, Keyboard.KEY_B);
        instance = this;
    }
}
