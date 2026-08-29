package org.nyaclient.module.utility;

import org.lwjgl.input.Keyboard;
import org.nyaclient.event.Subscribe;
import org.nyaclient.event.impl.EventUpdate;
import org.nyaclient.mixin.KeyBindingInterface;
import org.nyaclient.module.Mod;
import org.nyaclient.module.ModCategory;

// TODO: make this actually togglesprint
public class ToggleSprint extends Mod {
    public ToggleSprint() {
        super("Toggle Sprint", "make you sprint", ModCategory.UTILITY, Keyboard.KEY_N);
    }

    @Subscribe
    public void onUpdate(EventUpdate event) {
//        mc.player.setSprinting(true);
        ((KeyBindingInterface) mc.options.sprintKey).setKeyPressed(true);
    }
}
