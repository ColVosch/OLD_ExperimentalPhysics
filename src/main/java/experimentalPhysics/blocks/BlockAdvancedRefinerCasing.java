package experimentalPhysics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.constants.Tier;
import experimentalPhysics.constants.Tiers;

import net.minecraft.client.renderer.texture.IIconRegister;

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
	public Tier getTier() 
	{
		return Tiers.tierIron;
	}
}
