package experimentalPhysics.network.handlers;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.blocks.BlockAdvancedRefiner;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.util.Position;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class HandlerCoords implements IMessageHandler<PacketCoords, IMessage>
{
	public static final byte ID_FORM_ADVANCED_REFINER = 0;
	public static final byte ID_UNFORM_ADVANCED_REFINER = 1;
	
	@Override
	public IMessage onMessage(PacketCoords message, MessageContext ctx)
	{
		World world = Minecraft.getMinecraft().theWorld;
		Position messagePos = new Position(message.x, message.y, message.z);
		switch(message.id)
		{
			case ID_FORM_ADVANCED_REFINER:
			{
				Block block = messagePos.getBlock(world);
				if (block instanceof BlockAdvancedRefiner)
				{
					((BlockAdvancedRefiner) block).formStructure(world, messagePos.x, messagePos.y, messagePos.z);
				}
				else
				{
					FMLLog.warning("Invalid id for these coords: no BlockAdvancedRefiner was found.");
				}
				return null;
			}
			case ID_UNFORM_ADVANCED_REFINER:
			{
				Block block = messagePos.getBlock(world);
				if (block instanceof BlockAdvancedRefiner)
				{
					((BlockAdvancedRefiner) block).unFormStructure(world, messagePos.x, messagePos.y, messagePos.z);
				}
				else
				{
					FMLLog.warning("Invalid id for these coords: no BlockAdvancedRefiner was found.");
				}
				return null;
			}
		}
		return null;
	}

}
