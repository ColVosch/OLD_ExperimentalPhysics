package experimentalPhysics.network.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;

public class HandlerSyncAdvancedRefiner implements IMessageHandler<PacketSyncAdvancedRefiner, IMessage>
{

	@Override
	public IMessage onMessage(PacketSyncAdvancedRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
