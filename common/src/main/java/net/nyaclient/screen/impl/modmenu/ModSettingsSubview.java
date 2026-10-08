package net.nyaclient.screen.impl.modmenu;

import net.nyaclient.enums.Key;
import net.nyaclient.module.IMod;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.option.BooleanValue;
import net.nyaclient.option.KeyValue;
import net.nyaclient.option.Value;

import java.awt.*;

public class ModSettingsSubview extends Subview {
    private final IMod mod;

    private boolean listeningForKey = false;
    private KeyValue listeningForKeyOption = null;

    public ModSettingsSubview(float x, float y, float width, float height, IMod mod) {
        super(x, y, width, height);
        this.mod = mod;
    }

    @Override
    public void init(float width, float height) {

    }

    @Override
    public void render(float mouseX, float mouseY, float deltaTime, float width, float height) {
        float renderY = y + 5;
        for (Value<?> value : mod.getOptions()) {
            float renderX = x + 5;
            float centerY = renderY + 10;

            if (value instanceof BooleanValue) {
                NekoRenderer.drawRoundedRect(renderX, renderY, 150, 20, 5, new Color(26, 26, 26));
                NekoFontRenderer.renderText(8, renderX + NekoFontRenderer.getTextWidth(8, value.name())/2f + 5, centerY, value.name(), Color.WHITE);
                NekoRenderer.drawRoundedRect(renderX + 135, renderY + 5, 10, 10, 1, (boolean) value.get() ? new Color(38, 255, 22, 170) : new Color(255, 22, 22, 110));
            }

            if (value instanceof KeyValue) {
                String keyName = ((KeyValue) value).get().getName();
                NekoRenderer.drawRoundedRect(renderX, renderY, 150, 20, 5, new Color(26, 26, 26));
                NekoFontRenderer.renderCenteredText(8, renderX + NekoFontRenderer.getTextWidth(8, value.name())/2f + 5, centerY, value.name(), Color.WHITE);
                NekoFontRenderer.renderCenteredText(8, renderX + 145 - NekoFontRenderer.getTextWidth(8, keyName)/2f, centerY, keyName, Color.WHITE);
            }

            renderY += 25;
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button, float width, float height) {
        float renderY = y + 5;
        for (Value<?> value : mod.getOptions()) {
            if (value instanceof BooleanValue) {
                final float renderX = x + 5;

                if (mouseX >= renderX && mouseX <= renderX + 150 &&
                    mouseY >= renderY && mouseY <= renderY + 20) {
                    ((BooleanValue)value).set(!(boolean) value.get());
                }
            } else if (value instanceof KeyValue) {
                final float renderX = x + 5;

                if (mouseX >= renderX && mouseX <= renderX + 150 &&
                    mouseY >= renderY && mouseY <= renderY + 20) {
                    listeningForKey = true;
                    listeningForKeyOption = (KeyValue) value;
                }
            }

            renderY += 25;
        }
    }

    @Override
    public void onKey(Key key) {
        super.onKey(key);

        if (listeningForKey) {
            listeningForKey = false;

            // probably should've refactored instead of coding like this but it works so its fine
            if (listeningForKeyOption != null) {
                listeningForKeyOption.set(key);
                mod.setKey(key);
            }
        }
    }
}
