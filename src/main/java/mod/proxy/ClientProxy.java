package mod.proxy;

import mod.content.entity.EntityLifeTracker;
import mod.content.entity.EntityTranslater;
import mod.content.entity.EntityTranslaterStatic;
import mod.content.tile.TileEntityTeleporter;
import mod.render.RenderLifeTracker;
import mod.render.RenderTranslater;
import mod.render.TileEntityTeleporterRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy{

	@Override
	public void preInit()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeleporter.class, new TileEntityTeleporterRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTranslater.class, new IRenderFactory<EntityTranslater>() {

			@Override
			public Render<? super EntityTranslater> createRenderFor(RenderManager manager) 
			{
				return new RenderTranslater(manager, false);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTranslaterStatic.class, new IRenderFactory<EntityTranslaterStatic>() {

			@Override
			public Render<? super EntityTranslaterStatic> createRenderFor(RenderManager manager) 
			{
				return new RenderTranslater(manager, true);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLifeTracker.class, new IRenderFactory<EntityLifeTracker>() {

			@Override
			public Render<? super EntityLifeTracker> createRenderFor(RenderManager manager) 
			{
				return new RenderLifeTracker(manager);
			}
		});
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

}
