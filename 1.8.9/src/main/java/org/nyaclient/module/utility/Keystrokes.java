package org.nyaclient.module.utility;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.nyaclient.module.HUDMod;
import org.nyaclient.module.ModCategory;
import org.nyaclient.utils.Colors;
import org.nyaclient.utils.TextUtils;

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
        return 80;
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
        DrawableHelper.fill(x + 20, y, x + 40, y + 20, forward.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 30, y + 10, getKeyName(forward), -1);

        // left
        DrawableHelper.fill(x - 1, y + 22, x + 19, y + 42, left.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 9, y + 32, getKeyName(left), -1);

        // backward
        DrawableHelper.fill(x + 20, y + 22, x + 40, y + 42, backward.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 30, y + 32, getKeyName(backward), -1);

        // right
        DrawableHelper.fill(x + 42, y + 22, x + 62, y + 42, right.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 52, y + 32, getKeyName(right), -1);

        // attack
        DrawableHelper.fill(x - 1, y + 44, x + 29, y + 64, attack.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 15, y + 54, getKeyName(attack), -1);

        // build
        DrawableHelper.fill(x + 31, y + 44, x + 62, y + 64, use.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 47, y + 54, getKeyName(use), -1);

        // jump
        DrawableHelper.fill(x - 1, y + 66, x + 62, y + 84, jump.isPressed() ? Colors.LIGHT_GREY : Colors.DARK_GREY);
        TextUtils.drawCenteredString(x + 30, y + 76, getKeyName(jump), -1);
    }
}
