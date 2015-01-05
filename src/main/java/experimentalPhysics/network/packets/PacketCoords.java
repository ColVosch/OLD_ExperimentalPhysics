package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**This class implements {@link IMessage} and stores a set of coordinates.
 * @author ColVosch
 *
 */
public class PacketCoords implements IMessage
{
	public int x;
	public int y;
	public int z;
	
	public byte id;
	
	public PacketCoords() {}
	
	public PacketCoords(int x, int y, int z, byte id)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
	
	public PacketCoords(int x, int y, int z)
	{
		this(x, y, z, (byte) -1);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(id);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		id = buf.readByte();
	}

}
