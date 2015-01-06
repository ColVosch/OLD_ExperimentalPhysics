package experimentalPhysics.network.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.network.packets.PacketSyncRefiner;

public class HandlerSyncRefiner implements IMessageHandler<PacketSyncRefiner, IMessage>
{
	@Override
	public IMessage onMessage(PacketSyncRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
