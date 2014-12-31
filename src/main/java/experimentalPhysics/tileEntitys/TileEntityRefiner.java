package experimentalPhysics.tileEntitys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import experimentalPhysics.items.ModItems;

public class TileEntityRefiner extends TileEntityStoring
{
	private static final int PROCESSING_TIME = 1000;
	private int currentProcessingTime = 0;
	private boolean lit = false;
	
	public TileEntityRefiner()
	{
		super();	
	}
	
	/** inventory[0] = fuel<br>
	 *  inventory[1] = input<br>
	 *  inventory[2] = output<br>
	 * @see experimentalPhysics.tileEntitys.TileEntityStoring#initInvenory()
	 */
	@Override
	protected void initInvenory()
	{
		inventory = new ItemStack[3];		
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setByte("currentProcessingTime", (byte) currentProcessingTime);
		tagCompound.setBoolean("lit", lit);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		currentProcessingTime = tagCompound.getByte("currentProcessingTime");
		lit = tagCompound.getBoolean("lit");
	}		

	@Override
	public String getInventoryName()
	{	
		return "container.refiner";
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	public int getProgressScaled(int factor)
	{
		 return (currentProcessingTime * factor) / PROCESSING_TIME;
	}
	
	public boolean isLit()
	{
		return lit;	
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{		
		switch(slot)
		{
			case 0:
				return stack.getItem() == Items.flint_and_steel;
			case 1:
				return true;
			default:
				return false;
		}
	}

	@Override
	public void updateEntity()
	{
		if (currentProcessingTime == -1)
		{
			if (tryStartRefining())
			{
				currentProcessingTime = 0;
			}
		}
		
		if (currentProcessingTime >= 0 && currentProcessingTime < PROCESSING_TIME)
		{
			if (canContiniueRefining())
			{
				currentProcessingTime ++;
			}
			else
			{
				currentProcessingTime = -1;
				lit = false;
			}
		}
		
		if (currentProcessingTime >= PROCESSING_TIME)
		{
			process();
			currentProcessingTime = -1;
		}	
	}

	private boolean tryStartRefining()
	{
		if ((inventory[1] != null && inventory[1].getItem() == Items.ender_pearl) && (inventory[2] == null || (inventory[2].getItem() == ModItems.enderPearlCore && inventory[2].stackSize < getInventoryStackLimit() && !isLit())))
		{
			tryLight();
		}
		return isLit();
	}

	private boolean canContiniueRefining()
	{
		return (inventory[1] != null && inventory[1].getItem() == Items.ender_pearl);
	}

	private void process()
	{
		if (canContiniueRefining())
		{
			decrStackSize(1, 1);
			if (inventory[2] == null)
			{
				inventory[2] = new ItemStack(ModItems.enderPearlCore);
			}
			else
			{
				inventory[2].stackSize ++;
			}
			lit = false;
		}
	}

	private void tryLight()
	{
		if (inventory[0] != null && inventory[0].getItem() == Items.flint_and_steel)
		{
			inventory[0].attemptDamageItem(1, worldObj.rand);
			if (inventory[0].getItemDamage() > inventory[0].getMaxDamage())
			{
				inventory[0] = null;
			}
			lit = true;
		}
		else
		{
			lit = false;
		}
	}
}