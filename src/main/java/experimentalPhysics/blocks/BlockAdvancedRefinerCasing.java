package experimentalPhysics.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;

public class BlockAdvancedRefinerCasing extends BlockAdvancedRefinerPart
{
	private String name = "blockAdvancedRefinerCasing";
	
	public BlockAdvancedRefinerCasing()
	{
		super();
		setBlockName(name);
		setBlockTextureName(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingFull");
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[3] =  iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingUpLeft");
		icons[7] =  iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingUp");
		icons[6] =  iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingUpRight");
		icons[11] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingLeft");
		icons[15] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingMiddle");
		icons[14] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingRight");
		icons[9] =  iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingDownLeft");
		icons[13] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingDown");
		icons[12] = iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingDownRight");	
		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.getMainTextureFolder() + ":advancedRefinerCasingFull");
	}

	@Override
	public boolean canTextureConnect(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector)
	{
		return super.canTextureConnect(access, x, y, z, xConnector, yConnector, zConnector);
	}
}
