package experimentalPhysics.tileEntitys;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityAdvancedRefinerInsertionLock extends TileEntityStoring implements IMultiblockInput, IMultiblockOutput
{
	private TileEntityAdvancedRefiner refiner;
	
	public TileEntityAdvancedRefinerInsertionLock()
	{
		super();
	}

	@Override
	public String getInventoryName()
	{
		return "container.advancedRefinerInsertionLock";
	}

	@Override
	protected void initInvenory()
	{
		inventory = new ItemStack[1];
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public int[] getCoords()
	{
		int[] coords = {xCoord, yCoord, zCoord};
		return coords;
	}

	public void form(int xCore, int yCore, int zCore)
	{
		refiner = ((TileEntityAdvancedRefiner) worldObj.getTileEntity(xCore, yCore, zCore));
		refiner.registerInput(this);
		refiner.registerOutput(this);
	}

	@Override
	public ItemStack inputItem(Item item)
	{
		if (inventory[0] != null && inventory[0].getItem() == item)
		{
			ItemStack retStack = inventory[0].splitStack(1);
			if (inventory[0].stackSize <= 0)
			{
				inventory[0] = null;
			}
			return retStack;
		}
		else
		{
			return null;
		}	
	}

	@Override
	public boolean outputItem(ItemStack item)
	{
		if (item != null)
		{
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, item));
		}
		return true;
	}

}
