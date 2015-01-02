package experimentalPhysics.network.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import experimentalPhysics.network.packets.PacketLoadInteractor;
import experimentalPhysics.tileEntitys.IAdvancedRefinerModifier;
import experimentalPhysics.tileEntitys.IMultiblockInput;
import experimentalPhysics.tileEntitys.IMultiblockOutput;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;

public class HandlerLoadInteractor implements IMessageHandler<PacketLoadInteractor, IMessage>
{

	@Override
	public IMessage onMessage(PacketLoadInteractor message, MessageContext ctx)
	{
		World world = Minecraft.getMinecraft().theWorld;
		TileEntity tileRefiner = world.getTileEntity(message.refinerX, message.refinerY, message.refinerZ);
		TileEntity tileInteractor = world.getTileEntity(message.interactorX, message.interactorY, message.interactorZ);
		if (tileRefiner != null && tileInteractor != null && tileRefiner instanceof TileEntityAdvancedRefiner)
		{
			if (tileInteractor instanceof IMultiblockInput)
			{
				((TileEntityAdvancedRefiner) tileRefiner).registerInput((IMultiblockInput) tileInteractor);
			}
			if (tileInteractor instanceof IMultiblockOutput)
			{
				((TileEntityAdvancedRefiner) tileRefiner).registerOutput((IMultiblockOutput) tileInteractor);
			}
			if (tileInteractor instanceof IAdvancedRefinerModifier)
			{
				((TileEntityAdvancedRefiner) tileRefiner).registerModifyer((IAdvancedRefinerModifier) tileInteractor);
			}
		}
		return null;
	}

}
