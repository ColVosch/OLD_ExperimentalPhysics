package experimentalPhysics.network.handlers;

import experimentalPhysics.network.packets.PacketCoords;

import net.minecraft.client.Minecraft;

/**Helper class used for synchronizing a TileEntity with the server
 * @author ColVosch
 * @see Synchronizer#synchronize(PacketCoords)
 */
public class Synchronizer
{
	/**Call this to call the {@link ISynchronizable#synchronize(PacketCoords)} method of the ISerializable contained in the {@link PacketCoords} you provide.
	 * @param message the packet you have to provide
	 */
	public static void synchronize(PacketCoords message)
	{
		//TODO there has to be a better way!
		((ISynchronizable) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z)).synchronize(message);		
	}
}
