package experimentalPhysics.tileEntitys;

import experimentalPhysics.util.Position;

import net.minecraft.tileentity.TileEntity;

public class TileEntityAdvancedRefinerDisplay extends TileEntity
{
	public static final String NAME = "tileEntityAdvancedRefinerDisplay";
	
	private Position refinerPos;
	
	public void form(Position refinerPos)
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
	
}
