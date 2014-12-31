package experimentalPhysics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import experimentalPhysics.util.MathHelper;

public abstract class BlockConnectedTexture extends Block
{
	protected IIcon[] icons = new IIcon[16];

	
	public abstract boolean canTextureConnect(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector);
	

	public BlockConnectedTexture(Material material)
	{
		super(material);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
	{
		boolean[] connectingSides;
		connectingSides = new boolean[4];
		if (canTextureConnect(access, x, y, z, x, y, z)) 
		{			
			switch(side)
			{
				case 0:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x+1, y, z);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y, z+1);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x-1, y, z);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y, z-1);
					break;
				}
				case 1:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x+1, y, z);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y, z+1);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x-1, y, z);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y, z-1);
					break;
				}
				case 2:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x-1, y, z);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y-1, z);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x+1, y, z);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y+1, z);
					break;
				}
				case 3:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x+1, y, z);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y-1, z);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x-1, y, z);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y+1, z);
					break;
				}
				case 4:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x, y, z+1);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y-1, z);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x, y, z-1);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y+1, z);
					break;
				}
				case 5:
				{
					connectingSides[0] = canTextureConnectToBlock(access, x, y, z, x, y, z-1);
					connectingSides[1] = canTextureConnectToBlock(access, x, y, z, x, y-1, z);
					connectingSides[2] = canTextureConnectToBlock(access, x, y, z, x, y, z+1);
					connectingSides[3] = canTextureConnectToBlock(access, x, y, z, x, y+1, z);
					break;
				}
			}
			IIcon icon = icons[MathHelper.boolArrayToInt(connectingSides)];
			return icon != null ? icon : icons[0];
		}
		return icons[0];
	}

	private boolean canTextureConnectToBlock(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector)
	{
		return (access.getBlock(xConnector, yConnector, zConnector) instanceof BlockConnectedTexture) && ((BlockConnectedTexture) access.getBlock(xConnector, yConnector, zConnector)).canTextureConnect(access, xConnector, yConnector, zConnector, x, y, z);
	}	
}
