package experimentalPhysics.tileEntitys;

import experimentalPhysics.util.Position;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

/**Handles all the repeating functionality required by all TileEntitys implementing IInventory.
 * Also saves and loads inventory contents.
 * @author ColVosch
 *
 */
public abstract class TileEntityStoring extends TileEntityBasic implements IInventory
{
	protected ItemStack[] inventory;
	
	/**Initialize an itemStack array called inventory here. 
	 */
	protected abstract void initInvenory();
	
	public TileEntityStoring()
	{
		initInvenory();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagList nbttagItemList = new NBTTagList();

		 for (int i = 0; i < inventory.length; i++)
		 {
			 if (inventory[i] != null)
			 {
				 NBTTagCompound nbttagItem = new NBTTagCompound();
				 nbttagItem.setByte("Slot", (byte)i);
				 inventory[i].writeToNBT(nbttagItem);
				 nbttagItemList.appendTag(nbttagItem);
			 }
		 }
		 tagCompound.setTag("Items", nbttagItemList);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		initInvenory();
		NBTTagList itemListNbt = tagCompound.getTagList("Items", NBT.TAG_COMPOUND);
		
		for (int i = 0; i < itemListNbt.tagCount(); i++)
		 {
			 NBTTagCompound nbttagcompound = (NBTTagCompound)itemListNbt.getCompoundTagAt(i);
			 byte slot = nbttagcompound.getByte("Slot");
			 if (slot >= 0 && slot < inventory.length)
			 {
				 inventory[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			 }
		 }
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		assert inventory.length > slot : "[TileEntityStoring] [getStackInSlot] invalid slot index: " + Integer.toString(slot);
		
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int itemAmount)
	{
		ItemStack returnStack = inventory[slot] == null ? null : (itemAmount <= inventory[slot].stackSize ? inventory[slot].splitStack(itemAmount) : inventory[slot].splitStack(inventory[slot].stackSize));
		if(inventory[slot] != null && inventory[slot].stackSize == 0)
		{
			inventory[slot] = null;
		}
		return returnStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack retItemstack = inventory[slot];
		inventory[slot] = null;
		return retItemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		assert inventory.length > slot : "[TileEntityStoring] [setInventorySlotContents] invalid slot index: " + Integer.toString(slot);
		
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
		inventory[slot] = stack;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return getInventoryName() != null;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return false;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}

	public void dropItems()
	{
		for (int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack stack = getStackInSlot(i);
        	if (stack != null)
			{
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, stack));
			}
		}
	}
}
