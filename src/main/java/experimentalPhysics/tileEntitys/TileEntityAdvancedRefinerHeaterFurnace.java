package experimentalPhysics.tileEntitys;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.util.Position;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityAdvancedRefinerHeaterFurnace extends TileEntityStoring implements IAdvancedRefinerHeater
{
	public static final String NAME = "tileEntityAdvancedRefinerHeaterFurnace";
	
	private static final int FUEL = 0;
	
	private int itemBurnTime;
	private int fuelTimeRemaining = 0;
	
	@Override
	protected void initInvenory()
	{
		inventory = new ItemStack[1];
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		itemBurnTime = tagCompound.getInteger("itemBurnTime");
		fuelTimeRemaining = tagCompound.getInteger("fuelTimeRemaining");
	}
	
	@Override
    public S35PacketUpdateTileEntity getDescriptionPacket() 
	{
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
	
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		if (worldObj.isRemote)
		{
			this.readFromNBT(pkt.func_148857_g());
		}
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("itemBurnTime", itemBurnTime);
		tagCompound.setInteger("fuelTimeRemaining", fuelTimeRemaining);
	}
	
	@Override
	public String getInventoryName()
	{
		return "container.advancedRefinerHeaterFurnace.name";
	}

	@Override
	public int[] getCoords()
	{
		int [] coords = {xCoord, yCoord, zCoord};
		return coords;
	}

	public void form(Position refinerPos)
	{
		((TileEntityAdvancedRefiner) refinerPos.getTileEntity(worldObj)).registerHeater(this);
	}                                      
	                                       
	@Override                              
	public float getTemperatureIncrease()  
	{                                      
		if (isBurning())                   
		{        
			fuelTimeRemaining --;
			return 0.8488738361f;          
		}                                  
		else if (inventory[FUEL] != null)  
		{                                  
			int fuelValue = GameRegistry.getFuelValue(inventory[FUEL]);
			if (fuelValue == 0)
			{
				fuelValue = TileEntityFurnace.getItemBurnTime(inventory[FUEL]);
			}
			if (fuelValue > 0)
			{
				itemBurnTime = fuelValue;
				fuelTimeRemaining = fuelValue;
				decrStackSize(FUEL, 1);
			}
		}
		return 0;
	}

	public boolean isBurning()
	{
		return fuelTimeRemaining > 0;
	}

	public int getFuelTimeRemainingScaled(int i)
	{
		return (int) ((float) i * (float) fuelTimeRemaining / (float) itemBurnTime);
	}
}
