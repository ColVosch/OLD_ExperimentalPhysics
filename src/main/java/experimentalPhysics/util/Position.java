package experimentalPhysics.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class Position
{
	public int x;
	public int y;
	public int z;
	
	public Position(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(int[] coords)
	{
		this.x = coords[0];
		this.y = coords[1];
		this.z = coords[2];
	}
	
	public TileEntity getTileEntityFromPosition(IBlockAccess world)
	{
		return world.getTileEntity(x, y, z);
	}
	
	public int[] toIntArray()
	{
		int[] coords = {x, y, z};
		return coords;
	}
}
