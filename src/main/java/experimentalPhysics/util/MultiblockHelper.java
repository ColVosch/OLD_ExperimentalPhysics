package experimentalPhysics.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MultiblockHelper
{
	/**Returns a list of all the positions inside a defined cube.
	 * @param x coordinate of the cubes center
	 * @param y coordinate of the cubes center
	 * @param z coordinate of the cubes center
	 * @param cubeRadius
	 * @return cubeRadius the 'radius' of the cube. The length of an edge equals: <br>
	 * 2 * cubeRadius + 1
	 * @see Position
	 */
	public static List<Position> getCube(int x, int y, int z, int cubeRadius)
	{
		List<Position> positions = new ArrayList<Position>();
		for (int currentX = x - cubeRadius;(currentX <= x + cubeRadius); currentX++)
		{
			for (int currentY = y - cubeRadius;(currentY <= y + cubeRadius); currentY++)
			{
				for (int currentZ = z - cubeRadius;(currentZ <= z + cubeRadius); currentZ++)
				{
					positions.add(new Position(currentX, currentY, currentZ));
				}	
			}
		}
		return positions;
	}

	/** Returns a list of all the blocks that are in a cube. The center of the cube is defined by the coordinates.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param cubeRadius the 'radius' of the cube. The length of an edge equals: <br>
	 * 2 * cubeRadius + 1
	 * @return a List of blocks (block types)
	 */
	public static List<Block> getBlocksInCube(World world, int x, int y, int z, int cubeRadius)
	{
		List<Block> blocks = new ArrayList<Block>();
		for(Position p : getCube(x, y, z, cubeRadius))
		{
			blocks.add(p.getBlock(world));
		}
		return blocks;
	}
	
	public static int getCubeCenterIndex(int cubeRadius)
	{
		return ((((2 * cubeRadius + 1) ^ 3) - 1) / 2) + 1;	
	}
}
