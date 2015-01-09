package experimentalPhysics.tileEntitys;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

import experimentalPhysics.util.Position;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAdvancedRefinerDisplay extends TileEntity
{
	public static final String NAME = "tileEntityAdvancedRefinerDisplay";
	
	private Position refinerPos;
	
	public void readFromNBT(NBTTagCompound compound)
	{
		System.out.println(compound);
		super.readFromNBT(compound);
		if (compound.hasKey("refinerPos"))
		{		
			refinerPos = new Position(compound.getIntArray("refinerPos"));
		}
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		System.out.println(compound);
		if (refinerPos != null)
		{
			compound.setIntArray("refinerPos", refinerPos.toIntArray());
		}
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
		return refinerPos != null;
	}
	
	public TileEntityAdvancedRefiner getRefiner()
	{
		return hasRefiner() ? (TileEntityAdvancedRefiner) refinerPos.getTileEntity(worldObj) : null;
	}

	public float getProgressPercentage()
	{
		TileEntityAdvancedRefiner refiner = getRefiner();
		if (hasRefiner())
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
