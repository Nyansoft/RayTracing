package mod.initialization;

import mod.content.item.ItemTracker;
import mod.content.item.ItemTransferCore;
import mod.content.item.ItemTranslater;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems 
{
	public static final Item TRANSLATER = new ItemTranslater("translater");
	public static final Item TRANSFERCORE = new ItemTransferCore("remoteTransfer").setMaxStackSize(1);
	public static final Item TRACKER = new ItemTracker("tracker");
	
	public void registerAll()
	{
		registerItem(TRANSLATER);
		registerItem(TRANSFERCORE);
		registerItem(TRACKER);
	}
	
	private void registerItem(Item item)
	{
		ForgeRegistries.ITEMS.register(item);
	}
}
