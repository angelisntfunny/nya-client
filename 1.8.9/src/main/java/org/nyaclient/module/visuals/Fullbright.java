package org.nyaclient.module.visuals;

import org.lwjgl.input.Keyboard;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

/**
 * @see org.nyaclient.mixin.GameRendererMixin
 */
public class Fullbright extends AbstractMod {
    public static Fullbright instance;

    public Fullbright() {
        super("mods.fullbright", "icons/mods/fullbright.png", ModCategory.VISUALS, Keyboard.KEY_M);
        instance = this;
    }
}
