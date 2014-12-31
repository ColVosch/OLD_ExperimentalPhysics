package experimentalPhysics.util;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class MetaBlock
{

	protected Block blocktype;
	protected int meta;

	public MetaBlock(Block blocktype, int meta)
	{
		this.blocktype = blocktype;
		this.meta = meta;
	}

	public int getMeta()
	{
		return meta;
	}

	public Block getBlocktype()
	{
		return blocktype;
	}
	
	public static MetaBlock getMetablockFromWorld(IBlockAccess access, int x, int y, int z)
	{
		return new MetaBlock(access.getBlock(x, y, z), access.getBlockMetadata(x, y, z));
	}
}
