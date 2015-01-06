package experimentalPhysics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
		setBlockTextureName(ExperimentalPhysics.MODID+":purifier");
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityRefiner.class, "tileEntityRefiner");
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
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
		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":" + "refinerTop");
		icons[1] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":" + "refinerFront");
		icons[2] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":" + "refinerSide");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == meta)
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
		((TileEntityRefiner) world.getTileEntity(x, y, z)).dropItems();
		super.breakBlock(world, x, y, z, block, meta);
    }
}












