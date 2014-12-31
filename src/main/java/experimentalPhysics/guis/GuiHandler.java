package experimentalPhysics.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import experimentalPhysics.containers.ContainerAdvancedRefinerInsertionLock;
import experimentalPhysics.containers.ContainerRefiner;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		switch(ID)
		{
			case 0:
			{
				return new ContainerRefiner(player, (TileEntityRefiner) tile);
			}
			case 1:
			{
				return new ContainerAdvancedRefinerInsertionLock(player, (TileEntityAdvancedRefinerInsertionLock) tile);
			}
			default:
			{
				return null;
			}
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		switch(ID)
		{
			case 0:
			{
				return new GuiRefiner(player, (TileEntityRefiner) tile);
			}
			case 1:
			{
				return new GuiAdvancedRefinerInsertionLock(player, (TileEntityAdvancedRefinerInsertionLock) tile);
			}
			default:
			{
				return null;
			}
		}
	}
}
