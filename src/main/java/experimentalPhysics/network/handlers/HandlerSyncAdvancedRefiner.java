package experimentalPhysics.network.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;

public class HandlerSyncAdvancedRefiner implements IMessageHandler<PacketSyncAdvancedRefiner, IMessage>
{

	@Override
	public IMessage onMessage(PacketSyncAdvancedRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
