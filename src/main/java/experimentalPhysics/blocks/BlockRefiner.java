package experimentalPhysics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class BlockRefiner extends BlockContainer
{
	private String name = "blockRefiner";
	private IIcon[] icons = new IIcon[3];
	
	protected BlockRefiner()
	{
		super(Material.iron);
		setBlockName(name);
		setStepSound(soundTypeMetal);
		setHardness(18.0F);
		setBlockTextureName(ExperimentalPhysics.getMainTextureFolder()+":purifier");
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityRefiner.class, "tileEntityRefiner");
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
		//TODO make block face player
		switch(side)
		{
			case 2:
				return 0;
			case 3:
				return 1;
			case 4:
				return 2;
			default:
				return 3;
		}
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3)
    {
		player.openGui(ExperimentalPhysics.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityRefiner();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":" + "refinerTop");
		icons[1] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":" + "refinerFront");
		icons[2] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":" + "refinerSide");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		int front = meta + 2;
		if (side == front)
		{
			return icons[1];
		}
		switch(side)
		{
			case 1: case 0:
				return icons[0];
			default:
				return icons[2];
		}	
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (hasTileEntity(meta))
        {
			ItemStack stack = ((TileEntityRefiner) world.getTileEntity(x, y, z)).getStackInSlot(0);
        	if (stack != null)
			{
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
			}
			world.removeTileEntity(x, y, z);
        }
    }
}












