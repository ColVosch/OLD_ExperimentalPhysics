package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketSyncRefiner extends PacketSyncTileEntity implements IMessage
{
	public int currentProcessingTime;
	public boolean lit;
	
	public PacketSyncRefiner() {}
	
	public PacketSyncRefiner(int currentProcessingTime, boolean lit, int xCoord, int yCoord, int zCoord)
	{
		super(xCoord, yCoord, zCoord);
		this.currentProcessingTime = currentProcessingTime;
		this.lit = lit;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		super.toBytes(buf);
		buf.writeInt(currentProcessingTime);
		buf.writeBoolean(lit);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		super.fromBytes(buf);
		currentProcessingTime = buf.readInt();
		lit = buf.readBoolean();
	}

}
