package org.nyaclient.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;
import org.nyaclient.utils.FontRenderer;
import org.nyaclient.utils.NanoVGManager;

import java.awt.*;

public class NyaTitleScreen extends Screen {
    private static final Identifier[] PANORAMA_CUBE_FACES = new Identifier[]{
            new Identifier("nyaclient", "panorama/panorama_0.png"),
            new Identifier("nyaclient", "panorama/panorama_1.png"),
            new Identifier("nyaclient", "panorama/panorama_2.png"),
            new Identifier("nyaclient", "panorama/panorama_3.png"),
            new Identifier("nyaclient", "panorama/panorama_4.png"),
            new Identifier("nyaclient", "panorama/panorama_5.png")
    };

    @SuppressWarnings("FieldCanBeLocal")
    private NativeImageBackedTexture backgroundTexture;
    private Identifier backgroundTextureId;
    private int ticks;

    public NyaTitleScreen() {
        ticks = 0;
    }

    @Override
    public void init() {
        this.backgroundTexture = new NativeImageBackedTexture(256, 256);
        this.backgroundTextureId = this.client.getTextureManager().registerDynamicTexture("background", this.backgroundTexture);

        super.init();
    }

    @Override
    public void tick() {
        this.ticks++;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);

        for (int i = 0; i < 4; i++) {
            float buttonY = height / 2.0F + i * 30.0f;
            float x = width / 2.0F - 65.0f;

            if (mouseX >= x && mouseX <= x + 130 && mouseY >= buttonY && mouseY <= buttonY + 25) {
                if (i == 0) {
                    MinecraftClient.getInstance().setScreen(new SelectWorldScreen(this));
                } else if (i == 1) {
                    MinecraftClient.getInstance().setScreen(new MultiplayerScreen(this));
                } else if (i == 2) {
                    // why in the fuck does this need the game settings just get it yourself bro
                    MinecraftClient.getInstance().setScreen(new SettingsScreen(this, MinecraftClient.getInstance().options));
                } else {
                    client.stop();
                }
            }
        }
    }

    private void renderPanorama(float tickDelta) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        int i = 8;

        for (int j = 0; j < i * i; j++) {
            GlStateManager.pushMatrix();
            float f = ((float)(j % i) / i - 0.5F) / 64.0F;
            float g = ((float)(j / i) / i - 0.5F) / 64.0F;
            float h = 0.0F;
            GlStateManager.translate(f, g, h);
            GlStateManager.rotate(MathHelper.sin((this.ticks + tickDelta) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-(this.ticks + tickDelta) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int k = 0; k < 6; k++) {
                GlStateManager.pushMatrix();
                if (k == 1) {
                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 2) {
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 3) {
                    GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 4) {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (k == 5) {
                    GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.client.getTextureManager().bindTexture(PANORAMA_CUBE_FACES[k]);
                bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
                int l = 255 / (j + 1);
                bufferBuilder.vertex(-1.0, -1.0, 1.0).texture(0.0, 0.0).color(255, 255, 255, l).next();
                bufferBuilder.vertex(1.0, -1.0, 1.0).texture(1.0, 0.0).color(255, 255, 255, l).next();
                bufferBuilder.vertex(1.0, 1.0, 1.0).texture(1.0, 1.0).color(255, 255, 255, l).next();
                bufferBuilder.vertex(-1.0, 1.0, 1.0).texture(0.0, 1.0).color(255, 255, 255, l).next();
                tessellator.draw();
                GlStateManager.popMatrix();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        bufferBuilder.offset(0.0, 0.0, 0.0);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepthTest();
    }

    private void transformPanorama() {
        this.client.getTextureManager().bindTexture(this.backgroundTextureId);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        GlStateManager.colorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        GlStateManager.disableAlphaTest();
        int i = 3;

        for (int j = 0; j < i; j++) {
            float f = 1.0F / (j + 1);
            int k = this.width;
            int l = this.height;
            float g = (j - (float) i / 2) / 256.0F;
            bufferBuilder.vertex(k, l, this.zOffset).texture(0.0F + g, 1.0).color(1.0F, 1.0F, 1.0F, f).next();
            bufferBuilder.vertex(k, 0.0, this.zOffset).texture(1.0F + g, 1.0).color(1.0F, 1.0F, 1.0F, f).next();
            bufferBuilder.vertex(0.0, 0.0, this.zOffset).texture(1.0F + g, 0.0).color(1.0F, 1.0F, 1.0F, f).next();
            bufferBuilder.vertex(0.0, l, this.zOffset).texture(0.0F + g, 0.0).color(1.0F, 1.0F, 1.0F, f).next();
        }

        tessellator.draw();
        GlStateManager.enableAlphaTest();
        GlStateManager.colorMask(true, true, true, true);
    }

    private void renderBackground(float tickDelta) {
        this.client.getFramebuffer().unbind();
        GlStateManager.viewport(0, 0, 256, 256);
        this.renderPanorama(tickDelta);
        this.transformPanorama();
        this.transformPanorama();
        this.transformPanorama();
        this.transformPanorama();
        this.transformPanorama();
        this.transformPanorama();
        this.transformPanorama();
        this.client.getFramebuffer().bind(true);
        GlStateManager.viewport(0, 0, this.client.width, this.client.height);
        float f = this.width > this.height ? 120.0F / this.width : 120.0F / this.height;
        float g = this.height * f / 256.0F;
        float h = this.width * f / 256.0F;
        int i = this.width;
        int j = this.height;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(0.0, j, this.zOffset).texture(0.5F - g, 0.5F + h).color(1.0F, 1.0F, 1.0F, 1.0F).next();
        bufferBuilder.vertex(i, j, this.zOffset).texture(0.5F - g, 0.5F - h).color(1.0F, 1.0F, 1.0F, 1.0F).next();
        bufferBuilder.vertex(i, 0.0, this.zOffset).texture(0.5F + g, 0.5F - h).color(1.0F, 1.0F, 1.0F, 1.0F).next();
        bufferBuilder.vertex(0.0, 0.0, this.zOffset).texture(0.5F + g, 0.5F + h).color(1.0F, 1.0F, 1.0F, 1.0F).next();
        tessellator.draw();
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        this.renderBackground(tickDelta);
//        MinecraftClient mc = MinecraftClient.getInstance();

        NanoVGManager.render(() -> {
            FontRenderer.renderCenteredText(24, this.width / 2.0F, this.height / 2.0F - 50, "Nya", new Color(255, 255, 255));

            for (int i = 0; i < 4; i++) {
                float buttonY = height / 2.0F + i * 27;

                NanoVGManager.drawRoundedRect(
                        width/2.0F - 65,
                        buttonY,
                        130.0f,
                        25.0f,
                        5,
                        new Color(0, 0, 0, 170)
                );

                // java 8 doesn't have switches so I need to do this fuckery
                String label = i == 0 ? "Singleplayer" : (i == 1 ? "Multiplayer": (i == 2 ? "Settings" : "Quit"));

                FontRenderer.renderCenteredText(
                        10,
                        width/2.0F,
                        buttonY + 12.5f,
                        label,
                        new Color(255, 255, 255)
                );
            }
        });

        super.render(mouseX, mouseY, tickDelta);
    }
}
