package experimentalPhysics.blocks;

import java.util.Random;

import experimentalPhysics.constants.Tier;
import experimentalPhysics.util.MultiblockHelper;
import experimentalPhysics.util.Position;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO BURN!!!
public abstract class BlockAdvancedRefinerPart extends BlockConnectedTexture
{
	/**
	 * @return the tier of the refiner part.
	 */
	public abstract Tier getTier();
	
	
	public BlockAdvancedRefinerPart()
	{
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setHardness(25.0F);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[0];
	}
	

	@Override
	public boolean canTextureConnect(IBlockAccess access, Position thisPos, Position connectorPos)
	{
		return connectorPos.getBlock(access) instanceof BlockAdvancedRefinerPart && connectorPos.getMeta(access) == 1;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int meta)
	{	        
		world.scheduleBlockUpdate(x, y, z, this, 1);
		return meta;
	}

	public void updateTick(World world, int x, int y, int z, Random p_149674_5_)
	{
		Position refinerPos = findRefiner(world, x, y, z);
		if (refinerPos != null)
		{
			((BlockAdvancedRefiner) refinerPos.getBlock(world)).casingAdded(world, refinerPos.x, refinerPos.y, refinerPos.z);
		}
	}

	/**Gets called after all of the multiblocks components have been placed.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param xCore <br> 
	 * 		xCoord of the advancedRefinerCore
	 * @param yCore <br>
	 * 		yCoord of the advancedRefinerCore
	 * @param zCore <br>
	 * 		zCoord of the advancedRefinerCore
	 */
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
	{
		if (meta == 1)
		{
			Position refinerPos = findRefiner(world, x, y, z);
			if (refinerPos != null)
			{
				((BlockAdvancedRefiner) refinerPos.getBlock(world)).casingRemoved(world, refinerPos.x, refinerPos.y, refinerPos.z);
			}
		}
	}

	/**Gets called after one of the multiblocks components has been destroyed.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public void unForm(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}

	/**Finds a AdvancedRefinerCore that is one block away from the part
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return the position of the AdvancedRefinerCore or null if there is none in proximity
	 */
	protected Position findRefiner(World world, int x, int y, int z)
	{
		for (Position block : MultiblockHelper.getCube(x, y, z, 1))
		{
			if (block.getBlock(world) instanceof BlockAdvancedRefiner)
			{
				return block;
			}			
		}
		return null;
	}
	
}
