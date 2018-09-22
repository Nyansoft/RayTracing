package mod;

import mod.content.entity.EntityLifeTracker;
import mod.gui.GuiLifeTracker.EnumButtonType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SendLifeTrackerMessageHandler implements IMessageHandler<SendLifeTrackerMessage, IMessage>
{
	@Override
	public IMessage onMessage(SendLifeTrackerMessage message, MessageContext ctx) 
	{
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    WorldServer worldServer = serverPlayer.getServerWorld();
	    
	    serverPlayer.getServerWorld().addScheduledTask(() -> 
	    {
	    	for(Entity entity : worldServer.getLoadedEntityList())
		    {
		    	if(entity instanceof EntityLifeTracker)
		    	{
		    		EntityLifeTracker entityLifeTracker = (EntityLifeTracker)entity;
		    		
		    		if(entityLifeTracker.getEntityId() == message.entityLifeTrackerId)
			    	{
		    			if(message.buttonId == EnumButtonType.HURT.index)
		    			{
		    				entityLifeTracker.healTargets();
		    			}
		    			
		    			if(message.buttonId == EnumButtonType.HEAL.index)
		    			{
		    				entityLifeTracker.hurtTargets();
		    			}
		    			
		    			if(message.buttonId == EnumButtonType.PLAYERS.index)
		    			{
		    				entityLifeTracker.targetPlayers();
		    			}
		    			
		    			if(message.buttonId == EnumButtonType.NONPLAYERS.index)
		    			{
		    				entityLifeTracker.targetNonPlayers();
		    			}
			    	}
		    	}
		    }
	    });
	    // No response packet
	    return null;
	}
}