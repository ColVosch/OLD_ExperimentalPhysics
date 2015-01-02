package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketLoadInteractor implements IMessage
{
	public static final int INDEX_REFINER_X = 0;
	public static final int INDEX_REFINER_Y = 1;
	public static final int INDEX_REFINER_Z = 2;
	public static final int INDEX_INTERACTOR_X = 3;
	public static final int INDEX_INTERACTOR_Y = 4;
	public static final int INDEX_INTERACTOR_Z = 5;
	
	public int refinerX, refinerY, refinerZ, interactorX, interactorY, interactorZ;

	public PacketLoadInteractor() {}
	
 	public PacketLoadInteractor(int refinerX, int refinerY, int refinerZ, int interactorX, int interactorY, int interactorZ)
	{
		this.refinerX = refinerX;
		this.refinerY = refinerY;
		this.refinerZ = refinerZ;
		this.interactorX = interactorX;
		this.interactorY = interactorY;
		this.interactorZ = interactorZ;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		if (buf != null && buf.readableBytes() > 0)
		{
			refinerX = buf.readInt();
			refinerY = buf.readInt();
			refinerZ = buf.readInt();
			interactorX = buf.readInt();
			interactorY = buf.readInt();
			interactorZ = buf.readInt();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(refinerX);
		buf.writeInt(refinerY);
		buf.writeInt(refinerZ);
		buf.writeInt(interactorX);
		buf.writeInt(interactorY);
		buf.writeInt(interactorZ);
	}

	@Override
	public String toString()
	{
		return "Refiner: [" + Integer.toString(refinerX) + " " + Integer.toString(refinerY) + " " + Integer.toString(refinerZ) + "] Interactor:[" + Integer.toString(interactorX) + " " + Integer.toString(interactorY) + " " + Integer.toString(interactorZ) + "]";
	}
}
