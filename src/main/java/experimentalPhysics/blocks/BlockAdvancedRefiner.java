package experimentalPhysics.blocks;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.constants.Tiers;
import experimentalPhysics.items.ItemBlockAdvancedRefiner;
import experimentalPhysics.network.PacketController;
import experimentalPhysics.network.packets.PacketCoords;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;
import experimentalPhysics.util.MultiblockHelper;
import experimentalPhysics.util.Position;

public class BlockAdvancedRefiner extends BlockAdvancedRefinerPart implements ITileEntityProvider
{
	public static final String NAME = "blockAdvancedRefiner";
	
	
	public BlockAdvancedRefiner()
	{
		setBlockName(NAME);
		setBlockTextureName(ExperimentalPhysics.MODID + ":refinerAdvanced");
		GameRegistry.registerBlock(this, ItemBlockAdvancedRefiner.class, NAME);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefiner.class, TileEntityAdvancedRefiner.NAME);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":refinerAdvanced");
	}
	
	@Override
	public boolean canTextureConnect(IBlockAccess access, Position thisPos, Position connectorPos)
	{
		return false;
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
			formStructure(world, x, y, z);
		}	
	}

	private boolean canForm(World world, int x, int y, int z)
	{
		boolean canForm = true;
		List<Position> surroundingBlocks = MultiblockHelper.getCube(x, y, z, 1);
		for (Position block : surroundingBlocks)
		{
			canForm = (block.getBlock(world) instanceof BlockConnectedTexture && (block.getMeta(world) == 0));
			if (!canForm)
			{
				return false;
			}
		}
		return canForm;
	}

	public void formStructure(World world, int x, int y, int z)
	{	
		if (!world.isRemote)
		{
			PacketController.getNetworkWrapper().sendToAll(new PacketCoords(x, y, z, (byte) 0));
		}
		for (Position casing : MultiblockHelper.getCube(x, y, z, 1))
		{
			((BlockAdvancedRefinerPart) casing.getBlock(world)).form(world, casing.x, casing.y, casing.z, x, y, z);
		}
	}

	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).form();
	}

	
	public void casingRemoved(World world, int x, int y, int z)
	{
		unFormStructure(world, x, y, z);
	}

	private void unFormStructure(World world, int x, int y, int z)
	{
		for (Position casing : MultiblockHelper.getCube(x, y, z, 1))
		{
			if (casing.getBlock(world) instanceof BlockAdvancedRefinerPart)
			{
				((BlockAdvancedRefinerPart) casing.getBlock(world)).unForm(world, casing.x, casing.y, casing.z);
			}
		}
	}

	@Override
	public void unForm(World world, int x, int y, int z) 
	{
		super.unForm(world, x, y, z);
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).unForm();
	}

	
	@Override
	public int getMaxHeat()
	{
		return Tiers.tierIron.getMaxHeat();
	}
}
