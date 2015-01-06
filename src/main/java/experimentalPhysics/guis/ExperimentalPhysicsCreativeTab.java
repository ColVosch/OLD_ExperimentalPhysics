package experimentalPhysics.guis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ExperimentalPhysicsCreativeTab extends CreativeTabs
{
	private static ExperimentalPhysicsCreativeTab instance;
	
	public static ExperimentalPhysicsCreativeTab instance()
	{
		if (instance == null)
		{
			instance = new ExperimentalPhysicsCreativeTab();
		}
		return instance;
	}
	
	
	public ExperimentalPhysicsCreativeTab()
	{
		super("experimentalPhysics");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Items.ender_pearl;
	}
}
