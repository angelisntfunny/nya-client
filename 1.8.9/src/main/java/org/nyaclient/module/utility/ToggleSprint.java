package org.nyaclient.module.utility;

import net.minecraft.client.gui.DrawableHelper;
import org.lwjgl.input.Keyboard;
import org.nyaclient.event.Subscribe;
import org.nyaclient.event.impl.EventKey;
import org.nyaclient.event.impl.EventUpdate;
import org.nyaclient.mixin.KeyBindingInterface;
import org.nyaclient.module.HUDMod;
import org.nyaclient.module.ModCategory;
import org.nyaclient.utils.Colors;
import org.nyaclient.utils.TextUtils;

public class ToggleSprint extends HUDMod {
    private boolean toggleSprint = false;


    @Override
    public void onDisable() {
        ((KeyBindingInterface) mc.options.sprintKey).setKeyPressed(Keyboard.isKeyDown(mc.options.sprintKey.getCode()));
        super.onDisable();
    }

    private String getMode() {
        String sprintingState = "Vanilla]";

        if (toggleSprint && !Keyboard.isKeyDown(mc.options.sprintKey.getCode())) {
            sprintingState = "Toggled]";
        } else if (!toggleSprint && !Keyboard.isKeyDown(mc.options.sprintKey.getCode()) && !mc.player.isSprinting()) {
            sprintingState = "Not Sprinting]";
        }

        return sprintingState;
    }

    public ToggleSprint() {
        super("Toggle Sprint", "Makes you sprint", ModCategory.UTILITY, Keyboard.KEY_N, 100, 100);
    }

    @Subscribe
    public void onKeyPress(EventKey event) {
        if (mc.options.sprintKey.getCode() == event.getKey()) {
            toggleSprint = !toggleSprint;

            if (!toggleSprint) {
                ((KeyBindingInterface) mc.options.sprintKey).setKeyPressed(Keyboard.isKeyDown(mc.options.sprintKey.getCode()));
            }
        }
    }

    @Subscribe
    public void onUpdate(EventUpdate event) {
        if (toggleSprint) {
            ((KeyBindingInterface) mc.options.sprintKey).setKeyPressed(true);
        }
    }

    @Override
    public int getWidth() {
        return TextUtils.getStringWidth("[Sprinting: " + getMode()) + 5;
    }

    @Override
    public int getHeight() {
        return TextUtils.getFontHeight() + 4;
    }



    @Override
    public void render() {
        DrawableHelper.fill(x - 2, y - 2, x + getWidth() + 2, y + getHeight() + 2, Colors.DARK_GREY);

        TextUtils.drawString(getX() + 2, getY() + 2, "[Sprinting: " + getMode(), -1);
    }


}
