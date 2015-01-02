package experimentalPhysics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        ItemStack itemstack = slot.getStack();
        if (slot != null && slot.getHasStack())
        {
            if (slotIndex < 3)
            {
                if (!this.mergeItemStack(itemstack, 3, 39, true))
                {
                    return null;
                }
            }
            else if (itemstack.getItem() == Items.flint_and_steel)
            {
            	if (!this.mergeItemStack(itemstack, 0, 1, false))
					{
						return null;
					}
            }
            else if (itemstack.getItem() == Items.ender_pearl)
            {
            	if (!this.mergeItemStack(itemstack, 1, 2, false))
					{					
						return null;
					}
            }
            if (itemstack.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return slot.getStack();
    }	
}