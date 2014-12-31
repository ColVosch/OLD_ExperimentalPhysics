package experimentalPhysics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ModContainerBasic extends Container
{

	public void addPlayerInventoryToContainer(InventoryPlayer inventory, int xOffset, int yOffset)
	{	
		for (int i = 0; i < 3; i++)
		 {
			 for (int k = 0; k < 9; k++)
			 {
				 addSlotToContainer(new Slot(inventory, k + i * 9 + 9, xOffset + k * 18, yOffset + i * 18));
			 }
		 }
		 for (int j = 0; j < 9; j++)
		 {
			 addSlotToContainer(new Slot(inventory, j, xOffset + j * 18, yOffset + 58));
		 }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ < inventorySlots.size() - 36)
            {
                if (!this.mergeItemStack(itemstack1, inventorySlots.size() - 36, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, inventorySlots.size() - 36, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
