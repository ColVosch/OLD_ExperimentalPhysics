package experimentalPhysics.spaceField.events;

import experimentalPhysics.util.Position;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class EventEnderOutpost implements ISpaceFieldEvent
{

	@Override
	public boolean trigger(World world, Position pos)
	{
		if (pos.getBlock(world) != Blocks.air)
		{
			world.createExplosion((Entity) null, pos.x, pos.y, pos.z, 8, true);
			world.setBlock(pos.x, pos.y, pos.z, Blocks.mob_spawner);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos.x, pos.y, pos.z);
			spawner.func_145881_a().setEntityName("Enderman");
			return true;
		}
		else
		{
			return false;
		}
	}

}
