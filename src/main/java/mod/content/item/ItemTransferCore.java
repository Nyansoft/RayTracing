package mod.content.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTransferCore extends ItemBase 
{
	public ItemTransferCore(String string) 
	{
		super(string);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return stack.hasTagCompound();
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if(stack.hasTagCompound())
		{
			tooltip.add("X: " + stack.getTagCompound().getInteger("X"));
			tooltip.add("Y: " + stack.getTagCompound().getInteger("Y"));
			tooltip.add("Z: " + stack.getTagCompound().getInteger("Z"));
		}
	}
}
