package mod.content.block;

import mod.ModBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockBaseContainer extends BlockContainer
{
	protected BlockBaseContainer(Material materialIn, String name) 
	{
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

}
