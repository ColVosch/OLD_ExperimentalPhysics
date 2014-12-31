package experimentalPhysics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExperimentalPhysicsCreativeTab extends CreativeTabs
{

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
