package experimentalPhysics.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.constants.Tier;
import experimentalPhysics.constants.Tiers;
import experimentalPhysics.guis.GuiHandler;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerHeaterFurnace;
import experimentalPhysics.tileEntitys.TileEntityStoring;
import experimentalPhysics.util.Position;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAdvancedRefinerHeaterFurnace extends BlockAdvancedRefinerPart implements ITileEntityProvider
{		
	private static final String name = "blockAdvancedRefinerHeaterFurnace";

	public BlockAdvancedRefinerHeaterFurnace()
	{
		super();
		setBlockName(name);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerHeaterFurnaceFull");
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefinerHeaterFurnace.class, TileEntityAdvancedRefinerHeaterFurnace.NAME);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefinerHeaterFurnace();
	}

	@Override
	public Tier getTier()
	{
		return Tiers.tierIron;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerHeaterFurnaceFull");
		
//		icons[3] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUpLeft");
//		icons[7] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUp");
//		icons[6] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayUpRight");
//		icons[11] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayLeft");
//		icons[15] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayMiddle");
//		icons[14] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayRight");
//		icons[9] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDownLeft");
//		icons[13] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDown");
//		icons[12] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayDownRight");	
//		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerDisplayFull");
	}
	
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		super.form(world, x, y, z, xCore, yCore, zCore);
		((TileEntityAdvancedRefinerHeaterFurnace) world.getTileEntity(x, y, z)).form(new Position(xCore, yCore, zCore));
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3)
    {
		player.openGui(ExperimentalPhysics.instance, GuiHandler.ID_ADVANCED_REFINER_HEATER_FURNACE, world, x, y, z);
		return true;
	}
	
	 public void randomDisplayTick(World world, int x, int y, int z, Random rnd)
	 {
		 if (((TileEntityAdvancedRefinerHeaterFurnace) world.getTileEntity(x, y, z)).isBurning())
		{
			world.spawnParticle("flame", x + 1, y + rnd.nextFloat(), z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("flame", x, y + rnd.nextFloat(), z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("flame", x + rnd.nextFloat(), y + 1, z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("flame", x + rnd.nextFloat(), y, z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("flame", x + rnd.nextFloat(), y + rnd.nextFloat(), z + 1, 0, 0, 0);
			world.spawnParticle("flame", x + rnd.nextFloat(), y + rnd.nextFloat(), z, 0, 0, 0);
			world.spawnParticle("smoke", x + 1, y + rnd.nextFloat(), z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("smoke", x, y + rnd.nextFloat(), z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("smoke", x + rnd.nextFloat(), y + 1, z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("smoke", x + rnd.nextFloat(), y, z + rnd.nextFloat(), 0, 0, 0);
			world.spawnParticle("smoke", x + rnd.nextFloat(), y + rnd.nextFloat(), z + 1, 0, 0, 0);
			world.spawnParticle("smoke", x + rnd.nextFloat(), y + rnd.nextFloat(), z, 0, 0, 0);
		}
	 }
	 
	 @Override
		public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	    {
			((TileEntityStoring) world.getTileEntity(x, y, z)).dropItems();
			super.breakBlock(world, x, y, z, block, meta);
	    }
}
