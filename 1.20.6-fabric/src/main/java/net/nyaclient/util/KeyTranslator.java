/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.util;

import net.nyaclient.enums.Key;
import org.lwjgl.glfw.GLFW;

// I fucking hate my life

public class KeyTranslator {
    public static Key getKeyFromInteger(int key) {
        return switch (key) {
            case GLFW.GLFW_KEY_A -> Key.KEY_A;
            case GLFW.GLFW_KEY_B -> Key.KEY_B;
            case GLFW.GLFW_KEY_C -> Key.KEY_C;
            case GLFW.GLFW_KEY_D -> Key.KEY_D;
            case GLFW.GLFW_KEY_E -> Key.KEY_E;
            case GLFW.GLFW_KEY_F -> Key.KEY_F;
            case GLFW.GLFW_KEY_G -> Key.KEY_G;
            case GLFW.GLFW_KEY_H -> Key.KEY_H;
            case GLFW.GLFW_KEY_I -> Key.KEY_I;
            case GLFW.GLFW_KEY_J -> Key.KEY_J;
            case GLFW.GLFW_KEY_K -> Key.KEY_K;
            case GLFW.GLFW_KEY_L -> Key.KEY_L;
            case GLFW.GLFW_KEY_M -> Key.KEY_M;
            case GLFW.GLFW_KEY_N -> Key.KEY_N;
            case GLFW.GLFW_KEY_O -> Key.KEY_O;
            case GLFW.GLFW_KEY_P -> Key.KEY_P;
            case GLFW.GLFW_KEY_Q -> Key.KEY_Q;
            case GLFW.GLFW_KEY_R -> Key.KEY_R;
            case GLFW.GLFW_KEY_S -> Key.KEY_S;
            case GLFW.GLFW_KEY_T -> Key.KEY_T;
            case GLFW.GLFW_KEY_U -> Key.KEY_U;
            case GLFW.GLFW_KEY_V -> Key.KEY_V;
            case GLFW.GLFW_KEY_W -> Key.KEY_W;
            case GLFW.GLFW_KEY_X -> Key.KEY_X;
            case GLFW.GLFW_KEY_Y -> Key.KEY_Y;
            case GLFW.GLFW_KEY_Z -> Key.KEY_Z;
            case GLFW.GLFW_KEY_0 -> Key.KEY_0;
            case GLFW.GLFW_KEY_1 -> Key.KEY_1;
            case GLFW.GLFW_KEY_2 -> Key.KEY_2;
            case GLFW.GLFW_KEY_3 -> Key.KEY_3;
            case GLFW.GLFW_KEY_4 -> Key.KEY_4;
            case GLFW.GLFW_KEY_5 -> Key.KEY_5;
            case GLFW.GLFW_KEY_6 -> Key.KEY_6;
            case GLFW.GLFW_KEY_7 -> Key.KEY_7;
            case GLFW.GLFW_KEY_8 -> Key.KEY_8;
            case GLFW.GLFW_KEY_9 -> Key.KEY_9;
            case GLFW.GLFW_KEY_SPACE -> Key.KEY_SPACE;
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> Key.KEY_RIGHT_SHIFT;
            case GLFW.GLFW_KEY_LEFT_SHIFT -> Key.KEY_LEFT_SHIFT;
            case GLFW.GLFW_KEY_LEFT_CONTROL -> Key.KEY_LEFT_CONTROL;
            case GLFW.GLFW_KEY_LEFT_ALT -> Key.KEY_ALT;
            case GLFW.GLFW_KEY_BACKSLASH -> Key.KEY_BACKSLASH;
            case GLFW.GLFW_KEY_BACKSPACE -> Key.KEY_BACKSPACE;
            case GLFW.GLFW_KEY_CAPS_LOCK -> Key.KEY_CAPS_LOCK;
            case GLFW.GLFW_KEY_SEMICOLON -> Key.KEY_SEMICOLON;
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> Key.KEY_RIGHT_CONTROL;
            case GLFW.GLFW_KEY_RIGHT_ALT -> Key.KEY_RIGHT_ALT;
            case GLFW.GLFW_KEY_COMMA -> Key.KEY_COMMA;
            case GLFW.GLFW_KEY_DELETE -> Key.KEY_DELETE;
            case GLFW.GLFW_KEY_DOWN -> Key.KEY_DOWN_ARROW;
            case GLFW.GLFW_KEY_END -> Key.KEY_END;
            case GLFW.GLFW_KEY_EQUAL -> Key.KEY_EQUALS;
            case GLFW.GLFW_KEY_ESCAPE -> Key.KEY_ESCAPE;
            case GLFW.GLFW_KEY_F1 -> Key.KEY_F1;
            case GLFW.GLFW_KEY_F2 -> Key.KEY_F2;
            case GLFW.GLFW_KEY_F3 -> Key.KEY_F3;
            case GLFW.GLFW_KEY_F4 -> Key.KEY_F4;
            case GLFW.GLFW_KEY_F5 -> Key.KEY_F5;
            case GLFW.GLFW_KEY_F6 -> Key.KEY_F6;
            case GLFW.GLFW_KEY_F7 -> Key.KEY_F7;
            case GLFW.GLFW_KEY_F8 -> Key.KEY_F8;
            case GLFW.GLFW_KEY_F9 -> Key.KEY_F9;
            case GLFW.GLFW_KEY_PERIOD -> Key.KEY_PERIOD;
            case GLFW.GLFW_KEY_SLASH -> Key.KEY_SLASH;
            case GLFW.GLFW_KEY_ENTER -> Key.KEY_ENTER;
            case GLFW.GLFW_KEY_MINUS -> Key.KEY_MINUS;
            case GLFW.GLFW_KEY_APOSTROPHE -> Key.KEY_SINGLE_APOSTROPHE;
            case GLFW.GLFW_KEY_TAB -> Key.KEY_TAB;
            case GLFW.GLFW_KEY_F10 -> Key.KEY_F10;
            case GLFW.GLFW_KEY_F11 -> Key.KEY_F11;
            case GLFW.GLFW_KEY_F12 -> Key.KEY_F12;
            case GLFW.GLFW_KEY_GRAVE_ACCENT -> Key.KEY_GRAVE;
            case GLFW.GLFW_KEY_PRINT_SCREEN -> Key.KEY_PRINT_SCREEN;
            case GLFW.GLFW_KEY_SCROLL_LOCK -> Key.KEY_SCROLL_LOCK;
            case GLFW.GLFW_KEY_INSERT -> Key.KEY_INSERT;
            case GLFW.GLFW_KEY_HOME -> Key.KEY_HOME;
            case GLFW.GLFW_KEY_PAGE_UP -> Key.KEY_PAGE_UP;
            case GLFW.GLFW_KEY_PAGE_DOWN -> Key.KEY_PAGE_DOWN;
            case GLFW.GLFW_KEY_LEFT -> Key.KEY_LEFT_ARROW;
            case GLFW.GLFW_KEY_UP -> Key.KEY_UP_ARROW;
            case GLFW.GLFW_KEY_RIGHT -> Key.KEY_RIGHT_ARROW;
            default -> Key.KEY_NONE;
        };

    }

