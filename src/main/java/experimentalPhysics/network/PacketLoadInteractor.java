package experimentalPhysics.network;

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
		System.out.println(buf);
		if (buf != null && buf.readableBytes() > 0)
		{
			refinerX = buf.getInt(INDEX_REFINER_X);
			refinerY = buf.getInt(INDEX_REFINER_Y);
			refinerZ = buf.getInt(INDEX_REFINER_Z);
			interactorX = buf.getInt(INDEX_INTERACTOR_X);
			interactorY = buf.getInt(INDEX_INTERACTOR_Y);
			interactorZ = buf.getInt(INDEX_INTERACTOR_Z);
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.capacity(12);
		buf.setInt(INDEX_REFINER_X, refinerX);
		buf.setInt(INDEX_REFINER_Y, refinerY);
		buf.setInt(INDEX_REFINER_Z, refinerZ);
		buf.setInt(INDEX_INTERACTOR_X, interactorX);
		buf.setInt(INDEX_INTERACTOR_Y, interactorY);
		buf.setInt(INDEX_INTERACTOR_Z, interactorZ);
	}

	@Override
	public String toString()
	{
		return "Refiner: [" + Integer.toString(refinerX) + " " + Integer.toString(refinerY) + " " + Integer.toString(refinerZ) + "] Interactor:[" + Integer.toString(interactorX) + " " + Integer.toString(interactorY) + " " + Integer.toString(interactorZ) + "]";
	}
}
