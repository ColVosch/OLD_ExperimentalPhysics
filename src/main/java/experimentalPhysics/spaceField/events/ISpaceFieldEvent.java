package experimentalPhysics.spaceField.events;

import experimentalPhysics.util.Position;

import net.minecraft.world.World;

public interface ISpaceFieldEvent
{
	/**Triggers a space field event at the given position
	 * @param world
	 * @param pos
	 * @return Whether or not the space field event succeeded.
	 */
	public boolean trigger(World world, Position pos);
}
