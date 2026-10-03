/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.module;

import com.google.gson.annotations.Expose;
import net.nyaclient.NyaClient;
import net.nyaclient.keyboard.Key;
import net.nyaclient.neko.NekoRenderer;
import net.nyaclient.option.Value;
import net.nyaclient.platform.Services;
import net.nyaclient.util.IOUtils;
import org.lwjgl.nanovg.NanoVG;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class AbstractMod implements IMod {
    protected final String iconPath;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteBuffer imageBuffer;
    private int handle = 0;

    @Expose
    protected boolean enabled;
    @Expose
    protected Key key = Key.KEY_NONE;
    @Expose
    protected List<Value<?>> values;

    protected final ModCategory category;

    @Expose(deserialize = false)
    protected final String name;

    public AbstractMod(String term, String iconPath, ModCategory category) {
        this.name = term;
        this.iconPath = iconPath;
        this.category = category;
    }

    @Override
    public void toggle() {
        setEnabled(!enabled);
    }

    @Override
    public String getName() {
        return Services.MINECRAFT_BRIDGE.translate(this.name);
    }

    @Override
    public Key getKey() {
        return this.key;
    }

    @Override
    public void setKey(Key key) {
        this.key = key;
    }

    @Override
    public int getHandle() {
        if (handle == 0) {
            try {
                imageBuffer = IOUtils.ioResourceToByteBuffer("/assets/nyaclient/" + this.iconPath);
                handle = NanoVG.nvgCreateImageMem(NekoRenderer.getContext(), 0, imageBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return handle;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void onEnable() {
        NyaClient.getInstance().getEventBus().register(this);
    }
    public void onDisable() {
        NyaClient.getInstance().getEventBus().remove(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;

        this.enabled = enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    @Override
    public void cleanup() {

    }

    @Override
    public List<Value<?>> getOptions() {
        return List.of();
    }
}
