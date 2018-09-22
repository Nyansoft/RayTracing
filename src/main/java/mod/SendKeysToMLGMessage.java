package mod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SendKeysToMLGMessage implements IMessage
{
	public SendKeysToMLGMessage() {}
	
	public int entityID;
	public boolean spacePressed;
	public boolean aPressed;
	public boolean sPressed;
	public boolean dPressed;
	public boolean wPressed;
	
	public SendKeysToMLGMessage(int entity, boolean space, boolean a, boolean s, boolean d, boolean w)
	{
		this.entityID = entity;
		this.spacePressed = space;
		this.aPressed = a;
		this.sPressed = s;
		this.dPressed = d;
		this.wPressed = w;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.entityID = buf.readInt();
		this.spacePressed = buf.readBoolean();
		this.aPressed = buf.readBoolean();
		this.sPressed = buf.readBoolean();
		this.dPressed = buf.readBoolean();
		this.wPressed = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(entityID);
		buf.writeBoolean(spacePressed);
		buf.writeBoolean(aPressed);
		buf.writeBoolean(sPressed);
		buf.writeBoolean(dPressed);
		buf.writeBoolean(wPressed);
	}

}
