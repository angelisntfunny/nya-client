package net.nyaclient.screen.impl.modmenu;

import net.nyaclient.module.IMod;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.option.BooleanValue;
import net.nyaclient.option.Value;

import java.awt.*;

public class ModSettingsSubview extends Subview {
    private final IMod mod;

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
            if (value instanceof BooleanValue) {
                final float renderX = x + 5;

                NekoRenderer.drawRoundedRect(renderX, renderY, 150, 20, 5, new Color(26, 26, 26));
                float textY = NekoFontRenderer.getTextHeight(8)/2f;
                NekoFontRenderer.renderText(8, x + 45, textY + y + 11, value.name(), Color.WHITE);

                NekoRenderer.drawRoundedRect(renderX + 135, renderY + 5, 10, 10, 1, (boolean) value.get() ? new Color(38, 255, 22, 170) : new Color(255, 22, 22, 110));
            }
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
                    System.out.println("hi");
                    ((BooleanValue)value).set(!(boolean) value.get());
                }
            }
        }
    }
}
