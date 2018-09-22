package mod.content.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import mod.content.entity.EntityLifeTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class ItemTracker extends ItemBase
{
	public ItemTracker(String string)
	{
		super(string);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			EntityLifeTracker entityLifeTracker = new EntityLifeTracker(worldIn, pos.getX() + facing.getFrontOffsetX() + 0.5D, pos.getY() + facing.getFrontOffsetY() + 0.5D, pos.getZ() + facing.getFrontOffsetZ() + 0.5D);
			entityLifeTracker.setInitialRotation(player.rotationYaw - 180, -player.rotationPitch, 0);
			worldIn.spawnEntity(entityLifeTracker);
			
		}
		
		worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.BLOCKS, 1F, player.getRNG().nextFloat());
		
		return EnumActionResult.SUCCESS;
	}
}
