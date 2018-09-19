package mod;

import mod.handler.ModGuiHandler;
import mod.initialization.ModBlocks;
import mod.initialization.ModEntities;
import mod.initialization.ModItems;
import mod.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
