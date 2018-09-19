package mod.content.tile;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import mod.ModBase;
import mod.container.ContainerTeleporter;
import mod.content.block.BlockTeleporter;
import mod.initialization.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTeleporter extends TileEntityLockable implements ITickable, IInventory
{
	private NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private String customName;
	
	public int teleportingTime;
	public float ascendAnimationValue;
	public float rotationValue;
	public float rotationMultiplierValue;
	
	public final int FINAL_ASCEND_ANIMATION_VALUE = 5;
	
	@Override
	public void update() 
	{
		boolean flag = false;
		boolean isAscended = ascendAnimationValue == FINAL_ASCEND_ANIMATION_VALUE;
		
		if(isPowered())
		{
			if(ascendAnimationValue < FINAL_ASCEND_ANIMATION_VALUE)
			{
				ascendAnimationValue++;
			}
		}
		
		else
		{
			if(ascendAnimationValue > 0)
			{
				ascendAnimationValue--;
			}
		}
		
		if(isAscended)
		{
			BlockPos pos = getPositionInItemStackNBT();
			
			if(hasTransferCore())
			{
				List<EntityLivingBase> list = getList();
				
				if(!list.isEmpty())
				{
					Iterator<EntityLivingBase> it = list.iterator();
					
					if(this.rotationMultiplierValue < 1.5F)
					{
						this.rotationMultiplierValue += 0.025F;
					}
					
					flag = true;
					
					if(this.teleportingTime < 20 * 3)
					{
						this.teleportingTime++;
					}
					
					if(this.teleportingTime >= 20 * 3)
					{
						while(it.hasNext())
						{
							EntityLivingBase entityLivingBase = it.next();
							
							if(getPositionInItemStackNBT() != null)
							{
								TileEntity tileEntity = this.world.getTileEntity(getPositionInItemStackNBT());
								
								if(tileEntity instanceof TileEntityTeleporter)
								{
									TileEntityTeleporter teleporter = (TileEntityTeleporter)tileEntity;
									
									if(teleporter != null && teleporter.isPowered())
									{
										entityLivingBase.setPosition(getPositionInItemStackNBT().getX() + 0.5D, getPositionInItemStackNBT().getY() + 0.5D, getPositionInItemStackNBT().getZ() + 0.5D);
									}
								}
							}
						}
					}
				}
				
				else
				{
					if(this.rotationMultiplierValue < 0.1F)
					{
						this.rotationMultiplierValue += 0.025F;
					}
					
					else if(this.rotationMultiplierValue > 0)
					{
						this.rotationMultiplierValue -= 0.025F;
					}
				}
			}
		}
		
		else
		{
			if(rotationValue > 0 && rotationMultiplierValue > 0) 
			{
				this.rotationMultiplierValue -= 0.025F;
			}
		}
		
		this.rotationValue += this.rotationMultiplierValue;
		
		if(this.teleportingTime > 0 && !flag)
		{
			this.teleportingTime--;
		}
	}
	
	public boolean isPowered()
	{
		return this.world.isBlockPowered(this.getPos());
	}
	
	private List getList()
	{
		List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, this.getRenderBoundingBox().expand(0, 1, 0));
		return list;
	}
	
	private BlockPos getPositionInItemStackNBT()
	{
		ItemStack stack = this.getStack();
		
		if(hasTransferCore())
		{
			if(stack.hasTagCompound())
			{
				NBTTagCompound nbt = stack.getTagCompound();
				int x = nbt.getInteger("X");
				int y = nbt.getInteger("Y");
				int z = nbt.getInteger("Z");
				
				return new BlockPos(x, y, z);
			}
			
			else
			{
				return null;
			}
		}
		
		else
		{
			return null;
		}
	}
	
	private ItemStack getStack()
	{
		return this.itemStacks.get(0);
	}
	
	public boolean hasTransferCore()
	{
		return this.getStack() != null && this.getStack().getItem() == ModItems.TRANSFERCORE;
	}

	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container." + ModBase.MODID + ".teleporter";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public int getSizeInventory() 
	{
		return 1;
	}

	@Override
	public boolean isEmpty() 
	{
		for (ItemStack itemstack : this.itemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }
		
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return this.itemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.itemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		ItemStack itemstack = this.itemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.itemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() 
	{
		this.itemStacks.clear();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);
        compound.setInteger("teleportingTime", this.teleportingTime);
        compound.setFloat("rotationValue", this.rotationValue);
        compound.setFloat("ascendAnimationValue", this.ascendAnimationValue);
        compound.setFloat("rotationMultiplierValue", this.rotationMultiplierValue);
        
        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
        this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
        this.teleportingTime = compound.getInteger("teleportingTime");
        this.ascendAnimationValue = compound.getFloat("ascendAnimationValue");
        this.rotationValue = compound.getFloat("rotationValue");
        this.rotationMultiplierValue = compound.getFloat("rotationMultiplierValue");
        
        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
	}
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
		return new ContainerTeleporter(playerInventory, this);
	}

	@Override
	public String getGuiID() 
	{
		return ModBase.MODID + ":teleporter";
	}
}
