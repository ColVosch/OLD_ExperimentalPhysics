package experimentalPhysics.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.network.handlers.HandlerCoords;
import experimentalPhysics.network.handlers.HandlerLoadInteractor;
import experimentalPhysics.network.handlers.HandlerSyncAdvancedRefiner;
import experimentalPhysics.network.handlers.HandlerSyncRefiner;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.network.packets.PacketLoadInteractor;
import experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import experimentalPhysics.network.packets.PacketSyncRefiner;

public class PacketController
{
	private static SimpleNetworkWrapper network;

	public static void registerPackets()
	{		
		getNetworkWrapper().registerMessage(HandlerLoadInteractor.class, PacketLoadInteractor.class, 0, Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerSyncAdvancedRefiner.class, PacketSyncAdvancedRefiner.class, 1, Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerSyncRefiner.class, PacketSyncRefiner.class, 2, Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerCoords.class, PacketCoords.class, 3, Side.CLIENT);
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
