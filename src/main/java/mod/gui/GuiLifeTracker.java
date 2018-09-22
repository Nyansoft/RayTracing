package mod.gui;

import java.io.IOException;

import mod.ModBase;
import mod.SendLifeTrackerMessage;
import mod.content.entity.EntityLifeTracker;
import mod.lib.Localizations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class GuiLifeTracker extends GuiScreen
{
	private static int widgetWidth = 176;
	private static int widgetHeight = 105;
	public EntityLifeTracker entityLifeTracker;
	
	public Button healButton;
	public Button selectPlayersButton;
	public Button selectNonPlayersButton;
	
	public GuiLifeTracker(EntityLifeTracker entityLifeTracker) 
	{
		this.entityLifeTracker = entityLifeTracker;
	}

	@Override
	public void initGui() 
	{
		int x = (width - widgetWidth) / 2;
		int y = (height - widgetHeight) / 2;
		
		healButton = new Button(0, x + 9, y + 9, EnumButtonType.HEAL);
		selectPlayersButton = new Button(1, x + 52, y + 40, EnumButtonType.PLAYERS);
		selectNonPlayersButton = new Button(2, x + 77, y + 40, EnumButtonType.NONPLAYERS);
		
		this.buttonList.clear();
		this.buttonList.add(healButton);
		this.buttonList.add(selectPlayersButton);
		this.buttonList.add(selectNonPlayersButton);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		int x = (width - widgetWidth) / 2;
		int y = (height - widgetHeight) / 2;
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		this.mc.getTextureManager().bindTexture(Localizations.LIFETRACKER_GUI);
		drawTexturedModalRect(x, y, 0, 0, widgetWidth, widgetHeight);
		
		String str;
		
		if(entityLifeTracker.isHealing())
		{
			
			str = "Healing 1 HP per second";
		}
		
		else
		{
			str = "Causing 10 points of damage per second";
		}
		
		fontRenderer.drawSplitString(str, x + 36, y + 12, 126, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		if(button instanceof Button)
		{
			Button button2 = (Button)button;
			ModBase.instance.INSTANCE.sendToServer(new SendLifeTrackerMessage(entityLifeTracker, button2.buttonType.index));
		}
		
	}
	
	@Override
	public void updateScreen() 
	{
		healButton.buttonType = entityLifeTracker.isHealing() ? EnumButtonType.HEAL : EnumButtonType.HURT;
	}
	
	public class Button extends GuiButton
	{
		public static final int sizeX = 22;
		public static final int sizeY = 22;
		private EnumButtonType buttonType;
		
		public Button(int buttonId, int x, int y, EnumButtonType type) 
		{
			super(buttonId, x, y, sizeX, sizeY, "");
			this.buttonType = type;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			int x1 = 0;
			int y1 = 105;
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			mc.getTextureManager().bindTexture(Localizations.LIFETRACKER_GUI);
			
			if(hovered && enabled)
			{
				x1 += sizeX * 3;
			}
			
			if(!enabled)
			{
				x1 += sizeX * 2;
			}
			
			else
			{
				x1 += sizeX * 0;
			}
			
			this.drawTexturedModalRect(this.x, this.y, x1, y1, this.sizeX, this.sizeY);
			
			int x2 = this.sizeX * this.buttonType.index;
			int y2 = y1 + sizeY;
			
			this.drawTexturedModalRect(this.x, this.y, x2, y2, this.sizeX, this.sizeY);
		}
	}
	
	public enum EnumButtonType
	{
		HEAL(0), HURT(1), PLAYERS(2), NONPLAYERS(3);
		
		public int index;
		
		private EnumButtonType(int par1) 
		{
			this.index = par1;
		}
	}
}
