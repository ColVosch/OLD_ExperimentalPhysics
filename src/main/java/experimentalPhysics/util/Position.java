package experimentalPhysics.util;

import static java.lang.Math.*;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
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
	
	public static Position positionFromNBT(NBTTagCompound compound)
	{
		int x, y, z, strength;
		x = compound.getInteger("x");
		y = compound.getInteger("y");
		z = compound.getInteger("z");
		return new Position(x, y, z);
	}
	
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

	public float getDistance(Position pos)
	{
		float dist = (float) sqrt(pow(this.x - pos.x, 2) + pow(this.y - pos.y, 2) + pow(this.z - pos.z, 2));
		return dist;
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

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("x", x);
		compound.setInteger("y", y);
		compound.setInteger("z", z);
		return compound;
	}
	
	@Override
	public String toString()
	{
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ", " + Integer.toString(z) + ")";
	}
}
