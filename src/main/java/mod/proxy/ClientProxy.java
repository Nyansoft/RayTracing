package mod.proxy;

import org.lwjgl.input.Keyboard;

import mod.ModBase;
import mod.content.entity.EntityLifeTracker;
import mod.content.entity.EntityMobileLaserGenerator;
import mod.content.entity.EntityTranslater;
import mod.content.entity.EntityTranslaterStatic;
import mod.content.tile.TileEntityTeleporter;
import mod.render.RenderLifeTracker;
import mod.render.RenderMobileLaserGenerator;
import mod.render.RenderTranslater;
import mod.render.TileEntityTeleporterRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy
{
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
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMobileLaserGenerator.class, new IRenderFactory<EntityMobileLaserGenerator>() {

			@Override
			public Render<? super EntityMobileLaserGenerator> createRenderFor(RenderManager manager) 
			{
				return new RenderMobileLaserGenerator(manager);
			}
		});
	}
	
	public static final KeyBinding SPACE = new KeyBinding("Jump in MLG", Keyboard.KEY_SPACE, ModBase.NAME);
	public static final KeyBinding A = new KeyBinding(ModBase.NAME, Keyboard.KEY_A, ModBase.NAME);
	public static final KeyBinding S = new KeyBinding(ModBase.NAME, Keyboard.KEY_S, ModBase.NAME);
	public static final KeyBinding D = new KeyBinding(ModBase.NAME, Keyboard.KEY_D, ModBase.NAME);
	public static final KeyBinding W = new KeyBinding(ModBase.NAME, Keyboard.KEY_W, ModBase.NAME);

	@Override
	public void init() 
	{
		ClientRegistry.registerKeyBinding(SPACE);
		ClientRegistry.registerKeyBinding(A);
		ClientRegistry.registerKeyBinding(S);
		ClientRegistry.registerKeyBinding(D);
		ClientRegistry.registerKeyBinding(W);
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

}
