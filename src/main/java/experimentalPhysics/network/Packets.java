package experimentalPhysics.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import experimentalPhysics.ExperimentalPhysics;

public class Packets
{
	public static SimpleNetworkWrapper network;

	public static void register()
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel(ExperimentalPhysics.MODID);
		network.registerMessage(HandlerLoadInteractor.class, PacketLoadInteractor.class, 0, Side.CLIENT);
	}
}
