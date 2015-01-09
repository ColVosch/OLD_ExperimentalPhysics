package experimentalPhysics.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.network.handlers.HandlerCoords;
import experimentalPhysics.network.handlers.HandlerSyncAdvancedRefiner;
import experimentalPhysics.network.handlers.HandlerSyncRefiner;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import experimentalPhysics.network.packets.PacketSyncRefiner;

public class PacketController
{
	private static SimpleNetworkWrapper network;

	private static int currentDiscriminator = 0;

	public static void registerPackets()
	{
		getNetworkWrapper().registerMessage(HandlerSyncAdvancedRefiner.class, PacketSyncAdvancedRefiner.class, getNextDiscriminator(), Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerSyncRefiner.class, PacketSyncRefiner.class, getNextDiscriminator(), Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerCoords.class, PacketCoords.class, getNextDiscriminator(), Side.CLIENT);
	}

	private static int getNextDiscriminator()
	{
		return currentDiscriminator ++;
	}
	
	public static SimpleNetworkWrapper getNetworkWrapper()
	{
		if (network == null)
		{
			network = NetworkRegistry.INSTANCE.newSimpleChannel(ExperimentalPhysics.MODID);
		}
		return network;
	}
}
