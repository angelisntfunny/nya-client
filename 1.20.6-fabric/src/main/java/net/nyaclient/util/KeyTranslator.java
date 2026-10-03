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
        if (key == GLFW.GLFW_KEY_A) return Key.KEY_A;
        if (key == GLFW.GLFW_KEY_B) return Key.KEY_B;
        if (key == GLFW.GLFW_KEY_C) return Key.KEY_C;
        if (key == GLFW.GLFW_KEY_D) return Key.KEY_D;
        if (key == GLFW.GLFW_KEY_E) return Key.KEY_E;
        if (key == GLFW.GLFW_KEY_F) return Key.KEY_F;
        if (key == GLFW.GLFW_KEY_G) return Key.KEY_G;
        if (key == GLFW.GLFW_KEY_H) return Key.KEY_H;
        if (key == GLFW.GLFW_KEY_I) return Key.KEY_I;
        if (key == GLFW.GLFW_KEY_J) return Key.KEY_J;
        if (key == GLFW.GLFW_KEY_K) return Key.KEY_K;
        if (key == GLFW.GLFW_KEY_L) return Key.KEY_L;
        if (key == GLFW.GLFW_KEY_M) return Key.KEY_M;
        if (key == GLFW.GLFW_KEY_N) return Key.KEY_N;
        if (key == GLFW.GLFW_KEY_O) return Key.KEY_O;
        if (key == GLFW.GLFW_KEY_P) return Key.KEY_P;
        if (key == GLFW.GLFW_KEY_Q) return Key.KEY_Q;
        if (key == GLFW.GLFW_KEY_R) return Key.KEY_R;
        if (key == GLFW.GLFW_KEY_S) return Key.KEY_S;
        if (key == GLFW.GLFW_KEY_T) return Key.KEY_T;
        if (key == GLFW.GLFW_KEY_U) return Key.KEY_U;
        if (key == GLFW.GLFW_KEY_V) return Key.KEY_V;
        if (key == GLFW.GLFW_KEY_W) return Key.KEY_W;
        if (key == GLFW.GLFW_KEY_X) return Key.KEY_X;
        if (key == GLFW.GLFW_KEY_Y) return Key.KEY_Y;
        if (key == GLFW.GLFW_KEY_Z) return Key.KEY_Z;
        if (key == GLFW.GLFW_KEY_0) return Key.KEY_0;
        if (key == GLFW.GLFW_KEY_1) return Key.KEY_1;
        if (key == GLFW.GLFW_KEY_2) return Key.KEY_2;
        if (key == GLFW.GLFW_KEY_3) return Key.KEY_3;
        if (key == GLFW.GLFW_KEY_4) return Key.KEY_4;
        if (key == GLFW.GLFW_KEY_5) return Key.KEY_5;
        if (key == GLFW.GLFW_KEY_6) return Key.KEY_6;
        if (key == GLFW.GLFW_KEY_7) return Key.KEY_7;
        if (key == GLFW.GLFW_KEY_8) return Key.KEY_8;
        if (key == GLFW.GLFW_KEY_9) return Key.KEY_9;
        if (key == GLFW.GLFW_KEY_SPACE) return Key.KEY_SPACE;
        if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) return Key.KEY_RIGHT_SHIFT;
        if (key == GLFW.GLFW_KEY_LEFT_ALT) return Key.KEY_ALT;

        return Key.KEY_NONE;
    }
}
