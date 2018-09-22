package mod.gui;

import mod.ModBase;
import mod.container.ContainerTeleporter;
import mod.content.tile.TileEntityTeleporter;
import mod.lib.Localizations;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeleporter extends GuiContainer
{
	
	private TileEntityTeleporter tileEntityTeleporter;
	private InventoryPlayer playerInventory;
	
	public GuiTeleporter(InventoryPlayer par1, TileEntityTeleporter par2) 
	{
		super(new ContainerTeleporter(par1, par2));
		this.tileEntityTeleporter = par2;
		this.playerInventory = par1;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String s = this.tileEntityTeleporter.getName();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Localizations.TELEPORTER_GUI);
		drawTexturedModalRect((width - this.xSize) / 2, (height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
	}
}
