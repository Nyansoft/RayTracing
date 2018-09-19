package mod.event;

import java.util.List;

import org.lwjgl.opengl.GL11;

import mod.ModBase;
import mod.content.entity.EntityTranslaterStatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=ModBase.MODID, value=Side.CLIENT)
public class EventHandler extends Gui
{
	
	@SubscribeEvent
	public static void draw(RenderGameOverlayEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
	}
	
	
	@SubscribeEvent
	public static void renderWorldLast(RenderWorldLastEvent event)
	{
//		final float u = 0f;
//		final float v = 16f;
//		final float w = 10f;
//		final float h = 7f;
//		
//		float fu = u / 256f;
//		float fv = v / 256f;
//		
//		float ffu = (u + w) / 256f;
//		float ffv = (v + h) / 256f;
//		
//		Minecraft mc = Minecraft.getMinecraft();
//		EntityPlayer player = mc.player;
//		WorldClient worldClient = mc.world;
//		GlStateManager.pushMatrix();
//		GlStateManager.translate(-player.posX, -player.posY, -player.posZ);
//		GlStateManager.disableCull();
//		GlStateManager.disableDepth();
//		mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/icons.png"));
//		List<Entity> entities = worldClient.loadedEntityList;
//		
//		for(Entity entity : entities)
//		{
//			if(entity instanceof EntityTranslaterStatic)
//			{
//				Tessellator tessellator = Tessellator.getInstance();
//				BufferBuilder bufferBuilder = tessellator.getBuffer();
//				bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//				bufferBuilder.pos(entity.posX, entity.posY, entity.posZ).tex(ffu, ffv).endVertex();
//				bufferBuilder.pos(entity.posX, entity.posY + (0.0625f * h), entity.posZ).tex(ffu, fv).endVertex();
//				bufferBuilder.pos(entity.posX + (0.0625f * w), entity.posY + (0.0625f * h), entity.posZ).tex(fu, fv).endVertex();
//				bufferBuilder.pos(entity.posX + (0.0625f * w), entity.posY, entity.posZ).tex(fu, ffv).endVertex();
//				tessellator.draw();
//			}
//		}
//		
//		GlStateManager.popMatrix();
	}
}
