package experimentalPhysics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**This container implements basic shift-clicking, so you don't crash on the attempt. It also adds a method to add the players inventory to itself, witch spares a lot of copy-pasting.
 * @author ColVosch
 * @see ModContainerBasic#addPlayerInventoryToContainer(InventoryPlayer, int, int)
 * @see ModContainerBasic#transferStackInSlot(EntityPlayer, int)
 */
public abstract class ModContainerBasic extends Container
{

	/**Adds the given playerInventory to the ModContainerBasic
	 * @param inventory the playerInventory
	 * @param xOffset how far should the slots be moved along the x-axis
	 * @param yOffset how far should the slots be moved along the y-axis
	 */
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex < inventorySlots.size() - 36)
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
