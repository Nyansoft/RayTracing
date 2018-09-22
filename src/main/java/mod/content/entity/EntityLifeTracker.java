package mod.content.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import mod.content.block.GuiOpenHelper;
import mod.gui.GuiLifeTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityLifeTracker extends Entity
{
	public static final DataParameter<Rotations> ROTATION = EntityDataManager.<Rotations>createKey(EntityLifeTracker.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Boolean> ACTIVE = EntityDataManager.<Boolean>createKey(EntityLifeTracker.class, DataSerializers.BOOLEAN);
	
	public static final DataParameter<Boolean> HEALTARGETS = EntityDataManager.<Boolean>createKey(EntityLifeTracker.class, DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> TARGETPLAYER = EntityDataManager.<Boolean>createKey(EntityLifeTracker.class, DataSerializers.BOOLEAN);
	
	public EntityLivingBase trackingEntity;
	
	public EntityLifeTracker(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityLifeTracker(World worldIn, double x, double y, double z) 
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.setPositionAndUpdate(x, y, z);
//		this.setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		List<EntityLivingBase> openList = world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(5D));
		List<EntityLivingBase> closedList = Lists.<EntityLivingBase>newArrayList();
		
		if(openList != null)
		{
			if(!openList.isEmpty())
			{
				for(EntityLivingBase entity : openList)
				{
					if(this.canEntityBeSeen(this, entity) && !entity.isDead)
					{
						boolean targetPlayer = isTargetingPlayers();
						
						if(isHealing())
						{
							if(entity.getHealth() < entity.getMaxHealth())
							{
								if(targetPlayer)
								{
									if(entity instanceof EntityPlayer)
									{
										closedList.clear();
										closedList.add(entity);
									}
								}
								
								else
								{
									if(entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer))
									{
										closedList.clear();
										closedList.add(entity);
									}
								}
							}
						}
						
						else
						{
							if(targetPlayer)
							{
								if(entity instanceof EntityPlayer)
								{
									closedList.clear();
									closedList.add(entity);
								}
							}
							
							else
							{
								if(entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer))
								{
									closedList.clear();
									closedList.add(entity);
								}
							}
						}
					}
				}
			}
		}
		
		if(!closedList.isEmpty())
		{
			EntityLivingBase player = closedList.get(0);
			
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
				
				if(isHealing())
				{
					player.heal(0.05F);
				}
				else
				{
					player.attackEntityFrom(DamageSource.MAGIC, 0.5F);
				}
			}
			
			this.trackingEntity = player;
			this.activate();
		}
		
		else
		{
			this.setRotation(getInitialRotation().getX(), getInitialRotation().getY());
			this.desactivate();
			this.trackingEntity = null;
		}
		
		System.out.println(isTargetingPlayers());
	}
	
	public boolean canEntityBeSeen(Entity source, Entity target)
    {
        return this.world.rayTraceBlocks(new Vec3d(source.posX, source.posY + (double)source.getEyeHeight(), source.posZ), new Vec3d(target.posX, target.posY + (double)target.getEyeHeight(), target.posZ), false, true, false) == null;
    }

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(ROTATION, new Rotations(0, 0, 0));
		this.dataManager.register(ACTIVE, false);
		
		this.dataManager.register(HEALTARGETS, true);
		this.dataManager.register(TARGETPLAYER, true);
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
			setDataParameter(ACTIVE, (boolean)compound.getBoolean("isActive"));
		}
		
		if(compound.hasKey("healTargets"))
		{
			setDataParameter(HEALTARGETS, (boolean)compound.getBoolean("healTargets"));
		}
		
		if(compound.hasKey("targetPlayers"))
		{
			setDataParameter(TARGETPLAYER, (boolean)compound.getBoolean("targetPlayers"));
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
		compound.setBoolean("healTargets", this.isHealing());
		compound.setBoolean("targetPlayers", this.isTargetingPlayers());
	}
	
	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}
	
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) 
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiLifeTracker(this));
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public boolean hitByEntity(Entity entityIn) 
	{
		if(entityIn instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer)entityIn;
			this.setDead();
			world.playSound(entityPlayer, entityPlayer.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1F, entityPlayer.getRNG().nextFloat());
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return this.getEntityBoundingBox().grow(5);
	}
	
	/**
	 * Activate the tracker
	 */
	public void activate()
	{
		setDataParameter(ACTIVE, true);
	}
	
	/**
	 * Desactivate the tracker
	 */
	public void desactivate()
	{
		setDataParameter(ACTIVE, false);
	}
	
	public boolean isActive()
	{
		return (boolean)this.getDataParameter(ACTIVE);
	}
	/**
	 * Sets the initial rotation when player places the tracker
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setInitialRotation(float x, float y, float z)
	{
		setDataParameter(ROTATION, new Rotations(x, y, z));
	}
	
	public Rotations getInitialRotation()
	{
		return (Rotations)this.getDataParameter(ROTATION);
	}
	
	public boolean isHealing()
	{
		return (boolean)this.getDataParameter(HEALTARGETS);
	}
	
	/**
	 * Heal the selected targets.
	 */
	public void healTargets()
	{
		setDataParameter(HEALTARGETS, true);
	}
	
	/**
	 * Cause damage on selected targets.
	 */
	public void hurtTargets()
	{
		setDataParameter(HEALTARGETS, false);
	}
	
	/**
	 * Selected target: Only players.
	 */
	public void targetPlayers()
	{
		getDataManager().set(TARGETPLAYER, true);
	}
	
	/**
	 * Selected target: Entity which are instaced of EntityLivingBase. Checks onUpdate().
	 */
	public void targetNonPlayers()
	{
		getDataManager().set(TARGETPLAYER, false);
	}
	
	public boolean isTargetingPlayers()
	{
		return (boolean)getDataParameter(TARGETPLAYER);
	}
	
	public void setDataParameter(DataParameter par1, Object par2)
	{
		this.getDataManager().set(par1, par2);
	}
	
	public Object getDataParameter(DataParameter par1)
	{
		return this.getDataManager().get(par1);
	}
}
