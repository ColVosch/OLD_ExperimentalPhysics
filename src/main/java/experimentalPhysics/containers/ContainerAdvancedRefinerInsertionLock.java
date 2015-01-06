package experimentalPhysics.containers;

import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;
import experimentalPhysics.tileEntitys.TileEntityStoring;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAdvancedRefinerInsertionLock extends ModContainerBasic
{	
	private TileEntityStoring tileInsertionLock;

	public ContainerAdvancedRefinerInsertionLock(EntityPlayer player, TileEntityAdvancedRefinerInsertionLock tileInsertionLock)
	{
		this.tileInsertionLock = tileInsertionLock;
		
		addSlotToContainer(new Slot(tileInsertionLock, 0, 80, 36));
		addPlayerInventoryToContainer(player.inventory, 8, 84);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileInsertionLock.isUseableByPlayer(player);
	}

}
