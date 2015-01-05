package experimentalPhysics.network.handlers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import experimentalPhysics.blocks.BlockAdvancedRefiner;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.util.Position;

public class HandlerCoords implements IMessageHandler<PacketCoords, IMessage>
{
	public static final int ID_FORM_ADVANCED_REFINER_STRUCTURE = 0;
	
	@Override
	public IMessage onMessage(PacketCoords message, MessageContext ctx)
	{
		World world = Minecraft.getMinecraft().theWorld;
		Position messagePos = new Position(message.x, message.y, message.z);
		switch(message.id)
		{
			case ID_FORM_ADVANCED_REFINER_STRUCTURE:
			{
				Block block = messagePos.getBlock(world);
				if (block instanceof BlockAdvancedRefiner)
				{
					((BlockAdvancedRefiner) block).formStructure(world, messagePos.x, messagePos.y, messagePos.z);
				}
				else
				{
					System.out.println("Invalid id for these coords: no BlockAdvancedRefiner was found.");
				}
				return null;
			}
			default:
			{
				System.out.println("Id not defined: " + Byte.toString(message.id));
			}
		}
		return null;
	}

}
