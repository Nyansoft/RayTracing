package mod.content.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMobileLaserGenerator extends Entity
{
	private static final DataParameter<Boolean> ISASCENDING = EntityDataManager.createKey(EntityBoat.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ISFORWARD = EntityDataManager.createKey(EntityBoat.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ISBACKWARD = EntityDataManager.createKey(EntityBoat.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ISLEFTWARD = EntityDataManager.createKey(EntityBoat.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ISRIGHTWARD = EntityDataManager.createKey(EntityBoat.class, DataSerializers.BOOLEAN);
	
	private static final DataParameter<Float>[] LOOKS = new DataParameter[] {EntityDataManager.createKey(EntityBoat.class, DataSerializers.FLOAT), EntityDataManager.createKey(EntityBoat.class, DataSerializers.FLOAT)};
	
	public EntityMobileLaserGenerator(World worldIn) 
	{
		super(worldIn);
		this.setSize(1.5F, 1.5F);
		// TODO Auto-generated constructor stub
	}
	
	public EntityMobileLaserGenerator(World worldIn, double x, double y, double z) {
		super(worldIn);
		this.setSize(1.5F, 1.5F);
		this.setPositionAndUpdate(x, y, z);
	}

	@Override
	protected void entityInit() 
	{
		this.getDataManager().register(LOOKS[0], 0.0F);
		this.getDataManager().register(LOOKS[1], 0.0F);
		this.getDataManager().register(ISASCENDING, false);
		this.getDataManager().register(ISFORWARD, false);
		this.getDataManager().register(ISBACKWARD, false);
		this.getDataManager().register(ISLEFTWARD, false);
		this.getDataManager().register(ISRIGHTWARD, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	public void onInputUpdate(boolean spacePressed, boolean aPressed, boolean sPressed, boolean dPressed, boolean wPressed)
	{
		this.getDataManager().set(ISASCENDING, spacePressed);
		this.getDataManager().set(ISFORWARD, aPressed);
		this.getDataManager().set(ISBACKWARD, sPressed);
		this.getDataManager().set(ISRIGHTWARD, dPressed);
		this.getDataManager().set(ISLEFTWARD, wPressed);
	}
	
	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}
	
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) 
	{
		player.startRiding(this);
		return EnumActionResult.SUCCESS;
	}
}
