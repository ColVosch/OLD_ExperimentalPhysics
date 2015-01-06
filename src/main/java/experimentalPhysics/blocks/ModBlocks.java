package experimentalPhysics.blocks;

import java.util.ArrayList;
import java.util.List;

import experimentalPhysics.guis.ExperimentalPhysicsCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public final class ModBlocks
{
	
	public static Block refiner;
	public static Block advancedRefiner;
	public static Block advancedRefinerCasing;
	public static Block advancedRefinerInsertionLock;
	public static Block advancedRefinerDisplay;
	
	public static void register()
	{
		List<Block> modBlocks = new ArrayList<Block>();
		modBlocks.add(refiner = new BlockRefiner());
		modBlocks.add(advancedRefiner = new BlockAdvancedRefiner());
		modBlocks.add(advancedRefinerCasing = new BlockAdvancedRefinerCasing());
		modBlocks.add(advancedRefinerInsertionLock = new BlockAdvancedRefinerInsertionLock());
		modBlocks.add(advancedRefinerDisplay = new BlockAdvancedRefinerDisplay());
		

		fillCreativeTab(ExperimentalPhysicsCreativeTab.instance(), modBlocks);		
	}
	
	
	/**Adds the given items to the given CreativeTab
	 * @param tab
	 * 		Tab the items should be added to
	 * @param list
	 * 		List of items to add
	 */
	private static void fillCreativeTab(CreativeTabs tab, List<Block> list)
    {
    	for(Block i : list)
    	{
    		i.setCreativeTab(tab);
    	}
    }
}
