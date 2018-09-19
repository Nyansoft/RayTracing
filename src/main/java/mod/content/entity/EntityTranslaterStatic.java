package mod.content.entity;

import java.util.UUID;

import mod.initialization.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityTranslaterStatic extends Entity
{
	public static final DataParameter<EnumFacing> FACING = EntityDataManager.<EnumFacing>createKey(EntityTranslaterStatic.class, DataSerializers.FACING);
	public static final double SIZE = 0.25D;
	public String id;
	
	public EntityTranslaterStatic(World worldIn) 
	{
		super(worldIn);
		this.setSize((float)SIZE, (float)SIZE);
	}
	
	public EntityTranslaterStatic(World worldIn, Vec3d vec, EnumFacing facing2, EntityLivingBase entityLivingBase) 
	{
		super(worldIn);
		this.setSize((float)SIZE, (float)SIZE);
		
		double d = 0;
		
		if(facing2 == EnumFacing.DOWN)
		{
			d = (double)SIZE;
		}
		
		this.setPosition(vec.x, vec.y - d, vec.z);
		float f = 0;
		
		if(facing2 == EnumFacing.UP)
		{
			f = -90;
		}
		
		else if(facing2 == EnumFacing.DOWN)
		{
			f = 90;
		}
		
		this.setRotation(facing2.getHorizontalAngle(), f);
		this.setFacing(facing2);
		
		this.id = String.valueOf(this.rand.nextInt(Integer.MAX_VALUE));
		
		ItemStack stack = new ItemStack(ModItems.TRANSLATER, 1);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setUniqueId("entity", this.getUniqueID());
		stack.setTagCompound(compound);
		
		if(entityLivingBase instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityLivingBase;
			player.addItemStackToInventory(stack);
		}
	}

	@Override
	public void entityInit() 
	{
		this.dataManager.register(FACING, EnumFacing.NORTH);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		setFacing(EnumFacing.getFront(compound.getInteger("facing")));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("facing", getFacing().getIndex());
	}
	
	public void setFacing(EnumFacing facing)
	{
		this.getDataManager().set(FACING, (EnumFacing)facing);
	}
	
	public EnumFacing getFacing()
	{
		return (EnumFacing)this.dataManager.get(FACING);
	}
	
	@Override
	public void onUpdate() 
	{
		BlockPos pos = this.getPosition().offset(getFacing().getOpposite());
		
		if(!world.isRemote)
		{
			if(world.isAirBlock(pos)) this.setDead();
		}
	}
	
	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}
	
	@Override
	public boolean hitByEntity(Entity entityIn) 
	{
		if(!isDead)
		{
			this.setDead();
		}
		
		return true;
		
	}
}
