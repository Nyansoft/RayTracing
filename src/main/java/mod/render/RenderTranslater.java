package mod.render;

import org.lwjgl.opengl.GL11;

import mod.ModBase;
import mod.content.entity.EntityTranslaterStatic;
import mod.lib.Localizations;
import mod.model.ModelTranslater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTranslater extends Render<Entity>
{
	public ModelTranslater modelTranslater = new ModelTranslater();
	private boolean isStatic;
	
	public RenderTranslater(RenderManager manager, boolean par2) 
	{
		super(manager);
		this.isStatic = par2;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		this.bindTexture(Localizations.TRANSLATERTEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x, (float)y, (float)z);
		
		if(!isStatic)
		{	
			GlStateManager.rotate(entity.ticksExisted * 10, 1, 0, 1);
		}
		
		else
		{
			if(entity instanceof EntityTranslaterStatic)
			{
				EntityTranslaterStatic translaterStatic = (EntityTranslaterStatic)entity;
				EnumFacing facing = translaterStatic.getFacing();
				
				double d = EntityTranslaterStatic.SIZE;
				double d1 = d / 2D;
				
				if(facing == EnumFacing.DOWN) 
				{
					GlStateManager.rotate(180, 1, 0, 0);
					GlStateManager.translate(0, -d, 0);
				}
				
				else if(facing == EnumFacing.EAST) 
				{
					GlStateManager.rotate(-90, 0, 0, 1);
					GlStateManager.translate(-d1, -d1, 0);
				}
				
				else if(facing == EnumFacing.WEST) 
				{
					GlStateManager.rotate(90, 0, 0, 1);
					GlStateManager.translate(d1, -d1, 0);
				}
				
				else if(facing == EnumFacing.NORTH) 
				{
					GlStateManager.rotate(-90, 1, 0, 0);
					GlStateManager.translate(0, -d1, d1);
				}
				
				else if(facing == EnumFacing.SOUTH) 
				{
					GlStateManager.rotate(90, 1, 0, 0);
					GlStateManager.translate(0, -d1, -d1);
				}
				
				GlStateManager.translate(0.0D, 0.0625D, 0D);
				
				GlStateManager.pushMatrix();
				GlStateManager.disableCull();
				GlStateManager.disableDepth();
				GlStateManager.rotate(-this.getRenderManager().playerViewY, 0, 1, 0);
				GlStateManager.rotate(this.getRenderManager().playerViewX, 1, 0, 0);
				final float u = 52f;
				final float v = 20f;
				final float w = 12f;
				final float h = 12f;
				
				float fu = u / 64f;
				float fv = v / 32f;
				
				float ffu = (u + w) / 64f;
				float ffv = (v + h) / 32f;
				
				float distance = Minecraft.getMinecraft().player.getDistance(entity) / 2;
				float fffu = (0.0625f * w);
				float fffv = (0.0625f * h);
				
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferBuilder = tessellator.getBuffer();
				bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				bufferBuilder.pos(0F - (fffu / 2), 0 - (fffv / 2), 0).tex(ffu, ffv).endVertex();
				bufferBuilder.pos(0F - (fffu / 2), fffv - (fffv / 2), 0).tex(ffu, fv).endVertex();
				bufferBuilder.pos(fffu -(fffu / 2), fffv - (fffv / 2), 0).tex(fu, fv).endVertex();
				bufferBuilder.pos(fffu -(fffu / 2), 0 - (fffv / 2), 0).tex(fu, ffv).endVertex();
				tessellator.draw();
				GlStateManager.enableDepth();
				GlStateManager.popMatrix();
			}
		}
		
		GlStateManager.scale(1, -1, -1);
		modelTranslater.renderAll();
		
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return Localizations.TRANSLATERTEXTURE;
	}
}
