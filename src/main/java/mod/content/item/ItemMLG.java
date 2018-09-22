package mod.content.item;

import mod.content.entity.EntityLifeTracker;
import mod.content.entity.EntityMobileLaserGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMLG extends ItemBase
{
	public ItemMLG(String string) 
	{
		super(string);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			EntityMobileLaserGenerator entity = new EntityMobileLaserGenerator(worldIn, pos.getX() + facing.getFrontOffsetX() + 0.5D, pos.getY() + facing.getFrontOffsetY() + 0.5D, pos.getZ() + facing.getFrontOffsetZ() + 0.5D);
			worldIn.spawnEntity(entity);
			
		}
		
		return EnumActionResult.SUCCESS;
	}
}
