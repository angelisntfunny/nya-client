/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.services;

import net.minecraft.client.Minecraft;
import net.nyaclient.keyboard.Key;
import net.nyaclient.platform.services.ILWJGLBridge;
import net.nyaclient.util.KeyTranslator;
import net.nyaclient.util.MouseHelper;
import org.lwjgl.glfw.GLFW;

public class LWJGLBridge implements ILWJGLBridge {
    @Override
    public boolean isMousePressed(int button) {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().getWindow(), button) == GLFW.GLFW_PRESS;
    }

    @Override
    public boolean isKeyDown(Key key) {
        if (key == Key.KEY_NONE) return false;
        return GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), KeyTranslator.getIntegerFromKey(key)) == GLFW.GLFW_PRESS;
    }

    @Override
    public double getdWheel() {
        return MouseHelper.getdWheel();
    }
}
