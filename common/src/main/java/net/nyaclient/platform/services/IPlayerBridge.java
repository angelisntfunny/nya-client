package net.nyaclient.platform.services;

import net.nyaclient.enums.Biome;

import java.util.Optional;

public interface IPlayerBridge {
    Optional<Double> getX();
    Optional<Double> getY();
    Optional<Double> getZ();
    Optional<Biome> getBiome();
}
