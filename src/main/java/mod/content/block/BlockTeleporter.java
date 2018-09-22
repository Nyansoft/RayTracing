package mod.content.block;

import mod.ModBase;
import mod.content.tile.TileEntityTeleporter;
import mod.initialization.ModBlocks;
import mod.initialization.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockBaseContainer
{
	public BlockTeleporter() 
	{
		super(Material.IRON, "teleporter");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityTeleporter();
	}
	
	 public boolean isOpaqueCube(IBlockState state)
	 {
		 return false;
	 }

	 public boolean isFullCube(IBlockState state)
	 {
		 return false;
	 }
	 
	 public EnumBlockRenderType getRenderType(IBlockState state)
	 {
		 return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	 }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return new AxisAlignedBB(0, 0, 0, 1, (0.0625f * 6) + 0.0275F, 1);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityTeleporter)
        {
        	ItemStack holdingItem = playerIn.getHeldItem(hand);
    		
    		if(holdingItem.getItem() == ModItems.TRANSFERCORE)
    		{
    			NBTTagCompound compound = new NBTTagCompound();
    			compound.setInteger("X", pos.getX());
    			compound.setInteger("Y", pos.getY());
    			compound.setInteger("Z", pos.getZ());
    			holdingItem.setTagCompound(compound);
    		}
    		
    		else
    		{
    			GuiOpenHelper.openGui(playerIn, worldIn, pos, GuiOpenHelper.TELEPORTER);
    		}
        }

        return true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityTeleporter)
            {
                ((TileEntityTeleporter)tileentity).setCustomName(stack.getDisplayName());
            }
        }
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityTeleporter)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityTeleporter)tileentity);
        }

        super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) 
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) 
	{
		boolean flag0 = worldIn.isAirBlock(pos.down());
		if(flag0)
		{
			state.getBlock().dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}
}
