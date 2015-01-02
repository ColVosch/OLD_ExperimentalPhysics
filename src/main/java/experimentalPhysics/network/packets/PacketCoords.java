package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**This class implements {@link IMessage} and stores a set of coordinates.
 * @author ColVosch
 *
 */
public abstract class PacketCoords implements IMessage
{
	public int x;
	public int y;
	public int z;
	
	public PacketCoords() {}
	
	public PacketCoords(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

}
