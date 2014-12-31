package experimentalPhysics.util;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class LocatedBlock extends MetaBlock
{
	protected int xCoord;
	protected int yCoord;
	protected int zCoord;
	
	public LocatedBlock(Block blocktype, int xCoord, int yCoord, int zCoord, int meta)
	{
		super(blocktype, meta);
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}

	public LocatedBlock(MetaBlock metaBlock, int xCoord, int yCoord, int zCoord)
	{
		super(metaBlock.getBlocktype(), metaBlock.getMeta());
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}
	
	public int getxCoord()
	{
		return xCoord;
	}

	public int getyCoord()
	{
		return yCoord;
	}

	public int getzCoord()
	{
		return zCoord;
	}	
	
	public static LocatedBlock getLocatedBlockFromWorld(IBlockAccess access, int x, int y, int z)
	{
		return new LocatedBlock(getMetablockFromWorld(access, x, y, z), x, y, z);
	}
}
