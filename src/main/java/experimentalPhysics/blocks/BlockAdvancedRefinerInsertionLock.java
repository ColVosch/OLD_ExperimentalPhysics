package experimentalPhysics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;

public class BlockAdvancedRefinerInsertionLock extends BlockAdvancedRefinerPart implements ITileEntityProvider
{

	private static final String name = "blockAdvancedRefinerInsertionLock";

	public BlockAdvancedRefinerInsertionLock()
	{
		super();
		setBlockName(name);
		setBlockTextureName(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerInputTemp");
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefinerInsertionLock.class, "tileEntityAdvancedRefinerInsertionLock");
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerInputTemp");
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3)
    {
		player.openGui(ExperimentalPhysics.instance, 1, world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefinerInsertionLock();
	}
	
	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		super.form(world, x, y, z, xCore, yCore, zCore);
		((TileEntityAdvancedRefinerInsertionLock) world.getTileEntity(x, y, z)).form(xCore, yCore, zCore);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (hasTileEntity(meta))
        {
            TileEntityAdvancedRefinerInsertionLock tileEntity = (TileEntityAdvancedRefinerInsertionLock) world.getTileEntity(x, y, z);
				
            for (int i = 0; i < tileEntity.getSizeInventory(); i++)
			{
				ItemStack stack = tileEntity.getStackInSlot(i);
            	if (stack != null)
				{
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
				}
			}
			world.removeTileEntity(x, y, z);
        }
    }
}
