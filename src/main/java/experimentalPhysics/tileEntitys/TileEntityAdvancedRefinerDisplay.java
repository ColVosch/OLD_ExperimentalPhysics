package experimentalPhysics.tileEntitys;

import net.minecraft.tileentity.TileEntity;
import experimentalPhysics.util.Position;

public class TileEntityAdvancedRefinerDisplay extends TileEntity
{
	public static final String NAME = "tileEntityAdvancedRefinerDisplay";
	
	private Position refinerPos;
	
	public void setRefinerPos(Position refinerPos)
	{
		this.refinerPos = refinerPos;
	}

	public TileEntityAdvancedRefiner getRefiner()
	{
		return (TileEntityAdvancedRefiner) refinerPos.getTileEntity(worldObj);
	}
	
}
