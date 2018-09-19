package mod.initialization;

import mod.ModBase;
import mod.content.block.BlockTeleporter;
import mod.content.tile.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class ModBlocks 
{
	public static final Block TELEPORTER = new BlockTeleporter().setCreativeTab(CreativeTabs.REDSTONE);
	
	public void registerAll() 
	{
		registerBlockContainer(TELEPORTER, TileEntityTeleporter.class);
	}

	private void registerBlock(Block block) 
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	private void registerBlockContainer(Block block, Class tile) {
		registerBlock(block);
		GameRegistry.registerTileEntity(tile, new ResourceLocation(ModBase.MODID, block.getUnlocalizedName()));
	}
	
//	public void createItemModelMesher(Block block)
//	{
//		Item item = Item.getItemFromBlock(block);
//		ModelResourceLocation model = new ModelResourceLocation(ModBase.MODID + ":" + block.getUnlocalizedName(), "inventory");
//		ModelLoader.registerItemVariants(item, model);
//		mesher.register(item, 0, model);
//	}
}
