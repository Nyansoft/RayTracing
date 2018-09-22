package mod;

import mod.content.entity.EntityLifeTracker;
import mod.content.entity.EntityMobileLaserGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SendKeysToMLGMessageHandler implements IMessageHandler<SendKeysToMLGMessage, IMessage>
{
	@Override
	public IMessage onMessage(SendKeysToMLGMessage message, MessageContext ctx) 
	{
		EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    WorldServer worldServer = serverPlayer.getServerWorld();
	    
	    serverPlayer.getServerWorld().addScheduledTask(() -> 
	    {
	    	Entity entity = serverPlayer.getLowestRidingEntity();
	    	
	    	if(entity instanceof EntityMobileLaserGenerator)
	    	{
	    		EntityMobileLaserGenerator entityMobileLaserGenerator = (EntityMobileLaserGenerator)entity;
	    		entityMobileLaserGenerator.rotationPitch = serverPlayer.rotationPitch;
	    		entityMobileLaserGenerator.rotationYaw = serverPlayer.rotationYaw;
	    	}
	    	
	    	
	    	
	    });
	    
		return null;
	}

}
