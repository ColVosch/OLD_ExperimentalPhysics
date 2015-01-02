package experimentalPhysics.tileEntitys;

import net.minecraft.item.ItemStack;

public interface IMultiblockOutput extends IMultiblockInteractor
{
	public boolean outputItem(ItemStack item);
}
