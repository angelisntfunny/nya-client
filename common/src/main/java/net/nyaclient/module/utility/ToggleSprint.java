package net.nyaclient.module.utility;

import net.nyaclient.enums.Key;
import net.nyaclient.event.Subscribe;
import net.nyaclient.event.impl.EventKey;
import net.nyaclient.module.BasicHUDMod;
import net.nyaclient.module.ModCategory;
import net.nyaclient.platform.Services;

public class ToggleSprint extends BasicHUDMod {
    public ToggleSprint() {
        super("mods.togglesprint", "icons/togglesprint.png", ModCategory.Utility, 200, 100);
        this.key = null;
    }

    @Subscribe
    private void onKey(EventKey event) {
        if (event.key() == Key.KEY_LEFT_CONTROL && Services.MINECRAFT_BRIDGE.inGame())
            Services.MINECRAFT_BRIDGE.toggleSprint();
    }

    @Override
    protected String getString() {
        if (!Services.MINECRAFT_BRIDGE.isSprintToggled()) {
            return "[Toggle Sprint: Off]";
        }

        if (Services.MINECRAFT_BRIDGE.isSprinting()) {
            return "[Toggle Sprint: Sprinting]";
        }

        return "[Toggle Sprint: Not Sprinting]";
    }
}
