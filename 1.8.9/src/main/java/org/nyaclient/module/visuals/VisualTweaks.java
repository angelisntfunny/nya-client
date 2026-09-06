package org.nyaclient.module.visuals;

import org.lwjgl.input.Keyboard;
import org.nyaclient.mixin.HeldItemRendererMixin;
import org.nyaclient.module.AbstractMod;
import org.nyaclient.module.ModCategory;

/**
 * @see HeldItemRendererMixin
 */
public class VisualTweaks extends AbstractMod {
    // the way i did everything in this class is fucked but oh well, im not adding settings until i finish clickgui
    public boolean lowFire = true;

    public static VisualTweaks instance;
    public VisualTweaks() {
        super("Visual Tweaks", "Visual tweaks to make your life better.", ModCategory.VISUALS, Keyboard.KEY_N);
        instance = this;
    }
}
