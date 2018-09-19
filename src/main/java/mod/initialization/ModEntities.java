package mod.initialization;

import mod.ModBase;
import mod.content.entity.EntityLifeTracker;
import mod.content.entity.EntityTranslater;
import mod.content.entity.EntityTranslaterStatic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	private int id = 0;
	
	public void registerAll() 
	{
		register(EntityTranslater.class, "translater");
		register(EntityTranslaterStatic.class, "translaterStatic");
		register(EntityLifeTracker.class, "lifeTracker");
	}
	
	private void register(Class cls, String name)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(ModBase.MODID, name), cls, ModBase.MODID + ":" + name, id++, ModBase.instance, 20, 1, true);
	}
}
