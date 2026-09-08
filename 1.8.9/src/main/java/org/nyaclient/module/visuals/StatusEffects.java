package org.nyaclient.module.visuals;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import org.lwjgl.input.Keyboard;
import org.nyaclient.gui.HUDPositioner;
import org.nyaclient.module.HUDMod;
import org.nyaclient.module.ModCategory;
import org.nyaclient.utils.FontRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class StatusEffects extends HUDMod {
    private final DrawableHelper drawableHelper;
    protected static final Identifier INVENTORY_TEXTURE = new Identifier("textures/gui/container/inventory.png");

    public StatusEffects() {
        super("Status Effects", "hi", ModCategory.VISUALS, Keyboard.KEY_0, 150, 150);
        this.drawableHelper = new DrawableHelper();
    }

    private void drawEffects() {
        int i = this.x;
        int j = this.y;
        int k = 166;
        Collection<StatusEffectInstance> collection = MinecraftClient.getInstance().player.getStatusEffectInstances();

        if (collection.isEmpty() && mc.currentScreen instanceof HUDPositioner) {
            collection = new ArrayList<>();
            collection.add(new StatusEffectInstance(5, 1200, 1, false, true));
        }

        if (!collection.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;
            if (collection.size() > 5) {
                l = 132 / (collection.size() - 1);
            }

            for(StatusEffectInstance statusEffectInstance : collection) {
                StatusEffect statusEffect = StatusEffect.STATUS_EFFECTS[statusEffectInstance.getEffectId()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                MinecraftClient.getInstance().getTextureManager().bindTexture(INVENTORY_TEXTURE);

                if (statusEffect.hasIcon()) {
                    int m = statusEffect.getIconLevel();
                    drawableHelper.drawTexture(i + 6, j + 7, m % 8 * 18, 198 + m / 8 * 18, 18, 18);
                }

                String string = I18n.translate(statusEffect.getTranslationKey(), new Object[0]);
                if (statusEffectInstance.getAmplifier() == 1) {
                    string = string + " " + I18n.translate("enchantment.level.2", new Object[0]);
                } else if (statusEffectInstance.getAmplifier() == 2) {
                    string = string + " " + I18n.translate("enchantment.level.3", new Object[0]);
                } else if (statusEffectInstance.getAmplifier() == 3) {
                    string = string + " " + I18n.translate("enchantment.level.4", new Object[0]);
                }

                MinecraftClient.getInstance().textRenderer.draw(string, i + 10 + 18, j + 6, 16777215);
                String string2 = StatusEffect.getFormattedDuration(statusEffectInstance);
                MinecraftClient.getInstance().textRenderer.draw(string2, i + 10 + 18, j + 6 + 10, 8355711);
                j += l;
            }
        }
    }

    @Override
    public int getHeight() {
        int j = 0;
        Collection<StatusEffectInstance> collection = MinecraftClient.getInstance().player.getStatusEffectInstances();

        if (collection.isEmpty() && mc.currentScreen instanceof HUDPositioner) {
            collection = new ArrayList<>();
            collection.add(new StatusEffectInstance(5, 60, 1, false, true));
        }

        if (!collection.isEmpty()) {
            int l = 33;
            if (collection.size() > 5) {
                l = 132 / (collection.size() - 1);
            }

            j += l * collection.size();

        }
        return j;
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public void render() {
        drawEffects();
    }
}
