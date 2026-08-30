package org.nyaclient.module;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import org.nyaclient.NyaClient;

@Getter @Setter
public class AbstractMod implements IMod {
    protected final String name;
    protected final String description;
    protected final ModCategory category;
    protected int key;

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    protected boolean enabled;

    public AbstractMod(String name, String description, ModCategory category, int key) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = key;

        this.enabled = false;
    }

    public void setEnabled(boolean enabled) {
        if (enabled == this.enabled) return;

        this.enabled = enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void onEnable() {
        NyaClient.getInstance().getEventBus().register(this);
    }
    public void onDisable() {
        NyaClient.getInstance().getEventBus().remove(this);
    }
}
