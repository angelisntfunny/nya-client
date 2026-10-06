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

import net.nyaclient.keyboard.Key;
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
            case GLFW.GLFW_KEY_LEFT_ALT -> Key.KEY_ALT;
            default -> Key.KEY_NONE;
        };

    }

    public static int getIntegerFromKey(Key key) {
        return switch (key) {
            case Key.KEY_A -> GLFW.GLFW_KEY_A;
            case Key.KEY_B -> GLFW.GLFW_KEY_B;
            case Key.KEY_C -> GLFW.GLFW_KEY_C;
            case Key.KEY_D -> GLFW.GLFW_KEY_D;
            case Key.KEY_E -> GLFW.GLFW_KEY_E;
            case Key.KEY_F -> GLFW.GLFW_KEY_F;
            case Key.KEY_G -> GLFW.GLFW_KEY_G;
            case Key.KEY_H -> GLFW.GLFW_KEY_H;
            case Key.KEY_I -> GLFW.GLFW_KEY_I;
            case Key.KEY_J -> GLFW.GLFW_KEY_J;
            case Key.KEY_K -> GLFW.GLFW_KEY_K;
            case Key.KEY_L -> GLFW.GLFW_KEY_L;
            case Key.KEY_M -> GLFW.GLFW_KEY_M;
            case Key.KEY_N -> GLFW.GLFW_KEY_N;
            case Key.KEY_O -> GLFW.GLFW_KEY_O;
            case Key.KEY_P -> GLFW.GLFW_KEY_P;
            case Key.KEY_Q -> GLFW.GLFW_KEY_Q;
            case Key.KEY_R -> GLFW.GLFW_KEY_R;
            case Key.KEY_S -> GLFW.GLFW_KEY_S;
            case Key.KEY_T -> GLFW.GLFW_KEY_T;
            case Key.KEY_U -> GLFW.GLFW_KEY_U;
            case Key.KEY_V -> GLFW.GLFW_KEY_V;
            case Key.KEY_W -> GLFW.GLFW_KEY_W;
            case Key.KEY_X -> GLFW.GLFW_KEY_X;
            case Key.KEY_Y -> GLFW.GLFW_KEY_Y;
            case Key.KEY_Z -> GLFW.GLFW_KEY_Z;
            case Key.KEY_0 -> GLFW.GLFW_KEY_0;
            case Key.KEY_1 -> GLFW.GLFW_KEY_1;
            case Key.KEY_2 -> GLFW.GLFW_KEY_2;
            case Key.KEY_3 -> GLFW.GLFW_KEY_3;
            case Key.KEY_4 -> GLFW.GLFW_KEY_4;
            case Key.KEY_5 -> GLFW.GLFW_KEY_5;
            case Key.KEY_6 -> GLFW.GLFW_KEY_6;
            case Key.KEY_7 -> GLFW.GLFW_KEY_7;
            case Key.KEY_8 -> GLFW.GLFW_KEY_8;
            case Key.KEY_9 -> GLFW.GLFW_KEY_9;
            case Key.KEY_SPACE -> GLFW.GLFW_KEY_SPACE;
            case Key.KEY_RIGHT_SHIFT -> GLFW.GLFW_KEY_RIGHT_SHIFT;
            case Key.KEY_ALT -> GLFW.GLFW_KEY_LEFT_ALT;
            default -> GLFW.GLFW_KEY_UNKNOWN;
        };

    }
}
