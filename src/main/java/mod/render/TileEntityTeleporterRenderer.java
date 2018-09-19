package mod.render;

import org.lwjgl.opengl.GL11;

import mod.ModBase;
import mod.content.tile.TileEntityTeleporter;
import mod.lib.Localizations;
import mod.model.ModelTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTeleporterRenderer extends TileEntitySpecialRenderer<TileEntityTeleporter>
{
	private ModelTeleporter teleporter = new ModelTeleporter();
	
	
	@Override
	public void render(TileEntityTeleporter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) 
	{	
		
		GlStateManager.pushMatrix();
		this.bindTexture(Localizations.TELEPORTERTEXTURE);
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(0.5D, 0, 0.5D);
		GlStateManager.translate(0, 0.0625f, 0);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		
		float f = 0;
		
		float f0 = te.ascendAnimationValue;
		
		float f1 = f0 / (te.FINAL_ASCEND_ANIMATION_VALUE);
		float f2 = f1 * 0.0625F;
		teleporter.inneraxis.offsetY = -f2;
		teleporter.outteraxis.offsetY = teleporter.inneraxis.offsetY;
		teleporter.lowerplate.offsetY = teleporter.outteraxis.offsetY;
		teleporter.plate.offsetY = teleporter.lowerplate.offsetY;
		teleporter.magnum1.offsetY = teleporter.plate.offsetY;
		teleporter.magnum2.offsetY = teleporter.plate.offsetY;
		
		teleporter.inneraxis.rotateAngleY = te.rotationValue;
		teleporter.outteraxis.rotateAngleY = -teleporter.inneraxis.rotateAngleY;
		teleporter.lowerplate.rotateAngleY = teleporter.outteraxis.rotateAngleY;
		teleporter.plate.rotateAngleY = teleporter.inneraxis.rotateAngleY;
		teleporter.magnum1.rotateAngleY = teleporter.plate.rotateAngleY;
		teleporter.magnum2.rotateAngleY = teleporter.plate.rotateAngleY;
		
		teleporter.renderAll();
		GlStateManager.popMatrix();
		
		
		GlStateManager.pushMatrix();
		this.bindTexture(Localizations.EFFECTSTEXUTRE);
		GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//        
        float runningTime = (float)te.teleportingTime * 2;
//        
        if(te.ascendAnimationValue >= te.FINAL_ASCEND_ANIMATION_VALUE) 
        {
        	if(te.teleportingTime > 0)
        	{
        		float f10 = (float)(20 * 3);
        		float f11 = (float)te.teleportingTime;
        		
        		for(float clones = 0; clones <= f11 / f10; clones += 0.05)
            	{
            		renderCircleofCircles(te, x, y + clones, z, runningTime, 1F, 0F, 0.25F, 1 - clones);
            	}
        	}
        	
        	else
        	{
        		renderCircleofCircles(te, x, y, z, runningTime, 1F, 0F, 0.25F, 1);
        	}
        	
        }
//        
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        
	}
	
	private void renderCircleofCircles(TileEntityTeleporter te, double x, double y, double z, float runningTicks, float r, float g, float b, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.color(r, g, b, alpha);
		float size = 64;
		float f = size / 256;
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(0, (0.0625f * 7) + 0.0275F, 0);
		GlStateManager.translate(0.5D, 0, 0.5D);
		GlStateManager.rotate(runningTicks, 0, 1, 0);
		GlStateManager.translate(-0.5D, 0, -0.5D);
      
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferBuilder.pos(0, 0, 0).tex(0, 0).endVertex();
		bufferBuilder.pos(1, 0, 0).tex(f, 0).endVertex();
		bufferBuilder.pos(1, 0, 1).tex(f, f).endVertex();
		bufferBuilder.pos(0, 0, 1).tex(0, f).endVertex();
		tessellator.draw();
		GlStateManager.popMatrix();
		
		
	}
}
