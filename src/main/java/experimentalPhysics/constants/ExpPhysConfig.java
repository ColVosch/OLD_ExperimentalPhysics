package experimentalPhysics.constants;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ExpPhysConfig
{
	private static int limitHeatTierIron;
	private static float thermConstantTierIron;
	
	private static int coolDownFactor;
	
	public static void init(File location)
	{
		Configuration config = new Configuration(location);
		config.load();
		limitHeatTierIron = config.getInt("tierIronMaxHeat", Configuration.CATEGORY_GENERAL, 1530, 100, 2000, StatCollector.translateToLocal("propperty.tier1MaxHeat.comment"), "propperty.tier1MaxHeat.display");
		thermConstantTierIron = config.getFloat("tierIronTherm", Configuration.CATEGORY_GENERAL, 0.1781f, 0, 2, StatCollector.translateToLocal("propperty.tier1ThermConstant.comment"), "propperty.tier1ThermConstant.display");
		coolDownFactor = config.getInt("coolDownFactor", Configuration.CATEGORY_GENERAL, 1000, 1, 1000, StatCollector.translateToLocal("propperty.coolDownFactor.comment"), "propperty.coolDownFactor.display");
		
		config.save();
	}

	public static int getLimitHeatTierIron()
	{
		return limitHeatTierIron;
	}

	public static float getThermConstantTierIron()
	{
		return thermConstantTierIron;
	}

	public static int getCoolDownFactor()
	{
		return coolDownFactor;
	}
	
}
