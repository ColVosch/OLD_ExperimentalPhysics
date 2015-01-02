package experimentalPhysics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import experimentalPhysics.util.MathHelper;
import experimentalPhysics.util.Position;


/**This class provides connected textures for all its subclasses.
 * You have to override registerBlockIcons(...) in a very specific way for it to work properly though.
 * @author ColVosch
 * @see BlockConnectedTexture#icons
 */
public abstract class BlockConnectedTexture extends Block
{	
	/**Array of the blocks icons. The index represents a sum of flags:<br>
	 * 1: open to the right<br>
	 * 2: open to the bottom<br>
	 * 4: open to the left<br>
	 * 8: open to the top<br>
	 * So for example icons[6] is used when referring to the texture used when connecting to the bottom and left block
	 * @see BlockConnectedTexture
	 */
	protected IIcon[] icons = new IIcon[16];

	/**Used to determine if the texture of a block can connect to another
	 * @param access the world both blocks are in
	 * @param blockPos the position this block
	 * @param connectorPos the position of the block this block should connect to
	 * @return whether the textures can connect or not
	 */
	public abstract boolean canTextureConnect(IBlockAccess access, Position blockPos, Position connectorPos);
	

	public BlockConnectedTexture(Material material)
	{
		super(material);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
	{
		boolean[] connectingSides;
		connectingSides = new boolean[4];
		if (canTextureConnect(access, new Position(x, y, z), new Position(x, y, z))) 
		{			
			switch(side)
			{
				case 0:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x+1, y, z);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y, z+1);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x-1, y, z);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y, z-1);
					break;
				}
				case 1:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x+1, y, z);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y, z+1);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x-1, y, z);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y, z-1);
					break;
				}
				case 2:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x-1, y, z);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y-1, z);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x+1, y, z);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y+1, z);
					break;
				}
				case 3:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x+1, y, z);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y-1, z);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x-1, y, z);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y+1, z);
					break;
				}
				case 4:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x, y, z+1);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y-1, z);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x, y, z-1);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y+1, z);
					break;
				}
				case 5:
				{
					connectingSides[0] = isConnectionPossible(access, x, y, z, x, y, z-1);
					connectingSides[1] = isConnectionPossible(access, x, y, z, x, y-1, z);
					connectingSides[2] = isConnectionPossible(access, x, y, z, x, y, z+1);
					connectingSides[3] = isConnectionPossible(access, x, y, z, x, y+1, z);
					break;
				}
			}
			IIcon icon = icons[MathHelper.boolArrayToInt(connectingSides)];
			return icon != null ? icon : icons[0];
		}
		return icons[0];
	}

	private boolean isConnectionPossible(IBlockAccess access, int x, int y, int z, int xConnector, int yConnector, int zConnector)
	{
		return (access.getBlock(xConnector, yConnector, zConnector) instanceof BlockConnectedTexture) && ((BlockConnectedTexture) access.getBlock(xConnector, yConnector, zConnector)).canTextureConnect(access, new Position(x, y, z), new Position(xConnector, yConnector, zConnector));
	}	
}
