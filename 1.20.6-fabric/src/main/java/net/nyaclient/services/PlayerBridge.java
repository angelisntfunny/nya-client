package net.nyaclient.services;

import net.minecraft.client.Minecraft;
import net.nyaclient.enums.Biome;
import net.nyaclient.platform.services.IPlayerBridge;

import java.util.Optional;

public class PlayerBridge implements IPlayerBridge {

    @Override
    public Optional<Double> getX() {
        if (Minecraft.getInstance().player == null)
            return Optional.empty();
        return Optional.of(Minecraft.getInstance().player.position().x);
    }

    @Override
    public Optional<Double> getY() {
        if (Minecraft.getInstance().player == null)
            return Optional.empty();
        return Optional.of(Minecraft.getInstance().player.position().y);
    }

    @Override
    public Optional<Double> getZ() {
        if (Minecraft.getInstance().player == null)
            return Optional.empty();
        return Optional.of(Minecraft.getInstance().player.position().z);
    }

    @Override
    public Optional<Biome> getBiome() {
        if (Minecraft.getInstance().level == null || Minecraft.getInstance().player == null) return Optional.empty();
        return Optional.of(Biome.getBiome(Minecraft.getInstance().level.getBiome(Minecraft.getInstance().player.blockPosition()).getRegisteredName()));
    }
}
