/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.module.visual;

import net.nyaclient.enums.Key;
import net.nyaclient.module.BasicHUDMod;
import net.nyaclient.module.ModCategory;
import net.nyaclient.platform.Services;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

// inspired by https://www.youtube.com/watch?v=3q9A2vNuFx0, thanks eric golde!
public class CPSMod extends BasicHUDMod {
    private final List<Map.Entry<Long, Integer>> clicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private boolean wasRightPressed;

    public CPSMod() {
        super("mods.cpsdisplay", "icons/mouse.png", ModCategory.Visual, 200, 200);
        this.key = Key.KEY_N;
    }

    @Override
    protected String getString() {
        boolean isLeftPressed = Services.LWJGL_BRIDGE.isMousePressed(0);
        if (isLeftPressed != this.wasLeftPressed) {
            this.wasLeftPressed = isLeftPressed;
            if (isLeftPressed) {
                this.clicks.add(new AbstractMap.SimpleImmutableEntry<>(System.currentTimeMillis(), 0));
            }
        }

        boolean isRightPressed = Services.LWJGL_BRIDGE.isMousePressed(1);
        if (isRightPressed != this.wasRightPressed) {
            this.wasRightPressed = isRightPressed;
            if (isRightPressed) {
                this.clicks.add(new AbstractMap.SimpleImmutableEntry<>(System.currentTimeMillis(), 1));
            }
        }

        this.clicks.removeIf(entry -> System.currentTimeMillis() > entry.getKey() + 1000);
        int leftClicks = this.clicks.stream().filter(entry -> entry.getValue() == 0).collect(Collectors.toSet()).size();
        int rightClicks = this.clicks.stream().filter(entry -> entry.getValue() == 1).collect(Collectors.toSet()).size();

        return "[CPS] " + leftClicks + " | " + rightClicks;
    }
}
