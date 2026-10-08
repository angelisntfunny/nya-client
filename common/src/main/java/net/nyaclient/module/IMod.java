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
import net.nyaclient.option.Value;

import java.util.List;

/**
 * An interface that mods follow. Used so that any class can become a mod, even if it doesn't extend AbstractMod
 *
 * @see net.nyaclient.module.AbstractMod
 */
public interface IMod {
    void toggle();
    String getName();
    Key getKey();
    void setKey(Key key);
    int getHandle();
    boolean isEnabled();
    void setEnabled(boolean enabled);
    void cleanup();
    List<Value<?>> getOptions();

}
