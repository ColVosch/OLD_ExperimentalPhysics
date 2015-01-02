package experimentalPhysics.network.handlers;

import experimentalPhysics.network.packets.PacketSyncTileEntity;

/**Simplifies the synchronization of TileEntitys with the server
 * @author ColVosch
 * @see ISynchronizable#synchronize(PacketSyncTileEntity)
 * @param <PacketType> the type of packet the synchronizer will (hopefully) provide
 */
public interface ISynchronizable<PacketType extends PacketSyncTileEntity>
{
	/**This function is called by the synchronizer on the client side only.
	 * @param message the PacketSyncTileEntity you gave to the synchronizer
	 */
	public void synchronize(PacketType message);
}
