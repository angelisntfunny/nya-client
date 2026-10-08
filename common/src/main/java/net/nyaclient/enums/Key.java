/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.enums;

import net.nyaclient.util.StringUtils;

import java.util.Locale;

public enum Key {
    KEY_A,
    KEY_B,
    KEY_C,
    KEY_D,
    KEY_E,
    KEY_F,
    KEY_G,
    KEY_H,
    KEY_I,
    KEY_J,
    KEY_K,
    KEY_L,
    KEY_M,
    KEY_N,
    KEY_O,
    KEY_P,
    KEY_Q,
    KEY_R,
    KEY_S,
    KEY_T,
    KEY_U,
    KEY_V,
    KEY_W,
    KEY_X,
    KEY_Y,
    KEY_Z,
    KEY_0,
    KEY_1,
    KEY_2,
    KEY_3,
    KEY_4,
    KEY_5,
    KEY_6,
    KEY_7,
    KEY_8,
    KEY_9,
    KEY_RIGHT_SHIFT,
    KEY_LEFT_SHIFT,
    KEY_SPACE,
    KEY_ALT,
    KEY_LEFT_CONTROL,
    KEY_RIGHT_CONTROL,
    KEY_RIGHT_ALT,
    KEY_COMMA,
    KEY_PERIOD,
    KEY_SLASH,
    KEY_ENTER,
    KEY_BACKSLASH,
    KEY_BACKSPACE,
    KEY_EQUALS,
    KEY_MINUS,
    KEY_SEMICOLON,
    KEY_SINGLE_APOSTROPHE,
    KEY_CAPS_LOCK,
    KEY_TAB,
    KEY_F1,
    KEY_F2,
    KEY_F3,
    KEY_F4,
    KEY_F5,
    KEY_F6,
    KEY_F7,
    KEY_F8,
    KEY_F9,
    KEY_F10,
    KEY_F11,
    KEY_F12,
    KEY_GRAVE,
    KEY_ESCAPE,
    KEY_PRINT_SCREEN,
    KEY_SCROLL_LOCK,
    KEY_INSERT,
    KEY_HOME,
    KEY_PAGE_UP,
    KEY_DELETE,
    KEY_END,
    KEY_PAGE_DOWN,
    KEY_LEFT_ARROW,
    KEY_UP_ARROW,
    KEY_RIGHT_ARROW,
    KEY_DOWN_ARROW,
    KEY_NONE;

    public String getName() {
        return StringUtils.capitalizeText(this.name().replace("KEY_", "").replace("_", " ").replace("_", " ").toLowerCase(Locale.ROOT));
    }
}
