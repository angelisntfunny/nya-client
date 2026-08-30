package org.nyaclient.module;

import net.minecraft.client.MinecraftClient;
import org.nyaclient.module.utility.ToggleSprint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    private final List<IMod> MODS = new ArrayList<>();

    public ModManager() {
        MODS.add(new ToggleSprint());
    }

    public void onKey(int key) {
        if (MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().world == null) return;
        MODS.stream().filter(m -> m.getKey() == key).forEach(IMod::toggle);
    }

    public List<HUDMod> getHUDMods() {
        return MODS.stream().filter(m -> m instanceof HUDMod && ((HUDMod) m).isEnabled()).map(HUDMod.class::cast).collect(Collectors.toList());
    }
}
