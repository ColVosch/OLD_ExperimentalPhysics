package experimentalPhysics.network.handlers;

import net.minecraft.client.Minecraft;

import experimentalPhysics.network.packets.PacketSyncTileEntity;

/**Helper class used for synchronizing a TileEntity with the server
 * @author ColVosch
 * @see Synchronizer#synchronize(PacketSyncTileEntity)
 */
public class Synchronizer
{
	/**Call this to call the {@link ISynchronizable#synchronize(PacketSyncTileEntity)} method of the ISerializable contained in the {@link PacketSyncTileEntity} you provide.
	 * @param message the packet you have to provide
	 */
	public static void synchronize(PacketSyncTileEntity message)
	{
		//TODO there has to be a better way!
		((ISynchronizable) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z)).synchronize(message);		
	}
}
