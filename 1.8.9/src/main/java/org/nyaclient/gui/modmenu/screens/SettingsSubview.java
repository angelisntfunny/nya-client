package org.nyaclient.gui.modmenu.screens;

import org.nyaclient.Settings;
import org.nyaclient.gui.modmenu.Subview;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.awt.*;

public class SettingsSubview extends Subview {
    public SettingsSubview(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void handleMouse(float mouseX, float mouseY) {

    }

    @Override
    public void draw(float mouseX, float mouseY, float deltaTime) {
        long vg = NanoVGManager.getNvgContext();

        float renderY = y + 5;
        for (Settings.Setting<?> setting : Settings.SETTINGS) {
            if (setting.getType() == Boolean.class) {
                final float renderX = x + 5;

                NanoVGManager.drawRoundedRect(renderX, renderY, 150, 20, 5, new Color(26, 26, 26));
                float textY = FontRenderer.getStandardXYFromCenter(8, 0, renderY + 10, setting.getName())[1];

                FontRenderer.renderText(8, x + 10, textY, setting.getName(), new Color(255, 255, 255));

                NanoVGManager.drawRoundedRect(renderX + 135, renderY + 5, 10, 10, 1, (boolean) setting.getValue() ? new Color(22, 255, 22, 170) : new Color(255, 22, 22, 110));

                renderY += 25;
            }
        }
    }

    @Override
    public void onClick(float mouseX, float mouseY, int button) {
        float renderY = y + 5;
        for (Settings.Setting<?> setting : Settings.SETTINGS) {
            if (setting instanceof Settings.BooleanSetting) {
                final float renderX = x + 5;

                NanoVGManager.drawRoundedRect(renderX, renderY, 150, 20, 5, new Color(26, 26, 26));
                if (mouseX >= renderX && mouseX <= renderX + 150 && mouseY >= renderY && mouseY <= renderY + 20) {
                    ((Settings.BooleanSetting) setting).setValue(!(boolean) setting.getValue());
                }

                renderY += 25;
            }
        }
    }
}
