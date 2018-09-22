package mod;

import io.netty.buffer.ByteBuf;
import mod.content.entity.EntityLifeTracker;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SendLifeTrackerMessage implements IMessage
{
	public SendLifeTrackerMessage() {}
	
	public int entityLifeTrackerId;
	public int buttonId;
	
	public SendLifeTrackerMessage(EntityLifeTracker entityLifeTracker, int id)
	{
		this.entityLifeTrackerId = entityLifeTracker.getEntityId();
		this.buttonId = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entityLifeTrackerId = buf.readInt();
		buttonId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(entityLifeTrackerId);
		buf.writeInt(buttonId);
	}
	
}
