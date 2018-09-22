package mod;

import org.lwjgl.input.Keyboard;

import mod.handler.ModGuiHandler;
import mod.initialization.ModBlocks;
import mod.initialization.ModEntities;
import mod.initialization.ModItems;
import mod.proxy.IProxy;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ModBase.MODID, name = ModBase.NAME, version = ModBase.VERSION)
public class ModBase
{
    public static final String MODID = "raytracing";
    public static final String NAME = "Raytracing";
    public static final String AUTHOR = "Nyansoft";
    public static final String VERSION = "0.1";
    
    @Instance(ModBase.MODID)
    public static ModBase instance;
    
    @SidedProxy(clientSide = "mod.proxy.ClientProxy", serverSide = "mod.proxy.ServerProxy")
    public static IProxy proxy;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModBase.MODID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        new ModItems().registerAll();
        new ModBlocks().registerAll();
        new ModEntities().registerAll();
        
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
    	INSTANCE.registerMessage(SendLifeTrackerMessageHandler.class, SendLifeTrackerMessage.class, 0, Side.SERVER);
    	
    	INSTANCE.registerMessage(SendKeysToMLGMessageHandler.class, SendKeysToMLGMessage.class, 1, Side.SERVER);
    	proxy.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit();
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
    	
    }
}
