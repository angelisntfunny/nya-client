package org.nyaclient.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.entity.TntEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.DecimalFormat;

@Mixin(TntEntityRenderer.class)
public abstract class TNTEntityRendererMixin extends EntityRenderer<TntEntity> {
    @Unique
    public final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    protected TNTEntityRendererMixin(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Inject(method = "render(Lnet/minecraft/entity/TntEntity;DDDFF)V", at = @At("RETURN"))
    protected void onRenderTnt(TntEntity entity, double x, double y, double z, float yaw, float tickDelta, CallbackInfo callback) {
        String text = decimalFormat.format((float) entity.fuseTimer / 20);
        float maxDistance = 64;
        double d = entity.squaredDistanceTo(this.dispatcher.field_11098);
        if (!(d > (double)(maxDistance * maxDistance))) {
            TextRenderer textRenderer = this.getFontRenderer();
            float f = 1.6F;
            float g = 0.016666668F * f;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x + 0.0F, (float)y + entity.height + 0.5F, (float)z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-this.dispatcher.yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(this.dispatcher.pitch, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-g, -g, g);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepthTest();
            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            int i = 0;
            if (text.equals("deadmau5")) {
                i = -10;
            }

            int j = textRenderer.getStringWidth(text) / 2;
            GlStateManager.disableTexture();
            bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex((double)(-j - 1), (double)(-1 + i), (double)0.0F).color(0.0F, 0.0F, 0.0F, 0.25F).next();
            bufferBuilder.vertex((double)(-j - 1), (double)(8 + i), (double)0.0F).color(0.0F, 0.0F, 0.0F, 0.25F).next();
            bufferBuilder.vertex((double)(j + 1), (double)(8 + i), (double)0.0F).color(0.0F, 0.0F, 0.0F, 0.25F).next();
            bufferBuilder.vertex((double)(j + 1), (double)(-1 + i), (double)0.0F).color(0.0F, 0.0F, 0.0F, 0.25F).next();
            tessellator.draw();
            GlStateManager.enableTexture();
            textRenderer.draw(text, -textRenderer.getStringWidth(text) / 2, i, 553648127);
            GlStateManager.enableDepthTest();
            GlStateManager.depthMask(true);
            textRenderer.draw(text, -textRenderer.getStringWidth(text) / 2, i, -1);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }
}
