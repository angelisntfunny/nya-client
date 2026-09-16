package org.nyaclient.module;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import org.lwjgl.nanovg.NanoVG;
import org.nyaclient.NyaClient;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.io.IOException;
import java.nio.ByteBuffer;

@Getter @Setter
public class AbstractMod implements IMod {
    protected final String name;
    protected final String iconPath;
    protected final ModCategory category;
    protected int key;

    private ByteBuffer imageBuffer;
    private int handle = 0;

    public String getName() {
        return I18n.translate(name);
    }

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    protected boolean enabled;

    public AbstractMod(String name, String iconPath, ModCategory category, int key) {
        this.name = name;
        this.iconPath = iconPath;
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

    public int getIconHandle() {
        if (handle == 0) {
            try {
                imageBuffer = FontRenderer.ioResourceToByteBuffer("/assets/nyaclient/" + this.getIconPath());
                handle = NanoVG.nvgCreateImageMem(NanoVGManager.getNvgContext(), 0, imageBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return handle;
        }

        return handle;
    }

    public void cleanup() {
        if (this.handle != 0) {
            NanoVG.nvgDeleteImage(NanoVGManager.getNvgContext(), this.handle);
            this.handle = 0;
            this.imageBuffer = null;
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
