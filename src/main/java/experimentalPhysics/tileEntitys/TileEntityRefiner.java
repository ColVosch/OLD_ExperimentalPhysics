package experimentalPhysics.tileEntitys;

import experimentalPhysics.items.ModItems;
import experimentalPhysics.network.PacketController;
import experimentalPhysics.network.handlers.ISynchronizable;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.network.packets.PacketSyncRefiner;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityRefiner extends TileEntityStoring implements ISynchronizable
{
	public static final int PROCESSING_TIME = 300;
	
	private static final int FUEL = 0;
	private static final int INPUT = 1;
	private static final int OUTPUT = 2;	
	
	private int currentProcessingTime = 0;
	private boolean lit = false;
	
	@Override
	protected void initInvenory()
	{
		inventory = new ItemStack[3];		
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		//TODO not synchronizing properly after loading. something like a 'onWorldLoadet' event would be required to fix this
		super.readFromNBT(tagCompound);
		currentProcessingTime = tagCompound.getInteger("currentProcessingTime");
		lit = tagCompound.getBoolean("lit");
	}

	public void synchronize(PacketCoords message)
	{
		currentProcessingTime = ((PacketSyncRefiner) message).currentProcessingTime;
		lit = ((PacketSyncRefiner) message).lit;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("currentProcessingTime", currentProcessingTime);
		tagCompound.setBoolean("lit", lit);
	}

	@Override
	public String getInventoryName()
	{	
		return "container.refiner";
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

	public int getProgressScaled(int factor)
	{
		 return (currentProcessingTime * factor) / PROCESSING_TIME;
	}
	
	public boolean isLit()
	{
		return lit;	
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
				System.out.println((worldObj.isRemote ? "Client: " : "Server: ") + currentProcessingTime);
			}
			else
			{
				currentProcessingTime = -1;
				lit = false;
			}
		}
		
		if (currentProcessingTime >= PROCESSING_TIME)
		{
			if (!worldObj.isRemote)
			{
				PacketController.getNetworkWrapper().sendToAll(new PacketSyncRefiner(currentProcessingTime, lit, xCoord, yCoord, zCoord));
			}
			process();
			currentProcessingTime = -1;
		}	
	}

	private boolean tryStartRefining()
	{
		if ((inventory[INPUT] != null && inventory[INPUT].getItem() == Items.ender_pearl) && (inventory[OUTPUT] == null || (inventory[OUTPUT].getItem() == ModItems.enderPearlCore && inventory[OUTPUT].stackSize < getInventoryStackLimit() && !isLit())))
		{
			tryLight();
		}
		return isLit();
	}

	private boolean canContiniueRefining()
	{
		return (inventory[INPUT] != null && inventory[INPUT].getItem() == Items.ender_pearl);
	}

	private void tryLight()
	{
		if (inventory[FUEL] != null && inventory[FUEL].getItem() == Items.flint_and_steel)
		{
			inventory[FUEL].attemptDamageItem(1, worldObj.rand);
			if (inventory[FUEL].getItemDamage() > inventory[FUEL].getMaxDamage())
			{
				inventory[FUEL] = null;
			}
			lit = true;
		}
		else
		{
			lit = false;
		}
	}

	private void process()
	{
		if (canContiniueRefining())
		{
			decrStackSize(1, 1);
			if (inventory[OUTPUT] == null)
			{
				inventory[OUTPUT] = new ItemStack(ModItems.enderPearlCore);
			}
			else
			{
				inventory[OUTPUT].stackSize ++;
			}
			lit = false;
		}
	}
}