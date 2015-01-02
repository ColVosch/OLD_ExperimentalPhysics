package experimentalPhysics.tileEntitys;

/**Implement this, if you are an AdvancedRefinerPart, to change an AdvancedRefiners behavior.
 * @author ColVosch
 *
 */
public interface IAdvancedRefinerModifier extends IMultiblockInteractor
{
	public int getMinHeatChange();
	public int getMaxHeatChange();
	public float getDustChanceChange();
	public int getRefiningSpeedChange();
}
