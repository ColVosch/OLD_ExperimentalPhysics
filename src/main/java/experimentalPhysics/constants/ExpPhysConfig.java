package experimentalPhysics.constants;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ExpPhysConfig extends Configuration
{
	private static int limitHeatTierIron;
	
	public static void init(File location)
	{
		ExpPhysConfig config = new ExpPhysConfig(location);
		config.load();
		limitHeatTierIron = config.getInt("tierIronMaxHeat", CATEGORY_GENERAL, 1530, 100, 2000, StatCollector.translateToLocal("propperty.tier1MaxHeat.comment"), "propperty.tier1MaxHeat.display");
		config.save();
	}

	public ExpPhysConfig(File location)
	{
		super(location);
	}

	public static int getLimitHeatTierIron()
	{
		return limitHeatTierIron;
	}
	
}
