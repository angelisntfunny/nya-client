package org.nyaclient.module.utility;

import org.lwjgl.input.Keyboard;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

/**
 * @see org.nyaclient.mixin.GameRendererMixin
 */
public class Fullbright extends AbstractMod {
    public static Fullbright instance;
    public Fullbright() {
        super("Fullbright", "Fullbright", ModCategory.UTILITY, Keyboard.KEY_M);
        instance = this;
    }
}
