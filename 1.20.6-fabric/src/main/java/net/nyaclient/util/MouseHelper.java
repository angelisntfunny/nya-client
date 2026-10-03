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

public class MouseHelper {
    private static double dWheel = 0.0;

    public static void setdWheel(double dWheel) {
        MouseHelper.dWheel = dWheel;
    }

    public static double getdWheel() {
        double val = dWheel;
        dWheel = 0;
        return val;
    }
}
