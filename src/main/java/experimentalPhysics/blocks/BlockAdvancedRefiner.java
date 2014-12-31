package experimentalPhysics.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.items.ItemBlockAdvancedRefiner;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;
import experimentalPhysics.util.LocatedBlock;
import experimentalPhysics.util.MultiblockHelper;

public class BlockAdvancedRefiner extends BlockAdvancedRefinerPart implements ITileEntityProvider
{
	private String name = "blockAdvancedRefiner";
	
	
	public BlockAdvancedRefiner()
	{
		setBlockName(name);
		setBlockTextureName(ExperimentalPhysics.getMainTextureFolder()+":refinerAdvanced");
		GameRegistry.registerBlock(this, ItemBlockAdvancedRefiner.class, name);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefiner.class, "tileEntityAdvancedRefiner");
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":refinerAdvanced");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefiner();
	}
	
	public void casingAdded(World world, int x, int y, int z)
	{
		if (canForm(world, x, y, z))
		{
			formNeighbors(world, x, y, z);
			((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).form();
		}	
	}

	public void casingRemoved(World world, int x, int y, int z)
	{
		unFormNeighbors(world, x, y, z);
	}
	
	public boolean canForm(World world, int x, int y, int z)
	{
		boolean canForm = true;
		LocatedBlock[] surroundingBlocks = MultiblockHelper.getLocatedBlocksInCube(world, x, y, z, 1);
		for (LocatedBlock block : surroundingBlocks)
		{
			canForm = (block.getBlocktype() instanceof BlockConnectedTexture && (block.getMeta() == 0));
			if (!canForm)
			{
				return false;
			}
		}
		return canForm;
	}

	
	private void formNeighbors(World world, int x, int y, int z)
	{	
		for (LocatedBlock casing : MultiblockHelper.getLocatedBlocksInCube(world, x, y, z, 1))
		{
			((BlockAdvancedRefinerPart) casing.getBlocktype()).form(world, casing.getxCoord(), casing.getyCoord(), casing.getzCoord(), x, y, z);
		}
	}

	private void unFormNeighbors(World world, int x, int y, int z)
	{
		for (LocatedBlock casing : MultiblockHelper.getLocatedBlocksInCube(world, x, y, z, 1))
		{
			((BlockAdvancedRefinerPart) casing.getBlocktype()).unForm(world, casing.getxCoord(), casing.getyCoord(), casing.getzCoord());
		}
	}

	@Override
	public boolean canTextureConnect(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector)
	{
		return false;
	}
	

	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).form();
	}
	

	@Override
	public void unForm(World world, int x, int y, int z) {}
}
