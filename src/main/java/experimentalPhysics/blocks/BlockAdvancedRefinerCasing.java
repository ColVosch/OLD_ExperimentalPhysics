package experimentalPhysics.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.constants.ExpPhysConfig;
import experimentalPhysics.constants.Tiers;

public class BlockAdvancedRefinerCasing extends BlockAdvancedRefinerPart
{
	private String name = "blockAdvancedRefinerCasing";
	
	public BlockAdvancedRefinerCasing()
	{
		super();
		setBlockName(name);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull");
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[3] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpLeft");
		icons[7] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUp");
		icons[6] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpRight");
		icons[11] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingLeft");
		icons[15] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingMiddle");
		icons[14] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingRight");
		icons[9] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownLeft");
		icons[13] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDown");
		icons[12] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownRight");	
		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull");
	}

	@Override
	public int getMaxHeat() 
	{
		return Tiers.tierIron.getMaxHeat();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		//TODO remove debug code
		System.out.println(world.getBlockMetadata(x, y, z));
        return false;
    }
}
