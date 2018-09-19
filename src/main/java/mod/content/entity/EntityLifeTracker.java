package mod.content.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.world.World;

public class EntityLifeTracker extends Entity
{
	public static final DataParameter<Rotations> ROTATION = EntityDataManager.<Rotations>createKey(EntityLifeTracker.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Boolean> ACTIVE = EntityDataManager.<Boolean>createKey(EntityLifeTracker.class, DataSerializers.BOOLEAN);
	
	public EntityLifeTracker(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityLifeTracker(World worldIn, double x, double y, double z) 
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		World world = this.world;
		List<EntityPlayer> openList = world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(5D), new Predicate<EntityPlayer>() {

			@Override
			public boolean apply(EntityPlayer input)
			{
				return !input.isDead && input.getHealth() < input.getMaxHealth() && !input.isCreative() && !input.isSpectator();
			}
		});
		
		List<EntityPlayer> closedList = Lists.<EntityPlayer>newArrayList();
		
		if(openList != null)
		{
			if(!openList.isEmpty())
			{
				for(EntityPlayer player : openList)
				{
					if(player.canEntityBeSeen(this))
						closedList.add(player);
				}
			}
		}
		
		if(!closedList.isEmpty())
		{
			EntityPlayer player = closedList.get(0);
			
			if(!world.isRemote) 
			{
				double d0 = this.posX - player.posX;
	            double d1 = this.posY - (player.posY + ((double)player.getEyeHeight() / 2));
	            double d2 = this.posZ - player.posZ;
	            double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	            float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) + 90.0F;
	            float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
	            
				this.setRotation(f, -f1);
				float lastF = f;
				float lastF1 = -f1;
				player.setHealth(player.getHealth() + 0.05F);
			}
			
			this.setActiveState(true);
		}
		
		else
		{
			this.setRotation(getInitialRotation().getX(), getInitialRotation().getY());
			this.setActiveState(false);
		}
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(ROTATION, new Rotations(0, 0, 0));
		this.dataManager.register(ACTIVE, false);
	}
	
	public void setActiveState(boolean flag)
	{
		this.getDataManager().set(ACTIVE, flag);
	}
	
	public boolean isActive()
	{
		return (boolean)this.getDataManager().get(ACTIVE);
	}
	
	public void setInitialRotation(float x, float y, float z)
	{
		this.getDataManager().set(ROTATION, new Rotations(x, y, z));
	}
	
	public Rotations getInitialRotation()
	{
		return (Rotations)this.getDataManager().get(ROTATION);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		if(compound.hasKey("XRot") && compound.hasKey("YRot") && compound.hasKey("ZRot"))
		{
			float x = compound.getFloat("XRot");
			float y = compound.getFloat("YRot");
			float z = compound.getFloat("ZRot");
			
			Rotations rotation = new Rotations(x, y, z);
			setInitialRotation(x, y, z);
		}
		
		if(compound.hasKey("isActive"))
		{
			setActiveState(compound.getBoolean("isActive"));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		Rotations rotations = getInitialRotation();
		compound.setFloat("XRot", rotations.getX());
		compound.setFloat("YRot", rotations.getY());
		compound.setFloat("ZRot", rotations.getZ());
		compound.setBoolean("isActive", this.isActive());
	}
}
