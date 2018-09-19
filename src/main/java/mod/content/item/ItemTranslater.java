package mod.content.item;

import mod.content.entity.EntityTranslater;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemTranslater extends ItemBase
{
	public ItemTranslater(String string) 
	{
		super(string);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote) 
		{
			if(!itemstack.hasTagCompound())
			{
				EntityTranslater translater = new EntityTranslater(worldIn, playerIn);
				translater.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0F, 1.0F, 0F);
				worldIn.spawnEntity(translater);
				itemstack.shrink(1);
			}
			
			else
			{
				NBTTagCompound nbtTagCompound = itemstack.getTagCompound();
				double x = nbtTagCompound.getDouble("posX");
				double y = nbtTagCompound.getDouble("posY");
				double z = nbtTagCompound.getDouble("posZ");
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
}
