package experimentalPhysics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.spaceField.SpaceFieldManager;
import experimentalPhysics.util.Position;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockInterdimensionalDisturber extends Block
{
	public static final String name = "blockInterdimensionalDisturber";
	
	
	public BlockInterdimensionalDisturber()
	{
		super(Material.iron);
		setBlockName(name);
		setStepSound(soundTypeMetal);
		setHardness(18.0F);
		setBlockTextureName(ExperimentalPhysics.MODID+":purifier");
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta)
	{
		if (!world.isRemote)
		{
			SpaceFieldManager.spaceFields.get(world.provider.dimensionId).addTensionPoint(new Position(x, y, z), 10);
		}
	}
	
	@Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int oldMeta) 
    {
    	if (!world.isRemote)
		{
			SpaceFieldManager.spaceFields.get(world.provider.dimensionId).removeTensionPoint(new Position(x, y, z));
		}
    }

}
