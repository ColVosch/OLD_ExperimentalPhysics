package experimentalPhysics.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import experimentalPhysics.util.LocatedBlock;
import experimentalPhysics.util.MultiblockHelper;

public abstract class BlockAdvancedRefinerPart extends BlockConnectedTexture
{
	
	public BlockAdvancedRefinerPart()
	{
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setHardness(25.0F);
	}

	@Override
	public boolean canTextureConnect(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector)
	{
		return  access.getBlock(xConnector, yConnector, zConnector) instanceof BlockAdvancedRefinerPart && access.getBlockMetadata(x, y, z) == 1;
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[0];
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int meta)
	{	        
		world.scheduleBlockUpdate(x, y, z, this, 1);
		return meta;
	}

	public void updateTick(World world, int x, int y, int z, Random p_149674_5_)
	{
		LocatedBlock refiner = findRefiner(world, x, y, z);
		if (refiner != null)
		{
			((BlockAdvancedRefiner) refiner.getBlocktype()).casingAdded(world, refiner.getxCoord(), refiner.getyCoord(), refiner.getzCoord());
		}
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
	{
		if (meta == 1)
		{
			LocatedBlock refiner = findRefiner(world, x, y, z);
			if (refiner != null)
			{
				((BlockAdvancedRefiner) refiner.getBlocktype()).casingRemoved(world, refiner.getxCoord(), refiner.getyCoord(), refiner.getzCoord());
			}
		}
	}

	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
	}

	public void unForm(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}

	protected LocatedBlock findRefiner(World world, int x, int y, int z)
	{
		for (LocatedBlock block : MultiblockHelper.getLocatedBlocksInCube(world, x, y, z, 1))
		{
			if (block.getBlocktype() instanceof BlockAdvancedRefiner)
			{
				return block;
			}			
		}
		return null;
	}
}
