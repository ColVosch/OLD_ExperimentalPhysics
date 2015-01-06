package experimentalPhysics.constants;

public class Tiers
{
	public static Tier tierIron;
	
	public static void register()
	{
		tierIron = new Tier(ExpPhysConfig.getLimitHeatTierIron(), ExpPhysConfig.getThermConstantTierIron(), PhysicConstants.IRON_MASS_PER_BLOCK);
	}
}
