package experimentalPhysics.network.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.network.packets.PacketSyncRefiner;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class HandlerSyncRefiner implements IMessageHandler<PacketSyncRefiner, IMessage>
{
	@Override
	public IMessage onMessage(PacketSyncRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
