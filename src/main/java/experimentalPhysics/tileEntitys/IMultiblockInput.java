package experimentalPhysics.tileEntitys;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IMultiblockInput
{
	public ItemStack inputItem(Item item);
	
	public int[] getCoords();
}
