package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

//TODO generalize and rename
/**This class is used to synchronize a TileEntity with the server. It implements {@link IMessage} and stores the coordinates of the TileEntity to synchronize.
 * @author ColVosch
 *
 */
public abstract class PacketSyncTileEntity implements IMessage
{
	public int x;
	public int y;
	public int z;
	
	public PacketSyncTileEntity() {}
	
	public PacketSyncTileEntity(int x, int y, int z)
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
