package experimentalPhysics.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**Slot that prevents the player from putting items into it
 * @author ColVosch
 *
 */
public class SlotOutOnly extends Slot
{

	/**
	 * @see SlotOutOnly
	 */
	public SlotOutOnly(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		 return false;
	}
}