    public static int getIntegerFromKey(Key key) {
        return switch (key) {
            case KEY_A -> GLFW.GLFW_KEY_A;
            case KEY_B -> GLFW.GLFW_KEY_B;
            case KEY_C -> GLFW.GLFW_KEY_C;
            case KEY_D -> GLFW.GLFW_KEY_D;
            case KEY_E -> GLFW.GLFW_KEY_E;
            case KEY_F -> GLFW.GLFW_KEY_F;
            case KEY_G -> GLFW.GLFW_KEY_G;
            case KEY_H -> GLFW.GLFW_KEY_H;
            case KEY_I -> GLFW.GLFW_KEY_I;
            case KEY_J -> GLFW.GLFW_KEY_J;
            case KEY_K -> GLFW.GLFW_KEY_K;
            case KEY_L -> GLFW.GLFW_KEY_L;
            case KEY_M -> GLFW.GLFW_KEY_M;
            case KEY_N -> GLFW.GLFW_KEY_N;
            case KEY_O -> GLFW.GLFW_KEY_O;
            case KEY_P -> GLFW.GLFW_KEY_P;
            case KEY_Q -> GLFW.GLFW_KEY_Q;
            case KEY_R -> GLFW.GLFW_KEY_R;
            case KEY_S -> GLFW.GLFW_KEY_S;
            case KEY_T -> GLFW.GLFW_KEY_T;
            case KEY_U -> GLFW.GLFW_KEY_U;
            case KEY_V -> GLFW.GLFW_KEY_V;
            case KEY_W -> GLFW.GLFW_KEY_W;
            case KEY_X -> GLFW.GLFW_KEY_X;
            case KEY_Y -> GLFW.GLFW_KEY_Y;
            case KEY_Z -> GLFW.GLFW_KEY_Z;
            case KEY_0 -> GLFW.GLFW_KEY_0;
            case KEY_1 -> GLFW.GLFW_KEY_1;
            case KEY_2 -> GLFW.GLFW_KEY_2;
            case KEY_3 -> GLFW.GLFW_KEY_3;
            case KEY_4 -> GLFW.GLFW_KEY_4;
            case KEY_5 -> GLFW.GLFW_KEY_5;
            case KEY_6 -> GLFW.GLFW_KEY_6;
            case KEY_7 -> GLFW.GLFW_KEY_7;
            case KEY_8 -> GLFW.GLFW_KEY_8;
            case KEY_9 -> GLFW.GLFW_KEY_9;
            case KEY_SPACE -> GLFW.GLFW_KEY_SPACE;
            case KEY_RIGHT_SHIFT -> GLFW.GLFW_KEY_RIGHT_SHIFT;
            case KEY_LEFT_SHIFT -> GLFW.GLFW_KEY_LEFT_SHIFT;
            case KEY_LEFT_CONTROL -> GLFW.GLFW_KEY_LEFT_CONTROL;
            case KEY_ALT -> GLFW.GLFW_KEY_LEFT_ALT;
            case KEY_BACKSLASH -> GLFW.GLFW_KEY_BACKSLASH;
            case KEY_BACKSPACE -> GLFW.GLFW_KEY_BACKSPACE;
            case KEY_CAPS_LOCK -> GLFW.GLFW_KEY_CAPS_LOCK;
            case KEY_SEMICOLON -> GLFW.GLFW_KEY_SEMICOLON;
            case KEY_RIGHT_CONTROL -> GLFW.GLFW_KEY_RIGHT_CONTROL;
            case KEY_RIGHT_ALT -> GLFW.GLFW_KEY_RIGHT_ALT;
            case KEY_COMMA -> GLFW.GLFW_KEY_COMMA;
            case KEY_DELETE -> GLFW.GLFW_KEY_DELETE;
            case KEY_DOWN_ARROW -> GLFW.GLFW_KEY_DOWN;
            case KEY_END -> GLFW.GLFW_KEY_END;
            case KEY_EQUALS -> GLFW.GLFW_KEY_EQUAL;
            case KEY_ESCAPE -> GLFW.GLFW_KEY_ESCAPE;
            case KEY_F1 -> GLFW.GLFW_KEY_F1;
            case KEY_F2 -> GLFW.GLFW_KEY_F2;
            case KEY_F3 -> GLFW.GLFW_KEY_F3;
            case KEY_F4 -> GLFW.GLFW_KEY_F4;
            case KEY_F5 -> GLFW.GLFW_KEY_F5;
            case KEY_F6 -> GLFW.GLFW_KEY_F6;
            case KEY_F7 -> GLFW.GLFW_KEY_F7;
            case KEY_F8 -> GLFW.GLFW_KEY_F8;
            case KEY_F9 -> GLFW.GLFW_KEY_F9;
            case KEY_PERIOD -> GLFW.GLFW_KEY_PERIOD;
            case KEY_SLASH -> GLFW.GLFW_KEY_SLASH;
            case KEY_ENTER -> GLFW.GLFW_KEY_ENTER;
            case KEY_MINUS -> GLFW.GLFW_KEY_MINUS;
            case KEY_SINGLE_APOSTROPHE -> GLFW.GLFW_KEY_APOSTROPHE;
            case KEY_TAB -> GLFW.GLFW_KEY_TAB;
            case KEY_F10 -> GLFW.GLFW_KEY_F10;
            case KEY_F11 -> GLFW.GLFW_KEY_F11;
            case KEY_F12 -> GLFW.GLFW_KEY_F12;
            case KEY_GRAVE -> GLFW.GLFW_KEY_GRAVE_ACCENT;
            case KEY_PRINT_SCREEN -> GLFW.GLFW_KEY_PRINT_SCREEN;
            case KEY_SCROLL_LOCK -> GLFW.GLFW_KEY_SCROLL_LOCK;
            case KEY_INSERT -> GLFW.GLFW_KEY_INSERT;
            case KEY_HOME -> GLFW.GLFW_KEY_HOME;
            case KEY_PAGE_UP -> GLFW.GLFW_KEY_PAGE_UP;
            case KEY_PAGE_DOWN -> GLFW.GLFW_KEY_PAGE_DOWN;
            case KEY_LEFT_ARROW -> GLFW.GLFW_KEY_LEFT;
            case KEY_UP_ARROW -> GLFW.GLFW_KEY_UP;
            case KEY_RIGHT_ARROW -> GLFW.GLFW_KEY_RIGHT;
            default -> GLFW.GLFW_KEY_UNKNOWN;
        };

    }
}
