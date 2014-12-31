package experimentalPhysics.tileEntitys;

import net.minecraft.item.ItemStack;

public interface IMultiblockOutput
{
	public boolean outputItem(ItemStack item);
	
	public int[] getCoords();
}
