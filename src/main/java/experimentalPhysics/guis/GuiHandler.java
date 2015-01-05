package experimentalPhysics.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerAdvancedRefinerInsertionLock;
import experimentalPhysics.containers.ContainerEmpty;
import experimentalPhysics.containers.ContainerRefiner;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerDisplay;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class GuiHandler implements IGuiHandler
{
	public static final int ID_REFINER = 0;
	public static final int ID_ADVANCED_REFINER_INSERTION_LOCK = 1;
	public static final int ID_ADVANCED_REFINER_DISPLAY = 2;
	
	public static void register()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(ExperimentalPhysics.instance, new GuiHandler());
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		switch(ID)
		{
			case ID_REFINER:
			{
				return new ContainerRefiner(player, (TileEntityRefiner) tile);
			}
			case ID_ADVANCED_REFINER_INSERTION_LOCK:
			{
				return new ContainerAdvancedRefinerInsertionLock(player, (TileEntityAdvancedRefinerInsertionLock) tile);
			}
			case ID_ADVANCED_REFINER_DISPLAY:
			{
				return new ContainerEmpty();
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
			case ID_REFINER:
			{
				return new GuiRefiner(player, (TileEntityRefiner) tile);
			}
			case ID_ADVANCED_REFINER_INSERTION_LOCK:
			{
				return new GuiAdvancedRefinerInsertionLock(player, (TileEntityAdvancedRefinerInsertionLock) tile);
			}
			case ID_ADVANCED_REFINER_DISPLAY:
			{
				return new GuiAdvancedRefinerDisplay(((TileEntityAdvancedRefinerDisplay) world.getTileEntity(x, y, z)).getRefiner());
			}
			default:
			{
				return null;
			}
		}
	}
}
