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

import net.nyaclient.enums.Key;
import net.nyaclient.module.utility.ToggleSprint;
import net.nyaclient.module.utility.ZoomMod;
import net.nyaclient.module.visual.CPSMod;
import net.nyaclient.platform.Services;
import net.nyaclient.screen.impl.HUDPositionerRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    private final List<IMod> MODS = new ArrayList<>();

    public ModManager() {
        MODS.add(new ZoomMod());
        MODS.add(new CPSMod());
        MODS.add(new ToggleSprint());
    }

    public List<IMod> getMods() {
        return MODS;
    }

    public List<IHUDMod> getHUDMods() {
        return MODS.stream().filter(m -> m instanceof IHUDMod && m.isEnabled()).map(IHUDMod.class::cast).collect(Collectors.toList());
    }

    public void onKey(Key key) {
        if (!Services.MINECRAFT_BRIDGE.inGame()) return;

        if (key == Key.KEY_RIGHT_SHIFT) {
            Services.MINECRAFT_BRIDGE.setScreen(new HUDPositionerRenderer());
            return;
        }

        MODS.stream().filter(m -> m.getKey() == key).forEach(IMod::toggle);
    }
}
