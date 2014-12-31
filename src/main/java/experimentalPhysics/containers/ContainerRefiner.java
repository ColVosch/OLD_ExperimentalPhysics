package experimentalPhysics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class ContainerRefiner extends ModContainerBasic
{
	private TileEntityRefiner tileRefiner;
	
	public ContainerRefiner(EntityPlayer player, TileEntityRefiner tileRefiner)
	{
		this.tileRefiner = tileRefiner;
		
		addSlotToContainer(new Slot(tileRefiner, 0, 8, 55));
		addSlotToContainer(new Slot(tileRefiner, 1, 80, 9));
		addSlotToContainer(new SlotOutOnly(tileRefiner, 2, 80, 51));
		addPlayerInventoryToContainer(player.inventory, 8, 84);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{		
		return tileRefiner.isUseableByPlayer(player);
	}
}