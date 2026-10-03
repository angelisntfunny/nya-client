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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;

public class IOUtils {
    public static ByteBuffer ioResourceToByteBuffer(String resource) throws IOException {
        try (InputStream source = IOUtils.class.getResourceAsStream(resource)) {
            if (source == null) {
                throw new RuntimeException("source is null");
            }
            byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(source);
            ByteBuffer nativeBuffer = MemoryUtil.memAlloc(bytes.length);
            nativeBuffer.put(bytes).flip();
            return nativeBuffer;
        }
    }
}
