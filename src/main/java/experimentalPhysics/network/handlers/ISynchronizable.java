package experimentalPhysics.network.handlers;

import experimentalPhysics.network.packets.PacketCoords;

/**Simplifies the synchronization of TileEntitys with the server
 * @author ColVosch
 * @see ISynchronizable#synchronize(PacketCoords)
 * @param <PacketType> the type of packet the synchronizer will (hopefully) provide
 */
public interface ISynchronizable<PacketType extends PacketCoords>
{
	/**This function is called by the synchronizer on the client side only.
	 * @param message the PacketSyncTileEntity you gave to the synchronizer
	 */
	public void synchronize(PacketType message);
}
