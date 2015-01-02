package experimentalPhysics.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

/**A data object storing a set of three integer coordinates
 * @author ColVosch
 *
 */
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
		this(coords[0], coords[1], coords[2]);
	}
	
	public int[] toIntArray()
	{
		int[] coords = {x, y, z};
		return coords;
	}

	public TileEntity getTileEntity(IBlockAccess world)
	{
		return world.getTileEntity(x, y, z);
	}
	
	public Block getBlock(IBlockAccess world)
	{
		return world.getBlock(x, y, z);
	}
	
	public int getMeta(IBlockAccess world)
	{
		return world.getBlockMetadata(x, y, z);
	}
}
