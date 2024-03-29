package experimentalPhysics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.constants.Tier;
import experimentalPhysics.constants.Tiers;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerDisplay;
import experimentalPhysics.util.Position;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAdvancedRefinerDisplay extends BlockAdvancedRefinerPart implements ITileEntityProvider
{

	private static final String NAME = "blockAdvancedRefinerDisplay";

	public BlockAdvancedRefinerDisplay()
	{
		setBlockName(NAME);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerDisplayFull");
		GameRegistry.registerBlock(this, NAME);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefinerDisplay.class, TileEntityAdvancedRefinerDisplay.NAME);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[3] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUpLeft");
		icons[7] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUp");
		icons[6] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUpRight");
		icons[11] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayLeft");
		icons[15] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayMiddle");
		icons[14] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayRight");
		icons[9] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDownLeft");
		icons[13] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDown");
		icons[12] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDownRight");	
		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayFull");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefinerDisplay();
	}

	@Override
	public Tier getTier()
	{
		return Tiers.tierIron;
	}
	
	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		super.form(world, x, y, z, xCore, yCore, zCore);
		((TileEntityAdvancedRefinerDisplay) world.getTileEntity(x, y, z)).form(new Position(xCore, yCore, zCore));
	}

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3)
    {
		player.openGui(ExperimentalPhysics.instance, 2, world, x, y, z);
		return true;
	}
	
	@Override
	public void unForm(World world, int x, int y, int z)
	{
		super.unForm(world, x, y, z);
		((TileEntityAdvancedRefinerDisplay) world.getTileEntity(x, y, z)).unForm();
	}
}
