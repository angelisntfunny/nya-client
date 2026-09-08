package org.nyaclient.module;

import net.minecraft.client.MinecraftClient;
import org.nyaclient.module.utility.Fullbright;
import org.nyaclient.module.utility.Keystrokes;
import org.nyaclient.module.utility.ToggleSprint;
import org.nyaclient.module.utility.ZoomMod;
import org.nyaclient.module.visuals.StatusEffects;
import org.nyaclient.module.visuals.TNTTimer;
import org.nyaclient.module.visuals.VisualTweaks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    private final List<IMod> MODS = new ArrayList<>();

    public ModManager() {
        MODS.add(new ToggleSprint());
        MODS.add(new Keystrokes());
        MODS.add(new VisualTweaks());
        MODS.add(new Fullbright());
        MODS.add(new TNTTimer());
        MODS.add(new StatusEffects());
        MODS.add(new ZoomMod());
    }

    public void onKey(int key) {
        if (MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().world == null) return;
        MODS.stream().filter(m -> m.getKey() == key).forEach(IMod::toggle);
    }

    public <T> T getMod(Class<T> clazz) {
        return MODS.stream().filter(m -> m.getClass().equals(clazz)).map(clazz::cast).findFirst().orElse(null);
    }

    public List<HUDMod> getHUDMods() {
        return MODS.stream().filter(m -> m instanceof HUDMod && ((HUDMod) m).isEnabled()).map(HUDMod.class::cast).collect(Collectors.toList());
    }
}
