package experimentalPhysics.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.guis.ExperimentalPhysicsCreativeTab;

public final class ModItems
{
	public static Item enderPearlCore;
	public static Item enderPearlReusable;
	public static Item gunpowderFuelingCell;
	public static Item itemBlockAdvancedRefiner;
	
	public static void register()
	{
		List<Item> modItems = new ArrayList<Item>();
		
		modItems.add(enderPearlReusable = new ItemEnderPearlReusable());
		
		modItems.add(gunpowderFuelingCell = new Item().setUnlocalizedName("itemGunpowderFuelingCell").setTextureName(ExperimentalPhysics.MODID+":"+"gunpowderFuelingCell"));
		GameRegistry.registerItem(gunpowderFuelingCell, "itemGunpowderFuelingCell");
		modItems.add(enderPearlCore = new Item().setUnlocalizedName("itemEnderPearlCore").setTextureName(ExperimentalPhysics.MODID+":"+"enderPearlCore"));
		GameRegistry.registerItem(enderPearlCore, "itemEnderPearlCore");
		
		fillCreativeTab(ExperimentalPhysicsCreativeTab.instance(), modItems);
	}
	
	/**Adds the given items to the given CreativeTab
	 * @param tab
	 * 		Tab the items should be added to
	 * @param list
	 * 		List of items to add
	 */
	private static void fillCreativeTab(CreativeTabs tab, List<Item> list)
    {
    	for(Item i : list)
    	{
    		i.setCreativeTab(tab);
    	}
    }
}
