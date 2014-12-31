package experimentalPhysics.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MultiblockHelper
{
	public static List<int []> getBlockCoordsInCube(World world, int x, int y, int z, int cubeRadius)
	{
		List<int []> returnList = new ArrayList<int[]>();
		for (int checkX = x - cubeRadius;(checkX <= x + cubeRadius); checkX++)
		{
			for (int checkY = y - cubeRadius;(checkY <= y + cubeRadius); checkY++)
			{
				for (int checkZ = z - cubeRadius;(checkZ <= z + cubeRadius); checkZ++)
				{
					int [] coords = new int [3];
					coords[0] = checkX;
					coords[1] = checkY;
					coords[2] = checkZ;
					returnList.add(coords);
				}	
			}
		}
		return returnList;
	}
	
	public static LocatedBlock[] getLocatedBlocksInCube(World world, int x, int y, int z, int cubeRadius)
	{
		LocatedBlock[] blocks = new LocatedBlock[27];
		List<int []> coordList = getBlockCoordsInCube(world, x, y, z, cubeRadius);
		for(int i = 0; i < coordList.size(); i++)
		{
			blocks[i] = new LocatedBlock(world.getBlock(coordList.get(i)[0], coordList.get(i)[1], coordList.get(i)[2]),
										 coordList.get(i)[0], coordList.get(i)[1], coordList.get(i)[2],
										 world.getBlockMetadata(coordList.get(i)[0], coordList.get(i)[1], coordList.get(i)[2]));
		}
		return blocks;
	}
	
	public static List<Block> getBlocksInCube(World world, int x, int y, int z, int cubeRadius)
	{
		List<Block> blocks = new ArrayList<Block>();
		List<int []> coordList = getBlockCoordsInCube(world, x, y, z, cubeRadius);
		for(int i = 0; i < coordList.size(); i++)
		{
			blocks.add(world.getBlock(coordList.get(i)[0], coordList.get(i)[1], coordList.get(i)[2]));
		}
		return blocks;
	}
	
	public static int getCubeCenterIndex(int cubeRadius)
	{
		return ((((2 * cubeRadius + 1) ^ 3) - 1) / 2) + 1;	
	}
}
