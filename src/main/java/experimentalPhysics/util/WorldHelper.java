package experimentalPhysics.util;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldHelper
{
	/**Searches for the nearest non-air-block, looking downwards.
	 * @param world
	 * @param pos
	 * @return The y-coord of the nearest air block that is one above a non-air-block, looking downwards.
	 */
	public static int findGround(World world, Position pos)
	{
		int y = -1;
		for (int i = pos.y; (y == -1) && (i >= 0); i--)
		{
			if (world.getBlock(pos.x, i, pos.z) != Blocks.air && world.getBlock(pos.x, i + 1, pos.z) == Blocks.air)
			{
				y = i + 1;
			}
		}
		return y;
	}
}
