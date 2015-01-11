package experimentalPhysics.tileEntitys;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

import experimentalPhysics.util.Position;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAdvancedRefinerDisplay extends TileEntityBasic
{
	public static final String NAME = "tileEntityAdvancedRefinerDisplay";
	
	private Position refinerPos;
	
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("refinerPos"))
		{		
			refinerPos = new Position(compound.getIntArray("refinerPos"));
		}
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		if (refinerPos != null)
		{
			compound.setIntArray("refinerPos", refinerPos.toIntArray());
		}
	}
	
	 
	public void form(Position refinerPos)
	{
		setRefiner(refinerPos);
	}
	
	public void setRefiner(Position refinerPos)
	{
		this.refinerPos = refinerPos;
	}

	public boolean hasRefiner()
	{
		return refinerPos != null && refinerPos.getTileEntity(worldObj) != null;
	}
	
	public TileEntityAdvancedRefiner getRefiner()
	{
		return hasRefiner() ? (TileEntityAdvancedRefiner) refinerPos.getTileEntity(worldObj) : null;
	}

	public float getProgressPercentage()
	{
		TileEntityAdvancedRefiner refiner = getRefiner();
		if (refiner != null)
		{
			if (refiner.getProgress() == -1)
			{
				return 0;
			}
			else
			{
				return  (float) refiner.getProgress() / (float) TileEntityAdvancedRefiner.REQUIRED_PROGRESS * 100f;
			}
		}
		else
		{
			return -1;
		}
	}

	public float getHeat()
	{
		return hasRefiner() ? getRefiner().getHeat() : -1;
	}

	public Position getRefinerPos()
	{
		return refinerPos;
	}

	public void unForm()
	{
		setRefiner((Position) null);
	}
	
}
