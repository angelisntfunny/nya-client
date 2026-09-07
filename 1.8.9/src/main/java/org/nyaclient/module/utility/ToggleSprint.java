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
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;
import org.nyaclient.utils.TextUtils;

import java.awt.*;

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
        NanoVGManager.drawRoundedRect(x - 2, y - 2, getWidth() + 2, getHeight() + 2, 3, Colors.DARK_GREY);

        FontRenderer.renderText(10, getX() + 2, getY() + getHeight()/2f + 2, "[Sprinting: " + getMode(), Color.WHITE);
    }


}
