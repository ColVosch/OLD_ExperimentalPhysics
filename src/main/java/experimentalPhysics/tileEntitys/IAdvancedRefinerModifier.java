package experimentalPhysics.tileEntitys;

public interface IAdvancedRefinerModifier
{
	public int getMinHeatChange();
	public int getMaxHeatChange();
	public float getDustChanceChange();
	public int getRefiningSpeedChange();
	
	public int[] getCoords();
}
