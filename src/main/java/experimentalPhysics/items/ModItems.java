package experimentalPhysics.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.ExperimentalPhysics;

public final class ModItems
{
	public static Item enderPearlCore;
	public static Item enderPearlReusable;
	public static Item gunpowderFuelingCell;
	public static Item itemBlockAdvancedRefiner;
	
	public static List<Item> modItems = new ArrayList<Item>();
	
	public static void init(CreativeTabs expPhysTab)
	{
		modItems.add(enderPearlReusable = new ItemEnderPearlReusable());
		
		modItems.add(gunpowderFuelingCell = new Item().setUnlocalizedName("itemGunpowderFuelingCell").setTextureName(ExperimentalPhysics.getMainTextureFolder()+":"+"gunpowderFuelingCell"));
		GameRegistry.registerItem(gunpowderFuelingCell, "itemGunpowderFuelingCell");
		modItems.add(enderPearlCore = new Item().setUnlocalizedName("itemEnderPearlCore").setTextureName(ExperimentalPhysics.getMainTextureFolder()+":"+"enderPearlCore"));
		GameRegistry.registerItem(enderPearlCore, "itemEnderPearlCore");
		
		fillCreativeTab(expPhysTab, modItems);
	}
	
	private static void fillCreativeTab(CreativeTabs tab, List<Item> list)
    {
    	for(Item i : list)
    	{
    		i.setCreativeTab(tab);
    	}
    }
}
