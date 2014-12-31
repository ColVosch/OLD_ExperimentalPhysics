package experimentalPhysics.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public final class ModBlocks
{
	
	public static Block refiner;
	public static Block advancedRefiner;
	public static Block advancedRefinerCasing;
	public static Block advancedRefinerInsertionLock;
	
	public static List<Block> modBlocks = new ArrayList<Block>();
	
	public static void init(CreativeTabs expPhysTab)
	{
		modBlocks.add(refiner = new BlockRefiner());
		modBlocks.add(advancedRefiner = new BlockAdvancedRefiner());
		modBlocks.add(advancedRefinerCasing = new BlockAdvancedRefinerCasing());
		modBlocks.add(advancedRefinerInsertionLock = new BlockAdvancedRefinerInsertionLock());
		

		fillCreativeTab(expPhysTab, modBlocks);		
	}
	
	
	private static void fillCreativeTab(CreativeTabs tab, List<Block> list)
    {
    	for(Block i : list)
    	{
    		i.setCreativeTab(tab);
    	}
    }
}
