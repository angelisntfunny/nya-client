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

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.nyaclient.platform.services.INanoVGBridge;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class NanoVGBridge implements INanoVGBridge {
    @Override
    public long init() {
        if (GL.getCapabilities().OpenGL30) {
            int flags = NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES;

            long nvgContext = NanoVGGL3.nvgCreate(flags);

            if (nvgContext == 0) {
                throw new IllegalStateException("nanovg context is null");
            }

            return nvgContext;
        }

        throw new IllegalStateException("opengl 3.0 not supported");
    }

    @Override
    public void render(Runnable runnable, long context) {
        RenderSystem.assertOnRenderThread();

        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();

        float pixelRatio = (float) window.getGuiScale();
        float width = window.getGuiScaledWidth();
        float height = window.getGuiScaledHeight();

        GLState state = GLState.capture();

        try {
            NanoVG.nvgBeginFrame(
                    context,
                    width,
                    height,
                    pixelRatio
            );

            runnable.run();

            NanoVG.nvgEndFrame(context);
        } finally {
            state.restore();

            RenderSystem.setShaderColor(
                    1.0F,
                    1.0F,
                    1.0F,
                    1.0F
            );

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.depthFunc(GL11.GL_LEQUAL);

            RenderSystem.enableCull();

            RenderSystem.disableScissor();

            GL11.glColorMask(
                    true,
                    true,
                    true,
                    true
            );
        }
    }

    private static final class GLState {

        private final int activeTexture;
        private final int texture0;
        private final int activeTextureBinding;

        private final int currentProgram;
        private final int vertexArray;
        private final int arrayBuffer;
        private final int elementArrayBuffer;

        private final int drawFramebuffer;
        private final int readFramebuffer;

        private final int[] viewport;
        private final int[] scissorBox;

        private final boolean depthTest;
        private final boolean blend;
        private final boolean cullFace;
        private final boolean scissorTest;
        private final boolean stencilTest;
        private final boolean polygonOffsetFill;

        private final int depthFunc;
        private final boolean depthMask;

        private final int blendEquationRgb;
        private final int blendEquationAlpha;

        private final int blendSrcRgb;
        private final int blendDstRgb;
        private final int blendSrcAlpha;
        private final int blendDstAlpha;

        private final boolean[] colorMask;

        private final int stencilFuncFront;
        private final int stencilRefFront;
        private final int stencilValueMaskFront;
        private final int stencilWriteMaskFront;
        private final int stencilFailFront;
        private final int stencilDepthFailFront;
        private final int stencilDepthPassFront;

        private final int stencilFuncBack;
        private final int stencilRefBack;
        private final int stencilValueMaskBack;
        private final int stencilWriteMaskBack;
        private final int stencilFailBack;
        private final int stencilDepthFailBack;
        private final int stencilDepthPassBack;

        private GLState() {
            this.activeTexture = GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE);

            this.texture0 = getTextureBinding(GL13.GL_TEXTURE0);
            this.activeTextureBinding = getTextureBinding(this.activeTexture);

            this.currentProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
            this.vertexArray = GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING);
            this.arrayBuffer = GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING);
            this.elementArrayBuffer =
                    GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING);

            this.drawFramebuffer =
                    GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING);
            this.readFramebuffer =
                    GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING);

            this.viewport = getInt4(GL11.GL_VIEWPORT);
            this.scissorBox = getInt4(GL11.GL_SCISSOR_BOX);

            this.depthTest = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
            this.blend = GL11.glIsEnabled(GL11.GL_BLEND);
            this.cullFace = GL11.glIsEnabled(GL11.GL_CULL_FACE);
            this.scissorTest = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
            this.stencilTest = GL11.glIsEnabled(GL11.GL_STENCIL_TEST);
            this.polygonOffsetFill =
                    GL11.glIsEnabled(GL11.GL_POLYGON_OFFSET_FILL);

            this.depthFunc = GL11.glGetInteger(GL11.GL_DEPTH_FUNC);
            this.depthMask = GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK);

            this.blendEquationRgb =
                    GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB);
            this.blendEquationAlpha =
                    GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA);

            this.blendSrcRgb =
                    GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB);
            this.blendDstRgb =
                    GL11.glGetInteger(GL14.GL_BLEND_DST_RGB);
            this.blendSrcAlpha =
                    GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA);
            this.blendDstAlpha =
                    GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA);

            this.colorMask = getBoolean4();

            this.stencilFuncFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_FUNC);
            this.stencilRefFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_REF);
            this.stencilValueMaskFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_VALUE_MASK);
            this.stencilWriteMaskFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_WRITEMASK);
            this.stencilFailFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_FAIL);
            this.stencilDepthFailFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_FAIL);
            this.stencilDepthPassFront =
                    GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_PASS);

            this.stencilFuncBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_FUNC);
            this.stencilRefBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_REF);
            this.stencilValueMaskBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_VALUE_MASK);
            this.stencilWriteMaskBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_WRITEMASK);
            this.stencilFailBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_FAIL);
            this.stencilDepthFailBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_PASS_DEPTH_FAIL);
            this.stencilDepthPassBack =
                    GL11.glGetInteger(GL20.GL_STENCIL_BACK_PASS_DEPTH_PASS);
        }

        public static GLState capture() {
            return new GLState();
        }

        public void restore() {
            GL30.glBindFramebuffer(
                    GL30.GL_DRAW_FRAMEBUFFER,
                    drawFramebuffer
            );

            GL30.glBindFramebuffer(
                    GL30.GL_READ_FRAMEBUFFER,
                    readFramebuffer
            );

            GL11.glViewport(
                    viewport[0],
                    viewport[1],
                    viewport[2],
                    viewport[3]
            );

            GL11.glScissor(
                    scissorBox[0],
                    scissorBox[1],
                    scissorBox[2],
                    scissorBox[3]
            );

            GL20.glUseProgram(currentProgram);
            GL30.glBindVertexArray(vertexArray);

            GL15.glBindBuffer(
                    GL15.GL_ARRAY_BUFFER,
                    arrayBuffer
            );

            GL15.glBindBuffer(
                    GL15.GL_ELEMENT_ARRAY_BUFFER,
                    elementArrayBuffer
            );

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(
                    GL11.GL_TEXTURE_2D,
                    texture0
            );

            GL13.glActiveTexture(activeTexture);
            GL11.glBindTexture(
                    GL11.GL_TEXTURE_2D,
                    activeTextureBinding
            );

            restoreCapability(GL11.GL_DEPTH_TEST, depthTest);
            restoreCapability(GL11.GL_BLEND, blend);
            restoreCapability(GL11.GL_CULL_FACE, cullFace);
            restoreCapability(GL11.GL_SCISSOR_TEST, scissorTest);
            restoreCapability(GL11.GL_STENCIL_TEST, stencilTest);
            restoreCapability(
                    GL11.GL_POLYGON_OFFSET_FILL,
                    polygonOffsetFill
            );

            GL11.glDepthFunc(depthFunc);
            GL11.glDepthMask(depthMask);

            GL20.glBlendEquationSeparate(
                    blendEquationRgb,
                    blendEquationAlpha
            );

            GL20.glBlendFuncSeparate(
                    blendSrcRgb,
                    blendDstRgb,
                    blendSrcAlpha,
                    blendDstAlpha
            );

            GL20.glColorMask(
                    colorMask[0],
                    colorMask[1],
                    colorMask[2],
                    colorMask[3]
            );

            GL20.glStencilFuncSeparate(
                    GL11.GL_FRONT,
                    stencilFuncFront,
                    stencilRefFront,
                    stencilValueMaskFront
            );

            GL20.glStencilMaskSeparate(
                    GL11.GL_FRONT,
                    stencilWriteMaskFront
            );

            GL20.glStencilOpSeparate(
                    GL11.GL_FRONT,
                    stencilFailFront,
                    stencilDepthFailFront,
                    stencilDepthPassFront
            );

            GL20.glStencilFuncSeparate(
                    GL11.GL_BACK,
                    stencilFuncBack,
                    stencilRefBack,
                    stencilValueMaskBack
            );

            GL20.glStencilMaskSeparate(
                    GL11.GL_BACK,
                    stencilWriteMaskBack
            );

            GL20.glStencilOpSeparate(
                    GL11.GL_BACK,
                    stencilFailBack,
                    stencilDepthFailBack,
                    stencilDepthPassBack
            );
        }

        private static void restoreCapability(
                int capability,
                boolean enabled
        ) {
            if (enabled) {
                GL11.glEnable(capability);
            } else {
                GL11.glDisable(capability);
            }
        }

        private static int getTextureBinding(int textureUnit) {
            GL13.glActiveTexture(textureUnit);

            return GL11.glGetInteger(
                    GL11.GL_TEXTURE_BINDING_2D
            );
        }

        private static int[] getInt4(int parameter) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer buffer = stack.mallocInt(4);

                GL11.glGetIntegerv(parameter, buffer);

                return new int[]{
                        buffer.get(0),
                        buffer.get(1),
                        buffer.get(2),
                        buffer.get(3)
                };
            }
        }

        private static boolean[] getBoolean4() {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                ByteBuffer buffer = stack.malloc(4);

                GL11.glGetBooleanv(GL11.GL_COLOR_WRITEMASK, buffer);

                return new boolean[]{
                        buffer.get(0) != 0,
                        buffer.get(1) != 0,
                        buffer.get(2) != 0,
                        buffer.get(3) != 0
                };
            }
        }
    }
}
