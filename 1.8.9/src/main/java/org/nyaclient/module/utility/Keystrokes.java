package org.nyaclient.module.utility;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.nyaclient.module.HUDMod;
import org.nyaclient.module.ModCategory;
import org.nyaclient.utils.Colors;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;
import org.nyaclient.utils.TextUtils;

import java.awt.*;

public class Keystrokes extends HUDMod {
    public Keystrokes() {
        super("Keystrokes", "Visualizes your keystrokes.", ModCategory.UTILITY, Keyboard.KEY_0, 10, 10);
    }

    private String getKeyName(KeyBinding keyBinding) {
        if (keyBinding.getCode() >= 0 && keyBinding.getCode() <= 255) {
            return Keyboard.getKeyName(keyBinding.getCode());
        }

        if (keyBinding.getCode() == -100) {
            return "LMB";
        } else if (keyBinding.getCode() == -98) {
            return "MMB";
        } else {
            return "RMB";
        }
    }

    @Override
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return 83;
    }

    @Override
    public void render() {
        KeyBinding forward = mc.options.forwardKey;
        KeyBinding backward = mc.options.backKey;
        KeyBinding left = mc.options.leftKey;
        KeyBinding right = mc.options.rightKey;
        KeyBinding attack = mc.options.attackKey;
        KeyBinding use = mc.options.useKey;
        KeyBinding jump = mc.options.jumpKey;

        // forward
        NanoVGManager.drawRoundedRect(x + 20, y, 20, 20, 2.5f, forward.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 30, y + 10, getKeyName(forward), Color.WHITE);

        // left
        NanoVGManager.drawRoundedRect(x - 1, y + 22, 20, 20, 2.5f, left.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 9, y + 32, getKeyName(left), Color.WHITE);

        // backward
        NanoVGManager.drawRoundedRect(x + 20, y + 22, 20, 20, 2.5f, backward.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 30, y + 32, getKeyName(backward), Color.WHITE);

        // right
        NanoVGManager.drawRoundedRect(x + 42, y + 22, 20, 20, 2.5f, right.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 52, y + 32, getKeyName(right), Color.WHITE);

        // attack
        NanoVGManager.drawRoundedRect(x - 1, y + 44, 30, 20, 2.5f, attack.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 15, y + 54, getKeyName(attack), Color.WHITE);

        // build
        NanoVGManager.drawRoundedRect(x + 31, y + 44, 31, 20, 2.5f, use.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 47, y + 54, getKeyName(use), Color.WHITE);

        // jump
        NanoVGManager.drawRoundedRect(x - 1, y + 66, 63, 18, 2.5f, jump.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        FontRenderer.renderCenteredText(10, x + 30, y + 76, getKeyName(jump), Color.WHITE);
    }
}
