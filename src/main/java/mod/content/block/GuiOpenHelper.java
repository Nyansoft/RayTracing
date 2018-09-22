package mod.content.block;

import mod.ModBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiOpenHelper
{
	public static final int TELEPORTER = 0;
	public static final int LIFETRACKER = 1;
	
	public static void openGui(EntityPlayer playerIn, World worldIn, BlockPos pos, int id)
	{
		playerIn.openGui(ModBase.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
	}
}
