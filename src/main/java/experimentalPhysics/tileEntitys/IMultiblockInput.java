package experimentalPhysics.tileEntitys;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IMultiblockInput extends IMultiblockInteractor
{
	public ItemStack inputItem(Item item);
}
