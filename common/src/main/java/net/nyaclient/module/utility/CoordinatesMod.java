package net.nyaclient.module.utility;

import net.nyaclient.enums.Biome;
import net.nyaclient.module.HUDMod;
import net.nyaclient.module.ModCategory;
import net.nyaclient.neko.NekoFontRenderer;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.platform.Services;

import java.awt.*;

public class CoordinatesMod extends HUDMod {
    public CoordinatesMod() {
        super("mods.coordinates", "icons/coordinates.png", ModCategory.Utility, 250, 250);
    }

    @Override
    public float getWidth() {
        return 150;
    }

    @Override
    public float getHeight() {
        return NekoFontRenderer.getTextHeight(8) * 4 + 10;
    }

    @Override
    public void render() {
        NekoRenderer.drawRoundedRect(x, y, getWidth(), getHeight(), 3, new Color(0, 0, 0, 110));
        NekoRenderer.drawOutlineRounded(x, y, getWidth(), getHeight(), 1, 3, new Color(255, 255, 255, 40));

        if (Services.PLAYER_BRIDGE.getX().isEmpty() || Services.PLAYER_BRIDGE.getY().isEmpty() || Services.PLAYER_BRIDGE.getZ().isEmpty() || Services.PLAYER_BRIDGE.getBiome().isEmpty()) return;

        double playerX = Services.PLAYER_BRIDGE.getX().get();
        double playerY = Services.PLAYER_BRIDGE.getY().get();
        double playerZ = Services.PLAYER_BRIDGE.getZ().get();
        Biome biome = Services.PLAYER_BRIDGE.getBiome().get();

        NekoFontRenderer.renderText(8, x + 5, y + 5, "X: " + String.format("%.2f", playerX), Color.WHITE);
        NekoFontRenderer.renderText(8, x + 5, y + NekoFontRenderer.getTextHeight(8)*2 - 2.5f, "Y: " + String.format("%.2f", playerY), Color.WHITE);
        NekoFontRenderer.renderText(8, x + 5, y + NekoFontRenderer.getTextHeight(8)*3 - 2.5f, "Z: " + String.format("%.2f", playerZ), Color.WHITE);
        NekoFontRenderer.renderText(8, x + 5, y + NekoFontRenderer.getTextHeight(8)*4 - 2.5f, "Biome: ", Color.WHITE);
        NekoFontRenderer.renderText(8, x + 5 + NekoFontRenderer.getTextWidth(8, "Biome:_"), y + NekoFontRenderer.getTextHeight(8)*4 - 2.5f, biome.name, biome.color);
    }
}
