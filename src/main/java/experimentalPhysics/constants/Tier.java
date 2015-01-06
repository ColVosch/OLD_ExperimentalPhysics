package experimentalPhysics.constants;

public class Tier
{
	private int maxHeat;
	private float thermConstant;
	private int massPerBlock;
	
	public Tier(int maxHeat, float thermConstant, int massPerBlock)
	{
		this.maxHeat = maxHeat;
		this.thermConstant = thermConstant;
		this.massPerBlock = massPerBlock;
	}
	
	public int getMaxHeat()
	{
		return maxHeat;
	}

	public float getThermalConstant()
	{
		return this.thermConstant;
	}

	public int getMassPerBlock()
	{
		return massPerBlock;
	}
	
}
