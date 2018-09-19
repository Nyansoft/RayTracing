package mod.content.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityTranslater extends EntityThrowable
{
	public EntityTranslater(World worldIn)
	{
		super(worldIn);
	}

	public EntityTranslater(World world, EntityLivingBase thrower) 
	{
		super(world, thrower);
	}
	
	public EntityTranslater(World world, double x, double y, double z) 
	{
		super(world, x, y, z);
	}
	
	@Override
	protected void entityInit() 
	{
		
	}

	@Override
	protected void onImpact(RayTraceResult result) 
	{
		World world = this.getEntityWorld();
		
		if(!world.isRemote)
		{
			BlockPos pos = result.getBlockPos();
			Vec3d vec = result.hitVec;
			EnumFacing facing = result.sideHit;
			RayTraceResult.Type type = result.typeOfHit;
			
			if(type == Type.BLOCK)
			{
				EntityTranslaterStatic translaterStatic = new EntityTranslaterStatic(world, new Vec3d(vec.x + ((float)facing.getFrontOffsetX() * (0.25D / 2)), vec.y, vec.z + ((float)facing.getFrontOffsetZ() * (0.25D / 2))), facing, this.getThrower());
				world.spawnEntity(translaterStatic);
				this.setDead();				
			}
		}
		
	}
}
