package org.nyaclient.module.visuals;

import org.lwjgl.input.Keyboard;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

/**
 * @see org.nyaclient.mixin.TNTEntityRendererMixin
 */
public class TNTTimer extends AbstractMod {
    public TNTTimer() {
        super("TNT Timer", "hi", ModCategory.VISUALS, Keyboard.KEY_B);
    }
}
