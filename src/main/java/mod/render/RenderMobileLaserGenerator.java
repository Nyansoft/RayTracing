package mod.render;

import mod.content.entity.EntityMobileLaserGenerator;
import mod.lib.Localizations;
import mod.model.ModelMLG;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMobileLaserGenerator extends Render<EntityMobileLaserGenerator> 
{
	public ModelMLG modelMLG = new ModelMLG();
	
	public RenderMobileLaserGenerator(RenderManager renderManager) 
	{
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityMobileLaserGenerator entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(0D, 0.5D, 0D);
		GlStateManager.rotate(-entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(-entity.rotationPitch, 1, 0, 0);
		GlStateManager.scale(1.5D, 1.5D, 1.5D);
		GlStateManager.scale(1, -1, -1);
		this.bindTexture(Localizations.MLGTEXTURE);
		modelMLG.render();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMobileLaserGenerator entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
