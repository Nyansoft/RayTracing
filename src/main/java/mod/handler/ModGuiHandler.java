package mod.handler;

import mod.container.ContainerTeleporter;
import mod.content.tile.TileEntityTeleporter;
import mod.gui.GuiTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
		case 0: return new ContainerTeleporter(player.inventory, (TileEntityTeleporter) world.getTileEntity(new BlockPos(x, y, z)));
		default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
		case 0: return new GuiTeleporter(player.inventory, (TileEntityTeleporter) world.getTileEntity(new BlockPos(x, y, z)));
		default: return null;
		}
	}

}
