package mod.content.item;

import mod.ModBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
	public ItemBase(String string)
	{
		setUnlocalizedName(ModBase.MODID + ":" + string);
		setRegistryName(ModBase.MODID, string);
		this.setCreativeTab(CreativeTabs.REDSTONE);
	}
}
