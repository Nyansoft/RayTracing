package mod.render;

import org.lwjgl.opengl.GL11;

import mod.ModBase;
import mod.content.entity.EntityLifeTracker;
import mod.lib.Localizations;
import mod.model.ModelLifeTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderLifeTracker extends Render<EntityLifeTracker> 
{
	
	public ModelLifeTracker modelLifeTracker = new ModelLifeTracker();
	
	public RenderLifeTracker(RenderManager renderManager)
	{
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityLifeTracker entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		GlStateManager.pushMatrix();
		this.bindTexture(Localizations.LIFETRACKERTEXTURE);
//		float f1 = (float)(Minecraft.getSystemTime() % (Math.PI * 2000)) / 2000;
//        GlStateManager.translate(0.0F, MathHelper.sin(f1) / 3, 0.0F);
		GlStateManager.translate(0, 0.25, 0);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		renderModel(entity);
		
		if(entity.isActive()) renderSpector(bufferBuilder, tessellator, entity);
		GlStateManager.popMatrix();
		renderLines(bufferBuilder, tessellator, entity, x, y, z);
		GlStateManager.popMatrix();
	}
	
	private void renderLines(BufferBuilder bufferBuilder, Tessellator tessellator, EntityLifeTracker entity, double x, double y, double z)
	{
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		float lenght = 5f;
		bufferBuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
		bufferBuilder.pos(x, y, z).color(1F, 0F, 0F, 1F).endVertex();
		bufferBuilder.pos(x + (entity.getLookVec().x * lenght), y + (entity.getLookVec().y * lenght), z + (entity.getLookVec().z * lenght)).color(1F, 0F, 0F, 1F).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}
	
	private void renderModel(EntityLifeTracker entity)
	{
		GlStateManager.rotate(-entity.rotationYaw + 90, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch + 90, 0, 0, 1);
		GlStateManager.translate(0.0625F, 0.5625F, -0.0625F);
		GlStateManager.translate(-0.25F, 0, 0.25F);
		
		float f = 0;
		
		if(entity.isActive()) f = entity.ticksExisted;
		else f = 0;
		
		modelLifeTracker.axis.rotateAngleY = f;
		modelLifeTracker.base.rotateAngleY = -modelLifeTracker.axis.rotateAngleY;
		
		GlStateManager.scale(1, -1, -1);
		modelLifeTracker.render();
	}
	
	private void renderSpector(BufferBuilder bufferBuilder, Tessellator tessellator, EntityLifeTracker entity)
	{
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
		GlStateManager.color(0F, 1F, 0F);
		float f4 = 0.0625F;
		GlStateManager.translate(f4 * 3, 0, f4 * 3);
		GlStateManager.rotate(entity.ticksExisted, 0, 1, 0);
		GlStateManager.translate(-0.375, -0.25, -0.375);
		
		float f5 = 64f / 256f;
		float f6 = 95f / 256f;
		float f7 = 31 / 256f;
		
		GlStateManager.disableCull();
		this.bindTexture(Localizations.EFFECTSTEXUTRE);
		bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferBuilder.pos(0, 0, 0).tex(0.25, 0).endVertex();
		bufferBuilder.pos(0.75, 0, 0).tex(0.375, 0).endVertex();
		bufferBuilder.pos(0.75, 0, 0.75).tex(0.375, 0.125).endVertex();
		bufferBuilder.pos(0, 0, 0.75).tex(0.25, 0.125).endVertex();
		tessellator.draw();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLifeTracker entity)
	{
		return Localizations.LIFETRACKERTEXTURE;
	}

}
